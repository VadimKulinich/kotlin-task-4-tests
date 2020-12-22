package teacher.com.epam.collections

import com.epam.collections.TestArrayList
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class TestArrayListTest {
    private lateinit var list: MutableList<String?>

    @Before
    fun setUp() {
        list = TestArrayList()
    }

    @Test
    fun testListInit() {
        assertTrue(list.isEmpty())
        assertTrue(list.size == 0)
    }

    @Test
    fun testAddElements() {
        list.add(0, "Karol")
        list.add(1, "Vanessa")
        list.add(2, "Amanda")
        assertEquals("Karol", list[0])
        assertEquals("Vanessa", list[1])
        assertEquals("Amanda", list[2])
        list.add(1, "Mariana")
        assertEquals("Karol", list[0])
        assertEquals("Mariana", list[1])
        assertEquals("Vanessa", list[2])
        assertEquals("Amanda", list[3])
        assertTrue(list.size == 4)
    }

    @Test
    fun testAddElementNull() {
        list.add(0, null)
        assertEquals(null, list[0])
    }

    @Test
    fun testRemoveElement() {
        list.add(0, "Karol")
        list.add(1, "Vanessa")
        list.add(2, "Amanda")
        assertEquals("Amanda", list.removeAt(2))
        assertTrue(list.size == 2) //todo correct value 2
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun testRemoveWithEmptyList() {
        list.removeAt(0)
    }

}