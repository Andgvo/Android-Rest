package com.example.androidclientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidclientapp.view.LoginActivity;
import com.example.androidclientapp.view.WebViewActivity;

public class MainActivity extends AppCompatActivity {
    Button btnIrWebView;
    Button btnIrApp;
    EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnIrWebView.setOnClickListener( setListener( WebViewActivity.class ) );
        //btnIrApp.setOnClickListener( setListener( LoginActivity.class ) );
    }

    private View.OnClickListener setListener(final Class<?> clase){
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent itn = new Intent(MainActivity.this, clase);
                itn.putExtra("urlServidor",etUrl.getText().toString());
                startActivity(itn);
            }
        };
    }
}
