package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.serebii.SerebiiParser;

/**
 * Class that represents the Gender Ratio for a specific Pokemon
 */
public class GenderRatio {

    /**
     * The relative ratio of males in the species of Pokemon
     */
    private double maleRatio;

    /**
     * The relative ratio of females in the species of Pokemon
     */
    private double  femaleRatio;

    /**
     * Indicator for a Pokemon being genderless
     */
    private boolean neutralGendered;

    /**
     * Constructor for a Pokemon that has genders
     * @param maleRatio:   The male ratio of Pokemon of that species
     * @param femaleRatio: The female ratio of Pokemon of that species
     */
    public GenderRatio(double maleRatio, double femaleRatio) {
        this.maleRatio = maleRatio;
        this.femaleRatio = femaleRatio;
        this.neutralGendered = false;
    }

    /**
     * Constructor for a Pokemon that has a neutral gender
     */
    public GenderRatio() {
        this.maleRatio = 0.0;
        this.femaleRatio = 0.0;
        this.neutralGendered = true;
    }

    /**
     * @return true if the Pokemon is neutrally gendered, or false if the Pokemon has an actual gender
     */
    public boolean isNeutralGendered() {
        return this.neutralGendered;
    }

    /**
     * @return The male ratio of the species
     */
    public double getMaleRatio() {
        return this.maleRatio;
    }

    /**
     * @return The female ratio of the species
     */
    public double getFemaleRatio() {
        return this.femaleRatio;
    }

    /**
     * Creates a new GenderRatio object based on a Serebii page
     * @param parser: The parser to use
     * @return        A new GenderRatio object
     */
    public static GenderRatio fromSerebiiPage(SerebiiParser parser) {
        double[] parsedRatio = parser.parseGenderRatio();
        if (parsedRatio == null) {
            return new GenderRatio();
        }
        else {
            return new GenderRatio(parsedRatio[0], parsedRatio[1]);
        }
    }

}
