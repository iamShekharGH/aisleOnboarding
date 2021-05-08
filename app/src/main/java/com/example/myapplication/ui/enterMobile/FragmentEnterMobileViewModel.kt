package com.example.myapplication.ui.enterMobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.AisleRepository
import com.example.myapplication.repository.AisleRepositoryEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentEnterMobileViewModel @Inject constructor(
    private val repo: AisleRepository
) : ViewModel() {

    private val channelEvents = Channel<FragmentEvents>()
    val channelEventsAsFlow = channelEvents.receiveAsFlow()
    var mobileNo: String = ""


    fun onContinueClicked(text: String) = viewModelScope.launch {
        if (text.isEmpty() || text.length != 10) {
            channelEvents.send(FragmentEvents.ShowSnackbar("Mobile Number must be 10 digits"))
        } else {
            mobileNo = text
            repo.sendMobileNumber(text)
        }
    }

    fun listenToRepoEvents() = viewModelScope.launch {
        repo.repositoryChannelAsFlow.collect { aisleRepositoryEvents ->
            when (aisleRepositoryEvents) {
                is AisleRepositoryEvents.ResponseFailed -> {
                    channelEvents.send(FragmentEvents.ShowSnackbar("Could Not Connect. Sorry."))
                }
                is AisleRepositoryEvents.ResponseSuccess -> {
                    channelEvents.send(FragmentEvents.LaunchOTPFragment(mobileNo))
                }
            }
        }
    }
}

sealed class FragmentEvents {
    data class ShowSnackbar(val text: String) : FragmentEvents()
    data class LaunchOTPFragment(val text: String) : FragmentEvents()
    data class ProgressBarState(val state: Boolean) : FragmentEvents()
}