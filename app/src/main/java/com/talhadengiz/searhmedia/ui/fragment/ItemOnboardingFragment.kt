package com.talhadengiz.searhmedia.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.talhadengiz.searhmedia.databinding.FragmentItemOnboardingBinding

class ItemOnboardingFragment(private val resId: Int, private val title: String) : Fragment() {

    private var _binding: FragmentItemOnboardingBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemOnboardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivItemOnboarding.setImageResource(resId)
        binding.tvTitle.text = title
    }
}