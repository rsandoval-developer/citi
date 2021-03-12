package com.softtek.citi.presentation.ui.viewmodels.base

import androidx.lifecycle.ViewModel
import com.softtek.citi.data.exceptions.AppException

abstract class ViewModelBase() : ViewModel() {

    abstract fun defaultError(error: Throwable)

    fun handleError(error: Throwable) {
        if (error is AppException) {

        } else {
            this.defaultError(error)
        }
    }
}