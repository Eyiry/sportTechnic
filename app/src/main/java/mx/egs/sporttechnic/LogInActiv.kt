package mx.egs.sporttechnic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in_1.*

class LogInActiv : AppCompatActivity() {

    private var mail = ""
    private var password = ""
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_1)

        auth = FirebaseAuth.getInstance()
    }

    fun logIn(view: View){
        mail = etMailLogIn.text.toString()
        password = etPasswordLogIn.text.toString()

        if (mail != "" && password != ""){
            logInFirebase()
        }else{
            Toast.makeText(this, "Verifica que el correo y la contraseña no estén vacíos", Toast.LENGTH_SHORT).show()
        }
    }

    fun changePasword(view: View){
        val mail = auth.currentUser?.email
        if (mail != null) {
            auth.sendPasswordResetEmail(mail)
            Toast.makeText(this, "Se ha enviado un correo a $mail para restablecer la contraseña", Toast.LENGTH_LONG).show()
        }
    }

    private fun logInFirebase() {
        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(this){task->
            if(task.isSuccessful){
                val intent = Intent(this, MenuActiv::class.java)
                intent.putExtra("INICIO", "true")
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Verifica que el correo y la contraseña estén correctos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
