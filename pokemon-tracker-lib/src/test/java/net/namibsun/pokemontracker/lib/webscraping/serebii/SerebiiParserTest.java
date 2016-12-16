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

import org.junit.Test;
import java.util.HashMap;
import java.io.IOException;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.pokemon.enums.species.Languages;
import net.namibsun.pokemontracker.lib.pokemon.enums.species.EggGroupTypes;
import net.namibsun.pokemontracker.lib.webscraping.PokemonConstants;
import net.namibsun.pokemontracker.lib.pokemon.enums.species.PokemonStatTypes;

public class SerebiiParserTest {
    
    private static SerebiiParser bulbasaurParser;
    private static SerebiiParser venusaurParser;
    private static SerebiiParser charmanderParser;
    private static SerebiiParser rattataParser;
    private static SerebiiParser raichuParser;
    private static SerebiiParser venoMothParser;
    private static SerebiiParser magnemiteParser;
    private static SerebiiParser gastlyParser;
    private static SerebiiParser parasParser;
    private static SerebiiParser abraParser;
    private static SerebiiParser mewtwoParser;
    private static SerebiiParser deoxysParser;
    private static SerebiiParser shayminParser;
    private static SerebiiParser basculinParser;
    private static SerebiiParser darmanitanParser;
    private static SerebiiParser sigilyphParser;
    private static SerebiiParser kyuremParser;
    private static SerebiiParser zygardeParser;
    private static SerebiiParser hoopaParser;
    private static SerebiiParser popplioParser;
    private static SerebiiParser miniorParser;
    
    @BeforeClass
    public static void setupClass() throws IOException {
        bulbasaurParser = new SerebiiParser("Bulbasaur");
        venusaurParser = new SerebiiParser("Venusaur");
        charmanderParser = new SerebiiParser("Charmander");
        rattataParser = new SerebiiParser("Rattata");
        raichuParser = new SerebiiParser("Raichu");
        venoMothParser = new SerebiiParser("Venomoth");
        magnemiteParser = new SerebiiParser("Magnemite");
        gastlyParser = new SerebiiParser("Gastly");
        parasParser = new SerebiiParser("Paras");
        abraParser = new SerebiiParser("Abra");
        mewtwoParser = new SerebiiParser("Mewtwo");
        deoxysParser = new SerebiiParser("Deoxys");
        shayminParser = new SerebiiParser("Shaymin");
        basculinParser = new SerebiiParser("Basculin");
        darmanitanParser = new SerebiiParser("Darmanitan");
        sigilyphParser = new SerebiiParser("Sigilyph");
        kyuremParser = new SerebiiParser("Kyurem");
        zygardeParser = new SerebiiParser("Zygarde");
        hoopaParser = new SerebiiParser("Hoopa");
        popplioParser = new SerebiiParser("Popplio");
        miniorParser = new SerebiiParser("Minior");
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
        assertEquals("Bulbasaur", names.get(Languages.ENGLISH.name().toLowerCase()));
        assertEquals("Bisasam", names.get(Languages.GERMAN.name().toLowerCase()));
        assertEquals("Bulbizarre", names.get(Languages.FRENCH.name().toLowerCase()));
        assertEquals("Fushigidane フシギダネ", names.get(Languages.JAPANESE.name().toLowerCase()));
        assertEquals("이상해씨", names.get(Languages.KOREAN.name().toLowerCase()));
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
    public void testParsingAlolanPokemonNormalType() {
        String[] types = rattataParser.parseTypes();
        assertEquals(1, types.length);
        assertEquals("NORMAL", types[0]);
    }

    @Test
    public void testParsingWeight() {
        double[] results = bulbasaurParser.parseWeight();
        assertEquals(results[0], 6.9, 0.0);
        assertEquals(results[1], 15.2, 0.0);
    }

    @Test
    public void testParsingAlolanPokemonNormalWeight() {
        double[] results = rattataParser.parseWeight();
        assertEquals(results[0], 3.5, 0.0);
        assertEquals(results[1], 7.7, 0.0);
    }

    @Test
    public void testParsingMultiFormPokemonWeight() {
        double[] results = darmanitanParser.parseWeight();
        assertEquals(results[0], 92.9, 0.0);
        assertEquals(results[1], 204.8, 0.0);
    }

    @Test
    public void testParsingKyuremWeight() {
        double[] results = kyuremParser.parseWeight();
        assertEquals(results[0], 325.0, 0.0);
        assertEquals(results[1], 716.5, 0.0);
    }

    @Test
    public void testParsingZygardeWeight() {
        double[] results = zygardeParser.parseWeight();
        assertEquals(results[0], 284.6, 0.0);
        assertEquals(results[1], 627.4, 0.0);
    }

    @Test
    public void testParsingHoopaWeight() {
        double[] results = hoopaParser.parseWeight();
        assertEquals(results[0], 9.0, 0.0);
        assertEquals(results[1], 19.8, 0.0);
    }

    @Test
    public void testParsingHeight() {
        double[] results = bulbasaurParser.parseHeight();
        assertEquals(results[0], 0.7, 0.0);
        assertEquals(results[1], 2.04, 0.0);
    }

    @Test
    public void testParsingAlolanPokemonNormalHeight() {
        double[] results = rattataParser.parseHeight();
        assertEquals(results[0], 0.3, 0.0);
        assertEquals(results[1], 1.00, 0.0);
    }

    @Test
    public void testParsingMultiFormPokemonHeight() {
        double[] results = darmanitanParser.parseHeight();
        assertEquals(results[0], 1.3, 0.0);
        assertEquals(results[1], 4.03, 0.0);
    }

    @Test
    public void testParsingKyuremHeight() {
        double[] results = kyuremParser.parseHeight();
        assertEquals(results[0], 3.0, 0.0);
        assertEquals(results[1], 9.10, 0.0);
    }

    @Test
    public void testParsingZygardeHeight() {
        double[] results = zygardeParser.parseHeight();
        assertEquals(results[0], 5.0, 0.0);
        assertEquals(results[1], 16.05, 0.0);
    }

    @Test
    public void testParsingHoopaHeight() {
        double[] results = hoopaParser.parseHeight();
        assertEquals(results[0], 0.5, 0.0);
        assertEquals(results[1], 1.08, 0.0);
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
    public void testParsingMiniorCaptureRate() {
        assertEquals(30, miniorParser.parseCaptureRate());
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

    @Test
    public void testParsingDeoxysEffortValueYield() {
        assertEquals(0, deoxysParser.parseEffortValueYield(PokemonStatTypes.HP));
        assertEquals(1, deoxysParser.parseEffortValueYield(PokemonStatTypes.ATK));
        assertEquals(0, deoxysParser.parseEffortValueYield(PokemonStatTypes.DEF));
        assertEquals(1, deoxysParser.parseEffortValueYield(PokemonStatTypes.SATK));
        assertEquals(0, deoxysParser.parseEffortValueYield(PokemonStatTypes.SDEF));
        assertEquals(1, deoxysParser.parseEffortValueYield(PokemonStatTypes.SPD));
    }

    @Test
    public void testParsingSingleAbility() {
        String[] ability = bulbasaurParser.parseRegularAbilities();
        assertEquals(2, ability.length);
        assertArrayEquals(ability, new String[] {"Overgrow", "When HP is below 1/3rd its maximum, " +
                "power of Grass-type moves is increased by 50%."});
    }

    @Test
    public void testParsingMultipleAbilities() {
        String[] abilities = parasParser.parseRegularAbilities();
        assertEquals(4, abilities.length);
        assertArrayEquals(abilities, new String[] {
                "Effect Spore", "The opponent has a 10% chance of being induced by " +
                "PARALYZE, POISON, or SLEEP when using an attack, " +
                "that requires physical contact, against this Pokémon.",
                "Dry Skin", "HP is restored when hit by Water-type moves " +
                "or when it is raining but also makes the Pokémon weak to " +
                "Fire-type moves and reduces HP during strong sunlight."
        });
    }

    @Test
    public void testParsingAlolanPokemonNormalAbilities() {
        String[] abilities = rattataParser.parseRegularAbilities();
        assertEquals(4, abilities.length);
        assertArrayEquals(abilities, new String[] {
                "Run Away", "Except for trainer battles, can always run from battle. " +
                "Cannot run during Mean Look or Block or when the opponent is trapping with the Arena Trap, " +
                "Magnet Pull, or Shadow Tag ability.",
                "Guts", "Attack is increased by 50% when induced with a status " +
                "(BURN, PARALYZE, SLEEP, POISON, FREEZE). Burn’s effect of lowering Attack is not applied."
        });
    }

    @Test
    public void testParsingAnotherAlolanPokemonNormalAbilities() {
        String[] abilities = raichuParser.parseRegularAbilities();
        assertEquals(2, abilities.length);
        assertArrayEquals(abilities, new String[] {
                "Static", "The opponent has a 30% chance of being induced with PARALYZE when using an attack, " +
                "that requires physical contact, against this Pokémon."
        });
    }

    @Test
    public void testParsingSigilyphAbility() {
        String[] abilities = sigilyphParser.parseRegularAbilities();
        assertEquals(4, abilities.length);
        assertArrayEquals(abilities, new String[] {
                "Wonder Skin", "Makes non-damaging moves that are targeted at this Pokémon have the accuracy of 50%.",
                "Magic Guard", "Prevents all damage except from direct-attack moves."
        });
    }

    @Test
    public void testParsingBasculinRegularAbility() {
        String[] abilities = basculinParser.parseRegularAbilities();
        assertEquals(2, abilities.length);
        assertArrayEquals(abilities, new String[] {
                "Adaptability", "Increases the Same Type Attack Bonus from *1.5 to *2."
        });
    }

    @Test
    public void testParsingShayminAbilities() {
        String regular[] = shayminParser.parseRegularAbilities();
        String hidden[] = shayminParser.parseHiddenAbility();
        assertArrayEquals(regular,
                new String[] {"Natural Cure", "The Pokémon’s status (BURN, PARALYZE, SLEEP, POISON, FREEZE) " +
                        "is healed when withdrawn from battle."});
        assertTrue(hidden == null);
    }

    @Test
    public void testParsingZygardeAbilities() {
        String regular[] = zygardeParser.parseRegularAbilities();
        String hidden[] = zygardeParser.parseHiddenAbility();
        assertArrayEquals(regular,
                new String[] {
                "Aura Break", "The effects of Aura Abilities are reversed to lower the power of affected moves.",
                "Power Construct", "Other Cells gather to aid when its HP becomes half or less. " +
                        "Then the Pokémon changes its form to Complete Forme."});
        assertTrue(hidden == null);
    }

    @Test
    public void testParsingKyuremAbilities() {
        String regular[] = kyuremParser.parseRegularAbilities();
        String hidden[] = kyuremParser.parseHiddenAbility();
        assertArrayEquals(regular,
                new String[] {"Pressure", "When this Pokémon is hit by a move, the opponent’s PP lowers by 2 " +
                        "rather than 1. Opponents in S.O.S. Battles are more likely to call for help."});
        assertTrue(hidden == null);
    }

    @Test
    public void testParsingHiddenAbility() {
        String[] hiddenAbility = bulbasaurParser.parseHiddenAbility();
        assertEquals(2, hiddenAbility.length);
        assertArrayEquals(hiddenAbility, new String[] {
                "Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                "However, Speed will not double on the turn weather becomes Strong Sunlight."
        });
    }

    @Test
    public void testParsingDreamWorldAbility() {
        String[] hiddenAbility = basculinParser.parseHiddenAbility();
        assertEquals(2, hiddenAbility.length);
        assertArrayEquals(hiddenAbility, new String[] {
                "Mold Breaker", "The Pokémon's moves are not affected by foe’s abilities during battle."
        });
    }

    @Test
    public void testParsingHiddenAbilityWithVenomoth() {
        String[] hiddenAbility = venoMothParser.parseHiddenAbility();
        assertEquals(2, hiddenAbility.length);
        assertArrayEquals(hiddenAbility, new String[] {
                "Wonder Skin", "Makes non-damaging moves that are targeted at this Pokémon have the accuracy of 50%."
        });
    }

    @Test
    public void testParsingNoHiddenAbility() {
        assertTrue(gastlyParser.parseHiddenAbility() == null);
    }

    @Test
    public void testParsingBaseStats(){
        assertArrayEquals(new int[] {45, 49, 49, 65, 65, 45}, bulbasaurParser.parseBaseStats());
    }

    @Test
    public void testParsingSingleEggGroup() {
        String[] result = gastlyParser.parseEggGroups();
        assertEquals(EggGroupTypes.AMORPHOUS.name(), result[0]);
    }

    @Test
    public void testParsingMultipleEggGroups() {
        String[] results = bulbasaurParser.parseEggGroups();
        assertEquals(EggGroupTypes.MONSTER.name(), results[0]);
        assertEquals(EggGroupTypes.GRASS.name(), results[1]);
    }

    @Test
    public void testParsingHumanLikeEggGroup() {
        String[] results = abraParser.parseEggGroups();
        assertEquals(EggGroupTypes.HUMANLIKE.name(), results[0]);
    }

    @Test
    public void testParsingWaterEggGroup() {
        String[] results = popplioParser.parseEggGroups();
        assertEquals(EggGroupTypes.WATER1.name(), results[0]);
    }

    @Test
    public void testParsingOnlyDittoEggGroups() {
        String[] results = magnemiteParser.parseEggGroups();
        assertEquals(EggGroupTypes.MINERAL.name(), results[0]);
        assertEquals("true", results[2]);
    }

    @Test
    public void testParsingUndiscoveredEggGroups() {
        String[] results = mewtwoParser.parseEggGroups();
        assertEquals(EggGroupTypes.UNDISCOVERED.name(), results[0]);
    }

    @Test
    public void testParsingEggGroupsPokemonWithTwoHeldItems() {
        String[] results = parasParser.parseEggGroups();
        assertEquals(EggGroupTypes.BUG.name(), results[0]);
        assertEquals(EggGroupTypes.GRASS.name(), results[1]);
    }
}
