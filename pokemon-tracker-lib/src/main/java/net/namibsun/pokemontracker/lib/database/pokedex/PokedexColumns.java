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

package net.namibsun.pokemontracker.lib.database.pokedex;

import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;

/**
 * Class that defines the columns of the Pokedex Table
 */
public class PokedexColumns {

    /**
     * The Various Database Columns
     */
    public static DatabaseColumn POKEDEX_NUMBER = new DatabaseColumn("pokedex_number", "Integer");
    public static DatabaseColumn ENGLISH_NAME = new DatabaseColumn("english_name", "Text");
    public static DatabaseColumn GERMAN_NAME = new DatabaseColumn("german_name", "Text");
    public static DatabaseColumn FRENCH_NAME = new DatabaseColumn("french_name", "Text");
    public static DatabaseColumn JAPANESE_NAME= new DatabaseColumn("japanese_name", "Text");
    public static DatabaseColumn KOREAN_NAME = new DatabaseColumn("korean_name", "Text");
    public static DatabaseColumn MALE_RATIO = new DatabaseColumn("male_ratio", "Double");
    public static DatabaseColumn FEMALE_RATIO = new DatabaseColumn("female_ratio", "Double");
    public static DatabaseColumn PRIMARY_TYPE = new DatabaseColumn("primary_type", "Integer");
    public static DatabaseColumn SECONDARY_TYPE = new DatabaseColumn("secondary_type", "Text");
    public static DatabaseColumn METRIC_HEIGHT = new DatabaseColumn("metric_height", "Double");
    public static DatabaseColumn METRIC_WEIGHT = new DatabaseColumn("metric_weight", "Double");
    public static DatabaseColumn IMPERIAL_HEIGHT = new DatabaseColumn("imperial_height", "Double");
    public static DatabaseColumn IMPERIAL_WEIGHT = new DatabaseColumn("imperial_weight", "Double");
    public static DatabaseColumn CLASSIFICATION = new DatabaseColumn("classification", "Text");
    public static DatabaseColumn HP_EV_YIELD = new DatabaseColumn("hp_ev_yield", "Integer");
    public static DatabaseColumn ATTACK_EV_YIELD = new DatabaseColumn("attack_ev_yield", "Integer");
    public static DatabaseColumn DEFENSE_EV_YIELD = new DatabaseColumn("defense_ev_yield", "Integer");
    public static DatabaseColumn SP_ATTACK_EV_YIELD = new DatabaseColumn("sp_attack_ev_yield", "Integer");
    public static DatabaseColumn SP_DEFENSE_EV_YIELD = new DatabaseColumn("sp_defense_ev_yield", "Integer");
    public static DatabaseColumn SPEED_EV_YIELD = new DatabaseColumn("speed_ev_yield", "Integer");
    public static DatabaseColumn PRIMARY_ABILITY = new DatabaseColumn("primary_ability", "Text");
    public static DatabaseColumn PRIMARY_ABILITY_DESCRIPTION = new DatabaseColumn("primary_ability_description", "Text");
    public static DatabaseColumn SECONDARY_ABILITY = new DatabaseColumn("secondary_ability", "Text");
    public static DatabaseColumn SECONDARY_ABILITY_DESCRIPTION = new DatabaseColumn("secondary_ability_description", "Text");
    public static DatabaseColumn HIDDEN_ABILITY = new DatabaseColumn("hidden_ability", "TEXT");
    public static DatabaseColumn HIDDEN_ABILITY_DESCRIPTION = new DatabaseColumn("hidden_ability_description", "TEXT");
    public static DatabaseColumn HP_BASE = new DatabaseColumn("hp_base", "Integer");
    public static DatabaseColumn ATTACK_BASE = new DatabaseColumn("attack_base", "Integer");
    public static DatabaseColumn DEFENSE_BASE = new DatabaseColumn("defense_base", "Integer");
    public static DatabaseColumn SP_ATTACK_BASE = new DatabaseColumn("sp_attack_base", "Integer");
    public static DatabaseColumn SP_DEFENSE_BASE = new DatabaseColumn("sp_defense_base", "Integer");
    public static DatabaseColumn SPEED_BASE = new DatabaseColumn("speed_base", "Integer");
    public static DatabaseColumn EGG_GROUP_ONE = new DatabaseColumn("egg_group_one", "Text");
    public static DatabaseColumn EGG_GROUP_TWO = new DatabaseColumn("egg_group_two", "Text");
    public static DatabaseColumn CAPTURE_RATE = new DatabaseColumn("capture_rate", "Integer");
    public static DatabaseColumn BASE_EGG_STEPS = new DatabaseColumn("base_egg_steps", "Integer");
    public static DatabaseColumn BASE_HAPPINESS = new DatabaseColumn("base_happiness", "Integer");
    public static DatabaseColumn XP_GROWTH_POINTS = new DatabaseColumn("xp_growth_points", "Integer");
    public static DatabaseColumn XP_GROWTH_DESCRIPTION = new DatabaseColumn("xp_growth_description", "Text");

}
