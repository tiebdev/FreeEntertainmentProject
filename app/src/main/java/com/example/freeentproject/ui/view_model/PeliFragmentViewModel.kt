package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.domain.use_cases.GetPelisGridFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
Injectamos las dependecias de Hilt para el ViewModel. LLamamos al caso de uso en el constructor de
la clase. Creamos una variable LiveData que será una lista ModeloPeli. Esta variable nos permitirá
llamarla en el Fragment para crear el "observador". Creamos una función que nos permite obtener el
valor resultante de la llamada a la corrutina.
 */

@HiltViewModel
class PeliFragmentViewModel @Inject constructor (
    private val getPelisGridFragmentCorutinas: GetPelisGridFragment
): ViewModel() {

    var pelis = MutableLiveData<List<ModeloPeli>>()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            pelis.postValue(getPelisGridFragmentCorutinas.invoke())
        }
    }
}