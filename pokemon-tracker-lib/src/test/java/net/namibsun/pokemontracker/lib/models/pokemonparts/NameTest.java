package net.namibsun.pokemontracker.lib.models.pokemonparts;

import org.junit.Test;
import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;
import net.namibsun.pokemontracker.lib.serebii.SerebiiConstants;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class NameTest{

    @Test
    public void testCreatingNameAndGettingNameValue() {

        Name name = new Name("TestName", "TestLanguage");
        assertEquals("TestName", name.getName("TestLanguage"));

    }

    @Test
    public void testGettingPokemonNamesFromSerebii() throws IOException {
        SerebiiParser parser = new SerebiiParser("Bulbasaur");
        Name name = Name.fromSerebiiPage(parser);

        assertEquals("Bulbasaur", name.getName(SerebiiConstants.ENGLISH_KEY));
        assertEquals("Bisasam", name.getName(SerebiiConstants.GERMAN_KEY));
        assertEquals("Bulbizarre", name.getName(SerebiiConstants.FRENCH_KEY));
        assertEquals("Fushigidane フシギダネ", name.getName(SerebiiConstants.JAPANESE_KEY));
        assertEquals("이상해씨", name.getName(SerebiiConstants.KOREAN_KEY));

    }

    @Test
    public void testMultiLanguageConstructor() {

        Name name = new Name("A", "B", "C", "D", "E");

        assertEquals("A", name.getName(SerebiiConstants.ENGLISH_KEY));
        assertEquals("B", name.getName(SerebiiConstants.FRENCH_KEY));
        assertEquals("C", name.getName(SerebiiConstants.GERMAN_KEY));
        assertEquals("D", name.getName(SerebiiConstants.JAPANESE_KEY));
        assertEquals("E", name.getName(SerebiiConstants.KOREAN_KEY));

    }

    @Test
    public void testGettingInvalidLanguage(){

        Name name = new Name("TestName", "TestLanguage");
        assertEquals(name.getName("NotExistingLanguage"), "Missingno.");

    }
}
