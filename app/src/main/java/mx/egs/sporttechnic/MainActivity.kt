package mx.egs.sporttechnic

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            startActivity(Intent(this, MenuActiv::class.java))
            finish()
        }
    }

    fun createAccount(view: View) {
        startActivity(Intent(this, CreateAccountActiv::class.java))
    }

    fun signIn(view: View) {
        startActivity(Intent(this, LogInActiv::class.java))
    }

    fun signInAnonymously(view: View){
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MenuActiv::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Falla en autenticación como invitado," +
                            " compruebe su conexión a internet.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
