package ir.saatgroup.coursesscheduler.data.model

class Classes (val id : Int){
    lateinit var name :String
    var discipline = -1
    lateinit var stage : String
    var idealTerm = 0
    lateinit var requirement : MutableList<Classes>
    var units = 0
    lateinit var times: MutableList<ClassInstances>
}