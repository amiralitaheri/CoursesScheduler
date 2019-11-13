package ir.saatgroup.coursesscheduler.data.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.utils.toId
import kotlinx.coroutines.runBlocking
import androidx.appcompat.app.AppCompatActivity



class EditClassDialogViewModel : ViewModel() {
    fun edit(prev: Classes, new: Classes,context: Context): Task<Void> {
        Repository.deleteClasses(prev)

        val classInstancesLiveData = Repository.getClassInstances()
        val observer = Observer<List<ClassInstances>>{
            for (ci in it) {
                if (ci.classes?.toId() == prev.id) {
                    Repository.deleteClassInstance(ci)
                    ci.classes = new.name
                    Repository.setClassInstance(ci)
                }
            }
            classInstancesLiveData.removeObservers(context as AppCompatActivity)
        }
        classInstancesLiveData.observe(context as AppCompatActivity,observer)


        return Repository.setClass(new)
    }

    fun getClasses(): LiveData<MutableList<Classes>> = Repository.getClasses()
}
