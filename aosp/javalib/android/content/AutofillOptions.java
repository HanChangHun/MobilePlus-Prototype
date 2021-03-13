package android.content;

import android.app.ActivityThread;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.view.autofill.AutofillManager;
import java.io.PrintWriter;

public final class AutofillOptions implements Parcelable {
  public static final Parcelable.Creator<AutofillOptions> CREATOR;
  
  private static final String TAG = AutofillOptions.class.getSimpleName();
  
  public long appDisabledExpiration;
  
  public boolean augmentedAutofillEnabled;
  
  public final boolean compatModeEnabled;
  
  public ArrayMap<String, Long> disabledActivities;
  
  public final int loggingLevel;
  
  public ArraySet<ComponentName> whitelistedActivitiesForAugmentedAutofill;
  
  static {
    CREATOR = new Parcelable.Creator<AutofillOptions>() {
        public AutofillOptions createFromParcel(Parcel param1Parcel) {
          AutofillOptions autofillOptions = new AutofillOptions(param1Parcel.readInt(), param1Parcel.readBoolean());
          autofillOptions.augmentedAutofillEnabled = param1Parcel.readBoolean();
          autofillOptions.whitelistedActivitiesForAugmentedAutofill = param1Parcel.readArraySet(null);
          autofillOptions.appDisabledExpiration = param1Parcel.readLong();
          int i = param1Parcel.readInt();
          if (i > 0) {
            autofillOptions.disabledActivities = new ArrayMap();
            for (byte b = 0; b < i; b++)
              autofillOptions.disabledActivities.put(param1Parcel.readString(), Long.valueOf(param1Parcel.readLong())); 
          } 
          return autofillOptions;
        }
        
        public AutofillOptions[] newArray(int param1Int) {
          return new AutofillOptions[param1Int];
        }
      };
  }
  
  public AutofillOptions(int paramInt, boolean paramBoolean) {
    this.loggingLevel = paramInt;
    this.compatModeEnabled = paramBoolean;
  }
  
  public static AutofillOptions forWhitelistingItself() {
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    if (activityThread != null) {
      String str1 = activityThread.getApplication().getPackageName();
      if ("android.autofillservice.cts".equals(str1)) {
        AutofillOptions autofillOptions = new AutofillOptions(4, true);
        autofillOptions.augmentedAutofillEnabled = true;
        String str = TAG;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("forWhitelistingItself(");
        stringBuilder1.append(str1);
        stringBuilder1.append("): ");
        stringBuilder1.append(autofillOptions);
        Log.i(str, stringBuilder1.toString());
        return autofillOptions;
      } 
      String str2 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("forWhitelistingItself(): called by ");
      stringBuilder.append(str1);
      Log.e(str2, stringBuilder.toString());
      throw new SecurityException("Thou shall not pass!");
    } 
    throw new IllegalStateException("No ActivityThread");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpShort(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("logLvl=");
    paramPrintWriter.print(this.loggingLevel);
    paramPrintWriter.print(", compatMode=");
    paramPrintWriter.print(this.compatModeEnabled);
    paramPrintWriter.print(", augmented=");
    paramPrintWriter.print(this.augmentedAutofillEnabled);
    if (this.whitelistedActivitiesForAugmentedAutofill != null) {
      paramPrintWriter.print(", whitelistedActivitiesForAugmentedAutofill=");
      paramPrintWriter.print(this.whitelistedActivitiesForAugmentedAutofill);
    } 
    paramPrintWriter.print(", appDisabledExpiration=");
    paramPrintWriter.print(this.appDisabledExpiration);
    if (this.disabledActivities != null) {
      paramPrintWriter.print(", disabledActivities=");
      paramPrintWriter.print(this.disabledActivities);
    } 
  }
  
  public boolean isAugmentedAutofillEnabled(Context paramContext) {
    boolean bool = this.augmentedAutofillEnabled;
    boolean bool1 = false;
    if (!bool)
      return false; 
    AutofillManager.AutofillClient autofillClient = paramContext.getAutofillClient();
    if (autofillClient == null)
      return false; 
    ComponentName componentName = autofillClient.autofillClientGetComponentName();
    ArraySet<ComponentName> arraySet = this.whitelistedActivitiesForAugmentedAutofill;
    if (arraySet == null || arraySet.contains(componentName))
      bool1 = true; 
    return bool1;
  }
  
  public boolean isAutofillDisabledLocked(ComponentName paramComponentName) {
    long l = SystemClock.elapsedRealtime();
    String str = paramComponentName.flattenToString();
    if (this.appDisabledExpiration >= l)
      return true; 
    ArrayMap<String, Long> arrayMap = this.disabledActivities;
    if (arrayMap != null) {
      Long long_ = (Long)arrayMap.get(str);
      if (long_ != null) {
        if (long_.longValue() >= l)
          return true; 
        this.disabledActivities.remove(str);
      } 
    } 
    this.appDisabledExpiration = 0L;
    return false;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AutofillOptions [loggingLevel=");
    stringBuilder.append(this.loggingLevel);
    stringBuilder.append(", compatMode=");
    stringBuilder.append(this.compatModeEnabled);
    stringBuilder.append(", augmentedAutofillEnabled=");
    stringBuilder.append(this.augmentedAutofillEnabled);
    stringBuilder.append(", appDisabledExpiration=");
    stringBuilder.append(this.appDisabledExpiration);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.loggingLevel);
    paramParcel.writeBoolean(this.compatModeEnabled);
    paramParcel.writeBoolean(this.augmentedAutofillEnabled);
    paramParcel.writeArraySet(this.whitelistedActivitiesForAugmentedAutofill);
    paramParcel.writeLong(this.appDisabledExpiration);
    ArrayMap<String, Long> arrayMap = this.disabledActivities;
    if (arrayMap != null) {
      paramInt = arrayMap.size();
    } else {
      paramInt = 0;
    } 
    paramParcel.writeInt(paramInt);
    if (paramInt > 0)
      for (byte b = 0; b < paramInt; b++) {
        String str = (String)this.disabledActivities.keyAt(b);
        paramParcel.writeString(str);
        paramParcel.writeLong(((Long)this.disabledActivities.get(str)).longValue());
      }  
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/AutofillOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */