package com.androidupskill

fun main() {
    val result : (Int,Int) -> Int ={a,b ->
        val result = a+b
        result
    }

    println(result(20,20))
}