package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.data.viewmodel.TeachersTabViewModel
import ir.saatgroup.coursesscheduler.data.viewmodel.adapters.TeachersRecycleAdapter
import kotlinx.android.synthetic.main.teachers_tab_fragment.*


class TeachersTabFragment : Fragment() {

    companion object {
        fun newInstance() = TeachersTabFragment()
    }

    private lateinit var viewModel: TeachersTabViewModel
    private lateinit var adapter: TeachersRecycleAdapter
    private lateinit var teachersLiveData: LiveData<MutableList<Teacher>>
    private var v: View? = null
    private lateinit var inflater: LayoutInflater


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        v = inflater.inflate(R.layout.teachers_tab_fragment, container, false)
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeachersTabViewModel::class.java)
        teachersLiveData = viewModel.getTeachers()
        adapter = TeachersRecycleAdapter(inflater,teachersLiveData.value as List<Teacher>)
        val observer = Observer<List<Teacher>>{
            adapter.dataSource = it
            adapter.notifyDataSetChanged()
        }
        teachersLiveData.observe(this,observer)
        teachersRecyclerView.adapter = adapter

        addTeacher.setOnClickListener {
            val activity = it.context as FragmentActivity
            val ft = activity.supportFragmentManager.beginTransaction()
            val prev = activity.supportFragmentManager.findFragmentByTag("addTeacherDialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            val dialogFragment = AddTeacherDialogFragment()
            dialogFragment.show(ft, "addTeacherDialog")
        }
    }

}
