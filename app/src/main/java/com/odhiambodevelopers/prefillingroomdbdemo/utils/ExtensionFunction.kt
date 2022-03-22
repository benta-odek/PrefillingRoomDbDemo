package com.odhiambodevelopers.prefillingroomdbdemo.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.hideKeyboard(): Boolean {
    return (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
}

fun Fragment.showSnackbar(text: String) {
    Snackbar.make(
        requireView(),
        text,
        Snackbar.LENGTH_LONG
    ).show()
}