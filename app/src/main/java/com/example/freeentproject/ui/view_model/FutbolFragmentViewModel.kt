package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloLiga
import com.example.freeentproject.domain.use_cases.GetLiga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FutbolFragmentViewModel @Inject constructor (
    private val getLigasFragmentCorutinas: GetLiga
): ViewModel() {

    var ligas = MutableLiveData<List<ModeloLiga>>()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            ligas.postValue(getLigasFragmentCorutinas.invoke())
        }
    }
}