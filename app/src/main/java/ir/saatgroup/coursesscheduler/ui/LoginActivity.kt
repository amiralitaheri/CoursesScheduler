package ir.saatgroup.coursesscheduler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.content.Intent
import android.util.Log
import com.firebase.ui.auth.IdpResponse
import ir.saatgroup.coursesscheduler.data.auth.providers


class LoginActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 77

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
            RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                runMain()
                // Successfully signed in
                //val user = FirebaseAuth.getInstance().currentUser
               // val snack = Snackbar.make(findViewById<View>(R.id.rootLayout),"Logged In",Snackbar.LENGTH_LONG)
              //  snack.show()
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
