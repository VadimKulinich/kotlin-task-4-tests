package com.epam.task1

class Zoo(private val animalsLimit: Int, private val weightLimit: Double) {

    private val animals = ArrayList<Animal>()

    companion object {
        fun create(animalsLimit: Int, weightLimit: Double): Zoo {
            if (animalsLimit <= 0 || weightLimit <= 0.0) {
                throw IllegalArgumentException()
            }
            return Zoo(animalsLimit, weightLimit)
        }
    }

    fun getAllAnimals(): List<Animal> = animals

    operator fun plusAssign(animal: Animal) {
        if (animals.size + 1 > animalsLimit || weightLimit < animals.sumByDouble { it.weight } + animal.weight || animal.weight <= 0) {
            throw IllegalArgumentException("Cannot add animal due to restriction violations")
        }
        animals.add(animal)
    }

    operator fun minusAssign(animal: Animal) {
        animals.remove(animal)
    }

    operator fun minusAssign(index: Int) {
        if (index >= animals.size) {
            throw IllegalArgumentException("Wrong animal index $index")
        }
        animals.removeAt(index)
    }

    operator fun invoke(weight: Double) {
        animals.forEach {
            if (it.weight > weight) {
                println(it.name)
            }
        }
    }

    operator fun invoke() {
        animals.forEach {
            println("${it.name} fed completed")
        }
    }
}