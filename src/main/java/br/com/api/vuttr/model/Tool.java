package br.com.api.vuttr.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tool")
public class Tool {

	@Id
	private String id;
	private String title;
	private String link;
	private String description;
	private List<String> tags = new ArrayList<>();

	public Tool() {
		
	}
	
	public Tool(String title, String link, String description, List<String> tags) {
		
		this.title = title;
		this.link = link;
		this.description = description;
		this.tags = tags;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
