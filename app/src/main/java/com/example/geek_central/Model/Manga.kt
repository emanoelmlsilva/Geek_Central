package com.example.geek_central.Model

class Manga(
    override var title: String,
    override var currentGeek: Int,
    override var totalGeek: Int,
    override var note: Double,
    override var favorite: Boolean,
    override var categories: MutableList<String>?,
    override var author: Author?,
    override var popular: Popular?,
    override var hosted: Hosted?
) : WorkGeek{

}
