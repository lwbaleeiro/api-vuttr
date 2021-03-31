package br.com.api.vuttr.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.api.vuttr.model.Tool;

public class DetailToolDto {

	private String id;
	private String title;
	private String link;
	private String description;
	private List<String> tags;

	public DetailToolDto(Tool tool) {

		this.id = tool.getId();
		this.title = tool.getTitle();
		this.link = tool.getLink();
		this.description = tool.getDescription();
		this.tags = new ArrayList<>();
		this.tags.addAll(tool.getTags());

	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getTags() {
		return tags;
	}

	public static List<DetailToolDto> toDto(List<Tool> tools) {

		List<DetailToolDto> listDto = new ArrayList<>();
		tools.forEach(tool -> listDto.add(new DetailToolDto(tool)));

		return listDto;
	}

}
