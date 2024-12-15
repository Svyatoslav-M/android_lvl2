package com.smelnykov.homework_1.AuthActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smelnykov.homework_1.Constants.Constants
import com.smelnykov.homework_1.MyProfileActivity.MyProfileActivity
import com.smelnykov.homework_1.databinding.ActivityAuthBinding

/**
 * Class for activity with authentication.
 */
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreference =
            getSharedPreferences(Constants.AUTOLOGIN_PREFERENCE, Context.MODE_PRIVATE)

        if (sharedPreference.contains(Constants.EMAIL_AUTH_KEY)) {
            authLogin()
        }

        setClickListeners()
    }

    /**
     * Method sets up the click listeners for the register button.
     * Checks if the entered email and password are valid,
     * and shows appropriate error messages if they are not.
     */
    private fun setClickListeners() {
        val editTextEmail = binding.editTextTextEmailAddress
        val editTextPassword = binding.editTextTextPassword

        val buttonRegister = binding.buttonRegister

        val emailError = binding.textEmailError
        val passwordError = binding.textPasswordError

        val fieldsChecker = FieldsChecker()

        buttonRegister.setOnClickListener {
            var isAllFieldsCorrect = true

            if (!fieldsChecker.checkEmail(editTextEmail.text.toString())) {
                emailError.visibility = View.VISIBLE
                isAllFieldsCorrect = false
            } else {
                emailError.visibility = View.GONE
            }
            if (!fieldsChecker.checkPassword(editTextPassword.text.toString())) {
                passwordError.visibility = View.VISIBLE
                isAllFieldsCorrect = false
            } else {
                passwordError.visibility = View.GONE
            }

            if (isAllFieldsCorrect) {
                registerAndChangeActivity(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                )
            }
        }
    }

    /**
     * Method for saving email, password and change activity to My Profile Activity.
     * @param email email which will be saved
     * @param password password which will be saved
     * */
    private fun registerAndChangeActivity(email: String, password: String) {
        val editor = sharedPreference.edit()
        editor.putString(Constants.EMAIL_AUTH_KEY, email)
        editor.putString(Constants.PASSWORD_AUTH_KEY, password)
        editor.apply()

        val intent = Intent(this@AuthActivity, MyProfileActivity::class.java)
        startActivity(intent)
    }

    /**
     * Method for autologin redirection to my profile activity.
     * */
    private fun authLogin() {
        val intent = Intent(this@AuthActivity, MyProfileActivity::class.java)
        startActivity(intent)
    }

}