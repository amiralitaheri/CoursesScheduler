package ir.saatgroup.coursesscheduler.data.model

import ir.saatgroup.coursesscheduler.utils.toId

class Classes() {
    lateinit var id: String
    var name: String? = null
    var discipline: String? = null
    var stage: String? = null
    var idealTerm: Int? = null
    var units: Int? = null
    var requirement: MutableList<String>? = null

    constructor(
        name: String,
        discipline: String? = null,
        stage: String? = null,
        idealTerm: Int? = null,
        units: Int? = null,
        requirement: MutableList<String>? = null
    ) : this() {
        this.name = name
        this.discipline = discipline
        this.stage = stage
        this.idealTerm = idealTerm
        this.units = units
        this.requirement = requirement
        id = name.toId()
    }


}
