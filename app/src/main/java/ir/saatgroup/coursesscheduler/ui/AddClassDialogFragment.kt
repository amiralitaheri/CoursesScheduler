package ir.saatgroup.coursesscheduler.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_class_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddClassDialogViewModel::class.java)


        //cancel button
        cancel.setOnClickListener {
            dialog?.dismiss()
        }

        //submit button
        submit.setOnClickListener { v ->
            if(nameInput.text.toString() != ""){
                val clas = Classes(nameInput.text.toString(),disciplineInput.text.toString(),
                    stageInput.text.toString(),idealTermInput.text.toString().toIntOrNull(),unitsInput.toString().toIntOrNull())

                viewModel.setClass(clas).addOnSuccessListener {
                    val snack = Snackbar.make(activity?.rootLayout!!,"Class ${clas.name} added successfully",
                        Snackbar.LENGTH_LONG)
                    snack.view.setBackgroundColor(ContextCompat.getColor(activity!!,android.R.color.holo_blue_light))
                    snack.show()
                    dialog?.dismiss()
                }
            }else{
                val snack = Snackbar.make(v,"Class name is required", Snackbar.LENGTH_LONG)
                snack.view.setBackgroundColor(ContextCompat.getColor(activity!!,android.R.color.holo_red_light))
                snack.show()
            }


        }
    }

}
