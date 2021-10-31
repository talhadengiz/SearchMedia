package com.talhadengiz.hepsiburada.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.rd.animation.type.AnimationType
import com.talhadengiz.hepsiburada.R
import com.talhadengiz.hepsiburada.data.adapter.OnboardingViewPagerAdapter
import com.talhadengiz.hepsiburada.databinding.FragmentOnboardingBinding
import com.talhadengiz.hepsiburada.ui.viewModel.OnboardingFragmentVM

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: OnboardingFragmentVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initViewPager()
    }

    private fun initViewPager() {
        val viewPagerAdapter = OnboardingViewPagerAdapter(
            childFragmentManager,
            lifecycle
        )

        viewModel.sliderList.forEach {
            val fragment = ItemOnboardingFragment(it.resId, it.title)
            viewPagerAdapter.addFragment(fragment)
        }

        binding.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_searchFragment)
        }
        binding.vpOnboarding.adapter = viewPagerAdapter

        //Page indicator --> https://github.com/romandanylyk/PageIndicatorView
        binding.vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.pageIndicator.selection = position
                binding.pageIndicator.setAnimationType(AnimationType.WORM)
            }
        })
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(OnboardingFragmentVM::class.java)
    }
}