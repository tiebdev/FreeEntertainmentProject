package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.ModeloSmartbank
import com.example.freeentproject.repositories.SmartbankRepo
import javax.inject.Inject

/*
Caso de uso que nos permite llamar a un método del repositorio para obtener todos los partidos de la
liga Smartbank existentes en la base de datos.
 */

class GetSmartbank @Inject constructor(
    private val smartbankRepo: SmartbankRepo
) {
    suspend operator fun invoke () : List<ModeloSmartbank> = smartbankRepo.getSantanderGridFragmentCorutinas()
}