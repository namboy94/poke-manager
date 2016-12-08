package net.namibsun.pokemontracker.lib;

import java.io.IOException;
import java.sql.SQLException;
import net.namibsun.pokemontracker.lib.models.GenericPokemon;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;


public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        long timer = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - timer);
        new GenericPokemon(1);
        System.out.println(System.currentTimeMillis() - timer);
        new SerebiiParser("Bulbasaur").parsePokemonName();
        System.out.println(System.currentTimeMillis() - timer);

    }
}
