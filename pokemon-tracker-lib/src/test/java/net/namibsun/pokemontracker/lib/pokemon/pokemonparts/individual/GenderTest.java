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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts.individual;

import org.junit.Test;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.pokemon.enums.individual.GenderTypes;

public class GenderTest {

    @Test
    public void testMaleGenderCreation() {
        Gender gender = new Gender(GenderTypes.MALE);
        assertEquals(gender.getGender(), GenderTypes.MALE);
        assertEquals(gender.getGenderString(), "MALE");
        assertEquals(gender.getGenderSymbol(), "♂");
    }

    @Test
    public void testFemaleGenderCreation() {
        Gender gender = new Gender(GenderTypes.FEMALE);
        assertEquals(gender.getGender(), GenderTypes.FEMALE);
        assertEquals(gender.getGenderString(), "FEMALE");
        assertEquals(gender.getGenderSymbol(), "♀");
    }

    @Test
    public void testNeutralGenderCreation() {
        Gender gender = new Gender(GenderTypes.NEUTRAL);
        assertEquals(gender.getGender(), GenderTypes.NEUTRAL);
        assertEquals(gender.getGenderString(), "NEUTRAL");
        assertEquals(gender.getGenderSymbol(), "-");
    }

    @Test
    public void checkTrueEquality() {
        assertTrue(new Gender(GenderTypes.FEMALE).equals(new Gender(GenderTypes.FEMALE)));
        assertTrue(new Gender(GenderTypes.MALE).equals(new Gender(GenderTypes.MALE)));
        assertTrue(new Gender(GenderTypes.NEUTRAL).equals(new Gender(GenderTypes.NEUTRAL)));
    }

    @Test
    public void checkFalseEquality() {
        assertFalse(new Gender(GenderTypes.FEMALE).equals(new Gender(GenderTypes.MALE)));
        assertFalse(new Gender(GenderTypes.MALE).equals(new Gender(GenderTypes.NEUTRAL)));
        assertFalse(new Gender(GenderTypes.NEUTRAL).equals(new Gender(GenderTypes.FEMALE)));
    }

}