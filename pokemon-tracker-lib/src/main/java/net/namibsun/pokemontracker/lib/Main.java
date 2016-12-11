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
import net.namibsun.pokemontracker.lib.database.DatabaseHandler;
import net.namibsun.pokemontracker.lib.database.pokedex.PokedexDatabaseHandler;
import net.namibsun.pokemontracker.lib.database.sqlite.SQLiteDatabase;
import net.namibsun.pokemontracker.lib.models.PokemonSpecies;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        Database db = new SQLiteDatabase("/home/hermann/pokedex.db");
        PokedexDatabaseHandler handler = new PokedexDatabaseHandler(db);

        handler.createPokedexTable();
        handler.storePokemonSpeciesInDatabase(new PokemonSpecies(1, new SerebiiParser(1)));

    }
}
