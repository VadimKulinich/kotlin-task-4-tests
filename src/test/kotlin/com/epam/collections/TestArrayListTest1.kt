package com.epam.collections


import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test


class TestArrayListTest1 {
    private lateinit var list: MutableList<String?>

    @Before
    fun setUp() {
        list = TestArrayList()
    }

    @Test
    fun testListInit() {

    }

    @Test
    fun testAddElements() {

    }

    @Test
    fun testAddElementNull() {

    }

    @Test
    fun testRemoveElement() {

    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun testRemoveWithEmptyList() {
        list.removeAt(0)
    }

}