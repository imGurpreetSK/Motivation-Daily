package gurpreetsk.me.motivationdaily.data;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

/**
 * Created by Gurpreet on 25/12/16.
 */

@SimpleSQLConfig(
        name = "QuotesProvider",
        authority = "authority:gurpreetsk.me.motivationdaily",
        database = "quotes.db",
        version = 1
)

public class QuotesProvider implements ProviderConfig {
    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
