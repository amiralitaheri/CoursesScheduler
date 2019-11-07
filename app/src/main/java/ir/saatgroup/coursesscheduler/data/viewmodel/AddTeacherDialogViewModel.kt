package ir.saatgroup.coursesscheduler.data.viewmodel

import androidx.lifecycle.ViewModel;
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Teacher

class AddTeacherDialogViewModel : ViewModel() {
    fun setTeacher(teacher : Teacher) = Repository.setTeacher(teacher)
}
