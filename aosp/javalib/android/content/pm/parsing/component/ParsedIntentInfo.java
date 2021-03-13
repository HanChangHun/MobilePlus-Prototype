package android.content.pm.parsing.component;

import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.android.internal.util.Parcelling;
import java.util.ArrayList;
import java.util.List;

public final class ParsedIntentInfo extends IntentFilter {
  public static final Parcelable.Creator<ParsedIntentInfo> CREATOR;
  
  public static final Parceler PARCELER = new Parceler();
  
  boolean hasDefault;
  
  int icon;
  
  int labelRes;
  
  CharSequence nonLocalizedLabel;
  
  static {
    CREATOR = new Parcelable.Creator<ParsedIntentInfo>() {
        public ParsedIntentInfo createFromParcel(Parcel param1Parcel) {
          return new ParsedIntentInfo(param1Parcel);
        }
        
        public ParsedIntentInfo[] newArray(int param1Int) {
          return new ParsedIntentInfo[param1Int];
        }
      };
  }
  
  public ParsedIntentInfo() {}
  
  public ParsedIntentInfo(Parcel paramParcel) {
    super(paramParcel);
    this.hasDefault = paramParcel.readBoolean();
    this.labelRes = paramParcel.readInt();
    this.nonLocalizedLabel = paramParcel.readCharSequence();
    this.icon = paramParcel.readInt();
  }
  
  public int getIcon() {
    return this.icon;
  }
  
  public int getLabelRes() {
    return this.labelRes;
  }
  
  public CharSequence getNonLocalizedLabel() {
    return this.nonLocalizedLabel;
  }
  
  public boolean isHasDefault() {
    return this.hasDefault;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ProviderIntentInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeIntentInfoToParcel(Parcel paramParcel, int paramInt) {
    writeToParcel(paramParcel, paramInt);
    paramParcel.writeBoolean(this.hasDefault);
    paramParcel.writeInt(this.labelRes);
    paramParcel.writeCharSequence(this.nonLocalizedLabel);
    paramParcel.writeInt(this.icon);
  }
  
  public static class ListParceler implements Parcelling<List<ParsedIntentInfo>> {
    public void parcel(List<ParsedIntentInfo> param1List, Parcel param1Parcel, int param1Int) {
      if (param1List == null) {
        param1Parcel.writeInt(-1);
        return;
      } 
      int i = param1List.size();
      param1Parcel.writeInt(i);
      for (byte b = 0; b < i; b++)
        ParsedIntentInfo.PARCELER.parcel(param1List.get(b), param1Parcel, param1Int); 
    }
    
    public List<ParsedIntentInfo> unparcel(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      if (i == -1)
        return null; 
      if (i == 0)
        return new ArrayList<>(0); 
      ArrayList<ParsedIntentInfo> arrayList = new ArrayList(i);
      for (byte b = 0; b < i; b++)
        arrayList.add(ParsedIntentInfo.PARCELER.unparcel(param1Parcel)); 
      return arrayList;
    }
  }
  
  public static class Parceler implements Parcelling<ParsedIntentInfo> {
    public void parcel(ParsedIntentInfo param1ParsedIntentInfo, Parcel param1Parcel, int param1Int) {
      param1ParsedIntentInfo.writeIntentInfoToParcel(param1Parcel, param1Int);
    }
    
    public ParsedIntentInfo unparcel(Parcel param1Parcel) {
      return new ParsedIntentInfo(param1Parcel);
    }
  }
  
  public static class StringPairListParceler implements Parcelling<List<Pair<String, ParsedIntentInfo>>> {
    public void parcel(List<Pair<String, ParsedIntentInfo>> param1List, Parcel param1Parcel, int param1Int) {
      if (param1List == null) {
        param1Parcel.writeInt(-1);
        return;
      } 
      int i = param1List.size();
      param1Parcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        Pair pair = param1List.get(b);
        param1Parcel.writeString((String)pair.first);
        ParsedIntentInfo.PARCELER.parcel((ParsedIntentInfo)pair.second, param1Parcel, param1Int);
      } 
    }
    
    public List<Pair<String, ParsedIntentInfo>> unparcel(Parcel param1Parcel) {
      int i = param1Parcel.readInt();
      if (i == -1)
        return null; 
      if (i == 0)
        return new ArrayList<>(0); 
      ArrayList<Pair> arrayList = new ArrayList(i);
      for (byte b = 0; b < i; b++)
        arrayList.add(Pair.create(param1Parcel.readString(), ParsedIntentInfo.PARCELER.unparcel(param1Parcel))); 
      return (List)arrayList;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedIntentInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */