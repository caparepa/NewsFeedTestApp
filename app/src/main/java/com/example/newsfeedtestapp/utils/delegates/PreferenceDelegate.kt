package com.example.newsfeedtestapp.utils.delegates

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class PreferenceDelegate<T> : ReadWriteProperty<Any, T>, KoinComponent {

    companion object {
        private const val FIRST_TIME_DIALOG = "FIRST_TIME_DIALOG"
        var firstTimeDialog by booleanPreferenceDelegate(FIRST_TIME_DIALOG)
        private const val READ_ALL_ARTICLES = "READ_ALL_ARTICLE"
        var readAllArticles by booleanPreferenceDelegate(READ_ALL_ARTICLES)
    }

    protected val sharedPreferences: SharedPreferences by inject()
}

class stringPreferenceDelegate(private val key: String, private val defaultValue: String = "") :
    PreferenceDelegate<String>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getString(key, defaultValue)!!

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) =
        sharedPreferences.edit { putString(key, value) }
}

class intPreferenceDelegate(private val key: String, private val defaultValue: Int = 0) :
    PreferenceDelegate<Int>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getInt(key, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) =
        sharedPreferences.edit { putInt(key, value) }
}

class longPreferenceDelegate(private val key: String, private val defaultValue: Long = 0.toLong()) :
    PreferenceDelegate<Long>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getLong(key, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) =
        sharedPreferences.edit { putLong(key, value) }
}

class floatPreferenceDelegate(
    private val key: String,
    private val defaultValue: Float = 0.toFloat()
) : PreferenceDelegate<Float>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getFloat(key, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) =
        sharedPreferences.edit { putFloat(key, value) }
}

class booleanPreferenceDelegate(
    private val key: String,
    private val defaultValue: Boolean = false
) : PreferenceDelegate<Boolean>() {
    override fun getValue(thisRef: Any, property: KProperty<*>) =
        sharedPreferences.getBoolean(key, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) =
        sharedPreferences.edit { putBoolean(key, value) }
}