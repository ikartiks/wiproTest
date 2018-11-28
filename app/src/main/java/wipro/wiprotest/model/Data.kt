package wipro.wiprotest.model

import java.io.Serializable
import java.util.ArrayList

class Data :Serializable{

    var title: String? = null

    var rows: ArrayList<Rows>? = null

    override fun toString(): String {
        return "ClassPojo [title = $title, rows = $rows]"
    }
}