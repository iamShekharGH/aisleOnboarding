package com.example.myapplication.ui.enterMobile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEnterMobileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class FragmentEnterMobile @Inject constructor() : Fragment(R.layout.fragment_enter_mobile) {

    private val viewModel: FragmentEnterMobileViewModel by viewModels()
    private lateinit var binding: FragmentEnterMobileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listenToRepoEvents()

        binding = FragmentEnterMobileBinding.bind(view)
        binding.apply {
            enterMobileContinue.setOnClickListener {
                progressBarState(state = true)
                viewModel.onContinueClicked(enterMobileMobile.text.toString())
            }
        }
        setupViewModelEvents()
    }

    private fun setupViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.channelEventsAsFlow.collect { event ->
                when (event) {
                    is FragmentEvents.ShowSnackbar -> {
                        progressBarState(state = false)
                        showSnackbar(event.text)
                    }
                    is FragmentEvents.LaunchOTPFragment -> {
                        progressBarState(state = false)
                        val action =
                            FragmentEnterMobileDirections.actionFragmentEnterMobileToFragmentEnterOtp(
                                event.text
                            )
                        findNavController().navigate(action)
                    }
                    is FragmentEvents.ProgressBarState -> {
                        progressBarState(event.state)
                    }
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    private fun progressBarState(state: Boolean) {
        binding.apply {
            if (state) {
                enterMobileProgressbar.visibility = View.VISIBLE
                enterMobileProgressbar.isIndeterminate = true
            } else {
                enterMobileProgressbar.visibility = View.GONE
                enterMobileProgressbar.isIndeterminate = false
            }
        }
    }
}