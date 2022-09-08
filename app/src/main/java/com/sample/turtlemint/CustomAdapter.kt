package com.sample.turtlemint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(private val list: List<ResponseData>, private val listener: IssueClickListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_data, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        holder.tvName.text = item.title
        holder.tvDescription.text = item.body

        holder.tvUpdatedDate.text = item.updated_at?.let { DateUtils.getDate(it) }
        holder.tvUserName.text = item.user?.login

        Glide.with(holder.itemView.context)
            .load(item.user?.avatar_url)
            .into(holder.ivUserProfile)

        holder.clIssue.setOnClickListener {

            item.number?.let { it1 -> listener.onIssueClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvUpdatedDate: TextView = itemView.findViewById(R.id.tv_updated_at)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_user_name)
        val ivUserProfile: AppCompatImageView = itemView.findViewById(R.id.iv_user_profile)
        val clIssue: ConstraintLayout = itemView.findViewById(R.id.cl_issue)
    }
}
