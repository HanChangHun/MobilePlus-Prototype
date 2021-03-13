package android.content.pm.parsing.component;

import android.os.Parcel;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.List;

public class ListParceler implements Parcelling<List<ParsedIntentInfo>> {
  public void parcel(List<ParsedIntentInfo> paramList, Parcel paramParcel, int paramInt) {
    if (paramList == null) {
      paramParcel.writeInt(-1);
      return;
    } 
    int i = paramList.size();
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++)
      ParsedIntentInfo.PARCELER.parcel(paramList.get(b), paramParcel, paramInt); 
  }
  
  public List<ParsedIntentInfo> unparcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i == -1)
      return null; 
    if (i == 0)
      return new ArrayList<>(0); 
    ArrayList<ParsedIntentInfo> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(ParsedIntentInfo.PARCELER.unparcel(paramParcel)); 
    return arrayList;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedIntentInfo$ListParceler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */