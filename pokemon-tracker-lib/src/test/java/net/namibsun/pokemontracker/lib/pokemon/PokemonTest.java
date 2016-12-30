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

package net.namibsun.pokemontracker.lib.pokemon;

import net.namibsun.pokemontracker.lib.pokemon.enums.DamageCategories;
import net.namibsun.pokemontracker.lib.pokemon.enums.PokemonTypes;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Move;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.*;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.pokemon.enums.GenderTypes;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class PokemonTest {

    @Test
    public void testGeneratingFromWebparsedSpecies() throws IOException {
        PokemonSpecies species = new PokemonSpecies(1, new SerebiiParser(1));
        Pokemon bulbasaur = new Pokemon(
                species,
                new EffortValueSpread(12,2,1,4,1,2),
                new IndividualValueSpread(1,2,1,3,1,3),
                species.getAbilities().getAbilityOne(),
                new Gender(GenderTypes.MALE),
                new HeldItem("Testitem", "An item for testing"),
                new Level(1),
                new MoveSet(new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG))
        );

        assertTrue(species.equals(bulbasaur.getSpecies()));
    }

}
