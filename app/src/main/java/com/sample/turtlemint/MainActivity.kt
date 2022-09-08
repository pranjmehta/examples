package com.sample.turtlemint

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), IssueClickListener {

    private var gitIssues: List<ResponseData>? = null

    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recycleView)

        getGitIssues()
    }

    private fun getGitIssues() {

        val apiInterface = ApiInterface.create().getGitIssues()

        apiInterface.enqueue(object : Callback<List<ResponseData>> {
            override fun onResponse(
                call: Call<List<ResponseData>>?,
                response: Response<List<ResponseData>>?
            ) {

                if (response?.body() != null)
                    gitIssues = response.body()

                setData(gitIssues)
            }

            override fun onFailure(call: Call<List<ResponseData>>?, t: Throwable?) {

                Toast.makeText(this@MainActivity, "" + t.toString(), Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun setData(gitIssuesList: List<ResponseData>?) {

        if (!gitIssuesList.isNullOrEmpty()) {

            val adapter = CustomAdapter(gitIssuesList, this)
            recyclerview?.layoutManager = LinearLayoutManager(this)
            recyclerview?.adapter = adapter
        }
    }

    override fun onIssueClick(number: Int) {

        val intent = Intent(this, IssueCommentsActivity::class.java)
        val extras = Bundle()
        extras.putInt("issueNumber", number)
        intent.putExtras(extras)
        startActivity(intent)
    }

}