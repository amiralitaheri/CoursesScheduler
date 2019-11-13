package ir.saatgroup.coursesscheduler.data.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.utils.toId
import kotlinx.coroutines.runBlocking

class EditTeacherDialogViewModel : ViewModel() {
    fun getImage(id: String, context: Context): Bitmap? {
        return Repository.getImageBitmap(id, context).value
    }

    fun editTeacher(prev: Teacher, new: Teacher, context: Context): Task<Void> {
        val classInstancesLiveData = Repository.getClassInstances()
        val observer = Observer<List<ClassInstances>> {
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

        runBlocking { Repository.deleteTeacher(prev) }
        return Repository.setTeacher(new)
    }

    fun editImage(image: Bitmap, id: String, context: Context): UploadTask {
        Repository.storeImage(image, id, context)
        return Repository.uploadImage(image, id)
    }
}
