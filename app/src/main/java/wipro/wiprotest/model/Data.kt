package wipro.wiprotest.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

class Data() :Serializable,Parcelable{

    var title: String? = null

    var rows: ArrayList<Rows>? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
    }

    override fun toString(): String {
        return "ClassPojo [title = $title, rows = $rows]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}