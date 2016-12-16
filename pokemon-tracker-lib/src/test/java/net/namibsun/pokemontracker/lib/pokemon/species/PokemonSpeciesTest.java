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

package net.namibsun.pokemontracker.lib.pokemon.species;

import net.namibsun.pokemontracker.lib.pokemon.PokemonSpecies;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.*;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

import net.namibsun.pokemontracker.lib.pokemon.enums.species.PokemonTypes;
import net.namibsun.pokemontracker.lib.pokemon.enums.species.EggGroupTypes;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class PokemonSpeciesTest {

    @Test
    public void testGeneratingPokemonSpecies() {
        PokemonSpecies species = this.generateBulbasaur();
        this.checkBulbasaur(species);
    }

    private PokemonSpecies generateBulbasaur() {
        return new PokemonSpecies(
                1,
                new Name("Bulbasaur", "Bulbizarre", "Bisasam", "Fushigidane フシギダネ", "이상해씨"),
                new GenderRatio(87.5, 12.5),
                new Type(PokemonTypes.GRASS, PokemonTypes.POISON),
                new SpeciesDescription(6.9, 15.2, 0.7, 2.04, "Seed Pokémon"),
                new Rates(45, 5120, 70, 1059860, "Medium Slow"),
                new EffortValueYield(0, 0, 0, 1, 0, 0),
                new Abilities(
                        "Overgrow", "When HP is below 1/3rd its maximum, " +
                        "power of Grass-type moves is increased by 50%.",
                        null, null,
                        "Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                        "However, Speed will not double on the turn weather becomes Strong Sunlight."),
                new BaseStats(45, 49, 49, 65, 65, 45),
                new EggGroups(EggGroupTypes.MONSTER, EggGroupTypes.GRASS, false)
        );
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

        assertEquals(0, bulbasaur.getEffortValueYield().getHp());
        assertEquals(0, bulbasaur.getEffortValueYield().getAttack());
        assertEquals(0, bulbasaur.getEffortValueYield().getDefense());
        assertEquals(1, bulbasaur.getEffortValueYield().getSpecialAttack());
        assertEquals(0, bulbasaur.getEffortValueYield().getSpecialDefense());
        assertEquals(0, bulbasaur.getEffortValueYield().getSpeed());

        assertArrayEquals(new String[] {
                "Overgrow", "When HP is below 1/3rd its maximum, " +
                "power of Grass-type moves is increased by 50%."},
                bulbasaur.getAbilities().getAbilityOne());
        assertTrue(bulbasaur.getAbilities().getAbilityTwo() == null);
        assertArrayEquals(new String[] {
                "Chlorophyll", "When sunny, the Pokémon’s Speed doubles. " +
                "However, Speed will not double on the turn weather becomes Strong Sunlight."},
                bulbasaur.getAbilities().getHiddenAbility());
        assertTrue(bulbasaur.getAbilities().hasHiddenAbility());
        assertFalse(bulbasaur.getAbilities().hasSecondRegularAbility());

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

    }

    @Test
    public void testEqualityCheck() throws IOException {
        assertTrue(this.generateBulbasaur().equals(
                new PokemonSpecies(1, new SerebiiParser("Bulbasaur"))));
        assertFalse(this.generateBulbasaur().equals(
                new PokemonSpecies(1, new SerebiiParser("Venusaur"))));
    }

    @Test
    public void testGeneratingPokemonWithAlolaForm() throws IOException {
        new PokemonSpecies(20, new SerebiiParser(20));
    }

    @Test
    public void extensiveEqualityCheck() {

        //defaults
        Name name = new Name("A", "B", "C", "D", "E");
        GenderRatio ratio = new GenderRatio(0.0, 0.0);
        Type type = new Type(PokemonTypes.GRASS);
        SpeciesDescription desc = new SpeciesDescription(0.0, 0.0, 0.0, 0.0, "");
        Rates rates = new Rates(0, 0, 0, 0, "");
        EffortValueYield evYield = new EffortValueYield(0, 0, 0, 0, 0, 0);
        Abilities abilities = new Abilities("A", "B");
        BaseStats baseStats = new BaseStats(1, 1, 1, 1, 1, 1);
        EggGroups eggGroups = new EggGroups(EggGroupTypes.MONSTER,false);

        PokemonSpecies one =
                new PokemonSpecies(0, name, ratio, type, desc, rates, evYield, abilities, baseStats, eggGroups);
        assertFalse(one.equals(
                new PokemonSpecies(1, name, ratio, type, desc, rates, evYield, abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0,
                        new Name("A", "B"), ratio, type, desc, rates, evYield, abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name,
                        new GenderRatio(0.1, 0.2), type, desc, rates, evYield, abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio,
                        new Type("Fire"), desc, rates, evYield, abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio, type,
                        new SpeciesDescription(0.1, 0.1, 0.2, 0.1, "A"), rates, evYield, abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio, type, desc,
                        new Rates(1, 1, 1, 1, "A"), evYield, abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio, type, desc, rates,
                        new EffortValueYield(5, 5, 5, 5, 5, 5), abilities, baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio, type, desc, rates, evYield,
                        new Abilities("B", "A"), baseStats, eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio, type, desc, rates, evYield, abilities,
                        new BaseStats(5, 5, 5, 5, 5, 5), eggGroups)
        ));
        assertFalse(one.equals(
                new PokemonSpecies(0, name, ratio, type, desc, rates, evYield, abilities, baseStats,
                        new EggGroups(EggGroupTypes.AMORPHOUS, false))
        ));

    }

}
