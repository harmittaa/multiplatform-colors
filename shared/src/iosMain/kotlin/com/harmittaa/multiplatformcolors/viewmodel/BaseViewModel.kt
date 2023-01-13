package com.harmittaa.multiplatformcolors.viewmodel

// from: https://github.com/Oztechan/CCC/blob/develop/client/src/iosMain/kotlin/com/oztechan/ccc/client/base/BaseViewModel.kt

@Suppress("EmptyDefaultConstructor", "unused")
actual open class BaseViewModel actual constructor() {

    protected actual val viewModelScope: CoroutineScope = MainScope()

    init {
    }

    protected actual open fun onCleared() {
        viewModelScope.cancel()
    }
}