package ir.saatgroup.coursesscheduler.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Teacher

class TeachersTabViewModel : ViewModel() {
    fun getTeachers() = Repository.getTeachers()

    fun getDummyTeachers(): LiveData<MutableList<Teacher>> {
        val teachersLiveData = MutableLiveData<MutableList<Teacher>>()
        val teachersList = mutableListOf<Teacher>()
        teachersList.add(Teacher("teacherid1","name1"))
        teachersList.add(Teacher("teacherid2","name2"))
        teachersList.add(Teacher("teacherid3","name3"))
        teachersLiveData.value =  teachersList
        return teachersLiveData
    }
}
