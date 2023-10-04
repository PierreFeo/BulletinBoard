package ru.iw.bulletinboard.dialogs

import android.app.AlertDialog
import ru.iw.bulletinboard.MainActivity
import ru.iw.bulletinboard.R
import ru.iw.bulletinboard.databinding.SingDialogBinding

class DialogHelper(activity: MainActivity) {
    private val classContext = activity

    fun showSingDialog(indexButtonMenu: Int) {
        val builder = AlertDialog.Builder(classContext)
        val binding = SingDialogBinding.inflate(classContext.layoutInflater)
        val view = binding.root

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
        builder.setView(view)
        builder.show()
    }
}