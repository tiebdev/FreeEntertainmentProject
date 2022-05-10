package com.example.freeentproject.domain.models

/*
-Modelo de datos
-Constructor compuesto por un viewType (necesario para el adaptador) y una lista del tipo
 ModeloSmartbank
-Hereda de un ModeloPadre
 */

class ModeloHijoSmartbank (viewType: Int, var list: List<ModeloSmartbank>): ModeloPadre(viewType) {
}