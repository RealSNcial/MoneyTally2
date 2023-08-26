package com.yu.moneytally


import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

class SavingsWalletDialog(
    context: Context,
    private val listener: OnAddButtonListener? = null
): AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_savings_wallet_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        setCanceledOnTouchOutside(false)

        val dialogClose: ImageView = findViewById(R.id.walletDialogCloseImageView)
        val addAmountTextBox: EditText = findViewById(R.id.walletAddAmountTextBox)
        val addAmountButton: AppCompatButton = findViewById(R.id.walletAddAmountButton)

        dialogClose.setOnClickListener {
            dismiss()
        }

        addAmountButton.setOnClickListener {
            if (addAmountTextBox.text.isNotEmpty()) {
                listener?.onAddAmountWallet(addAmountTextBox.text.toString())
                dismiss()
            } else {
                show()
            }
        }
    }
}
