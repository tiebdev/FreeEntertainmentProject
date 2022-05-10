package com.example.freeentproject.domain.use_cases
import com.example.freeentproject.domain.models.*
import javax.inject.Inject

/*
Como posible mejora para reducir el consumo de recursos, queda preparado el caso de uso que llama a
todos los casos de usos juntos, quedaría por implementar el ViewModel y el InicioFragment con
un solo observer{} en vez de cuatro que es como está actualmente.
 */

class GetListaInicio @Inject constructor(
    private val getPelisConLimite: GetPelisConLimite,
    private val getRadioConLimite: GetRadioConLimite,
    private val getTvConLimite : GetTvConLimite,
    private val getSantanderConLimite: GetSantanderConLimite
) {
    suspend operator fun invoke () : List<ModeloPadre> {

        val listaPadre = mutableListOf<ModeloPadre>()

        val santander = getSantanderConLimite.invoke()
        val peli = getPelisConLimite.invoke()
        val tv = getTvConLimite.invoke()
        val radio = getRadioConLimite.invoke()

        val pelis = ModeloHijoPeli(0, peli)
        val tvs = ModeloHijoTv(1, tv)
        val radios = ModeloHijoRadio(2, radio)
        val primera = ModeloHijoSantander(3, santander)

        listaPadre.add(0, pelis)
        listaPadre.add(1, tvs)
        listaPadre.add(2, radios)
        listaPadre.add(3, primera)

        return listaPadre
    }
}