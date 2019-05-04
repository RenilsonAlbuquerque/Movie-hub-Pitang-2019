package br.pitang.moviehub.api;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.jayway.jsonpath.JsonPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class GoogleKnowledgeGraphSearch {

    private static String API_KEY = "AIzaSyA_qb2p4C6CDurI5_nOcXbgn-nhP_l0uMM";

    public static void main(String[] args) {
        try {


            HttpTransport httpTransport = new NetHttpTransport();
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
            JSONParser parser = new JSONParser();
            GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
            url.put("query", "Jim Carrey");
            url.put("limit", "100");
            url.put("indent", "true");
            url.put("key", API_KEY);
            HttpRequest request = requestFactory.buildGetRequest(url);
            HttpResponse httpResponse = request.execute();
            JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
            JSONArray elements = (JSONArray) response.get("itemListElement");
            for (Object element : elements) {
                System.out.println(JsonPath.read(element, "$.result.name").toString());
                /*
                if(JsonPath.read(element, "$.result.name").toString().contains("height")){

                }
                */


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
