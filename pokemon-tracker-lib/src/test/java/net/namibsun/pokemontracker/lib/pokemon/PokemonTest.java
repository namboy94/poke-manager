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

import net.namibsun.pokemontracker.lib.pokemon.enums.*;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Ability;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Move;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual.*;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class PokemonTest {

    @Test
    public void testEquality() throws IOException {
        PokemonSpecies species = new PokemonSpecies(1, new SerebiiParser(1));
        EffortValueSpread evSpread = new EffortValueSpread(1,1,1,1,1,1);
        IndividualValueSpread ivSpread = new IndividualValueSpread(1,1,1,1,1,1);
        Ability ability = new Ability("A", "B", false);
        Gender gender = new Gender(GenderTypes.MALE);
        HeldItem heldItem = new HeldItem("Item", "Desc");
        Level level = new Level(1);
        MoveSet moveSet = new MoveSet(new Move("A", "B", DamageCategories.PHYSICAL, PokemonTypes.BUG,
                100, 100, 20));
        Nature nature = new Nature(Natures.ADAMANT);
        Origin origin = new Origin();
        OriginalTrainer trainer = new OriginalTrainer("A", "123");
        Notes notes = new Notes("ABC");
        Pokeball pokeball = new Pokeball();
        Pokerus pokerus = new Pokerus();

        Pokemon pokemon = new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                moveSet, nature, notes, origin, trainer, pokeball, pokerus);

        assertTrue(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(
                        new PokemonSpecies(2, new SerebiiParser(2)),
                        evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, new EffortValueSpread(1,1,1,1,1,5), ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, new IndividualValueSpread(1,1,1,1,1,5), ability, gender, heldItem, level,
                        moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, new Ability("X", "Y", true),
                        gender, heldItem, level, moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, new Gender(GenderTypes.FEMALE), heldItem, level,
                        moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, new HeldItem("A", "B"),
                        level, moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, new Level(2),
                        moveSet, nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        new MoveSet(new Move("A", "B", DamageCategories.SPECIAL, PokemonTypes.GRASS,
                                100, 80,  30)),
                        nature, notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, new Nature(Natures.MODEST), notes, origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, new Notes(), origin, trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, notes, new Origin(Games.RED), trainer, pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, notes, origin, new OriginalTrainer("Z", "111"), pokeball, pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, notes, origin, trainer, new Pokeball(Pokeballs.MASTERBALL), pokerus)));

        assertFalse(pokemon.equals(
                new Pokemon(species, evSpread, ivSpread, ability, gender, heldItem, level,
                        moveSet, nature, notes, origin, trainer, pokeball, new Pokerus(PokerusStates.INFECTED))));


    }

}
