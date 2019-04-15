package com.example.androidclient.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidclient.MainActivity;
import com.example.androidclient.R;
import com.example.androidclient.dao.UsuarioDAO;
import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.URL;

public class UsuarioFormActivity extends AppCompatActivity {
    private final UsuarioDAO dao = new UsuarioDAO(this);
    private Usuario usuario;
    private LinearLayout llManageUsuario;
    private LinearLayout llInsertUsuario;
    private EditText etNombre;
    private EditText etApelldio;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_form);
        etNombre = (EditText) findViewById(R.id.xetUsuarioAddNombre);
        etApelldio = (EditText) findViewById(R.id.xetUsuarioAddApellido);
        etEmail = (EditText) findViewById(R.id.xetUsuarioAddEmail);
        etPassword = (EditText) findViewById(R.id.xetUsuarioAddPassword);
        btnInsert = (Button) findViewById(R.id.xbnUsarioInsert);
        btnUpdate = (Button) findViewById(R.id.xbtnUsarioUpdate);
        btnDelete = (Button) findViewById(R.id.xbtnUsarioDelete);

        llInsertUsuario = (LinearLayout) findViewById(R.id.xllUsuarioInsert);
        llManageUsuario = (LinearLayout) findViewById(R.id.xllUsuarioUpdate);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        if(usuario != null){
            setTextContent(usuario);
            llInsertUsuario.setVisibility(View.GONE);
            llManageUsuario.setVisibility(View.VISIBLE);
        }
        btnInsert.setOnClickListener( insert() );
        btnUpdate.setOnClickListener( update() );
        btnDelete.setOnClickListener( delete() );
    }

    private View.OnClickListener insert() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioAux = new Usuario(
                    etNombre.getText().toString(),
                    etApelldio.getText().toString(),
                    etEmail.getText().toString(),
                    etPassword.getText().toString()
                );
                dao.create(usuarioAux);
                goToWebView(URL.goServletUsuario( "INSERT",usuarioAux));
            }
        };
    }

    private View.OnClickListener update() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioAux = new Usuario(
                        usuario.getIdUsuario(),
                        etNombre.getText().toString(),
                        etApelldio.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                );
                dao.update(usuarioAux);
            }
        };
    }

    private View.OnClickListener delete() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete(usuario);
            }
        };
    }

    private void setTextContent(Usuario usuario){
        etNombre.setText(usuario.getNombreUsuario());;
        etApelldio.setText(usuario.getApellidoUsuario());;
        etEmail.setText(usuario.getEmailUsuario());;
        etPassword.setText(usuario.getPasswordUsuario());;
    }

    private boolean goToWebView(final String url){
        Intent itn = new Intent(getApplicationContext(), WebViewActivity.class);
        itn.putExtra("urlServidor", url);
        startActivity(itn);
        return  true;
    }
}
