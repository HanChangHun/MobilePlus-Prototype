package android.content.pm.parsing.component;

import android.os.Parcel;
import com.android.internal.util.Parcelling;

public class Parceler implements Parcelling<ParsedIntentInfo> {
  public void parcel(ParsedIntentInfo paramParsedIntentInfo, Parcel paramParcel, int paramInt) {
    paramParsedIntentInfo.writeIntentInfoToParcel(paramParcel, paramInt);
  }
  
  public ParsedIntentInfo unparcel(Parcel paramParcel) {
    return new ParsedIntentInfo(paramParcel);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedIntentInfo$Parceler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */