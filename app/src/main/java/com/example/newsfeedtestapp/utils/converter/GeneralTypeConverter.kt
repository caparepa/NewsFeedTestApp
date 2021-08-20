package com.example.newsfeedtestapp.utils.converter

import androidx.room.TypeConverter
import com.example.newsfeedtestapp.data.model.HighlightResult
import com.example.newsfeedtestapp.utils.toJsonString
import com.example.newsfeedtestapp.utils.toKotlinObject

object GeneralTypeConverter {

    @TypeConverter
    fun toHighlightResult(result: String?): HighlightResult? {
        return result?.toKotlinObject()
    }

    @TypeConverter
    @JvmStatic
    fun fromHightlightResult(result: HighlightResult?): String? {
        return result?.toJsonString()
    }

    @TypeConverter
    @JvmStatic
    fun fromListString(list: List<String>?): String {
        return list.toString()
    }

    @TypeConverter
    @JvmStatic
    fun toListString(list: String): List<String> {
        return list.toKotlinObject()
    }

}