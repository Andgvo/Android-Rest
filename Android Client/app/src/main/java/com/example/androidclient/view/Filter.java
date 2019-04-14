package com.example.androidclient.view;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.androidclient.dto.Post;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Post> filterCategoriaPost(List<Post> lista, String categoria ){
        /*return lista.stream()
                .filter( post -> post.getCategoriaPost().equals("Java"))
                .collect(Collectors.toList());*/
        return null;
    }
}
