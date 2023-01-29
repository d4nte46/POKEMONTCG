import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

public class ptcg_io_interaction {

//    @Value("${POKEMONTCG.IO.API.KEY}")
    private String api = "4b7cb311-a9f6-4310-b370-21bf559b0c67";
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

    public static void main(String args[]){
        ptcg_io_interaction cl = new ptcg_io_interaction();
        try {
            String hmm = cl.find_set("Lost.Origin");
            System.out.println(hmm);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
