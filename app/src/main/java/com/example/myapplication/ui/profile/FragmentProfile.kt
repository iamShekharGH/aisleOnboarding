package com.example.myapplication.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentProfile : Fragment(R.layout.fragment_test_profile_list) {

    val viewModel: FragmentProfileViewModel by viewModels()
}