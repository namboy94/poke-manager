package net.namibsun.pokemontracker.lib;

import java.io.IOException;
import java.sql.SQLException;
import net.namibsun.pokemontracker.lib.models.GenericPokemon;
import net.namibsun.pokemontracker.lib.models.pokemonparts.Type;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;


public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        long timer = System.currentTimeMillis();
        new GenericPokemon(1);
        System.out.println(System.currentTimeMillis() - timer);

    }
}
