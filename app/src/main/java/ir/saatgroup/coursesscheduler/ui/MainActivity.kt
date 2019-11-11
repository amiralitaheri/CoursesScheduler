package ir.saatgroup.coursesscheduler.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.Repository

import ir.saatgroup.coursesscheduler.data.viewmodel.adapters.ViewPagerAdapterMain

class MainActivity : AppCompatActivity() {

    lateinit var viewPager : ViewPager2
    lateinit var viewPagerAdapterMain: ViewPagerAdapterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPagerMain)
        viewPagerAdapterMain = ViewPagerAdapterMain(supportFragmentManager,lifecycle)
        viewPager.adapter = viewPagerAdapterMain
        Repository.init()
    }


}