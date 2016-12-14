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

package net.namibsun.pokemontracker.lib.species.pokemonparts;

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
     * Constructor that creates a new Rates object
     * @param captureRate:                 The Pokemon's capture rate
     * @param baseEggSteps:                The Pokemon's base egg steps
     * @param baseHappiness:               The Pokemon's base happiness
     * @param experienceGrowthPoints:      The Pokemon's total XP at level 100
     * @param experienceGrowthDescription: The Pokemon's growth speed description
     */
    public Rates(int captureRate, int baseEggSteps, int baseHappiness,
                 int experienceGrowthPoints, String experienceGrowthDescription) {
        this.captureRate = captureRate;
        this.baseEggSteps = baseEggSteps;
        this.baseHappiness = baseHappiness;
        this.experienceGrowthPoints = experienceGrowthPoints;
        this.experienceGrowthDescription = experienceGrowthDescription;
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
     * Creates a new Rates object from a Web parser's information
     * @param parser: The parser to use
     * @return        The generates Rates object
     */
    public static Rates fromWebParser(PokemonScraper parser) {
        return new Rates(
                parser.parseCaptureRate(),
                parser.parseBaseEggSteps(),
                parser.parseBaseHappiness(),
                parser.parseExperienceGrowthPoints(),
                parser.parseExperienceGrowthDescription());
    }

    /**
     * Compares two Rates objects
     * @param otherRates: The other object to compare against
     * @return            true if they are equal, false otherwise
     */
    public boolean equals(Rates otherRates) {
        return  this.captureRate == otherRates.getCaptureRate() &&
                this.baseEggSteps == otherRates.getBaseEggSteps() &&
                this.baseHappiness == otherRates.getBaseHappiness() &&
                this.experienceGrowthPoints == otherRates.getExperienceGrowthPoints() &&
                this.experienceGrowthDescription.equals(otherRates.getExperienceGrowthDescription());
    }

}
