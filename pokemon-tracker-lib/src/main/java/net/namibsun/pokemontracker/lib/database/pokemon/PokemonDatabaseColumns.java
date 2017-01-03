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

package net.namibsun.pokemontracker.lib.database.pokemon;

import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;

/**
 * The definition of the columns used by the Pokemon Database Handler
 */
public class PokemonDatabaseColumns {

    public static DatabaseColumn POKEDEX_NUMBER = new DatabaseColumn("pokedex_number", "Integer");
    public static DatabaseColumn EV_HP = new DatabaseColumn("ev_hp", "Integer");
    public static DatabaseColumn EV_ATTACK = new DatabaseColumn("ev_atk", "Integer");
    public static DatabaseColumn EV_DEFENSE = new DatabaseColumn("ev_def", "Integer");
    public static DatabaseColumn EV_SPECIAL_ATTACK = new DatabaseColumn("ev_satk", "Integer");
    public static DatabaseColumn EV_SPECIAL_DEFENSE = new DatabaseColumn("ev_sdef", "Integer");
    public static DatabaseColumn EV_SPEED = new DatabaseColumn("ev_spd", "Integer");
    public static DatabaseColumn IV_HP = new DatabaseColumn("iv_hp", "Integer");
    public static DatabaseColumn IV_ATTACK = new DatabaseColumn("iv_atk", "Integer");
    public static DatabaseColumn IV_DEFENSE = new DatabaseColumn("iv_def", "Integer");
    public static DatabaseColumn IV_SPECIAL_ATTACK = new DatabaseColumn("iv_satk", "Integer");
    public static DatabaseColumn IV_SPECIAL_DEFENSE = new DatabaseColumn("iv_sdef", "Integer");
    public static DatabaseColumn IV_SPEED = new DatabaseColumn("iv_spd", "Integer");
    public static DatabaseColumn ABILITY = new DatabaseColumn("ability_name", "Text");
    public static DatabaseColumn GENDER = new DatabaseColumn("gender", "Text");
    public static DatabaseColumn HELDITEM_NAME = new DatabaseColumn("held_item_name", "Text");
    public static DatabaseColumn HELDITEM_DESCRIPTION = new DatabaseColumn("held_item_desc", "Text");
    public static DatabaseColumn LEVEL = new DatabaseColumn("level", "Integer");
    public static DatabaseColumn MOVE_ONE_NAME = new DatabaseColumn("move_one_name", "Text");
    public static DatabaseColumn MOVE_ONE_PP = new DatabaseColumn("move_one_pp", "Integer");
    public static DatabaseColumn MOVE_TWO_NAME = new DatabaseColumn("move_two_name", "Text");
    public static DatabaseColumn MOVE_TWO_PP = new DatabaseColumn("move_two_pp", "Integer");
    public static DatabaseColumn MOVE_THREE_NAME = new DatabaseColumn("move_three_name", "Text");
    public static DatabaseColumn MOVE_THREE_PP = new DatabaseColumn("move_three_pp", "Integer");
    public static DatabaseColumn MOVE_FOUR_NAME = new DatabaseColumn("move_four_name", "Text");
    public static DatabaseColumn MOVE_FOUR_PP = new DatabaseColumn("move_four_pp", "Integer");
    public static DatabaseColumn NATURE = new DatabaseColumn("nature", "Text");
    public static DatabaseColumn ORIGIN_GENERATION = new DatabaseColumn("origin_generation", "Text");
    public static DatabaseColumn ORIGIN_GAME = new DatabaseColumn("origin_game", "Text");
    public static DatabaseColumn OT_NAME = new DatabaseColumn("original_trainer_name", "Text");
    public static DatabaseColumn OT_ID = new DatabaseColumn("original_trainer_id", "Text");
    public static DatabaseColumn NOTES = new DatabaseColumn("notes", "Text");
    public static DatabaseColumn POKEBALL = new DatabaseColumn("pokeball", "Text");
    public static DatabaseColumn POKERUS = new DatabaseColumn("pokerus", "Text");

}
