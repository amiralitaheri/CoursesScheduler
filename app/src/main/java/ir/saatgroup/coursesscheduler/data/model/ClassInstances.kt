package ir.saatgroup.coursesscheduler.data.model

class ClassInstances () {
    var teacher: String? = null
    var classes: String? = null
    lateinit var id : String
    lateinit var timess: MutableList<Time>
    constructor(teacher: String,classes: String) : this(){
        this.teacher = teacher
        this.classes = classes
        id = teacher+"&"+classes+"&"+System.currentTimeMillis().toString()
    }

}