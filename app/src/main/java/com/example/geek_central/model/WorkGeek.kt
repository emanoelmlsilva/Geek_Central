package com.example.geek_central.model

class WorkGeek(
    var title: String? = null,
    var currentGeek: Int? = null,
    var season: Int? = null,
    var totalGeek: Int? = null,
    var categories: MutableList<String>? = null,
    var author: Author? = null,
    var popular: Popular? = null,
    var hosted: Hosted? = null
) {

}
