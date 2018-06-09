package myapps;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class App {
    
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String url;
    private String token;
    
    public App(String url, String token) {
        this.url = url;
        this.token = token;
    }
    
    public static void main( String[] args ) {
        String url = args[0];
        String token = args[1];
        String titel = args[2];
        String tags = args[3];
        String fileName = args[4];

        App app = new App(url, token);
        app.postKnowledge(titel, tags, fileName);
    }
    
    public void postKnowledge(String titel, String tags, String fileName) {
        Knowledge knowledge = new Knowledge(titel);
        
        if (tags.length() != 0) {
            List<String> listTags = Arrays.asList(tags.split(","));
            knowledge.setTags(listTags);
        }
        knowledge.setContent(readTextFileAll(fileName));
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        
        try {
            String json = mapper.writeValueAsString(knowledge);
            //System.out.println(json);
            
            String result = postJson(url + "/api/knowledges", json);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String postJson(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url)
            .header("PRIVATE-TOKEN", token)
            .post(body)
            .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
    public String readTextFileAll(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            return stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
