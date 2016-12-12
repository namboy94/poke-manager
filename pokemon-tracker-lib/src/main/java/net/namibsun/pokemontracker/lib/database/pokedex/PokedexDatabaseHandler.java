package net.namibsun.pokemontracker.lib.database.pokedex;

import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;
import net.namibsun.pokemontracker.lib.models.PokemonSpecies;
import net.namibsun.pokemontracker.lib.models.enums.Languages;

import java.sql.SQLException;


public class PokedexDatabaseHandler {

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


    private Database database;

    public PokedexDatabaseHandler(Database database) {
        this.database = database;
    }

    public PokemonSpecies getSpeciesFromDatabase(int pokedexNumber) throws SQLException {

        QueryResult query = this.database.query("SELECT * FROM pokedex_data WHERE pokedex_number = " + pokedexNumber);
        if (query.getQueryLength() == 0) {
            return null;
        }
        else {
            //return new PokemonSpecies();
        }
        return null;


    }

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
            values += "\"" + species.getType().getPrimaryTypeAsString() + "\",";
            values += "\"" + species.getType().getSecondaryTypeAsString() + "\",";
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
