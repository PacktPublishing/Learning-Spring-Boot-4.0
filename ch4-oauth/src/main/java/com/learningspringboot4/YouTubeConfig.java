package com.learningspringboot4;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;

@Configuration
public class YouTubeConfig {

  static final String YOUTUBE_V3_API = "https://www.googleapis.com/youtube/v3";

  @Bean
  WebClient youtubeWebClient(OAuth2AuthorizedClientManager clientManager) {

    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientManager);
    oauth2.setDefaultClientRegistrationId("google");

    return WebClient.builder()
            .baseUrl(YOUTUBE_V3_API)
            .apply(oauth2.oauth2Configuration())
            .build();
  }

  @Bean
  HttpServiceProxyFactory proxyFactory(WebClient oauth2WebClient) {
    return HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(oauth2WebClient))
            .build();
  }

  @Bean
  YouTube youTubeClient(HttpServiceProxyFactory youtubeProxyFactory) {
    return youtubeProxyFactory.createClient(YouTube.class);
  }
}
