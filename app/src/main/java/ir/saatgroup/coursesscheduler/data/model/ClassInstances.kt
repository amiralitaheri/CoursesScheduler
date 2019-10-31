package ir.saatgroup.coursesscheduler.data.model

data class ClassInstances(val id:Int) {

    lateinit var teacher: Teacher
    lateinit var Times: MutableList<Time>

}