package android.app.assist;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class AssistContent implements Parcelable {
  public static final Parcelable.Creator<AssistContent> CREATOR = new Parcelable.Creator<AssistContent>() {
      public AssistContent createFromParcel(Parcel param1Parcel) {
        return new AssistContent(param1Parcel);
      }
      
      public AssistContent[] newArray(int param1Int) {
        return new AssistContent[param1Int];
      }
    };
  
  private ClipData mClipData;
  
  private final Bundle mExtras;
  
  private Intent mIntent;
  
  private boolean mIsAppProvidedIntent;
  
  private boolean mIsAppProvidedWebUri;
  
  private String mStructuredData;
  
  private Uri mUri;
  
  public AssistContent() {
    this.mIsAppProvidedIntent = false;
    this.mIsAppProvidedWebUri = false;
    this.mExtras = new Bundle();
  }
  
  AssistContent(Parcel paramParcel) {
    boolean bool1 = false;
    this.mIsAppProvidedIntent = false;
    this.mIsAppProvidedWebUri = false;
    if (paramParcel.readInt() != 0)
      this.mIntent = (Intent)Intent.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.mClipData = (ClipData)ClipData.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.mUri = (Uri)Uri.CREATOR.createFromParcel(paramParcel); 
    if (paramParcel.readInt() != 0)
      this.mStructuredData = paramParcel.readString(); 
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsAppProvidedIntent = bool2;
    this.mExtras = paramParcel.readBundle();
    boolean bool2 = bool1;
    if (paramParcel.readInt() == 1)
      bool2 = true; 
    this.mIsAppProvidedWebUri = bool2;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public ClipData getClipData() {
    return this.mClipData;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public Intent getIntent() {
    return this.mIntent;
  }
  
  public String getStructuredData() {
    return this.mStructuredData;
  }
  
  public Uri getWebUri() {
    return this.mUri;
  }
  
  public boolean isAppProvidedIntent() {
    return this.mIsAppProvidedIntent;
  }
  
  public boolean isAppProvidedWebUri() {
    return this.mIsAppProvidedWebUri;
  }
  
  public void setClipData(ClipData paramClipData) {
    this.mClipData = paramClipData;
  }
  
  public void setDefaultIntent(Intent paramIntent) {
    this.mIntent = paramIntent;
    this.mIsAppProvidedIntent = false;
    this.mIsAppProvidedWebUri = false;
    this.mUri = null;
    if (paramIntent != null && "android.intent.action.VIEW".equals(paramIntent.getAction())) {
      Uri uri = paramIntent.getData();
      if (uri != null && ("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())))
        this.mUri = uri; 
    } 
  }
  
  public void setIntent(Intent paramIntent) {
    this.mIsAppProvidedIntent = true;
    this.mIntent = paramIntent;
  }
  
  public void setStructuredData(String paramString) {
    this.mStructuredData = paramString;
  }
  
  public void setWebUri(Uri paramUri) {
    this.mIsAppProvidedWebUri = true;
    this.mUri = paramUri;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    writeToParcelInternal(paramParcel, paramInt);
  }
  
  void writeToParcelInternal(Parcel paramParcel, int paramInt) {
    if (this.mIntent != null) {
      paramParcel.writeInt(1);
      this.mIntent.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mClipData != null) {
      paramParcel.writeInt(1);
      this.mClipData.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mUri != null) {
      paramParcel.writeInt(1);
      this.mUri.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    if (this.mStructuredData != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString(this.mStructuredData);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.mIsAppProvidedIntent);
    paramParcel.writeBundle(this.mExtras);
    paramParcel.writeInt(this.mIsAppProvidedWebUri);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */