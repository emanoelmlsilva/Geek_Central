package com.example.geek_central.model

interface WorkGeek {
    var title: String?
    var currentGeek: Int?
    var totalGeek: Int?
    var categories: MutableList<String>?
    var author: Author?
    var popular: Popular?
    var hosted: Hosted?
}