package net.namibsun.pokemontracker.lib.database.pokemon;

import net.namibsun.pokemontracker.lib.database.dbinterface.Database;
import net.namibsun.pokemontracker.lib.database.dbinterface.DatabaseColumn;

import java.sql.SQLException;

/**
 * Handles inserting and getting Pokemon into the database into different tables
 */
public class PokemonDatabaseHandler {

    private DatabaseColumn[] columnOrder = new DatabaseColumn[] {
            PokemonDatabaseColumns.POKEDEX_NUMBER,
            PokemonDatabaseColumns.EV_HP,
            PokemonDatabaseColumns.EV_ATTACK,
            PokemonDatabaseColumns.EV_DEFENSE,
            PokemonDatabaseColumns.EV_SPECIAL_ATTACK,
            PokemonDatabaseColumns.EV_SPECIAL_DEFENSE,
            PokemonDatabaseColumns.EV_SPEED,
            PokemonDatabaseColumns.IV_HP,
            PokemonDatabaseColumns.IV_ATTACK,
            PokemonDatabaseColumns.IV_DEFENSE,
            PokemonDatabaseColumns.IV_SPECIAL_ATTACK,
            PokemonDatabaseColumns.IV_SPECIAL_DEFENSE,
            PokemonDatabaseColumns.IV_SPEED,
            PokemonDatabaseColumns.ABILITY,
            PokemonDatabaseColumns.GENDER,
            PokemonDatabaseColumns.HELDITEM_NAME,
            PokemonDatabaseColumns.HELDITEM_DESCRIPTION,
            PokemonDatabaseColumns.LEVEL,
            PokemonDatabaseColumns.MOVE_ONE_NAME,
            PokemonDatabaseColumns.MOVE_ONE_PP,
            PokemonDatabaseColumns.MOVE_TWO_NAME,
            PokemonDatabaseColumns.MOVE_TWO_PP,
            PokemonDatabaseColumns.MOVE_THREE_NAME,
            PokemonDatabaseColumns.MOVE_THREE_PP,
            PokemonDatabaseColumns.MOVE_FOUR_NAME,
            PokemonDatabaseColumns.MOVE_FOUR_PP,
            PokemonDatabaseColumns.NATURE,
            PokemonDatabaseColumns.ORIGIN_GENERATION,
            PokemonDatabaseColumns.ORIGIN_GAME,
            PokemonDatabaseColumns.OT_NAME,
            PokemonDatabaseColumns.OT_ID,
            PokemonDatabaseColumns.NOTES,
            PokemonDatabaseColumns.POKEBALL,
            PokemonDatabaseColumns.POKERUS
    };

    private Database database;
    private String table;

    public PokemonDatabaseHandler(Database database, String table) {
        this.database = database;
        this.table = table;
    }

    /**
     * Creates the Pokemon Table
     * @throws SQLException  If an SQL Error occurred
     */
    public void createPokedexTable() throws SQLException {
        this.database.createTable(this.table, this.columnOrder);
        this.database.commitChanges();
    }

}
