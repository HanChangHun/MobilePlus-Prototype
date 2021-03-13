package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ClipDescription implements Parcelable {
  public static final Parcelable.Creator<ClipDescription> CREATOR = new Parcelable.Creator<ClipDescription>() {
      public ClipDescription createFromParcel(Parcel param1Parcel) {
        return new ClipDescription(param1Parcel);
      }
      
      public ClipDescription[] newArray(int param1Int) {
        return new ClipDescription[param1Int];
      }
    };
  
  public static final String EXTRA_TARGET_COMPONENT_NAME = "android.content.extra.TARGET_COMPONENT_NAME";
  
  public static final String EXTRA_USER_SERIAL_NUMBER = "android.content.extra.USER_SERIAL_NUMBER";
  
  public static final String MIMETYPE_TEXT_HTML = "text/html";
  
  public static final String MIMETYPE_TEXT_INTENT = "text/vnd.android.intent";
  
  public static final String MIMETYPE_TEXT_PLAIN = "text/plain";
  
  public static final String MIMETYPE_TEXT_URILIST = "text/uri-list";
  
  public static final String MIMETYPE_UNKNOWN = "application/octet-stream";
  
  private PersistableBundle mExtras;
  
  final CharSequence mLabel;
  
  private final ArrayList<String> mMimeTypes;
  
  private long mTimeStamp;
  
  public ClipDescription(ClipDescription paramClipDescription) {
    this.mLabel = paramClipDescription.mLabel;
    this.mMimeTypes = new ArrayList<>(paramClipDescription.mMimeTypes);
    this.mTimeStamp = paramClipDescription.mTimeStamp;
  }
  
  ClipDescription(Parcel paramParcel) {
    this.mLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mMimeTypes = paramParcel.createStringArrayList();
    this.mExtras = paramParcel.readPersistableBundle();
    this.mTimeStamp = paramParcel.readLong();
  }
  
  public ClipDescription(CharSequence paramCharSequence, String[] paramArrayOfString) {
    if (paramArrayOfString != null) {
      this.mLabel = paramCharSequence;
      this.mMimeTypes = new ArrayList<>(Arrays.asList(paramArrayOfString));
      return;
    } 
    throw new NullPointerException("mimeTypes is null");
  }
  
  public static boolean compareMimeTypes(String paramString1, String paramString2) {
    int i = paramString2.length();
    if (i == 3 && paramString2.equals("*/*"))
      return true; 
    int j = paramString2.indexOf('/');
    if (j > 0)
      if (i == j + 2 && paramString2.charAt(j + 1) == '*') {
        if (paramString2.regionMatches(0, paramString1, 0, j + 1))
          return true; 
      } else if (paramString2.equals(paramString1)) {
        return true;
      }  
    return false;
  }
  
  void addMimeTypes(String[] paramArrayOfString) {
    for (byte b = 0; b != paramArrayOfString.length; b++) {
      String str = paramArrayOfString[b];
      if (!this.mMimeTypes.contains(str))
        this.mMimeTypes.add(str); 
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    long l = paramProtoOutputStream.start(paramLong);
    int i = this.mMimeTypes.size();
    for (byte b = 0; b < i; b++)
      paramProtoOutputStream.write(2237677961217L, this.mMimeTypes.get(b)); 
    CharSequence charSequence = this.mLabel;
    if (charSequence != null)
      paramProtoOutputStream.write(1138166333442L, charSequence.toString()); 
    PersistableBundle persistableBundle = this.mExtras;
    if (persistableBundle != null)
      persistableBundle.dumpDebug(paramProtoOutputStream, 1146756268035L); 
    paramLong = this.mTimeStamp;
    if (paramLong > 0L)
      paramProtoOutputStream.write(1112396529668L, paramLong); 
    paramProtoOutputStream.end(l);
  }
  
  public String[] filterMimeTypes(String paramString) {
    ArrayList<String> arrayList = null;
    int i = this.mMimeTypes.size();
    byte b = 0;
    while (b < i) {
      ArrayList<String> arrayList1 = arrayList;
      if (compareMimeTypes(this.mMimeTypes.get(b), paramString)) {
        arrayList1 = arrayList;
        if (arrayList == null)
          arrayList1 = new ArrayList(); 
        arrayList1.add(this.mMimeTypes.get(b));
      } 
      b++;
      arrayList = arrayList1;
    } 
    if (arrayList == null)
      return null; 
    String[] arrayOfString = new String[arrayList.size()];
    arrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public PersistableBundle getExtras() {
    return this.mExtras;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
  
  public String getMimeType(int paramInt) {
    return this.mMimeTypes.get(paramInt);
  }
  
  public int getMimeTypeCount() {
    return this.mMimeTypes.size();
  }
  
  public long getTimestamp() {
    return this.mTimeStamp;
  }
  
  public boolean hasMimeType(String paramString) {
    int i = this.mMimeTypes.size();
    for (byte b = 0; b < i; b++) {
      if (compareMimeTypes(this.mMimeTypes.get(b), paramString))
        return true; 
    } 
    return false;
  }
  
  public void setExtras(PersistableBundle paramPersistableBundle) {
    this.mExtras = new PersistableBundle(paramPersistableBundle);
  }
  
  public void setTimestamp(long paramLong) {
    this.mTimeStamp = paramLong;
  }
  
  public boolean toShortString(StringBuilder paramStringBuilder) {
    boolean bool = toShortStringTypesOnly(paramStringBuilder);
    boolean bool1 = true;
    int i = bool ^ true;
    int j = i;
    if (this.mLabel != null) {
      if (i == 0)
        paramStringBuilder.append(' '); 
      j = 0;
      paramStringBuilder.append('"');
      paramStringBuilder.append(this.mLabel);
      paramStringBuilder.append('"');
    } 
    i = j;
    if (this.mExtras != null) {
      if (j == 0)
        paramStringBuilder.append(' '); 
      i = 0;
      paramStringBuilder.append(this.mExtras.toString());
    } 
    j = i;
    if (this.mTimeStamp > 0L) {
      if (i == 0)
        paramStringBuilder.append(' '); 
      j = 0;
      paramStringBuilder.append('<');
      paramStringBuilder.append(TimeUtils.logTimeOfDay(this.mTimeStamp));
      paramStringBuilder.append('>');
    } 
    if (j != 0)
      bool1 = false; 
    return bool1;
  }
  
  public boolean toShortStringTypesOnly(StringBuilder paramStringBuilder) {
    boolean bool = true;
    int i = this.mMimeTypes.size();
    for (byte b = 0; b < i; b++) {
      if (!bool)
        paramStringBuilder.append(' '); 
      bool = false;
      paramStringBuilder.append(this.mMimeTypes.get(b));
    } 
    return bool ^ true;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("ClipDescription { ");
    toShortString(stringBuilder);
    stringBuilder.append(" }");
    return stringBuilder.toString();
  }
  
  public void validate() {
    ArrayList<String> arrayList = this.mMimeTypes;
    if (arrayList != null) {
      int i = arrayList.size();
      if (i > 0) {
        byte b = 0;
        while (b < i) {
          if (this.mMimeTypes.get(b) != null) {
            b++;
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("mime type at ");
          stringBuilder.append(b);
          stringBuilder.append(" is null");
          throw new NullPointerException(stringBuilder.toString());
        } 
        return;
      } 
      throw new IllegalArgumentException("must have at least 1 mime type");
    } 
    throw new NullPointerException("null mime types");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    TextUtils.writeToParcel(this.mLabel, paramParcel, paramInt);
    paramParcel.writeStringList(this.mMimeTypes);
    paramParcel.writePersistableBundle(this.mExtras);
    paramParcel.writeLong(this.mTimeStamp);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ClipDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */