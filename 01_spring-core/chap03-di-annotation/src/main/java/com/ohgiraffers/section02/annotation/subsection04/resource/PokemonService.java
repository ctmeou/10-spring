package com.ohgiraffers.section02.annotation.subsection04.resource;

import com.ohgiraffers.section02.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("pokemonServiceResource")
public class PokemonService {

    /* @Resource 어노테이션은 자바에서 제공하는 기본 어노테이션이다.
    * @Autowired와 같은 스프링 어노테이션과 다르게 name 속성 값으로 의존성 주입을 할 수 있다. */

    /* 1. 필드 주입 방식 */
//    @Resource(name = "pikachu")
//    private Pokemon pokemon;
//
//    public void pokemonAttack() {
//        pokemon.attack();
//    }

    /* List<Pokemon> 타입으로 변경한 뒤 name 속성을 따로 기재하지 않고 동작시킬 수 있다.
    * 기본적으로는 name 속성을 통해 주입하지만 name 속성이 없을 경우 Type을 통해 의존성 주입한다. */
    @Resource
    private List<Pokemon> pokemonList;

    public void pokemonAttack() {
        pokemonList.forEach(Pokemon::attack);
    }

}