package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ir.saatgroup.coursesscheduler.R

class teacherDialogFragment : Fragment() {

    companion object {
        fun newInstance() = teacherDialogFragment()
    }

    private lateinit var viewModel: TeacherDialogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.teacher_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeacherDialogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
