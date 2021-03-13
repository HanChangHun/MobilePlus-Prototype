package android.app;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class RemoteInputHistoryItem implements Parcelable {
  public static final Parcelable.Creator<RemoteInputHistoryItem> CREATOR = new Parcelable.Creator<RemoteInputHistoryItem>() {
      public RemoteInputHistoryItem createFromParcel(Parcel param1Parcel) {
        return new RemoteInputHistoryItem(param1Parcel);
      }
      
      public RemoteInputHistoryItem[] newArray(int param1Int) {
        return new RemoteInputHistoryItem[param1Int];
      }
    };
  
  private String mMimeType;
  
  private CharSequence mText;
  
  private Uri mUri;
  
  protected RemoteInputHistoryItem(Parcel paramParcel) {
    this.mText = paramParcel.readCharSequence();
    this.mMimeType = paramParcel.readStringNoHelper();
    this.mUri = (Uri)paramParcel.readParcelable(Uri.class.getClassLoader());
  }
  
  public RemoteInputHistoryItem(CharSequence paramCharSequence) {
    this.mText = Notification.safeCharSequence(paramCharSequence);
  }
  
  public RemoteInputHistoryItem(String paramString, Uri paramUri, CharSequence paramCharSequence) {
    this.mMimeType = paramString;
    this.mUri = paramUri;
    this.mText = Notification.safeCharSequence(paramCharSequence);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getMimeType() {
    return this.mMimeType;
  }
  
  public CharSequence getText() {
    return this.mText;
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeCharSequence(this.mText);
    paramParcel.writeStringNoHelper(this.mMimeType);
    paramParcel.writeParcelable((Parcelable)this.mUri, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteInputHistoryItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */