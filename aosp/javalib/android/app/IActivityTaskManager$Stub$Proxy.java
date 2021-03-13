package android.app;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.ComponentName;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.ParceledListSlice;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.service.voice.IVoiceInteractionSession;
import android.text.TextUtils;
import android.view.IRecentsAnimationRunner;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationDefinition;
import android.window.IWindowOrganizerController;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.policy.IKeyguardDismissCallback;
import java.util.List;

class Proxy implements IActivityTaskManager {
  public static IActivityTaskManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void activityDestroyed(IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(23, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityDestroyed(paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void activityIdle(IBinder paramIBinder, Configuration paramConfiguration, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel.writeStrongBinder(paramIBinder);
      boolean bool = false;
      if (paramConfiguration != null) {
        parcel.writeInt(1);
        paramConfiguration.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(18, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityIdle(paramIBinder, paramConfiguration, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void activityPaused(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityPaused(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void activityRelaunched(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityRelaunched(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void activityResumed(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityResumed(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void activityStopped(IBinder paramIBinder, Bundle paramBundle, PersistableBundle paramPersistableBundle, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramPersistableBundle != null) {
        parcel1.writeInt(1);
        paramPersistableBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityStopped(paramIBinder, paramBundle, paramPersistableBundle, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void activityTopResumedStateLost() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().activityTopResumedStateLost();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int addAppTask(IBinder paramIBinder, Intent paramIntent, ActivityManager.TaskDescription paramTaskDescription, Bitmap paramBitmap) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramTaskDescription != null) {
        parcel1.writeInt(1);
        paramTaskDescription.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBitmap != null) {
        parcel1.writeInt(1);
        paramBitmap.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().addAppTask(paramIBinder, paramIntent, paramTaskDescription, paramBitmap); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void alwaysShowUnsupportedCompileSdkWarning(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().alwaysShowUnsupportedCompileSdkWarning(paramComponentName);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancelRecentsAnimation(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().cancelRecentsAnimation(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelTaskWindowTransition(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().cancelTaskWindowTransition(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearLaunchParamsForPackages(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().clearLaunchParamsForPackages(paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean convertFromTranslucent(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(43, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().convertFromTranslucent(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean convertToTranslucent(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().convertToTranslucent(paramIBinder, paramBundle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dismissKeyguard(IBinder paramIBinder, IKeyguardDismissCallback paramIKeyguardDismissCallback, CharSequence paramCharSequence) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramIKeyguardDismissCallback != null) {
        iBinder = paramIKeyguardDismissCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().dismissKeyguard(paramIBinder, paramIKeyguardDismissCallback, paramCharSequence);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean enterPictureInPictureMode(IBinder paramIBinder, PictureInPictureParams paramPictureInPictureParams) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramPictureInPictureParams != null) {
        parcel1.writeInt(1);
        paramPictureInPictureParams.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().enterPictureInPictureMode(paramIBinder, paramPictureInPictureParams);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean finishActivity(IBinder paramIBinder, int paramInt1, Intent paramIntent, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt1);
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().finishActivity(paramIBinder, paramInt1, paramIntent, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean finishActivityAffinity(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().finishActivityAffinity(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishSubActivity(IBinder paramIBinder, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().finishSubActivity(paramIBinder, paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishVoiceTask(IVoiceInteractionSession paramIVoiceInteractionSession) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIVoiceInteractionSession != null) {
        iBinder = paramIVoiceInteractionSession.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().finishVoiceTask(paramIVoiceInteractionSession);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getActivityClassForToken(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getActivityClassForToken(paramIBinder); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        paramIBinder = null;
      } 
      return (ComponentName)paramIBinder;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getActivityOptions(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getActivityOptions(paramIBinder); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        paramIBinder = null;
      } 
      return (Bundle)paramIBinder;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getAllStackInfos(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.StackInfo> getAllStackInfosOnDisplay(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getAllStackInfosOnDisplay(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Point getAppTaskThumbnailSize() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Point point;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        point = IActivityTaskManager.Stub.getDefaultImpl().getAppTaskThumbnailSize();
        return point;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        point = (Point)Point.CREATOR.createFromParcel(parcel2);
      } else {
        point = null;
      } 
      return point;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<IBinder> getAppTasks(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getAppTasks(paramString); 
      parcel2.readException();
      return parcel2.createBinderArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bundle getAssistContextExtras(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Bundle bundle;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bundle = IActivityTaskManager.Stub.getDefaultImpl().getAssistContextExtras(paramInt);
        return bundle;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
      } else {
        bundle = null;
      } 
      return bundle;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName getCallingActivity(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getCallingActivity(paramIBinder); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
      } else {
        paramIBinder = null;
      } 
      return (ComponentName)paramIBinder;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getCallingPackage(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getCallingPackage(paramIBinder); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ConfigurationInfo configurationInfo;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        configurationInfo = IActivityTaskManager.Stub.getDefaultImpl().getDeviceConfigurationInfo();
        return configurationInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        configurationInfo = (ConfigurationInfo)ConfigurationInfo.CREATOR.createFromParcel(parcel2);
      } else {
        configurationInfo = null;
      } 
      return configurationInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getDisplayId(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getDisplayId(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.RunningTaskInfo> getFilteredTasks(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getFilteredTasks(paramInt, paramBoolean); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ActivityManager.StackInfo stackInfo;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getFocusedStackInfo();
        return stackInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
      } else {
        stackInfo = null;
      } 
      return stackInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getFrontActivityScreenCompatMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getFrontActivityScreenCompatMode(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IActivityTaskManager";
  }
  
  public int getLastResumedActivityUserId() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getLastResumedActivityUserId(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getLaunchedFromPackage(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getLaunchedFromPackage(paramIBinder); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getLaunchedFromUid(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getLaunchedFromUid(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getLockTaskModeState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getLockTaskModeState(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getMaxNumPictureInPictureActions(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getMaxNumPictureInPictureActions(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getPackageAskScreenCompat(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(150, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().getPackageAskScreenCompat(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getPackageForToken(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getPackageForToken(paramIBinder); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPackageScreenCompatMode(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getPackageScreenCompatMode(paramString); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice getRecentTasks(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IActivityTaskManager.Stub.getDefaultImpl().getRecentTasks(paramInt1, paramInt2, paramInt3);
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getRequestedOrientation(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getRequestedOrientation(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityManager.StackInfo getStackInfo(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ActivityManager.StackInfo stackInfo;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getStackInfo(paramInt1, paramInt2);
        return stackInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
      } else {
        stackInfo = null;
      } 
      return stackInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityManager.StackInfo getStackInfoOnDisplay(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ActivityManager.StackInfo stackInfo;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getStackInfoOnDisplay(paramInt1, paramInt2, paramInt3);
        return stackInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
      } else {
        stackInfo = null;
      } 
      return stackInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Rect getTaskBounds(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Rect rect;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        rect = IActivityTaskManager.Stub.getDefaultImpl().getTaskBounds(paramInt);
        return rect;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        rect = (Rect)Rect.CREATOR.createFromParcel(parcel2);
      } else {
        rect = null;
      } 
      return rect;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityManager.TaskDescription getTaskDescription(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ActivityManager.TaskDescription taskDescription;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        taskDescription = IActivityTaskManager.Stub.getDefaultImpl().getTaskDescription(paramInt);
        return taskDescription;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        taskDescription = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel2);
      } else {
        taskDescription = null;
      } 
      return taskDescription;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Bitmap getTaskDescriptionIcon(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getTaskDescriptionIcon(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (Bitmap)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getTaskForActivity(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        i = IActivityTaskManager.Stub.getDefaultImpl().getTaskForActivity(paramIBinder, paramBoolean);
        return i;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      return i;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ActivityManager.TaskSnapshot getTaskSnapshot(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      ActivityManager.TaskSnapshot taskSnapshot;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        taskSnapshot = IActivityTaskManager.Stub.getDefaultImpl().getTaskSnapshot(paramInt, paramBoolean);
        return taskSnapshot;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel2);
      } else {
        taskSnapshot = null;
      } 
      return taskSnapshot;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.RunningTaskInfo> getTasks(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getTasks(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder getUriPermissionOwnerForActivity(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        paramIBinder = IActivityTaskManager.Stub.getDefaultImpl().getUriPermissionOwnerForActivity(paramIBinder);
        return paramIBinder;
      } 
      parcel2.readException();
      paramIBinder = parcel2.readStrongBinder();
      return paramIBinder;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IWindowOrganizerController getWindowOrganizerController() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
        return IActivityTaskManager.Stub.getDefaultImpl().getWindowOrganizerController(); 
      parcel2.readException();
      return IWindowOrganizerController.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void invalidateHomeTaskSnapshot(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().invalidateHomeTaskSnapshot(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isActivityStartAllowedOnDisplay(int paramInt1, Intent paramIntent, String paramString, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isActivityStartAllowedOnDisplay(paramInt1, paramIntent, paramString, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAssistDataAllowedOnCurrentActivity() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(102, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isAssistDataAllowedOnCurrentActivity();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isImmersive(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(48, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isImmersive(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isInLockTaskMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(64, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isInLockTaskMode();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isRootVoiceInteraction(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(104, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isRootVoiceInteraction(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isTopActivityImmersive() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(50, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isTopActivityImmersive();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isTopOfTask(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(72, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().isTopOfTask(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void keyguardGoingAway(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().keyguardGoingAway(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean launchAssistIntent(Intent paramIntent, int paramInt1, String paramString, int paramInt2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeInt(paramInt2);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                bool = IActivityTaskManager.Stub.getDefaultImpl().launchAssistIntent(paramIntent, paramInt1, paramString, paramInt2, paramBundle);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              paramInt1 = parcel2.readInt();
              if (paramInt1 == 0)
                bool = false; 
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIntent;
  }
  
  public boolean moveActivityTaskToBack(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityTaskManager.Stub.getDefaultImpl().moveActivityTaskToBack(paramIBinder, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void moveStackToDisplay(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().moveStackToDisplay(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void moveTaskToFront(IApplicationThread paramIApplicationThread, String paramString, int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().moveTaskToFront(paramIApplicationThread, paramString, paramInt1, paramInt2, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void moveTaskToStack(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().moveTaskToStack(paramInt1, paramInt2, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean moveTopActivityToPinnedStack(int paramInt, Rect paramRect) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      boolean bool = true;
      if (paramRect != null) {
        parcel1.writeInt(1);
        paramRect.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().moveTopActivityToPinnedStack(paramInt, paramRect);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean navigateUpTo(IBinder paramIBinder, Intent paramIntent1, int paramInt, Intent paramIntent2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramIntent1 != null) {
        parcel1.writeInt(1);
        paramIntent1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (paramIntent2 != null) {
        parcel1.writeInt(1);
        paramIntent2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().navigateUpTo(paramIBinder, paramIntent1, paramInt, paramIntent2);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyActivityDrawn(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().notifyActivityDrawn(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyEnterAnimationComplete(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().notifyEnterAnimationComplete(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyLaunchTaskBehindComplete(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().notifyLaunchTaskBehindComplete(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onBackPressedOnTaskRoot(IBinder paramIBinder, IRequestFinishCallback paramIRequestFinishCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramIRequestFinishCallback != null) {
        iBinder = paramIRequestFinishCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().onBackPressedOnTaskRoot(paramIBinder, paramIRequestFinishCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void overridePendingTransition(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().overridePendingTransition(paramIBinder, paramString, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void positionTaskInStack(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().positionTaskInStack(paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerRemoteAnimationForNextActivityStart(String paramString, RemoteAnimationAdapter paramRemoteAnimationAdapter) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      if (paramRemoteAnimationAdapter != null) {
        parcel1.writeInt(1);
        paramRemoteAnimationAdapter.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimationForNextActivityStart(paramString, paramRemoteAnimationAdapter);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerRemoteAnimations(IBinder paramIBinder, RemoteAnimationDefinition paramRemoteAnimationDefinition) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramRemoteAnimationDefinition != null) {
        parcel1.writeInt(1);
        paramRemoteAnimationDefinition.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimations(paramIBinder, paramRemoteAnimationDefinition);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerRemoteAnimationsForDisplay(int paramInt, RemoteAnimationDefinition paramRemoteAnimationDefinition) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (paramRemoteAnimationDefinition != null) {
        parcel1.writeInt(1);
        paramRemoteAnimationDefinition.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimationsForDisplay(paramInt, paramRemoteAnimationDefinition);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramITaskStackListener != null) {
        iBinder = paramITaskStackListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().registerTaskStackListener(paramITaskStackListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean releaseActivityInstance(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(77, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().releaseActivityInstance(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void releaseSomeActivities(IApplicationThread paramIApplicationThread) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().releaseSomeActivities(paramIApplicationThread);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeAllVisibleRecentTasks() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().removeAllVisibleRecentTasks();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeStack(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().removeStack(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeStacksInWindowingModes(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().removeStacksInWindowingModes(paramArrayOfint);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeStacksWithActivityTypes(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().removeStacksWithActivityTypes(paramArrayOfint);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeTask(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(30, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().removeTask(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportActivityFullyDrawn(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().reportActivityFullyDrawn(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportAssistContextExtras(IBinder paramIBinder, Bundle paramBundle, AssistStructure paramAssistStructure, AssistContent paramAssistContent, Uri paramUri) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAssistStructure != null) {
        parcel1.writeInt(1);
        paramAssistStructure.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAssistContent != null) {
        parcel1.writeInt(1);
        paramAssistContent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().reportAssistContextExtras(paramIBinder, paramBundle, paramAssistStructure, paramAssistContent, paramUri);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportSizeConfigurations(IBinder paramIBinder, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeIntArray(paramArrayOfint1);
      parcel1.writeIntArray(paramArrayOfint2);
      parcel1.writeIntArray(paramArrayOfint3);
      if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().reportSizeConfigurations(paramIBinder, paramArrayOfint1, paramArrayOfint2, paramArrayOfint3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean requestAssistContextExtras(int paramInt, IAssistDataReceiver paramIAssistDataReceiver, Bundle paramBundle, IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      try {
        IBinder iBinder;
        parcel1.writeInt(paramInt);
        if (paramIAssistDataReceiver != null) {
          iBinder = paramIAssistDataReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        boolean bool = true;
        if (paramBundle != null) {
          parcel1.writeInt(1);
          paramBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          boolean bool1;
          parcel1.writeStrongBinder(paramIBinder);
          if (paramBoolean1) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          parcel1.writeInt(bool1);
          if (paramBoolean2) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          parcel1.writeInt(bool1);
          try {
            if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
              paramBoolean1 = IActivityTaskManager.Stub.getDefaultImpl().requestAssistContextExtras(paramInt, paramIAssistDataReceiver, paramBundle, paramIBinder, paramBoolean1, paramBoolean2);
              parcel2.recycle();
              parcel1.recycle();
              return paramBoolean1;
            } 
            parcel2.readException();
            paramInt = parcel2.readInt();
            if (paramInt != 0) {
              paramBoolean1 = bool;
            } else {
              paramBoolean1 = false;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return paramBoolean1;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIAssistDataReceiver;
  }
  
  public boolean requestAutofillData(IAssistDataReceiver paramIAssistDataReceiver, Bundle paramBundle, IBinder paramIBinder, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIAssistDataReceiver != null) {
        iBinder = paramIAssistDataReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      boolean bool = true;
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().requestAutofillData(paramIAssistDataReceiver, paramBundle, paramIBinder, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestPictureInPictureMode(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().requestPictureInPictureMode(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder requestStartActivityPermissionToken(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        paramIBinder = IActivityTaskManager.Stub.getDefaultImpl().requestStartActivityPermissionToken(paramIBinder);
        return paramIBinder;
      } 
      parcel2.readException();
      paramIBinder = parcel2.readStrongBinder();
      return paramIBinder;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resizeDockedStack(Rect paramRect1, Rect paramRect2, Rect paramRect3, Rect paramRect4, Rect paramRect5) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramRect1 != null) {
        parcel1.writeInt(1);
        paramRect1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramRect2 != null) {
        parcel1.writeInt(1);
        paramRect2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramRect3 != null) {
        parcel1.writeInt(1);
        paramRect3.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramRect4 != null) {
        parcel1.writeInt(1);
        paramRect4.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramRect5 != null) {
        parcel1.writeInt(1);
        paramRect5.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().resizeDockedStack(paramRect1, paramRect2, paramRect3, paramRect4, paramRect5);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean resizeTask(int paramInt1, Rect paramRect, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      boolean bool = true;
      if (paramRect != null) {
        parcel1.writeInt(1);
        paramRect.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().resizeTask(paramInt1, paramRect, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restartActivityProcessIfVisible(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().restartActivityProcessIfVisible(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resumeAppSwitches() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().resumeAppSwitches();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setActivityController(IActivityController paramIActivityController, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIActivityController != null) {
        iBinder = paramIActivityController.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setActivityController(paramIActivityController, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDisablePreviewScreenshots(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setDisablePreviewScreenshots(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDisplayToSingleTaskInstance(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setDisplayToSingleTaskInstance(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setFocusedStack(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setFocusedStack(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setFocusedTask(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setFocusedTask(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setFrontActivityScreenCompatMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setFrontActivityScreenCompatMode(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setImmersive(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setImmersive(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setInheritShowWhenLocked(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setInheritShowWhenLocked(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setLockScreenShown(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      boolean bool1 = true;
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setLockScreenShown(paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPackageAskScreenCompat(String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setPackageAskScreenCompat(paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPackageScreenCompatMode(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setPackageScreenCompatMode(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPersistentVrThread(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setPersistentVrThread(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPictureInPictureParams(IBinder paramIBinder, PictureInPictureParams paramPictureInPictureParams) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramPictureInPictureParams != null) {
        parcel1.writeInt(1);
        paramPictureInPictureParams.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setPictureInPictureParams(paramIBinder, paramPictureInPictureParams);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setRequestedOrientation(IBinder paramIBinder, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setRequestedOrientation(paramIBinder, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setShowWhenLocked(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setShowWhenLocked(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSplitScreenResizing(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setSplitScreenResizing(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTaskDescription(IBinder paramIBinder, ActivityManager.TaskDescription paramTaskDescription) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramTaskDescription != null) {
        parcel1.writeInt(1);
        paramTaskDescription.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setTaskDescription(paramIBinder, paramTaskDescription);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTaskResizeable(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setTaskResizeable(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setTaskWindowingMode(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityTaskManager.Stub.getDefaultImpl().setTaskWindowingMode(paramInt1, paramInt2, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setTaskWindowingModeSplitScreenPrimary(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityTaskManager.Stub.getDefaultImpl().setTaskWindowingModeSplitScreenPrimary(paramInt, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTurnScreenOn(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setTurnScreenOn(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setVoiceKeepAwake(IVoiceInteractionSession paramIVoiceInteractionSession, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIVoiceInteractionSession != null) {
        iBinder = paramIVoiceInteractionSession.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setVoiceKeepAwake(paramIVoiceInteractionSession, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int setVrMode(IBinder paramIBinder, boolean paramBoolean, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        i = IActivityTaskManager.Stub.getDefaultImpl().setVrMode(paramIBinder, paramBoolean, paramComponentName);
        return i;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      return i;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setVrThread(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().setVrThread(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean shouldUpRecreateTask(IBinder paramIBinder, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(34, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().shouldUpRecreateTask(paramIBinder, paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean showAssistFromActivity(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().showAssistFromActivity(paramIBinder, paramBundle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void showLockTaskEscapeMessage(IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(105, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().showLockTaskEscapeMessage(paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public int startActivities(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, IBinder paramIBinder, Bundle paramBundle, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeTypedArray((Parcelable[])paramArrayOfIntent, 0);
            parcel1.writeStringArray(paramArrayOfString);
            parcel1.writeStrongBinder(paramIBinder);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            parcel1.writeInt(paramInt);
            if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
              paramInt = IActivityTaskManager.Stub.getDefaultImpl().startActivities(paramIApplicationThread, paramString1, paramString2, paramArrayOfIntent, paramArrayOfString, paramIBinder, paramBundle, paramInt);
              parcel2.recycle();
              parcel1.recycle();
              return paramInt;
            } 
            parcel2.readException();
            paramInt = parcel2.readInt();
            parcel2.recycle();
            parcel1.recycle();
            return paramInt;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public int startActivity(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString3);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString4);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramProfilerInfo != null) {
        parcel1.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityTaskManager.startActivity(paramIApplicationThread, paramString1, paramString2, paramIntent, paramString3, paramIBinder, paramString4, paramInt1, paramInt2, paramProfilerInfo, paramBundle);
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } else {
        Parcel parcel = parcel2;
        parcel.readException();
        paramInt1 = parcel.readInt();
        parcel.recycle();
        parcel1.recycle();
        return paramInt1;
      } 
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public WaitResult startActivityAndWait(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString3);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString4);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramProfilerInfo != null) {
        parcel1.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt3);
      boolean bool = this.mRemote.transact(7, parcel1, parcel2, 0);
      if (!bool)
        try {
          if (IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              WaitResult waitResult = iActivityTaskManager.startActivityAndWait(paramIApplicationThread, paramString1, paramString2, paramIntent, paramString3, paramIBinder, paramString4, paramInt1, paramInt2, paramProfilerInfo, paramBundle, paramInt3);
              parcel2.recycle();
              parcel1.recycle();
              return waitResult;
            } finally {}
            parcel2.recycle();
            parcel1.recycle();
            throw paramIApplicationThread;
          } 
        } finally {} 
      try {
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Parcelable.Creator<WaitResult> creator = WaitResult.CREATOR;
          try {
            WaitResult waitResult = (WaitResult)creator.createFromParcel(parcel2);
          } finally {}
        } else {
          paramIApplicationThread = null;
        } 
        parcel2.recycle();
        parcel1.recycle();
        return (WaitResult)paramIApplicationThread;
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public int startActivityAsCaller(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder1, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, IBinder paramIBinder2, boolean paramBoolean, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString2);
      parcel1.writeStrongBinder(paramIBinder1);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramProfilerInfo != null) {
        parcel1.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder2);
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityTaskManager.startActivityAsCaller(paramIApplicationThread, paramString1, paramIntent, paramString2, paramIBinder1, paramString3, paramInt1, paramInt2, paramProfilerInfo, paramBundle, paramIBinder2, paramBoolean, paramInt3);
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } else {
        Parcel parcel = parcel2;
        parcel.readException();
        paramInt1 = parcel.readInt();
        parcel.recycle();
        parcel1.recycle();
        return paramInt1;
      } 
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public int startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString3);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString4);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramProfilerInfo != null) {
        parcel1.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityTaskManager.startActivityAsUser(paramIApplicationThread, paramString1, paramString2, paramIntent, paramString3, paramIBinder, paramString4, paramInt1, paramInt2, paramProfilerInfo, paramBundle, paramInt3);
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } else {
        Parcel parcel = parcel2;
        parcel.readException();
        paramInt1 = parcel.readInt();
        parcel.recycle();
        parcel1.recycle();
        return paramInt1;
      } 
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public int startActivityFromRecents(int paramInt, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityTaskManager.Stub.getDefaultImpl().startActivityFromRecents(paramInt, paramBundle);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int startActivityIntentSender(IApplicationThread paramIApplicationThread, IIntentSender paramIIntentSender, IBinder paramIBinder1, Intent paramIntent, String paramString1, IBinder paramIBinder2, String paramString2, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        try {
          iBinder2 = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIIntentSender != null)
        iBinder2 = paramIIntentSender.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeStrongBinder(paramIBinder1);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeStrongBinder(paramIBinder2);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityTaskManager.startActivityIntentSender(paramIApplicationThread, paramIIntentSender, paramIBinder1, paramIntent, paramString1, paramIBinder2, paramString2, paramInt1, paramInt2, paramInt3, paramBundle);
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } else {
        Parcel parcel = parcel2;
        parcel.readException();
        paramInt1 = parcel.readInt();
        parcel.recycle();
        parcel1.recycle();
        return paramInt1;
      } 
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public int startActivityWithConfig(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, Configuration paramConfiguration, Bundle paramBundle, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString3);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString4);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramConfiguration != null) {
        parcel1.writeInt(1);
        paramConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityTaskManager.startActivityWithConfig(paramIApplicationThread, paramString1, paramString2, paramIntent, paramString3, paramIBinder, paramString4, paramInt1, paramInt2, paramConfiguration, paramBundle, paramInt3);
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } else {
        Parcel parcel = parcel2;
        parcel.readException();
        paramInt1 = parcel.readInt();
        parcel.recycle();
        parcel1.recycle();
        return paramInt1;
      } 
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public int startAssistantActivity(String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, String paramString3, Bundle paramBundle, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          parcel1.writeInt(paramInt1);
          parcel1.writeInt(paramInt2);
          if (paramIntent != null) {
            parcel1.writeInt(1);
            paramIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(paramString3);
          if (paramBundle != null) {
            parcel1.writeInt(1);
            paramBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(paramInt3);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            paramInt1 = IActivityTaskManager.Stub.getDefaultImpl().startAssistantActivity(paramString1, paramString2, paramInt1, paramInt2, paramIntent, paramString3, paramBundle, paramInt3);
            parcel2.recycle();
            parcel1.recycle();
            return paramInt1;
          } 
          parcel2.readException();
          paramInt1 = parcel2.readInt();
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public boolean startDreamActivity(Intent paramIntent) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().startDreamActivity(paramIntent);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startLocalVoiceInteraction(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().startLocalVoiceInteraction(paramIBinder, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startLockTaskModeByToken(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().startLockTaskModeByToken(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean startNextMatchingActivity(IBinder paramIBinder, Intent paramIntent, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().startNextMatchingActivity(paramIBinder, paramIntent, paramBundle);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startRecentsActivity(Intent paramIntent, IAssistDataReceiver paramIAssistDataReceiver, IRecentsAnimationRunner paramIRecentsAnimationRunner) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      IBinder iBinder1 = null;
      if (paramIAssistDataReceiver != null) {
        iBinder2 = paramIAssistDataReceiver.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIRecentsAnimationRunner != null)
        iBinder2 = paramIRecentsAnimationRunner.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().startRecentsActivity(paramIntent, paramIAssistDataReceiver, paramIRecentsAnimationRunner);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startSystemLockTaskMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().startSystemLockTaskMode(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int startVoiceActivity(String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, String paramString3, IVoiceInteractionSession paramIVoiceInteractionSession, IVoiceInteractor paramIVoiceInteractor, int paramInt3, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramIntent != null) {
        try {
          parcel1.writeInt(1);
          paramIntent.writeToParcel(parcel1, 0);
        } finally {}
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString3);
      IBinder iBinder1 = null;
      if (paramIVoiceInteractionSession != null) {
        iBinder2 = paramIVoiceInteractionSession.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIVoiceInteractor != null)
        iBinder2 = paramIVoiceInteractor.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeInt(paramInt3);
      if (paramProfilerInfo != null) {
        parcel1.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt4);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityTaskManager.startVoiceActivity(paramString1, paramString2, paramInt1, paramInt2, paramIntent, paramString3, paramIVoiceInteractionSession, paramIVoiceInteractor, paramInt3, paramProfilerInfo, paramBundle, paramInt4);
          parcel2.recycle();
          parcel1.recycle();
          return paramInt1;
        } finally {}
      } else {
        Parcel parcel = parcel2;
        parcel.readException();
        paramInt1 = parcel.readInt();
        parcel.recycle();
        parcel1.recycle();
        return paramInt1;
      } 
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public void stopAppSwitches() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().stopAppSwitches();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopLocalVoiceInteraction(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(123, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().stopLocalVoiceInteraction(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopLockTaskModeByToken(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().stopLockTaskModeByToken(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopSystemLockTaskMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().stopSystemLockTaskMode();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean supportsLocalVoiceInteraction() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(124, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().supportsLocalVoiceInteraction();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void suppressResizeConfigChanges(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().suppressResizeConfigChanges(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void toggleFreeformWindowingMode(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().toggleFreeformWindowingMode(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unhandledBack() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().unhandledBack();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterRemoteAnimations(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().unregisterRemoteAnimations(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      if (paramITaskStackListener != null) {
        iBinder = paramITaskStackListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().unregisterTaskStackListener(paramITaskStackListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean updateConfiguration(Configuration paramConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      boolean bool = true;
      if (paramConfiguration != null) {
        parcel1.writeInt(1);
        paramConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().updateConfiguration(paramConfiguration);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateLockTaskFeatures(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().updateLockTaskFeatures(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateLockTaskPackages(int paramInt, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        IActivityTaskManager.Stub.getDefaultImpl().updateLockTaskPackages(paramInt, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean willActivityBeVisible(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(40, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
        bool = IActivityTaskManager.Stub.getDefaultImpl().willActivityBeVisible(paramIBinder);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityTaskManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */