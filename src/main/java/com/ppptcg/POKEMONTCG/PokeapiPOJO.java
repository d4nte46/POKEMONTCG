package com.ppptcg.POKEMONTCG;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PokeapiPOJO {


    private String Api_Key ;

    public PokeapiPOJO(
            @Value("POKEMONTCG.IO.API.KEY") String Api_Key
    ){
        this.Api_Key = Api_Key;

    }

    public String getApi_Key() {
        return Api_Key;
    }

}
