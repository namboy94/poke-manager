/*
This file is part of pokemon-tracker.

    pokemon-tracker is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    pokemon-tracker is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with pokemon-tracker.  If not, see <http://www.gnu.org/licenses/>.
*/

package net.namibsun.pokemontracker.lib;


import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.pokedex.PokedexDatabaseHandler;
import net.namibsun.pokemontracker.lib.database.sqlite.SQLiteDatabase;
import net.namibsun.pokemontracker.lib.pokemon.PokemonSpecies;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        Database db = new SQLiteDatabase("/home/hermann/pokedex.db");
        PokedexDatabaseHandler handler = new PokedexDatabaseHandler(db);

        handler.createPokedexTable();

        int errorCount = 0;

        for (int i = 1; i < 802; i++) {
            System.out.println(i);
            try {
                handler.storePokemonSpeciesInDatabase(new PokemonSpecies(i, new SerebiiParser(i)));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(i + " has an error");
                //System.exit(1);
                errorCount += 1;
            }
        }

        System.out.println("Errors: " + errorCount);

        handler.storePokemonSpeciesInDatabase(new PokemonSpecies(1, new SerebiiParser(1)));
        PokemonSpecies species = handler.getSpeciesFromDatabase(1);
        System.out.println(species.getName().getName("english"));
    }
}
