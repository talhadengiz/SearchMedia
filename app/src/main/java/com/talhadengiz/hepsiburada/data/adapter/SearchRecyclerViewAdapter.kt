package com.talhadengiz.hepsiburada.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.hepsiburada.data.model.DataResponse
import com.talhadengiz.hepsiburada.data.model.Result
import com.talhadengiz.hepsiburada.databinding.ItemRecyclerviewBinding
import com.talhadengiz.hepsiburada.util.downloadFromUrl
import com.talhadengiz.hepsiburada.util.placeHolderProgressBar

class SearchRecyclerViewAdapter :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>() {

    var resultList: List<Result>? = listOf()

    class SearchViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.tvCollectionName.text = resultList?.get(position)?.collectionName
        holder.binding.tvCollectionPrice.text = resultList?.get(position)?.collectionPrice.toString()
        holder.binding.tvReleaseDate.text = resultList?.get(position)?.releaseDate

        holder.binding.ivItem.downloadFromUrl(
            resultList?.get(position)?.artworkUrl100,
            placeHolderProgressBar(holder.itemView.context)
        )
    }

    override fun getItemCount(): Int = resultList!!.size
}