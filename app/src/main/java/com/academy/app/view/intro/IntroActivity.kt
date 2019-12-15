package com.academy.app.view.intro

import android.content.Intent
import android.os.Bundle
import com.academy.app.view.MainActivity
import com.academy.app.view.intro.money.MoneyFragment
import com.academy.app.view.intro.signup.GoogleSignInFragment
import com.github.paolorotolo.appintro.AppIntro

class IntroActivity : AppIntro() {

    private var isFirstLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupIntro()
    }

    private fun setupIntro() {
        showSkipButton(false)
        addSlides()
    }

    private fun addSlides() {
        addSlide(GoogleSignInFragment.newInstance())
        addSlide(MoneyFragment.newInstance())
    }

    interface OnNextClickListener {
        fun onClickNext(activity: IntroActivity)
    }

    override fun onDonePressed() {
        super.onDonePressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
//        finish()
    }
}
