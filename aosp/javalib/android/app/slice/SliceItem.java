package android.app.slice;

import android.app.PendingIntent;
import android.app.RemoteInput;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.android.internal.util.ArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

public final class SliceItem implements Parcelable {
  public static final Parcelable.Creator<SliceItem> CREATOR = new Parcelable.Creator<SliceItem>() {
      public SliceItem createFromParcel(Parcel param1Parcel) {
        return new SliceItem(param1Parcel);
      }
      
      public SliceItem[] newArray(int param1Int) {
        return new SliceItem[param1Int];
      }
    };
  
  public static final String FORMAT_ACTION = "action";
  
  public static final String FORMAT_BUNDLE = "bundle";
  
  public static final String FORMAT_IMAGE = "image";
  
  public static final String FORMAT_INT = "int";
  
  public static final String FORMAT_LONG = "long";
  
  public static final String FORMAT_REMOTE_INPUT = "input";
  
  public static final String FORMAT_SLICE = "slice";
  
  public static final String FORMAT_TEXT = "text";
  
  @Deprecated
  public static final String FORMAT_TIMESTAMP = "long";
  
  private static final String TAG = "SliceItem";
  
  private final String mFormat;
  
  protected String[] mHints;
  
  private final Object mObj;
  
  private final String mSubType;
  
  public SliceItem(PendingIntent paramPendingIntent, Slice paramSlice, String paramString1, String paramString2, String[] paramArrayOfString) {
    this(new Pair(paramPendingIntent, paramSlice), paramString1, paramString2, paramArrayOfString);
  }
  
  public SliceItem(Parcel paramParcel) {
    this.mHints = paramParcel.readStringArray();
    this.mFormat = paramParcel.readString();
    this.mSubType = paramParcel.readString();
    this.mObj = readObj(this.mFormat, paramParcel);
  }
  
  public SliceItem(Object paramObject, String paramString1, String paramString2, List<String> paramList) {
    this(paramObject, paramString1, paramString2, paramList.<String>toArray(new String[paramList.size()]));
  }
  
  public SliceItem(Object paramObject, String paramString1, String paramString2, String[] paramArrayOfString) {
    this.mHints = paramArrayOfString;
    this.mFormat = paramString1;
    this.mSubType = paramString2;
    this.mObj = paramObject;
  }
  
  private static String getBaseType(String paramString) {
    int i = paramString.indexOf('/');
    return (i >= 0) ? paramString.substring(0, i) : paramString;
  }
  
  private static Object readObj(String paramString, Parcel paramParcel) {
    StringBuilder stringBuilder;
    byte b;
    String str = getBaseType(paramString);
    switch (str.hashCode()) {
      default:
        b = -1;
        break;
      case 109526418:
        if (str.equals("slice")) {
          b = 0;
          break;
        } 
      case 100358090:
        if (str.equals("input")) {
          b = 6;
          break;
        } 
      case 100313435:
        if (str.equals("image")) {
          b = 2;
          break;
        } 
      case 3556653:
        if (str.equals("text")) {
          b = 1;
          break;
        } 
      case 3327612:
        if (str.equals("long")) {
          b = 5;
          break;
        } 
      case 104431:
        if (str.equals("int")) {
          b = 4;
          break;
        } 
      case -1377881982:
        if (str.equals("bundle")) {
          b = 7;
          break;
        } 
      case -1422950858:
        if (str.equals("action")) {
          b = 3;
          break;
        } 
    } 
    switch (b) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported type ");
        stringBuilder.append(paramString);
        throw new RuntimeException(stringBuilder.toString());
      case 7:
        return Bundle.CREATOR.createFromParcel((Parcel)stringBuilder);
      case 6:
        return RemoteInput.CREATOR.createFromParcel((Parcel)stringBuilder);
      case 5:
        return Long.valueOf(stringBuilder.readLong());
      case 4:
        return Integer.valueOf(stringBuilder.readInt());
      case 3:
        return new Pair(PendingIntent.CREATOR.createFromParcel((Parcel)stringBuilder), Slice.CREATOR.createFromParcel((Parcel)stringBuilder));
      case 2:
        return Icon.CREATOR.createFromParcel((Parcel)stringBuilder);
      case 1:
        return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)stringBuilder);
      case 0:
        break;
    } 
    return Slice.CREATOR.createFromParcel((Parcel)stringBuilder);
  }
  
  private static void writeObj(Parcel paramParcel, int paramInt, Object paramObject, String paramString) {
    byte b;
    paramString = getBaseType(paramString);
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 109526418:
        if (paramString.equals("slice")) {
          b = 0;
          break;
        } 
      case 100358090:
        if (paramString.equals("input")) {
          b = 2;
          break;
        } 
      case 100313435:
        if (paramString.equals("image")) {
          b = 1;
          break;
        } 
      case 3556653:
        if (paramString.equals("text")) {
          b = 5;
          break;
        } 
      case 3327612:
        if (paramString.equals("long")) {
          b = 7;
          break;
        } 
      case 104431:
        if (paramString.equals("int")) {
          b = 6;
          break;
        } 
      case -1377881982:
        if (paramString.equals("bundle")) {
          b = 3;
          break;
        } 
      case -1422950858:
        if (paramString.equals("action")) {
          b = 4;
          break;
        } 
    } 
    switch (b) {
      default:
        return;
      case 7:
        paramParcel.writeLong(((Long)paramObject).longValue());
      case 6:
        paramParcel.writeInt(((Integer)paramObject).intValue());
      case 5:
        TextUtils.writeToParcel((CharSequence)paramObject, paramParcel, paramInt);
      case 4:
        ((PendingIntent)((Pair)paramObject).first).writeToParcel(paramParcel, paramInt);
        ((Slice)((Pair)paramObject).second).writeToParcel(paramParcel, paramInt);
      case 0:
      case 1:
      case 2:
      case 3:
        break;
    } 
    ((Parcelable)paramObject).writeToParcel(paramParcel, paramInt);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public PendingIntent getAction() {
    return (PendingIntent)((Pair)this.mObj).first;
  }
  
  public Bundle getBundle() {
    return (Bundle)this.mObj;
  }
  
  public String getFormat() {
    return this.mFormat;
  }
  
  public List<String> getHints() {
    return Arrays.asList(this.mHints);
  }
  
  public Icon getIcon() {
    return (Icon)this.mObj;
  }
  
  public int getInt() {
    return ((Integer)this.mObj).intValue();
  }
  
  public long getLong() {
    return ((Long)this.mObj).longValue();
  }
  
  public RemoteInput getRemoteInput() {
    return (RemoteInput)this.mObj;
  }
  
  public RemoteViews getRemoteView() {
    return (RemoteViews)this.mObj;
  }
  
  public Slice getSlice() {
    return "action".equals(getFormat()) ? (Slice)((Pair)this.mObj).second : (Slice)this.mObj;
  }
  
  public String getSubType() {
    return this.mSubType;
  }
  
  public CharSequence getText() {
    return (CharSequence)this.mObj;
  }
  
  @Deprecated
  public long getTimestamp() {
    return ((Long)this.mObj).longValue();
  }
  
  public boolean hasAnyHints(String[] paramArrayOfString) {
    if (paramArrayOfString == null)
      return false; 
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (ArrayUtils.contains((Object[])this.mHints, str))
        return true; 
    } 
    return false;
  }
  
  public boolean hasHint(String paramString) {
    return ArrayUtils.contains((Object[])this.mHints, paramString);
  }
  
  public boolean hasHints(String[] paramArrayOfString) {
    if (paramArrayOfString == null)
      return true; 
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (!TextUtils.isEmpty(str) && !ArrayUtils.contains((Object[])this.mHints, str))
        return false; 
    } 
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStringArray(this.mHints);
    paramParcel.writeString(this.mFormat);
    paramParcel.writeString(this.mSubType);
    writeObj(paramParcel, paramInt, this.mObj, this.mFormat);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SliceType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */