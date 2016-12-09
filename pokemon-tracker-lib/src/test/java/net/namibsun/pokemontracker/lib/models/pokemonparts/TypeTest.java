package net.namibsun.pokemontracker.lib.models.pokemonparts;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;
import net.namibsun.pokemontracker.lib.serebii.SerebiiConstants;


public class TypeTest {

    @Test
    public void generateMonotypePokemonFromSerebii() throws IOException {
        Type type = Type.fromSerebiiPage(new SerebiiParser("Charmander"));
        assertEquals(SerebiiConstants.PokemonType.FIRE, type.getPrimaryType());
        assertEquals("FIRE", type.getPrimaryTypeAsString());
        assertEquals(null, type.getSecondaryType());
        assertEquals(null, type.getSecondaryTypeAsString());
        assertFalse(type.isDualTyped());
    }

    @Test
    public void generateMultitypePokemonFromSerebii() throws IOException {
        Type type = Type.fromSerebiiPage(new SerebiiParser("Bulbasaur"));
        assertEquals(SerebiiConstants.PokemonType.GRASS, type.getPrimaryType());
        assertEquals("GRASS", type.getPrimaryTypeAsString());
        assertEquals(SerebiiConstants.PokemonType.POISON, type.getSecondaryType());
        assertEquals("POISON", type.getSecondaryTypeAsString());
        assertTrue(type.isDualTyped());
    }

}
