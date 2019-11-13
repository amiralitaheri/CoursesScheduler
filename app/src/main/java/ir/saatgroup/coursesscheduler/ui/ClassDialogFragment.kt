package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.data.viewmodel.ClassDialogViewModel
import ir.saatgroup.coursesscheduler.data.viewmodel.adapters.ClassInstancesRecycleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.class_dialog_fragment.*

class ClassDialogFragment(private val clas : Classes) : DialogFragment() {

    companion object {
        fun newInstance(clas: Classes) = ClassDialogFragment(clas)
    }

    private lateinit var viewModel: ClassDialogViewModel
    private lateinit var classesInstancesLiveData: LiveData<MutableList<ClassInstances>>
    private lateinit var inflater: LayoutInflater
    private lateinit var adapter: ClassInstancesRecycleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        return inflater.inflate(R.layout.class_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ClassDialogViewModel::class.java)

        name.text = clas.name
        stage.text = clas.stage
        idealTerm.text = clas.idealTerm.toString()
        discipline.text = clas.discipline
        units.text = clas.units.toString()

        delete.setOnClickListener { v ->
            if(classesInstancesLiveData.value?.size == 0) {
                viewModel.deleteClass(clas).addOnSuccessListener {
                    val snack =
                        Snackbar.make(activity?.rootLayout!!, "${clas.name} deleted successfully", Snackbar.LENGTH_LONG)
                    snack.show()
                    dialog?.dismiss()
                }.addOnFailureListener {
                    val snack = Snackbar.make(v, "Something went wrong!! Try again", Snackbar.LENGTH_LONG)
                    snack.show()
                }
            }else{
                val snack = Snackbar.make(v, "You can't delete a class when, it has some class instances", Snackbar.LENGTH_LONG)
                snack.show()
            }
        }

        edit.setOnClickListener {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            val prev = activity?.supportFragmentManager?.findFragmentByTag("editClassDialog")
            activity?.supportFragmentManager?.popBackStack("classDialog", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            if (prev != null) {
                ft?.remove(prev)
            }
            ft?.addToBackStack("editClassDialog")

            val dialogFragment = EditClassDialogFragment(clas)
            dialogFragment.show(ft!!, "editClassDialog")

        }

        classesInstancesLiveData = viewModel.getClasses(clas.id)
        adapter = ClassInstancesRecycleAdapter(inflater, classesInstancesLiveData.value as List<ClassInstances>)
        classInstancesRecycleView.adapter = adapter

    }

}
