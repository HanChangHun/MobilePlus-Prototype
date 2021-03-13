package android.app;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.PrintWriter;

public final class RemoteAction implements Parcelable {
  public static final Parcelable.Creator<RemoteAction> CREATOR = new Parcelable.Creator<RemoteAction>() {
      public RemoteAction createFromParcel(Parcel param1Parcel) {
        return new RemoteAction(param1Parcel);
      }
      
      public RemoteAction[] newArray(int param1Int) {
        return new RemoteAction[param1Int];
      }
    };
  
  private static final String TAG = "RemoteAction";
  
  private final PendingIntent mActionIntent;
  
  private final CharSequence mContentDescription;
  
  private boolean mEnabled;
  
  private final Icon mIcon;
  
  private boolean mShouldShowIcon;
  
  private final CharSequence mTitle;
  
  public RemoteAction(Icon paramIcon, CharSequence paramCharSequence1, CharSequence paramCharSequence2, PendingIntent paramPendingIntent) {
    if (paramIcon != null && paramCharSequence1 != null && paramCharSequence2 != null && paramPendingIntent != null) {
      this.mIcon = paramIcon;
      this.mTitle = paramCharSequence1;
      this.mContentDescription = paramCharSequence2;
      this.mActionIntent = paramPendingIntent;
      this.mEnabled = true;
      this.mShouldShowIcon = true;
      return;
    } 
    throw new IllegalArgumentException("Expected icon, title, content description and action callback");
  }
  
  RemoteAction(Parcel paramParcel) {
    this.mIcon = (Icon)Icon.CREATOR.createFromParcel(paramParcel);
    this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mActionIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel);
    this.mEnabled = paramParcel.readBoolean();
    this.mShouldShowIcon = paramParcel.readBoolean();
  }
  
  public RemoteAction clone() {
    RemoteAction remoteAction = new RemoteAction(this.mIcon, this.mTitle, this.mContentDescription, this.mActionIntent);
    remoteAction.setEnabled(this.mEnabled);
    remoteAction.setShouldShowIcon(this.mShouldShowIcon);
    return remoteAction;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter) {
    paramPrintWriter.print(paramString);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("title=");
    stringBuilder.append(this.mTitle);
    paramPrintWriter.print(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(" enabled=");
    stringBuilder.append(this.mEnabled);
    paramPrintWriter.print(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(" contentDescription=");
    stringBuilder.append(this.mContentDescription);
    paramPrintWriter.print(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(" icon=");
    stringBuilder.append(this.mIcon);
    paramPrintWriter.print(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(" action=");
    stringBuilder.append(this.mActionIntent.getIntent());
    paramPrintWriter.print(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(" shouldShowIcon=");
    stringBuilder.append(this.mShouldShowIcon);
    paramPrintWriter.print(stringBuilder.toString());
    paramPrintWriter.println();
  }
  
  public PendingIntent getActionIntent() {
    return this.mActionIntent;
  }
  
  public CharSequence getContentDescription() {
    return this.mContentDescription;
  }
  
  public Icon getIcon() {
    return this.mIcon;
  }
  
  public CharSequence getTitle() {
    return this.mTitle;
  }
  
  public boolean isEnabled() {
    return this.mEnabled;
  }
  
  public void setEnabled(boolean paramBoolean) {
    this.mEnabled = paramBoolean;
  }
  
  public void setShouldShowIcon(boolean paramBoolean) {
    this.mShouldShowIcon = paramBoolean;
  }
  
  public boolean shouldShowIcon() {
    return this.mShouldShowIcon;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mIcon.writeToParcel(paramParcel, 0);
    TextUtils.writeToParcel(this.mTitle, paramParcel, paramInt);
    TextUtils.writeToParcel(this.mContentDescription, paramParcel, paramInt);
    this.mActionIntent.writeToParcel(paramParcel, paramInt);
    paramParcel.writeBoolean(this.mEnabled);
    paramParcel.writeBoolean(this.mShouldShowIcon);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */