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

package net.namibsun.pokemontracker.lib.database;

import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.models.PokemonSpecies;
import java.sql.SQLException;

public class DatabaseHandler {



    public static void storePokemonSpecies(PokemonSpecies species) {

        String sqlStatement = "INSERT INTO pokedex_data (pokedex)";

    }


    public static void createPokedexTable(Database database) throws SQLException {

        String sql = "CREATE TABLE if not exists pokedex_data (" +
                "pokedex_number Integer," +
                "english_name Text," +
                "german_name Text," +
                "french_name Text," +
                "japanese_name Text," +
                "korean_name Text," +
                "male_ratio Float," +
                "female_ratio Float," +
                "primary_type Text," +
                "secondary_type Text," +
                "metric_height Float," +
                "metric_weight Float," +
                "imperial_height Float," +
                "imperial_weight Float," +
                "classification Text," +
                "hp_ev_yield Integer," +
                "attack_ev_yield Integer," +
                "defense_ev_yield Integer," +
                "sp_attack_yield Integer," +
                "sp_defense_yield Integer," +
                "speed_yield Integer," +
                "primary_ability Text," +
                "primary_ability_description Text" +
                "secondary_ability Text," +
                "secondary_ability_description Text," +
                "hidden_ability Text," +
                "hidden_ability_description Text," +
                "hp_base Integer," +
                "attack_base Integer," +
                "defense_base Integer," +
                "sp_attack_base Integer," +
                "sp_defense_base Integer," +
                "speed_base Integer," +
                "egg_group_one Text," +
                "egg_group_two Text," +
                "has_mega_evolution Boolean);";

        database.executeSql(sql);
        database.commitChanges();

    }



}
