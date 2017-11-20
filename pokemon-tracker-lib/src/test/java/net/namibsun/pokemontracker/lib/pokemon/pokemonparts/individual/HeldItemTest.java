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

public class HeldItemTest {

    @Test
    public void testNormalHeldItem() {
        HeldItem item = new HeldItem("item", "desc");
        assertEquals(item.getItemName(), "item");
        assertEquals(item.getItemDescription(), "desc");
        assertTrue(item.hasHeldItem());
    }

    @Test
    public void testNullParameters() {

        HeldItem item = new HeldItem(null, null);
        checkNoHeldItem(item);
        item = new HeldItem("", "");
        checkNoHeldItem(item);
        item = new HeldItem(null, "");
        checkNoHeldItem(item);
        item = new HeldItem("", null);
        checkNoHeldItem(item);

    }

    public void checkNoHeldItem(HeldItem item) {
        assertEquals(item.getItemName(), "");
        assertEquals(item.getItemDescription(), "");
        assertFalse(item.hasHeldItem());
    }

    @Test
    public void testIllegalArguments() {
        try {
            new HeldItem("A", "");
            fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new HeldItem("A", null);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new HeldItem(null, "B");
            fail();
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new HeldItem("", "B");
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testPositiveEquality() {
        assertTrue(new HeldItem("A", "B").equals(new HeldItem("A", "B")));
        assertTrue(new HeldItem("", null).equals(new HeldItem(null, null)));
    }

    @Test
    public void testNegativeEquality() {
        assertFalse(new HeldItem("A", "B").equals(new HeldItem("B", "A")));
        assertFalse(new HeldItem("A", "A").equals(new HeldItem("B", "A")));
        assertFalse(new HeldItem("A", "A").equals(new HeldItem("A", "B")));
        assertFalse(new HeldItem("A", "B").equals(new HeldItem("", "")));
    }

}
