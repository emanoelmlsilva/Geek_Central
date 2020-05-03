package com.example.geek_central.Model

interface WorkGeek {
    var title: String
    var currentGeek: Double
    var totalGeek: Double
    var note: Double
    var favorite: Boolean
    var categories: MutableList<String>
    var author: Author
    var popular: Popular
    var hosted: Hosted
}