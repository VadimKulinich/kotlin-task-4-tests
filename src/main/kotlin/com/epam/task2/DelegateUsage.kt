package com.epam.task2

import java.io.File

fun main() {
    var delegatedString by StringToFileDelegate(File(""))
    print(delegatedString)
    delegatedString = "Some string"
}