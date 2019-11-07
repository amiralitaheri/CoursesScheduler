package ir.saatgroup.coursesscheduler.data.viewmodel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.ClassInstances

class ClassInstancesRecycleAdapter(
    private var inflater: LayoutInflater,
    private var classInstances: List<ClassInstances>
) :RecyclerView.Adapter<ClassInstancesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassInstancesViewHolder {
        return ClassInstancesViewHolder(inflater.inflate(R.layout.class_instance_card_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return classInstances.size
    }

    override fun onBindViewHolder(holder: ClassInstancesViewHolder, position: Int) {
        holder.bindClassInstance(classInstances[position])
    }


}
