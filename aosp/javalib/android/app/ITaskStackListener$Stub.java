package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITaskStackListener {
  private static final String DESCRIPTOR = "android.app.ITaskStackListener";
  
  static final int TRANSACTION_onActivityDismissingDockedStack = 6;
  
  static final int TRANSACTION_onActivityForcedResizable = 5;
  
  static final int TRANSACTION_onActivityLaunchOnSecondaryDisplayFailed = 7;
  
  static final int TRANSACTION_onActivityLaunchOnSecondaryDisplayRerouted = 8;
  
  static final int TRANSACTION_onActivityPinned = 2;
  
  static final int TRANSACTION_onActivityRequestedOrientationChanged = 13;
  
  static final int TRANSACTION_onActivityRestartAttempt = 4;
  
  static final int TRANSACTION_onActivityRotation = 26;
  
  static final int TRANSACTION_onActivityUnpinned = 3;
  
  static final int TRANSACTION_onBackPressedOnTaskRoot = 18;
  
  static final int TRANSACTION_onRecentTaskListFrozenChanged = 23;
  
  static final int TRANSACTION_onRecentTaskListUpdated = 22;
  
  static final int TRANSACTION_onSingleTaskDisplayDrawn = 19;
  
  static final int TRANSACTION_onSingleTaskDisplayEmpty = 20;
  
  static final int TRANSACTION_onSizeCompatModeActivityChanged = 17;
  
  static final int TRANSACTION_onTaskCreated = 9;
  
  static final int TRANSACTION_onTaskDescriptionChanged = 12;
  
  static final int TRANSACTION_onTaskDisplayChanged = 21;
  
  static final int TRANSACTION_onTaskFocusChanged = 24;
  
  static final int TRANSACTION_onTaskMovedToFront = 11;
  
  static final int TRANSACTION_onTaskProfileLocked = 15;
  
  static final int TRANSACTION_onTaskRemovalStarted = 14;
  
  static final int TRANSACTION_onTaskRemoved = 10;
  
  static final int TRANSACTION_onTaskRequestedOrientationChanged = 25;
  
  static final int TRANSACTION_onTaskSnapshotChanged = 16;
  
  static final int TRANSACTION_onTaskStackChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.app.ITaskStackListener");
  }
  
  public static ITaskStackListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.ITaskStackListener");
    return (iInterface != null && iInterface instanceof ITaskStackListener) ? (ITaskStackListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITaskStackListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 26:
        return "onActivityRotation";
      case 25:
        return "onTaskRequestedOrientationChanged";
      case 24:
        return "onTaskFocusChanged";
      case 23:
        return "onRecentTaskListFrozenChanged";
      case 22:
        return "onRecentTaskListUpdated";
      case 21:
        return "onTaskDisplayChanged";
      case 20:
        return "onSingleTaskDisplayEmpty";
      case 19:
        return "onSingleTaskDisplayDrawn";
      case 18:
        return "onBackPressedOnTaskRoot";
      case 17:
        return "onSizeCompatModeActivityChanged";
      case 16:
        return "onTaskSnapshotChanged";
      case 15:
        return "onTaskProfileLocked";
      case 14:
        return "onTaskRemovalStarted";
      case 13:
        return "onActivityRequestedOrientationChanged";
      case 12:
        return "onTaskDescriptionChanged";
      case 11:
        return "onTaskMovedToFront";
      case 10:
        return "onTaskRemoved";
      case 9:
        return "onTaskCreated";
      case 8:
        return "onActivityLaunchOnSecondaryDisplayRerouted";
      case 7:
        return "onActivityLaunchOnSecondaryDisplayFailed";
      case 6:
        return "onActivityDismissingDockedStack";
      case 5:
        return "onActivityForcedResizable";
      case 4:
        return "onActivityRestartAttempt";
      case 3:
        return "onActivityUnpinned";
      case 2:
        return "onActivityPinned";
      case 1:
        break;
    } 
    return "onTaskStackChanged";
  }
  
  public static boolean setDefaultImpl(ITaskStackListener paramITaskStackListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITaskStackListener != null) {
        Proxy.sDefaultImpl = paramITaskStackListener;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 26:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onActivityRotation(paramParcel1.readInt());
          return true;
        case 25:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onTaskRequestedOrientationChanged(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 24:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool3 = true; 
          onTaskFocusChanged(paramInt1, bool3);
          return true;
        case 23:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          bool3 = bool1;
          if (paramParcel1.readInt() != 0)
            bool3 = true; 
          onRecentTaskListFrozenChanged(bool3);
          return true;
        case 22:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onRecentTaskListUpdated();
          return true;
        case 21:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onTaskDisplayChanged(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 20:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onSingleTaskDisplayEmpty(paramParcel1.readInt());
          return true;
        case 19:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onSingleTaskDisplayDrawn(paramParcel1.readInt());
          return true;
        case 18:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onBackPressedOnTaskRoot((ActivityManager.RunningTaskInfo)paramParcel1);
          return true;
        case 17:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onSizeCompatModeActivityChanged(paramParcel1.readInt(), paramParcel1.readStrongBinder());
          return true;
        case 16:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            ActivityManager.TaskSnapshot taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onTaskSnapshotChanged(paramInt1, (ActivityManager.TaskSnapshot)paramParcel1);
          return true;
        case 15:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onTaskProfileLocked(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 14:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onTaskRemovalStarted((ActivityManager.RunningTaskInfo)paramParcel1);
          return true;
        case 13:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onActivityRequestedOrientationChanged(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 12:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onTaskDescriptionChanged((ActivityManager.RunningTaskInfo)paramParcel1);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onTaskMovedToFront((ActivityManager.RunningTaskInfo)paramParcel1);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onTaskRemoved(paramParcel1.readInt());
          return true;
        case 9:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onTaskCreated(paramInt1, (ComponentName)paramParcel1);
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onActivityLaunchOnSecondaryDisplayRerouted((ActivityManager.RunningTaskInfo)paramParcel2, paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          onActivityLaunchOnSecondaryDisplayFailed((ActivityManager.RunningTaskInfo)paramParcel2, paramParcel1.readInt());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onActivityDismissingDockedStack();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onActivityForcedResizable(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          if (paramParcel1.readInt() != 0) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            bool3 = true;
          } else {
            bool3 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onActivityRestartAttempt((ActivityManager.RunningTaskInfo)paramParcel2, bool3, bool1, bool2);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onActivityUnpinned();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.ITaskStackListener");
          onActivityPinned(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.ITaskStackListener");
      onTaskStackChanged();
      return true;
    } 
    paramParcel2.writeString("android.app.ITaskStackListener");
    return true;
  }
  
  private static class Proxy implements ITaskStackListener {
    public static ITaskStackListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
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
    
    public void onActivityForcedResizable(String param2String, int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityForcedResizable(param2String, param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo param2RunningTaskInfo, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayFailed(param2RunningTaskInfo, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo param2RunningTaskInfo, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayRerouted(param2RunningTaskInfo, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityPinned(String param2String, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeString(param2String);
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(2, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityPinned(param2String, param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityRequestedOrientationChanged(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(13, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityRequestedOrientationChanged(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo param2RunningTaskInfo, boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        boolean bool1 = false;
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        boolean bool2 = bool1;
        if (param2Boolean3)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(4, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityRestartAttempt(param2RunningTaskInfo, param2Boolean1, param2Boolean2, param2Boolean3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityRotation(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(26, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityRotation(param2Int);
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
    
    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo param2RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(18, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onBackPressedOnTaskRoot(param2RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRecentTaskListFrozenChanged(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(23, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onRecentTaskListFrozenChanged(param2Boolean);
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
    
    public void onSingleTaskDisplayDrawn(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(19, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onSingleTaskDisplayDrawn(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSingleTaskDisplayEmpty(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(20, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onSingleTaskDisplayEmpty(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSizeCompatModeActivityChanged(int param2Int, IBinder param2IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        parcel.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(17, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onSizeCompatModeActivityChanged(param2Int, param2IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskCreated(int param2Int, ComponentName param2ComponentName) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (param2ComponentName != null) {
          parcel.writeInt(1);
          param2ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskCreated(param2Int, param2ComponentName);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo param2RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskDescriptionChanged(param2RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskDisplayChanged(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(21, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskDisplayChanged(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskFocusChanged(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(24, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskFocusChanged(param2Int, param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo param2RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskMovedToFront(param2RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskProfileLocked(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(15, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskProfileLocked(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo param2RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param2RunningTaskInfo != null) {
          parcel.writeInt(1);
          param2RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskRemovalStarted(param2RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskRemoved(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(10, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskRemoved(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskRequestedOrientationChanged(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(25, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskRequestedOrientationChanged(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskSnapshotChanged(int param2Int, ActivityManager.TaskSnapshot param2TaskSnapshot) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param2Int);
        if (param2TaskSnapshot != null) {
          parcel.writeInt(1);
          param2TaskSnapshot.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(16, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskSnapshotChanged(param2Int, param2TaskSnapshot);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITaskStackListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */