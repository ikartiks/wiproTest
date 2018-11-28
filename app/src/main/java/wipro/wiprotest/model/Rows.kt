package wipro.wiprotest.model

import java.io.Serializable

class Rows : Serializable {
    var title: String? = null

    var description: String? = null

    var imageHref: String? = null

    override fun toString(): String {
        return "ClassPojo [title = $title, description = $description, imageHref = $imageHref]"
    }
}
