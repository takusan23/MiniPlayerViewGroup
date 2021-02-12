package io.github.takusan23.miniplayerviewgroup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.takusan23.miniplayerviewgroup.R

/**
 * [io.github.takusan23.miniplayerviewgroup.fragment.VideoListFragment]で一覧表示の際に使うAdapter
 *
 * @param videoList タイトル配列
 * @param videoItemClick 一覧の項目を押したときに呼ばれる
 * */
class VideoListAdapter(private val videoList: ArrayList<String>, val videoItemClick: (String) -> Unit) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.adapter_video_list_title_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = videoList[position]
        holder.titleTextView.text = data
        holder.titleTextView.setOnClickListener {
            videoItemClick(data)
        }
    }

    override fun getItemCount(): Int = videoList.size

}