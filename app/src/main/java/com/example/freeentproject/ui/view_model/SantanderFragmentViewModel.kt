package com.example.freeentproject.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloSantander
import com.example.freeentproject.domain.use_cases.GetSantander
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SantanderFragmentViewModel @Inject constructor (
    private val getSantanderGridFragmentCorutinas: GetSantander
): ViewModel() {

    var santander = MutableLiveData<List<ModeloSantander>>()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            santander.postValue(getSantanderGridFragmentCorutinas.invoke())
        }
    }
}