package com.talhadengiz.searhmedia.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.searhmedia.R
import com.talhadengiz.searhmedia.data.model.Result
import com.talhadengiz.searhmedia.databinding.ItemRecyclerviewBinding
import com.talhadengiz.searhmedia.util.Format
import com.talhadengiz.searhmedia.util.convertToDateFormat
import com.talhadengiz.searhmedia.util.downloadFromUrl
import com.talhadengiz.searhmedia.util.placeHolderProgressBar

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

        holder.binding.apply {
            tvCollectionName.text = media.collectionName ?: media.trackName
            tvCollectionPrice.text = tvCollectionPrice.context.getString(
                R.string.currency,
                (media.collectionPrice ?: media.price).toString()
            )
            tvReleaseDate.text =
                media.releaseDate?.convertToDateFormat(
                    Format.FROM_DATE_FORMAT,
                    Format.TO_DATE_FORMAT
                )
            ivItem.downloadFromUrl(
                media.artworkUrl100,
                placeHolderProgressBar(holder.itemView.context)
            )
        }

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