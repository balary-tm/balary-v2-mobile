package tm.com.balary.features.home.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeCategoryDao {
    @Query("SELECT * FROM HomeCategoryLocalEntity")
    suspend fun getCategories(): List<HomeCategoryLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<HomeCategoryLocalEntity>)

    @Query("DELETE FROM HomeCategoryLocalEntity WHERE 1==1")
    suspend fun deleteAll()
}