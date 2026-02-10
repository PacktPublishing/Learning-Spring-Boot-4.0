package com.learningspringboot4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(VideoClient.class)
class VideoClientConfig {

    @Bean
    RestClientHttpServiceGroupConfigurer videoClientConfigurer() {
        return groups -> groups.forEachClient((name, builder) ->
                builder
                        .baseUrl("http://localhost:8080")
                        .apiVersionInserter(ApiVersionInserter.useHeader("API-Version")) // Header Versioning
                        // .apiVersionInserter(ApiVersionInserter.useQueryParam("version")) // Query Param Versioning
                        // .apiVersionInserter(ApiVersionInserter.usePathSegment(1))   // URI (Path) Versioning
                        // .apiVersionInserter(ApiVersionInserter.useMediaTypeParam("version")) // Media Type Versioning

        );
    }
}