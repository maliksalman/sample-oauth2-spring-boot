package com.smalik.resourceserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private List<InfoBean> heroes = Arrays.asList("superman", "spiderman", "batman")
            .stream()
            .map(InfoBean::new)
            .collect(Collectors.toList());


    @GetMapping("/hero")
    public List<InfoBean> listHeroes() {
        return heroes;
    }

    @PostMapping("/hero/{name}")
    public InfoBean addHero(@PathVariable("name") String name) {
        InfoBean info = new InfoBean(name);
        heroes.add(info);
        return info;
    }
}
