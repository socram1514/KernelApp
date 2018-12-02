package com.innovandoapps.library.kernel.acivitys;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;
import dmax.dialog.SpotsDialog;

/**
 * Created by windows 8.1 on 17/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity{

   private AlertDialog dialogProgresIndeterminate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResources());
        initController();
    }

    public void showProgressDialog(String mensaje){
        dialogProgresIndeterminate = new SpotsDialog(BaseActivity.this,mensaje);
        dialogProgresIndeterminate.show();
    }

    public void offProgressDialog(){
        if(dialogProgresIndeterminate != null && dialogProgresIndeterminate.isShowing()){
            dialogProgresIndeterminate.dismiss();
        }
    }

    ///Inicializar Controles
    public abstract void initController();

    public abstract int getLayoutResources();

    /**
     * Muestra un notificacion Toast
     * @param mensaje String a mostrar en el toast
     */
    public void notificacionToast(String mensaje){
        Toast toast = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
