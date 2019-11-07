package ir.saatgroup.coursesscheduler.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.data.viewmodel.TeacherDialogViewModel
import ir.saatgroup.coursesscheduler.data.viewmodel.adapters.ClassInstancesRecycleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.teacher_dialog_fragment.*
import java.util.*

class TeacherDialogFragment(private val teacher: Teacher) : DialogFragment() {

    companion object {
        fun newInstance(teacher: Teacher) = TeacherDialogFragment(teacher)
    }

    private lateinit var viewModel: TeacherDialogViewModel
    private lateinit var classesLiveData: LiveData<MutableList<ClassInstances>>
    private lateinit var adapter: ClassInstancesRecycleAdapter
    private lateinit var inflater: LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        return inflater.inflate(R.layout.teacher_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeacherDialogViewModel::class.java)

        setCardValues()

        email.setOnClickListener {
            val i = Intent(Intent.ACTION_SENDTO)
            i.setData(Uri.parse("mailto:${teacher.email}"))
            startActivity(i)
        }

        delete.setOnClickListener { v ->
            viewModel.deleteTeacher(teacher).addOnSuccessListener {
                val snack = Snackbar.make(activity?.rootLayout!!, "${teacher.name} deleted successfully", Snackbar.LENGTH_LONG)
                snack.show()
                dialog?.dismiss()
            }.addOnFailureListener {
                val snack = Snackbar.make(v, "Something went wrong!! Try again", Snackbar.LENGTH_LONG)
                snack.show()
            }
        }


        classesLiveData = viewModel.getClasses(teacher.id)
        adapter = ClassInstancesRecycleAdapter(inflater, classesLiveData.value as List<ClassInstances>)
        classInstancesRecycleView.adapter = adapter
    }

    fun setCardValues() {
        name.text = teacher.name
        degree.text = teacher.degree
        discipline.text = teacher.discipline
        expertise.text = teacher.expertise
        if (teacher.birthYear != null) {
            age.text = (Calendar.getInstance().get(Calendar.YEAR) - teacher.birthYear!!).toString()
        } else {
            age.text = getString(R.string.unknown)
        }
        email.text = teacher.email
        if (teacher.img != null) {
            profilePic.setImageURI(Uri.parse(teacher.img))
        } else {
            profilePic.setImageResource(R.drawable.man)
        }
    }

}
