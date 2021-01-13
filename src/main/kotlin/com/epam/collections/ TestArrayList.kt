package com.epam.collections



/**
 * Resizable-array implementation of the <tt>List</tt> interface.  Implements
 * all optional list operations except ListIterator and subList. Permits all elements, including <tt>null</tt>.
 *
 *
 *
 * The capacity is the size of the array used to store the elements in the list.  It is always
 * at least as large as the list size.  As elements are added to an ArrayList,
 * its capacity grows automatically. Formula is capacity += capacity/2;
 * By default capacity equal 10;
 *
 *
 *
 * In case adding a large number of elements. Exist logic which allows to created new capacity for collection
 * which equals current capacity + capacity of external collection
 *
 *
 *
 * **Note that <tt>VehiclesList</tt>  can contain only Object extends Vehicles.**
 *
 *
 *
 *  <tt>VehiclesList</tt>  has public static method filter().
 *
 * @param <V> all objects which expends Vehicles
 * @author Alexandr Lenivenko
 * @version 1.0
</V> */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class TestArrayList<V: Any>() : MutableList<V?> {
    /**
     * Capacity for VehiclesList
     */
    private var capacity = 10

    /**
     * Array of objects extends Vehicles
     */
    private var data: Array<Any?> = arrayOfNulls(capacity)

    /**
     * Marker which shows current position to add new item
     */
    private var pointer = 0

    override val size: Int
        get() = pointer

    override fun contains(element: V?): Boolean = indexOf(element) != -1

    override fun containsAll(elements: Collection<V?>): Boolean {
        for (item in elements) {
            if (!contains(item)) {
                return false
            }
        }
        return true
    }

    override fun isEmpty(): Boolean = pointer == 0

    override fun add(element: V?): Boolean {
        checkListSize()
        data[pointer] = element

        return when (element) {
            null -> {
                pointer++
                data[pointer] == null
            }
            data[pointer] -> {
                pointer++
                true
            }
            else -> false
        }
    }

    override fun addAll(elements: Collection<V?>): Boolean {
        throwExceptionIfCollectionNull(elements)
        val preRemoveSize = pointer
        for (item in elements) {
            add(item as V)
        }
        return preRemoveSize < pointer
    }

    override fun clear() {
        for (i in 0 until pointer) data[i] = null

        pointer = 0
    }

    override fun iterator(): MutableIterator<V?> {
        TODO("Not yet implemented")
    }

    override fun remove(element: V?): Boolean {
        if (pointer == 0) throw UnsupportedOperationException()
        val index = indexOf(element)
        if (index != -1) {
            removeAt(index)
            return true
        }
        return false
    }

    override fun removeAll(elements: Collection<V?>): Boolean {

        val preRemoveSize = pointer
        for (i in pointer - 1 downTo 0) {
            if ((elements as TestArrayList<*>).indexOf(data[i]) !== -1) {
                remove(data[i])
            }
        }
        return preRemoveSize > pointer
    }

    override fun retainAll(elements: Collection<V?>): Boolean {
        throwExceptionIfCollectionNull(elements as Collection<V?>?)
        val tempPointer = pointer
        for (i in pointer downTo 0) {
            if (!elements.contains(data[i])) {
                remove(data[i])
            }
        }
        return tempPointer != pointer
    }

    override fun get(index: Int): V? {
        validateIndex(index)
        return  data[index] as V?
    }

    override fun indexOf(element: V?): Int {
        if (element == null) {
            for (i in 0 until pointer) if (data[i] == null) return i
        } else {
            for (i in 0 until pointer) if (element == data[i]) return i
        }
        return -1
    }

    override fun lastIndexOf(element: V?): Int {
        if (element == null) {
            for (i in pointer - 1 downTo 0) if (data[i] == null) return i
        } else {
            for (i in pointer - 1 downTo 0) if (element == data[i]) return i
        }
        return -1
    }

    override fun add(index: Int, element: V?) {
        checkListSize()
        validateIndex(index)
        System.arraycopy(data, index, data, index + 1,
                pointer - index)
        data[index] = element
        pointer++
    }

    override fun addAll(index: Int, elements: Collection<V?>): Boolean {
        validateIndex(index)
        throwExceptionIfCollectionNull(elements)
        val collectionSize: Int = elements.size
        if (collectionSize == 0) return false
        val sumToCollectionSize = pointer + collectionSize

        val tempArrayForMoving = arrayOfNulls<Any?>(pointer - index)
        System.arraycopy(data, index, tempArrayForMoving, 0, pointer - index)

        if (sumToCollectionSize >= capacity) {
            capacity = sumToCollectionSize //Maybe should give extra ?? . For example + 10;
        }
        val newData: Array<Any?> = arrayOfNulls(capacity)
        System.arraycopy(data, 0, newData, 0, index)
        System.arraycopy(arrayOf(elements), 0, newData, index, elements.size)
        System.arraycopy(tempArrayForMoving, 0, newData, index + elements.size, tempArrayForMoving.size)


        data = newData
        pointer = sumToCollectionSize
        return true
    }

    override fun listIterator(): MutableListIterator<V?> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<V?> {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): V? {
        if (index >= pointer) {
            throw IndexOutOfBoundsException()
        }
        validateIndex(index)
        val moveCount = pointer - index
        val removedVehicles: V? = data[index] as V?
        System.arraycopy(data, index + 1, data, index,
                moveCount)
        pointer--
        return removedVehicles
    }

    override fun set(index: Int, element: V?): V? {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<V?> {
        TODO("Not yet implemented")
    }

    /**
     * Increase capacity of array in case if array is full
     * Formula: capacity += capacity /2; // old capacity = 10 -> new capacity 15;
     */
    private fun checkListSize() {
        if (pointer == capacity - 1) {
            capacity += capacity / 2
            val newData = arrayOfNulls<Any?>(capacity)
            System.arraycopy(data, 0, newData, 0, pointer)
            data = newData
        }
    }

    private fun throwExceptionIfCollectionNull(c: Collection<V?>?) {
        c ?: throw NullPointerException()
    }
    private fun validateIndex(index: Int) {
        if (index < 0 || index > pointer) {
            throw IndexOutOfBoundsException()
        }
    }
}
