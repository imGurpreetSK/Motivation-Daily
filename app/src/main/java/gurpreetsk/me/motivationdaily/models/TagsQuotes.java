package gurpreetsk.me.motivationdaily.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class TagsQuotes implements Parcelable {

    public String authorName;
    public String quote;

    public TagsQuotes(String authorName, String quote) {
        this.authorName = authorName;
        this.quote = quote;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getQuote() {
        return quote;
    }

    public TagsQuotes(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        this.authorName = data[0];
        this.quote = data[1];
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.authorName
                , this.quote});
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
