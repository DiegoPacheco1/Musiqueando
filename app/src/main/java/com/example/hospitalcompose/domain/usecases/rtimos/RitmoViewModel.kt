package com.example.hospitalcompose.domain.usecases.rtimos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalcompose.data.local.RitmosRepository
import com.example.hospitalcompose.domain.modelo.RitmoEntity

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RitmoViewModel @Inject constructor(
    private val repo: RitmosRepository
): ViewModel() {

    private val _ritmos = MutableStateFlow<List<RitmoEntity>>(emptyList())
    val ritmos: StateFlow<List<RitmoEntity>> = _ritmos.asStateFlow()

    init {
        // 1) Cargar datos desde Room
        viewModelScope.launch {
            repo.obtenerTodos()
                .collect { lista ->
                    _ritmos.value = lista
                }
        }
    }

    // 2) Método público para insertar si lo necesitas
    fun seedInicial(inicial: List<RitmoEntity>) {
        viewModelScope.launch {
            repo.insertarTodos(inicial)
        }
    }
}
