package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

class Proxy implements ICrossProfileApps {
  public static ICrossProfileApps sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean canConfigureInteractAcrossProfiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        bool = ICrossProfileApps.Stub.getDefaultImpl().canConfigureInteractAcrossProfiles(paramString);
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
  
  public boolean canInteractAcrossProfiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(4, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        bool = ICrossProfileApps.Stub.getDefaultImpl().canInteractAcrossProfiles(paramString);
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
  
  public boolean canRequestInteractAcrossProfiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        bool = ICrossProfileApps.Stub.getDefaultImpl().canRequestInteractAcrossProfiles(paramString);
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
  
  public boolean canUserAttemptToConfigureInteractAcrossProfiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        bool = ICrossProfileApps.Stub.getDefaultImpl().canUserAttemptToConfigureInteractAcrossProfiles(paramString);
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
  
  public void clearInteractAcrossProfilesAppOps() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        ICrossProfileApps.Stub.getDefaultImpl().clearInteractAcrossProfilesAppOps();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.ICrossProfileApps";
  }
  
  public List<UserHandle> getTargetUserProfiles(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null)
        return ICrossProfileApps.Stub.getDefaultImpl().getTargetUserProfiles(paramString); 
      parcel2.readException();
      return parcel2.createTypedArrayList(UserHandle.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resetInteractAcrossProfilesAppOps(List<String> paramList) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeStringList(paramList);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        ICrossProfileApps.Stub.getDefaultImpl().resetInteractAcrossProfilesAppOps(paramList);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setInteractAcrossProfilesAppOp(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
        ICrossProfileApps.Stub.getDefaultImpl().setInteractAcrossProfilesAppOp(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
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
          if (paramComponentName != null) {
            parcel1.writeInt(1);
            paramComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            boolean bool;
            parcel1.writeInt(paramInt);
            if (paramBoolean) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel1.writeInt(bool);
            try {
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
                ICrossProfileApps.Stub.getDefaultImpl().startActivityAsUser(paramIApplicationThread, paramString1, paramString2, paramComponentName, paramInt, paramBoolean);
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
    throw paramIApplicationThread;
  }
  
  public void startActivityAsUserByIntent(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, int paramInt, IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.ICrossProfileApps");
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
          if (paramIntent != null) {
            parcel1.writeInt(1);
            paramIntent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt);
            parcel1.writeStrongBinder(paramIBinder);
            if (paramBundle != null) {
              parcel1.writeInt(1);
              paramBundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ICrossProfileApps.Stub.getDefaultImpl() != null) {
              ICrossProfileApps.Stub.getDefaultImpl().startActivityAsUserByIntent(paramIApplicationThread, paramString1, paramString2, paramIntent, paramInt, paramIBinder, paramBundle);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramIApplicationThread;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ICrossProfileApps$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */