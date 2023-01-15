package com.harmittaa.multiplatformcolors.viewmodel

import com.harmittaa.multiplatformcolors.repository.ColorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ColorTemplate(
    val name: String = "",
    val colors: List<List<Int>> = emptyList()
)

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

    fun onTemplateSelected(template: ColorTemplate) {
        viewModelScope.launch {
            val newTemplate = template.takeIf { it.colors.isNotEmpty() }
                ?: template.copy(colors = repository.getAColor(template.name))
            val idxOfTemplate =
                _state.value.colorTemplates.indexOfFirst { it.name == newTemplate.name }
            val templates = _state.value.colorTemplates.toMutableList()
            templates[idxOfTemplate] = newTemplate
            _state.value = _state.value.copy(
                currentTemplate = newTemplate,
                colorTemplates = templates
            )
        }
    }

    fun onNextTemplateClicked() {
        val current =
            _state.value.colorTemplates.indexOfFirst { it.name == _state.value.currentTemplate.name }
        if (current == _state.value.colorTemplates.size - 1) {
            onTemplateSelected(_state.value.colorTemplates.first())
        } else {
            onTemplateSelected(_state.value.colorTemplates[current + 1])
        }
    }

    private suspend fun getColorNames() {
        val templates = repository.getColorModelNames()
            .map { ColorTemplate(name = it) }
        _state.value = _state.value.copy(
            colorTemplates = templates
        )
        onTemplateSelected(templates.first())
    }

    data class ColorState(
        val currentTemplate: ColorTemplate = ColorTemplate(),
        val colorTemplates: List<ColorTemplate> = emptyList(),
    )
}
