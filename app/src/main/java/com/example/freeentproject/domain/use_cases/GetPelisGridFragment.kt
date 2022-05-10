package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.repositories.PelisRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener todas las peliculas
existentes en la base de datos.
 */

class GetPelisGridFragment  @Inject constructor(
    private val peliRepo : PelisRepo
) {
    suspend operator fun invoke(): List<ModeloPeli> = peliRepo.getPelisGridFragmentCorutinas()
}