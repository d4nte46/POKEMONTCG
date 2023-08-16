package com.ppptcg.POKEMONTCG.nonSpringclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


public class imageLoader {

    private String link;

    @Autowired
    private ResourceLoader RL;

    public imageLoader(String link) {
        this.link = link;
    }

    private ResponseEntity<Resource> img;


}
