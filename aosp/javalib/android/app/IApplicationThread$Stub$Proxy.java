package android.app;

import android.app.servertransaction.ClientTransaction;
import android.content.AutofillOptions;
import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ParceledListSlice;
import android.content.pm.ProviderInfo;
import android.content.pm.ProviderInfoList;
import android.content.pm.ServiceInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import com.android.internal.app.IVoiceInteractor;
import java.util.Map;

class Proxy implements IApplicationThread {
  public static IApplicationThread sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void attachAgent(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(48, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().attachAgent(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void attachStartupAgents(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(49, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().attachStartupAgents(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void bindApplication(String paramString1, ApplicationInfo paramApplicationInfo, ProviderInfoList paramProviderInfoList, ComponentName paramComponentName, ProfilerInfo paramProfilerInfo, Bundle paramBundle1, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, Map paramMap, Bundle paramBundle2, String paramString2, AutofillOptions paramAutofillOptions, ContentCaptureOptions paramContentCaptureOptions, long[] paramArrayOflong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      boolean bool;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeString(paramString1);
      if (paramApplicationInfo != null) {
        try {
          parcel.writeInt(1);
          paramApplicationInfo.writeToParcel(parcel, 0);
        } finally {}
      } else {
        parcel.writeInt(0);
      } 
      if (paramProviderInfoList != null) {
        parcel.writeInt(1);
        paramProviderInfoList.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramComponentName != null) {
        parcel.writeInt(1);
        paramComponentName.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramProfilerInfo != null) {
        parcel.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBundle1 != null) {
        parcel.writeInt(1);
        paramBundle1.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramIInstrumentationWatcher != null) {
        iBinder = paramIInstrumentationWatcher.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramIUiAutomationConnection != null) {
        iBinder = paramIUiAutomationConnection.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeInt(paramInt);
      if (paramBoolean1) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramBoolean2) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramBoolean3) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramBoolean4) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramConfiguration != null) {
        parcel.writeInt(1);
        paramConfiguration.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramCompatibilityInfo != null) {
        parcel.writeInt(1);
        paramCompatibilityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeMap(paramMap);
      if (paramBundle2 != null) {
        parcel.writeInt(1);
        paramBundle2.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeString(paramString2);
      if (paramAutofillOptions != null) {
        parcel.writeInt(1);
        paramAutofillOptions.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramContentCaptureOptions != null) {
        parcel.writeInt(1);
        paramContentCaptureOptions.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeLongArray(paramArrayOflong);
      if (!this.mRemote.transact(4, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread iApplicationThread = IApplicationThread.Stub.getDefaultImpl();
        try {
          iApplicationThread.bindApplication(paramString1, paramApplicationInfo, paramProviderInfoList, paramComponentName, paramProfilerInfo, paramBundle1, paramIInstrumentationWatcher, paramIUiAutomationConnection, paramInt, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramConfiguration, paramCompatibilityInfo, paramMap, paramBundle2, paramString2, paramAutofillOptions, paramContentCaptureOptions, paramArrayOflong);
          parcel.recycle();
          return;
        } finally {}
      } else {
        parcel.recycle();
        return;
      } 
    } finally {}
    parcel.recycle();
    throw paramString1;
  }
  
  public void clearDnsCache() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(25, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().clearDnsCache();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dispatchPackageBroadcast(int paramInt, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeInt(paramInt);
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(21, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dispatchPackageBroadcast(paramInt, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dumpActivity(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String paramString, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStrongBinder(paramIBinder);
      parcel.writeString(paramString);
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(24, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dumpActivity(paramParcelFileDescriptor, paramIBinder, paramString, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dumpCacheInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(33, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dumpCacheInfo(paramParcelFileDescriptor, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dumpDbInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(35, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dumpDbInfo(paramParcelFileDescriptor, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dumpGfxInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(32, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dumpGfxInfo(paramParcelFileDescriptor, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dumpHeap(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramBoolean1) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramBoolean2) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramBoolean3) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      try {
        parcel.writeString(paramString);
        if (paramParcelFileDescriptor != null) {
          parcel.writeInt(1);
          paramParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (paramRemoteCallback != null) {
          parcel.writeInt(1);
          paramRemoteCallback.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          if (!this.mRemote.transact(23, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpHeap(paramBoolean1, paramBoolean2, paramBoolean3, paramString, paramParcelFileDescriptor, paramRemoteCallback);
            parcel.recycle();
            return;
          } 
          parcel.recycle();
          return;
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramString;
  }
  
  public void dumpMemInfo(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      boolean bool1 = false;
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramMemoryInfo != null) {
        parcel.writeInt(1);
        paramMemoryInfo.writeToParcel(parcel, 0);
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
      if (paramBoolean3) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      if (paramBoolean4) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      boolean bool2 = bool1;
      if (paramBoolean5)
        bool2 = true; 
      parcel.writeInt(bool2);
      try {
        parcel.writeStringArray(paramArrayOfString);
        try {
          if (!this.mRemote.transact(30, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpMemInfo(paramParcelFileDescriptor, paramMemoryInfo, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, paramArrayOfString);
            parcel.recycle();
            return;
          } 
          parcel.recycle();
          return;
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramParcelFileDescriptor;
  }
  
  public void dumpMemInfoProto(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      boolean bool1 = false;
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramMemoryInfo != null) {
        parcel.writeInt(1);
        paramMemoryInfo.writeToParcel(parcel, 0);
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
      if (paramBoolean3) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel.writeInt(bool2);
      boolean bool2 = bool1;
      if (paramBoolean4)
        bool2 = true; 
      parcel.writeInt(bool2);
      try {
        parcel.writeStringArray(paramArrayOfString);
        try {
          if (!this.mRemote.transact(31, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpMemInfoProto(paramParcelFileDescriptor, paramMemoryInfo, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramArrayOfString);
            parcel.recycle();
            return;
          } 
          parcel.recycle();
          return;
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramParcelFileDescriptor;
  }
  
  public void dumpProvider(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStrongBinder(paramIBinder);
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(34, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dumpProvider(paramParcelFileDescriptor, paramIBinder, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void dumpService(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStrongBinder(paramIBinder);
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(12, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().dumpService(paramParcelFileDescriptor, paramIBinder, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IApplicationThread";
  }
  
  public void handleTrustStorageUpdate() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(47, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().handleTrustStorageUpdate();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void notifyCleartextNetwork(byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(43, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().notifyCleartextNetwork(paramArrayOfbyte);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void performDirectAction(IBinder paramIBinder, String paramString, Bundle paramBundle, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramRemoteCallback1 != null) {
        parcel.writeInt(1);
        paramRemoteCallback1.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramRemoteCallback2 != null) {
        parcel.writeInt(1);
        paramRemoteCallback2.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(54, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().performDirectAction(paramIBinder, paramString, paramBundle, paramRemoteCallback1, paramRemoteCallback2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void processInBackground() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(9, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().processInBackground();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void profilerControl(boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramProfilerInfo != null) {
        parcel.writeInt(1);
        paramProfilerInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(15, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().profilerControl(paramBoolean, paramProfilerInfo, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void requestAssistContextExtras(IBinder paramIBinder1, IBinder paramIBinder2, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder1);
      parcel.writeStrongBinder(paramIBinder2);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(37, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().requestAssistContextExtras(paramIBinder1, paramIBinder2, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void requestDirectActions(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramIVoiceInteractor != null) {
        iBinder = paramIVoiceInteractor.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (paramRemoteCallback1 != null) {
        parcel.writeInt(1);
        paramRemoteCallback1.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramRemoteCallback2 != null) {
        parcel.writeInt(1);
        paramRemoteCallback2.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(53, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().requestDirectActions(paramIBinder, paramIVoiceInteractor, paramRemoteCallback1, paramRemoteCallback2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void runIsolatedEntryPoint(String paramString, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeString(paramString);
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(5, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().runIsolatedEntryPoint(paramString, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleApplicationInfoChanged(ApplicationInfo paramApplicationInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramApplicationInfo != null) {
        parcel.writeInt(1);
        paramApplicationInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(50, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleApplicationInfoChanged(paramApplicationInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleBindService(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      boolean bool = false;
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleBindService(paramIBinder, paramIntent, paramBoolean, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleCrash(String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeString(paramString);
      if (!this.mRemote.transact(22, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleCrash(paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleCreateBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramApplicationInfo != null) {
        parcel.writeInt(1);
        paramApplicationInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramCompatibilityInfo != null) {
        parcel.writeInt(1);
        paramCompatibilityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(17, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleCreateBackupAgent(paramApplicationInfo, paramCompatibilityInfo, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleCreateService(IBinder paramIBinder, ServiceInfo paramServiceInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramServiceInfo != null) {
        parcel.writeInt(1);
        paramServiceInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramCompatibilityInfo != null) {
        parcel.writeInt(1);
        paramCompatibilityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleCreateService(paramIBinder, paramServiceInfo, paramCompatibilityInfo, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleDestroyBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramApplicationInfo != null) {
        parcel.writeInt(1);
        paramApplicationInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramCompatibilityInfo != null) {
        parcel.writeInt(1);
        paramCompatibilityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleDestroyBackupAgent(paramApplicationInfo, paramCompatibilityInfo, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleEnterAnimationComplete(IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(42, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleEnterAnimationComplete(paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleExit() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(6, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleExit();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleInstallProvider(ProviderInfo paramProviderInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramProviderInfo != null) {
        parcel.writeInt(1);
        paramProviderInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(40, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleInstallProvider(paramProviderInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleLocalVoiceInteractionStarted(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramIVoiceInteractor != null) {
        iBinder = paramIVoiceInteractor.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(46, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleLocalVoiceInteractionStarted(paramIBinder, paramIVoiceInteractor);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleLowMemory() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(14, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleLowMemory();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleOnNewActivityOptions(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(19, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleOnNewActivityOptions(paramIBinder, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleReceiver(Intent paramIntent, ActivityInfo paramActivityInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      boolean bool = false;
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramActivityInfo != null) {
        parcel.writeInt(1);
        paramActivityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramCompatibilityInfo != null) {
        parcel.writeInt(1);
        paramCompatibilityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt1);
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(1, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleReceiver(paramIntent, paramActivityInfo, paramCompatibilityInfo, paramInt1, paramString, paramBundle, paramBoolean, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleRegisteredReceiver(IIntentReceiver paramIIntentReceiver, Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramIIntentReceiver != null) {
        iBinder = paramIIntentReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      boolean bool = false;
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      try {
        parcel.writeInt(paramInt1);
        try {
          parcel.writeString(paramString);
          if (paramBundle != null) {
            parcel.writeInt(1);
            paramBundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (paramBoolean1) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          parcel.writeInt(bool1);
          boolean bool1 = bool;
          if (paramBoolean2)
            bool1 = true; 
          parcel.writeInt(bool1);
          parcel.writeInt(paramInt2);
          parcel.writeInt(paramInt3);
          if (!this.mRemote.transact(13, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleRegisteredReceiver(paramIIntentReceiver, paramIntent, paramInt1, paramString, paramBundle, paramBoolean1, paramBoolean2, paramInt2, paramInt3);
            parcel.recycle();
            return;
          } 
          parcel.recycle();
          return;
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramIIntentReceiver;
  }
  
  public void scheduleServiceArgs(IBinder paramIBinder, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramParceledListSlice != null) {
        parcel.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(7, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleServiceArgs(paramIBinder, paramParceledListSlice);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleStopService(IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(3, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleStopService(paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleSuicide() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(20, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleSuicide();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleTransaction(ClientTransaction paramClientTransaction) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramClientTransaction != null) {
        parcel.writeInt(1);
        paramClientTransaction.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(52, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleTransaction(paramClientTransaction);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleTranslucentConversionComplete(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(38, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleTranslucentConversionComplete(paramIBinder, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleTrimMemory(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(29, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleTrimMemory(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void scheduleUnbindService(IBinder paramIBinder, Intent paramIntent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().scheduleUnbindService(paramIBinder, paramIntent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setCoreSettings(Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().setCoreSettings(paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setNetworkBlockSeq(long paramLong) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeLong(paramLong);
      if (!this.mRemote.transact(51, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().setNetworkBlockSeq(paramLong);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setProcessState(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(39, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().setProcessState(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setSchedulingGroup(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(16, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().setSchedulingGroup(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void startBinderTracking() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(44, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().startBinderTracking();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (paramParcelFileDescriptor != null) {
        parcel.writeInt(1);
        paramParcelFileDescriptor.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(45, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().stopBinderTrackingAndDump(paramParcelFileDescriptor);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void unstableProviderDied(IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(36, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().unstableProviderDied(paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void updateHttpProxy() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(26, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().updateHttpProxy();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void updatePackageCompatibilityInfo(String paramString, CompatibilityInfo paramCompatibilityInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeString(paramString);
      if (paramCompatibilityInfo != null) {
        parcel.writeInt(1);
        paramCompatibilityInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(28, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().updatePackageCompatibilityInfo(paramString, paramCompatibilityInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void updateTimePrefs(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(41, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().updateTimePrefs(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void updateTimeZone() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IApplicationThread");
      if (!this.mRemote.transact(8, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
        IApplicationThread.Stub.getDefaultImpl().updateTimeZone();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IApplicationThread$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */