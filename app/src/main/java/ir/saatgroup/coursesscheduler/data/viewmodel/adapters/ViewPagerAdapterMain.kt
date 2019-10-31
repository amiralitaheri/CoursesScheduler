package ir.saatgroup.coursesscheduler.data.viewmodel.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapterMain (fm: FragmentManager,lc:Lifecycle) : FragmentStateAdapter(fm,lc){

    val ITEM_COUNT = 5

    init {

    }

    override fun getItemCount(): Int {
        return  ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }







}