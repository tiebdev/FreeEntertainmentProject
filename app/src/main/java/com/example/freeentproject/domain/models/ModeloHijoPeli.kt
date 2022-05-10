package com.example.freeentproject.domain.models

/*
-Modelo de datos
-Constructor compuesto por un viewType (necesario para el adaptador) y una lista del tipo ModeloPeli
-Hereda de un ModeloPadre
 */

class ModeloHijoPeli (viewType: Int, var list: List<ModeloPeli>): ModeloPadre(viewType) {
}