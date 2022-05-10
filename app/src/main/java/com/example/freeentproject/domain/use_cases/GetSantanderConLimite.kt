package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloSantander
import com.example.freeentproject.repositories.SantanderRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener los partidos de la liga,
Santander tiene un parámetro como limite para traer un número concreto de partidos dentro de los
existentes en la base de datos.
 */

class GetSantanderConLimite @Inject constructor(
    private val santanderRepo : SantanderRepo
) {
    suspend operator fun invoke () : List<ModeloSantander> = santanderRepo.getInicioSantanderCorutinas()
}