package android.content.pm.parsing.component;

import android.os.Parcel;
import android.util.Pair;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.List;

public class StringPairListParceler implements Parcelling<List<Pair<String, ParsedIntentInfo>>> {
  public void parcel(List<Pair<String, ParsedIntentInfo>> paramList, Parcel paramParcel, int paramInt) {
    if (paramList == null) {
      paramParcel.writeInt(-1);
      return;
    } 
    int i = paramList.size();
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++) {
      Pair pair = paramList.get(b);
      paramParcel.writeString((String)pair.first);
      ParsedIntentInfo.PARCELER.parcel((ParsedIntentInfo)pair.second, paramParcel, paramInt);
    } 
  }
  
  public List<Pair<String, ParsedIntentInfo>> unparcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i == -1)
      return null; 
    if (i == 0)
      return new ArrayList<>(0); 
    ArrayList<Pair> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(Pair.create(paramParcel.readString(), ParsedIntentInfo.PARCELER.unparcel(paramParcel))); 
    return (List)arrayList;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedIntentInfo$StringPairListParceler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */