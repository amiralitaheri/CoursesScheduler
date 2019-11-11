@file:Suppress("UNCHECKED_CAST", "CAST_NEVER_SUCCEEDS")

package ir.saatgroup.coursesscheduler.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

import ir.saatgroup.coursesscheduler.data.model.ClassInstances
import ir.saatgroup.coursesscheduler.data.model.Classes
import ir.saatgroup.coursesscheduler.data.model.Teacher
import ir.saatgroup.coursesscheduler.data.model.Time

import java.util.*

object Repository {
    @SuppressLint("StaticFieldLeak")
    private val db = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    private val teachersLiveData = MutableLiveData<MutableList<Teacher>>()
    private val classesLiveData = MutableLiveData<MutableList<Classes>>()
    private val classInstancesLiveData = MutableLiveData<MutableList<ClassInstances>>()
    private val registeredClassesLiveData = MutableLiveData<MutableList<ClassInstances>>()

    fun init() {
        teachersLiveData.value = mutableListOf()
        classesLiveData.value = mutableListOf()
        classInstancesLiveData.value = mutableListOf()
        registeredClassesLiveData.value = mutableListOf()

    }

    fun setTeacher(teacher: Teacher): Task<Void> {
        val data = hashMapOf(
            teacher.id to teacher
        )
        return db.collection("allTeachers").document(user?.uid!!).set(data, SetOptions.merge()).addOnSuccessListener {
            teachersLiveData.value?.add(teacher)
            teachersLiveData.value = teachersLiveData.value
        }
    }

    fun deleteTeacher(teacher: Teacher): Task<Void> {
        val updates = hashMapOf<String, Any>(
            teacher.id to FieldValue.delete()
        )
        return db.collection("allTeachers").document(user?.uid!!).update(updates).addOnSuccessListener {
            teachersLiveData.value?.remove(teacher)
            teachersLiveData.value = teachersLiveData.value
        }
    }


    fun getTeachers(): LiveData<MutableList<Teacher>> {

        val teachers = mutableListOf<Teacher>()
        db.collection("allTeachers").document(user?.uid!!).get()
            .addOnSuccessListener { documentSnapshot ->

                for (key in documentSnapshot.data!!.keys) {
                    val firebaseTeacher = documentSnapshot.data!![key] as MutableMap<String, Objects>
                    val teacher = Teacher()
                    for (innerKey in firebaseTeacher.keys) {
                        when (innerKey) {
                            "id" -> teacher.id = firebaseTeacher[innerKey] as String
                            "name" -> teacher.name = firebaseTeacher[innerKey] as String
                            "degree" -> teacher.degree = firebaseTeacher[innerKey] as String?
                            "discipline" -> teacher.discipline = firebaseTeacher[innerKey] as String?
                            "expertise" -> teacher.expertise = firebaseTeacher[innerKey] as String?
                            "birthYear" -> teacher.birthYear = (firebaseTeacher[innerKey] as Long?)?.toInt()
                            "img" -> teacher.img = firebaseTeacher[innerKey] as String?
                            "email" -> teacher.email = firebaseTeacher[innerKey] as String?
                            else -> Log.e("firebase", "extra key value for teacher: $key , $innerKey")
                        }
                    }
                    teachers.add(teacher)
                }
                teachersLiveData.value = teachers
            }
            .addOnFailureListener {
                Log.e("firebase", "Failed to retrieve Teachers")
            }

        return teachersLiveData
    }


    fun setClass(classes: Classes): Task<Void> {
        val data = hashMapOf(
            classes.id to classes
        )
        return db.collection("allClasses").document(user?.uid!!).set(data, SetOptions.merge()).addOnSuccessListener {
            classesLiveData.value?.add(classes)
            classesLiveData.value = classesLiveData.value
        }
    }

    fun getClasses(): LiveData<MutableList<Classes>> {
        val classesSet = mutableListOf<Classes>()
        db.collection("allClasses").document(user?.uid!!).get()
            .addOnSuccessListener { documentSnapshot ->
                for (key in documentSnapshot.data!!.keys) {
                    val firebaseClasses = documentSnapshot.data!![key] as MutableMap<String, Objects>
                    val classes = Classes()
                    for (innerKey in firebaseClasses.keys) {
                        when (innerKey) {
                            "id" -> classes.id = firebaseClasses[innerKey] as String
                            "name" -> classes.name = firebaseClasses[innerKey] as String?
                            "discipline" -> classes.discipline = firebaseClasses[innerKey] as String?
                            "stage" -> classes.stage = firebaseClasses[innerKey] as String?
                            "idealTerm" -> classes.idealTerm = (firebaseClasses[innerKey] as Long?)?.toInt()
                            "units" -> classes.units = (firebaseClasses[innerKey] as Long?)?.toInt()
                            "requirement" -> classes.requirement = firebaseClasses[innerKey] as MutableList<String>?
                            else -> Log.e("firebase", "extra key value for Classes: $key , $innerKey")
                        }
                    }


                    classesSet.add(classes)
                }
                classesLiveData.value = classesSet
            }
            .addOnFailureListener {
                Log.e("firebase", "Failed to retrieve Classes")
            }
        return classesLiveData
    }

    fun deleteClasses(classes: Classes): Task<Void> {
        val updates = hashMapOf<String, Any>(
            classes.id to FieldValue.delete()
        )
        return db.collection("allClasses").document(user?.uid!!).update(updates).addOnSuccessListener {
            classesLiveData.value?.remove(classes)
            classesLiveData.value = classesLiveData.value
        }
    }


    fun setClassInstance(classInstances: ClassInstances): Task<Void> {
        val data = hashMapOf(
            classInstances.id to classInstances
        )
        return db.collection("allClassInstances").document(user?.uid!!).set(data, SetOptions.merge())
            .addOnSuccessListener {
                classInstancesLiveData.value?.add(classInstances)
                classInstancesLiveData.value = classInstancesLiveData.value
            }
    }

    fun getClassInstances(): LiveData<MutableList<ClassInstances>> {
        val classInstancesSet = mutableListOf<ClassInstances>()
        db.collection("allClassInstances").document(user?.uid!!).get()
            .addOnSuccessListener { documentSnapshot ->
                for (key in documentSnapshot.data!!.keys) {
                    val firebaseClassInstances = documentSnapshot.data!![key] as MutableMap<String, Objects>
                    val classesInstance = ClassInstances()
                    var timess: List<MutableMap<String, Objects>>? = null
                    for (innerKey in firebaseClassInstances.keys) {
                        when (innerKey) {
                            "id" -> classesInstance.id = firebaseClassInstances[innerKey] as String
                            "teacher" -> classesInstance.teacher = firebaseClassInstances[innerKey] as String?
                            "classes" -> classesInstance.classes = firebaseClassInstances[innerKey] as String?
                            "timess" -> timess = firebaseClassInstances[innerKey] as List<MutableMap<String, Objects>>?
                            else -> Log.e("firebase", "extra key value for ClassInstances: $key , $innerKey")
                        }
                        val timeList = mutableListOf<Time>()
                        if (timess != null) {
                            for (tim in timess) {
                                val time = Time()
                                for (timeKey in tim.keys) {
                                    when (timeKey) {
                                        "day" -> time.day = tim[timeKey] as Int
                                        "startHour" -> time.startHour = tim[timeKey] as Int
                                        "endHour" -> time.endHour = tim[timeKey] as Int
                                        "week" -> time.week = tim[timeKey] as String
                                    }
                                }
                                timeList.add(time)
                            }
                        }
                        classesInstance.timess = timeList
                    }
                    classInstancesSet.add(classesInstance)
                }
                classInstancesLiveData.value = classInstancesSet
            }
            .addOnFailureListener {
                Log.e("firebase", "Failed to retrieve classInstances")
            }

        return classInstancesLiveData
    }

    fun deleteClassInstance(classInstance: ClassInstances): Task<Void> {

        val updates = hashMapOf<String, Any>(
            classInstance.id to FieldValue.delete()
        )
        return db.collection("allClassInstances").document(user?.uid!!).update(updates).addOnSuccessListener {
            classInstancesLiveData.value?.remove(classInstance)
            classInstancesLiveData.value = classInstancesLiveData.value
        }
    }

    fun registerClass(classInstances: ClassInstances): Task<Void> {
        val data = hashMapOf(
            classInstances.id to classInstances
        )
        return db.collection("userClassInstances").document(user?.uid!!).set(data, SetOptions.merge())
            .addOnSuccessListener {
                registeredClassesLiveData.value?.add(classInstances)
                registeredClassesLiveData.value = registeredClassesLiveData.value
            }
    }

    fun getRegisteredClasses(): LiveData<MutableList<ClassInstances>> {
        val classInstancesSet = mutableListOf<ClassInstances>()
        db.collection("userClassInstances").document(user?.uid!!).get()
            .addOnSuccessListener { documentSnapshot ->
                for (key in documentSnapshot.data!!.keys) {
                    val firebaseClassInstances = documentSnapshot.data!![key] as MutableMap<String, Objects>
                    val classesInstance = ClassInstances()
                    var timess: List<MutableMap<String, Objects>>? = null
                    for (innerKey in firebaseClassInstances.keys) {
                        when (innerKey) {
                            "id" -> classesInstance.id = firebaseClassInstances[innerKey] as String
                            "teacher" -> classesInstance.teacher = firebaseClassInstances[innerKey] as String?
                            "classes" -> classesInstance.classes = firebaseClassInstances[innerKey] as String?
                            "timess" -> timess = firebaseClassInstances[innerKey] as List<MutableMap<String, Objects>>?
                            else -> Log.e("firebase", "extra key value for ClassInstances: $key , $innerKey")
                        }
                        val timeList = mutableListOf<Time>()
                        if (timess != null) {
                            for (tim in timess) {
                                val time = Time()
                                for (timeKey in tim.keys) {
                                    when (timeKey) {
                                        "day" -> time.day = tim[timeKey] as Int
                                        "startHour" -> time.startHour = tim[timeKey] as Int
                                        "endHour" -> time.endHour = tim[timeKey] as Int
                                        "week" -> time.week = tim[timeKey] as String
                                    }
                                }
                                timeList.add(time)
                            }
                        }
                        classesInstance.timess = timeList
                    }
                    classInstancesSet.add(classesInstance)
                }
                registeredClassesLiveData.value = classInstancesSet
            }
            .addOnFailureListener {
                Log.e("firebase", "Failed to retrieve classInstances")
            }

        return classInstancesLiveData
    }

    fun deleteRegisteredClass(classInstance: ClassInstances): Task<Void> {

        val updates = hashMapOf<String, Any>(
            classInstance.id to FieldValue.delete()
        )
        return db.collection("userClassInstances").document(user?.uid!!).update(updates).addOnSuccessListener {
            registeredClassesLiveData.value?.remove(classInstance)
            registeredClassesLiveData.value = registeredClassesLiveData.value
        }
    }


}