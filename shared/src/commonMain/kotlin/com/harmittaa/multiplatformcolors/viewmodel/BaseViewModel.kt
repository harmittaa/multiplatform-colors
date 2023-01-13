package com.harmittaa.multiplatformcolors.viewmodel

// from: https://github.com/Oztechan/CCC/blob/develop/client/src/commonMain/kotlin/com/oztechan/ccc/client/base/BaseViewModel.kt

import kotlinx.coroutines.CoroutineScope

@Suppress("EmptyDefaultConstructor")
expect open class BaseViewModel() {
    protected val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
