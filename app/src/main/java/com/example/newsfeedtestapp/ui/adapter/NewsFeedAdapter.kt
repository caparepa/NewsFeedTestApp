package com.example.newsfeedtestapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedtestapp.R
import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.utils.setOneOffClickListener

class NewsFeedAdapter(
    private val context: Context,
    private val newsList: List<Hit>,
    private val onClick: (Hit) -> Unit = { _ -> }
) : RecyclerView.Adapter<NewsFeedAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val mainContainer = itemView.findViewById<ConstraintLayout>(R.id.clMainContainer)
        val storyTitle = itemView.findViewById<TextView>(R.id.tvStoryTitle)
        val author = itemView.findViewById<TextView>(R.id.tvAuthor)
        val createdAt = itemView.findViewById<TextView>(R.id.tvCreatedAt)

        fun bind(hit: Hit, onClick: (Hit) -> Unit) {

            val clMainContainer = mainContainer
            val tvStoryTitle = storyTitle
            val tvAuthor = author
            val tvCreatedAt = createdAt

            tvStoryTitle.text = hit.title ?: hit.storyTitle
            tvAuthor.text = hit.author
            tvCreatedAt.text = hit.createdAt

            clMainContainer.setOneOffClickListener {
                onClick.invoke(hit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.news_list_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hit: Hit = newsList.get(position)
        holder.bind(hit, onClick)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}