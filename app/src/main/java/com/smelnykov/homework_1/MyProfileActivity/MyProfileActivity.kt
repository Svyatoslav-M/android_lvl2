package com.smelnykov.homework_1.MyProfileActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.smelnykov.homework_1.AuthActivity.AuthActivity
import com.smelnykov.homework_1.AuthActivity.FieldsChecker
import com.smelnykov.homework_1.Constants.Constants
import com.smelnykov.homework_1.databinding.ActivityMyProfileBinding

/**
 * Class for my profile activity.
 */
class MyProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyProfileBinding
    private lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreference =
            getSharedPreferences(Constants.AUTOLOGIN_PREFERENCE, Context.MODE_PRIVATE)
        val email = sharedPreference.getString(Constants.EMAIL_AUTH_KEY, null)
        if (email != null) {
            setNameFromEmail(email)
        }

        setClickListeners()
    }

    /**
     * Method for setup name from email at My Profile Activity.
     * @param email email which will be parse to name and surname
     * */
    private fun setNameFromEmail(email: String) {
        val parser = NameParser()
        val name = binding.appCompatTextName
        name.text = parser.parseNameSurname(email)
    }

    /**
     * Method sets up the click listeners for log out button.
     */
    private fun setClickListeners() {

        val buttonLogOut = binding.appCompatButtonLogOut

        buttonLogOut.setOnClickListener {
            clearData()
            logOutRedirection()
        }
    }

    /**
     * Method clears users data in sharedPreference.
     * */
    private fun clearData() {
        val editor = sharedPreference.edit()
        editor.clear()
        editor.apply()
    }

    /**
     * Method for changing activity to Authorization activity.
     * */
    private fun logOutRedirection() {
        val intent = Intent(this@MyProfileActivity, AuthActivity::class.java)
        startActivity(intent)
    }
}