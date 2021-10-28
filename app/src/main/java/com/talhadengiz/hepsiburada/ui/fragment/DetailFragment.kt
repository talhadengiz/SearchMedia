package com.talhadengiz.hepsiburada.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.data.model.Result
import com.talhadengiz.hepsiburada.databinding.FragmentDetailBinding
import com.talhadengiz.hepsiburada.ui.viewModel.DetailFragmentVM
import com.talhadengiz.hepsiburada.util.downloadFromUrl
import com.talhadengiz.hepsiburada.util.placeHolderProgressBar

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailFragmentVM
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var search: Result
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        setData()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(DetailFragmentVM::class.java)
        search = args.search
    }

    private fun setData() {
        binding.ivItemDetail.apply {
            downloadFromUrl(
                search.artworkUrl100,
                placeHolderProgressBar(context)
            )
        }
        binding.tvCollectionName.text = search.collectionName
        binding.tvCollectionPrice.text = search.collectionPrice.toString()
        binding.tvReleaseDate.text = search.releaseDate
    }

}