package uz.uzdroid.andfunkotlindevbytes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.uzdroid.andfunkotlindevbytes.model.Info

@Database(entities = [Info::class], exportSchema = false, version = 1)
abstract class InfoDatabase : RoomDatabase() {
    abstract fun infoDao(): InfoDao

    companion object {
        @Volatile
        var INSTANCE: InfoDatabase? = null

        fun getInstance(context: Context): InfoDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        InfoDatabase::class.java,
                        "video_database",
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
