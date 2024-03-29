package ir.saatgroup.coursesscheduler.ui


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.data.viewmodel.AddClassDialogViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_class_dialog_fragment.*


class AddClassDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = AddClassDialogFragment()
    }

    private lateinit var viewModel: AddClassDialogViewModel
    private lateinit var classesLiveData: LiveData<MutableList<Classes>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_class_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddClassDialogViewModel::class.java)
        classesLiveData = viewModel.getClasses()

        //cancel button
        cancel.setOnClickListener {
            dialog?.dismiss()
        }

        //submit button
        submit.setOnClickListener { v ->
            if (nameInput.text.toString() != "") {
                for (c in classesLiveData.value!!) {
                    if (c.name == nameInput.text.toString()) {
                        val snack = Snackbar.make(v, "Class ${c.name} already exist!!", Snackbar.LENGTH_LONG)
                        snack.view.setBackgroundColor(
                            ContextCompat.getColor(
                                activity!!,
                                android.R.color.holo_red_light
                            )
                        )
                        snack.show()
                        return@setOnClickListener
                    }
                }
                val clas = Classes(
                    nameInput.text.toString(),
                    disciplineInput.text.toString(),
                    stageInput.text.toString(),
                    idealTermInput.text.toString().toIntOrNull(),
                    unitsInput.toString().toIntOrNull()
                )

                viewModel.setClass(clas).addOnSuccessListener {
                    val snack = Snackbar.make(
                        activity?.rootLayout!!, "Class ${clas.name} added successfully",
                        Snackbar.LENGTH_LONG
                    )
                    snack.view.setBackgroundColor(ContextCompat.getColor(activity!!, android.R.color.holo_blue_light))
                    snack.show()
                    dialog?.dismiss()
                }
            } else {
                val snack = Snackbar.make(v, "Class name is required", Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(ContextCompat.getColor(activity!!, android.R.color.holo_red_light))
                snack.show()
            }


        }
    }

}
