package com.talhadengiz.searhmedia.ui.fragment

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.talhadengiz.searhmedia.R
import com.talhadengiz.searhmedia.databinding.FragmentSplashBinding
import com.talhadengiz.searhmedia.util.SharedPrefHelper

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val lottieAnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            if (isSkipedOnboarding()) {
                findNavController().navigate(R.id.action_splashFragment_to_searchFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
            }
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.lottieAnimationView.addAnimatorListener(lottieAnimatorListener)
    }

    private fun isSkipedOnboarding(): Boolean {
        return SharedPrefHelper(requireContext()).isSkipedOnboarding()
    }
}