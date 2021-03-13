package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.io.PrintWriter;

public final class LocusId implements Parcelable {
  public static final Parcelable.Creator<LocusId> CREATOR = new Parcelable.Creator<LocusId>() {
      public LocusId createFromParcel(Parcel param1Parcel) {
        return new LocusId(param1Parcel.readString());
      }
      
      public LocusId[] newArray(int param1Int) {
        return new LocusId[param1Int];
      }
    };
  
  private final String mId;
  
  public LocusId(String paramString) {
    this.mId = (String)Preconditions.checkStringNotEmpty(paramString, "id cannot be empty");
  }
  
  private String getSanitizedId() {
    int i = this.mId.length();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(i);
    stringBuilder.append("_chars");
    return stringBuilder.toString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("id:");
    paramPrintWriter.println(getSanitizedId());
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (getClass() != paramObject.getClass())
      return false; 
    LocusId locusId = (LocusId)paramObject;
    paramObject = this.mId;
    if (paramObject == null) {
      if (locusId.mId != null)
        return false; 
    } else if (!paramObject.equals(locusId.mId)) {
      return false;
    } 
    return true;
  }
  
  public String getId() {
    return this.mId;
  }
  
  public int hashCode() {
    int i;
    String str = this.mId;
    if (str == null) {
      i = 0;
    } else {
      i = str.hashCode();
    } 
    return 1 * 31 + i;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LocusId[");
    stringBuilder.append(getSanitizedId());
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/LocusId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */