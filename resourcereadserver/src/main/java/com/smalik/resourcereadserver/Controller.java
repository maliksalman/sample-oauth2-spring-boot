package com.smalik.resourcereadserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@RestController
public class Controller {

    private Logger logger = LoggerFactory.getLogger(Controller.class);
    private WebClient readClient;
    private List<InfoBean> lastKnownList = Collections.emptyList();

    public Controller(WebClient readClient) {
        this.readClient = readClient;
    }

    @GetMapping("/hero")
    public List<InfoBean> listHeroes() {
        return lastKnownList;
    }

    @Scheduled(fixedDelay = 15000)
    public void retrieveHeroes() {
        this.lastKnownList = readClient
                .get()
                .uri("/hero")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<InfoBean>>() {})
                .block();
        logger.info("Got list of heroes, count={}", this.lastKnownList.size());
    }
}
