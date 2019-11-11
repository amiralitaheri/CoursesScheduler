package ir.saatgroup.coursesscheduler.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.utils.toId

class ClassDialogViewModel : ViewModel() {

    private val filteredClassInstancesLiveData = MutableLiveData<MutableList<ClassInstances>>()
    private val filteredClassInstances = mutableListOf<ClassInstances>()


    fun deleteClass(c : Classes) = Repository.deleteClasses(c)


    fun getClasses(id: String): LiveData<MutableList<ClassInstances>> {
        val classInstances = Repository.getClassInstances().value
        if (classInstances != null) {
            for (classInstance in classInstances) {
                if (classInstance.classes?.toId() == id) {
                    filteredClassInstances.add(classInstance)
                }
            }
        }
        filteredClassInstancesLiveData.value = filteredClassInstances
        return filteredClassInstancesLiveData
    }
}
