package ir.saatgroup.coursesscheduler.data.viewmodel.adapters


import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ir.saatgroup.coursesscheduler.R
import ir.saatgroup.coursesscheduler.data.Repository
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.ui.TeacherDialogFragment
import kotlinx.android.synthetic.main.teacher_card_layout.view.*


class TeachersRecycleAdapter(private val inflater: LayoutInflater, var dataSource: List<Teacher>) :
    RecyclerView.Adapter<TeachersRecycleAdapter.TeacherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherHolder {
        return TeacherHolder(inflater.inflate(R.layout.teacher_card_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: TeacherHolder, position: Int) {
        holder.bindTeacher(dataSource[position])
    }

    class TeacherHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var parentView: View = v
        private var teacher: Teacher? = null
        private var bitmapLiveData: LiveData<Bitmap?>? = null

        fun bindTeacher(t: Teacher) {
            this.teacher = t
            bitmapLiveData = Repository.getImageBitmap(t.id, parentView.context)
            parentView.name.text = t.name
            if (bitmapLiveData!!.value != null) {
                parentView.pic.setImageBitmap(bitmapLiveData!!.value)
            } else {
                parentView.pic.setImageResource(R.drawable.man)
            }

            parentView.setOnClickListener {
                var activity = it.context as FragmentActivity
                val ft = activity.supportFragmentManager.beginTransaction()
                val prev = activity.supportFragmentManager.findFragmentByTag("teacherDialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                val dialogFragment = TeacherDialogFragment.newInstance(teacher!! ,bitmapLiveData!!)
                dialogFragment.show(ft, "teacherDialog")
            }

        }

    }
}

