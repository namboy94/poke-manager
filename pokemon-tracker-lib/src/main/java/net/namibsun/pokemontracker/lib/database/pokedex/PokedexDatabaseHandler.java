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

import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.Ability;
import net.namibsun.pokemontracker.lib.pokemon.pokemonparts.species.*;
import net.namibsun.pokemontracker.lib.pokemon.PokemonSpecies;
import net.namibsun.pokemontracker.lib.pokemon.enums.Languages;
import net.namibsun.pokemontracker.lib.pokemon.enums.EggGroupTypes;
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
     * Closes the connected database
     * @throws SQLException: If an SQL error occured
     */
    public void quit() throws SQLException {
        this.database.close();
    }

    /**
     * Retrieves a Pokemon Species from the Pokedex Table
     * @param pokedexNumber: The Pokemon's Pokedex Number
     * @return               The Pokemon Species, or null if it was not found in the database
     * @throws SQLException  If an SQL Error occurred
     */
    public PokemonSpecies getSpeciesFromDatabase(int pokedexNumber) throws SQLException {


        QueryResult query = this.database.query(
                "SELECT * FROM pokedex_data WHERE pokedex_number=?", new String[]{"" + pokedexNumber});
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
            } catch (IllegalArgumentException | NullPointerException e) {
                secondaryEggGroup = null;
            }

            GenderRatio genderRatio = new GenderRatio(
                    query.getDouble(PokedexColumns.MALE_RATIO.getName(), 0),
                    query.getDouble(PokedexColumns.FEMALE_RATIO.getName(), 0)
            );

            Type type;
            try {
                type = new Type(
                        query.getString(PokedexColumns.PRIMARY_TYPE.getName(), 0),
                        query.getString(PokedexColumns.SECONDARY_TYPE.getName(), 0)
                );
            } catch (IllegalArgumentException | NullPointerException e) {
                type = new Type(
                        query.getString(PokedexColumns.PRIMARY_TYPE.getName(), 0)
                );
            }

            Ability primaryAbility = new Ability(
                    query.getString(PokedexColumns.PRIMARY_ABILITY.getName(), 0),
                    query.getString(PokedexColumns.PRIMARY_ABILITY_DESCRIPTION.getName(), 0),
                    false
            );

            Ability secondaryAbility = new Ability(
                    query.getString(PokedexColumns.SECONDARY_ABILITY.getName(), 0),
                    query.getString(PokedexColumns.SECONDARY_ABILITY_DESCRIPTION.getName(), 0),
                    false
            );
            if (secondaryAbility.getName() == null) {
                secondaryAbility = null;
            }

            Ability hiddenAbility = new Ability(
                    query.getString(PokedexColumns.HIDDEN_ABILITY.getName(), 0),
                    query.getString(PokedexColumns.HIDDEN_ABILITY_DESCRIPTION.getName(), 0),
                    true
            );
            if (hiddenAbility.getName() == null) {
                hiddenAbility = null;
            }

            return new PokemonSpecies(
                    query.getInt(PokedexColumns.POKEDEX_NUMBER.getName(), 0),
                    new Name(
                            query.getString(PokedexColumns.ENGLISH_NAME.getName(), 0),
                            query.getString(PokedexColumns.FRENCH_NAME.getName(), 0),
                            query.getString(PokedexColumns.GERMAN_NAME.getName(), 0),
                            query.getString(PokedexColumns.JAPANESE_NAME.getName(), 0),
                            query.getString(PokedexColumns.KOREAN_NAME.getName(), 0)
                    ),
                    genderRatio,
                    type,
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
                    new Abilities(primaryAbility, secondaryAbility, hiddenAbility),
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
                "SELECT * FROM pokedex_data WHERE pokedex_number=?",
                new String[]{"" + species.getPokedexNumber()});

        if (checkIfExists.getQueryLength() == 0) {

            String secondaryType = species.getType().getSecondaryTypeAsString();
            if (secondaryType != null) {
                secondaryType = secondaryType.toUpperCase();
            }

            String secondaryEggGroup;
            if (species.getEggGroups().getSecondaryEggGroup() != null) {
                secondaryEggGroup = species.getEggGroups().getSecondaryEggGroup().name();
            }
            else {
                secondaryEggGroup = null;
            }

            String secondAbilityName = null;
            String secondAbilityDescription = null;
            String hiddenAbilityName = null;
            String hiddenAbilityDescription = null;

            if (species.getAbilities().getAbilityTwo() != null) {
                secondAbilityName = species.getAbilities().getAbilityTwo().getName();
                secondAbilityDescription = species.getAbilities().getAbilityTwo().getDescription();
            }
            if (species.getAbilities().getHiddenAbility() != null) {
                hiddenAbilityName = species.getAbilities().getHiddenAbility().getName();
                hiddenAbilityDescription = species.getAbilities().getHiddenAbility().getDescription();
            }

            Object[] values = new Object[] {
                    species.getPokedexNumber(),
                    species.getName().getName(Languages.ENGLISH),
                    species.getName().getName(Languages.GERMAN),
                    species.getName().getName(Languages.FRENCH),
                    species.getName().getName(Languages.JAPANESE),
                    species.getName().getName(Languages.KOREAN),
                    species.getGenderRatio().getMaleRatio(),
                    species.getGenderRatio().getFemaleRatio(),
                    species.getType().getPrimaryTypeAsString().toUpperCase(),
                    secondaryType,
                    species.getSpeciesDescription().getMetricHeight(),
                    species.getSpeciesDescription().getMetricWeight(),
                    species.getSpeciesDescription().getImperialHeight(),
                    species.getSpeciesDescription().getImperialWeight(),
                    species.getSpeciesDescription().getClassification(),
                    species.getEffortValueYield().getHp(),
                    species.getEffortValueYield().getAttack(),
                    species.getEffortValueYield().getDefense(),
                    species.getEffortValueYield().getSpecialAttack(),
                    species.getEffortValueYield().getSpecialDefense(),
                    species.getEffortValueYield().getSpeed(),
                    species.getAbilities().getAbilityOne().getName(),
                    species.getAbilities().getAbilityOne().getDescription(),
                    secondAbilityName,
                    secondAbilityDescription,
                    hiddenAbilityName,
                    hiddenAbilityDescription,
                    species.getBaseStats().getHp(),
                    species.getBaseStats().getAttack(),
                    species.getBaseStats().getDefense(),
                    species.getBaseStats().getSpecialAttack(),
                    species.getBaseStats().getSpecialDefense(),
                    species.getBaseStats().getSpeed(),
                    species.getEggGroups().getPrimaryEggGroup().name(),
                    secondaryEggGroup,
                    species.getRates().getCaptureRate(),
                    species.getRates().getBaseEggSteps(),
                    species.getRates().getBaseHappiness(),
                    species.getRates().getExperienceGrowthPoints(),
                    species.getRates().getExperienceGrowthDescription()

            };

            this.database.insert("pokedex_data", this.tableOrder, values);
            this.database.commitChanges();
        }
    }

    /**
     * Creates the Pokedex Table
     * @throws SQLException  If an SQL Error occurred
     */
    public void createPokedexTable() throws SQLException {
        this.database.createTable("pokedex_data", this.tableOrder);
        this.database.commitChanges();
    }
}
