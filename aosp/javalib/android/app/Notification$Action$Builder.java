package android.app;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;

public final class Builder {
  private boolean mAllowGeneratedReplies = true;
  
  private final Bundle mExtras;
  
  private final Icon mIcon;
  
  private final PendingIntent mIntent;
  
  private boolean mIsContextual;
  
  private ArrayList<RemoteInput> mRemoteInputs;
  
  private int mSemanticAction;
  
  private final CharSequence mTitle;
  
  @Deprecated
  public Builder(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent) {
    this(Icon.createWithResource("", paramInt), paramCharSequence, paramPendingIntent);
  }
  
  public Builder(Notification.Action paramAction) {
    this(paramAction.getIcon(), paramAction.title, paramAction.actionIntent, new Bundle(Notification.Action.access$100(paramAction)), paramAction.getRemoteInputs(), paramAction.getAllowGeneratedReplies(), paramAction.getSemanticAction());
  }
  
  public Builder(Icon paramIcon, CharSequence paramCharSequence, PendingIntent paramPendingIntent) {
    this(paramIcon, paramCharSequence, paramPendingIntent, new Bundle(), null, true, 0);
  }
  
  private Builder(Icon paramIcon, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput, boolean paramBoolean, int paramInt) {
    this.mIcon = paramIcon;
    this.mTitle = paramCharSequence;
    this.mIntent = paramPendingIntent;
    this.mExtras = paramBundle;
    if (paramArrayOfRemoteInput != null) {
      ArrayList<RemoteInput> arrayList = new ArrayList(paramArrayOfRemoteInput.length);
      this.mRemoteInputs = arrayList;
      Collections.addAll(arrayList, paramArrayOfRemoteInput);
    } 
    this.mAllowGeneratedReplies = paramBoolean;
    this.mSemanticAction = paramInt;
  }
  
  private void checkContextualActionNullFields() {
    if (!this.mIsContextual)
      return; 
    if (this.mIcon != null) {
      if (this.mIntent != null)
        return; 
      throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
    } 
    throw new NullPointerException("Contextual Actions must contain a valid icon");
  }
  
  public Builder addExtras(Bundle paramBundle) {
    if (paramBundle != null)
      this.mExtras.putAll(paramBundle); 
    return this;
  }
  
  public Builder addRemoteInput(RemoteInput paramRemoteInput) {
    if (this.mRemoteInputs == null)
      this.mRemoteInputs = new ArrayList<>(); 
    this.mRemoteInputs.add(paramRemoteInput);
    return this;
  }
  
  public Notification.Action build() {
    RemoteInput[] arrayOfRemoteInput1;
    checkContextualActionNullFields();
    ArrayList<RemoteInput> arrayList1 = new ArrayList();
    RemoteInput[] arrayOfRemoteInput2 = (RemoteInput[])Notification.access$000(this.mExtras, "android.extra.DATA_ONLY_INPUTS", RemoteInput.class);
    if (arrayOfRemoteInput2 != null) {
      int i = arrayOfRemoteInput2.length;
      for (byte b = 0; b < i; b++)
        arrayList1.add(arrayOfRemoteInput2[b]); 
    } 
    ArrayList<RemoteInput> arrayList2 = new ArrayList();
    ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
    if (arrayList3 != null)
      for (RemoteInput remoteInput : arrayList3) {
        if (remoteInput.isDataOnly()) {
          arrayList1.add(remoteInput);
          continue;
        } 
        arrayList2.add(remoteInput);
      }  
    if (!arrayList1.isEmpty()) {
      RemoteInput[] arrayOfRemoteInput = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
      this.mExtras.putParcelableArray("android.extra.DATA_ONLY_INPUTS", (Parcelable[])arrayOfRemoteInput);
    } 
    if (arrayList2.isEmpty()) {
      arrayList2 = null;
    } else {
      arrayOfRemoteInput1 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]);
    } 
    return new Notification.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mIsContextual, null);
  }
  
  public Builder extend(Notification.Action.Extender paramExtender) {
    paramExtender.extend(this);
    return this;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public Builder setAllowGeneratedReplies(boolean paramBoolean) {
    this.mAllowGeneratedReplies = paramBoolean;
    return this;
  }
  
  public Builder setContextual(boolean paramBoolean) {
    this.mIsContextual = paramBoolean;
    return this;
  }
  
  public Builder setSemanticAction(int paramInt) {
    this.mSemanticAction = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$Action$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */