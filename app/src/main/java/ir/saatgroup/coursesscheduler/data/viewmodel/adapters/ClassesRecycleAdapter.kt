package ir.saatgroup.coursesscheduler.data.viewmodel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.utils.inflate
import kotlinx.android.synthetic.main.class_card_layout.view.*

class ClassesRecycleAdapter(private val inflater: LayoutInflater, var dataSource: List<Classes>):
    RecyclerView.Adapter<ClassesRecycleAdapter.ClassHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesRecycleAdapter.ClassHolder {
        return ClassHolder(inflater.inflate(R.layout.class_card_layout,parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ClassesRecycleAdapter.ClassHolder, position: Int) {
        holder.bindClass(dataSource[position])
    }

    class ClassHolder(v:View):RecyclerView.ViewHolder(v){
        private var parentView: View = v
        private var clas: Classes? = null
        fun bindClass(c :Classes){
            this.clas = c
            parentView.name.text = c.name
        }
    }
}