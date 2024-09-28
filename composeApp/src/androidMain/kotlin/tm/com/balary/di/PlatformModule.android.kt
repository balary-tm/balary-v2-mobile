package tm.com.balary.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import tm.com.balary.database.AppDatabase
import tm.com.balary.database.dbFileName

fun getDatabase(ctx: Context): AppDatabase {
    val dbFile = ctx.getDatabasePath(dbFileName)
    return Room.databaseBuilder<AppDatabase>(
        context = ctx,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration(true)
        .build()
}

actual fun platformModule(context: Any?) = module {
    single<AppDatabase> { getDatabase(context as Context) }
}