package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloSantander
import com.example.freeentproject.repositories.SantanderRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener todos los partidos de la
liga Santander existentes en la base de datos.
 */

class GetSantander @Inject constructor(
    private val santanderRepo : SantanderRepo
) {
    suspend operator fun invoke () : List<ModeloSantander> = santanderRepo.getSantanderGridFragmentCorutinas()
}