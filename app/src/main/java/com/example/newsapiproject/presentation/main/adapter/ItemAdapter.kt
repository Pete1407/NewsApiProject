package com.example.newsapiproject.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiproject.R
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.databinding.ItemArticleSaveBinding

class ItemAdapter(val list: ArrayList<Article>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.item_article_save, null, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun deleteItem(position : Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemArticleSaveBinding.bind(view)

        fun setItem(article: Article) {
            binding.titleNews.text = article.title
            binding.etc.text = article.description
        }
    }
}