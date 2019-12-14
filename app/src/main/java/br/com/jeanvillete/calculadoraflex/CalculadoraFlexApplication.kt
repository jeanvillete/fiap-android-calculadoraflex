package br.com.jeanvillete.calculadoraflex

import android.app.Application
import com.facebook.stetho.Stetho


class CalculadoraFlexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}