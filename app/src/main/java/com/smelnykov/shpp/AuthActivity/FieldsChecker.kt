package com.smelnykov.shpp.AuthActivity

import android.util.Patterns
import com.smelnykov.shpp.Constants.Constants

/**
 * Object for checking email and passwords fields.
 * */
class FieldsChecker {

    /**
     * Method checks validationpasswords.
     * @param editTextPassword EditText with password
     * @return True or False
     * */
    fun checkPassword(editTextPassword: String): Boolean {
        //Regex for password. Minimum eight characters
        val passwordRegex = Regex(".{${Constants.MIN_PASSWORD_LENGTH},}")
        if (passwordRegex.matches(editTextPassword)) {
            return true
        }
        return false
    }

    /**
     * Method checks validation email.
     * @param editTextEmail EditText with email
     * @return True or False
     * */
    fun checkEmail(editTextEmail: String): Boolean {
        //Pattern for email.
        val emailPattern = Patterns.EMAIL_ADDRESS

        if (emailPattern.matcher(editTextEmail).matches()) {
            return true
        }
        return false
    }
}