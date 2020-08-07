package android.assignment.telstra.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//city info table
@Entity
data class CityInfoProvider(
    var title: String? = null,
    var description: String? = null,
    var imageHref: String? = null
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}