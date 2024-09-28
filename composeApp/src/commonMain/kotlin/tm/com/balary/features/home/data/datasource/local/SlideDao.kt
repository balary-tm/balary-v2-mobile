package tm.com.balary.features.home.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SlideDao {
    @Query("SELECT * FROM SlideLocalEntity")
    suspend fun getSlides(): List<SlideLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSlides(items: List<SlideLocalEntity>)

    @Query("DELETE FROM SlideLocalEntity WHERE 1==1")
    suspend fun deleteAll()
}