package ru.iw.bulletinboard.accountHelper

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import ru.iw.bulletinboard.MainActivity
import ru.iw.bulletinboard.R

class AccountHelper(act: MainActivity) {
    private val act = act

    fun singUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.fireBaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        sendEmailVerification(task.result?.user!!)
                        task.result.user?.let { act.uiUpdate(it) }

                        //  act.uiUpdate(task.result.user)
                    } else {
                        Toast.makeText(
                            act, act.resources.getString(
                                R.string.sing_up_error
                            ), Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }


    fun singInWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.fireBaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        act.uiUpdate(task.result?.user)
                        task.result.user?.let { act.uiUpdate(it) }

                    } else {
                        task.exception
                        Toast.makeText(
                            act, act.resources.getString(
                                R.string.sing_in_error
                            ), Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    act, act.resources.getString(
                        R.string.send_verification_email_done
                    ), Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    act, act.resources.getString(
                        R.string.send_verification_email_error
                    ), Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}