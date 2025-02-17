package com.androidupskill
data class Student(val name:String,val age : Int)

fun main() {
    val listOfStudent = ArrayList<Student>()
    listOfStudent.add(Student("Mallangi",28))
    listOfStudent.add(Student("Mallangi",18))
    listOfStudent.add(Student("Mallangi",17))
    listOfStudent.add(Student("Mallangi",16))
    listOfStudent.add(Student("Mallangi",15))
    listOfStudent.add(Student("Mallangi",27))
    listOfStudent.add(Student("Mallangi",24))

    val filter = listOfStudent.filter {it.age>=18}
    println(filter.toString())
}