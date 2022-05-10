package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.repositories.PelisRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener las películas, tiene un
parámetro como limite  para traer un número concreto de peliculas dentro de las existentes en la
base de datos.
 */
class GetPelisConLimite @Inject constructor(
    private val peliRepo : PelisRepo
) {
    suspend operator fun invoke () : List<ModeloPeli> = peliRepo.getInicioPelisCorutinas()
}