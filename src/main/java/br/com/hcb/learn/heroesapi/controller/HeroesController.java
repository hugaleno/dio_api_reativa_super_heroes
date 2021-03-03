package br.com.hcb.learn.heroesapi.controller;

import static br.com.hcb.learn.heroesapi.contants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import br.com.hcb.learn.heroesapi.document.Heroes;
import br.com.hcb.learn.heroesapi.repository.HeroesRepository;
import br.com.hcb.learn.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {


  HeroesService heroesService;

  HeroesRepository heroesRepository;

  public HeroesController(HeroesService heroesService,
      HeroesRepository heroesRepository) {
    this.heroesService = heroesService;
    this.heroesRepository = heroesRepository;
  }

  @GetMapping(HEROES_ENDPOINT_LOCAL)
  public Flux<Heroes> getAllItems(){
    log.info("Requesting heroes list");
    return this.heroesService.findAll();
  }

  @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
  public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id){
    return heroesService.findById(id).map(heroes -> new ResponseEntity<>(heroes, HttpStatus.OK))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(HEROES_ENDPOINT_LOCAL)
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Heroes> createHero(@RequestBody Heroes heroes){
    return this.heroesService.save(heroes);
  }

  @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Mono<Boolean> deleteHero(@PathVariable String id){
    return this.heroesService.deleteById(id);
  }
}
