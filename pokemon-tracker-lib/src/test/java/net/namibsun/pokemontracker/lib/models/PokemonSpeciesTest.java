package net.namibsun.pokemontracker.lib.models;

import net.namibsun.pokemontracker.lib.models.enums.EggGroupTypes;
import net.namibsun.pokemontracker.lib.models.enums.PokemonTypes;
import net.namibsun.pokemontracker.lib.models.pokemonparts.*;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class PokemonSpeciesTest {

    @Test
    public void testGeneratingPokemonSpecies() {
        PokemonSpecies species = new PokemonSpecies(
                1,
                new Name("Bulbasaur", "Bulbizarre", "Bisasam", "Fushigidane フシギダネ", "이상해씨"),
                new GenderRatio(87.5, 12.5),
                new Type(PokemonTypes.GRASS, PokemonTypes.POISON),
                new SpeciesDescription(6.9, 15.2, 0.7, 2.04, "Seed Pokémon"),
                new Rates(45, 5120, 70, 1059860, "Medium Slow"),
                new EffortValueYield(0, 0, 0, 1, 0, 0),
                new Ability(
                        "Overgrow", "When HP is below 1/3rd its maximum, " +
                        "power of Grass-type moves is increased by 50%.",
                        null, null,
                        "Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                        "However, Speed will not double on the turn weather becomes Strong Sunlight."),
                new BaseStats(45, 49, 49, 65, 65, 45),
                new EggGroups(EggGroupTypes.MONSTER, EggGroupTypes.GRASS, false),
                new MegaEvolution(false)
        );
        this.checkBulbasaur(species);
    }

    @Test
    public void testGeneratingPokemonSpeciesFromWebParser() throws IOException {
        PokemonSpecies species = new PokemonSpecies(1, new SerebiiParser("Bulbasaur"));
        this.checkBulbasaur(species);
    }

    private void checkBulbasaur(PokemonSpecies bulbasaur) {
        assertEquals(1, bulbasaur.getPokedexNumber());

        assertEquals("Bulbasaur", bulbasaur.getName().getName("english"));
        assertEquals("Bisasam", bulbasaur.getName().getName("german"));
        assertEquals("Bulbizarre", bulbasaur.getName().getName("french"));
        assertEquals("Fushigidane フシギダネ", bulbasaur.getName().getName("japanese"));
        assertEquals("이상해씨", bulbasaur.getName().getName("korean"));

        assertEquals(12.5, bulbasaur.getGenderRatio().getFemaleRatio(), 0.0);
        assertEquals(87.5, bulbasaur.getGenderRatio().getMaleRatio(), 0.0);
        assertFalse(bulbasaur.getGenderRatio().isNeutralGendered());

        assertEquals(PokemonTypes.GRASS, bulbasaur.getType().getPrimaryType());
        assertEquals(PokemonTypes.POISON, bulbasaur.getType().getSecondaryType());
        assertEquals("Grass", bulbasaur.getType().getPrimaryTypeAsString());
        assertEquals("Poison", bulbasaur.getType().getSecondaryTypeAsString());
        assertTrue(bulbasaur.getType().isDualTyped());

        assertEquals(6.9, bulbasaur.getSpeciesDescription().getMetricWeight(), 0.0);
        assertEquals(15.2, bulbasaur.getSpeciesDescription().getImperialWeight(), 0.0);
        assertEquals(0.7, bulbasaur.getSpeciesDescription().getMetricHeight(), 0.0);
        assertEquals(2.04, bulbasaur.getSpeciesDescription().getImperialHeight(), 0.0);
        assertEquals("Seed Pokémon", bulbasaur.getSpeciesDescription().getClassification());

        assertEquals(45, bulbasaur.getRates().getCaptureRate());
        assertEquals(5120, bulbasaur.getRates().getBaseEggSteps());
        assertEquals(70, bulbasaur.getRates().getBaseHappiness());
        assertEquals(1059860, bulbasaur.getRates().getExperienceGrowthPoints());
        assertEquals("Medium Slow", bulbasaur.getRates().getExperienceGrowthDescription());

        assertEquals(0, bulbasaur.getEffortValueYield().getHpYield());
        assertEquals(0, bulbasaur.getEffortValueYield().getAttackYield());
        assertEquals(0, bulbasaur.getEffortValueYield().getDefenseYield());
        assertEquals(1, bulbasaur.getEffortValueYield().getSpAttackYield());
        assertEquals(0, bulbasaur.getEffortValueYield().getSpDefenseYield());
        assertEquals(0, bulbasaur.getEffortValueYield().getSpeedYield());

        assertArrayEquals(new String[] {
                "Overgrow", "When HP is below 1/3rd its maximum, " +
                "power of Grass-type moves is increased by 50%."},
                bulbasaur.getAbility().getAbilityOne());
        assertTrue(bulbasaur.getAbility().getAbilityTwo() == null);
        assertArrayEquals(new String[] {
                "Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                "However, Speed will not double on the turn weather becomes Strong Sunlight."},
                bulbasaur.getAbility().getHiddenAbility());
        assertTrue(bulbasaur.getAbility().hasHiddenAbility());
        assertFalse(bulbasaur.getAbility().hasSecondRegularAbility());

        assertEquals(45, bulbasaur.getBaseStats().getHp());
        assertEquals(49, bulbasaur.getBaseStats().getAttack());
        assertEquals(49, bulbasaur.getBaseStats().getDefense());
        assertEquals(65, bulbasaur.getBaseStats().getSpecialAttack());
        assertEquals(65, bulbasaur.getBaseStats().getSpecialDefense());
        assertEquals(45, bulbasaur.getBaseStats().getSpeed());

        assertEquals(EggGroupTypes.MONSTER, bulbasaur.getEggGroups().getPrimaryEggGroup());
        assertEquals(EggGroupTypes.GRASS, bulbasaur.getEggGroups().getSecondaryEggGroup());
        assertTrue(bulbasaur.getEggGroups().hasTwoEggGroups());
        assertTrue(bulbasaur.getEggGroups().canBreed());
        assertFalse(bulbasaur.getEggGroups().canOnlyBreedWithDitto());

        assertFalse(bulbasaur.getMegaEvolution().hasMegaEvolution());

    }
}
