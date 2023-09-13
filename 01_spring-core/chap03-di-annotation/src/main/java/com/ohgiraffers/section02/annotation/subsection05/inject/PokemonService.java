package com.ohgiraffers.section02.annotation.subsection05.inject;

import com.ohgiraffers.section02.common.Pokemon;
import jdk.jfr.Name;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service("pokemonServiceInject")
public class PokemonService {

    /* @Inject 어노테이션은 자바에서 제공하는 기본 어노테이션이다.
    * @Autowired와 같은 스프링 어노테이션과 같이 Type을 통해 의존성 주입을 하며 @Named를 통해 이름을 명시할 수 있다. */

    /* 1. 필드 주입 방식 */
//    @Inject
//    @Named("pikachu")
    private Pokemon pokemon;

    /* 2. 생성자 주입 방식 */
//    @Inject
//    public PokemonService(@Named("pikachu") Pokemon pokemon) { this.pokemon = pokemon; }

    /* 3. setter 타입 주입 */
    @Inject
    public void setPokemon(@Named("pikachu") Pokemon pokemon) { this.pokemon = pokemon; }

    public void pokemonAttack() {
        pokemon.attack();
    }

}