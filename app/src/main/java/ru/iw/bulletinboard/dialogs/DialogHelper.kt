package ru.iw.bulletinboard.dialogs

import android.app.AlertDialog
import android.util.Log
import ru.iw.bulletinboard.MainActivity
import ru.iw.bulletinboard.R
import ru.iw.bulletinboard.accountHelper.AccountHelper
import ru.iw.bulletinboard.databinding.SingDialogBinding

class DialogHelper(activity: MainActivity) {
    private val classContext = activity
    private val accountHelper = AccountHelper(classContext)

    fun showSingDialog(indexButtonMenu: Int) {
        val builder = AlertDialog.Builder(classContext)
        val binding = SingDialogBinding.inflate(classContext.layoutInflater)
        val view = binding.root
        builder.setView(view)

        when (indexButtonMenu) {
            DialogConst.SING_UP_STATE -> {
                binding.tvSingTitle.text = classContext.getText(R.string.aсс_sing_up)
                binding.singInButton.text = classContext.getText(R.string.sing_up_action)
            }
            DialogConst.SING_IN_STATE -> {
                binding.tvSingTitle.text = classContext.getText(R.string.aсс_sing_in)
                binding.singInButton.text = classContext.getText(R.string.sing_in_action)
            }
        }
        val dialog = builder.create()
        binding.singInButton.setOnClickListener {
            dialog.dismiss()
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


        dialog.show()
    }


}