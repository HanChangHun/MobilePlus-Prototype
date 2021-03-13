package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITaskStackListener extends IInterface {
  public static final int FORCED_RESIZEABLE_REASON_SECONDARY_DISPLAY = 2;
  
  public static final int FORCED_RESIZEABLE_REASON_SPLIT_SCREEN = 1;
  
  void onActivityDismissingDockedStack() throws RemoteException;
  
  void onActivityForcedResizable(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo paramRunningTaskInfo, int paramInt) throws RemoteException;
  
  void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo paramRunningTaskInfo, int paramInt) throws RemoteException;
  
  void onActivityPinned(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onActivityRequestedOrientationChanged(int paramInt1, int paramInt2) throws RemoteException;
  
  void onActivityRestartAttempt(ActivityManager.RunningTaskInfo paramRunningTaskInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws RemoteException;
  
  void onActivityRotation(int paramInt) throws RemoteException;
  
  void onActivityUnpinned() throws RemoteException;
  
  void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException;
  
  void onRecentTaskListFrozenChanged(boolean paramBoolean) throws RemoteException;
  
  void onRecentTaskListUpdated() throws RemoteException;
  
  void onSingleTaskDisplayDrawn(int paramInt) throws RemoteException;
  
  void onSingleTaskDisplayEmpty(int paramInt) throws RemoteException;
  
  void onSizeCompatModeActivityChanged(int paramInt, IBinder paramIBinder) throws RemoteException;
  
  void onTaskCreated(int paramInt, ComponentName paramComponentName) throws RemoteException;
  
  void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException;
  
  void onTaskDisplayChanged(int paramInt1, int paramInt2) throws RemoteException;
  
  void onTaskFocusChanged(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onTaskMovedToFront(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException;
  
  void onTaskProfileLocked(int paramInt1, int paramInt2) throws RemoteException;
  
  void onTaskRemovalStarted(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException;
  
  void onTaskRemoved(int paramInt) throws RemoteException;
  
  void onTaskRequestedOrientationChanged(int paramInt1, int paramInt2) throws RemoteException;
  
  void onTaskSnapshotChanged(int paramInt, ActivityManager.TaskSnapshot paramTaskSnapshot) throws RemoteException;
  
  void onTaskStackChanged() throws RemoteException;
  
  public static class Default implements ITaskStackListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onActivityDismissingDockedStack() throws RemoteException {}
    
    public void onActivityForcedResizable(String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo param1RunningTaskInfo, int param1Int) throws RemoteException {}
    
    public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo param1RunningTaskInfo, int param1Int) throws RemoteException {}
    
    public void onActivityPinned(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onActivityRequestedOrientationChanged(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo param1RunningTaskInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) throws RemoteException {}
    
    public void onActivityRotation(int param1Int) throws RemoteException {}
    
    public void onActivityUnpinned() throws RemoteException {}
    
    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {}
    
    public void onRecentTaskListFrozenChanged(boolean param1Boolean) throws RemoteException {}
    
    public void onRecentTaskListUpdated() throws RemoteException {}
    
    public void onSingleTaskDisplayDrawn(int param1Int) throws RemoteException {}
    
    public void onSingleTaskDisplayEmpty(int param1Int) throws RemoteException {}
    
    public void onSizeCompatModeActivityChanged(int param1Int, IBinder param1IBinder) throws RemoteException {}
    
    public void onTaskCreated(int param1Int, ComponentName param1ComponentName) throws RemoteException {}
    
    public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {}
    
    public void onTaskDisplayChanged(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onTaskFocusChanged(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {}
    
    public void onTaskProfileLocked(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {}
    
    public void onTaskRemoved(int param1Int) throws RemoteException {}
    
    public void onTaskRequestedOrientationChanged(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onTaskSnapshotChanged(int param1Int, ActivityManager.TaskSnapshot param1TaskSnapshot) throws RemoteException {}
    
    public void onTaskStackChanged() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITaskStackListener {
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
    
    public static ITaskStackListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.ITaskStackListener");
      return (iInterface != null && iInterface instanceof ITaskStackListener) ? (ITaskStackListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITaskStackListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(ITaskStackListener param1ITaskStackListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITaskStackListener != null) {
          Proxy.sDefaultImpl = param1ITaskStackListener;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool1 = false;
        boolean bool2 = false;
        boolean bool3 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 26:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onActivityRotation(param1Parcel1.readInt());
            return true;
          case 25:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onTaskRequestedOrientationChanged(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 24:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0)
              bool3 = true; 
            onTaskFocusChanged(param1Int1, bool3);
            return true;
          case 23:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            bool3 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool3 = true; 
            onRecentTaskListFrozenChanged(bool3);
            return true;
          case 22:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onRecentTaskListUpdated();
            return true;
          case 21:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onTaskDisplayChanged(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 20:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onSingleTaskDisplayEmpty(param1Parcel1.readInt());
            return true;
          case 19:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onSingleTaskDisplayDrawn(param1Parcel1.readInt());
            return true;
          case 18:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onBackPressedOnTaskRoot((ActivityManager.RunningTaskInfo)param1Parcel1);
            return true;
          case 17:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onSizeCompatModeActivityChanged(param1Parcel1.readInt(), param1Parcel1.readStrongBinder());
            return true;
          case 16:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.TaskSnapshot taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onTaskSnapshotChanged(param1Int1, (ActivityManager.TaskSnapshot)param1Parcel1);
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onTaskProfileLocked(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onTaskRemovalStarted((ActivityManager.RunningTaskInfo)param1Parcel1);
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onActivityRequestedOrientationChanged(param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onTaskDescriptionChanged((ActivityManager.RunningTaskInfo)param1Parcel1);
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onTaskMovedToFront((ActivityManager.RunningTaskInfo)param1Parcel1);
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onTaskRemoved(param1Parcel1.readInt());
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onTaskCreated(param1Int1, (ComponentName)param1Parcel1);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onActivityLaunchOnSecondaryDisplayRerouted((ActivityManager.RunningTaskInfo)param1Parcel2, param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            onActivityLaunchOnSecondaryDisplayFailed((ActivityManager.RunningTaskInfo)param1Parcel2, param1Parcel1.readInt());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onActivityDismissingDockedStack();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onActivityForcedResizable(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            if (param1Parcel1.readInt() != 0) {
              ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool3 = true;
            } else {
              bool3 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onActivityRestartAttempt((ActivityManager.RunningTaskInfo)param1Parcel2, bool3, bool1, bool2);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onActivityUnpinned();
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.ITaskStackListener");
            onActivityPinned(param1Parcel1.readString(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.ITaskStackListener");
        onTaskStackChanged();
        return true;
      } 
      param1Parcel2.writeString("android.app.ITaskStackListener");
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
  
  private static class Proxy implements ITaskStackListener {
    public static ITaskStackListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void onActivityForcedResizable(String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityForcedResizable(param1String, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo param1RunningTaskInfo, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayFailed(param1RunningTaskInfo, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo param1RunningTaskInfo, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayRerouted(param1RunningTaskInfo, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityPinned(String param1String, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(2, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityPinned(param1String, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityRequestedOrientationChanged(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(13, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityRequestedOrientationChanged(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo param1RunningTaskInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        boolean bool1 = false;
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        if (param1Boolean2) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        boolean bool2 = bool1;
        if (param1Boolean3)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(4, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityRestartAttempt(param1RunningTaskInfo, param1Boolean1, param1Boolean2, param1Boolean3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onActivityRotation(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(26, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onActivityRotation(param1Int);
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
    
    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(18, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onBackPressedOnTaskRoot(param1RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRecentTaskListFrozenChanged(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(23, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onRecentTaskListFrozenChanged(param1Boolean);
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
    
    public void onSingleTaskDisplayDrawn(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(19, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onSingleTaskDisplayDrawn(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSingleTaskDisplayEmpty(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(20, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onSingleTaskDisplayEmpty(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSizeCompatModeActivityChanged(int param1Int, IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(17, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onSizeCompatModeActivityChanged(param1Int, param1IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskCreated(int param1Int, ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (param1ComponentName != null) {
          parcel.writeInt(1);
          param1ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskCreated(param1Int, param1ComponentName);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskDescriptionChanged(param1RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskDisplayChanged(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(21, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskDisplayChanged(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskFocusChanged(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(24, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskFocusChanged(param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskMovedToFront(param1RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskProfileLocked(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(15, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskProfileLocked(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo param1RunningTaskInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        if (param1RunningTaskInfo != null) {
          parcel.writeInt(1);
          param1RunningTaskInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskRemovalStarted(param1RunningTaskInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskRemoved(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskRemoved(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskRequestedOrientationChanged(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(25, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskRequestedOrientationChanged(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTaskSnapshotChanged(int param1Int, ActivityManager.TaskSnapshot param1TaskSnapshot) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITaskStackListener");
        parcel.writeInt(param1Int);
        if (param1TaskSnapshot != null) {
          parcel.writeInt(1);
          param1TaskSnapshot.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(16, parcel, null, 1) && ITaskStackListener.Stub.getDefaultImpl() != null) {
          ITaskStackListener.Stub.getDefaultImpl().onTaskSnapshotChanged(param1Int, param1TaskSnapshot);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/ITaskStackListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */