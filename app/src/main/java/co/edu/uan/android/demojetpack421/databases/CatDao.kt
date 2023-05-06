package co.edu.uan.android.demojetpack421.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDao { // Operaciones CRUD: crear, leer, actualizar, y borrar

    @Insert
    suspend fun save(cat: CatEntity)

    @Query("SELECT * FROM cat_images WHERE id=:pid")
    suspend fun getCatByNameById(pid: String): CatEntity

    @Query("SELECT * FROM cat_images")
    suspend fun getAllCats(): List<CatEntity>

}