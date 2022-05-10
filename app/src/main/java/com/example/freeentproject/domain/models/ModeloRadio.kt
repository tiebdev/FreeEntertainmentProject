package com.example.freeentproject.domain.models
import android.os.Parcel
import android.os.Parcelable

/*
-Modelo de datos
-Constructor compuesto por una imagen y una url de tipo String, con la posibilidad de ser nulo.
-Hereda de un Parcelable (necesario para pasar objetos de este tipo entre fragments)
 */

class ModeloRadio (var imagen: String?, var url: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagen)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModeloRadio> {
        override fun createFromParcel(parcel: Parcel): ModeloRadio {
            return ModeloRadio(parcel)
        }

        override fun newArray(size: Int): Array<ModeloRadio?> {
            return arrayOfNulls(size)
        }
    }
}