package jp.suntec.c22002.bmicalculatorc002;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ConfirmDialogFragment extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState){
        //ダイアログビルダーを生成 120
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title);//タイトル
        builder.setMessage(R.string.dialog_msg);//メッセージ
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());//送信

        //ダイアログ生成 120
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {


        }
    }
}
