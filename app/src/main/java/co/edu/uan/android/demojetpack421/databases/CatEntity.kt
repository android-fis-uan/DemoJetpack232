package co.edu.uan.android.demojetpack421.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "cat_images")
data class CatEntity (
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
) {
}