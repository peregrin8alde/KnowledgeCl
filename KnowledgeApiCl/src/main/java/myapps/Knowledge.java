package myapps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;


@JsonIgnoreProperties({})
public class Knowledge {
    
    private List<String> attachments;
    private int commentCount;
    private List<String> comments;
    private String content;
    private int publicFlag;
    private List<String> tags;
    private String template;
    private String title;

    public Knowledge(String title) {
        this.title = title;
        
        this.attachments = new ArrayList<String>();
        this.commentCount = 0;
        this.comments = new ArrayList<String>();
        this.content = "";
        this.publicFlag = 0;
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

    
    
}