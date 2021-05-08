package com.example.myapplication.ui.profile

import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.AisleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentProfileViewModel @Inject constructor(
    val repo: AisleRepository
) : ViewModel() {
}