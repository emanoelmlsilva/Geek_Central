package com.example.geek_central.observer

interface IObserver {

    fun update(filter : String? = null, typeOrder : String = "NOT", tpeWorkGeek: String)
}