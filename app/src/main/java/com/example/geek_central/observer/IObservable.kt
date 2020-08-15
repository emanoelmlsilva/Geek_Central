package com.example.geek_central.observer

interface IObservable {

    fun add(observer : IObserver, type : String)

    fun remove(observer: IObserver)

    fun sendUpdate(TypeOrder : Boolean = false)

}