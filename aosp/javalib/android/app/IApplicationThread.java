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
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import com.android.internal.app.IVoiceInteractor;
import java.util.HashMap;
import java.util.Map;

public interface IApplicationThread extends IInterface {
  void attachAgent(String paramString) throws RemoteException;
  
  void attachStartupAgents(String paramString) throws RemoteException;
  
  void bindApplication(String paramString1, ApplicationInfo paramApplicationInfo, ProviderInfoList paramProviderInfoList, ComponentName paramComponentName, ProfilerInfo paramProfilerInfo, Bundle paramBundle1, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, Map paramMap, Bundle paramBundle2, String paramString2, AutofillOptions paramAutofillOptions, ContentCaptureOptions paramContentCaptureOptions, long[] paramArrayOflong) throws RemoteException;
  
  void clearDnsCache() throws RemoteException;
  
  void dispatchPackageBroadcast(int paramInt, String[] paramArrayOfString) throws RemoteException;
  
  void dumpActivity(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String paramString, String[] paramArrayOfString) throws RemoteException;
  
  void dumpCacheInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException;
  
  void dumpDbInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException;
  
  void dumpGfxInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException;
  
  void dumpHeap(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  void dumpMemInfo(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String[] paramArrayOfString) throws RemoteException;
  
  void dumpMemInfoProto(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String[] paramArrayOfString) throws RemoteException;
  
  void dumpProvider(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) throws RemoteException;
  
  void dumpService(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) throws RemoteException;
  
  void handleTrustStorageUpdate() throws RemoteException;
  
  void notifyCleartextNetwork(byte[] paramArrayOfbyte) throws RemoteException;
  
  void performDirectAction(IBinder paramIBinder, String paramString, Bundle paramBundle, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) throws RemoteException;
  
  void processInBackground() throws RemoteException;
  
  void profilerControl(boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt) throws RemoteException;
  
  void requestAssistContextExtras(IBinder paramIBinder1, IBinder paramIBinder2, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void requestDirectActions(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) throws RemoteException;
  
  void runIsolatedEntryPoint(String paramString, String[] paramArrayOfString) throws RemoteException;
  
  void scheduleApplicationInfoChanged(ApplicationInfo paramApplicationInfo) throws RemoteException;
  
  void scheduleBindService(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean, int paramInt) throws RemoteException;
  
  void scheduleCrash(String paramString) throws RemoteException;
  
  void scheduleCreateBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, int paramInt2) throws RemoteException;
  
  void scheduleCreateService(IBinder paramIBinder, ServiceInfo paramServiceInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) throws RemoteException;
  
  void scheduleDestroyBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) throws RemoteException;
  
  void scheduleEnterAnimationComplete(IBinder paramIBinder) throws RemoteException;
  
  void scheduleExit() throws RemoteException;
  
  void scheduleInstallProvider(ProviderInfo paramProviderInfo) throws RemoteException;
  
  void scheduleLocalVoiceInteractionStarted(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor) throws RemoteException;
  
  void scheduleLowMemory() throws RemoteException;
  
  void scheduleOnNewActivityOptions(IBinder paramIBinder, Bundle paramBundle) throws RemoteException;
  
  void scheduleReceiver(Intent paramIntent, ActivityInfo paramActivityInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2, int paramInt3) throws RemoteException;
  
  void scheduleRegisteredReceiver(IIntentReceiver paramIIntentReceiver, Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3) throws RemoteException;
  
  void scheduleServiceArgs(IBinder paramIBinder, ParceledListSlice paramParceledListSlice) throws RemoteException;
  
  void scheduleStopService(IBinder paramIBinder) throws RemoteException;
  
  void scheduleSuicide() throws RemoteException;
  
  void scheduleTransaction(ClientTransaction paramClientTransaction) throws RemoteException;
  
  void scheduleTranslucentConversionComplete(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void scheduleTrimMemory(int paramInt) throws RemoteException;
  
  void scheduleUnbindService(IBinder paramIBinder, Intent paramIntent) throws RemoteException;
  
  void setCoreSettings(Bundle paramBundle) throws RemoteException;
  
  void setNetworkBlockSeq(long paramLong) throws RemoteException;
  
  void setProcessState(int paramInt) throws RemoteException;
  
  void setSchedulingGroup(int paramInt) throws RemoteException;
  
  void startBinderTracking() throws RemoteException;
  
  void stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException;
  
  void unstableProviderDied(IBinder paramIBinder) throws RemoteException;
  
  void updateHttpProxy() throws RemoteException;
  
  void updatePackageCompatibilityInfo(String paramString, CompatibilityInfo paramCompatibilityInfo) throws RemoteException;
  
  void updateTimePrefs(int paramInt) throws RemoteException;
  
  void updateTimeZone() throws RemoteException;
  
  public static class Default implements IApplicationThread {
    public IBinder asBinder() {
      return null;
    }
    
    public void attachAgent(String param1String) throws RemoteException {}
    
    public void attachStartupAgents(String param1String) throws RemoteException {}
    
    public void bindApplication(String param1String1, ApplicationInfo param1ApplicationInfo, ProviderInfoList param1ProviderInfoList, ComponentName param1ComponentName, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle1, IInstrumentationWatcher param1IInstrumentationWatcher, IUiAutomationConnection param1IUiAutomationConnection, int param1Int, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, Configuration param1Configuration, CompatibilityInfo param1CompatibilityInfo, Map param1Map, Bundle param1Bundle2, String param1String2, AutofillOptions param1AutofillOptions, ContentCaptureOptions param1ContentCaptureOptions, long[] param1ArrayOflong) throws RemoteException {}
    
    public void clearDnsCache() throws RemoteException {}
    
    public void dispatchPackageBroadcast(int param1Int, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpActivity(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String param1String, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpCacheInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpDbInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpGfxInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpHeap(boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, String param1String, ParcelFileDescriptor param1ParcelFileDescriptor, RemoteCallback param1RemoteCallback) throws RemoteException {}
    
    public void dumpMemInfo(ParcelFileDescriptor param1ParcelFileDescriptor, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpMemInfoProto(ParcelFileDescriptor param1ParcelFileDescriptor, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpProvider(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String[] param1ArrayOfString) throws RemoteException {}
    
    public void dumpService(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String[] param1ArrayOfString) throws RemoteException {}
    
    public void handleTrustStorageUpdate() throws RemoteException {}
    
    public void notifyCleartextNetwork(byte[] param1ArrayOfbyte) throws RemoteException {}
    
    public void performDirectAction(IBinder param1IBinder, String param1String, Bundle param1Bundle, RemoteCallback param1RemoteCallback1, RemoteCallback param1RemoteCallback2) throws RemoteException {}
    
    public void processInBackground() throws RemoteException {}
    
    public void profilerControl(boolean param1Boolean, ProfilerInfo param1ProfilerInfo, int param1Int) throws RemoteException {}
    
    public void requestAssistContextExtras(IBinder param1IBinder1, IBinder param1IBinder2, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void requestDirectActions(IBinder param1IBinder, IVoiceInteractor param1IVoiceInteractor, RemoteCallback param1RemoteCallback1, RemoteCallback param1RemoteCallback2) throws RemoteException {}
    
    public void runIsolatedEntryPoint(String param1String, String[] param1ArrayOfString) throws RemoteException {}
    
    public void scheduleApplicationInfoChanged(ApplicationInfo param1ApplicationInfo) throws RemoteException {}
    
    public void scheduleBindService(IBinder param1IBinder, Intent param1Intent, boolean param1Boolean, int param1Int) throws RemoteException {}
    
    public void scheduleCrash(String param1String) throws RemoteException {}
    
    public void scheduleCreateBackupAgent(ApplicationInfo param1ApplicationInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void scheduleCreateService(IBinder param1IBinder, ServiceInfo param1ServiceInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int) throws RemoteException {}
    
    public void scheduleDestroyBackupAgent(ApplicationInfo param1ApplicationInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int) throws RemoteException {}
    
    public void scheduleEnterAnimationComplete(IBinder param1IBinder) throws RemoteException {}
    
    public void scheduleExit() throws RemoteException {}
    
    public void scheduleInstallProvider(ProviderInfo param1ProviderInfo) throws RemoteException {}
    
    public void scheduleLocalVoiceInteractionStarted(IBinder param1IBinder, IVoiceInteractor param1IVoiceInteractor) throws RemoteException {}
    
    public void scheduleLowMemory() throws RemoteException {}
    
    public void scheduleOnNewActivityOptions(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {}
    
    public void scheduleReceiver(Intent param1Intent, ActivityInfo param1ActivityInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void scheduleRegisteredReceiver(IIntentReceiver param1IIntentReceiver, Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void scheduleServiceArgs(IBinder param1IBinder, ParceledListSlice param1ParceledListSlice) throws RemoteException {}
    
    public void scheduleStopService(IBinder param1IBinder) throws RemoteException {}
    
    public void scheduleSuicide() throws RemoteException {}
    
    public void scheduleTransaction(ClientTransaction param1ClientTransaction) throws RemoteException {}
    
    public void scheduleTranslucentConversionComplete(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void scheduleTrimMemory(int param1Int) throws RemoteException {}
    
    public void scheduleUnbindService(IBinder param1IBinder, Intent param1Intent) throws RemoteException {}
    
    public void setCoreSettings(Bundle param1Bundle) throws RemoteException {}
    
    public void setNetworkBlockSeq(long param1Long) throws RemoteException {}
    
    public void setProcessState(int param1Int) throws RemoteException {}
    
    public void setSchedulingGroup(int param1Int) throws RemoteException {}
    
    public void startBinderTracking() throws RemoteException {}
    
    public void stopBinderTrackingAndDump(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {}
    
    public void unstableProviderDied(IBinder param1IBinder) throws RemoteException {}
    
    public void updateHttpProxy() throws RemoteException {}
    
    public void updatePackageCompatibilityInfo(String param1String, CompatibilityInfo param1CompatibilityInfo) throws RemoteException {}
    
    public void updateTimePrefs(int param1Int) throws RemoteException {}
    
    public void updateTimeZone() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IApplicationThread {
    private static final String DESCRIPTOR = "android.app.IApplicationThread";
    
    static final int TRANSACTION_attachAgent = 48;
    
    static final int TRANSACTION_attachStartupAgents = 49;
    
    static final int TRANSACTION_bindApplication = 4;
    
    static final int TRANSACTION_clearDnsCache = 25;
    
    static final int TRANSACTION_dispatchPackageBroadcast = 21;
    
    static final int TRANSACTION_dumpActivity = 24;
    
    static final int TRANSACTION_dumpCacheInfo = 33;
    
    static final int TRANSACTION_dumpDbInfo = 35;
    
    static final int TRANSACTION_dumpGfxInfo = 32;
    
    static final int TRANSACTION_dumpHeap = 23;
    
    static final int TRANSACTION_dumpMemInfo = 30;
    
    static final int TRANSACTION_dumpMemInfoProto = 31;
    
    static final int TRANSACTION_dumpProvider = 34;
    
    static final int TRANSACTION_dumpService = 12;
    
    static final int TRANSACTION_handleTrustStorageUpdate = 47;
    
    static final int TRANSACTION_notifyCleartextNetwork = 43;
    
    static final int TRANSACTION_performDirectAction = 54;
    
    static final int TRANSACTION_processInBackground = 9;
    
    static final int TRANSACTION_profilerControl = 15;
    
    static final int TRANSACTION_requestAssistContextExtras = 37;
    
    static final int TRANSACTION_requestDirectActions = 53;
    
    static final int TRANSACTION_runIsolatedEntryPoint = 5;
    
    static final int TRANSACTION_scheduleApplicationInfoChanged = 50;
    
    static final int TRANSACTION_scheduleBindService = 10;
    
    static final int TRANSACTION_scheduleCrash = 22;
    
    static final int TRANSACTION_scheduleCreateBackupAgent = 17;
    
    static final int TRANSACTION_scheduleCreateService = 2;
    
    static final int TRANSACTION_scheduleDestroyBackupAgent = 18;
    
    static final int TRANSACTION_scheduleEnterAnimationComplete = 42;
    
    static final int TRANSACTION_scheduleExit = 6;
    
    static final int TRANSACTION_scheduleInstallProvider = 40;
    
    static final int TRANSACTION_scheduleLocalVoiceInteractionStarted = 46;
    
    static final int TRANSACTION_scheduleLowMemory = 14;
    
    static final int TRANSACTION_scheduleOnNewActivityOptions = 19;
    
    static final int TRANSACTION_scheduleReceiver = 1;
    
    static final int TRANSACTION_scheduleRegisteredReceiver = 13;
    
    static final int TRANSACTION_scheduleServiceArgs = 7;
    
    static final int TRANSACTION_scheduleStopService = 3;
    
    static final int TRANSACTION_scheduleSuicide = 20;
    
    static final int TRANSACTION_scheduleTransaction = 52;
    
    static final int TRANSACTION_scheduleTranslucentConversionComplete = 38;
    
    static final int TRANSACTION_scheduleTrimMemory = 29;
    
    static final int TRANSACTION_scheduleUnbindService = 11;
    
    static final int TRANSACTION_setCoreSettings = 27;
    
    static final int TRANSACTION_setNetworkBlockSeq = 51;
    
    static final int TRANSACTION_setProcessState = 39;
    
    static final int TRANSACTION_setSchedulingGroup = 16;
    
    static final int TRANSACTION_startBinderTracking = 44;
    
    static final int TRANSACTION_stopBinderTrackingAndDump = 45;
    
    static final int TRANSACTION_unstableProviderDied = 36;
    
    static final int TRANSACTION_updateHttpProxy = 26;
    
    static final int TRANSACTION_updatePackageCompatibilityInfo = 28;
    
    static final int TRANSACTION_updateTimePrefs = 41;
    
    static final int TRANSACTION_updateTimeZone = 8;
    
    public Stub() {
      attachInterface(this, "android.app.IApplicationThread");
    }
    
    public static IApplicationThread asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IApplicationThread");
      return (iInterface != null && iInterface instanceof IApplicationThread) ? (IApplicationThread)iInterface : new Proxy(param1IBinder);
    }
    
    public static IApplicationThread getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 54:
          return "performDirectAction";
        case 53:
          return "requestDirectActions";
        case 52:
          return "scheduleTransaction";
        case 51:
          return "setNetworkBlockSeq";
        case 50:
          return "scheduleApplicationInfoChanged";
        case 49:
          return "attachStartupAgents";
        case 48:
          return "attachAgent";
        case 47:
          return "handleTrustStorageUpdate";
        case 46:
          return "scheduleLocalVoiceInteractionStarted";
        case 45:
          return "stopBinderTrackingAndDump";
        case 44:
          return "startBinderTracking";
        case 43:
          return "notifyCleartextNetwork";
        case 42:
          return "scheduleEnterAnimationComplete";
        case 41:
          return "updateTimePrefs";
        case 40:
          return "scheduleInstallProvider";
        case 39:
          return "setProcessState";
        case 38:
          return "scheduleTranslucentConversionComplete";
        case 37:
          return "requestAssistContextExtras";
        case 36:
          return "unstableProviderDied";
        case 35:
          return "dumpDbInfo";
        case 34:
          return "dumpProvider";
        case 33:
          return "dumpCacheInfo";
        case 32:
          return "dumpGfxInfo";
        case 31:
          return "dumpMemInfoProto";
        case 30:
          return "dumpMemInfo";
        case 29:
          return "scheduleTrimMemory";
        case 28:
          return "updatePackageCompatibilityInfo";
        case 27:
          return "setCoreSettings";
        case 26:
          return "updateHttpProxy";
        case 25:
          return "clearDnsCache";
        case 24:
          return "dumpActivity";
        case 23:
          return "dumpHeap";
        case 22:
          return "scheduleCrash";
        case 21:
          return "dispatchPackageBroadcast";
        case 20:
          return "scheduleSuicide";
        case 19:
          return "scheduleOnNewActivityOptions";
        case 18:
          return "scheduleDestroyBackupAgent";
        case 17:
          return "scheduleCreateBackupAgent";
        case 16:
          return "setSchedulingGroup";
        case 15:
          return "profilerControl";
        case 14:
          return "scheduleLowMemory";
        case 13:
          return "scheduleRegisteredReceiver";
        case 12:
          return "dumpService";
        case 11:
          return "scheduleUnbindService";
        case 10:
          return "scheduleBindService";
        case 9:
          return "processInBackground";
        case 8:
          return "updateTimeZone";
        case 7:
          return "scheduleServiceArgs";
        case 6:
          return "scheduleExit";
        case 5:
          return "runIsolatedEntryPoint";
        case 4:
          return "bindApplication";
        case 3:
          return "scheduleStopService";
        case 2:
          return "scheduleCreateService";
        case 1:
          break;
      } 
      return "scheduleReceiver";
    }
    
    public static boolean setDefaultImpl(IApplicationThread param1IApplicationThread) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IApplicationThread != null) {
          Proxy.sDefaultImpl = param1IApplicationThread;
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
      IBinder iBinder;
      if (param1Int1 != 1598968902) {
        IBinder iBinder1;
        String str1;
        IBinder iBinder3;
        IVoiceInteractor iVoiceInteractor;
        IIntentReceiver iIntentReceiver;
        IBinder iBinder2;
        String str2;
        RemoteCallback remoteCallback;
        IBinder iBinder5;
        String str3;
        IBinder iBinder4;
        boolean bool5;
        String str4;
        Bundle bundle1;
        IInstrumentationWatcher iInstrumentationWatcher;
        IUiAutomationConnection iUiAutomationConnection;
        Configuration configuration;
        CompatibilityInfo compatibilityInfo;
        HashMap hashMap;
        Bundle bundle2;
        String str6;
        AutofillOptions autofillOptions;
        ContentCaptureOptions contentCaptureOptions;
        boolean bool1 = false;
        boolean bool2 = false;
        boolean bool3 = false;
        boolean bool4 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 54:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder3 = param1Parcel1.readStrongBinder();
            str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              remoteCallback = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            performDirectAction(iBinder3, str2, (Bundle)param1Parcel2, remoteCallback, (RemoteCallback)param1Parcel1);
            return true;
          case 53:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder5 = param1Parcel1.readStrongBinder();
            iVoiceInteractor = IVoiceInteractor.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            requestDirectActions(iBinder5, iVoiceInteractor, (RemoteCallback)param1Parcel2, (RemoteCallback)param1Parcel1);
            return true;
          case 52:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ClientTransaction clientTransaction = (ClientTransaction)ClientTransaction.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            scheduleTransaction((ClientTransaction)param1Parcel1);
            return true;
          case 51:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            setNetworkBlockSeq(param1Parcel1.readLong());
            return true;
          case 50:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            scheduleApplicationInfoChanged((ApplicationInfo)param1Parcel1);
            return true;
          case 49:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            attachStartupAgents(param1Parcel1.readString());
            return true;
          case 48:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            attachAgent(param1Parcel1.readString());
            return true;
          case 47:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            handleTrustStorageUpdate();
            return true;
          case 46:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleLocalVoiceInteractionStarted(param1Parcel1.readStrongBinder(), IVoiceInteractor.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 45:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            stopBinderTrackingAndDump((ParcelFileDescriptor)param1Parcel1);
            return true;
          case 44:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            startBinderTracking();
            return true;
          case 43:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            notifyCleartextNetwork(param1Parcel1.createByteArray());
            return true;
          case 42:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleEnterAnimationComplete(param1Parcel1.readStrongBinder());
            return true;
          case 41:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            updateTimePrefs(param1Parcel1.readInt());
            return true;
          case 40:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ProviderInfo providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            scheduleInstallProvider((ProviderInfo)param1Parcel1);
            return true;
          case 39:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            setProcessState(param1Parcel1.readInt());
            return true;
          case 38:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder1 = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            scheduleTranslucentConversionComplete(iBinder1, bool4);
            return true;
          case 37:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            requestAssistContextExtras(param1Parcel1.readStrongBinder(), param1Parcel1.readStrongBinder(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 36:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            unstableProviderDied(param1Parcel1.readStrongBinder());
            return true;
          case 35:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder1 = null;
            } 
            dumpDbInfo((ParcelFileDescriptor)iBinder1, param1Parcel1.createStringArray());
            return true;
          case 34:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder1 = null;
            } 
            dumpProvider((ParcelFileDescriptor)iBinder1, param1Parcel1.readStrongBinder(), param1Parcel1.createStringArray());
            return true;
          case 33:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder1 = null;
            } 
            dumpCacheInfo((ParcelFileDescriptor)iBinder1, param1Parcel1.createStringArray());
            return true;
          case 32:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder1 = null;
            } 
            dumpGfxInfo((ParcelFileDescriptor)iBinder1, param1Parcel1.createStringArray());
            return true;
          case 31:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder1 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo)Debug.MemoryInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder5 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool4 = true;
            } else {
              bool4 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool3 = true;
            } else {
              bool3 = false;
            } 
            dumpMemInfoProto((ParcelFileDescriptor)iBinder1, (Debug.MemoryInfo)iBinder5, bool4, bool1, bool2, bool3, param1Parcel1.createStringArray());
            return true;
          case 30:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder1 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo)Debug.MemoryInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder5 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool4 = true;
            } else {
              bool4 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool3 = true;
            } else {
              bool3 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool5 = true;
            } else {
              bool5 = false;
            } 
            dumpMemInfo((ParcelFileDescriptor)iBinder1, (Debug.MemoryInfo)iBinder5, bool4, bool1, bool2, bool3, bool5, param1Parcel1.createStringArray());
            return true;
          case 29:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleTrimMemory(param1Parcel1.readInt());
            return true;
          case 28:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            str1 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            updatePackageCompatibilityInfo(str1, (CompatibilityInfo)param1Parcel1);
            return true;
          case 27:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            setCoreSettings((Bundle)param1Parcel1);
            return true;
          case 26:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            updateHttpProxy();
            return true;
          case 25:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            clearDnsCache();
            return true;
          case 24:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str1 = null;
            } 
            dumpActivity((ParcelFileDescriptor)str1, param1Parcel1.readStrongBinder(), param1Parcel1.readString(), param1Parcel1.createStringArray());
            return true;
          case 23:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              bool4 = true;
            } else {
              bool4 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            str3 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str1 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            dumpHeap(bool4, bool1, bool2, str3, (ParcelFileDescriptor)str1, (RemoteCallback)param1Parcel1);
            return true;
          case 22:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleCrash(param1Parcel1.readString());
            return true;
          case 21:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            dispatchPackageBroadcast(param1Parcel1.readInt(), param1Parcel1.createStringArray());
            return true;
          case 20:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleSuicide();
            return true;
          case 19:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            scheduleOnNewActivityOptions(iBinder, (Bundle)param1Parcel1);
            return true;
          case 18:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str3 = null;
            } 
            scheduleDestroyBackupAgent((ApplicationInfo)iBinder, (CompatibilityInfo)str3, param1Parcel1.readInt());
            return true;
          case 17:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str3 = null;
            } 
            scheduleCreateBackupAgent((ApplicationInfo)iBinder, (CompatibilityInfo)str3, param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 16:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            setSchedulingGroup(param1Parcel1.readInt());
            return true;
          case 15:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            bool4 = bool1;
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            if (param1Parcel1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            profilerControl(bool4, (ProfilerInfo)iBinder, param1Parcel1.readInt());
            return true;
          case 14:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleLowMemory();
            return true;
          case 13:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iIntentReceiver = IIntentReceiver.Stub.asInterface(param1Parcel1.readStrongBinder());
            if (param1Parcel1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            param1Int1 = param1Parcel1.readInt();
            str2 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str3 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool4 = true;
            } else {
              bool4 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            scheduleRegisteredReceiver(iIntentReceiver, (Intent)iBinder, param1Int1, str2, (Bundle)str3, bool4, bool1, param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 12:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            if (param1Parcel1.readInt() != 0) {
              ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            dumpService((ParcelFileDescriptor)iBinder, param1Parcel1.readStrongBinder(), param1Parcel1.createStringArray());
            return true;
          case 11:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            scheduleUnbindService(iBinder, (Intent)param1Parcel1);
            return true;
          case 10:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder4 = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            bool4 = bool2;
            if (param1Parcel1.readInt() != 0)
              bool4 = true; 
            scheduleBindService(iBinder4, (Intent)iBinder, bool4, param1Parcel1.readInt());
            return true;
          case 9:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            processInBackground();
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            updateTimeZone();
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0) {
              ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            scheduleServiceArgs(iBinder, (ParceledListSlice)param1Parcel1);
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleExit();
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            runIsolatedEntryPoint(param1Parcel1.readString(), param1Parcel1.createStringArray());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            str4 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              ProviderInfoList providerInfoList = (ProviderInfoList)ProviderInfoList.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder4 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iIntentReceiver = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str2 = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bundle1 = null;
            } 
            iInstrumentationWatcher = IInstrumentationWatcher.Stub.asInterface(param1Parcel1.readStrongBinder());
            iUiAutomationConnection = IUiAutomationConnection.Stub.asInterface(param1Parcel1.readStrongBinder());
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool4 = true;
            } else {
              bool4 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            if (param1Parcel1.readInt() != 0)
              bool3 = true; 
            if (param1Parcel1.readInt() != 0) {
              configuration = (Configuration)Configuration.CREATOR.createFromParcel(param1Parcel1);
            } else {
              configuration = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              compatibilityInfo = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              compatibilityInfo = null;
            } 
            hashMap = param1Parcel1.readHashMap(getClass().getClassLoader());
            if (param1Parcel1.readInt() != 0) {
              bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              bundle2 = null;
            } 
            str6 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              autofillOptions = (AutofillOptions)AutofillOptions.CREATOR.createFromParcel(param1Parcel1);
            } else {
              autofillOptions = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              contentCaptureOptions = (ContentCaptureOptions)ContentCaptureOptions.CREATOR.createFromParcel(param1Parcel1);
            } else {
              contentCaptureOptions = null;
            } 
            bindApplication(str4, (ApplicationInfo)iBinder, (ProviderInfoList)iBinder4, (ComponentName)iIntentReceiver, (ProfilerInfo)str2, bundle1, iInstrumentationWatcher, iUiAutomationConnection, param1Int1, bool4, bool1, bool2, bool3, configuration, compatibilityInfo, hashMap, bundle2, str6, autofillOptions, contentCaptureOptions, param1Parcel1.createLongArray());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            scheduleStopService(param1Parcel1.readStrongBinder());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.IApplicationThread");
            iBinder2 = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0) {
              ServiceInfo serviceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(param1Parcel1);
            } else {
              iBinder4 = null;
            } 
            scheduleCreateService(iBinder2, (ServiceInfo)iBinder, (CompatibilityInfo)iBinder4, param1Parcel1.readInt());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.IApplicationThread");
        if (param1Parcel1.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
        } else {
          iBinder = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(param1Parcel1);
        } else {
          iBinder4 = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(param1Parcel1);
        } else {
          iBinder2 = null;
        } 
        param1Int1 = param1Parcel1.readInt();
        String str5 = param1Parcel1.readString();
        if (param1Parcel1.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          str2 = null;
        } 
        if (param1Parcel1.readInt() != 0) {
          bool4 = true;
        } else {
          bool4 = false;
        } 
        scheduleReceiver((Intent)iBinder, (ActivityInfo)iBinder4, (CompatibilityInfo)iBinder2, param1Int1, str5, (Bundle)str2, bool4, param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      iBinder.writeString("android.app.IApplicationThread");
      return true;
    }
    
    private static class Proxy implements IApplicationThread {
      public static IApplicationThread sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void attachAgent(String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeString(param2String);
          if (!this.mRemote.transact(48, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().attachAgent(param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void attachStartupAgents(String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeString(param2String);
          if (!this.mRemote.transact(49, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().attachStartupAgents(param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void bindApplication(String param2String1, ApplicationInfo param2ApplicationInfo, ProviderInfoList param2ProviderInfoList, ComponentName param2ComponentName, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle1, IInstrumentationWatcher param2IInstrumentationWatcher, IUiAutomationConnection param2IUiAutomationConnection, int param2Int, boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3, boolean param2Boolean4, Configuration param2Configuration, CompatibilityInfo param2CompatibilityInfo, Map param2Map, Bundle param2Bundle2, String param2String2, AutofillOptions param2AutofillOptions, ContentCaptureOptions param2ContentCaptureOptions, long[] param2ArrayOflong) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          boolean bool;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeString(param2String1);
          if (param2ApplicationInfo != null) {
            try {
              parcel.writeInt(1);
              param2ApplicationInfo.writeToParcel(parcel, 0);
            } finally {}
          } else {
            parcel.writeInt(0);
          } 
          if (param2ProviderInfoList != null) {
            parcel.writeInt(1);
            param2ProviderInfoList.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2ComponentName != null) {
            parcel.writeInt(1);
            param2ComponentName.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2ProfilerInfo != null) {
            parcel.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Bundle1 != null) {
            parcel.writeInt(1);
            param2Bundle1.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2IInstrumentationWatcher != null) {
            iBinder = param2IInstrumentationWatcher.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2IUiAutomationConnection != null) {
            iBinder = param2IUiAutomationConnection.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          parcel.writeInt(param2Int);
          if (param2Boolean1) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Boolean2) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Boolean3) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Boolean4) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Configuration != null) {
            parcel.writeInt(1);
            param2Configuration.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2CompatibilityInfo != null) {
            parcel.writeInt(1);
            param2CompatibilityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeMap(param2Map);
          if (param2Bundle2 != null) {
            parcel.writeInt(1);
            param2Bundle2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeString(param2String2);
          if (param2AutofillOptions != null) {
            parcel.writeInt(1);
            param2AutofillOptions.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2ContentCaptureOptions != null) {
            parcel.writeInt(1);
            param2ContentCaptureOptions.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeLongArray(param2ArrayOflong);
          if (!this.mRemote.transact(4, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread iApplicationThread = IApplicationThread.Stub.getDefaultImpl();
            try {
              iApplicationThread.bindApplication(param2String1, param2ApplicationInfo, param2ProviderInfoList, param2ComponentName, param2ProfilerInfo, param2Bundle1, param2IInstrumentationWatcher, param2IUiAutomationConnection, param2Int, param2Boolean1, param2Boolean2, param2Boolean3, param2Boolean4, param2Configuration, param2CompatibilityInfo, param2Map, param2Bundle2, param2String2, param2AutofillOptions, param2ContentCaptureOptions, param2ArrayOflong);
              parcel.recycle();
              return;
            } finally {}
          } else {
            parcel.recycle();
            return;
          } 
        } finally {}
        parcel.recycle();
        throw param2String1;
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
      
      public void dispatchPackageBroadcast(int param2Int, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeInt(param2Int);
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(21, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dispatchPackageBroadcast(param2Int, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void dumpActivity(ParcelFileDescriptor param2ParcelFileDescriptor, IBinder param2IBinder, String param2String, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStrongBinder(param2IBinder);
          parcel.writeString(param2String);
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(24, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpActivity(param2ParcelFileDescriptor, param2IBinder, param2String, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void dumpCacheInfo(ParcelFileDescriptor param2ParcelFileDescriptor, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(33, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpCacheInfo(param2ParcelFileDescriptor, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void dumpDbInfo(ParcelFileDescriptor param2ParcelFileDescriptor, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(35, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpDbInfo(param2ParcelFileDescriptor, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void dumpGfxInfo(ParcelFileDescriptor param2ParcelFileDescriptor, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(32, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpGfxInfo(param2ParcelFileDescriptor, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void dumpHeap(boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3, String param2String, ParcelFileDescriptor param2ParcelFileDescriptor, RemoteCallback param2RemoteCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2Boolean1) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Boolean2) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2Boolean3) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          try {
            parcel.writeString(param2String);
            if (param2ParcelFileDescriptor != null) {
              parcel.writeInt(1);
              param2ParcelFileDescriptor.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (param2RemoteCallback != null) {
              parcel.writeInt(1);
              param2RemoteCallback.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            try {
              if (!this.mRemote.transact(23, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
                IApplicationThread.Stub.getDefaultImpl().dumpHeap(param2Boolean1, param2Boolean2, param2Boolean3, param2String, param2ParcelFileDescriptor, param2RemoteCallback);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2String;
      }
      
      public void dumpMemInfo(ParcelFileDescriptor param2ParcelFileDescriptor, Debug.MemoryInfo param2MemoryInfo, boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3, boolean param2Boolean4, boolean param2Boolean5, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          boolean bool1 = false;
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2MemoryInfo != null) {
            parcel.writeInt(1);
            param2MemoryInfo.writeToParcel(parcel, 0);
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
          if (param2Boolean3) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel.writeInt(bool2);
          if (param2Boolean4) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel.writeInt(bool2);
          boolean bool2 = bool1;
          if (param2Boolean5)
            bool2 = true; 
          parcel.writeInt(bool2);
          try {
            parcel.writeStringArray(param2ArrayOfString);
            try {
              if (!this.mRemote.transact(30, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
                IApplicationThread.Stub.getDefaultImpl().dumpMemInfo(param2ParcelFileDescriptor, param2MemoryInfo, param2Boolean1, param2Boolean2, param2Boolean3, param2Boolean4, param2Boolean5, param2ArrayOfString);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2ParcelFileDescriptor;
      }
      
      public void dumpMemInfoProto(ParcelFileDescriptor param2ParcelFileDescriptor, Debug.MemoryInfo param2MemoryInfo, boolean param2Boolean1, boolean param2Boolean2, boolean param2Boolean3, boolean param2Boolean4, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          boolean bool1 = false;
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2MemoryInfo != null) {
            parcel.writeInt(1);
            param2MemoryInfo.writeToParcel(parcel, 0);
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
          if (param2Boolean3) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel.writeInt(bool2);
          boolean bool2 = bool1;
          if (param2Boolean4)
            bool2 = true; 
          parcel.writeInt(bool2);
          try {
            parcel.writeStringArray(param2ArrayOfString);
            try {
              if (!this.mRemote.transact(31, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
                IApplicationThread.Stub.getDefaultImpl().dumpMemInfoProto(param2ParcelFileDescriptor, param2MemoryInfo, param2Boolean1, param2Boolean2, param2Boolean3, param2Boolean4, param2ArrayOfString);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2ParcelFileDescriptor;
      }
      
      public void dumpProvider(ParcelFileDescriptor param2ParcelFileDescriptor, IBinder param2IBinder, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStrongBinder(param2IBinder);
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(34, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpProvider(param2ParcelFileDescriptor, param2IBinder, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void dumpService(ParcelFileDescriptor param2ParcelFileDescriptor, IBinder param2IBinder, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStrongBinder(param2IBinder);
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(12, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().dumpService(param2ParcelFileDescriptor, param2IBinder, param2ArrayOfString);
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
      
      public void notifyCleartextNetwork(byte[] param2ArrayOfbyte) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeByteArray(param2ArrayOfbyte);
          if (!this.mRemote.transact(43, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().notifyCleartextNetwork(param2ArrayOfbyte);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void performDirectAction(IBinder param2IBinder, String param2String, Bundle param2Bundle, RemoteCallback param2RemoteCallback1, RemoteCallback param2RemoteCallback2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2RemoteCallback1 != null) {
            parcel.writeInt(1);
            param2RemoteCallback1.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2RemoteCallback2 != null) {
            parcel.writeInt(1);
            param2RemoteCallback2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(54, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().performDirectAction(param2IBinder, param2String, param2Bundle, param2RemoteCallback1, param2RemoteCallback2);
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
      
      public void profilerControl(boolean param2Boolean, ProfilerInfo param2ProfilerInfo, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2ProfilerInfo != null) {
            parcel.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(15, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().profilerControl(param2Boolean, param2ProfilerInfo, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void requestAssistContextExtras(IBinder param2IBinder1, IBinder param2IBinder2, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder1);
          parcel.writeStrongBinder(param2IBinder2);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(37, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().requestAssistContextExtras(param2IBinder1, param2IBinder2, param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void requestDirectActions(IBinder param2IBinder, IVoiceInteractor param2IVoiceInteractor, RemoteCallback param2RemoteCallback1, RemoteCallback param2RemoteCallback2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2IVoiceInteractor != null) {
            iBinder = param2IVoiceInteractor.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (param2RemoteCallback1 != null) {
            parcel.writeInt(1);
            param2RemoteCallback1.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2RemoteCallback2 != null) {
            parcel.writeInt(1);
            param2RemoteCallback2.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(53, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().requestDirectActions(param2IBinder, param2IVoiceInteractor, param2RemoteCallback1, param2RemoteCallback2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void runIsolatedEntryPoint(String param2String, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeString(param2String);
          parcel.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(5, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().runIsolatedEntryPoint(param2String, param2ArrayOfString);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleApplicationInfoChanged(ApplicationInfo param2ApplicationInfo) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ApplicationInfo != null) {
            parcel.writeInt(1);
            param2ApplicationInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(50, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleApplicationInfoChanged(param2ApplicationInfo);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleBindService(IBinder param2IBinder, Intent param2Intent, boolean param2Boolean, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          boolean bool = false;
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(10, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleBindService(param2IBinder, param2Intent, param2Boolean, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleCrash(String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeString(param2String);
          if (!this.mRemote.transact(22, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleCrash(param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleCreateBackupAgent(ApplicationInfo param2ApplicationInfo, CompatibilityInfo param2CompatibilityInfo, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ApplicationInfo != null) {
            parcel.writeInt(1);
            param2ApplicationInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2CompatibilityInfo != null) {
            parcel.writeInt(1);
            param2CompatibilityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(17, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleCreateBackupAgent(param2ApplicationInfo, param2CompatibilityInfo, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleCreateService(IBinder param2IBinder, ServiceInfo param2ServiceInfo, CompatibilityInfo param2CompatibilityInfo, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2ServiceInfo != null) {
            parcel.writeInt(1);
            param2ServiceInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2CompatibilityInfo != null) {
            parcel.writeInt(1);
            param2CompatibilityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(2, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleCreateService(param2IBinder, param2ServiceInfo, param2CompatibilityInfo, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleDestroyBackupAgent(ApplicationInfo param2ApplicationInfo, CompatibilityInfo param2CompatibilityInfo, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ApplicationInfo != null) {
            parcel.writeInt(1);
            param2ApplicationInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2CompatibilityInfo != null) {
            parcel.writeInt(1);
            param2CompatibilityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(18, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleDestroyBackupAgent(param2ApplicationInfo, param2CompatibilityInfo, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleEnterAnimationComplete(IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(42, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleEnterAnimationComplete(param2IBinder);
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
      
      public void scheduleInstallProvider(ProviderInfo param2ProviderInfo) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ProviderInfo != null) {
            parcel.writeInt(1);
            param2ProviderInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(40, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleInstallProvider(param2ProviderInfo);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleLocalVoiceInteractionStarted(IBinder param2IBinder, IVoiceInteractor param2IVoiceInteractor) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2IVoiceInteractor != null) {
            iBinder = param2IVoiceInteractor.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(46, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleLocalVoiceInteractionStarted(param2IBinder, param2IVoiceInteractor);
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
      
      public void scheduleOnNewActivityOptions(IBinder param2IBinder, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(19, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleOnNewActivityOptions(param2IBinder, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleReceiver(Intent param2Intent, ActivityInfo param2ActivityInfo, CompatibilityInfo param2CompatibilityInfo, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          boolean bool = false;
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2ActivityInfo != null) {
            parcel.writeInt(1);
            param2ActivityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2CompatibilityInfo != null) {
            parcel.writeInt(1);
            param2CompatibilityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int1);
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(1, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleReceiver(param2Intent, param2ActivityInfo, param2CompatibilityInfo, param2Int1, param2String, param2Bundle, param2Boolean, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleRegisteredReceiver(IIntentReceiver param2IIntentReceiver, Intent param2Intent, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean1, boolean param2Boolean2, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2IIntentReceiver != null) {
            iBinder = param2IIntentReceiver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          boolean bool = false;
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            parcel.writeInt(param2Int1);
            try {
              parcel.writeString(param2String);
              if (param2Bundle != null) {
                parcel.writeInt(1);
                param2Bundle.writeToParcel(parcel, 0);
              } else {
                parcel.writeInt(0);
              } 
              if (param2Boolean1) {
                bool1 = true;
              } else {
                bool1 = false;
              } 
              parcel.writeInt(bool1);
              boolean bool1 = bool;
              if (param2Boolean2)
                bool1 = true; 
              parcel.writeInt(bool1);
              parcel.writeInt(param2Int2);
              parcel.writeInt(param2Int3);
              if (!this.mRemote.transact(13, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
                IApplicationThread.Stub.getDefaultImpl().scheduleRegisteredReceiver(param2IIntentReceiver, param2Intent, param2Int1, param2String, param2Bundle, param2Boolean1, param2Boolean2, param2Int2, param2Int3);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2IIntentReceiver;
      }
      
      public void scheduleServiceArgs(IBinder param2IBinder, ParceledListSlice param2ParceledListSlice) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2ParceledListSlice != null) {
            parcel.writeInt(1);
            param2ParceledListSlice.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(7, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleServiceArgs(param2IBinder, param2ParceledListSlice);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleStopService(IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(3, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleStopService(param2IBinder);
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
      
      public void scheduleTransaction(ClientTransaction param2ClientTransaction) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ClientTransaction != null) {
            parcel.writeInt(1);
            param2ClientTransaction.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(52, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleTransaction(param2ClientTransaction);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleTranslucentConversionComplete(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(38, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleTranslucentConversionComplete(param2IBinder, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleTrimMemory(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(29, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleTrimMemory(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void scheduleUnbindService(IBinder param2IBinder, Intent param2Intent) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().scheduleUnbindService(param2IBinder, param2Intent);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setCoreSettings(Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(27, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().setCoreSettings(param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setNetworkBlockSeq(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(51, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().setNetworkBlockSeq(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setProcessState(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(39, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().setProcessState(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setSchedulingGroup(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(16, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().setSchedulingGroup(param2Int);
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
      
      public void stopBinderTrackingAndDump(ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          if (param2ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(45, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().stopBinderTrackingAndDump(param2ParcelFileDescriptor);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void unstableProviderDied(IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(36, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().unstableProviderDied(param2IBinder);
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
      
      public void updatePackageCompatibilityInfo(String param2String, CompatibilityInfo param2CompatibilityInfo) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeString(param2String);
          if (param2CompatibilityInfo != null) {
            parcel.writeInt(1);
            param2CompatibilityInfo.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(28, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().updatePackageCompatibilityInfo(param2String, param2CompatibilityInfo);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void updateTimePrefs(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IApplicationThread");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(41, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
            IApplicationThread.Stub.getDefaultImpl().updateTimePrefs(param2Int);
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
  }
  
  private static class Proxy implements IApplicationThread {
    public static IApplicationThread sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void attachAgent(String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeString(param1String);
        if (!this.mRemote.transact(48, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().attachAgent(param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void attachStartupAgents(String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeString(param1String);
        if (!this.mRemote.transact(49, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().attachStartupAgents(param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void bindApplication(String param1String1, ApplicationInfo param1ApplicationInfo, ProviderInfoList param1ProviderInfoList, ComponentName param1ComponentName, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle1, IInstrumentationWatcher param1IInstrumentationWatcher, IUiAutomationConnection param1IUiAutomationConnection, int param1Int, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, Configuration param1Configuration, CompatibilityInfo param1CompatibilityInfo, Map param1Map, Bundle param1Bundle2, String param1String2, AutofillOptions param1AutofillOptions, ContentCaptureOptions param1ContentCaptureOptions, long[] param1ArrayOflong) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeString(param1String1);
        if (param1ApplicationInfo != null) {
          try {
            parcel.writeInt(1);
            param1ApplicationInfo.writeToParcel(parcel, 0);
          } finally {}
        } else {
          parcel.writeInt(0);
        } 
        if (param1ProviderInfoList != null) {
          parcel.writeInt(1);
          param1ProviderInfoList.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ComponentName != null) {
          parcel.writeInt(1);
          param1ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ProfilerInfo != null) {
          parcel.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Bundle1 != null) {
          parcel.writeInt(1);
          param1Bundle1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1IInstrumentationWatcher != null) {
          iBinder = param1IInstrumentationWatcher.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1IUiAutomationConnection != null) {
          iBinder = param1IUiAutomationConnection.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        parcel.writeInt(param1Int);
        if (param1Boolean1) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Boolean2) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Boolean3) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Boolean4) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Configuration != null) {
          parcel.writeInt(1);
          param1Configuration.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1CompatibilityInfo != null) {
          parcel.writeInt(1);
          param1CompatibilityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeMap(param1Map);
        if (param1Bundle2 != null) {
          parcel.writeInt(1);
          param1Bundle2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeString(param1String2);
        if (param1AutofillOptions != null) {
          parcel.writeInt(1);
          param1AutofillOptions.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ContentCaptureOptions != null) {
          parcel.writeInt(1);
          param1ContentCaptureOptions.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeLongArray(param1ArrayOflong);
        if (!this.mRemote.transact(4, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread iApplicationThread = IApplicationThread.Stub.getDefaultImpl();
          try {
            iApplicationThread.bindApplication(param1String1, param1ApplicationInfo, param1ProviderInfoList, param1ComponentName, param1ProfilerInfo, param1Bundle1, param1IInstrumentationWatcher, param1IUiAutomationConnection, param1Int, param1Boolean1, param1Boolean2, param1Boolean3, param1Boolean4, param1Configuration, param1CompatibilityInfo, param1Map, param1Bundle2, param1String2, param1AutofillOptions, param1ContentCaptureOptions, param1ArrayOflong);
            parcel.recycle();
            return;
          } finally {}
        } else {
          parcel.recycle();
          return;
        } 
      } finally {}
      parcel.recycle();
      throw param1String1;
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
    
    public void dispatchPackageBroadcast(int param1Int, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeInt(param1Int);
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(21, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dispatchPackageBroadcast(param1Int, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void dumpActivity(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String param1String, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStrongBinder(param1IBinder);
        parcel.writeString(param1String);
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(24, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dumpActivity(param1ParcelFileDescriptor, param1IBinder, param1String, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void dumpCacheInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(33, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dumpCacheInfo(param1ParcelFileDescriptor, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void dumpDbInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(35, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dumpDbInfo(param1ParcelFileDescriptor, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void dumpGfxInfo(ParcelFileDescriptor param1ParcelFileDescriptor, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(32, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dumpGfxInfo(param1ParcelFileDescriptor, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void dumpHeap(boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, String param1String, ParcelFileDescriptor param1ParcelFileDescriptor, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1Boolean1) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Boolean2) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1Boolean3) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        try {
          parcel.writeString(param1String);
          if (param1ParcelFileDescriptor != null) {
            parcel.writeInt(1);
            param1ParcelFileDescriptor.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param1RemoteCallback != null) {
            parcel.writeInt(1);
            param1RemoteCallback.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            if (!this.mRemote.transact(23, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
              IApplicationThread.Stub.getDefaultImpl().dumpHeap(param1Boolean1, param1Boolean2, param1Boolean3, param1String, param1ParcelFileDescriptor, param1RemoteCallback);
              parcel.recycle();
              return;
            } 
            parcel.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1String;
    }
    
    public void dumpMemInfo(ParcelFileDescriptor param1ParcelFileDescriptor, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        boolean bool1 = false;
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1MemoryInfo != null) {
          parcel.writeInt(1);
          param1MemoryInfo.writeToParcel(parcel, 0);
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
        if (param1Boolean3) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        if (param1Boolean4) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        boolean bool2 = bool1;
        if (param1Boolean5)
          bool2 = true; 
        parcel.writeInt(bool2);
        try {
          parcel.writeStringArray(param1ArrayOfString);
          try {
            if (!this.mRemote.transact(30, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
              IApplicationThread.Stub.getDefaultImpl().dumpMemInfo(param1ParcelFileDescriptor, param1MemoryInfo, param1Boolean1, param1Boolean2, param1Boolean3, param1Boolean4, param1Boolean5, param1ArrayOfString);
              parcel.recycle();
              return;
            } 
            parcel.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1ParcelFileDescriptor;
    }
    
    public void dumpMemInfoProto(ParcelFileDescriptor param1ParcelFileDescriptor, Debug.MemoryInfo param1MemoryInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        boolean bool1 = false;
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1MemoryInfo != null) {
          parcel.writeInt(1);
          param1MemoryInfo.writeToParcel(parcel, 0);
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
        if (param1Boolean3) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        boolean bool2 = bool1;
        if (param1Boolean4)
          bool2 = true; 
        parcel.writeInt(bool2);
        try {
          parcel.writeStringArray(param1ArrayOfString);
          try {
            if (!this.mRemote.transact(31, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
              IApplicationThread.Stub.getDefaultImpl().dumpMemInfoProto(param1ParcelFileDescriptor, param1MemoryInfo, param1Boolean1, param1Boolean2, param1Boolean3, param1Boolean4, param1ArrayOfString);
              parcel.recycle();
              return;
            } 
            parcel.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1ParcelFileDescriptor;
    }
    
    public void dumpProvider(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStrongBinder(param1IBinder);
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(34, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dumpProvider(param1ParcelFileDescriptor, param1IBinder, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void dumpService(ParcelFileDescriptor param1ParcelFileDescriptor, IBinder param1IBinder, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStrongBinder(param1IBinder);
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(12, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().dumpService(param1ParcelFileDescriptor, param1IBinder, param1ArrayOfString);
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
    
    public void notifyCleartextNetwork(byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(43, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().notifyCleartextNetwork(param1ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void performDirectAction(IBinder param1IBinder, String param1String, Bundle param1Bundle, RemoteCallback param1RemoteCallback1, RemoteCallback param1RemoteCallback2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1RemoteCallback1 != null) {
          parcel.writeInt(1);
          param1RemoteCallback1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1RemoteCallback2 != null) {
          parcel.writeInt(1);
          param1RemoteCallback2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(54, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().performDirectAction(param1IBinder, param1String, param1Bundle, param1RemoteCallback1, param1RemoteCallback2);
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
    
    public void profilerControl(boolean param1Boolean, ProfilerInfo param1ProfilerInfo, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1ProfilerInfo != null) {
          parcel.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(15, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().profilerControl(param1Boolean, param1ProfilerInfo, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void requestAssistContextExtras(IBinder param1IBinder1, IBinder param1IBinder2, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder1);
        parcel.writeStrongBinder(param1IBinder2);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(37, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().requestAssistContextExtras(param1IBinder1, param1IBinder2, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void requestDirectActions(IBinder param1IBinder, IVoiceInteractor param1IVoiceInteractor, RemoteCallback param1RemoteCallback1, RemoteCallback param1RemoteCallback2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1IVoiceInteractor != null) {
          iBinder = param1IVoiceInteractor.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (param1RemoteCallback1 != null) {
          parcel.writeInt(1);
          param1RemoteCallback1.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1RemoteCallback2 != null) {
          parcel.writeInt(1);
          param1RemoteCallback2.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(53, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().requestDirectActions(param1IBinder, param1IVoiceInteractor, param1RemoteCallback1, param1RemoteCallback2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void runIsolatedEntryPoint(String param1String, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeString(param1String);
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(5, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().runIsolatedEntryPoint(param1String, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleApplicationInfoChanged(ApplicationInfo param1ApplicationInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ApplicationInfo != null) {
          parcel.writeInt(1);
          param1ApplicationInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(50, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleApplicationInfoChanged(param1ApplicationInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleBindService(IBinder param1IBinder, Intent param1Intent, boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        boolean bool = false;
        if (param1Intent != null) {
          parcel.writeInt(1);
          param1Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleBindService(param1IBinder, param1Intent, param1Boolean, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleCrash(String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeString(param1String);
        if (!this.mRemote.transact(22, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleCrash(param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleCreateBackupAgent(ApplicationInfo param1ApplicationInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ApplicationInfo != null) {
          parcel.writeInt(1);
          param1ApplicationInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1CompatibilityInfo != null) {
          parcel.writeInt(1);
          param1CompatibilityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(17, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleCreateBackupAgent(param1ApplicationInfo, param1CompatibilityInfo, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleCreateService(IBinder param1IBinder, ServiceInfo param1ServiceInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1ServiceInfo != null) {
          parcel.writeInt(1);
          param1ServiceInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1CompatibilityInfo != null) {
          parcel.writeInt(1);
          param1CompatibilityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleCreateService(param1IBinder, param1ServiceInfo, param1CompatibilityInfo, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleDestroyBackupAgent(ApplicationInfo param1ApplicationInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ApplicationInfo != null) {
          parcel.writeInt(1);
          param1ApplicationInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1CompatibilityInfo != null) {
          parcel.writeInt(1);
          param1CompatibilityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(18, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleDestroyBackupAgent(param1ApplicationInfo, param1CompatibilityInfo, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleEnterAnimationComplete(IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(42, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleEnterAnimationComplete(param1IBinder);
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
    
    public void scheduleInstallProvider(ProviderInfo param1ProviderInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ProviderInfo != null) {
          parcel.writeInt(1);
          param1ProviderInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(40, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleInstallProvider(param1ProviderInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleLocalVoiceInteractionStarted(IBinder param1IBinder, IVoiceInteractor param1IVoiceInteractor) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1IVoiceInteractor != null) {
          iBinder = param1IVoiceInteractor.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(46, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleLocalVoiceInteractionStarted(param1IBinder, param1IVoiceInteractor);
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
    
    public void scheduleOnNewActivityOptions(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(19, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleOnNewActivityOptions(param1IBinder, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleReceiver(Intent param1Intent, ActivityInfo param1ActivityInfo, CompatibilityInfo param1CompatibilityInfo, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        boolean bool = false;
        if (param1Intent != null) {
          parcel.writeInt(1);
          param1Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ActivityInfo != null) {
          parcel.writeInt(1);
          param1ActivityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1CompatibilityInfo != null) {
          parcel.writeInt(1);
          param1CompatibilityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int1);
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(1, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleReceiver(param1Intent, param1ActivityInfo, param1CompatibilityInfo, param1Int1, param1String, param1Bundle, param1Boolean, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleRegisteredReceiver(IIntentReceiver param1IIntentReceiver, Intent param1Intent, int param1Int1, String param1String, Bundle param1Bundle, boolean param1Boolean1, boolean param1Boolean2, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1IIntentReceiver != null) {
          iBinder = param1IIntentReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        boolean bool = false;
        if (param1Intent != null) {
          parcel.writeInt(1);
          param1Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeInt(param1Int1);
          try {
            parcel.writeString(param1String);
            if (param1Bundle != null) {
              parcel.writeInt(1);
              param1Bundle.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (param1Boolean1) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            parcel.writeInt(bool1);
            boolean bool1 = bool;
            if (param1Boolean2)
              bool1 = true; 
            parcel.writeInt(bool1);
            parcel.writeInt(param1Int2);
            parcel.writeInt(param1Int3);
            if (!this.mRemote.transact(13, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
              IApplicationThread.Stub.getDefaultImpl().scheduleRegisteredReceiver(param1IIntentReceiver, param1Intent, param1Int1, param1String, param1Bundle, param1Boolean1, param1Boolean2, param1Int2, param1Int3);
              parcel.recycle();
              return;
            } 
            parcel.recycle();
            return;
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1IIntentReceiver;
    }
    
    public void scheduleServiceArgs(IBinder param1IBinder, ParceledListSlice param1ParceledListSlice) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1ParceledListSlice != null) {
          parcel.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(7, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleServiceArgs(param1IBinder, param1ParceledListSlice);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleStopService(IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(3, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleStopService(param1IBinder);
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
    
    public void scheduleTransaction(ClientTransaction param1ClientTransaction) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ClientTransaction != null) {
          parcel.writeInt(1);
          param1ClientTransaction.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(52, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleTransaction(param1ClientTransaction);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleTranslucentConversionComplete(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(38, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleTranslucentConversionComplete(param1IBinder, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleTrimMemory(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(29, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleTrimMemory(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void scheduleUnbindService(IBinder param1IBinder, Intent param1Intent) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (param1Intent != null) {
          parcel.writeInt(1);
          param1Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().scheduleUnbindService(param1IBinder, param1Intent);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setCoreSettings(Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().setCoreSettings(param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setNetworkBlockSeq(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(51, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().setNetworkBlockSeq(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setProcessState(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(39, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().setProcessState(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setSchedulingGroup(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(16, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().setSchedulingGroup(param1Int);
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
    
    public void stopBinderTrackingAndDump(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        if (param1ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(45, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().stopBinderTrackingAndDump(param1ParcelFileDescriptor);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void unstableProviderDied(IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(36, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().unstableProviderDied(param1IBinder);
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
    
    public void updatePackageCompatibilityInfo(String param1String, CompatibilityInfo param1CompatibilityInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeString(param1String);
        if (param1CompatibilityInfo != null) {
          parcel.writeInt(1);
          param1CompatibilityInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(28, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().updatePackageCompatibilityInfo(param1String, param1CompatibilityInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void updateTimePrefs(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IApplicationThread");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(41, parcel, null, 1) && IApplicationThread.Stub.getDefaultImpl() != null) {
          IApplicationThread.Stub.getDefaultImpl().updateTimePrefs(param1Int);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IApplicationThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */