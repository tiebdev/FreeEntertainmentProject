package com.example.freeentproject.ui.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeentproject.domain.models.ModeloTv
import com.example.freeentproject.domain.use_cases.GetTvGridFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvFragmentViewModel @Inject constructor (
    private val getTvGridFragmentCorutinas: GetTvGridFragment
): ViewModel() {

    var tvs = MutableLiveData<List<ModeloTv>>()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            tvs.postValue(getTvGridFragmentCorutinas.invoke())
        }
    }
}