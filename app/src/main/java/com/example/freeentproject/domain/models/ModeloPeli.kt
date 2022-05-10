package com.example.freeentproject.domain.models
import android.os.Parcel
import android.os.Parcelable

/*
-Modelo de datos
-Constructor compuesto por una imagen, un titulo, una url y una descripción de tipo String, con la
 posibilidad de ser nulo.
-Hereda de un Parcelable (necesario para pasar objetos de este tipo entre fragments)
 */

class ModeloPeli (var imagen: String?, var url: String?, var titulo: String?, var descripcion: String?) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagen)
        parcel.writeString(url)
        parcel.writeString(titulo)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModeloPeli> {
        override fun createFromParcel(parcel: Parcel): ModeloPeli {
            return ModeloPeli(parcel)
        }

        override fun newArray(size: Int): Array<ModeloPeli?> {
            return arrayOfNulls(size)
        }
    }
}