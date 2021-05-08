package com.example.myapplication.ui.enterOtp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.modelResponse.responseProfile.ResponseProfile
import com.example.myapplication.repository.AisleRepository
import com.example.myapplication.repository.AisleRepositoryEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentEnterOtpViewModel @Inject constructor(
    private val repo: AisleRepository
) : ViewModel() {

    private val channelEvents = Channel<FragmentEvents>()
    val channelEventsAsFlow = channelEvents.receiveAsFlow()

    fun listenToRepoEvents() = viewModelScope.launch {
        repo.repositoryChannelAsFlow.collect { aisleRepositoryEvents ->
            when (aisleRepositoryEvents) {
                is AisleRepositoryEvents.ResponseFailed -> {
                    channelEvents.send(FragmentEvents.ShowSnackbar("Login Attempt Failed. Please try again."))
                }
                is AisleRepositoryEvents.ResponseSuccess -> {

                }
                is AisleRepositoryEvents.LoadProfileFragment -> {
                    channelEvents.send(FragmentEvents.LoadProfileFragment(aisleRepositoryEvents.profile))
                }
                AisleRepositoryEvents.LoginSuccessful -> {
                    channelEvents.send(FragmentEvents.ShowSnackbar("Login Successful!! Loading profile..."))
                }
            }
        }
    }

    fun onContinueClicked(mobNo: String, otp: String) = viewModelScope.launch {
        if (otp.isEmpty() || otp.length != 4) {
            channelEvents.send(FragmentEvents.ShowSnackbar("Please complete the OTP. Almost There!"))
        } else {
            repo.sendOtpTo(mobNo, otp)
        }
    }
}

sealed class FragmentEvents {
    data class ShowSnackbar(val text: String) : FragmentEvents()
    object EnterProfileFragment : FragmentEvents()
    data class ProgressBarState(val pbState: Boolean) : FragmentEvents()
    data class LoadProfileFragment(val profile: ResponseProfile) : FragmentEvents()
}