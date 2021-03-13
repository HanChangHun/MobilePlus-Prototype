package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

public class RestrictionEntry implements Parcelable {
  public static final Parcelable.Creator<RestrictionEntry> CREATOR = new Parcelable.Creator<RestrictionEntry>() {
      public RestrictionEntry createFromParcel(Parcel param1Parcel) {
        return new RestrictionEntry(param1Parcel);
      }
      
      public RestrictionEntry[] newArray(int param1Int) {
        return new RestrictionEntry[param1Int];
      }
    };
  
  public static final int TYPE_BOOLEAN = 1;
  
  public static final int TYPE_BUNDLE = 7;
  
  public static final int TYPE_BUNDLE_ARRAY = 8;
  
  public static final int TYPE_CHOICE = 2;
  
  public static final int TYPE_CHOICE_LEVEL = 3;
  
  public static final int TYPE_INTEGER = 5;
  
  public static final int TYPE_MULTI_SELECT = 4;
  
  public static final int TYPE_NULL = 0;
  
  public static final int TYPE_STRING = 6;
  
  private String[] mChoiceEntries;
  
  private String[] mChoiceValues;
  
  private String mCurrentValue;
  
  private String[] mCurrentValues;
  
  private String mDescription;
  
  private String mKey;
  
  private RestrictionEntry[] mRestrictions;
  
  private String mTitle;
  
  private int mType;
  
  public RestrictionEntry(int paramInt, String paramString) {
    this.mType = paramInt;
    this.mKey = paramString;
  }
  
  public RestrictionEntry(Parcel paramParcel) {
    this.mType = paramParcel.readInt();
    this.mKey = paramParcel.readString();
    this.mTitle = paramParcel.readString();
    this.mDescription = paramParcel.readString();
    this.mChoiceEntries = paramParcel.readStringArray();
    this.mChoiceValues = paramParcel.readStringArray();
    this.mCurrentValue = paramParcel.readString();
    this.mCurrentValues = paramParcel.readStringArray();
    Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(null);
    if (arrayOfParcelable != null) {
      this.mRestrictions = new RestrictionEntry[arrayOfParcelable.length];
      for (byte b = 0; b < arrayOfParcelable.length; b++)
        this.mRestrictions[b] = (RestrictionEntry)arrayOfParcelable[b]; 
    } 
  }
  
  public RestrictionEntry(String paramString, int paramInt) {
    this.mKey = paramString;
    this.mType = 5;
    setIntValue(paramInt);
  }
  
  public RestrictionEntry(String paramString1, String paramString2) {
    this.mKey = paramString1;
    this.mType = 2;
    this.mCurrentValue = paramString2;
  }
  
  public RestrictionEntry(String paramString, boolean paramBoolean) {
    this.mKey = paramString;
    this.mType = 1;
    setSelectedState(paramBoolean);
  }
  
  private RestrictionEntry(String paramString, RestrictionEntry[] paramArrayOfRestrictionEntry, boolean paramBoolean) {
    this.mKey = paramString;
    if (paramBoolean) {
      this.mType = 8;
      if (paramArrayOfRestrictionEntry != null) {
        int i = paramArrayOfRestrictionEntry.length;
        byte b = 0;
        while (b < i) {
          if (paramArrayOfRestrictionEntry[b].getType() == 7) {
            b++;
            continue;
          } 
          throw new IllegalArgumentException("bundle_array restriction can only have nested restriction entries of type bundle");
        } 
      } 
    } else {
      this.mType = 7;
    } 
    setRestrictions(paramArrayOfRestrictionEntry);
  }
  
  public RestrictionEntry(String paramString, String[] paramArrayOfString) {
    this.mKey = paramString;
    this.mType = 4;
    this.mCurrentValues = paramArrayOfString;
  }
  
  public static RestrictionEntry createBundleArrayEntry(String paramString, RestrictionEntry[] paramArrayOfRestrictionEntry) {
    return new RestrictionEntry(paramString, paramArrayOfRestrictionEntry, true);
  }
  
  public static RestrictionEntry createBundleEntry(String paramString, RestrictionEntry[] paramArrayOfRestrictionEntry) {
    return new RestrictionEntry(paramString, paramArrayOfRestrictionEntry, false);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof RestrictionEntry))
      return false; 
    RestrictionEntry restrictionEntry = (RestrictionEntry)paramObject;
    if (this.mType != restrictionEntry.mType || !this.mKey.equals(restrictionEntry.mKey))
      return false; 
    if (this.mCurrentValues == null && restrictionEntry.mCurrentValues == null && this.mRestrictions == null && restrictionEntry.mRestrictions == null && Objects.equals(this.mCurrentValue, restrictionEntry.mCurrentValue))
      return true; 
    if (this.mCurrentValue == null && restrictionEntry.mCurrentValue == null && this.mRestrictions == null && restrictionEntry.mRestrictions == null && Arrays.equals((Object[])this.mCurrentValues, (Object[])restrictionEntry.mCurrentValues))
      return true; 
    paramObject = this.mCurrentValue;
    if (paramObject == null) {
      String str = restrictionEntry.mCurrentValue;
      if (str == null && paramObject == null && str == null && Arrays.equals((Object[])this.mRestrictions, (Object[])restrictionEntry.mRestrictions))
        return true; 
    } 
    return false;
  }
  
  public String[] getAllSelectedStrings() {
    return this.mCurrentValues;
  }
  
  public String[] getChoiceEntries() {
    return this.mChoiceEntries;
  }
  
  public String[] getChoiceValues() {
    return this.mChoiceValues;
  }
  
  public String getDescription() {
    return this.mDescription;
  }
  
  public int getIntValue() {
    return Integer.parseInt(this.mCurrentValue);
  }
  
  public String getKey() {
    return this.mKey;
  }
  
  public RestrictionEntry[] getRestrictions() {
    return this.mRestrictions;
  }
  
  public boolean getSelectedState() {
    return Boolean.parseBoolean(this.mCurrentValue);
  }
  
  public String getSelectedString() {
    return this.mCurrentValue;
  }
  
  public String getTitle() {
    return this.mTitle;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public int hashCode() {
    int j;
    int i = 17 * 31 + this.mKey.hashCode();
    String str = this.mCurrentValue;
    if (str != null) {
      j = i * 31 + str.hashCode();
    } else {
      String[] arrayOfString = this.mCurrentValues;
      if (arrayOfString != null) {
        int k = arrayOfString.length;
        byte b = 0;
        while (true) {
          j = i;
          if (b < k) {
            String str1 = arrayOfString[b];
            j = i;
            if (str1 != null)
              j = i * 31 + str1.hashCode(); 
            b++;
            i = j;
            continue;
          } 
          break;
        } 
      } else {
        RestrictionEntry[] arrayOfRestrictionEntry = this.mRestrictions;
        j = i;
        if (arrayOfRestrictionEntry != null)
          j = i * 31 + Arrays.hashCode((Object[])arrayOfRestrictionEntry); 
      } 
    } 
    return j;
  }
  
  public void setAllSelectedStrings(String[] paramArrayOfString) {
    this.mCurrentValues = paramArrayOfString;
  }
  
  public void setChoiceEntries(Context paramContext, int paramInt) {
    this.mChoiceEntries = paramContext.getResources().getStringArray(paramInt);
  }
  
  public void setChoiceEntries(String[] paramArrayOfString) {
    this.mChoiceEntries = paramArrayOfString;
  }
  
  public void setChoiceValues(Context paramContext, int paramInt) {
    this.mChoiceValues = paramContext.getResources().getStringArray(paramInt);
  }
  
  public void setChoiceValues(String[] paramArrayOfString) {
    this.mChoiceValues = paramArrayOfString;
  }
  
  public void setDescription(String paramString) {
    this.mDescription = paramString;
  }
  
  public void setIntValue(int paramInt) {
    this.mCurrentValue = Integer.toString(paramInt);
  }
  
  public void setRestrictions(RestrictionEntry[] paramArrayOfRestrictionEntry) {
    this.mRestrictions = paramArrayOfRestrictionEntry;
  }
  
  public void setSelectedState(boolean paramBoolean) {
    this.mCurrentValue = Boolean.toString(paramBoolean);
  }
  
  public void setSelectedString(String paramString) {
    this.mCurrentValue = paramString;
  }
  
  public void setTitle(String paramString) {
    this.mTitle = paramString;
  }
  
  public void setType(int paramInt) {
    this.mType = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("RestrictionEntry{mType=");
    stringBuilder.append(this.mType);
    stringBuilder.append(", mKey='");
    stringBuilder.append(this.mKey);
    stringBuilder.append('\'');
    stringBuilder.append(", mTitle='");
    stringBuilder.append(this.mTitle);
    stringBuilder.append('\'');
    stringBuilder.append(", mDescription='");
    stringBuilder.append(this.mDescription);
    stringBuilder.append('\'');
    stringBuilder.append(", mChoiceEntries=");
    stringBuilder.append(Arrays.toString((Object[])this.mChoiceEntries));
    stringBuilder.append(", mChoiceValues=");
    stringBuilder.append(Arrays.toString((Object[])this.mChoiceValues));
    stringBuilder.append(", mCurrentValue='");
    stringBuilder.append(this.mCurrentValue);
    stringBuilder.append('\'');
    stringBuilder.append(", mCurrentValues=");
    stringBuilder.append(Arrays.toString((Object[])this.mCurrentValues));
    stringBuilder.append(", mRestrictions=");
    stringBuilder.append(Arrays.toString((Object[])this.mRestrictions));
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mType);
    paramParcel.writeString(this.mKey);
    paramParcel.writeString(this.mTitle);
    paramParcel.writeString(this.mDescription);
    paramParcel.writeStringArray(this.mChoiceEntries);
    paramParcel.writeStringArray(this.mChoiceValues);
    paramParcel.writeString(this.mCurrentValue);
    paramParcel.writeStringArray(this.mCurrentValues);
    paramParcel.writeParcelableArray((Parcelable[])this.mRestrictions, 0);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/RestrictionEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */