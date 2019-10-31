package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.viewmodel.SettingTabViewModel

class SettingTabFragment : Fragment() {

    companion object {
        fun newInstance() = SettingTabFragment()
    }

    private lateinit var viewModel: SettingTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
