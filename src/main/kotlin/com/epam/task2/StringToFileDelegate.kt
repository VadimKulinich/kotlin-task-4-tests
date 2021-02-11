package com.epam.task2

import java.io.File
import kotlin.reflect.KProperty

class StringToFileDelegate(private val file: File) {

    private var cache: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if(cache == null) {
            println("return from file")
        } else {
            println("return from cache")
        }
        return cache ?: file.readText().also {
            cache = it
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("save to file")
        cache = null
        file.writeText(value)
    }
}