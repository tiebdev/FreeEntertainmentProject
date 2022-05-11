package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloLiga
import com.example.freeentproject.domain.use_cases.GetLiga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
Injectamos las dependecias de Hilt para el ViewModel. LLamamos al caso de uso en el constructor de
la clase. Creamos una variable LiveData que será una lista ModeloLiga. Esta variable nos permitirá
llamarla en el Fragment para crear el "observador". Creamos una función que nos permite obtener el
valor resultante de la llamada a la corrutina.
 */

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