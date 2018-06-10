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
import com.fasterxml.jackson.core.type.TypeReference;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KnowledgeApiCl {
    
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String baseUrl;
    private String token;
    
    public KnowledgeApiCl(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }
    
    public void postKnowledge(String title, String tags, String fileName) {
        Knowledge knowledge = new Knowledge(title);
        
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
            
            String result = postJson(baseUrl + "/api/knowledges", json);
            System.out.println("POST : " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteKnowledges(String title, String tags) {

        List<String> listTags = new ArrayList<String>();
        if (tags != null) {
            if (tags.length() != 0) {
                listTags.addAll(Arrays.asList(tags.split(",")));
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            // 一覧取得
            // API上はoffsetとlimitパラメタがある。
            // limitは最大50
            // https://github.com/support-project/knowledge/blob/8423ec9d7ce62ba932849a538aae89954c9a6e57/src/main/java/org/support/project/web/control/GetApiControl.java
            String strListKnowledgeJson = get(baseUrl + "/api/knowledges?limit=50");
            //System.out.println(strListKnowledgeJson);
            List<Knowledge> listKnowledge = mapper.readValue(strListKnowledgeJson, new TypeReference<List<Knowledge>>(){});
            for (Knowledge knowledge : listKnowledge) {
                //System.out.println(knowledge.getKnowledgeId());
                //System.out.println(knowledge.getTitle());
                if (title != null) {
                    if ( !title.equals(knowledge.getTitle()) ) {
                        continue;
                    }
                }
                if (tags != null) {
                    if ( !knowledge.getTags().containsAll(listTags) ) {
                        continue;
                    }
                }
                
                String result = delete(baseUrl + "/api/knowledges/" + knowledge.getKnowledgeId());
                System.out.println("DEL : " + knowledge.getKnowledgeId() + ", " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder().url(url)
            .header("PRIVATE-TOKEN", token)
            .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
    public String delete(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder().url(url)
            .header("PRIVATE-TOKEN", token)
            .delete()
            .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
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
