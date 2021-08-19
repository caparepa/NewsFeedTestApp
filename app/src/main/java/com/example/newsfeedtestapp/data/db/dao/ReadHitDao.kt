package com.example.newsfeedtestapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeedtestapp.data.db.entity.ReadHitEntity

@Dao
interface ReadHitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(readHit: ReadHitEntity)

    @Query("SELECT * FROM read_hits")
    suspend fun fetchReadHits(): List<ReadHitEntity>
}