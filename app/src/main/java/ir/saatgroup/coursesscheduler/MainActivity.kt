package ir.saatgroup.coursesscheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.content.Intent
import android.view.View
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import ir.saatgroup.coursesscheduler.auth.signOut
import ir.saatgroup.coursesscheduler.auth.providers


class MainActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 77

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            signOut(this)
        } else {
            firebaseAuth()
        }

    }

    private fun firebaseAuth() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                val snack = Snackbar.make(findViewById<View>(R.id.rootLayout),"Logged In",Snackbar.LENGTH_LONG)
                snack.show()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
