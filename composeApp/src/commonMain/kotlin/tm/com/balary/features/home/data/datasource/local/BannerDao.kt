package tm.com.balary.features.home.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {
    @Query("SELECT * FROM BannerLocalEntity")
    suspend fun getBanners(): List<BannerLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanner(bannerLocalEntity: BannerLocalEntity)

    @Query("DELETE FROM BannerLocalEntity WHERE 1==1")
    suspend fun deleteAll()
}