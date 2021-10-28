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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.data.adapter.SearchRecyclerViewAdapter
import com.talhadengiz.hepsiburada.databinding.FragmentSearchBinding
import com.talhadengiz.hepsiburada.ui.custom.SwitchCategoryButton
import com.talhadengiz.hepsiburada.ui.viewModel.SearchFragmentVM

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
        observe()
        eventHandler()
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
        binding?.switchControl?.onSelectionChanged = ::onSwitchChanged
        binding?.switchControl?.setCategory(0)
        binding?.etSearch?.addTextChangedListener { editable ->
            editable?.let {
                term = it.toString()
                viewModel.getData(editable.toString(), media)
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
            searchAdapter.resultList = it.results
            searchAdapter.notifyDataSetChanged()
        })
    }

    private fun onSwitchChanged(category: SwitchCategoryButton.Category) {
        media = category.queryName
        viewModel.getData(term,media)
    }

}