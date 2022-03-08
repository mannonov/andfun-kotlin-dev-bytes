package uz.uzdroid.andfunkotlindevbytes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.uzdroid.andfunkotlindevbytes.model.Info
import uz.uzdroid.andfunkotlindevbytes.model.Videos


@Dao
interface InfoDao {

    @Query("SELECT * FROM video_cache ORDER BY id DESC")
    fun queryAllVideo(): LiveData<List<Info>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(vararg info: Info)

}