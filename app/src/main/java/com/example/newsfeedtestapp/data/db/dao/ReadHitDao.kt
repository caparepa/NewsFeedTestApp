package com.example.newsfeedtestapp.data.db.dao

import com.example.newsfeedtestapp.data.db.entity.ReadHitEntity
import android.provider.SyncStateContract.Helpers.update

import android.provider.SyncStateContract.Helpers.insert
import androidx.room.*


@Dao
interface ReadHitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(readHit: ReadHitEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(readHit: ReadHitEntity)

    @Transaction
    suspend fun upsert(readHit: ReadHitEntity) {
        val id: Long = insert(readHit)
        if (id == -1L) {
            update(readHit)
        }
    }

    @Query("SELECT * FROM read_hits")
    suspend fun fetchReadHits(): List<ReadHitEntity>
}