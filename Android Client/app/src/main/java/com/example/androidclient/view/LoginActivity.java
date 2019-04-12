package com.example.androidclient.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidclient.R;
import com.example.androidclient.dto.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = (EditText) findViewById(R.id.xetUsuario);
        txtPassword = (EditText) findViewById(R.id.xetPassword);
        btnLogin = (Button) findViewById(R.id.xbnLogin);
        //btnLogin.setOnClickListener( setListener( HomeActivity.class ) );
    }

    private View.OnClickListener setListener(final Class<?> clase){
        return new View.OnClickListener() {
            public void onClick(View v) {
                String nombreUsuario = txtUsuario.getText().toString();
                String passwordUsuario = txtUsuario.getText().toString();
                Usuario usuario = new Usuario(nombreUsuario, passwordUsuario);
                Intent itn = new Intent(LoginActivity.this, clase);
                itn.putExtra("nombreUsuario", nombreUsuario);
                itn.putExtra("passwordUsuario", passwordUsuario);
                startActivity(itn);
            }
        };
    }
}
