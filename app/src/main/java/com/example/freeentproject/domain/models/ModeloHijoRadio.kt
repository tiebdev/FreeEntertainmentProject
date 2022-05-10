package com.example.freeentproject.domain.models

/*
-Modelo de datos
-Constructor compuesto por un viewType (necesario para el adaptador) y una lista del tipo ModeloRadio
-Hereda de un ModeloPadre
 */

class ModeloHijoRadio (viewType: Int, var list: List<ModeloRadio>): ModeloPadre(viewType)