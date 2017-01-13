package com.example.handoff.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.widget.EditText
import com.example.handoff.R
import com.example.handoff.data.model.Token
import com.example.handoff.data.model.TokenRequest
import com.example.handoff.data.model.User
import com.example.handoff.util.Constants.BEARER
import com.example.handoff.util.Constants.KEY_TOKEN
import com.example.handoff.util.Constants.PUBLIC_GRANT
import com.example.handoff.util.Constants.USER_CLIENT
import com.example.handoff.util.Constants.USER_SECRET
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface Extensions {

    fun <T> Call<T>.enqueue(success: (response: Response<T>) -> Unit,
                            failure: (t: Throwable) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                success(response)
            }

            override fun onFailure(call: Call<T>?, t: Throwable) {
                failure(t)
            }
        })
    }

    fun valid(vararg fields: EditText): Boolean {
        var valid = true
        fields.forEach {
            if (it.text.isBlank()) {
                valid = false
                showRequired(it)
            }
        }
        return valid
    }

    fun showRequired(field: EditText) {
        field.error = field.context.getString(R.string.error_required)
        field.requestFocus()
    }

    fun Context.getPrefs(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun Activity.saveToken(token: Token) {
        val json = Gson().toJson(token)
        getPrefs().edit().putString(KEY_TOKEN, json).apply()
    }

    fun Activity.getToken(): Token {
        val json = getPrefs().getString(KEY_TOKEN, "")
        return Gson().fromJson(json, Token::class.java)
    }

    fun requestFor(user: User): TokenRequest {
        return TokenRequest(
                USER_CLIENT, USER_SECRET, PUBLIC_GRANT,
                user.email, user.password)
    }

    fun Context.dpToPixels(dp: Int): Int {
        return applyDimension(COMPLEX_UNIT_DIP, dp.toFloat(),
                resources.displayMetrics).toInt()
    }

    fun bearer(token: Token): String {
        return BEARER + token.access_token
    }
}