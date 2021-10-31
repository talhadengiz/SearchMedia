package com.talhadengiz.hepsiburada.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.data.adapter.SearchRecyclerViewAdapter
import com.talhadengiz.hepsiburada.databinding.FragmentSearchBinding
import com.talhadengiz.hepsiburada.ui.custom.SwitchCategoryButton
import com.talhadengiz.hepsiburada.viewModel.SearchFragmentVM
import com.talhadengiz.hepsiburada.util.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchFragmentVM

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var searchAdapter: SearchRecyclerViewAdapter
    private lateinit var media:String
    private var term:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupRecyclerview()
        eventHandler()
        observe()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(SearchFragmentVM::class.java)
    }

    private fun setupRecyclerview(){
        searchAdapter = SearchRecyclerViewAdapter()
        binding?.rvData?.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(context, 2)
            addOnScrollListener(this@SearchFragment.scrollListener)
        }
    }

    private fun eventHandler() {
        binding?.switchControl?.onSelectionChanged = ::onSwitchChanged
        binding?.switchControl?.setCategory(0)
        var job: Job? = null
        binding?.etSearch?.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(Constants.DELAY_TIME)
                editable?.let {
                    viewModel.dataLiveData_ = null
                    viewModel.page = 1
                    isLoading = true
                    term = it.toString()
                    if(term.length>=2){
                        viewModel.getData(editable.toString(), media)
                    }
                }
            }
        }

        searchAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("search", it)
                putString("media",media)
            }
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        viewModel.dataLiveData.observe(viewLifecycleOwner, {
            it?.let {
                isLoading = false
                searchAdapter.differ.submitList(it.results.toList())
                val totalPages = it.resultCount / 20 + 2
                isLastPage = viewModel.page == totalPages
                if(isLastPage){
                    binding?.rvData?.setPadding(0,0,0,0)
                }
            }
        })
    }

    private fun onSwitchChanged(category: SwitchCategoryButton.Category) {
        viewModel.dataLiveData_ = null
        viewModel.page = 1
        media = category.queryName
        if(term.length>=2){
            viewModel.getData(term,media)
        }
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if(shouldPaginate){
                isLoading = true
                viewModel.getData(term, media)
                isScrolling = false
            }
        }
    }

}