package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.models.enums.PokemonStatTypes;
import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that stores various Pokemon rates, for example the capture rate
 */
public class Rates {

    /**
     * The Pokemon's capture rate
     */
    private int captureRate;

    /**
     * The amount of steps it takes to hatch an Egg containing this Pokemon species
     */
    private int baseEggSteps;

    /**
     * The Pokemon's base happiness value
     */
    private int baseHappiness;

    /**
     * The total amount of experience points needed for this Pokemon species to reach level 100
     */
    private int experienceGrowthPoints;

    /**
     * A description of the speed this Pokemon species grows in term of XP.
     */
    private String experienceGrowthDescription;

    /**
     * The amount of effort values gained when defeating this Pokemon.
     */
    private int effortValuesGained;

    /**
     * The type of effort value stat is gained when defeating this Pokemon species
     */
    private PokemonStatTypes effortValueType;

    /**
     * Constructor that creates a new Rates object
     * @param captureRate:                 The Pokemon's capture rate
     * @param baseEggSteps:                The Pokemon's base egg steps
     * @param baseHappiness:               The Pokemon's base happiness
     * @param experienceGrowthPoints:      The Pokemon's total XP at level 100
     * @param experienceGrowthDescription: The Pokemon's growth speed description
     * @param effortValuesGained:          The Pokemon's EV gain amount
     * @param effortValueType:             The Pokemon's EV gain type
     */
    public Rates(int captureRate, int baseEggSteps, int baseHappiness,
                 int experienceGrowthPoints, String experienceGrowthDescription,
                 int effortValuesGained, PokemonStatTypes effortValueType) {
        this.captureRate = captureRate;
        this.baseEggSteps = baseEggSteps;
        this.baseHappiness = baseHappiness;
        this.experienceGrowthPoints = experienceGrowthPoints;
        this.experienceGrowthDescription = experienceGrowthDescription;
        this.effortValuesGained = effortValuesGained;
        this.effortValueType = effortValueType;
    }

    /**
     * @return The Pokemon's capture rate
     */
    public int getCaptureRate(){
        return this.captureRate;
    }

    /**
     * @return The Pokemon's base egg steps
     */
    public int getBaseEggSteps() {
        return this.baseEggSteps;
    }

    /**
     * @return The Pokemon's base happiness
     */
    public int getBaseHappiness() {
        return this.baseHappiness;
    }

    /**
     * @return The Pokemon's XP at level 100
     */
    public int getExperienceGrowthPoints() {
        return this.experienceGrowthPoints;
    }

    /**
     * @return The Pokemon's growth speed description
     */
    public String getExperienceGrowthDescription() {
        return this.experienceGrowthDescription;
    }

    /**
     * @return The Pokemon's effort value gain amount
     */
    public int getEffortValuesGained() {
        return this.effortValuesGained;
    }

    /**
     * @return The Pokemon's effort value gain type
     */
    public PokemonStatTypes getEffortValueType() {
        return this.effortValueType;
    }

    /**
     * Creates a new Rates object from a Web parser's information
     * @param parser: The parser to use
     * @return        The generates Rates object
     */
    public static Rates fromWebParser(PokemonScraper parser) {
        return new Rates(
                parser.parseCaptureRate(),
                parser.parseBaseEggSteps(),
                parser.parseBaseHappiness(),
                parser.parserExperienceGrowthPoints(),
                parser.parseExperienceGrowthDescription(),
                parser.parseEffortValueGainedAmount(),
                parser.parseEffortValueGainedType());
    }

}
