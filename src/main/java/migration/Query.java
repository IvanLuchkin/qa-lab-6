package migration;

public enum Query {
    NEW_USER_UP_MIGRATION("ALTER TABLE \"user\" ADD COLUMN surname varchar(255) not null default 'obruch';"),

    USER_TABLE_DOWN_MIGRATION("ALTER TABLE \"user\" drop COLUMN surname;"),

    NEW_CAR_COLUMN_UP_MIGRATION("ALTER TABLE \"car\" ADD COLUMN model varchar(255) not null default 'M5';"),

    CAR_TABLE_DOWN_MIGRATION("ALTER TABLE \"car\" drop COLUMN model;");

    public String query;

    Query(String query) {
        this.query = query;
    }
}