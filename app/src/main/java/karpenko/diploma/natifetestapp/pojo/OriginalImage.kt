package karpenko.diploma.natifetestapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "gifs_list")
data class OriginalImage(
    @PrimaryKey
    val url: String


) : Serializable {
    override fun toString(): String {
        return url
    }
}

