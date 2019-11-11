package ir.saatgroup.coursesscheduler.ui

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

class AddTeacherDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = AddTeacherDialogFragment()
    }

    private lateinit var viewModel: AddTeacherDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_teacher_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddTeacherDialogViewModel::class.java)

        //cancel button
        cancel.setOnClickListener {
            dialog?.dismiss()
        }

        //submit button
        submit.setOnClickListener { v ->
            if(nameInput.text.toString() != ""){
                val teacher = Teacher(nameInput.text.toString(),stageInput.text.toString(),disciplineInput.text.toString(),
                    idealTermInput.text.toString(),unitsInput.text.toString().toIntOrNull(),null,emailInput.text.toString())

                viewModel.setTeacher(teacher).addOnSuccessListener {
                    val snack = Snackbar.make(activity?.rootLayout!!,"Teacher ${teacher.name} added successfully",Snackbar.LENGTH_LONG)
                    snack.show()
                    dialog?.dismiss()
                }
            }else{
                val snack = Snackbar.make(v,"Teacher name is required",Snackbar.LENGTH_LONG)
                snack.show()
            }


        }

    }

}
