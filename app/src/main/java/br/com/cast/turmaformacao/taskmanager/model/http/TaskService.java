package br.com.cast.turmaformacao.taskmanager.model.http;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.services.TaskBusinessServices;

/**
 * Created by Administrador on 24/09/2015.
 */
public class TaskService {

    private static final String URL = "http://10.11.21.193:3000/api/v1/task/";

    private TaskService() {
        super();
    }

    public static Task getTaskByWebId(Long webId) {

        Task task = null;

        try {

            java.net.URL url = new java.net.URL(URL + webId);

            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                task = objectMapper.readValue(inputStream, Task.class);

                conn.disconnect();
            }

        } catch (Exception e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }

        return task;
    }


    public static List<Task> getAll() {

        List<Task> lista = new ArrayList<>();

        try {

            java.net.URL url = new java.net.URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            int responseCod = conn.getResponseCode();

            if (responseCod == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();

                lista = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Task.class));

                conn.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return lista;

    }

    public static void save(Task task){

        try {

            java.net.URL url = new java.net.URL(URL+task.getId());
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = conn.getOutputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(task);

            outputStream.write(objectMapper.writeValueAsBytes(task));
            outputStream.flush();
            outputStream.close();
            conn.disconnect();


        } catch (Exception e) {
            e.printStackTrace();

        }



    }

}
