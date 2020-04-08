package com.vicky7230.newsapp.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vicky7230.newsapp.R
import com.vicky7230.newsapp.data.network.Article
import com.vicky7230.newsapp.ui.news.NewsAdapter.NewsViewHolder
import com.vicky7230.newsapp.utils.CommonUtils.isValidUrl
import kotlinx.android.synthetic.main.news_list_item.view.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(private var newsArrayList: ArrayList<Article>) :
    RecyclerView.Adapter<NewsViewHolder>() {
    interface Callback {
        fun onItemClick(article: Article)
    }

    private var callback: Callback? = null
    private val inputDateFormat: SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    private val outputDateFormat: SimpleDateFormat =
        SimpleDateFormat("MMM dd yyyy '  •  ' hh:mm a", Locale.ENGLISH)

    fun setCallback(callback: Callback?) {
        this.callback = callback
    }

    fun updateItems(newsArrayList: ArrayList<Article>) {
        this.newsArrayList.clear()
        this.newsArrayList = newsArrayList
        notifyDataSetChanged()
    }

    fun getItems() :ArrayList<Article>{
        return newsArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsViewHolder = NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        )
        newsViewHolder.itemView.setOnClickListener { v: View? ->
            val position = newsViewHolder.adapterPosition
            if (callback != null) {
                callback!!.onItemClick(newsArrayList[position])
            }
        }
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(newsArrayList[position])
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    inner class NewsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun onBind(news: Article) {
            if (isValidUrl(news.urlToImage)) {
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .error(R.drawable.ic_warning)
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(itemView.image)

            } else {
                itemView.image.setImageResource(R.drawable.ic_warning)
            }

            itemView.title.text = news.title

            try {
                val date = inputDateFormat.parse(news.publishedAt)
                itemView.date_time.text = outputDateFormat.format(date)
            } catch (e: Exception) {
                Timber.e("Date Parsing Error : ${e.localizedMessage}")
            }
        }
    }

}