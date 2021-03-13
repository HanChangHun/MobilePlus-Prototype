package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.annotation.StringRes;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.android.internal.util.AnnotationValidations;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class ParsedAttribution implements Parcelable {
  public static final Parcelable.Creator<ParsedAttribution> CREATOR = new Parcelable.Creator<ParsedAttribution>() {
      public ParsedAttribution createFromParcel(Parcel param1Parcel) {
        return new ParsedAttribution(param1Parcel);
      }
      
      public ParsedAttribution[] newArray(int param1Int) {
        return new ParsedAttribution[param1Int];
      }
    };
  
  public static final int MAX_ATTRIBUTION_TAG_LEN = 50;
  
  private static final int MAX_NUM_ATTRIBUTIONS = 1000;
  
  public final List<String> inheritFrom;
  
  public final int label;
  
  public final String tag;
  
  protected ParsedAttribution(Parcel paramParcel) {
    String str = paramParcel.readString();
    int i = paramParcel.readInt();
    ArrayList<String> arrayList = new ArrayList();
    paramParcel.readStringList(arrayList);
    this.tag = str;
    AnnotationValidations.validate(NonNull.class, null, str);
    this.label = i;
    AnnotationValidations.validate(StringRes.class, null, i);
    this.inheritFrom = arrayList;
    AnnotationValidations.validate(NonNull.class, null, arrayList);
  }
  
  public ParsedAttribution(String paramString, int paramInt, List<String> paramList) {
    this.tag = paramString;
    AnnotationValidations.validate(NonNull.class, null, paramString);
    this.label = paramInt;
    AnnotationValidations.validate(StringRes.class, null, paramInt);
    this.inheritFrom = paramList;
    AnnotationValidations.validate(NonNull.class, null, paramList);
  }
  
  @Deprecated
  private void __metadata() {}
  
  public static boolean isCombinationValid(List<ParsedAttribution> paramList) {
    if (paramList == null)
      return true; 
    ArraySet arraySet1 = new ArraySet(paramList.size());
    ArraySet arraySet2 = new ArraySet();
    int i = paramList.size();
    if (i > 1000)
      return false; 
    byte b;
    for (b = 0; b < i; b++) {
      if (!arraySet1.add(((ParsedAttribution)paramList.get(b)).tag))
        return false; 
    } 
    for (b = 0; b < i; b++) {
      ParsedAttribution parsedAttribution = paramList.get(b);
      int j = parsedAttribution.inheritFrom.size();
      for (byte b1 = 0; b1 < j; b1++) {
        String str = parsedAttribution.inheritFrom.get(b1);
        if (arraySet1.contains(str))
          return false; 
        if (!arraySet2.add(str))
          return false; 
      } 
    } 
    return true;
  }
  
  public static String maxToString(int paramInt) {
    return (paramInt != 50) ? ((paramInt != 1000) ? Integer.toHexString(paramInt) : "MAX_NUM_ATTRIBUTIONS") : "MAX_ATTRIBUTION_TAG_LEN";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.tag);
    paramParcel.writeInt(this.label);
    paramParcel.writeStringList(this.inheritFrom);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Max {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedAttribution.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */