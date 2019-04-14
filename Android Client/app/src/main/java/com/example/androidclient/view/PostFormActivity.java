package com.example.androidclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidclient.R;
import com.example.androidclient.dao.PostDAO;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;

public class PostFormActivity extends AppCompatActivity {
    private static final String[] ITEMS_CATEGORIA = new String[]{"Java", "Android"};
    private final PostDAO dao = new PostDAO(this);
    private long idPostInsert;
    private Post post;
    private Usuario usuario;
    private LinearLayout llManagePost;
    private LinearLayout llInsertPost;
    private EditText etTitulo;
    private Spinner sCategoria;
    private EditText etResumen;
    private EditText etContenido;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_form);
        
        etTitulo = (EditText) findViewById(R.id.xetPostAddTitulo);
        sCategoria = (Spinner) findViewById(R.id.xsPostAddCategoria);
        etResumen = (EditText) findViewById(R.id.xetPostAddResumen);
        etContenido = (EditText) findViewById(R.id.xetPostAddContenido);
        
        btnInsert = (Button) findViewById(R.id.xbtnPostInsert);
        btnUpdate = (Button) findViewById(R.id.xbtnPostUpdate);
        btnDelete = (Button) findViewById(R.id.xbtnPostDelete);

        llInsertPost = (LinearLayout) findViewById(R.id.xllPostInsert);
        llManagePost = (LinearLayout) findViewById(R.id.xllPostUpdate);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        post = (Post) getIntent().getSerializableExtra("post");

        if(post != null){
            setTextContent(post);
            llInsertPost.setVisibility(View.GONE);
            llManagePost.setVisibility(View.VISIBLE);
        }
        btnInsert.setOnClickListener( insert() );
        sCategoria.setAdapter( new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ITEMS_CATEGORIA) );
        btnUpdate.setOnClickListener( update() );
        btnDelete.setOnClickListener( delete() );
    }

    private View.OnClickListener insert() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post postAux = new Post(
                        etTitulo.getText().toString(),
                        sCategoria.getSelectedItem().toString(),
                        etResumen.getText().toString(),
                        etContenido.getText().toString(),
                        "",
                        usuario
                );
                idPostInsert = dao.create(postAux);
                Toast.makeText(getApplicationContext(),"Agregado correctamente con id "+idPostInsert, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener update() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post postAux = new Post(
                        post.getIdPost(),
                        etTitulo.getText().toString(),
                        sCategoria.getSelectedItem().toString(),
                        etResumen.getText().toString(),
                        etContenido.getText().toString(),
                        "",
                        usuario
                );
                dao.update(postAux);
            }
        };
    }

    private View.OnClickListener delete() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete(post);
            }
        };
    }

    private void setTextContent(Post post){
        etTitulo.setText(post.getTituloPost());
        etResumen.setText(post.getResumenPost());
        etContenido.setText(post.getContenidoPost());
    }
}
