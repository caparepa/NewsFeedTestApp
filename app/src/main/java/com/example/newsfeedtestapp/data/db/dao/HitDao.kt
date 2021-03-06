package com.example.newsfeedtestapp.data.db.dao

import androidx.room.*
import com.example.newsfeedtestapp.data.db.entity.HitEntity

@Dao
interface HitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(readHit: HitEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(readHit: HitEntity)

    @Query("DELETE FROM hits WHERE story_url = :storyUrl")
    suspend fun delete(storyUrl: String)

    @Transaction
    suspend fun upsert(readHit: HitEntity) {
        val id: Long = insert(readHit)
        if (id == -1L) {
            update(readHit)
        }
    }

    @Query("SELECT * FROM hits")
    suspend fun fetchNewsHits(): List<HitEntity>

    @Query("SELECT * FROM hits WHERE story_url NOT IN (:storyUrlList)")
    suspend fun fetchUnreadNewsHits(storyUrlList: List<String>): List<HitEntity>
}