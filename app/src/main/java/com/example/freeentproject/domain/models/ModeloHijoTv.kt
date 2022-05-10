package com.example.freeentproject.domain.models

/*
-Modelo de datos
-Constructor compuesto por un viewType (necesario para el adaptador) y una lista del tipo ModeloTv
-Hereda de un ModeloPadre
 */

class ModeloHijoTv (viewType: Int, var list: List<ModeloTv>): ModeloPadre(viewType) {
}