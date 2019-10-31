package ir.saatgroup.coursesscheduler.data.model

data class Teacher(val id : Int){
    lateinit var name : String
    lateinit var degree : String
    lateinit var discipline : String
    lateinit var expertise : String
    var birthYear = 1900
    lateinit var img : String
    lateinit var email : String
    lateinit var classes : MutableList<Classes>
}