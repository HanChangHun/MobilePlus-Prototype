package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class BackReference implements Parcelable {
  public static final Parcelable.Creator<BackReference> CREATOR = new Parcelable.Creator<BackReference>() {
      public ContentProviderOperation.BackReference createFromParcel(Parcel param2Parcel) {
        return new ContentProviderOperation.BackReference(param2Parcel);
      }
      
      public ContentProviderOperation.BackReference[] newArray(int param2Int) {
        return new ContentProviderOperation.BackReference[param2Int];
      }
    };
  
  private final int fromIndex;
  
  private final String fromKey;
  
  private BackReference(int paramInt, String paramString) {
    this.fromIndex = paramInt;
    this.fromKey = paramString;
  }
  
  public BackReference(Parcel paramParcel) {
    this.fromIndex = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      this.fromKey = paramParcel.readString8();
    } else {
      this.fromKey = null;
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Object resolve(ContentProviderResult[] paramArrayOfContentProviderResult, int paramInt) {
    int i = this.fromIndex;
    if (i < paramInt) {
      Object object = paramArrayOfContentProviderResult[i];
      if (((ContentProviderResult)object).extras != null) {
        object = ((ContentProviderResult)object).extras.get(this.fromKey);
      } else if (((ContentProviderResult)object).uri != null) {
        object = Long.valueOf(ContentUris.parseId(((ContentProviderResult)object).uri));
      } else {
        object = Long.valueOf(((ContentProviderResult)object).count.intValue());
      } 
      return object;
    } 
    Log.e("ContentProviderOperation", toString());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("asked for back ref ");
    stringBuilder.append(this.fromIndex);
    stringBuilder.append(" but there are only ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" back refs");
    throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.fromIndex);
    if (this.fromKey != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString8(this.fromKey);
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderOperation$BackReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */