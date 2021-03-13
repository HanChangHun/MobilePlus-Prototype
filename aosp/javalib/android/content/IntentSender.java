package android.content;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.AndroidException;

public class IntentSender implements Parcelable {
  public static final Parcelable.Creator<IntentSender> CREATOR = new Parcelable.Creator<IntentSender>() {
      public IntentSender createFromParcel(Parcel param1Parcel) {
        IBinder iBinder = param1Parcel.readStrongBinder();
        if (iBinder != null) {
          IntentSender intentSender = new IntentSender(iBinder);
        } else {
          iBinder = null;
        } 
        return (IntentSender)iBinder;
      }
      
      public IntentSender[] newArray(int param1Int) {
        return new IntentSender[param1Int];
      }
    };
  
  private final IIntentSender mTarget;
  
  IBinder mWhitelistToken;
  
  public IntentSender(IIntentSender paramIIntentSender) {
    this.mTarget = paramIIntentSender;
  }
  
  public IntentSender(IIntentSender paramIIntentSender, IBinder paramIBinder) {
    this.mTarget = paramIIntentSender;
    this.mWhitelistToken = paramIBinder;
  }
  
  public IntentSender(IBinder paramIBinder) {
    this.mTarget = IIntentSender.Stub.asInterface(paramIBinder);
  }
  
  public static IntentSender readIntentSenderOrNullFromParcel(Parcel paramParcel) {
    IBinder iBinder = paramParcel.readStrongBinder();
    if (iBinder != null) {
      IntentSender intentSender = new IntentSender(iBinder);
    } else {
      iBinder = null;
    } 
    return (IntentSender)iBinder;
  }
  
  public static void writeIntentSenderOrNullToParcel(IntentSender paramIntentSender, Parcel paramParcel) {
    if (paramIntentSender != null) {
      IBinder iBinder = paramIntentSender.mTarget.asBinder();
    } else {
      paramIntentSender = null;
    } 
    paramParcel.writeStrongBinder((IBinder)paramIntentSender);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof IntentSender) ? this.mTarget.asBinder().equals(((IntentSender)paramObject).mTarget.asBinder()) : false;
  }
  
  public String getCreatorPackage() {
    try {
      return ActivityManager.getService().getPackageForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public int getCreatorUid() {
    try {
      return ActivityManager.getService().getUidForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      return -1;
    } 
  }
  
  public UserHandle getCreatorUserHandle() {
    UserHandle userHandle = null;
    try {
      int i = ActivityManager.getService().getUidForIntentSender(this.mTarget);
      if (i > 0)
        userHandle = new UserHandle(UserHandle.getUserId(i)); 
      return userHandle;
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public IIntentSender getTarget() {
    return this.mTarget;
  }
  
  @Deprecated
  public String getTargetPackage() {
    try {
      return ActivityManager.getService().getPackageForIntentSender(this.mTarget);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public IBinder getWhitelistToken() {
    return this.mWhitelistToken;
  }
  
  public int hashCode() {
    return this.mTarget.asBinder().hashCode();
  }
  
  public void sendIntent(Context paramContext, int paramInt, Intent paramIntent, OnFinished paramOnFinished, Handler paramHandler) throws SendIntentException {
    sendIntent(paramContext, paramInt, paramIntent, paramOnFinished, paramHandler, null);
  }
  
  public void sendIntent(Context paramContext, int paramInt, Intent paramIntent, OnFinished paramOnFinished, Handler paramHandler, String paramString) throws SendIntentException {
    if (paramIntent != null) {
      try {
        String str = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
      } catch (RemoteException null) {}
    } else {
      paramContext = null;
    } 
    IActivityManager iActivityManager = ActivityManager.getService();
    IIntentSender iIntentSender = this.mTarget;
    IBinder iBinder = this.mWhitelistToken;
    if (paramOnFinished != null) {
      FinishedDispatcher finishedDispatcher = new FinishedDispatcher();
      try {
        this(this, paramOnFinished, paramHandler);
        FinishedDispatcher finishedDispatcher1 = finishedDispatcher;
        if (iActivityManager.sendIntentSender(iIntentSender, iBinder, paramInt, paramIntent, (String)paramContext, finishedDispatcher1, paramString, null) >= 0)
          return; 
      } catch (RemoteException remoteException) {}
    } else {
      paramOnFinished = null;
      if (iActivityManager.sendIntentSender(iIntentSender, iBinder, paramInt, paramIntent, (String)remoteException, (IIntentReceiver)paramOnFinished, paramString, null) >= 0)
        return; 
    } 
    throw new SendIntentException();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("IntentSender{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(": ");
    IIntentSender iIntentSender = this.mTarget;
    if (iIntentSender != null) {
      IBinder iBinder = iIntentSender.asBinder();
    } else {
      iIntentSender = null;
    } 
    stringBuilder.append(iIntentSender);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.mTarget.asBinder());
  }
  
  private static class FinishedDispatcher extends IIntentReceiver.Stub implements Runnable {
    private final Handler mHandler;
    
    private Intent mIntent;
    
    private final IntentSender mIntentSender;
    
    private int mResultCode;
    
    private String mResultData;
    
    private Bundle mResultExtras;
    
    private final IntentSender.OnFinished mWho;
    
    FinishedDispatcher(IntentSender param1IntentSender, IntentSender.OnFinished param1OnFinished, Handler param1Handler) {
      this.mIntentSender = param1IntentSender;
      this.mWho = param1OnFinished;
      this.mHandler = param1Handler;
    }
    
    public void performReceive(Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2) {
      this.mIntent = param1Intent;
      this.mResultCode = param1Int1;
      this.mResultData = param1String;
      this.mResultExtras = param1Bundle;
      Handler handler = this.mHandler;
      if (handler == null) {
        run();
      } else {
        handler.post(this);
      } 
    }
    
    public void run() {
      this.mWho.onSendFinished(this.mIntentSender, this.mIntent, this.mResultCode, this.mResultData, this.mResultExtras);
    }
  }
  
  public static interface OnFinished {
    void onSendFinished(IntentSender param1IntentSender, Intent param1Intent, int param1Int, String param1String, Bundle param1Bundle);
  }
  
  public static class SendIntentException extends AndroidException {
    public SendIntentException() {}
    
    public SendIntentException(Exception param1Exception) {
      super(param1Exception);
    }
    
    public SendIntentException(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */