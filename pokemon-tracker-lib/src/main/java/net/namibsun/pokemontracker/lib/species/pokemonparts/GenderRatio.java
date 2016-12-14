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
    private boolean neutralGendered = true;

    /**
     * Constructor for a Pokemon that has genders
     * @param maleRatio:   The male ratio of Pokemon of that species
     * @param femaleRatio: The female ratio of Pokemon of that species
     */
    public GenderRatio(double maleRatio, double femaleRatio) {
        this.maleRatio = maleRatio;
        this.femaleRatio = femaleRatio;

        if (maleRatio != 0.0 && femaleRatio != 0.0) {
            this.neutralGendered = false;
        }
    }

    /**
     * Constructor for a Pokemon that has a neutral gender
     */
    public GenderRatio() {
        this.maleRatio = 0.0;
        this.femaleRatio = 0.0;
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
     * Creates a new GenderRatio object based on a Web page parser
     * @param parser: The parser to use
     * @return        A new GenderRatio object
     */
    public static GenderRatio fromWebParser(PokemonScraper parser) {
        double[] parsedRatio = parser.parseGenderRatio();
        if (parsedRatio == null) {
            return new GenderRatio();
        }
        else {
            return new GenderRatio(parsedRatio[0], parsedRatio[1]);
        }
    }

    /**
     * Compares two GenderRatio objects
     * @param otherRatio: The other GenderRatio object to compare against
     * @return            true, if the objects are equal, false otherwise
     */
    public boolean equals(GenderRatio otherRatio) {
        return  this.maleRatio == otherRatio.getMaleRatio() &&
                this.femaleRatio == otherRatio.getFemaleRatio();
    }

}
