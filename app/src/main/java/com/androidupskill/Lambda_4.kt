package com.androidupskill

fun main() {
    // 1- Skip the function type.
    val variable1 = { a: Int, b: Int, c: Int ->
        a + b + c
    }
    println(variable1(2, 2, 2))

    // 2- Skip the data types inside the curly brackets
    val variable2: (Int, Int) -> Int = { a, b ->
        a + b
    }
    println(variable2(2, 2))
    // 1-With Parameters and No Return Value:
    val variable3: (Int, String) -> Unit = { a, b ->
        println("$a $b")

    }
    variable3(12, "Malangi")

    // 3-No Parameters and No Return Value:
    val variable4: () -> Unit = { println("\"No Parameters and No Return Value\" ") }
    variable4()

    // 4-No Parameters and Return Value:
    val variable5 :() ->String ={
        "No Parameters and Return Value"
    }
    println(variable5())
// A variable isn't always necessary, as lambda expressions can be used directly.
    println({a:Int,b:String -> "$a $b" } (2,"String Vlauee"))


}