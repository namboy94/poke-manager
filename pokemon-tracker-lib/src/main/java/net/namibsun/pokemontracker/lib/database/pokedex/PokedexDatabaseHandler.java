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

import java.sql.SQLException;
import net.namibsun.pokemontracker.lib.models.pokemonparts.*;
import net.namibsun.pokemontracker.lib.models.PokemonSpecies;
import net.namibsun.pokemontracker.lib.models.enums.Languages;
import net.namibsun.pokemontracker.lib.models.enums.EggGroupTypes;
import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;
import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;

/**
 * A Database handler that manages access to the Pokedex table
 */
public class PokedexDatabaseHandler {

    /**
     * The order of the database columns
     */
    private DatabaseColumn[] tableOrder = new DatabaseColumn[] {
        PokedexColumns.POKEDEX_NUMBER,
        PokedexColumns.ENGLISH_NAME,
        PokedexColumns.GERMAN_NAME,
        PokedexColumns.FRENCH_NAME,
        PokedexColumns.JAPANESE_NAME,
        PokedexColumns.KOREAN_NAME,
        PokedexColumns.MALE_RATIO,
        PokedexColumns.FEMALE_RATIO,
        PokedexColumns.PRIMARY_TYPE,
        PokedexColumns.SECONDARY_TYPE,
        PokedexColumns.METRIC_HEIGHT,
        PokedexColumns.METRIC_WEIGHT,
        PokedexColumns.IMPERIAL_HEIGHT,
        PokedexColumns.IMPERIAL_WEIGHT,
        PokedexColumns.CLASSIFICATION,
        PokedexColumns.HP_EV_YIELD,
        PokedexColumns.ATTACK_EV_YIELD,
        PokedexColumns.DEFENSE_EV_YIELD,
        PokedexColumns.SP_ATTACK_EV_YIELD,
        PokedexColumns.SP_DEFENSE_EV_YIELD,
        PokedexColumns.SPEED_EV_YIELD,
        PokedexColumns.PRIMARY_ABILITY,
        PokedexColumns.PRIMARY_ABILITY_DESCRIPTION,
        PokedexColumns.SECONDARY_ABILITY,
        PokedexColumns.SECONDARY_ABILITY_DESCRIPTION,
        PokedexColumns.HIDDEN_ABILITY,
        PokedexColumns.HIDDEN_ABILITY_DESCRIPTION,
        PokedexColumns.HP_BASE,
        PokedexColumns.ATTACK_BASE,
        PokedexColumns.DEFENSE_BASE,
        PokedexColumns.SP_ATTACK_BASE,
        PokedexColumns.SP_DEFENSE_BASE,
        PokedexColumns.SPEED_BASE,
        PokedexColumns.EGG_GROUP_ONE,
        PokedexColumns.EGG_GROUP_TWO,
        PokedexColumns.CAPTURE_RATE,
        PokedexColumns.BASE_EGG_STEPS,
        PokedexColumns.BASE_HAPPINESS,
        PokedexColumns.XP_GROWTH_POINTS,
        PokedexColumns.XP_GROWTH_DESCRIPTION,
        PokedexColumns.HAS_MEGA_EVOLUTION
    };

    /**
     * The Database that the Handler is connected to
     */
    private Database database;

    /**
     * Creates a new Pokedex Database handler
     * @param database: The database to use for SQL statements
     */
    public PokedexDatabaseHandler(Database database) {
        this.database = database;
    }

    /**
     * Retrieves a Pokemon Species from the Pokedex Table
     * @param pokedexNumber: The Pokemon's Pokedex Number
     * @return               The Pokemon Species, or null if it was not found in the database
     * @throws SQLException  If an SQL Error occurred
     */
    public PokemonSpecies getSpeciesFromDatabase(int pokedexNumber) throws SQLException {

        QueryResult query = this.database.query("SELECT * FROM pokedex_data WHERE pokedex_number = " + pokedexNumber);
        if (query.getQueryLength() == 0) {
            return null;
        }
        else {

            String primaryEggGroupString = query.getString(PokedexColumns.EGG_GROUP_ONE.getName(), 0);
            String secondaryEggGroupString = query.getString(PokedexColumns.EGG_GROUP_TWO.getName(), 0);
            EggGroupTypes primaryEggGroup = EggGroupTypes.valueOf(primaryEggGroupString);
            EggGroupTypes secondaryEggGroup;
            try {
                secondaryEggGroup = EggGroupTypes.valueOf(secondaryEggGroupString);
            } catch (NullPointerException e) {
                secondaryEggGroup = null;
            }

            GenderRatio genderRatio = new GenderRatio(
                    query.getDouble(PokedexColumns.MALE_RATIO.getName(), 0),
                    query.getDouble(PokedexColumns.FEMALE_RATIO.getName(), 0)
            );

            return new PokemonSpecies(
                    query.getInt(PokedexColumns.POKEDEX_NUMBER.getName(), 0),
                    new Name(
                            query.getString(PokedexColumns.ENGLISH_NAME.getName(), 0),
                            query.getString(PokedexColumns.GERMAN_NAME.getName(), 0),
                            query.getString(PokedexColumns.FRENCH_NAME.getName(), 0),
                            query.getString(PokedexColumns.JAPANESE_NAME.getName(), 0),
                            query.getString(PokedexColumns.KOREAN_NAME.getName(), 0)
                    ),
                    genderRatio,
                    new Type(
                            query.getString(PokedexColumns.PRIMARY_TYPE.getName(), 0),
                            query.getString(PokedexColumns.SECONDARY_TYPE.getName(), 0)
                    ),
                    new SpeciesDescription(
                            query.getDouble(PokedexColumns.METRIC_WEIGHT.getName(), 0),
                            query.getDouble(PokedexColumns.IMPERIAL_WEIGHT.getName(), 0),
                            query.getDouble(PokedexColumns.METRIC_HEIGHT.getName(), 0),
                            query.getDouble(PokedexColumns.IMPERIAL_HEIGHT.getName(), 0),
                            query.getString(PokedexColumns.CLASSIFICATION.getName(), 0)
                    ),
                    new Rates(
                            query.getInt(PokedexColumns.CAPTURE_RATE.getName(), 0),
                            query.getInt(PokedexColumns.BASE_EGG_STEPS.getName(), 0),
                            query.getInt(PokedexColumns.BASE_HAPPINESS.getName(), 0),
                            query.getInt(PokedexColumns.XP_GROWTH_POINTS.getName(), 0),
                            query.getString(PokedexColumns.XP_GROWTH_DESCRIPTION.getName(), 0)
                    ),
                    new EffortValueYield(
                            query.getInt(PokedexColumns.HP_EV_YIELD.getName(), 0),
                            query.getInt(PokedexColumns.ATTACK_EV_YIELD.getName(), 0),
                            query.getInt(PokedexColumns.DEFENSE_EV_YIELD.getName(), 0),
                            query.getInt(PokedexColumns.SP_ATTACK_EV_YIELD.getName(), 0),
                            query.getInt(PokedexColumns.SP_DEFENSE_EV_YIELD.getName(), 0),
                            query.getInt(PokedexColumns.SPEED_EV_YIELD.getName(), 0)
                    ),
                    new Ability(
                            query.getString(PokedexColumns.PRIMARY_ABILITY.getName(), 0),
                            query.getString(PokedexColumns.PRIMARY_ABILITY_DESCRIPTION.getName(), 0),
                            query.getString(PokedexColumns.SECONDARY_ABILITY.getName(), 0),
                            query.getString(PokedexColumns.SECONDARY_ABILITY_DESCRIPTION.getName(), 0),
                            query.getString(PokedexColumns.HIDDEN_ABILITY.getName(), 0),
                            query.getString(PokedexColumns.HIDDEN_ABILITY_DESCRIPTION.getName(), 0)
                    ),
                    new BaseStats(
                            query.getInt(PokedexColumns.HP_BASE.getName(), 0),
                            query.getInt(PokedexColumns.ATTACK_BASE.getName(), 0),
                            query.getInt(PokedexColumns.DEFENSE_BASE.getName(), 0),
                            query.getInt(PokedexColumns.SP_ATTACK_BASE.getName(), 0),
                            query.getInt(PokedexColumns.SP_DEFENSE_BASE.getName(), 0),
                            query.getInt(PokedexColumns.SPEED_BASE.getName(), 0)
                    ),
                    new EggGroups(
                            primaryEggGroup,
                            secondaryEggGroup,
                            genderRatio.isNeutralGendered()
                    ),
                    new MegaEvolution(
                            query.getBoolean(PokedexColumns.HAS_MEGA_EVOLUTION.getName(),0)
                    )
            );
        }
    }

    /**
     * Stores a Pokemon Species in the database, if it does not already exist
     * @param species: The species to store in the database
     * @throws SQLException  If an SQL Error occurred
     */
    public void storePokemonSpeciesInDatabase(PokemonSpecies species) throws SQLException {

        QueryResult checkIfExists = this.database.query(
                "SELECT * FROM pokedex_data WHERE pokedex_number = " + species.getPokedexNumber());

        if (checkIfExists.getQueryLength() == 0) {

            String tableColumnHeaders = "(";
            for (DatabaseColumn column: this.tableOrder) {
                tableColumnHeaders += column.getName(",");
            }
            tableColumnHeaders = tableColumnHeaders.substring(0, tableColumnHeaders.length() - 1);
            tableColumnHeaders += ")";

            String values = "VALUES (";
            values += species.getPokedexNumber() + ",";
            values += "\"" + species.getName().getName(Languages.ENGLISH) + "\",";
            values += "\"" + species.getName().getName(Languages.GERMAN) + "\",";
            values += "\"" + species.getName().getName(Languages.FRENCH) + "\",";
            values += "\"" + species.getName().getName(Languages.JAPANESE) + "\",";
            values += "\"" + species.getName().getName(Languages.KOREAN) + "\",";
            values += species.getGenderRatio().getMaleRatio() + ",";
            values += species.getGenderRatio().getFemaleRatio() + ",";
            values += "\"" + species.getType().getPrimaryTypeAsString().toUpperCase() + "\",";
            values += "\"" + species.getType().getSecondaryTypeAsString().toUpperCase() + "\",";
            values += species.getSpeciesDescription().getMetricHeight() + ",";
            values += species.getSpeciesDescription().getMetricWeight() + ",";
            values += species.getSpeciesDescription().getImperialHeight() + ",";
            values += species.getSpeciesDescription().getImperialWeight() + ",";
            values += "\"" + species.getSpeciesDescription().getClassification() + "\",";
            values += species.getEffortValueYield().getHpYield() + ",";
            values += species.getEffortValueYield().getAttackYield() + ",";
            values += species.getEffortValueYield().getDefenseYield() + ",";
            values += species.getEffortValueYield().getSpAttackYield() + ",";
            values += species.getEffortValueYield().getSpDefenseYield() + ",";
            values += species.getEffortValueYield().getSpeedYield() + ",";
            values += "\"" + species.getAbility().getAbilityOne()[0] + "\",";
            values += "\"" + species.getAbility().getAbilityOne()[1] + "\",";
            if (species.getAbility().getAbilityTwo() != null) {
                values += "\"" + species.getAbility().getAbilityTwo()[0] + "\",";
                values += "\"" + species.getAbility().getAbilityTwo()[1] + "\",";
            }
            else {
                values += "NULL,NULL,";
            }
            if (species.getAbility().getHiddenAbility() != null) {
                values += "\"" + species.getAbility().getHiddenAbility()[0] + "\",";
                values += "\"" + species.getAbility().getHiddenAbility()[1] + "\",";
            }
            else {
                values += "NULL,NULL,";
            }
            values += species.getBaseStats().getHp() + ",";
            values += species.getBaseStats().getAttack() + ",";
            values += species.getBaseStats().getDefense() + ",";
            values += species.getBaseStats().getSpecialAttack() + ",";
            values += species.getBaseStats().getSpecialDefense() + ",";
            values += species.getBaseStats().getSpeed() + ",";
            values += "\"" + species.getEggGroups().getPrimaryEggGroup().name() + "\",";
            if (species.getEggGroups().getSecondaryEggGroup() != null) {
                values += "\"" + species.getEggGroups().getSecondaryEggGroup().name() + "\",";
            }
            else {
                values += "NULL,";
            }
            values += species.getRates().getCaptureRate() + ",";
            values += species.getRates().getBaseEggSteps() + ",";
            values += species.getRates().getBaseHappiness() + ",";
            values += species.getRates().getExperienceGrowthPoints() + ",";
            values += "\"" + species.getRates().getExperienceGrowthDescription() + "\",";
            values += "\"" + species.getMegaEvolution().hasMegaEvolution() + "\");";

            String sql = "INSERT INTO pokedex_data " + tableColumnHeaders + " " + values;

            this.database.executeSql(sql);
            this.database.commitChanges();
        }
    }

    /**
     * Creates the Pokedex Table
     * @throws SQLException  If an SQL Error occurred
     */
    public void createPokedexTable() throws SQLException {

        String sql = "CREATE TABLE if not exists pokedex_data (";
        for (DatabaseColumn column: this.tableOrder) {
            sql += column.getColumn(",");
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ");";

        this.database.executeSql(sql);
        this.database.commitChanges();

    }
}
