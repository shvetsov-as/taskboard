package com.example.taskboard.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.scheme}") String currentScheme;
    @Value("${server.host}") String currentHost;
    @Value("${server.port}") String currentPort;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Demo taskboard RESTful API")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("a002eshvetsov@gmail.com")

                                                .name("Aleksandr Shvetsov")))
                .servers(getServers())
                .tags(getTags());
                //.components(getComponents());

    }

    @Bean
    public List<Server> getServers() {
        List<Server> serverList = new ArrayList<>();
        String activeServerUrl = getServerUrl(currentScheme, currentHost, currentPort);
        serverList.add(new Server().url(activeServerUrl).description("working connection"));
        return serverList;
    }

    @Bean
    public String getServerUrl(String scheme, String host, String port) {
        UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .build();
        return builder.toString();
    }

    @Bean
    public List<Tag> getTags() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag().name("API v.1").description("API version 1"));
        tagList.add(new Tag().name("API internal type").description(""));
        tagList.add(new Tag().name("API external type").description(""));

        tagList.add(new Tag().name("Employee controller").description("All operations with Employee"));
        tagList.add(new Tag().name("Release controller").description("All operations with Release"));
        tagList.add(new Tag().name("Taskboard controller").description("All operations with Taskboard"));
        tagList.add(new Tag().name("Task controller").description("All operations with Tasks"));
        tagList.add(new Tag().name("User controller").description("All operations with users"));
        return tagList;
    }
//    @Bean
//    public Components getComponents(){
//        Components components = new Components();
//        Schema<DtoPage> schema = new Schema<>();
//        schema.$ref("/components/schemas/DtoPage");
//        schema.type("Object");
//        schema.description("DtoPage");
//        components.addSchemas("DtoPage", schema);
//        return components;
//    }


}