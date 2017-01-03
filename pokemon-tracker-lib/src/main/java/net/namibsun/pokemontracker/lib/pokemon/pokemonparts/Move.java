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

package net.namibsun.pokemontracker.lib.pokemon.pokemonparts;

import net.namibsun.pokemontracker.lib.pokemon.enums.DamageCategories;
import net.namibsun.pokemontracker.lib.pokemon.enums.PokemonTypes;

/**
 * Class that models a Pokemon move
 */
public class Move {

    /**
     * The Move's name
     */
    private String name;

    /**
     * The Move's description
     */
    private String description;

    /**
     * The damage category of the move
     */
    private DamageCategories damageCategory;

    /**
     * The Base Damage of the Move
     */
    private int baseDamage;

    /**
     * The Accuracy of the Move
     */
    private int accuracy;

    /**
     * The Move's amount of Power Points
     */
    private int powerPoints;

    /**
     * The move's type
     */
    private PokemonTypes type;

    /**
     * Creates a new Move Object
     * @param name:           The name of the Move
     * @param description:    The description of the Move
     * @param damageCategory: The damage category of the move
     * @param type:           The move's type
     * @param baseDamage:     The move's base damage
     * @param accuracy:       The move's accuracy
     * @param powerPoints:    The move's amount of PP
     */
    public Move(String name, String description, DamageCategories damageCategory,
                PokemonTypes type, int baseDamage, int accuracy, int powerPoints) {
        this.name = name;
        this.description = description;
        this.damageCategory = damageCategory;
        this.type = type;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
        this.powerPoints = powerPoints;
    }

    /**
     * @return the Move's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the Move's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the Move's damage category
     */
    public DamageCategories getDamageCategory() {
        return this.damageCategory;
    }

    /**
     * @return the Move's damage category
     */
    public String getDamageCategoryAsString() {
        return this.damageCategory.name();
    }

    /**
     * @return the Move's type
     */
    public PokemonTypes getType() {
        return this.type;
    }

    /**
     * @return the Move's type as String
     */
    public String getTypeAsString() {
        return this.type.name();
    }

    /**
     * @return The move's base damage
     */
    public int getBaseDamage() {
        return this.baseDamage;
    }

    /**
     * @return The move's accuracy
     */
    public int getAccuracy() {
        return this.accuracy;
    }

    /**
     * @return The move's Power Points
     */
    public int getPowerPoints() {
        return this.powerPoints;
    }

    /**
     * Adds an amount of Power Points to the move
     * @param additional: The amount of power points to add
     */
    public void addPowerPoints(int additional) {
        this.powerPoints += additional;
    }

    /**
     * Compares two Move objects
     * @param otherMove: The other Move
     * @return           true if equal, false if otherwise
     */
    public boolean equals(Move otherMove) {
        return  this.name.equals(otherMove.getName()) &&
                this.description.equals(otherMove.getDescription()) &&
                this.damageCategory == otherMove.getDamageCategory() &&
                this.type == otherMove.getType() &&
                this.baseDamage == otherMove.getBaseDamage() &&
                this.accuracy == otherMove.getAccuracy() &&
                this.powerPoints == otherMove.getPowerPoints();
    }

}
