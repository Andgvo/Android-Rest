package com.example.androidclient.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidclient.R;
import com.example.androidclient.dao.UsuarioDAO;
import com.example.androidclient.dto.Usuario;

public class LoginActivity extends AppCompatActivity {
    private final UsuarioDAO dao = new UsuarioDAO(this);
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnLogin;
    private Button btnSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = (EditText) findViewById(R.id.xetUsuario);
        txtPassword = (EditText) findViewById(R.id.xetPassword);
        btnLogin = (Button) findViewById(R.id.xbnLogin);
        btnSignin = (Button) findViewById(R.id.xbnSignin);
        btnLogin.setOnClickListener( setListenerLogin( HomeActivity.class ) );
        btnSignin.setOnClickListener( setListener( UsuarioFormActivity.class ) );
    }

    private View.OnClickListener setListenerLogin(final Class<?> clase){
        return new View.OnClickListener() {
            public void onClick(View v) {
                Usuario usuario = new Usuario(
                        txtUsuario.getText().toString(),
                        txtPassword.getText().toString()
                );
                usuario = dao.login(usuario);
                if( usuario != null ){
                    Intent itn = new Intent(LoginActivity.this, clase);
                    itn.putExtra("usuario",  usuario);
                    Toast.makeText(getApplicationContext(),"Bienvenido  "+usuario.getNombreUsuario(), Toast.LENGTH_SHORT).show();
                    startActivity(itn);
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR: Revisar usuario y/o password", Toast.LENGTH_SHORT).show();
                }

            }
        };
    }

    private View.OnClickListener setListener(final Class<?> clase){
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent itn = new Intent(LoginActivity.this, clase);
                startActivity(itn);
            }
        };
    }

}
