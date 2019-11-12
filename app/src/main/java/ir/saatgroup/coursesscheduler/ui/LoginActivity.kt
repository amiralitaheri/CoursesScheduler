package ir.saatgroup.coursesscheduler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.content.Intent
import android.util.Log
import com.firebase.ui.auth.IdpResponse
import ir.saatgroup.coursesscheduler.data.ConstData
import ir.saatgroup.coursesscheduler.data.auth.providers


class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            runMain()
            Log.i("login","user logged in!!")
        } else {
            firebaseAuth()
        }

    }

    private fun runMain(){
        val i = Intent(this, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
        Log.i("debug","running the main activity")
        this.finish()
    }

    private fun firebaseAuth() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            ConstData.RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ConstData.RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                runMain()
            } else {
                firebaseAuth()
            }
        }
    }
}
