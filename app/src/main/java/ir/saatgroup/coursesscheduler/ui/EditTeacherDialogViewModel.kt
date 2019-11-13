package ir.saatgroup.coursesscheduler.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Teacher
import kotlinx.coroutines.runBlocking

class EditTeacherDialogViewModel : ViewModel() {
    fun getImage(id: String, context: Context): Bitmap? {
        return Repository.getImageBitmap(id, context).value
    }

    fun editTeacher(prev: Teacher, new: Teacher): Task<Void> {
        runBlocking { Repository.deleteTeacher(prev) }
        return Repository.setTeacher(new)
    }

    fun editImage(image: Bitmap, id: String, context: Context): UploadTask {
        Repository.storeImage(image,id,context)
        return Repository.uploadImage(image,id)
    }
}
