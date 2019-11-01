package ir.saatgroup.coursesscheduler.data.model

class Time(){
    var day:Int = -1
    var startHour:Int = -1
    var endHour:Int = -1
    var week:String = ""
    constructor(day:Int,startHour:Int,endHour:Int,week:String) : this(){
        this.day = day
        this.startHour = startHour
        this.endHour = endHour
        this.week = week
    }
}