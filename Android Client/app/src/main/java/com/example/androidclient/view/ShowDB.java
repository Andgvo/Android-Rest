package com.example.androidclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidclient.R;
import com.example.androidclient.dao.ComentarioDAO;
import com.example.androidclient.dao.PostDAO;
import com.example.androidclient.dao.UsuarioDAO;
import com.example.androidclient.dto.Comentario;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;

import java.sql.SQLException;
import java.util.List;

public class ShowDB extends AppCompatActivity {

    private UsuarioDAO usuarioDao;
    private PostDAO postDao;
    private ComentarioDAO comentariosDao;

    TextView tvUsuarios;
    TextView tvPost;
    TextView tvComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_db);
        usuarioDao = new UsuarioDAO( getApplicationContext() );
        postDao = new PostDAO( getApplicationContext() );
        comentariosDao = new ComentarioDAO( getApplicationContext() );
        tvUsuarios = (TextView) findViewById( R.id.xtxShowUsuarios );
        tvPost = (TextView) findViewById( R.id.xtxShowPost );
        tvComentarios = (TextView) findViewById( R.id.xtxShowComentarios );
        try {
            llenarUsuario();
            llenarPost();
            llenarComentario();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void llenarUsuario() throws SQLException {
        List<Usuario> usuarios = usuarioDao.readAll();
        for( Usuario usuario : usuarios  ){
            tvUsuarios.append(usuario.toListAttributes() + "\n\n");
        }
    }

    private void llenarPost() throws SQLException {
        List<Post> posts = postDao.readAll();
        for( Post post : posts  ){
            tvPost.append(post.toListAttributes() + "\n\n");
        }
    }

    private void llenarComentario(){
        List<Comentario> comentarios = comentariosDao.readAll();
        for(Comentario comentario : comentarios){
            tvComentarios.append( comentario.toListAttributes()+"\n\n" );
        }
    }
}
