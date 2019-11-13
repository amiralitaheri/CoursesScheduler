package ir.saatgroup.coursesscheduler.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Classes

class AddClassDialogViewModel : ViewModel() {
    fun setClass(c : Classes) = Repository.setClass(c)
    fun getClasses(): LiveData<MutableList<Classes>> = Repository.getClasses()
}
