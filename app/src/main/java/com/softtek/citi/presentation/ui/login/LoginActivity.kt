package com.softtek.citi.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.softtek.citi.R
import com.softtek.citi.domain.models.requestObjects.AuthRequestObject
import com.softtek.citi.presentation.ui.home.HomeActivity
import com.softtek.citi.presentation.ui.home.HomeCitiActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.initButtons()
        this.initObserveViewModel()
    }

    private fun initButtons() {
        this.btnLogin.setOnClickListener {
            this.loginViewModel.auth(
                AuthRequestObject(
                    this.etEmail.text.toString(),
                    this.etPassword.text.toString()
                )
            )
        }
    }

    private fun initObserveViewModel() {
        this.loginViewModel.loginViewModelState.observe(this, Observer { state ->
            when (state) {
                is LoginViewModelState.LoginSucces -> {
                    this.nextActivityHome()
                }
                is LoginViewModelState.ProgressVisibility -> {
                    this.loading.visibility = state.visibility
                    this.btnLogin.visibility =
                        if (state.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
                }
                is LoginViewModelState.EmailErrorState -> {
                    this.etEmail.error = getString(state.error)
                }

                is LoginViewModelState.PasswordErrorState -> {
                    this.etPassword.error = getString(state.error)
                }
                is LoginViewModelState.ErrorState -> {
                    this.contentLogin?.let {
                        Snackbar.make(it, state.error, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun nextActivityHome() {
        val intent = Intent(this, HomeCitiActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

}