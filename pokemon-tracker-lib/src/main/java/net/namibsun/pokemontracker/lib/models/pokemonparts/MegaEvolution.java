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

package net.namibsun.pokemontracker.lib.models.pokemonparts;

import net.namibsun.pokemontracker.lib.webscraping.PokemonScraper;

/**
 * Class that models the Mega Evolution of a Pokemon
 */
public class MegaEvolution {

    /**
     * Variables that stores if the Pokemon has a Mega Evolution or not
     */
    private boolean megaEvolutionExists;

    /**
     * Creates a new MegaEvolution object
     * @param megaEvolutionExists: true if the Pokemon has a Mega Evolution, false otherwise
     */
    public MegaEvolution(boolean megaEvolutionExists) {
        this.megaEvolutionExists = megaEvolutionExists;
    }

    /**
     * @return true if the Pokemon has a Mega Evolution, false otherwise
     */
    public boolean hasMegaEvolution() {
        return this.megaEvolutionExists;
    }

    /**
     * Creates a new MegaEvolution object from the information of a Web Parser
     * @param parser: The parser to use
     * @return        The generated Mega Evolution object
     */
    public static MegaEvolution fromWebParser(PokemonScraper parser) {
        return new MegaEvolution(parser.parseHasMegaEvolution());
    }

    /**
     * Compares two MegaEvolution objects
     * @param otherMegaEvolution: The other MegaEvolution object
     * @return                    true if equal, false otherwise
     */
    public boolean equals(MegaEvolution otherMegaEvolution) {
        return this.megaEvolutionExists && otherMegaEvolution.hasMegaEvolution();
    }

}
