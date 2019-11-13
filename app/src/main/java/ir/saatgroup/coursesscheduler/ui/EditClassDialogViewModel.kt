package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Classes
import kotlinx.coroutines.runBlocking

class EditClassDialogViewModel : ViewModel() {
    fun edit(prev: Classes, new: Classes): Task<Void> {
        runBlocking { Repository.deleteClasses(prev) }
        return Repository.setClass(new)
    }
}
