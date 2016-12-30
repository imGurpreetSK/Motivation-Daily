package gurpreetsk.me.motivationdaily.models;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class DailyQuotes {

    String quote;
    String author;

    public DailyQuotes() {
    }

    public DailyQuotes(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
