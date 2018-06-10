package myapps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Knowledge {
    
    private int knowledgeId;
    private List<String> attachments;
    private int commentCount;
    private List<String> comments;
    private String content;
    private int publicFlag;
    private List<String> tags;
    private String template;
    private String title;

    // 引数なしのコンストラクタがないとJson文字列=>Javaオブジェクト変換で怒られる
    public Knowledge() {
        this.attachments = new ArrayList<String>();
        this.comments = new ArrayList<String>();
        this.tags = new ArrayList<String>();
    }
    
    public Knowledge(String title) {
        this.title = title;
        
        this.attachments = new ArrayList<String>();
        this.comments = new ArrayList<String>();
        this.content = "";
        this.tags = new ArrayList<String>();
        this.template = "knowledge";
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setPublicFlag(int publicFlag) {
        this.publicFlag = publicFlag;
    }

    public int getKnowledgeId() {
        return this.knowledgeId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getContetns() {
        return this.content;
    }
    
    public List<String> getTags() {
        return this.tags;
    }
    
}