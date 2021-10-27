package com.talhadengiz.hepsiburada.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.talhadengiz.hepsiburada.data.adapter.SearchRecyclerViewAdapter
import com.talhadengiz.hepsiburada.databinding.FragmentSearchBinding
import com.talhadengiz.hepsiburada.ui.viewModel.SearchFragmentVM

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchFragmentVM

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var searchAdapter: SearchRecyclerViewAdapter

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
        eventHandler()
        observe()
    }

    private fun init() {
        searchAdapter = SearchRecyclerViewAdapter()
        viewModel = ViewModelProvider(this).get(SearchFragmentVM::class.java)
        binding?.rvData?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter
        }
    }

    private fun eventHandler() {
        binding?.etSearch?.addTextChangedListener { editable ->
            editable?.let {
                viewModel.getData(editable.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        viewModel.dataLiveData.observe(viewLifecycleOwner, {
            Log.d("test", it.results.toString())
            searchAdapter.resultList = it.results
            searchAdapter.notifyDataSetChanged()
        })
    }

}