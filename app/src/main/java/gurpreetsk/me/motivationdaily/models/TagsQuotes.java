package gurpreetsk.me.motivationdaily.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class TagsQuotes implements Parcelable {

    public String AuthorName;
    public String Quote;

    public TagsQuotes() {}

    public TagsQuotes(String AuthorName, String Quote) {
        this.AuthorName = AuthorName;
        this.Quote = Quote;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String Quote) {
        this.Quote = Quote;
    }

    public TagsQuotes(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        this.AuthorName = data[0];
        this.Quote = data[1];
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.AuthorName
                , this.Quote});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TagsQuotes createFromParcel(Parcel in) {
            return new TagsQuotes(in);
        }

        public TagsQuotes[] newArray(int size) {
            return new TagsQuotes[size];
        }
    };

}
