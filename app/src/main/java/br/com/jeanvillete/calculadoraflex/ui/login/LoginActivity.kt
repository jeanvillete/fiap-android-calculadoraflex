package br.com.jeanvillete.calculadoraflex.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.jeanvillete.calculadoraflex.R
import br.com.jeanvillete.calculadoraflex.ui.form.FormActivity
import br.com.jeanvillete.calculadoraflex.ui.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val new_User_Request_Code = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        mAuth.currentUser?.reload()
        if (mAuth.currentUser != null) {
            goToHome()
        }

        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                inputLoginEmail.text.toString(),
                inputLoginPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToHome()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Não foi possível autenticar o usuário. Senha pode estar errada ou usuário pode não existir ou ainda ter sido removido",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        btSignup.setOnClickListener {
            var criarConta = Intent(this, SignUpActivity::class.java)

            startActivityForResult(
                criarConta,
                new_User_Request_Code
            )
        }
    }

    private fun goToHome() {
        val intent = Intent(this, FormActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            new_User_Request_Code -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        inputLoginEmail.setText(data?.getStringExtra("email"))
                    }
                }
            }
        }

//        if (requestCode == new_User_Request_Code && resultCode == Activity.RESULT_OK) {
//            inputLoginEmail.setText(data?.getStringExtra("email"))
//        }
    }
}
