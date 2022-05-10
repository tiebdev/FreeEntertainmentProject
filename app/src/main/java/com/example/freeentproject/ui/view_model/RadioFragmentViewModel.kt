package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.domain.use_cases.GetRadiosGridFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioFragmentViewModel @Inject constructor (
    private val getRadioGridFragmentCorutinas: GetRadiosGridFragment
): ViewModel() {

    var radios = MutableLiveData<List<ModeloRadio>>()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            radios.postValue(getRadioGridFragmentCorutinas.invoke())
        }
    }
}