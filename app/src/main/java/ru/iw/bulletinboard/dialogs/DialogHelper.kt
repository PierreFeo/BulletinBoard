package ru.iw.bulletinboard.dialogs

import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import ru.iw.bulletinboard.MainActivity
import ru.iw.bulletinboard.R
import ru.iw.bulletinboard.accountHelper.AccountHelper
import ru.iw.bulletinboard.databinding.SingDialogBinding

class DialogHelper(activity: MainActivity) {
    private val myActivity = activity
    private val accountHelper = AccountHelper(myActivity)

    fun showSingDialog(indexButtonMenu: Int) {
        val builder = AlertDialog.Builder(myActivity)
        val binding = SingDialogBinding.inflate(myActivity.layoutInflater)
        val view = binding.root
        builder.setView(view)

        setDialogState(indexButtonMenu, binding)

        val dialog = builder.create()
        binding.singInButton.setOnClickListener {
            setOnClickSignUpIn(indexButtonMenu, binding, dialog)
        }
        binding.btForgetP.setOnClickListener {
            setOnClickResetPassword(binding, dialog)
        }
        dialog.show()
    }

    private fun setOnClickResetPassword(binding: SingDialogBinding, dialog: AlertDialog?) {
        val userEmail = binding.enSingEmail.text
        binding.errEmptyEmail.visibility = View.VISIBLE
        binding.enSingPassword.visibility = View.GONE
        binding.singInButton.visibility = View.GONE
        binding.btForgetP.text = myActivity.resources.getString(R.string.send_button)

        binding.btForgetP.setOnClickListener {
            if (userEmail.isEmpty()) {
                binding.btForgetP.error =
                    myActivity.resources.getString(R.string.do_not_empty_email)
                binding.errEmptyEmail.text =
                    myActivity.resources.getString(R.string.do_not_empty_email)


            } else {
                myActivity.fireBaseAuth.sendPasswordResetEmail(userEmail.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) makeToast(myActivity.resources.getString(R.string.email_reset_pass_was_sent))

                    }
                dialog?.dismiss()
            }


        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(myActivity, text, Toast.LENGTH_LONG).show()
    }

    private fun setOnClickSignUpIn(
        indexButtonMenu: Int,
        binding: SingDialogBinding,
        dialog: AlertDialog?
    ) {
        dialog?.dismiss()
        if (indexButtonMenu == DialogConst.SING_UP_STATE) {
            //Sing up
            accountHelper.singUpWithEmail(
                binding.enSingEmail.text.toString().trim(),
                binding.enSingPassword.text.toString().trim()
            )
        } else {
            //Sing in
            accountHelper.singInWithEmail(
                binding.enSingEmail.text.toString().trim(),
                binding.enSingPassword.text.toString().trim()
            )
            Log.d("error auth", "${binding.enSingEmail}, ${binding.enSingPassword}")
        }
    }

    private fun setDialogState(indexButtonMenu: Int, binding: SingDialogBinding) {
        when (indexButtonMenu) {
            DialogConst.SING_UP_STATE -> {
                binding.tvSingTitle.text = myActivity.getText(R.string.aсс_sing_up)
                binding.singInButton.text = myActivity.getText(R.string.sing_up_action)
            }
            DialogConst.SING_IN_STATE -> {
                binding.tvSingTitle.text = myActivity.getText(R.string.aсс_sing_in)
                binding.singInButton.text = myActivity.getText(R.string.sing_in_action)
                binding.btForgetP.visibility = View.VISIBLE
            }
        }
    }


}