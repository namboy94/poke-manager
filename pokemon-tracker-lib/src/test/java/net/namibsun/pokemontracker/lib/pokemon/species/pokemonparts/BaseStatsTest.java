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

package net.namibsun.pokemontracker.lib.pokemon.species.pokemonparts;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
import net.namibsun.pokemontracker.lib.webscraping.serebii.SerebiiParser;

public class BaseStatsTest {

    @Test
    public void testGeneratingBaseStatsFromWebParser() throws IOException {
        BaseStats stats = BaseStats.fromWebParser(new SerebiiParser("Bulbasaur"));
        assertEquals(45, stats.getHp());
        assertEquals(49, stats.getAttack());
        assertEquals(49, stats.getDefense());
        assertEquals(65, stats.getSpecialAttack());
        assertEquals(65, stats.getSpecialDefense());
        assertEquals(45, stats.getSpeed());
    }

    @Test
    public void testRangeLimitsAbove() {
        try {
            BaseStats stats = new BaseStats(50,50,50,50,50,256);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (NumberFormatException ignored) {
        }
    }

    @Test
    public void testRangeLimitsBelow() {
        try {
            BaseStats stats = new BaseStats(50,50,50,50,50,0);
            //noinspection ConstantConditions
            assertTrue(false);
        } catch (NumberFormatException ignored) {
        }
    }

    @Test
    public void testValidRange() {
        try {
            BaseStats stats = new BaseStats(1,1,1,255,255,255);
        } catch (NumberFormatException e) {
            //noinspection ConstantConditions
            assertTrue(false);
        }
    }



}
