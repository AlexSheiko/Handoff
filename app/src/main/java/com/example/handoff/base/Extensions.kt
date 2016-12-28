package com.example.handoff.base

import android.widget.EditText
import com.example.handoff.R
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
}
