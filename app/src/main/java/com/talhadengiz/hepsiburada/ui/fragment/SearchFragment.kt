package com.talhadengiz.hepsiburada.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.databinding.SearchFragmentBinding
import com.talhadengiz.hepsiburada.ui.viewModel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        eventHandler()
    }

    private fun init(){
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private fun eventHandler(){
        binding?.etSearch?.addTextChangedListener { editable ->
            editable?.let {
                viewModel.getData(editable.toString())
            }
        }
    }

}