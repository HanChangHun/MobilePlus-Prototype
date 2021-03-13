package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ParcelableException;

public class ContentProviderResult implements Parcelable {
  public static final Parcelable.Creator<ContentProviderResult> CREATOR = new Parcelable.Creator<ContentProviderResult>() {
      public ContentProviderResult createFromParcel(Parcel param1Parcel) {
        return new ContentProviderResult(param1Parcel);
      }
      
      public ContentProviderResult[] newArray(int param1Int) {
        return new ContentProviderResult[param1Int];
      }
    };
  
  public final Integer count;
  
  public final Throwable exception;
  
  public final Bundle extras;
  
  public final Uri uri;
  
  public ContentProviderResult(int paramInt) {
    this(null, Integer.valueOf(paramInt), null, null);
  }
  
  public ContentProviderResult(ContentProviderResult paramContentProviderResult, int paramInt) {
    this.uri = ContentProvider.maybeAddUserId(paramContentProviderResult.uri, paramInt);
    this.count = paramContentProviderResult.count;
    this.extras = paramContentProviderResult.extras;
    this.exception = paramContentProviderResult.exception;
  }
  
  public ContentProviderResult(Uri paramUri) {
    this(paramUri, null, null, null);
  }
  
  public ContentProviderResult(Uri paramUri, Integer paramInteger, Bundle paramBundle, Throwable paramThrowable) {
    this.uri = paramUri;
    this.count = paramInteger;
    this.extras = paramBundle;
    this.exception = paramThrowable;
  }
  
  public ContentProviderResult(Bundle paramBundle) {
    this(null, null, paramBundle, null);
  }
  
  public ContentProviderResult(Parcel paramParcel) {
    if (paramParcel.readInt() != 0) {
      this.uri = (Uri)Uri.CREATOR.createFromParcel(paramParcel);
    } else {
      this.uri = null;
    } 
    if (paramParcel.readInt() != 0) {
      this.count = Integer.valueOf(paramParcel.readInt());
    } else {
      this.count = null;
    } 
    if (paramParcel.readInt() != 0) {
      this.extras = paramParcel.readBundle();
    } else {
      this.extras = null;
    } 
    if (paramParcel.readInt() != 0) {
      this.exception = ParcelableException.readFromParcel(paramParcel);
    } else {
      this.exception = null;
    } 
  }
  
  public ContentProviderResult(Throwable paramThrowable) {
    this(null, null, null, paramThrowable);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("ContentProviderResult(");
    if (this.uri != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("uri=");
      stringBuilder1.append(this.uri);
      stringBuilder1.append(" ");
      stringBuilder.append(stringBuilder1.toString());
    } 
    if (this.count != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("count=");
      stringBuilder1.append(this.count);
      stringBuilder1.append(" ");
      stringBuilder.append(stringBuilder1.toString());
    } 
    if (this.extras != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("extras=");
      stringBuilder1.append(this.extras);
      stringBuilder1.append(" ");
      stringBuilder.append(stringBuilder1.toString());
    } 
    if (this.exception != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("exception=");
      stringBuilder1.append(this.exception);
      stringBuilder1.append(" ");
      stringBuilder.append(stringBuilder1.toString());
    } 
    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.uri != null) {
      paramParcel.writeInt(1);
      this.uri.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.count != null) {
      paramParcel.writeInt(1);
      paramParcel.writeInt(this.count.intValue());
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.extras != null) {
      paramParcel.writeInt(1);
      paramParcel.writeBundle(this.extras);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.exception != null) {
      paramParcel.writeInt(1);
      ParcelableException.writeToParcel(paramParcel, this.exception);
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */