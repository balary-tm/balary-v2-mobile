package tm.com.balary.features.basket.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BasketDao {
    @Query("SELECT * FROM BasketLocalEntity WHERE count > 0")
    suspend fun getBasket(): List<BasketLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToBasket(item: BasketLocalEntity)

    @Query("DELETE FROM BasketLocalEntity WHERE id=:id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM BasketLocalEntity WHERE 1==1")
    suspend fun deleteAll()
}