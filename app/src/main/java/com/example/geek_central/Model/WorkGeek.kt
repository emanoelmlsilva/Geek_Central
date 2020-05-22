package com.example.geek_central.Model

interface WorkGeek {
    var title: String
    var currentGeek: Int
    var totalGeek: Int
    var note: Double
    var favorite: Boolean
    var categories: MutableList<String>?
    var author: Author?
    var popular: Popular?
    var hosted: Hosted?
}