package tm.com.balary.features.home.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductLocalEntity WHERE type=:type")
    suspend fun getProductsByType(type: String): List<ProductLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(items: List<ProductLocalEntity>)

    @Query("DELETE FROM ProductLocalEntity WHERE 1==1")
    suspend fun deleteAll()
}