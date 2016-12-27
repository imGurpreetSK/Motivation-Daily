package gurpreetsk.me.motivationdaily.data;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

/**
 * Created by Gurpreet on 25/12/16.
 */

@SimpleSQLTable(
        table = "Quotes",
        provider = "QuotesProvider"
)

public class Database {

    @SimpleSQLColumn(value = TableStructure.COLUMN_ID, primary = true, autoincrement = true)
    public int id;

    @SimpleSQLColumn(value = TableStructure.COLUMN_AUTHOR)
    public String authorName;

    @SimpleSQLColumn(value = TableStructure.COLUMN_QUOTE)
    public String quote;

}
