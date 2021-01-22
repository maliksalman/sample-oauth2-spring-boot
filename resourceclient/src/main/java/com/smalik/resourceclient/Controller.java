package com.smalik.resourceclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class Controller {

    private WebClient readClient;
    private WebClient writeClient;

    public Controller(
            @Qualifier("readClient") WebClient readClient,
            @Qualifier("writeClient") WebClient writeClient) {
        this.readClient = readClient;
        this.writeClient = writeClient;
    }

    @PostMapping("/hero/{name}")
    public InfoBean addHero(@PathVariable("name") String name) {
        return writeClient
                .post()
                .uri("/hero/" + name)
                .retrieve()
                .bodyToMono(InfoBean.class)
                .block();
    }

    @GetMapping("/hero")
    public List<InfoBean> listHeroes() {
        return readClient
                .get()
                .uri("/hero")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<InfoBean>>() {})
                .block();
    }
}
