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
import com.example.androidclient.dao.ComentarioDAO;
import com.example.androidclient.dto.Comentario;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;

public class ComentarioFormActivity extends AppCompatActivity {

    private final ComentarioDAO dao = new ComentarioDAO(this);
    private long idComentarioInsert;
    private Usuario usuario;
    private Post post;
    private Comentario comentario;
    private LinearLayout llManageComentario;
    private LinearLayout llInsertComentario;
    private EditText etContenido;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_form);
        etContenido = (EditText) findViewById(R.id.xetComentarioAddContenido);

        btnInsert = (Button) findViewById(R.id.xbtnComentarioInsert);
        btnUpdate = (Button) findViewById(R.id.xbtnComentarioUpdate);
        btnDelete = (Button) findViewById(R.id.xbtnComentarioDelete);

        llInsertComentario = (LinearLayout) findViewById(R.id.xllComentarioInsert);
        llManageComentario = (LinearLayout) findViewById(R.id.xllComentarioUpdate);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        post = (Post) getIntent().getSerializableExtra("post");
        comentario = (Comentario) getIntent().getSerializableExtra("comentario");

        if (comentario != null) {
            setTextContent(comentario);
            llInsertComentario.setVisibility(View.GONE);
            llManageComentario.setVisibility(View.VISIBLE);
        }
        
        btnInsert.setOnClickListener(insert());
        btnUpdate.setOnClickListener(update());
        btnDelete.setOnClickListener(delete());
    }

    private View.OnClickListener insert() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comentario ComentarioAux = new Comentario(
                        etContenido.getText().toString(),
                        usuario,
                        post
                );
                idComentarioInsert = dao.create(ComentarioAux);
                Toast.makeText(getApplicationContext(), "Agregado correctamente con id " + idComentarioInsert, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private View.OnClickListener update() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comentario comentarioAux = new Comentario(
                        comentario.getIdComentario(),
                        etContenido.getText().toString(),
                        usuario,
                        post
                );
                dao.update(comentarioAux);
                finish();
            }
        };
    }

    private View.OnClickListener delete() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete(comentario);
            }
        };
    }

    private void setTextContent(Comentario comentario) {
        etContenido.setText(comentario.getContenidoComentario());
    }
}
