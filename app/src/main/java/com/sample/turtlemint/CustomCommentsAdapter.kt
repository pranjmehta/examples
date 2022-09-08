package com.sample.turtlemint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomCommentsAdapter(private val list: List<CommentsResponseData>) :
    RecyclerView.Adapter<CustomCommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_comments, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        item.body?.let { holder.webView.loadData(it, "text/html", "utf-8") };

        holder.tvUpdatedDate.text = item.updated_at?.let { DateUtils.getDate(it) }
        holder.tvUserName.text = item.user?.login

        Glide.with(holder.itemView.context)
            .load(item.user?.avatar_url)
            .into(holder.ivUserProfile)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val webView: WebView = itemView.findViewById(R.id.wv_description)
        val tvUpdatedDate: TextView = itemView.findViewById(R.id.tv_updated_at)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_user_name)
        val ivUserProfile: AppCompatImageView = itemView.findViewById(R.id.iv_user_profile)
        val clIssue: ConstraintLayout = itemView.findViewById(R.id.cl_issue)
    }
}