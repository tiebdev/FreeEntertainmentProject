package com.example.freeentproject.domain.models

/*
-Modelo de datos
-Constructor compuesto por un viewType (necesario para el adaptador) y una lista del tipo
 ModeloSantander
-Hereda de un ModeloPadre
 */

class ModeloHijoSantander (viewType: Int, var list: List<ModeloSantander>): ModeloPadre(viewType) {
}