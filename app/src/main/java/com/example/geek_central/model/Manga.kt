package com.example.geek_central.model

class Manga(
    override var title: String? = null,
    override var currentGeek: Int? = null,
    override var totalGeek: Int? = null,
    override var note: Double? = null,
    override var favorite: Boolean? = null,
    override var categories: MutableList<String>? = null,
    override var author: Author? = null,
    override var popular: Popular? = null,
    override var hosted: Hosted? = null
) : WorkGeek{

}
