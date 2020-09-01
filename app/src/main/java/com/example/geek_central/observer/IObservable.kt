package com.example.geek_central.observer

interface IObservable {

    fun add(observer : IObserver)

    fun remove(observer: IObserver)

    fun sendUpdate(typeOrder : String = "NOT")

}