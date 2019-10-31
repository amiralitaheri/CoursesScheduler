package ir.saatgroup.coursesscheduler.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.viewmodel.ProfileTabViewModel

class ProfileTabFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileTabFragment()
    }

    private lateinit var viewModel: ProfileTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
