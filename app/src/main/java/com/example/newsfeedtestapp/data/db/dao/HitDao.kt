package com.example.newsfeedtestapp.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeedtestapp.data.db.entity.HitEntity

interface HitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(commandCode: HitEntity)

    @Query("SELECT * FROM hits WHERE story_id NOT IN (:idList)")
    suspend fun fetchNewsHits(idList: List<Int>): List<HitEntity>
}