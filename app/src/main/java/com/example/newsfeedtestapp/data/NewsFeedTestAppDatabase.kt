package com.example.newsfeedtestapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsfeedtestapp.data.db.dao.HitDao
import com.example.newsfeedtestapp.data.db.dao.ReadHitDao
import com.example.newsfeedtestapp.data.db.entity.HitEntity
import com.example.newsfeedtestapp.data.db.entity.ReadHitEntity

@Database(
    entities = [HitEntity::class, ReadHitEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsFeedTestAppDatabase : RoomDatabase() {

    abstract fun getHitDao(): HitDao
    abstract fun getReadHitDao(): ReadHitDao

    companion object {

        @Volatile
        private var instance: NewsFeedTestAppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                //if there is no instance, build a database (buildDatabase(context))
                //and also, whatever is returned from the builder, set the instance equal to "it"
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        //We create a function to build the database, which will receive the application context
        ///This builder will initialize the database java class for the app (NewsFeedTestAppDatabase)
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsFeedTestAppDatabase::class.java, "newsfeedtest.db"
            ).fallbackToDestructiveMigration().build()

    }
}