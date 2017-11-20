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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Move;

/**
 * Class that models the active Move Set of a Pokemon
 */
public class MoveSet {

    /**
     * The four moves the Pokemon currently knows
     */
    private Move moveOne;
    private Move moveTwo;
    private Move moveThree;
    private Move moveFour;

    /**
     * Creates a new MoveSet
     * @param moveOne:   The first move
     * @throws IllegalArgumentException if the passed moves did not pass the checks
     */
    public MoveSet(Move moveOne) {
        this(moveOne, null, null, null);
    }

    /**
     * Creates a new MoveSet
     * @param moveOne:   The first move
     * @param moveTwo:   The second move
     * @throws IllegalArgumentException if the passed moves did not pass the checks
     */
    public MoveSet(Move moveOne, Move moveTwo) {
        this(moveOne, moveTwo, null, null);
    }

    /**
     * Creates a new MoveSet
     * @param moveOne:   The first move
     * @param moveTwo:   The second move
     * @param moveThree: The third move
     * @throws IllegalArgumentException if the passed moves did not pass the checks
     */
    public MoveSet(Move moveOne, Move moveTwo, Move moveThree) {
        this(moveOne, moveTwo, moveThree, null);
    }

    /**
     * Creates a new MoveSet.
     * - Move must be unique
     * - If move is null, the next ones must also be null, otherwise the order is wrong
     * - moveOne must always be not null
     * @param moveOne:   The first move
     * @param moveTwo:   The second move
     * @param moveThree: The third move
     * @param moveFour:  The fourth move
     * @throws IllegalArgumentException if the passed moves did not pass the checks
     */
    public MoveSet(Move moveOne, Move moveTwo, Move moveThree, Move moveFour) {

        if (moveOne == null) {
            if (moveTwo != null || moveThree != null || moveFour != null) {
                throw new IllegalArgumentException("Moves must be in order");
            }
            else {
                throw new IllegalArgumentException("MoveSet must contain at least one move");
            }
        }
        else if ((moveTwo == null && (moveThree != null || moveFour != null)) ||
                 (moveThree == null && moveFour != null)) {
            throw new IllegalArgumentException("Moves must be in order");
        }
        else if (isEqual(moveOne, moveTwo, true) ||
                 isEqual(moveOne, moveThree, true) ||
                 isEqual(moveOne, moveFour, true) ||
                 isEqual(moveTwo, moveThree, true) ||
                 isEqual(moveTwo, moveFour, true) ||
                 isEqual(moveThree, moveFour, true)) {
            throw new IllegalArgumentException("Moves must be unique");
        }
        else {
            this.moveOne = moveOne;
            this.moveTwo = moveTwo;
            this.moveThree = moveThree;
            this.moveFour = moveFour;
        }
    }

    /**
     * Helper method that checks objects that may be null for equality.
     * Optionally considers null values to never be the same
     * @param one:            The first object to compare
     * @param two:            The second object to compare
     * @param nullNeverEqual: Specifies if null values can be considered the same as each other
     * @return                true if the objects are equal, otherwise false
     */
    private boolean isEqual(Object one, Object two, boolean nullNeverEqual) {
        if (one == null || two == null) {
            return !nullNeverEqual && (one == two);
        }
        else {
            return one.equals(two);
        }
    }

    /**
     * @return The MoveSet's first move
     */
    public Move getMoveOne() {
        return this.moveOne;
    }

    /**
     * @return The MoveSet's second move
     */
    public Move getMoveTwo() {
        return this.moveTwo;
    }

    /**
     * @return The MoveSet's third move
     */
    public Move getMoveThree() {
        return this.moveThree;
    }

    /**
     * @return The MoveSet's fourth move
     */
    public Move getMoveFour() {
        return this.moveFour;
    }

    /**
     * Checks two Movesets for equality
     * @param otherMoveSet: The other MoveSet
     * @return              true if equal, else false
     */
    public boolean equals(MoveSet otherMoveSet) {
        return  this.isEqual(moveOne, otherMoveSet.getMoveOne(), false) &&
                this.isEqual(moveTwo, otherMoveSet.getMoveTwo(), false) &&
                this.isEqual(moveThree, otherMoveSet.getMoveThree(), false) &&
                this.isEqual(moveFour, otherMoveSet.getMoveFour(), false);
    }

}
