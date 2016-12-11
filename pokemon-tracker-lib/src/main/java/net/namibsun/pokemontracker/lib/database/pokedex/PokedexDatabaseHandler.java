package net.namibsun.pokemontracker.lib.database.pokedex;

import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.dbinterface.QueryResult;
import net.namibsun.pokemontracker.lib.models.PokemonSpecies;

import java.sql.SQLException;


public class PokedexDatabaseHandler {

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


    }

    public void storePokemonSpeciesInDatabase(PokemonSpecies species) throws SQLException {

        QueryResult checkIfExists = this.database.query(
                "SELECT * FROM pokedex_data WHERE pokedex_number = " + species.getPokedexNumber());

        if (checkIfExists.getQueryLength() == 0) {

            String sql = "INSERT INTO pokedex_data () VALUES ();";

            this.database.executeSql(sql);
            this.database.commitChanges();
        }
    }


    public void createPokedexTable() throws SQLException {

        String sql =
                "CREATE TABLE if not exists pokedex_data (" +
                        PokedexColumns.POKEDEX_NUMBER.getColumn(",") +
                        PokedexColumns.ENGLISH_NAME.getColumn(",") +
                        PokedexColumns.GERMAN_NAME.getColumn(",") +
                        PokedexColumns.FRENCH_NAME.getColumn(",") +
                        PokedexColumns.JAPANESE_NAME.getColumn(",") +
                        PokedexColumns.KOREAN_NAME.getColumn(",") +
                        PokedexColumns.MALE_RATIO.getColumn(",") +
                        PokedexColumns.FEMALE_RATIO.getColumn(",") +
                        PokedexColumns.PRIMARY_TYPE.getColumn(",") +
                        PokedexColumns.SECONDARY_TYPE.getColumn(",") +
                        PokedexColumns.METRIC_HEIGHT.getColumn(",") +
                        PokedexColumns.METRIC_WEIGHT.getColumn(",") +
                        PokedexColumns.IMPERIAL_HEIGHT.getColumn(",") +
                        PokedexColumns.IMPERIAL_WEIGHT.getColumn(",") +
                        PokedexColumns.CLASSIFICATION.getColumn(",") +
                        PokedexColumns.HP_EV_YIELD.getColumn(",") +
                        PokedexColumns.ATTACK_EV_YIELD.getColumn(",") +
                        PokedexColumns.DEFENSE_EV_YIELD.getColumn(",") +
                        PokedexColumns.SP_ATTACK_EV_YIELD.getColumn(",") +
                        PokedexColumns.SP_DEFENSE_EV_YIELD.getColumn(",") +
                        PokedexColumns.SPEED_EV_YIELD.getColumn(",") +
                        PokedexColumns.PRIMARY_ABILITY.getColumn(",") +
                        PokedexColumns.PRIMARY_ABILITY_DESCRIPTION.getColumn(",") +
                        PokedexColumns.SECONDARY_ABILITY.getColumn(",") +
                        PokedexColumns.SECONDARY_ABILITY_DESCRIPTION.getColumn(",") +
                        PokedexColumns.HIDDEN_ABILITY.getColumn(",") +
                        PokedexColumns.HIDDEN_ABILITY_DESCRIPTION.getColumn(",") +
                        PokedexColumns.HP_BASE.getColumn(",") +
                        PokedexColumns.ATTACK_BASE.getColumn(",") +
                        PokedexColumns.DEFENSE_BASE.getColumn(",") +
                        PokedexColumns.SP_ATTACK_BASE.getColumn(",") +
                        PokedexColumns.SP_DEFENSE_BASE.getColumn(",") +
                        PokedexColumns.SPEED_BASE.getColumn(",") +
                        PokedexColumns.EGG_GROUP_ONE.getColumn(",") +
                        PokedexColumns.EGG_GROUP_TWO.getColumn(",") +
                        PokedexColumns.CAPTURE_RATE.getColumn(",") +
                        PokedexColumns.BASE_EGG_STEPS.getColumn(",") +
                        PokedexColumns.BASE_HAPPINESS.getColumn(",") +
                        PokedexColumns.XP_GROWTH_POINTS.getColumn(",") +
                        PokedexColumns.XP_GROWTH_DESCRIPTION.getColumn(",") +
                        PokedexColumns.HAS_MEGA_EVOLUTION.getColumn() +
                        ");";

        this.database.executeSql(sql);
        this.database.commitChanges();

    }
}
