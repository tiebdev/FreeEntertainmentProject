package com.example.freeentproject.domain.models
import android.os.Parcel
import android.os.Parcelable

/*
-Modelo de datos
-Constructor compuesto por una imagen y un nombre tipo String, con la posibilidad de ser nulo.
-Hereda de un Parcelable (necesario para pasar objetos de este tipo entre fragments)
 */

class ModeloLiga (var imagen: String?, var nombre: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagen)
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModeloLiga> {
        override fun createFromParcel(parcel: Parcel): ModeloLiga {
            return ModeloLiga(parcel)
        }

        override fun newArray(size: Int): Array<ModeloLiga?> {
            return arrayOfNulls(size)
        }
    }

}