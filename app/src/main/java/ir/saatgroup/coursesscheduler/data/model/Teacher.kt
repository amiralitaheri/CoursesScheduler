package ir.saatgroup.coursesscheduler.data.model

import ir.saatgroup.coursesscheduler.utils.toId

class Teacher() {
    lateinit var id: String
    lateinit var name: String
    var degree: String? = null
    var discipline: String? = null
    var expertise: String? = null
    var birthYear: Int? = null
    var img: String? = null
    var email: String? = null

    constructor(
        name: String,
        degree: String? = null,
        discipline: String? = null,
        expertise: String? = null,
        birthYear: Int? = null,
        img: String? = null,
        email: String? = null
    ):this(){
        this.id = name.toId()
        this.name = name
        this.degree = degree
        this.discipline = discipline
        this.expertise = expertise
        this.birthYear = birthYear
        this.img = img
        this.email = email
    }
}