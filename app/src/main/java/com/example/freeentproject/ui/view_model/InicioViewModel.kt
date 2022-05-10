package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.*
import com.example.freeentproject.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val getRadioConLimite: GetRadioConLimite,
    private val getSantanderConLimite: GetSantanderConLimite,
    private val getTvConLimite: GetTvConLimite,
    private val getPelisConLimite: GetPelisConLimite
)  : ViewModel() {

    var pelis = MutableLiveData<List<ModeloPeli>>()
    var radios = MutableLiveData<List<ModeloRadio>>()
    var tvs = MutableLiveData<List<ModeloTv>>()
    var santander = MutableLiveData<List<ModeloSantander>>()

    init {
        getAll()
    }

      private fun getAll() {
        viewModelScope.launch {
            pelis.postValue(getPelisConLimite.invoke())
            tvs.postValue(getTvConLimite.invoke())
            radios.postValue(getRadioConLimite.invoke())
            santander.postValue(getSantanderConLimite.invoke())
        }
    }
}