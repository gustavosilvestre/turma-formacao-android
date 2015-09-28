package br.com.cast.turmaformacao.taskmanager.model.http;

import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.net.HttpURLConnection;

import br.com.cast.turmaformacao.taskmanager.model.entities.Address;

/**
 * Created by Gustavo on 23/09/2015.
 */
public final class AddressService {

    private final static String URL = "http://correiosapi.apphb.com/cep/";

    private AddressService(){
        super();
    }


    public static Address getAdressByZipCode(String zipCode){

        Address address = null;

        try {
            java.net.URL url = new java.net.URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();

            address = objectMapper.readValue(inputStream, Address.class);

            conn.disconnect();

        } catch (Exception e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }


        return address;

    }
}