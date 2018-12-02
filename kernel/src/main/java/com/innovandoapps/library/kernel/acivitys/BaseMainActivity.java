package com.innovandoapps.library.kernel.acivitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.innovandoapps.library.kernel.R;
import com.innovandoapps.library.kernel.dialogs.DialogAlertSimple;
import com.innovandoapps.library.kernel.dialogs.listener.OnPositiveClickListener;

/**
 * Created by windows 8.1 on 20/11/2017.
 */

public abstract  class BaseMainActivity extends BaseActivity{

    protected Handler handler = new Handler();
    private final int TIME_SLEEP_RETURN = 100;


    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= 23) {
            validarPermisos();
        }else{
            validarServicios();
        }
    }

    public abstract void saltarActivity();

    public abstract String[] getArrPermisos();

    protected void validarPermisos() {
        String[] arr_permissions = getArrPermisos();
        int sw = 0;
        for(String permission : arr_permissions){
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                sw = 1;
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
                    break;
                }else{
                    ActivityCompat.requestPermissions(this,new String[]{permission},100);
                    break;
                }
            }
        }

        if(sw == 0){
            validarServicios();
        }
    }

    public void validarServicios(){
        saltarActivity();
    }

    /**
     * Retorno del request de los permisos
     * Solo si el dispositivo tiene API 23
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        alertaPermisos();
                    }
                }, TIME_SLEEP_RETURN);
            }
        }
    }

    private void alertaPermisos(){
        DialogAlertSimple dialogAlertSimple = new DialogAlertSimple(getString(R.string.dialog_title_permisos),getString(R.string.dialog_msj_permisos));
        dialogAlertSimple.setOnPositiveClickListener(new OnPositiveClickListener() {
            @Override
            public void OnPositiveClick(DialogInterface dialog, String tag) {
                Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(i);
            }
        });
        dialogAlertSimple.show(getSupportFragmentManager(),"");
    }
}
