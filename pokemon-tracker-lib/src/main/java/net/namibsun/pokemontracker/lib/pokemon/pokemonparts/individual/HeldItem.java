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


/**
 * A class that models the held item of a Pokemon
 */
public class HeldItem {

    /**
     * The Held Item's name
     */
    private String itemName = "";

    /**
     * The Held Item's description
     */
    private String itemDescription = "";

    /**
     * Creates a new Held Item Object. If null values are passed, or even empty Strings, the Pokemon will be considered
     * to have no held item at all
     * @param itemName:        The item's name
     * @param itemDescription: The item's description
     */
    public HeldItem(String itemName, String itemDescription) {

        this.itemName = itemName;
        this.itemDescription = itemDescription;

        if (itemName == null || itemName.equals("")) {

            if (itemDescription != null && !itemDescription.equals("")) {
                throw new IllegalArgumentException("A Held item must declare a description if an item name was set");
            }
            else {
                this.itemName = "";
                this.itemDescription = "";
            }
        } else if (itemDescription == null || itemDescription.equals("")) {
            throw new IllegalArgumentException("A Held item must declare an item name if an item description was set");
        }
    }

    /**
     * @return True if the Pokemon has a held item or false if not
     */
    public boolean hasHeldItem() {
        return !this.itemName.equals("");
    }

    /**
     * @return The held item's name, or an empty string if the Pokemon has no held item
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * @return The held item's description, or an empty string if the Pokemon has no held item
     */
    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * Checks if two held item objects are equal to one another
     * @param otherHeldItem: The held item to compare against
     * @return               true if equal, false if not
     */
    public boolean equals(HeldItem otherHeldItem) {
        return this.itemName.equals(otherHeldItem.getItemName())
                && this.itemDescription.equals(otherHeldItem.getItemDescription());
    }

}
