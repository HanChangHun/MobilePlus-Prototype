package android.app;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ITaskStackListener {
  public static ITaskStackListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.ITaskStackListener";
  }
  
  public void onActivityDismissingDockedStack() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (!this.mRemote.transact(6, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityDismissingDockedStack();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityForcedResizable(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(5, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityForcedResizable(paramString, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo paramRunningTaskInfo, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayFailed(paramRunningTaskInfo, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo paramRunningTaskInfo, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayRerouted(paramRunningTaskInfo, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityPinned(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(2, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityPinned(paramString, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityRequestedOrientationChanged(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(13, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityRequestedOrientationChanged(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo paramRunningTaskInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      boolean bool1 = false;
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      if (paramBoolean2) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      boolean bool2 = bool1;
      if (paramBoolean3)
        bool2 = true; 
      parcel.writeInt(bool2);
      if (!this.mRemote.transact(4, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityRestartAttempt(paramRunningTaskInfo, paramBoolean1, paramBoolean2, paramBoolean3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityRotation(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityRotation(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onActivityUnpinned() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (!this.mRemote.transact(3, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onActivityUnpinned();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(18, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onBackPressedOnTaskRoot(paramRunningTaskInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRecentTaskListFrozenChanged(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(23, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onRecentTaskListFrozenChanged(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRecentTaskListUpdated() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (!this.mRemote.transact(22, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onRecentTaskListUpdated();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSingleTaskDisplayDrawn(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(19, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onSingleTaskDisplayDrawn(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSingleTaskDisplayEmpty(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(20, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onSingleTaskDisplayEmpty(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSizeCompatModeActivityChanged(int paramInt, IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(17, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onSizeCompatModeActivityChanged(paramInt, paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskCreated(int paramInt, ComponentName paramComponentName) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (paramComponentName != null) {
        parcel.writeInt(1);
        paramComponentName.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskCreated(paramInt, paramComponentName);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(12, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskDescriptionChanged(paramRunningTaskInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskDisplayChanged(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(21, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskDisplayChanged(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskFocusChanged(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(24, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskFocusChanged(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskMovedToFront(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskMovedToFront(paramRunningTaskInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskProfileLocked(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(15, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskProfileLocked(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (paramRunningTaskInfo != null) {
        parcel.writeInt(1);
        paramRunningTaskInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskRemovalStarted(paramRunningTaskInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskRemoved(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskRemoved(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskRequestedOrientationChanged(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(25, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskRequestedOrientationChanged(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskSnapshotChanged(int paramInt, ActivityManager.TaskSnapshot paramTaskSnapshot) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      parcel.writeInt(paramInt);
      if (paramTaskSnapshot != null) {
        parcel.writeInt(1);
        paramTaskSnapshot.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(16, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskSnapshotChanged(paramInt, paramTaskSnapshot);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTaskStackChanged() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.ITaskStackListener");
      if (!this.mRemote.transact(1, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
        ITaskStackListener.Stub.getDefaultImpl().onTaskStackChanged();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITaskStackListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */