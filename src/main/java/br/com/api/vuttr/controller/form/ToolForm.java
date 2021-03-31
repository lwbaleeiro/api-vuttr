package br.com.api.vuttr.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.api.vuttr.model.Tool;

public class ToolForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String title;
	private String link;
	private String description;
	private List<String> tags = new ArrayList<>();
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public Tool ToTool() {
		
		return new Tool(this.title, this.link, this.description, this.tags); 
		
	}


}
