package tm.com.balary.features.home.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SeasonDao {
    @Query("SELECT * FROM SeasonLocalEntity ORDER BY ID DESC LIMIT 1")
    suspend fun getSeason(): SeasonLocalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeason(item: SeasonLocalEntity)

    @Query("DELETE FROM SeasonLocalEntity WHERE 1==1")
    suspend fun deleteAll()
}