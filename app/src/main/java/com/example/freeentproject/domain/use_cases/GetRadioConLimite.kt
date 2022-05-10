package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.repositories.RadioRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener las radios, tiene un
parámetro como limite  para traer un número concreto de radios dentro de las existentes en la
base de datos.
 */
class GetRadioConLimite @Inject constructor(
    private val radioRepo : RadioRepo
) {
    suspend operator fun invoke () : List<ModeloRadio> = radioRepo.getInicioRadioCorutinas()
}