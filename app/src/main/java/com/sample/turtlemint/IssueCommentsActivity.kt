package com.sample.turtlemint

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IssueCommentsActivity : AppCompatActivity() {

    private var recyclerview: RecyclerView? = null
    private var tvCommentsUnavailable: TextView? = null

    private var gitIssueComments: List<CommentsResponseData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_comments)

        recyclerview = findViewById(R.id.comments_recycleView)
        tvCommentsUnavailable = findViewById(R.id.tv_comments_unavailable)

        val issueNumber = intent.getIntExtra("issueNumber", 0)

        getComments(issueNumber)
    }

    private fun getComments(issueNumber: Int) {

        val apiInterface = ApiInterface.create().getComments(issueNumber)

        apiInterface.enqueue(object : Callback<List<CommentsResponseData>> {
            override fun onResponse(
                call: Call<List<CommentsResponseData>>?,
                response: Response<List<CommentsResponseData>>?
            ) {

                if (response?.body() != null)
                    gitIssueComments = response.body()

                setData(gitIssueComments)
            }

            override fun onFailure(call: Call<List<CommentsResponseData>>?, t: Throwable?) {

                Toast.makeText(this@IssueCommentsActivity, "" + t.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }

    private fun setData(gitIssueComments: List<CommentsResponseData>?) {

        if (!gitIssueComments.isNullOrEmpty()) {

            tvCommentsUnavailable?.visibility = View.GONE

            val adapter = CustomCommentsAdapter(gitIssueComments)
            recyclerview?.layoutManager = LinearLayoutManager(this)
            recyclerview?.adapter = adapter
        } else {
            tvCommentsUnavailable?.visibility = View.VISIBLE
            recyclerview?.visibility = View.GONE
        }
    }
}