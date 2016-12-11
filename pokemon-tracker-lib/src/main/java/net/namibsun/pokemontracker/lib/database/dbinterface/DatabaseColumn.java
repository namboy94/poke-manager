package net.namibsun.pokemontracker.lib.database.dbinterface;

public class DatabaseColumn {

    private String name;
    private String type;

    public DatabaseColumn(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getColumn() {
        return this.name + " " + this.type;
    }

    public String getColumn(String end) {
        return this.getColumn() + end;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

}
