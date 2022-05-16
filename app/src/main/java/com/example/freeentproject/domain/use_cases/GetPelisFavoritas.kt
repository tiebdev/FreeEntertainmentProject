package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.repositories.PelisRepo
import javax.inject.Inject

class GetPelisFavoritas @Inject constructor(
    private val peliRepo : PelisRepo
) {
    suspend operator fun invoke () : List<ModeloPeli> = peliRepo.getPelisFavoritasFragmentCorutinas()
}