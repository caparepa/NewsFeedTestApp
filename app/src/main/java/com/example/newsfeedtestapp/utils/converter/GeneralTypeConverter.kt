package com.example.newsfeedtestapp.utils.converter

import androidx.room.TypeConverter
import com.example.newsfeedtestapp.data.model.HighlightResult
import com.example.newsfeedtestapp.utils.toJsonString
import com.example.newsfeedtestapp.utils.toKotlinObject

object GeneralTypeConverter {

    @TypeConverter
    @JvmStatic
    fun toHighlightResult(result: String?): HighlightResult? {
        return result?.toKotlinObject()
    }

    @TypeConverter
    @JvmStatic
    fun fromHightlightResult(result: HighlightResult?): String? {
        return result?.toJsonString()
    }

}