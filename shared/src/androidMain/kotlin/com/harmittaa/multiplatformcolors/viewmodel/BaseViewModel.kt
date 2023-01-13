package com.harmittaa.multiplatformcolors.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

// from https://github.com/Oztechan/CCC/blob/develop/client/src/androidMain/kotlin/com/oztechan/ccc/client/base/BaseViewModel.kt
@Suppress("EmptyDefaultConstructor")
actual open class BaseViewModel actual constructor() : ViewModel() {

    protected actual val viewModelScope: CoroutineScope by lazy {
        ViewModel::viewModelScope.get(this)
    }

    actual override fun onCleared() {
        super.onCleared()
    }
}