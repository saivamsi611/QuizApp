package com.example.quizmasterapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskviewmodelFactory( private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(myviewmodel::class.java)) {
            myviewmodel(
                application =application
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
