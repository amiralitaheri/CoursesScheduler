package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.data.viewmodel.ClassesTabViewModel
import ir.saatgroup.coursesscheduler.data.viewmodel.adapters.ClassesRecycleAdapter
import kotlinx.android.synthetic.main.classes_tab_fragment.*

class ClassesTabFragment : Fragment() {

    companion object {
        fun newInstance() = ClassesTabFragment()
    }

    private lateinit var viewModel: ClassesTabViewModel
    private lateinit var adapter: ClassesRecycleAdapter
    private lateinit var classLiveData: LiveData<MutableList<Classes>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.classes_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ClassesTabViewModel::class.java)
        classLiveData = viewModel.getClasses()
        adapter = ClassesRecycleAdapter(classLiveData.value as List<Classes>)
        val observer = Observer<List<Classes>>{
            adapter.dataSource = it
            adapter.notifyDataSetChanged()
        }
        classLiveData.observe(this,observer)
        classesRecyclerView.adapter = adapter

        addClass.setOnClickListener {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            val prev = activity?.supportFragmentManager?.findFragmentByTag("addClassDialog")
            if (prev != null) {
                ft?.remove(prev)
            }
            ft?.addToBackStack(null)
            val dialogFragment = AddClassDialogFragment()
            dialogFragment.show(ft!!, "addClassDialog")
        }
    }

}
