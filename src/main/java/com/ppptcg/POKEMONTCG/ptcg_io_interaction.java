package com.ppptcg.POKEMONTCG;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
public class ptcg_io_interaction {

    public void setApi(String api) {
        this.api = api;
    }

    private String api;

    public ptcg_io_interaction(String api){
        this.setApi(api);
    }
//    private String api = "4b7cb311-a9f6-4310-b370-21bf559b0c67";
    String get_card(String card) throws UnirestException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{card}")
                .routeParam("card",card)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/rarity");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(co);
    }

    String find_set(String set) throws UnirestException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNSET= Unirest.get("https://api.pokemontcg.io/v2/sets?q=name:{set}")
                .routeParam("set",set)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNSET));
        JsonNode co = node.at("/data");
        List<JsonNode> list = co.findValues("id");
        return list.toString();
    }

    String gotta_get_all_sets() throws UnirestException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNSETS = Unirest.get("https://api.pokemontcg.io/v2/sets")
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNSETS));
        JsonNode co = node.at("/data");
        List<JsonNode> namelist = co.findValues("name");
        List<JsonNode> idlist = co.findValues("id");

        HashMap<JsonNode,JsonNode> nameid = new HashMap<>();

        for (int i = 0 ; i<idlist.size() ; i++ ){
            nameid.put(namelist.get(i), idlist.get(i));
        }


        return nameid.toString();
    }

}
