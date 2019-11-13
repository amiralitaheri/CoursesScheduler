package ir.saatgroup.coursesscheduler.data.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
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
        filteredClassInstances.add(ClassInstances("Mohsen Rezvani", "Data Mining",false))
        filteredClassInstances.add(ClassInstances("Mohsen Rezvani", "Network",false))
        filteredClassInstancesLiveData.value = filteredClassInstances
        return filteredClassInstancesLiveData
    }

    fun deleteTeacher(teacher: Teacher, context: Context): Task<Void> {
        Repository.deleteImage(teacher.id, context)
        return Repository.deleteTeacher(teacher)
    }
}
