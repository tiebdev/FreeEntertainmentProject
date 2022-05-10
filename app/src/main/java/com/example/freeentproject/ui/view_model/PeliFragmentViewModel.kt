package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.domain.use_cases.GetPelisGridFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

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