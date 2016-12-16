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

package net.namibsun.pokemontracker.lib.database.pokedex;

import java.io.File;
import org.junit.Test;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import java.sql.SQLException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.pokemon.PokemonSpecies;
import net.namibsun.pokemontracker.lib.database.sqlite.SQLiteDatabase;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class PokedexDatabaseHandlerTest {

    private static PokedexDatabaseHandler handler;

    @BeforeClass
    public static void setupClass() throws SQLException {
        handler = new PokedexDatabaseHandler(new SQLiteDatabase("test.db"));
        handler.createPokedexTable();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException, IOException {
        handler.quit();
        //noinspection ResultOfMethodCallIgnored
        new File("test.db").delete();
    }

    @Test
    public void testStoringAndRetrievingFromDataBase() throws IOException, SQLException {
        PokemonSpecies species = new PokemonSpecies(1, new SerebiiParser(1));
        handler.storePokemonSpeciesInDatabase(species);
        PokemonSpecies retrieved = handler.getSpeciesFromDatabase(1);
        assertTrue(species.equals(retrieved));
    }

    @Test
    public void testRetrievingNotExistingPokemon() throws SQLException {
        assertTrue(handler.getSpeciesFromDatabase(2) == null);
    }

    @Test
    public void testStoringAndRetrievingPokemonWithSingleEggGroup() throws IOException, SQLException {
        PokemonSpecies species = new PokemonSpecies(150, new SerebiiParser(150));
        handler.storePokemonSpeciesInDatabase(species);
        PokemonSpecies retrieved = handler.getSpeciesFromDatabase(150);
        assertTrue(species.equals(retrieved));
    }

    @Test
    public void testStoringPokemonTwice() throws IOException, SQLException {
        PokemonSpecies species = new PokemonSpecies(3, new SerebiiParser(3));
        handler.storePokemonSpeciesInDatabase(species);
        handler.storePokemonSpeciesInDatabase(species);
        assertTrue(handler.getSpeciesFromDatabase(3).equals(species));
    }

    @Test
    public void testStoringAndRetrivingPokemonWithoutHiddenAbility() throws IOException, SQLException {
        PokemonSpecies species = new PokemonSpecies(92, new SerebiiParser(92));
        handler.storePokemonSpeciesInDatabase(species);
        PokemonSpecies retrieved = handler.getSpeciesFromDatabase(92);
        assertTrue(species.equals(retrieved));
    }

    @Test
    public void testStoringAndRetrivingPokemonWithoutTwoRegularAbilities() throws IOException, SQLException {
        PokemonSpecies species = new PokemonSpecies(19, new SerebiiParser(19));
        handler.storePokemonSpeciesInDatabase(species);
        PokemonSpecies retrieved = handler.getSpeciesFromDatabase(19);
        assertTrue(species.equals(retrieved));
    }

}
