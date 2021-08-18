package com.example.newsfeedtestapp.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeedtestapp.data.model.Hit

class NewsFeedAdapter(
    private val context: Context,
    private val newsList: List<Hit>?,
    private val onClick: (Hit) -> Unit = { _ -> }
): RecyclerView.Adapter<NewsFeedAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View): RecyclerView.ViewHolder(listItemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}