package com.harmittaa.multiplatformcolors.viewmodel

import com.harmittaa.multiplatformcolors.repository.ColorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ColorScreenViewModel(
    private val repository: ColorRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow(ColorState())

    val state: StateFlow<ColorState> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ColorState()
    )

    init {
        viewModelScope.launch {
            getColorNames()
        }
    }

    fun onTemplateSelected(template: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                currentTemplate = template,
                colorTemplate = repository.getAColor(template)
            )
        }
    }

    private suspend fun getColorNames() {
        val names = repository.getColorModelNames()
        _state.value = _state.value.copy(
            colorModelNames = names
        )
        onTemplateSelected(names.first())
    }

    data class ColorState(
        val currentTemplate: String? = null,
        val colorModelNames: List<String> = emptyList(),
        val colorTemplate: List<List<Int>> = emptyList()
    )
}
