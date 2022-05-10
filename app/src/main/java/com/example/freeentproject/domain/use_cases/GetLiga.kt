package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloLiga
import com.example.freeentproject.repositories.LigasRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener las ligas existentes
en la base de datos.
 */
class GetLiga @Inject constructor(
    private val ligaRepo : LigasRepo
) {
    suspend operator fun invoke(): List<ModeloLiga> = ligaRepo.getLigasFragmentCorutinas()
}