package ir.saatgroup.coursesscheduler.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.data.viewmodel.AddTeacherDialogViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_teacher_dialog_fragment.*
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
import androidx.lifecycle.LiveData
import ir.saatgroup.coursesscheduler.data.ConstData


class AddTeacherDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = AddTeacherDialogFragment()
    }

    private lateinit var viewModel: AddTeacherDialogViewModel
    private lateinit var profileImage: Bitmap
    private lateinit var teachersLiveData: LiveData<MutableList<Teacher>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_teacher_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddTeacherDialogViewModel::class.java)
        teachersLiveData = viewModel.getTeachers()
        //cancel button
        cancel.setOnClickListener {
            dialog?.dismiss()
        }

        //submit button
        submit.setOnClickListener { v ->
            if (nameInput.text.toString() != "") {
                for(t in teachersLiveData.value!!){
                    if(t.name ==nameInput.text.toString()){
                        val snack = Snackbar.make(v, "Teacher ${t.name} already exist!!", Snackbar.LENGTH_LONG)
                        snack.view.setBackgroundColor(ContextCompat.getColor(activity!!,android.R.color.holo_red_light))
                        snack.show()
                        return@setOnClickListener
                    }
                }

                val teacher = Teacher(
                    nameInput.text.toString(),
                    stageInput.text.toString(),
                    disciplineInput.text.toString(),
                    idealTermInput.text.toString(),
                    unitsInput.text.toString().toIntOrNull(),
                    null,
                    emailInput.text.toString()
                )

                viewModel.setTeacher(teacher).addOnSuccessListener {
                    val snack = Snackbar.make(
                        activity?.rootLayout!!,
                        "Teacher ${teacher.name} added successfully",
                        Snackbar.LENGTH_LONG
                    )
                    snack.view.setBackgroundColor(ContextCompat.getColor(activity!!,android.R.color.holo_blue_light))
                    snack.show()
                    viewModel.setImage(profileImage, teacher.id, context!!).addOnFailureListener {
                        val snack = Snackbar.make(v, "Failed to upload image", Snackbar.LENGTH_LONG)
                        snack.view.setBackgroundColor(ContextCompat.getColor(activity!!,android.R.color.holo_red_light))
                        snack.show()
                    }
                    dialog?.dismiss()
                }
            } else {
                val snack = Snackbar.make(v, "Teacher name is required", Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(ContextCompat.getColor(activity!!,android.R.color.holo_red_light))
                snack.show()
            }


        }



        profileImage = BitmapFactory.decodeResource(resources,R.drawable.man).scale(240, 240, true)
        profilePic.setImageBitmap(profileImage)


        imageSelect.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, ConstData.RESULT_LOAD_IMG)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val imageUri = data?.data
            val imageStream = activity?.contentResolver?.openInputStream(imageUri!!)
            profileImage = BitmapFactory.decodeStream(imageStream).scale(240, 240, true)
            profilePic.setImageBitmap(profileImage)
        }

    }
}
