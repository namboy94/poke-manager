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

import net.namibsun.pokemontracker.lib.pokemon.enums.Games;
import net.namibsun.pokemontracker.lib.pokemon.enums.Generations;


public class Origin {

    /**
     * The Origin Game of the Pokemon
     */
    private Games originGame;

    /**
     * The Origin Generation of the Pokemon
     */
    private Generations originGeneration;

    /**
     * Creates a new Origin object, where the game and generation are both unknown
     */
    public Origin() {
        this(Games.UNKNOWN, Generations.UNKNOWN);
    }

    /**
     * Creates a new Origin object where the Game is known. The generation is inferred by the game.
     * @param game: The origin game of the Pokemon
     */
    public Origin(Games game) {
        this.originGame = game;
        this.originGeneration = this.getGenerationFromGame(game);
    }

    /**
     * Creates a new Origin object where only the Generation is known
     * @param generation: The origin generation of the Pokemon
     */
    public Origin(Generations generation) {
        this(Games.UNKNOWN, generation);
    }

    /**
     * Creates a new Origin object
     * @param game:       The origin game
     * @param generation: The origin generation
     */
    public Origin(Games game, Generations generation) {
        this.originGame = game;
        this.originGeneration = generation;
    }

    /**
     * Maps a game to a Generation
     * @param game: The game to map
     * @return      The resulting generation
     */
    private Generations getGenerationFromGame(Games game) {
        if (game == Games.RED || game == Games.GREEN || game == Games.BLUE || game == Games.YELLOW) {
            return Generations.I;
        }
        else if (game == Games.SILVER || game == Games.GOLD || game == Games.CRYSTAL) {
            return Generations.II;
        }
        else if (game == Games.RUBY || game == Games.SAPPHIRE || game == Games.EMERALD || game == Games.FIRERED ||
                 game == Games.LEAFGREEN || game == Games.XD || game == Games.COLOSSEUM) {
            return Generations.III;
        }
        else if (game == Games.PEARL || game == Games.DIAMOND || game == Games.PLATINUM ||
                 game == Games.HEARTGOLD || game == Games.SOULSILVER) {
            return Generations.IV;
        }
        else if (game == Games.BLACK || game == Games.WHITE || game == Games.BLACK2 || game == Games.WHITE2) {
            return Generations.V;
        }
        else if (game == Games.X || game == Games.Y || game == Games.OMEGARUBY || game == Games.ALPHASAPPHIRE) {
            return Generations.VI;
        }
        else if (game == Games.SUN || game == Games.MOON) {
            return Generations.VII;
        }
        else {
            return Generations.UNKNOWN;
        }
    }

    /**
     * @return The Origin Game
     */
    public Games getOriginGame() {
        return this.originGame;
    }

    /**
     * @return The Origin Generation
     */
    public Generations getOriginGeneration() {
        return this.originGeneration;
    }

    /**
     * Checks two Origin objects for equality
     * @param otherOrigin: The other Origin object
     * @return             true if equal, else false
     */
    public boolean equals(Origin otherOrigin) {
        return  this.originGame == otherOrigin.getOriginGame() &&
                this.originGeneration == otherOrigin.getOriginGeneration();
    }

}
