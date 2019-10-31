package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.viewmodel.MainTabViewModel

class MainTabFragment : Fragment() {

    companion object {
        fun newInstance() = MainTabFragment()
    }

    private lateinit var viewModel: MainTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
