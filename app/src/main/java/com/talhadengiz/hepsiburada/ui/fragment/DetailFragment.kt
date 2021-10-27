package com.talhadengiz.hepsiburada.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.ui.viewModel.DetailFragmentVM

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailFragmentVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailFragmentVM::class.java)
        // TODO: Use the ViewModel
    }

}