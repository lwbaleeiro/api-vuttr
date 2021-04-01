package br.com.api.vuttr;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.api.vuttr.model.Tool;
import br.com.api.vuttr.repository.ToolRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VuttrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VuttrApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ToolRepository toolRepository) {

		return args -> {
	
			List<Tool> tools = toolRepository.findAll();
			if (tools.isEmpty()) {

				Tool tool = new Tool();
				tool.setTitle("hotel");
				tool.setLink("https://github.com/typicode/hotel");
				tool.setDescription("Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.");
				var tags = List.of("node", "organizing", "webapps", "domain", "developer", "https", "proxy");
				tool.setTags(tags);
				
				toolRepository.save(tool);

			}

		};

	}

}
