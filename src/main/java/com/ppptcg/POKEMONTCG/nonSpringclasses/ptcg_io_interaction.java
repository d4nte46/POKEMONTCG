package com.ppptcg.POKEMONTCG.nonSpringclasses;

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
//    String get_card(String card) throws UnirestException, JsonProcessingException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{card}")
//                .routeParam("card",card)
//                .queryString("x-Api-Key",api)
//                .asJson()
//                .getBody()
//                .getObject();
//
//        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
//        JsonNode co = node.at("/data/rarity");
//
//        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(co);
//    }
//
//    String find_set(String set) throws UnirestException, JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject PKMNSET= Unirest.get("https://api.pokemontcg.io/v2/sets?q=name:{set}")
//                .routeParam("set",set)
//                .queryString("x-Api-Key",api)
//                .asJson()
//                .getBody()
//                .getObject();
//
//        JsonNode node = objectMapper.readTree(String.valueOf(PKMNSET));
//        JsonNode co = node.at("/data");
//        List<JsonNode> list = co.findValues("id");
//        return list.toString();
//    }

    public HashMap<String, String> gotta_get_all_sets() throws UnirestException, JsonProcessingException {
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

        HashMap<String,String> nameid = new HashMap<>();

        for (int i = 0 ; i<idlist.size() ; i++ ){
            nameid.put(String.valueOf(namelist.get(i)).replace("\"",""), String.valueOf(idlist.get(i)).replace("\"",""));
        }



        return nameid;
    }

    public String[] gotta_get_all_set_names() throws UnirestException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNSETS = Unirest.get("https://api.pokemontcg.io/v2/sets")
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNSETS));
        JsonNode co = node.at("/data");
        List<JsonNode> namelist = co.findValues("name");
        String[] names = new String[namelist.size()];

        for (int i = 0 ; i < namelist.size(); i++){
            names[i] = (String.valueOf(namelist.get(i)).replace("\"",""));
        }

        return names;
    }

    public String get_card_img(String id) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/images/large");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(co);

    }

    public String getArtist(short id, String setNameId) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",setNameId+"-"+id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/artist");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(co).replace("\"" , "");
    }

    public String getSuperType(short id, String setNameId) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",setNameId+"-"+id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/supertype");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(co).replace("\"" , "");

    }

    public String[] getNationalDexID(short id, String setNameId) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",setNameId+"-"+id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/nationalPokedexNumbers");


        String[] names = objectMapper.treeToValue(co, String[].class);

        return names;

    }

    public String[] getSubTypes(short id,String setNameId) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",setNameId+"-"+id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/subtypes");


        String[] names = objectMapper.treeToValue(co, String[].class);

        return names;
    }


    public String[] getTypes(short id,String setNameId) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",setNameId+"-"+id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/types");


        String[] names = objectMapper.treeToValue(co, String[].class);

        return names;
    }

    public String getRarity(short id, String setNameId) throws UnirestException,JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject PKMNCard = Unirest.get("https://api.pokemontcg.io/v2/cards/{id}")
                .routeParam("id",setNameId+"-"+id)
                .queryString("x-Api-Key",api)
                .asJson()
                .getBody()
                .getObject();

        JsonNode node = objectMapper.readTree(String.valueOf(PKMNCard));
        JsonNode co = node.at("/data/rarity");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(co).replace("\"" , "");

    }
}
