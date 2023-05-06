package co.edu.uan.android.demojetpack421.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.uan.android.demojetpack421.apis.CatApi
import co.edu.uan.android.demojetpack421.databases.CatDao
import co.edu.uan.android.demojetpack421.databases.CatEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Database(
    entities = arrayOf(CatEntity::class),
    version = 1)
abstract class CatDatabase : RoomDatabase() {

    abstract fun catDao(): CatDao

    companion object {
        fun getInstance(context: Context) : CatDatabase {
            return Room.databaseBuilder(context, CatDatabase::class.java, "cats-database")
                .build()
        }
    }
}