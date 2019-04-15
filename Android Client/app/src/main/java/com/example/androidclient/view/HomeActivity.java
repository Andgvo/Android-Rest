package com.example.androidclient.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidclient.R;
import com.example.androidclient.dao.PostDAO;
import com.example.androidclient.dto.Post;
import com.example.androidclient.dto.Usuario;
import com.example.androidclient.utilerias.AdapterPost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Usuario usuario;
    private PostDAO dao;
    private List<Post> listaPost;
    private PlaceholderFragment pageHome;
    private PlaceholderFragment pageJava;
    private PlaceholderFragment pageAndroid;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //GetPost Values
        dao = new PostDAO(this);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        listaPost = dao.readAll();
        pageHome = new PlaceholderFragment(  listaPost, usuario);
        pageJava = new PlaceholderFragment(
                filterCategoria(listaPost, "Java"), usuario );
        pageAndroid = new PlaceholderFragment(
                filterCategoria(listaPost, "Android"), usuario );

        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(setListener( PostFormActivity.class ));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        listaPost = dao.readAll();
        List<Post> post1 = new ArrayList<>();
        List<Post> post2 = new ArrayList<>();
        post1.addAll(listaPost);
        post2.addAll(listaPost);

        pageHome.updateAdapter( listaPost);
        pageJava.updateAdapter(
                filterCategoria( post1 , "Java"));
        pageAndroid.updateAdapter(
                filterCategoria( post2, "Android"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent itn = new Intent(HomeActivity.this, UsuarioFormActivity.class);
            itn.putExtra("usuario", usuario);
            startActivity(itn);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener setListener(final Class<?> clase){
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent itn = new Intent(HomeActivity.this, clase);
                itn.putExtra("usuario", usuario);
                startActivity(itn);
            }
        };
    }

    private List<Post> filterCategoria( List<Post> lista, String categoria ){
        List<Post> nuevaLista = new ArrayList<>();
        for(Post post: lista){
            if(post.getCategoriaPost().equals(categoria))
                nuevaLista.add( post );
        }
        return nuevaLista;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    @SuppressLint("ValidFragment")
    public static class PlaceholderFragment extends Fragment {

        public RecyclerView recyclerPost;
        public List<Post> listaPost = new ArrayList<>();
        private Usuario usuarioInternFragment;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment(List<Post> listaPost, Usuario usuarioInternFragment) {
            this.listaPost = listaPost;
            this.usuarioInternFragment = usuarioInternFragment;
        }

        @Override
        public View onCreateView( LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState ) {
            View rootView = inflater.inflate( R.layout.fragment_home, container, false );
            recyclerPost = (RecyclerView) rootView.findViewById( R.id.xrecyclerId );
            recyclerPost.setLayoutManager( new LinearLayoutManager(this.getContext()) );
            Context c = this.getContext();
            AdapterPost adapterPost = new AdapterPost(listaPost);
            adapterPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itn = new Intent(v.getContext(), PostActivity.class);
                    Post postAux = listaPost.get(recyclerPost.getChildAdapterPosition(v));
                    itn.putExtra("post",postAux);
                    itn.putExtra("usuario",usuarioInternFragment);
                    //itn.putExtra("usuario",usuario);
                    startActivity(itn);
                }
            });
            recyclerPost.setAdapter(adapterPost);
            return rootView;
        }

        public void updateAdapter(List<Post> listaPostAux){
            AdapterPost adapterPost = new AdapterPost(listaPostAux);
            this.listaPost = listaPostAux;
            adapterPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itn = new Intent(v.getContext(), PostActivity.class);
                    Post postAux = listaPost.get(recyclerPost.getChildAdapterPosition(v));
                    itn.putExtra("post",postAux);
                    itn.putExtra("usuario",usuarioInternFragment);
                    //itn.putExtra("usuario",usuario);
                    startActivity(itn);
                }
            });
            System.out.println("================ ======== ");
            System.out.println("listaPost : " + listaPostAux);
            recyclerPost.setAdapter(adapterPost);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return pageHome;
                case 1:
                    return pageJava;
                case 2:
                    return pageAndroid;
                default:
                    return pageHome;
            }
        }

        private List<Post> filterCategoria( List<Post> lista, String categoria ){
            List<Post> nuevaLista = new ArrayList<>();
            for(Post post: lista){
                if(post.getCategoriaPost().equals(categoria))
                    nuevaLista.add(post);
            }
            return nuevaLista;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String section = null;
            switch (position) {
                case 0:
                    section = "Home";
                    break;
                case 1:
                    section = "Java";
                    break;
                case 2:
                    section = "Android";
                    break;
            }
            return section;
        }
    }
}
