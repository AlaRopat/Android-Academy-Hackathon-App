package com.academy.app.view.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.academy.app.R
import com.academy.app.mvvm.Status.ERROR
import com.academy.app.mvvm.Status.LOADING
import com.academy.app.mvvm.Status.SUCCESS
import com.academy.app.view.MainActivity
import com.academy.app.view.intro.IntroActivity
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_splash.lottie

class SplashActivity : AppCompatActivity() {

    private var isFirstLoading: Boolean = false

    private val viewModel: SplashViewModel by lazy { ViewModelProviders.of(this).get(SplashViewModel::class.java) }
    private val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForFirstLoading()
        setContentView(R.layout.activity_splash)
        lottie.playAnimation()


        viewModel.data.observe(this, Observer {
            when (it.status) {
                SUCCESS -> startActivity(Intent(this, IntroActivity::class.java))
                ERROR -> TODO("error handle")
                LOADING -> TODO("loading handle")
            }
        })
    }

    private fun checkForFirstLoading() {
        isFirstLoading = Prefs.getBoolean("is_first_launch", true)
        Log.d(TAG, "checkForFirstLoading: $isFirstLoading")
        if (isFirstLoading) {
            Prefs.putBoolean("is_first_launch", false)
            Log.d(TAG, "checkForFirstLoading: +++")
            viewModel.doMagic()
        } else {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
//        finish()
    }
}
