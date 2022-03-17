package com.example.taskboard.model.service.uriBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CustomUriComponentsBuilder {

    private final String uriScheme;
    private final String uriHost;
    private final String uriPort;
    private final String apiVersion;

    public CustomUriComponentsBuilder(@Value("${scheme.http}") String uriScheme,
                                      @Value("${server.host}") String uriHost,
                                      @Value("${server.port}") String uriPort,
                                      @Value("${api-version.v1}") String apiVersion) {
        this.uriScheme = uriScheme;
        this.uriHost = uriHost;
        this.uriPort = uriPort;
        this.apiVersion = apiVersion;
    }

public URI buildUriRedirectByEntityId(String pathParameterPrefix, Long EntityId){

    UriComponents builder = UriComponentsBuilder.newInstance()
            .scheme(uriScheme)
            .host(uriHost)
            .port(uriPort)
            .path(apiVersion)
            .path(pathParameterPrefix)
            .path("{EntityId}")
            .buildAndExpand(EntityId);
    return builder.toUri();
}

}
