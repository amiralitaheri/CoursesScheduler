package ir.saatgroup.coursesscheduler.ui

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_class_dialog_fragment.*

class EditClassDialogFragment(private var prev: Classes) : DialogFragment() {

    companion object {
        fun newInstance(prev: Classes) = EditClassDialogFragment(prev)
    }

    private lateinit var viewModel: EditClassDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_class_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditClassDialogViewModel::class.java)


        setPrevValues()

        //cancel button
        cancel.setOnClickListener {
            dialog?.dismiss()
        }

        //submit button
        submit.setOnClickListener { v ->
            if (nameInput.text.toString() != "") {
                val new = Classes(
                    nameInput.text.toString(),
                    disciplineInput.text.toString(),
                    stageInput.text.toString(),
                    idealTermInput.text.toString().toIntOrNull(),
                    unitsInput.toString().toIntOrNull()
                )

                viewModel.edit(prev, new).addOnSuccessListener {
                    val snack = Snackbar.make(
                        activity?.rootLayout!!, "Class ${new.name} edited successfully",
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

    private fun setPrevValues() {
        nameInput.setText(prev.name)
        stageInput.setText(prev.stage)
        disciplineInput.setText(prev.discipline)
        idealTermInput.setText(prev.idealTerm.toString())
        unitsInput.setText(prev.units.toString())
    }

}
