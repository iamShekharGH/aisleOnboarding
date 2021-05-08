package com.example.myapplication.ui.enterOtp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEnterOtpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FragmentEnterOtp : Fragment(R.layout.fragment_enter_otp) {

    private val viewModel: FragmentEnterOtpViewModel by viewModels()
    private lateinit var binding: FragmentEnterOtpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listenToRepoEvents()

        val mobNo = setupPassedInformation()
        if (mobNo.isEmpty()) {
            returnToMobileFrag()
        }

        binding = FragmentEnterOtpBinding.bind(view)
        binding.apply {
            enterOtpEditMobile.setOnClickListener {
                returnToMobileFrag()
            }

            enterOtpEditMobileNo.text = mobNo

            enterOtpContinue.setOnClickListener {
                progressBarState(state = true)
                viewModel.onContinueClicked(mobNo, enterOtpOtpEdittext.text.toString().trim())
            }

        }
        showCountdown()

        setupViewModelEvents()

    }

    private fun showCountdown() {
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.enterOtpCountdown.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.enterOtpCountdown.text = "00:00"
            }
        }
        timer.start()
    }

    private fun returnToMobileFrag() {
        findNavController().popBackStack()
//        val action = FragmentEnterOtpDirections.actionFragmentEnterOtpToFragmentEnterMobile()
//        findNavController().navigate(action)
    }

    private fun setupPassedInformation(): String {
        return if (arguments != null) {
            val args = FragmentEnterOtpArgs.fromBundle(requireArguments())
            args.mobileNo
        } else {
            ""
        }
    }

    private fun setupViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.channelEventsAsFlow.collect { event ->
                when (event) {
                    is FragmentEvents.ShowSnackbar -> {
                        progressBarState(state = false)
                        showSnackbar(event.text)
                    }
                    FragmentEvents.EnterProfileFragment -> {

                    }
                    is FragmentEvents.ProgressBarState -> {
                        progressBarState(event.pbState)
                    }
                    is FragmentEvents.LoadProfileFragment -> {
                        val action =
                            FragmentEnterOtpDirections.actionFragmentEnterOtpToFragmentProfile(event.profile)
                        findNavController().navigate(action)
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
                enterOtpProgressbar.visibility = View.VISIBLE
                enterOtpProgressbar.isIndeterminate = true
            } else {
                enterOtpProgressbar.visibility = View.GONE
                enterOtpProgressbar.isIndeterminate = false
            }
        }
    }
}

