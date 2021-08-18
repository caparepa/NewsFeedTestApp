package com.example.newsfeedtestapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.newsfeedtestapp.ui.custom.AppLoader
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf

open class BaseFragment: Fragment(), KoinComponent {

    private val appLoader: AppLoader by inject { parametersOf(requireContext()) }

    fun showLoading() {
        appLoader.show(requireActivity())
    }

    fun dismissLoading(onDismiss: () -> Unit = {}) {
        appLoader.dismiss(requireActivity(), onDismiss)
    }
}