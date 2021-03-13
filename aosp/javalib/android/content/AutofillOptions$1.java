package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

class null implements Parcelable.Creator<AutofillOptions> {
  public AutofillOptions createFromParcel(Parcel paramParcel) {
    AutofillOptions autofillOptions = new AutofillOptions(paramParcel.readInt(), paramParcel.readBoolean());
    autofillOptions.augmentedAutofillEnabled = paramParcel.readBoolean();
    autofillOptions.whitelistedActivitiesForAugmentedAutofill = paramParcel.readArraySet(null);
    autofillOptions.appDisabledExpiration = paramParcel.readLong();
    int i = paramParcel.readInt();
    if (i > 0) {
      autofillOptions.disabledActivities = new ArrayMap();
      for (byte b = 0; b < i; b++)
        autofillOptions.disabledActivities.put(paramParcel.readString(), Long.valueOf(paramParcel.readLong())); 
    } 
    return autofillOptions;
  }
  
  public AutofillOptions[] newArray(int paramInt) {
    return new AutofillOptions[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/AutofillOptions$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */