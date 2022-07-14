package karpenko.diploma.natifetestapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import karpenko.diploma.natifetestapp.pojo.OriginalImage
import androidx.room.Query

@Dao
interface AllGifsDao {

    @Query("SELECT * FROM gifs_list")
    fun getAllGifs(): LiveData<List<OriginalImage>>

    @Query("SELECT * FROM gifs_list WHERE url == :url LIMIT 1")
    fun getGif(url: String): LiveData<OriginalImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGifsList(gifsList: List<OriginalImage>)
}