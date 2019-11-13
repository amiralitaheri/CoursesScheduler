package ir.saatgroup.coursesscheduler.data.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.google.firebase.storage.UploadTask
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Teacher

class AddTeacherDialogViewModel : ViewModel() {
    fun setTeacher(teacher: Teacher) = Repository.setTeacher(teacher)

    fun setImage(profileImage: Bitmap, name: String, context: Context): UploadTask {
        Repository.storeImage(profileImage, name, context)
        return Repository.uploadImage(profileImage, name)
    }

    fun getTeachers(): LiveData<MutableList<Teacher>> = Repository.getTeachers()
}
