package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloTv
import com.example.freeentproject.repositories.TvRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener las cadenas de tv, tiene
un parámetro como limite  para traer un número concreto de cadenas dentro de las existentes en la
base de datos.
 */

class GetTvConLimite @Inject constructor(
    private val tvRepo : TvRepo
) {
    suspend operator fun invoke (): List<ModeloTv> = tvRepo.getInicioTvCorutinas()
}