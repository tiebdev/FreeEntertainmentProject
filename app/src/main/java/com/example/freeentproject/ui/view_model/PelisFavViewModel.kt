package com.example.freeentproject.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.domain.use_cases.GetPelisFavoritas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PelisFavViewModel @Inject constructor (
    private val getPelisFavoritasFragmentCorutinas: GetPelisFavoritas,
): ViewModel() {

    var pelisFav = MutableLiveData<List<ModeloPeli>>()
    var deletePeli = ModeloPeli

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            pelisFav.postValue(getPelisFavoritasFragmentCorutinas.invoke())
        }
    }

}