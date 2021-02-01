package com.smalik.resourceclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("readClient")
    public WebClient readClient(@Value("${resources.base-read-uri}") String baseUri, OAuth2AuthorizedClientManager authorizedClientManager) {
        return createWebClient(baseUri, authorizedClientManager, "grp_read_client");
    }

    @Bean("writeClient")
    public WebClient writeClient(@Value("${resources.base-write-uri}") String baseUri, OAuth2AuthorizedClientManager authorizedClientManager) {
        return createWebClient(baseUri, authorizedClientManager, "grp_write_client");
    }

    private WebClient createWebClient(String baseUri, OAuth2AuthorizedClientManager authorizedClientManager, String clientRegistrationId) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2Client.setDefaultClientRegistrationId(clientRegistrationId);

        return WebClient.builder()
                .apply(oauth2Client.oauth2Configuration())
                .baseUrl(baseUri)
                .build();
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository registrationRepository,
            OAuth2AuthorizedClientRepository clientRepository) {
        DefaultOAuth2AuthorizedClientManager clientManager = new DefaultOAuth2AuthorizedClientManager(
                registrationRepository, clientRepository);
        clientManager.setAuthorizedClientProvider(OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build());

        return clientManager;
    }
}