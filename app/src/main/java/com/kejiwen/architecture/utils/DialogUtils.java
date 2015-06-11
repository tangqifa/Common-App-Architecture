package com.kejiwen.architecture.utils;

import android.content.Context;
import android.view.View;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by tangqifa on 15/5/15.
 */
public class DialogUtils {

    public static MaterialDialog getNoticeDialog(Context ctx,String title, String msg) {
        final MaterialDialog materialDialog = new MaterialDialog(ctx);
        materialDialog.setTitle(title);
        materialDialog.setMessage(msg);
        materialDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();

            }
        });
        return materialDialog;
    }

}
