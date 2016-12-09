package net.namibsun.pokemontracker.lib.models.pokemonparts;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;
import net.namibsun.pokemontracker.lib.serebii.SerebiiConstants;


public class SpeciesDescriptionTest {

    @Test
    public void testGeneratingFromSerebii() throws IOException {

        SerebiiParser parser = new SerebiiParser("Bulbasaur");
        SpeciesDescription description = SpeciesDescription.fromSerebiiPage(parser);

        assertEquals(15.2, description.getImperialWeight(), 0.0);
        assertEquals(6.9, description.getMetricWeight(), 0.0);
        assertEquals(2.04, description.getImperialHeight(), 0.0);
        assertEquals(0.7, description.getMetricHeight(), 0.0);
        assertEquals("Seed Pokémon", description.getClassification());

    }

}
