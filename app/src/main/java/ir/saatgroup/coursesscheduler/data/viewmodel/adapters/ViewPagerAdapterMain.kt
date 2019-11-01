package ir.saatgroup.coursesscheduler.data.viewmodel.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.saatgroup.coursesscheduler.ui.*
import java.lang.IndexOutOfBoundsException

class ViewPagerAdapterMain (fm: FragmentManager,lc:Lifecycle) : FragmentStateAdapter(fm,lc){

    private val ITEM_COUNT = 5
    private var teachersTabFragment =TeachersTabFragment.newInstance()
    private var classesTabFragment = ClassesTabFragment.newInstance()
    private var mainTabFragment = MainTabFragment.newInstance()
    private var profileTabFragment = ProfileTabFragment.newInstance()
    private var settingTabFragment = SettingTabFragment.newInstance()

    override fun getItemCount(): Int {
        return  ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> teachersTabFragment
            1 -> classesTabFragment
            2 -> mainTabFragment
            3 -> profileTabFragment
            4 -> settingTabFragment
            else -> throw IndexOutOfBoundsException()
        }
    }







}