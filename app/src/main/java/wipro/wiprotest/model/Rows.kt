package wipro.wiprotest.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Rows() : Serializable,Parcelable {


    var title: String? = null

    var description: String? = null

    var imageHref: String? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        description = parcel.readString()
        imageHref = parcel.readString()
    }

    override fun toString(): String {
        return "ClassPojo [title = $title, description = $description, imageHref = $imageHref]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageHref)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rows> {
        override fun createFromParcel(parcel: Parcel): Rows {
            return Rows(parcel)
        }

        override fun newArray(size: Int): Array<Rows?> {
            return arrayOfNulls(size)
        }
    }
}
