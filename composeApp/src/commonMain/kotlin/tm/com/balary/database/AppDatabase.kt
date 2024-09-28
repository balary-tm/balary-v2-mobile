package tm.com.balary.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import tm.com.balary.features.basket.data.local.BasketDao
import tm.com.balary.features.basket.data.local.BasketLocalEntity
import tm.com.balary.features.home.data.datasource.local.BannerDao
import tm.com.balary.features.home.data.datasource.local.BannerLocalEntity
import tm.com.balary.features.home.data.datasource.local.HomeCategoryDao
import tm.com.balary.features.home.data.datasource.local.HomeCategoryLocalEntity
import tm.com.balary.features.home.data.datasource.local.ProductDao
import tm.com.balary.features.home.data.datasource.local.ProductLocalEntity
import tm.com.balary.features.home.data.datasource.local.SeasonDao
import tm.com.balary.features.home.data.datasource.local.SeasonLocalEntity
import tm.com.balary.features.home.data.datasource.local.SlideDao
import tm.com.balary.features.home.data.datasource.local.SlideLocalEntity

@Database(
    entities = [
        BannerLocalEntity::class,
        SeasonLocalEntity::class,
        ProductLocalEntity::class,
        SlideLocalEntity::class,
        HomeCategoryLocalEntity::class,
        BasketLocalEntity::class],
    version = 7
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBannerDao(): BannerDao
    abstract fun getSeasonDao(): SeasonDao
    abstract fun getProductDao(): ProductDao
    abstract fun getSlidesDao(): SlideDao
    abstract fun getHomeCategoryDao(): HomeCategoryDao
    abstract fun getBasketDao(): BasketDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal const val dbFileName = "balary.db"