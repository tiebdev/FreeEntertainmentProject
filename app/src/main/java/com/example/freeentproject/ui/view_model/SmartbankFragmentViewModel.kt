package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloSmartbank
import com.example.freeentproject.domain.use_cases.GetSmartbank
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmartbankFragmentViewModel @Inject constructor (
    private val getSmartbankGridFragmentCorutinas: GetSmartbank
): ViewModel() {

    var smartbank = MutableLiveData<List<ModeloSmartbank>>()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch  {
            smartbank.postValue(getSmartbankGridFragmentCorutinas.invoke())
        }
    }
}