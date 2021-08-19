package com.example.newsfeedtestapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeedtestapp.data.db.entity.HitEntity

@Dao
interface HitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(commandCode: HitEntity)

    @Query("SELECT * FROM hits")
    suspend fun fetchNewsHits(): List<HitEntity>

    @Query("SELECT * FROM hits WHERE story_id NOT IN (:storyIdList)")
    suspend fun fetchUnreadNewsHits(storyIdList: List<Int>): List<HitEntity>
}