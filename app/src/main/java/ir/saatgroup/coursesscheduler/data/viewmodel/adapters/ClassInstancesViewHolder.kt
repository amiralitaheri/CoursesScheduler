package ir.saatgroup.coursesscheduler.data.viewmodel.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import kotlinx.android.synthetic.main.class_instance_card_layout.view.*

class ClassInstancesViewHolder(v : View) :RecyclerView.ViewHolder(v) {
    private var parentView: View = v
    private var classInstance: ClassInstances? = null

    fun bindClassInstance(c : ClassInstances){
        this.classInstance = c
        parentView.className.text = classInstance?.classes
    }
}