package android.assignment.telstra.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class CityInfoProvider (
        val title : String,
        val description : String,
        val imageHref : String
) : Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}