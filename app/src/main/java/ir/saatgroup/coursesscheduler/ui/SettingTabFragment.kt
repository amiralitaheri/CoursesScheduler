package ir.saatgroup.coursesscheduler.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.viewmodel.SettingTabViewModel
import kotlinx.android.synthetic.main.setting_tab_fragment.*

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

        logout.setOnClickListener {v ->
            AuthUI.getInstance().signOut(v.context).addOnSuccessListener {
                val i = Intent(v.context, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(i)
                val activity = v.context as Activity
                activity.finish()
            }
        }
    }

}
