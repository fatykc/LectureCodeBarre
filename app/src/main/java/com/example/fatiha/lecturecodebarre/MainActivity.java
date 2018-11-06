package com.example.fatiha.lecturecodebarre;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button scan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        scan_btn = (Button)findViewById( R.id.scan_btn );
        final Activity activity= this;

        // utilisation de la librairie ZXING
        scan_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator( activity );
                integrator.setDesiredBarcodeFormats( IntentIntegrator.ALL_CODE_TYPES );
                integrator.setPrompt("Scan");
                integrator.setCameraId( 0 );
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        } );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult( requestCode,resultCode,data);

         if (result !=null){
             if (result.getContents()==null){
                 Toast.makeText( this,"Vous avez annulé le Scan ",Toast.LENGTH_LONG ).show();
             }
             else {
                 Toast.makeText( this, result.getContents(),Toast.LENGTH_LONG ).show();

             }
         }
         else {
             super.onActivityResult( requestCode, resultCode, data );
         }



    }


}
