package ir.saatgroup.coursesscheduler.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.utils.toId

class TeacherDialogViewModel : ViewModel() {
    private val filteredClassInstancesLiveData = MutableLiveData<MutableList<ClassInstances>>()
    private val filteredClassInstances = mutableListOf<ClassInstances>()
    fun getClasses(id: String): LiveData<MutableList<ClassInstances>> {
        val classInstances = Repository.getClassInstances().value
        if (classInstances != null) {
            for (classInstance in classInstances) {
                if (classInstance.teacher?.toId() == id) {
                    filteredClassInstances.add(classInstance)
                }
            }
        }
        filteredClassInstancesLiveData.value = filteredClassInstances
        return filteredClassInstancesLiveData
    }

    fun getDummyClasses(): MutableLiveData<MutableList<ClassInstances>> {
        filteredClassInstances.add(ClassInstances("Mohsen Rezvani", "Data Mining"))
        filteredClassInstances.add(ClassInstances("Mohsen Rezvani", "Network"))
        filteredClassInstancesLiveData.value = filteredClassInstances
        return filteredClassInstancesLiveData
    }

    fun deleteTeacher(teacher : Teacher) = Repository.deleteTeacher(teacher)
}
