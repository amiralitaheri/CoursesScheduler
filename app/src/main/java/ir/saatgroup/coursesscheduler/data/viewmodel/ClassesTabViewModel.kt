package ir.saatgroup.coursesscheduler.data.viewmodel

import androidx.lifecycle.ViewModel
import ir.saatgroup.coursesscheduler.data.Repository

class ClassesTabViewModel : ViewModel() {
    fun getClasses() = Repository.getClasses()
}
