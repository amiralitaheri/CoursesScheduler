package ir.saatgroup.coursesscheduler.data.model

class ClassInstances() {
    var teacher: String? = null
        set(value) {
            field = value
            this.id = teacher + "&" + classes + "&" + System.currentTimeMillis().toString()
        }
    var classes: String? = null
        set(value) {
            field = value
            this.id = teacher + "&" + classes + "&" + System.currentTimeMillis().toString()
        }
    var isRegistered: Boolean = false
    lateinit var id: String
    lateinit var timess: MutableList<Time>

    constructor(teacher: String, classes: String, isRegistered: Boolean) : this() {
        this.teacher = teacher
        this.classes = classes
        this.isRegistered = isRegistered
        id = teacher + "&" + classes + "&" + System.currentTimeMillis().toString()
    }

}