package ir.saatgroup.coursesscheduler.data.model

class Teacher() {
    lateinit var id: String
    var name: String? = null
    var degree: String? = null
    var discipline: String? = null
    var expertise: String? = null
    var birthYear: Int? = null
    var img: String? = null
    var email: String? = null

    constructor(
        id: String,
        name: String? = null,
        degree: String? = null,
        discipline: String? = null,
        expertise: String? = null,
        birthYear: Int? = null,
        img: String? = null,
        email: String? = null
    ):this(){
        this.id = id
        this.name = name
        this.degree = degree
        this.discipline = discipline
        this.expertise = expertise
        this.birthYear = birthYear
        this.img = img
        this.email = email
    }
}