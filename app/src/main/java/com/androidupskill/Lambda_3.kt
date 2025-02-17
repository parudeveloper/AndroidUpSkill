package com.androidupskill

fun main() {
    val exampleLambda : (Int, String) -> String ={ a, b ->
        "$a $b"
    }
    println(exampleLambda(28,"Mallangi Parameswar Reddy"))
}