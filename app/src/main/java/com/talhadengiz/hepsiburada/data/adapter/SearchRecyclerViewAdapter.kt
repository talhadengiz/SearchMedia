package com.talhadengiz.hepsiburada.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.hepsiburada.data.model.DataResponse
import com.talhadengiz.hepsiburada.data.model.Result
import com.talhadengiz.hepsiburada.databinding.ItemRecyclerviewBinding
import com.talhadengiz.hepsiburada.util.convertToDateFormat
import com.talhadengiz.hepsiburada.util.downloadFromUrl
import com.talhadengiz.hepsiburada.util.placeHolderProgressBar

class SearchRecyclerViewAdapter :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>() {

    class SearchViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val media = differ.currentList[position]

        holder.binding.tvCollectionName.text = media.collectionName ?: media.trackName
        holder.binding.tvCollectionPrice.text = "$" + media.collectionPrice.toString()
        holder.binding.tvReleaseDate.text = media.releaseDate?.convertToDateFormat("yyyy-MM-dd'T'HH:mm:sss","dd.MM.yyyy")


        holder.binding.ivItem.downloadFromUrl(
            media.artworkUrl100,
            placeHolderProgressBar(holder.itemView.context)
        )

        holder.binding.ivItem.setOnClickListener {
            onItemClickListener?.let {
                it(media)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}