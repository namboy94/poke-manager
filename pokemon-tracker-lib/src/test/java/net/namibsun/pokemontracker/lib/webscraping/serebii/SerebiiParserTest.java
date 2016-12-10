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

package net.namibsun.pokemontracker.lib.webscraping.serebii;

import net.namibsun.pokemontracker.lib.models.enums.PokemonStatTypes;
import org.junit.Test;
import java.util.HashMap;
import java.io.IOException;
import org.junit.BeforeClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import net.namibsun.pokemontracker.lib.webscraping.PokemonConstants;

public class SerebiiParserTest {
    
    private static SerebiiParser bulbasaurParser;
    private static SerebiiParser venusaurParser;
    private static SerebiiParser charmanderParser;

    private static SerebiiParser magnemiteParser;
    
    @BeforeClass
    public static void setupClass() throws IOException {
        bulbasaurParser = new SerebiiParser("Bulbasaur");
        venusaurParser = new SerebiiParser("Venusaur");
        charmanderParser = new SerebiiParser("Charmander");
        magnemiteParser = new SerebiiParser("Magnemite");
    }
    

    @Test
    public void testParserInitialization() throws IOException {
        assertEquals(
                new SerebiiParser(1).getUrl(),
                new SerebiiParser("Bulbasaur").getUrl()
        );
    }

    @Test
    public void testParsingInvalidPokedexNumber() {
        try {
            new SerebiiParser(PokemonConstants.MAX_POKEMON_NUMBER + 1);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (IOException e) {
            assertEquals("Maximum number of Pokemon exceeded", e.getMessage());
        }
    }

    @Test
    public void testParsingInvalidPokemonName() {
        try {
            new SerebiiParser("BulbasaurTwo");
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (IOException e) {
            assertEquals("Pokemon not found", e.getMessage());
        }
    }
    
    @Test
    public void testParsingPokemonName() {
        HashMap<String, String> names = bulbasaurParser.parsePokemonName();
        assertEquals("Bulbasaur", names.get(PokemonConstants.ENGLISH_KEY));
        assertEquals("Bisasam", names.get(PokemonConstants.GERMAN_KEY));
        assertEquals("Bulbizarre", names.get(PokemonConstants.FRENCH_KEY));
        assertEquals("Fushigidane フシギダネ", names.get(PokemonConstants.JAPANESE_KEY));
        assertEquals("이상해씨", names.get(PokemonConstants.KOREAN_KEY));
    }

    @Test
    public void testParsingPokemonGenderRatioForPokemonWithGender() {
        double[] bulbasaurRatio = bulbasaurParser.parseGenderRatio();
        assertEquals(87.5, bulbasaurRatio[0], 0.0);
        assertEquals(12.5, bulbasaurRatio[1], 0.0);
    }

    @Test
    public void testParsingPokemonGenderRatioForPokemonWithNoGender() {
        assertEquals(null, magnemiteParser.parseGenderRatio());
    }

    @Test
    public void testParsingPokemonTypesMonoType() {
        String[] types = charmanderParser.parseTypes();
        assertEquals(1, types.length);
        assertEquals("FIRE", types[0]);
    }

    @Test
    public void testParsingPokemonTypesDualType() {
        String[] types = bulbasaurParser.parseTypes();
        assertEquals(2, types.length);
        assertEquals("GRASS", types[0]);
        assertEquals("POISON", types[1]);
    }

    @Test
    public void testParsingWeight() {
        double[] results = bulbasaurParser.parseWeight();
        assertEquals(results[0], 6.9, 0.0);
        assertEquals(results[1], 15.2, 0.0);
    }

    @Test
    public void testParsingHeight() {
        double[] results = bulbasaurParser.parseHeight();
        assertEquals(results[0], 0.7, 0.0);
        assertEquals(results[1], 2.04, 0.0);
    }

    @Test
    public void testParsingClassification() {
        String classification = bulbasaurParser.parseClassification();
        assertEquals("Seed Pokémon", classification);
    }

    @Test
    public void testParsingCaptureRate() {
        assertEquals(45, bulbasaurParser.parseCaptureRate());
    }

    @Test
    public void testParsingBaseEggSteps() {
        assertEquals(5120, bulbasaurParser.parseBaseEggSteps());
    }

    @Test
    public void testParsingBaseHappiness() {
        assertEquals(70, bulbasaurParser.parseBaseHappiness());
    }

    @Test
    public void testParsingExperienceGrowthPoints() {
        assertEquals(1059860, bulbasaurParser.parseExperienceGrowthPoints());
    }

    @Test
    public void testParsingExperienceGrowthDescription() {
        assertEquals("Medium Slow", bulbasaurParser.parseExperienceGrowthDescription());
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testParsingEffortValueYieldForPokemonWithMultipleEVYields() {
        assertEquals(0, venusaurParser.parseEffortValueYield(PokemonStatTypes.HP));
        assertEquals(0, venusaurParser.parseEffortValueYield(PokemonStatTypes.ATK));
        assertEquals(0, venusaurParser.parseEffortValueYield(PokemonStatTypes.DEF));
        assertEquals(2, venusaurParser.parseEffortValueYield(PokemonStatTypes.SATK));
        assertEquals(1, venusaurParser.parseEffortValueYield(PokemonStatTypes.SDEF));
        assertEquals(0, venusaurParser.parseEffortValueYield(PokemonStatTypes.SPD));
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testParsingEffortValueYieldForPokemonWithSingleEVYields() {
        assertEquals(0, bulbasaurParser.parseEffortValueYield(PokemonStatTypes.HP));
        assertEquals(0, bulbasaurParser.parseEffortValueYield(PokemonStatTypes.ATK));
        assertEquals(0, bulbasaurParser.parseEffortValueYield(PokemonStatTypes.DEF));
        assertEquals(1, bulbasaurParser.parseEffortValueYield(PokemonStatTypes.SATK));
        assertEquals(0, bulbasaurParser.parseEffortValueYield(PokemonStatTypes.SDEF));
        assertEquals(0, bulbasaurParser.parseEffortValueYield(PokemonStatTypes.SPD));
    }

}
