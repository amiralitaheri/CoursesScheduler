package ir.saatgroup.coursesscheduler.data.auth

import android.content.Context

import com.firebase.ui.auth.AuthUI




fun signOut(context: Context){
    AuthUI.getInstance()
        .signOut(context)
        .addOnCompleteListener {

        }
}

val providers = arrayListOf(
    AuthUI.IdpConfig.GoogleBuilder().build(),
    AuthUI.IdpConfig.AnonymousBuilder().build())
