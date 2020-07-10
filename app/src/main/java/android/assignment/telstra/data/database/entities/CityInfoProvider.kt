package android.assignment.telstra.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class CityInfoProvider(
    val title: String? = null,
    val description: String? = null,
    val imageHref: String? = null
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}