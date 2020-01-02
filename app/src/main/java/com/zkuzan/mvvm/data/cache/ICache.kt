package com.zkuzan.mvvm.data.cache

import java.lang.reflect.Type


interface ICache {

    fun setObject(key: String, obj: Any)

    fun <T> getObject(key: String, clazz: Class<T>): T

    fun <T> getObject(key: String, type: Type): T

    operator fun contains(key: String): Boolean

    fun delete(key: String)

    fun clearAll()

}
