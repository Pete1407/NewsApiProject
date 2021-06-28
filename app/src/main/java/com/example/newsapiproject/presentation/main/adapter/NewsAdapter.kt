package com.example.newsapiproject.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapiproject.R
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.util.SimpleDate
import com.example.newsapiproject.databinding.ItemNewsListBinding

class NewsAdapter(val list: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var clickListener : ((Article) -> Unit)? = null

    fun setOnClickListenr(listener : (article : Article) -> Unit){
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.item_news_list, null, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = list.get(position)
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(data : ArrayList<Article>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var binding : ItemNewsListBinding = ItemNewsListBinding.bind(view)

        fun setItem(article: Article){
            binding.tvName.text = article.title
            binding.tvDescription.text = article.description
            val convertDateFormat = article.publishedAt?.let { SimpleDate.setDateFormat(it) }
            binding.tvPublishDate.text = convertDateFormat
            //binding.tvPublishDate.text = article.publishedAt
            Log.d("debug","${article.urlToImage}")
            Glide.with(binding.imgArticle.context)
                .load(article.urlToImage)
                .into(binding.imgArticle)

            binding.root.setOnClickListener {
                clickListener?.invoke(article)
            }
        }
    }
}