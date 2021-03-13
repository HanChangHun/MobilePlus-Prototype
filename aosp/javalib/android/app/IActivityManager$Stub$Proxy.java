package android.app;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.LocusId;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.ParceledListSlice;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.IProgressListener;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.WorkSource;
import android.text.TextUtils;
import android.view.IRecentsAnimationRunner;
import com.android.internal.os.IResultReceiver;
import java.util.List;

class Proxy implements IActivityManager {
  public static IActivityManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addInstrumentationResults(IApplicationThread paramIApplicationThread, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().addInstrumentationResults(paramIApplicationThread, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addPackageDependency(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().addPackageDependency(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void appNotResponding(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(210, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().appNotResponding(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void appNotRespondingViaProvider(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().appNotRespondingViaProvider(paramIBinder);
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
  
  public void attachApplication(IApplicationThread paramIApplicationThread, long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().attachApplication(paramIApplicationThread, paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void backgroundWhitelistUid(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(204, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().backgroundWhitelistUid(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void backupAgentCreated(String paramString, IBinder paramIBinder, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().backupAgentCreated(paramString, paramIBinder, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean bindBackupAgent(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(81, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().bindBackupAgent(paramString, paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int bindIsolatedService(IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, String paramString1, IServiceConnection paramIServiceConnection, int paramInt1, String paramString2, String paramString3, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        iBinder2 = paramIApplicationThread.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      try {
        parcel1.writeStrongBinder(paramIBinder);
        if (paramIntent != null) {
          parcel1.writeInt(1);
          paramIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(paramString1);
          iBinder2 = iBinder1;
          if (paramIServiceConnection != null)
            iBinder2 = paramIServiceConnection.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeInt(paramInt1);
          parcel1.writeString(paramString2);
          parcel1.writeString(paramString3);
          parcel1.writeInt(paramInt2);
          if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            paramInt1 = IActivityManager.Stub.getDefaultImpl().bindIsolatedService(paramIApplicationThread, paramIBinder, paramIntent, paramString1, paramIServiceConnection, paramInt1, paramString2, paramString3, paramInt2);
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
    throw paramIApplicationThread;
  }
  
  public int bindService(IApplicationThread paramIApplicationThread, IBinder paramIBinder, Intent paramIntent, String paramString1, IServiceConnection paramIServiceConnection, int paramInt1, String paramString2, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        iBinder2 = paramIApplicationThread.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      try {
        parcel1.writeStrongBinder(paramIBinder);
        if (paramIntent != null) {
          parcel1.writeInt(1);
          paramIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(paramString1);
          iBinder2 = iBinder1;
          if (paramIServiceConnection != null)
            iBinder2 = paramIServiceConnection.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          try {
            parcel1.writeInt(paramInt1);
            parcel1.writeString(paramString2);
            parcel1.writeInt(paramInt2);
            if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              paramInt1 = IActivityManager.Stub.getDefaultImpl().bindService(paramIApplicationThread, paramIBinder, paramIntent, paramString1, paramIServiceConnection, paramInt1, paramString2, paramInt2);
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
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public void bootAnimationComplete() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(164, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().bootAnimationComplete();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int broadcastIntent(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString1, IIntentReceiver paramIIntentReceiver, int paramInt1, String paramString2, Bundle paramBundle1, String[] paramArrayOfString, int paramInt2, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        try {
          iBinder2 = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      boolean bool1 = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      IBinder iBinder2 = iBinder1;
      if (paramIIntentReceiver != null)
        iBinder2 = paramIIntentReceiver.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString2);
      if (paramBundle1 != null) {
        parcel1.writeInt(1);
        paramBundle1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt2);
      if (paramBundle2 != null) {
        parcel1.writeInt(1);
        paramBundle2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
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
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityManager.broadcastIntent(paramIApplicationThread, paramIntent, paramString1, paramIIntentReceiver, paramInt1, paramString2, paramBundle1, paramArrayOfString, paramInt2, paramBundle2, paramBoolean1, paramBoolean2, paramInt3);
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
  
  public int broadcastIntentWithFeature(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IIntentReceiver paramIIntentReceiver, int paramInt1, String paramString3, Bundle paramBundle1, String[] paramArrayOfString, int paramInt2, Bundle paramBundle2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        try {
          iBinder2 = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeString(paramString1);
      boolean bool1 = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString2);
      IBinder iBinder2 = iBinder1;
      if (paramIIntentReceiver != null)
        iBinder2 = paramIIntentReceiver.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString3);
      if (paramBundle1 != null) {
        parcel1.writeInt(1);
        paramBundle1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt2);
      if (paramBundle2 != null) {
        parcel1.writeInt(1);
        paramBundle2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
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
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityManager.broadcastIntentWithFeature(paramIApplicationThread, paramString1, paramIntent, paramString2, paramIIntentReceiver, paramInt1, paramString3, paramBundle1, paramArrayOfString, paramInt2, paramBundle2, paramBoolean1, paramBoolean2, paramInt3);
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
  
  public void cancelIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().cancelIntentSender(paramIIntentSender);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelRecentsAnimation(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(160, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().cancelRecentsAnimation(paramBoolean);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(199, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().cancelTaskWindowTransition(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int checkPermission(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IActivityManager.Stub.getDefaultImpl().checkPermission(paramString, paramInt1, paramInt2);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int checkPermissionWithToken(String paramString, int paramInt1, int paramInt2, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(165, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt1 = IActivityManager.Stub.getDefaultImpl().checkPermissionWithToken(paramString, paramInt1, paramInt2, paramIBinder);
        return paramInt1;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2, int paramInt3, int paramInt4, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeInt(paramInt2);
          try {
            parcel1.writeInt(paramInt3);
            try {
              parcel1.writeInt(paramInt4);
              try {
                parcel1.writeStrongBinder(paramIBinder);
                if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  paramInt1 = IActivityManager.Stub.getDefaultImpl().checkUriPermission(paramUri, paramInt1, paramInt2, paramInt3, paramInt4, paramIBinder);
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
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramUri;
  }
  
  public boolean clearApplicationUserData(String paramString, boolean paramBoolean, IPackageDataObserver paramIPackageDataObserver, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (paramIPackageDataObserver != null) {
        iBinder = paramIPackageDataObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityManager.Stub.getDefaultImpl().clearApplicationUserData(paramString, paramBoolean, paramIPackageDataObserver, paramInt);
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
  
  public void closeSystemDialogs(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().closeSystemDialogs(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void crashApplication(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeInt(paramInt2);
          try {
            parcel1.writeString(paramString1);
            try {
              parcel1.writeInt(paramInt3);
              try {
                boolean bool;
                parcel1.writeString(paramString2);
                if (paramBoolean) {
                  bool = true;
                } else {
                  bool = false;
                } 
                parcel1.writeInt(bool);
                try {
                  if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                    IActivityManager.Stub.getDefaultImpl().crashApplication(paramInt1, paramInt2, paramString1, paramInt3, paramString2, paramBoolean);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public boolean dumpHeap(String paramString1, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel1.writeString(paramString1);
        try {
          boolean bool2;
          parcel1.writeInt(paramInt);
          boolean bool1 = true;
          if (paramBoolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (paramBoolean2) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (paramBoolean3) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          parcel1.writeString(paramString2);
          if (paramParcelFileDescriptor != null) {
            parcel1.writeInt(1);
            paramParcelFileDescriptor.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (paramRemoteCallback != null) {
            parcel1.writeInt(1);
            paramRemoteCallback.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            paramBoolean1 = IActivityManager.Stub.getDefaultImpl().dumpHeap(paramString1, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramString2, paramParcelFileDescriptor, paramRemoteCallback);
            parcel2.recycle();
            parcel1.recycle();
            return paramBoolean1;
          } 
          parcel2.readException();
          paramInt = parcel2.readInt();
          if (paramInt != 0) {
            paramBoolean1 = bool1;
          } else {
            paramBoolean1 = false;
          } 
          parcel2.recycle();
          parcel1.recycle();
          return paramBoolean1;
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public void dumpHeapFinished(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(173, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().dumpHeapFinished(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean enableAppFreezer(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(217, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityManager.Stub.getDefaultImpl().enableAppFreezer(paramBoolean);
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
  
  public void enterSafeMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().enterSafeMode();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean finishActivity(IBinder paramIBinder, int paramInt1, Intent paramIntent, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().finishActivity(paramIBinder, paramInt1, paramIntent, paramInt2);
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
  
  public void finishHeavyWeightApp() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().finishHeavyWeightApp();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishInstrumentation(IApplicationThread paramIApplicationThread, int paramInt, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().finishInstrumentation(paramIApplicationThread, paramInt, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void finishReceiver(IBinder paramIBinder, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel.writeStrongBinder(paramIBinder);
        try {
          parcel.writeInt(paramInt1);
          try {
            parcel.writeString(paramString);
            boolean bool = false;
            if (paramBundle != null) {
              parcel.writeInt(1);
              paramBundle.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (paramBoolean)
              bool = true; 
            parcel.writeInt(bool);
            try {
              parcel.writeInt(paramInt2);
              try {
                if (!this.mRemote.transact(17, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
                  IActivityManager.Stub.getDefaultImpl().finishReceiver(paramIBinder, paramInt1, paramString, paramBundle, paramBoolean, paramInt2);
                  parcel.recycle();
                  return;
                } 
                parcel.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramIBinder;
  }
  
  public void forceStopPackage(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().forceStopPackage(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getAllStackInfos(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getBugreportWhitelistedPackages() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getBugreportWhitelistedPackages(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Configuration getConfiguration() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Configuration configuration;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        configuration = IActivityManager.Stub.getDefaultImpl().getConfiguration();
        return configuration;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        configuration = (Configuration)Configuration.CREATOR.createFromParcel(parcel2);
      } else {
        configuration = null;
      } 
      return configuration;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ContentProviderHolder getContentProvider(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getContentProvider(paramIApplicationThread, paramString1, paramString2, paramInt, paramBoolean); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ContentProviderHolder contentProviderHolder = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel2);
      } else {
        paramIApplicationThread = null;
      } 
      return (ContentProviderHolder)paramIApplicationThread;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ContentProviderHolder getContentProviderExternal(String paramString1, int paramInt, IBinder paramIBinder, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getContentProviderExternal(paramString1, paramInt, paramIBinder, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ContentProviderHolder contentProviderHolder = (ContentProviderHolder)ContentProviderHolder.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ContentProviderHolder)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public UserInfo getCurrentUser() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      UserInfo userInfo;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        userInfo = IActivityManager.Stub.getDefaultImpl().getCurrentUser();
        return userInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        userInfo = (UserInfo)UserInfo.CREATOR.createFromParcel(parcel2);
      } else {
        userInfo = null;
      } 
      return userInfo;
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(150, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        stackInfo = IActivityManager.Stub.getDefaultImpl().getFocusedStackInfo();
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
  
  public int getForegroundServiceType(ComponentName paramComponentName, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getForegroundServiceType(paramComponentName, paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice<ApplicationExitInfo> getHistoricalProcessExitReasons(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(211, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getHistoricalProcessExitReasons(paramString, paramInt1, paramInt2, paramInt3); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParceledListSlice<ApplicationExitInfo>)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Intent getIntentForIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getIntentForIntentSender(paramIIntentSender); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
      } else {
        paramIIntentSender = null;
      } 
      return (Intent)paramIIntentSender;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IIntentSender getIntentSender(int paramInt1, String paramString1, IBinder paramIBinder, String paramString2, int paramInt2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, int paramInt3, Bundle paramBundle, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel1.writeInt(paramInt1);
        parcel1.writeString(paramString1);
        parcel1.writeStrongBinder(paramIBinder);
        parcel1.writeString(paramString2);
        parcel1.writeInt(paramInt2);
        parcel1.writeTypedArray((Parcelable[])paramArrayOfIntent, 0);
        parcel1.writeStringArray(paramArrayOfString);
        parcel1.writeInt(paramInt3);
        if (paramBundle != null) {
          parcel1.writeInt(1);
          paramBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(paramInt4);
        if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
          IIntentSender iIntentSender1 = IActivityManager.Stub.getDefaultImpl().getIntentSender(paramInt1, paramString1, paramIBinder, paramString2, paramInt2, paramArrayOfIntent, paramArrayOfString, paramInt3, paramBundle, paramInt4);
          parcel2.recycle();
          parcel1.recycle();
          return iIntentSender1;
        } 
        parcel2.readException();
        IIntentSender iIntentSender = IIntentSender.Stub.asInterface(parcel2.readStrongBinder());
        parcel2.recycle();
        parcel1.recycle();
        return iIntentSender;
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public IIntentSender getIntentSenderWithFeature(int paramInt1, String paramString1, String paramString2, IBinder paramIBinder, String paramString3, int paramInt2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, int paramInt3, Bundle paramBundle, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramInt2);
      parcel1.writeTypedArray((Parcelable[])paramArrayOfIntent, 0);
      parcel1.writeStringArray(paramArrayOfString);
      parcel1.writeInt(paramInt3);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt4);
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getIntentSenderWithFeature(paramInt1, paramString1, paramString2, paramIBinder, paramString3, paramInt2, paramArrayOfIntent, paramArrayOfString, paramInt3, paramBundle, paramInt4); 
      parcel2.readException();
      return IIntentSender.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IActivityManager";
  }
  
  public String getLaunchedFromPackage(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getLaunchedFromPackage(paramIBinder); 
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getLaunchedFromUid(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelFileDescriptor getLifeMonitor() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParcelFileDescriptor parcelFileDescriptor;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(208, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        parcelFileDescriptor = IActivityManager.Stub.getDefaultImpl().getLifeMonitor();
        return parcelFileDescriptor;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        parcelFileDescriptor = null;
      } 
      return parcelFileDescriptor;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getLockTaskModeState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(171, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getLockTaskModeState(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getMemoryInfo(ActivityManager.MemoryInfo paramMemoryInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().getMemoryInfo(paramMemoryInfo);
        return;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0)
        paramMemoryInfo.readFromParcel(parcel2); 
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getMemoryTrimLevel() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(189, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getMemoryTrimLevel(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getMyMemoryState(ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().getMyMemoryState(paramRunningAppProcessInfo);
        return;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0)
        paramRunningAppProcessInfo.readFromParcel(parcel2); 
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getPackageForIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getPackageForIntentSender(paramIIntentSender); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPackageProcessState(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(177, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getPackageProcessState(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getProcessLimit() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getProcessLimit(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Debug.MemoryInfo[] getProcessMemoryInfo(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getProcessMemoryInfo(paramArrayOfint); 
      parcel2.readException();
      return (Debug.MemoryInfo[])parcel2.createTypedArray(Debug.MemoryInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long[] getProcessPss(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getProcessPss(paramArrayOfint); 
      parcel2.readException();
      return parcel2.createLongArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getProcessesInErrorState(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.ProcessErrorStateInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getProviderMimeType(Uri paramUri, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getProviderMimeType(paramUri, paramInt); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getProviderMimeTypeAsync(Uri paramUri, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IActivityManager");
      if (paramUri != null) {
        parcel.writeInt(1);
        paramUri.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (paramRemoteCallback != null) {
        parcel.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(100, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().getProviderMimeTypeAsync(paramUri, paramInt, paramRemoteCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public ParceledListSlice getRecentTasks(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IActivityManager.Stub.getDefaultImpl().getRecentTasks(paramInt1, paramInt2, paramInt3);
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
  
  public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getRunningAppProcesses(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.RunningAppProcessInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ApplicationInfo> getRunningExternalApplications() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getRunningExternalApplications(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ApplicationInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public PendingIntent getRunningServiceControlPanel(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getRunningServiceControlPanel(paramComponentName); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel2);
      } else {
        paramComponentName = null;
      } 
      return (PendingIntent)paramComponentName;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] getRunningUserIds() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getRunningUserIds(); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<ActivityManager.RunningServiceInfo> getServices(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getServices(paramInt1, paramInt2); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.RunningServiceInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getTagForIntentSender(IIntentSender paramIIntentSender, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(156, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getTagForIntentSender(paramIIntentSender, paramString); 
      parcel2.readException();
      return parcel2.readString();
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        rect = IActivityManager.Stub.getDefaultImpl().getTaskBounds(paramInt);
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
  
  public int getTaskForActivity(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        i = IActivityManager.Stub.getDefaultImpl().getTaskForActivity(paramIBinder, paramBoolean);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(200, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        taskSnapshot = IActivityManager.Stub.getDefaultImpl().getTaskSnapshot(paramInt, paramBoolean);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getTasks(paramInt); 
      parcel2.readException();
      return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getUidForIntentSender(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().getUidForIntentSender(paramIIntentSender); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getUidProcessState(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityManager.Stub.getDefaultImpl().getUidProcessState(paramInt, paramString);
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
  
  public void grantUriPermission(IApplicationThread paramIApplicationThread, String paramString, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().grantUriPermission(paramIApplicationThread, paramString, paramUri, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void handleApplicationCrash(IBinder paramIBinder, ApplicationErrorReport.ParcelableCrashInfo paramParcelableCrashInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramParcelableCrashInfo != null) {
        parcel1.writeInt(1);
        paramParcelableCrashInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().handleApplicationCrash(paramIBinder, paramParcelableCrashInfo);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void handleApplicationStrictModeViolation(IBinder paramIBinder, int paramInt, StrictMode.ViolationInfo paramViolationInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (paramViolationInfo != null) {
        parcel1.writeInt(1);
        paramViolationInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().handleApplicationStrictModeViolation(paramIBinder, paramInt, paramViolationInfo);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean handleApplicationWtf(IBinder paramIBinder, String paramString, boolean paramBoolean, ApplicationErrorReport.ParcelableCrashInfo paramParcelableCrashInfo, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          boolean bool2;
          parcel1.writeString(paramString);
          boolean bool1 = true;
          if (paramBoolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (paramParcelableCrashInfo != null) {
            parcel1.writeInt(1);
            paramParcelableCrashInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt);
            try {
              if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                paramBoolean = IActivityManager.Stub.getDefaultImpl().handleApplicationWtf(paramIBinder, paramString, paramBoolean, paramParcelableCrashInfo, paramInt);
                parcel2.recycle();
                parcel1.recycle();
                return paramBoolean;
              } 
              parcel2.readException();
              paramInt = parcel2.readInt();
              if (paramInt != 0) {
                paramBoolean = bool1;
              } else {
                paramBoolean = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return paramBoolean;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public int handleIncomingUser(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeInt(paramInt2);
          try {
            boolean bool2;
            parcel1.writeInt(paramInt3);
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
            try {
              parcel1.writeString(paramString1);
              try {
                parcel1.writeString(paramString2);
                if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                  paramInt1 = IActivityManager.Stub.getDefaultImpl().handleIncomingUser(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, paramString1, paramString2);
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
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString1;
  }
  
  public void hang(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().hang(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAppFreezerSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(215, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isAppFreezerSupported();
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
  
  public boolean isAppStartModeDisabled(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(184, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isAppStartModeDisabled(paramInt, paramString);
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
  
  public boolean isBackgroundRestricted(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(195, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isBackgroundRestricted(paramString);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(158, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isInLockTaskMode();
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
  
  public boolean isIntentSenderABroadcast(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(123, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderABroadcast(paramIIntentSender);
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
  
  public boolean isIntentSenderAForegroundService(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(122, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderAForegroundService(paramIIntentSender);
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
  
  public boolean isIntentSenderAnActivity(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(121, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderAnActivity(paramIIntentSender);
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
  
  public boolean isIntentSenderTargetedToPackage(IIntentSender paramIIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(108, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isIntentSenderTargetedToPackage(paramIIntentSender);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(97, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isTopActivityImmersive();
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(163, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isTopOfTask(paramIBinder);
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
  
  public boolean isUidActive(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isUidActive(paramInt, paramString);
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
  
  public boolean isUserAMonkey() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(93, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isUserAMonkey();
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
  
  public boolean isUserRunning(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(102, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isUserRunning(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isVrModePackageEnabled(ComponentName paramComponentName) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(190, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().isVrModePackageEnabled(paramComponentName);
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
  
  public void killAllBackgroundProcesses() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killAllBackgroundProcesses();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void killApplication(String paramString1, int paramInt1, int paramInt2, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killApplication(paramString1, paramInt1, paramInt2, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void killApplicationProcess(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killApplicationProcess(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void killBackgroundProcesses(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killBackgroundProcesses(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void killPackageDependents(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(186, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killPackageDependents(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean killPids(int[] paramArrayOfint, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeIntArray(paramArrayOfint);
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityManager.Stub.getDefaultImpl().killPids(paramArrayOfint, paramString, paramBoolean);
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
  
  public boolean killProcessesBelowForeground(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(117, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().killProcessesBelowForeground(paramString);
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
  
  public void killProcessesWhenImperceptible(int[] paramArrayOfint, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeIntArray(paramArrayOfint);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(212, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killProcessesWhenImperceptible(paramArrayOfint, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void killUid(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killUid(paramInt1, paramInt2, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void killUidForPermissionChange(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(216, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().killUidForPermissionChange(paramInt1, paramInt2, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean launchBugReportHandlerApp() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(140, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().launchBugReportHandlerApp();
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
  
  public void makePackageIdle(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(188, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().makePackageIdle(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean moveActivityTaskToBack(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IActivityManager.Stub.getDefaultImpl().moveActivityTaskToBack(paramIBinder, paramBoolean);
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
  
  public void moveTaskToFront(IApplicationThread paramIApplicationThread, String paramString, int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().moveTaskToFront(paramIApplicationThread, paramString, paramInt1, paramInt2, paramBundle);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().moveTaskToStack(paramInt1, paramInt2, paramBoolean);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      boolean bool = true;
      if (paramRect != null) {
        parcel1.writeInt(1);
        paramRect.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(183, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().moveTopActivityToPinnedStack(paramInt, paramRect);
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
  
  public void noteAlarmFinish(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramWorkSource != null) {
        parcel1.writeInt(1);
        paramWorkSource.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(176, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().noteAlarmFinish(paramIIntentSender, paramWorkSource, paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void noteAlarmStart(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramWorkSource != null) {
        parcel1.writeInt(1);
        paramWorkSource.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(175, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().noteAlarmStart(paramIIntentSender, paramWorkSource, paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void noteWakeupAlarm(IIntentSender paramIIntentSender, WorkSource paramWorkSource, int paramInt, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentSender != null) {
        iBinder = paramIIntentSender.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramWorkSource != null) {
        parcel1.writeInt(1);
        paramWorkSource.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().noteWakeupAlarm(paramIIntentSender, paramWorkSource, paramInt, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyCleartextNetwork(int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(168, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().notifyCleartextNetwork(paramInt, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void notifyLockedProfile(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(191, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().notifyLockedProfile(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelFileDescriptor openContentUri(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().openContentUri(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParcelFileDescriptor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder peekService(Intent paramIntent, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null)
        return IActivityManager.Stub.getDefaultImpl().peekService(paramIntent, paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readStrongBinder();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void performIdleMaintenance() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().performIdleMaintenance();
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(181, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().positionTaskInStack(paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean profileControl(String paramString, int paramInt1, boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      try {
        parcel1.writeString(paramString);
        try {
          boolean bool2;
          parcel1.writeInt(paramInt1);
          boolean bool1 = true;
          if (paramBoolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (paramProfilerInfo != null) {
            parcel1.writeInt(1);
            paramProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt2);
            try {
              if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                paramBoolean = IActivityManager.Stub.getDefaultImpl().profileControl(paramString, paramInt1, paramBoolean, paramProfilerInfo, paramInt2);
                parcel2.recycle();
                parcel1.recycle();
                return paramBoolean;
              } 
              parcel2.readException();
              paramInt1 = parcel2.readInt();
              if (paramInt1 != 0) {
                paramBoolean = bool1;
              } else {
                paramBoolean = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return paramBoolean;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public void publishContentProviders(IApplicationThread paramIApplicationThread, List<ContentProviderHolder> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeTypedList(paramList);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().publishContentProviders(paramIApplicationThread, paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void publishService(IBinder paramIBinder1, Intent paramIntent, IBinder paramIBinder2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder1);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder2);
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().publishService(paramIBinder1, paramIntent, paramIBinder2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean refContentProvider(IBinder paramIBinder, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(24, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().refContentProvider(paramIBinder, paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerIntentSenderCancelListener(IIntentSender paramIIntentSender, IResultReceiver paramIResultReceiver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIIntentSender != null) {
        iBinder2 = paramIIntentSender.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIResultReceiver != null)
        iBinder2 = paramIResultReceiver.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().registerIntentSenderCancelListener(paramIIntentSender, paramIResultReceiver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerProcessObserver(IProcessObserver paramIProcessObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIProcessObserver != null) {
        iBinder = paramIProcessObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().registerProcessObserver(paramIProcessObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Intent registerReceiver(IApplicationThread paramIApplicationThread, String paramString1, IIntentReceiver paramIIntentReceiver, IntentFilter paramIntentFilter, String paramString2, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        iBinder2 = paramIApplicationThread.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      try {
        parcel1.writeString(paramString1);
        iBinder2 = iBinder1;
        if (paramIIntentReceiver != null)
          iBinder2 = paramIIntentReceiver.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (paramIntentFilter != null) {
          parcel1.writeInt(1);
          paramIntentFilter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeInt(paramInt1);
            try {
              parcel1.writeInt(paramInt2);
              if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                Intent intent = IActivityManager.Stub.getDefaultImpl().registerReceiver(paramIApplicationThread, paramString1, paramIIntentReceiver, paramIntentFilter, paramString2, paramInt1, paramInt2);
                parcel2.recycle();
                parcel1.recycle();
                return intent;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
              } else {
                paramIApplicationThread = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (Intent)paramIApplicationThread;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public Intent registerReceiverWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, IIntentReceiver paramIIntentReceiver, IntentFilter paramIntentFilter, String paramString3, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIApplicationThread != null) {
        iBinder2 = paramIApplicationThread.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeString(paramString2);
          iBinder2 = iBinder1;
          if (paramIIntentReceiver != null)
            iBinder2 = paramIIntentReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          if (paramIntentFilter != null) {
            parcel1.writeInt(1);
            paramIntentFilter.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeString(paramString3);
            parcel1.writeInt(paramInt1);
            parcel1.writeInt(paramInt2);
            if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
              Intent intent = IActivityManager.Stub.getDefaultImpl().registerReceiverWithFeature(paramIApplicationThread, paramString1, paramString2, paramIIntentReceiver, paramIntentFilter, paramString3, paramInt1, paramInt2);
              parcel2.recycle();
              parcel1.recycle();
              return intent;
            } 
            parcel2.readException();
            if (parcel2.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel2);
            } else {
              paramIApplicationThread = null;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return (Intent)paramIApplicationThread;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public void registerTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramITaskStackListener != null) {
        iBinder = paramITaskStackListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(166, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().registerTaskStackListener(paramITaskStackListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerUidObserver(IUidObserver paramIUidObserver, int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIUidObserver != null) {
        iBinder = paramIUidObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().registerUidObserver(paramIUidObserver, paramInt1, paramInt2, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerUserSwitchObserver(IUserSwitchObserver paramIUserSwitchObserver, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIUserSwitchObserver != null) {
        iBinder = paramIUserSwitchObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().registerUserSwitchObserver(paramIUserSwitchObserver, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeContentProvider(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IActivityManager");
      parcel.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(62, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().removeContentProvider(paramIBinder, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void removeContentProviderExternal(String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().removeContentProviderExternal(paramString, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeContentProviderExternalAsUser(String paramString, IBinder paramIBinder, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().removeContentProviderExternalAsUser(paramString, paramIBinder, paramInt);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(187, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().removeStack(paramInt);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(105, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().removeTask(paramInt);
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
  
  public void requestBugReport(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestBugReport(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestBugReportWithDescription(String paramString1, String paramString2, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestBugReportWithDescription(paramString1, paramString2, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestFullBugReport() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestFullBugReport();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestInteractiveBugReport() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestInteractiveBugReport();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestInteractiveBugReportWithDescription(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestInteractiveBugReportWithDescription(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestRemoteBugReport() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestRemoteBugReport();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestSystemServerHeapDump() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestSystemServerHeapDump();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestTelephonyBugReport(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestTelephonyBugReport(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestWifiBugReport(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().requestWifiBugReport(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resizeTask(int paramInt1, Rect paramRect, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      if (paramRect != null) {
        parcel1.writeInt(1);
        paramRect.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(170, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().resizeTask(paramInt1, paramRect, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void restart() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().restart();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int restartUserInBackground(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(198, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityManager.Stub.getDefaultImpl().restartUserInBackground(paramInt);
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
  
  public void resumeAppSwitches() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().resumeAppSwitches();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void revokeUriPermission(IApplicationThread paramIApplicationThread, String paramString, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (paramUri != null) {
        parcel1.writeInt(1);
        paramUri.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().revokeUriPermission(paramIApplicationThread, paramString, paramUri, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void scheduleApplicationInfoChanged(List<String> paramList, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStringList(paramList);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(201, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().scheduleApplicationInfoChanged(paramList, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendIdleJobTrigger() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(193, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().sendIdleJobTrigger();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int sendIntentSender(IIntentSender paramIIntentSender, IBinder paramIBinder, int paramInt, Intent paramIntent, String paramString1, IIntentReceiver paramIIntentReceiver, String paramString2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIIntentSender != null) {
        iBinder2 = paramIIntentSender.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeInt(paramInt);
          if (paramIntent != null) {
            parcel1.writeInt(1);
            paramIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(paramString1);
          iBinder2 = iBinder1;
          if (paramIIntentReceiver != null)
            iBinder2 = paramIIntentReceiver.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeString(paramString2);
          if (paramBundle != null) {
            parcel1.writeInt(1);
            paramBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(194, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            paramInt = IActivityManager.Stub.getDefaultImpl().sendIntentSender(paramIIntentSender, paramIBinder, paramInt, paramIntent, paramString1, paramIIntentReceiver, paramString2, paramBundle);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramIIntentSender;
  }
  
  public void serviceDoneExecuting(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IActivityManager");
      parcel.writeStrongBinder(paramIBinder);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(53, parcel, null, 1) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().serviceDoneExecuting(paramIBinder, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setActivityController(IActivityController paramIActivityController, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setActivityController(paramIActivityController, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setActivityLocusContext(ComponentName paramComponentName, LocusId paramLocusId, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramLocusId != null) {
        parcel1.writeInt(1);
        paramLocusId.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(213, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setActivityLocusContext(paramComponentName, paramLocusId, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAgentApp(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setAgentApp(paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAlwaysFinish(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setAlwaysFinish(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDebugApp(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
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
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setDebugApp(paramString, paramBoolean1, paramBoolean2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setDumpHeapDebugLimit(String paramString1, int paramInt, long paramLong, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeLong(paramLong);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(172, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setDumpHeapDebugLimit(paramString1, paramInt, paramLong, paramString2);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setFocusedStack(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setHasTopUi(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(197, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setHasTopUi(paramBoolean);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setPackageScreenCompatMode(paramString, paramInt);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(202, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setPersistentVrThread(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setProcessImportant(IBinder paramIBinder, int paramInt, boolean paramBoolean, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setProcessImportant(paramIBinder, paramInt, paramBoolean, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setProcessLimit(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setProcessLimit(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setProcessMemoryTrimLevel(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(155, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().setProcessMemoryTrimLevel(paramString, paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setProcessStateSummary(byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(214, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setProcessStateSummary(paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setRenderThread(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(196, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setRenderThread(paramInt);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setRequestedOrientation(paramIBinder, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setServiceForeground(ComponentName paramComponentName, IBinder paramIBinder, int paramInt1, Notification paramNotification, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeInt(paramInt1);
          if (paramNotification != null) {
            parcel1.writeInt(1);
            paramNotification.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt2);
            try {
              parcel1.writeInt(paramInt3);
              if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                IActivityManager.Stub.getDefaultImpl().setServiceForeground(paramComponentName, paramIBinder, paramInt1, paramNotification, paramInt2, paramInt3);
                parcel2.recycle();
                parcel1.recycle();
                return;
              } 
              parcel2.readException();
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public void setTaskResizeable(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(169, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setTaskResizeable(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setUserIsMonkey(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().setUserIsMonkey(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void showBootMessage(CharSequence paramCharSequence, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramCharSequence != null) {
        parcel1.writeInt(1);
        TextUtils.writeToParcel(paramCharSequence, parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().showBootMessage(paramCharSequence, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void showWaitingForDebugger(IApplicationThread paramIApplicationThread, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
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
      if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().showWaitingForDebugger(paramIApplicationThread, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean shutdown(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(78, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().shutdown(paramInt);
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
  
  public void signalPersistentProcesses(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().signalPersistentProcesses(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int startActivity(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString2);
      parcel1.writeStrongBinder(paramIBinder);
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
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityManager.startActivity(paramIApplicationThread, paramString1, paramIntent, paramString2, paramIBinder, paramString3, paramInt1, paramInt2, paramProfilerInfo, paramBundle);
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
  
  public int startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        try {
          iBinder = paramIApplicationThread.asBinder();
        } finally {}
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString1);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString2);
      parcel1.writeStrongBinder(paramIBinder);
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
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(124, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityManager.startActivityAsUser(paramIApplicationThread, paramString1, paramIntent, paramString2, paramIBinder, paramString3, paramInt1, paramInt2, paramProfilerInfo, paramBundle, paramInt3);
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
  
  public int startActivityAsUserWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityManager.startActivityAsUserWithFeature(paramIApplicationThread, paramString1, paramString2, paramIntent, paramString3, paramIBinder, paramString4, paramInt1, paramInt2, paramProfilerInfo, paramBundle, paramInt3);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(161, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityManager.Stub.getDefaultImpl().startActivityFromRecents(paramInt, paramBundle);
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
  
  public int startActivityWithFeature(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager iActivityManager = IActivityManager.Stub.getDefaultImpl();
        try {
          paramInt1 = iActivityManager.startActivityWithFeature(paramIApplicationThread, paramString1, paramString2, paramIntent, paramString3, paramIBinder, paramString4, paramInt1, paramInt2, paramProfilerInfo, paramBundle);
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
  
  public boolean startBinderTracking() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(179, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().startBinderTracking();
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
  
  public void startConfirmDeviceCredentialIntent(Intent paramIntent, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(192, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().startConfirmDeviceCredentialIntent(paramIntent, paramBundle);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startDelegateShellPermissionIdentity(int paramInt, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(206, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().startDelegateShellPermissionIdentity(paramInt, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean startInstrumentation(ComponentName paramComponentName, String paramString1, int paramInt1, Bundle paramBundle, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt2, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        try {
          parcel1.writeInt(paramInt1);
          if (paramBundle != null) {
            parcel1.writeInt(1);
            paramBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          IBinder iBinder1 = null;
          if (paramIInstrumentationWatcher != null) {
            iBinder2 = paramIInstrumentationWatcher.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (paramIUiAutomationConnection != null)
            iBinder2 = paramIUiAutomationConnection.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeInt(paramInt2);
          parcel1.writeString(paramString2);
          if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
            bool = IActivityManager.Stub.getDefaultImpl().startInstrumentation(paramComponentName, paramString1, paramInt1, paramBundle, paramIInstrumentationWatcher, paramIUiAutomationConnection, paramInt2, paramString2);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramComponentName;
  }
  
  public void startRecentsActivity(Intent paramIntent, IAssistDataReceiver paramIAssistDataReceiver, IRecentsAnimationRunner paramIRecentsAnimationRunner) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
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
      if (!this.mRemote.transact(159, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().startRecentsActivity(paramIntent, paramIAssistDataReceiver, paramIRecentsAnimationRunner);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ComponentName startService(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString1, boolean paramBoolean, String paramString2, String paramString3, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      try {
        parcel1.writeString(paramString1);
        if (!paramBoolean)
          bool = false; 
        parcel1.writeInt(bool);
        try {
          parcel1.writeString(paramString2);
          try {
            parcel1.writeString(paramString3);
            try {
              parcel1.writeInt(paramInt);
              if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
                ComponentName componentName = IActivityManager.Stub.getDefaultImpl().startService(paramIApplicationThread, paramIntent, paramString1, paramBoolean, paramString2, paramString3, paramInt);
                parcel2.recycle();
                parcel1.recycle();
                return componentName;
              } 
              parcel2.readException();
              if (parcel2.readInt() != 0) {
                ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
              } else {
                paramIApplicationThread = null;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return (ComponentName)paramIApplicationThread;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
  
  public void startSystemLockTaskMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(162, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().startSystemLockTaskMode(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean startUserInBackground(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(157, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().startUserInBackground(paramInt);
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
  
  public boolean startUserInBackgroundWithListener(int paramInt, IProgressListener paramIProgressListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (paramIProgressListener != null) {
        iBinder = paramIProgressListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(205, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().startUserInBackgroundWithListener(paramInt, paramIProgressListener);
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
  
  public boolean startUserInForegroundWithListener(int paramInt, IProgressListener paramIProgressListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (paramIProgressListener != null) {
        iBinder = paramIProgressListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(209, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().startUserInForegroundWithListener(paramInt, paramIProgressListener);
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
  
  public void stopAppSwitches() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().stopAppSwitches();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramParcelFileDescriptor != null) {
        parcel1.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(180, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().stopBinderTrackingAndDump(paramParcelFileDescriptor);
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
  
  public void stopDelegateShellPermissionIdentity() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(207, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().stopDelegateShellPermissionIdentity();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int stopService(IApplicationThread paramIApplicationThread, Intent paramIntent, String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityManager.Stub.getDefaultImpl().stopService(paramIApplicationThread, paramIntent, paramString, paramInt);
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
  
  public boolean stopServiceToken(ComponentName paramComponentName, IBinder paramIBinder, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramComponentName != null) {
        parcel1.writeInt(1);
        paramComponentName.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().stopServiceToken(paramComponentName, paramIBinder, paramInt);
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
  
  public int stopUser(int paramInt, boolean paramBoolean, IStopUserCallback paramIStopUserCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (paramIStopUserCallback != null) {
        iBinder = paramIStopUserCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityManager.Stub.getDefaultImpl().stopUser(paramInt, paramBoolean, paramIStopUserCallback);
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
  
  public int stopUserWithDelayedLocking(int paramInt, boolean paramBoolean, IStopUserCallback paramIStopUserCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (paramIStopUserCallback != null) {
        iBinder = paramIStopUserCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        paramInt = IActivityManager.Stub.getDefaultImpl().stopUserWithDelayedLocking(paramInt, paramBoolean, paramIStopUserCallback);
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
  
  public void suppressResizeConfigChanges(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(182, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().suppressResizeConfigChanges(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean switchUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(104, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().switchUser(paramInt);
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
  
  public void unbindBackupAgent(ApplicationInfo paramApplicationInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramApplicationInfo != null) {
        parcel1.writeInt(1);
        paramApplicationInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unbindBackupAgent(paramApplicationInfo);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unbindFinished(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      boolean bool = true;
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!paramBoolean)
        bool = false; 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(64, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unbindFinished(paramIBinder, paramIntent, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean unbindService(IServiceConnection paramIServiceConnection) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIServiceConnection != null) {
        iBinder = paramIServiceConnection.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(31, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().unbindService(paramIServiceConnection);
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
  
  public void unbroadcastIntent(IApplicationThread paramIApplicationThread, Intent paramIntent, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIApplicationThread != null) {
        iBinder = paramIApplicationThread.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramIntent != null) {
        parcel1.writeInt(1);
        paramIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unbroadcastIntent(paramIApplicationThread, paramIntent, paramInt);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unhandledBack();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean unlockUser(int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, IProgressListener paramIProgressListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte1);
      parcel1.writeByteArray(paramArrayOfbyte2);
      if (paramIProgressListener != null) {
        iBinder = paramIProgressListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(185, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().unlockUser(paramInt, paramArrayOfbyte1, paramArrayOfbyte2, paramIProgressListener);
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
  
  public void unregisterIntentSenderCancelListener(IIntentSender paramIIntentSender, IResultReceiver paramIResultReceiver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      IBinder iBinder1 = null;
      if (paramIIntentSender != null) {
        iBinder2 = paramIIntentSender.asBinder();
      } else {
        iBinder2 = null;
      } 
      parcel1.writeStrongBinder(iBinder2);
      IBinder iBinder2 = iBinder1;
      if (paramIResultReceiver != null)
        iBinder2 = paramIResultReceiver.asBinder(); 
      parcel1.writeStrongBinder(iBinder2);
      if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unregisterIntentSenderCancelListener(paramIIntentSender, paramIResultReceiver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterProcessObserver(IProcessObserver paramIProcessObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIProcessObserver != null) {
        iBinder = paramIProcessObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unregisterProcessObserver(paramIProcessObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterReceiver(IIntentReceiver paramIIntentReceiver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIIntentReceiver != null) {
        iBinder = paramIIntentReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unregisterReceiver(paramIIntentReceiver);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramITaskStackListener != null) {
        iBinder = paramITaskStackListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(167, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unregisterTaskStackListener(paramITaskStackListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterUidObserver(IUidObserver paramIUidObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIUidObserver != null) {
        iBinder = paramIUidObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unregisterUidObserver(paramIUidObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterUserSwitchObserver(IUserSwitchObserver paramIUserSwitchObserver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIUserSwitchObserver != null) {
        iBinder = paramIUserSwitchObserver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unregisterUserSwitchObserver(paramIUserSwitchObserver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unstableProviderDied(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().unstableProviderDied(paramIBinder);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      boolean bool = true;
      if (paramConfiguration != null) {
        parcel1.writeInt(1);
        paramConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().updateConfiguration(paramConfiguration);
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
  
  public void updateDeviceOwner(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(178, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().updateDeviceOwner(paramString);
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
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(174, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().updateLockTaskPackages(paramInt, paramArrayOfString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean updateMccMncConfiguration(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(41, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        bool = IActivityManager.Stub.getDefaultImpl().updateMccMncConfiguration(paramString1, paramString2);
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
  
  public void updatePersistentConfiguration(Configuration paramConfiguration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramConfiguration != null) {
        parcel1.writeInt(1);
        paramConfiguration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().updatePersistentConfiguration(paramConfiguration);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateServiceGroup(IServiceConnection paramIServiceConnection, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      if (paramIServiceConnection != null) {
        iBinder = paramIServiceConnection.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().updateServiceGroup(paramIServiceConnection, paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void waitForNetworkStateUpdate(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IActivityManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(203, parcel1, parcel2, 0) && IActivityManager.Stub.getDefaultImpl() != null) {
        IActivityManager.Stub.getDefaultImpl().waitForNetworkStateUpdate(paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */