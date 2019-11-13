package ir.saatgroup.coursesscheduler.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.ConstData
import ir.saatgroup.coursesscheduler.data.model.Teacher
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_teacher_dialog_fragment.*


class EditTeacherDialogFragment(private var prevTeacher: Teacher) : DialogFragment() {

    companion object {
        fun newInstance(prevTeacher: Teacher) = EditTeacherDialogFragment(prevTeacher)
    }

    private lateinit var viewModel: EditTeacherDialogViewModel
    private lateinit var profileImage: Bitmap
    private var picModified: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_teacher_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditTeacherDialogViewModel::class.java)

        //set previous values
        setPrevValues()

        //cancel button
        cancel.setOnClickListener {
            dialog?.dismiss()
        }

        //submit button
        submit.setOnClickListener { v ->
            if (nameInput.text.toString() != "") {
                //create a teacher base on the input
                val teacher = Teacher(
                    nameInput.text.toString(),
                    degreeInput.text.toString(),
                    disciplineInput.text.toString(),
                    expertiseInput.text.toString(),
                    birthYearInput.text.toString().toIntOrNull(),
                    null,
                    emailInput.text.toString()
                )
                //call edit function
                viewModel.editTeacher(prevTeacher,teacher).addOnSuccessListener {
                    val snack = Snackbar.make(
                        activity?.rootLayout!!,
                        "Teacher ${teacher.name} added successfully",
                        Snackbar.LENGTH_LONG
                    )
                    snack.view.setBackgroundColor(ContextCompat.getColor(activity!!, android.R.color.holo_blue_light))
                    snack.show()
                    //call edit image if edit teacher was successful and image was modified
                    if(picModified){
                        viewModel.editImage(profileImage, teacher.id, context!!).addOnFailureListener {
                            val snack = Snackbar.make(v, "Failed to upload image", Snackbar.LENGTH_LONG)
                            snack.view.setBackgroundColor(
                                ContextCompat.getColor(
                                    activity!!,
                                    android.R.color.holo_red_light
                                )
                            )
                            snack.show()
                        }
                    }
                    dialog?.dismiss()
                }
            } else {
                val snack = Snackbar.make(v, "Teacher name is required", Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(ContextCompat.getColor(activity!!, android.R.color.holo_red_light))
                snack.show()
            }
        }

        imageSelect.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, ConstData.RESULT_LOAD_IMG)
        }

    }

    private fun setPrevValues() {
        nameInput.setText(prevTeacher.name)
        degreeInput.setText(prevTeacher.degree)
        disciplineInput.setText(prevTeacher.discipline)
        expertiseInput.setText(prevTeacher.expertise)
        birthYearInput.setText(prevTeacher.birthYear.toString())
        emailInput.setText(prevTeacher.email)

        profilePic.setImageBitmap(viewModel.getImage(prevTeacher.id,context!!))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val imageStream = activity?.contentResolver?.openInputStream(imageUri!!)
            profileImage = BitmapFactory.decodeStream(imageStream).scale(240, 240, true)
            profilePic.setImageBitmap(profileImage)
            picModified = true
        }

    }

}
