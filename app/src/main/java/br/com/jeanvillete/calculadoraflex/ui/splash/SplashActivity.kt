package br.com.jeanvillete.calculadoraflex.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import br.com.jeanvillete.calculadoraflex.R
import br.com.jeanvillete.calculadoraflex.ui.form.FormActivity
import br.com.jeanvillete.calculadoraflex.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 3500L
    private val USER_PREFERENCES = "user_preferences"
    private val KEY_OPEN_FIRST = "open_first"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var preferences = getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        var isFirstOpen = preferences.getBoolean(KEY_OPEN_FIRST, true)

        if (isFirstOpen) {
            markAppAlreadyOpen(preferences)
            showSplash()
        } else showLogin()
    }

    private fun showSplash() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()

        ivLogo.clearAnimation()
        ivLogo.startAnimation(anim)

        Handler().postDelayed({
            showLogin()
        }, TEMPO_AGUARDO_SPLASHSCREEN)
    }

    private fun markAppAlreadyOpen(preferences: SharedPreferences?) {
            preferences?.edit()
                ?.putBoolean(KEY_OPEN_FIRST, false)
                ?.apply()
    }

    private fun showLogin() {
        val proximaTela = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(proximaTela)
        finish()
    }

}
