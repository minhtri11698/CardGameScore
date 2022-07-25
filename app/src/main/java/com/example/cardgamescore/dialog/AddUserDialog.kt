package com.example.cardgamescore.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.cardgamescore.R
import com.example.cardgamescore.base.BaseDialogFragment
import com.example.cardgamescore.databinding.AddUserDialogBinding
import com.example.cardgamescore.model.Player
import com.example.cardgamescore.ulti.setOnClick

class AddUserDialog: BaseDialogFragment<AddUserDialogBinding>() {
    override fun getLayoutId(): Int = R.layout.add_user_dialog

    private var confirmClicked = false

    var onConfirm: ((Player) -> (Unit))? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playerNameInput.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank() && confirmClicked) {
                binding.warningName.visibility = View.GONE
            }
        }
        binding.cancelButton.setOnClick {
            dismiss()
        }
        binding.confirmButton.setOnClick {
            val name = binding.playerNameInput.text.toString()
            confirmClicked = true
            if (name.isBlank()) {
                binding.warningName.visibility = View.VISIBLE
            } else {
                val newPlayer = Player(0, name, 0, false)
                onConfirm?.invoke(newPlayer)
                dismiss()
            }
        }
    }

    companion object {
        private const val BUNDLE_PLAYER_NAME = "BUNDLE_PLAYER_NAME"
        private const val TAG = "AddPlayerDialog"

        fun show(fm: FragmentManager): AddUserDialog {
            val fragment = newInstance()
            fragment.show(fm, TAG)
            return fragment
        }

        fun dismiss(fm: FragmentManager) {
            val fragment = fm.findFragmentByTag(TAG)
            if (fragment is AddUserDialog) {
                fragment.dismiss()
            }
        }

        private fun newInstance(): AddUserDialog {
            val bundle = Bundle()
            val fragment = AddUserDialog()
            fragment.arguments = bundle
            return fragment
        }
    }
}