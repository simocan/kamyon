package com.blue.kamyonarkasisozler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class KamyonSozEkleActivity extends AppCompatActivity {

    AdView adView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamyon_soz_ekle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest); //adView i yüklüyoruz

        final EditText ekle = (EditText) findViewById(R.id.ekle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(Color.GREEN);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String data=ekle.getText().toString();
                String result;
                if(data!=null && !data.isEmpty()){

                JSONSender sender=new JSONSender();
                try {
                    sender.sendData(ekle.getText().toString());
                    ekle.setText(null);
                    result="Söz Onaylandıktan Sonra Yayınlanacaktır";

                }catch (Exception e){
                    result="Hata ile karşılaşıldı";
                }}else{
                    result="Söz Girişi Yapınız";
                }


                Snackbar.make(view, result, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adView.resume();
    }

}
