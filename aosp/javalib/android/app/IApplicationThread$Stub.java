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

public abstract class Stub extends Binder implements IApplicationThread {
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
  
  public static IApplicationThread asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IApplicationThread");
    return (iInterface != null && iInterface instanceof IApplicationThread) ? (IApplicationThread)iInterface : new Proxy(paramIBinder);
  }
  
  public static IApplicationThread getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IApplicationThread paramIApplicationThread) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIApplicationThread != null) {
        Proxy.sDefaultImpl = paramIApplicationThread;
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
    IBinder iBinder;
    if (paramInt1 != 1598968902) {
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
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 54:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder3 = paramParcel1.readStrongBinder();
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            remoteCallback = null;
          } 
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          performDirectAction(iBinder3, str2, (Bundle)paramParcel2, remoteCallback, (RemoteCallback)paramParcel1);
          return true;
        case 53:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder5 = paramParcel1.readStrongBinder();
          iVoiceInteractor = IVoiceInteractor.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          requestDirectActions(iBinder5, iVoiceInteractor, (RemoteCallback)paramParcel2, (RemoteCallback)paramParcel1);
          return true;
        case 52:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ClientTransaction clientTransaction = (ClientTransaction)ClientTransaction.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          scheduleTransaction((ClientTransaction)paramParcel1);
          return true;
        case 51:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          setNetworkBlockSeq(paramParcel1.readLong());
          return true;
        case 50:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          scheduleApplicationInfoChanged((ApplicationInfo)paramParcel1);
          return true;
        case 49:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          attachStartupAgents(paramParcel1.readString());
          return true;
        case 48:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          attachAgent(paramParcel1.readString());
          return true;
        case 47:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          handleTrustStorageUpdate();
          return true;
        case 46:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleLocalVoiceInteractionStarted(paramParcel1.readStrongBinder(), IVoiceInteractor.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 45:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          stopBinderTrackingAndDump((ParcelFileDescriptor)paramParcel1);
          return true;
        case 44:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          startBinderTracking();
          return true;
        case 43:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          notifyCleartextNetwork(paramParcel1.createByteArray());
          return true;
        case 42:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleEnterAnimationComplete(paramParcel1.readStrongBinder());
          return true;
        case 41:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          updateTimePrefs(paramParcel1.readInt());
          return true;
        case 40:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ProviderInfo providerInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          scheduleInstallProvider((ProviderInfo)paramParcel1);
          return true;
        case 39:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          setProcessState(paramParcel1.readInt());
          return true;
        case 38:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder1 = paramParcel1.readStrongBinder();
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          scheduleTranslucentConversionComplete(iBinder1, bool4);
          return true;
        case 37:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          requestAssistContextExtras(paramParcel1.readStrongBinder(), paramParcel1.readStrongBinder(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 36:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          unstableProviderDied(paramParcel1.readStrongBinder());
          return true;
        case 35:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder1 = null;
          } 
          dumpDbInfo((ParcelFileDescriptor)iBinder1, paramParcel1.createStringArray());
          return true;
        case 34:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder1 = null;
          } 
          dumpProvider((ParcelFileDescriptor)iBinder1, paramParcel1.readStrongBinder(), paramParcel1.createStringArray());
          return true;
        case 33:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder1 = null;
          } 
          dumpCacheInfo((ParcelFileDescriptor)iBinder1, paramParcel1.createStringArray());
          return true;
        case 32:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder1 = null;
          } 
          dumpGfxInfo((ParcelFileDescriptor)iBinder1, paramParcel1.createStringArray());
          return true;
        case 31:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo)Debug.MemoryInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder5 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool3 = true;
          } else {
            bool3 = false;
          } 
          dumpMemInfoProto((ParcelFileDescriptor)iBinder1, (Debug.MemoryInfo)iBinder5, bool4, bool1, bool2, bool3, paramParcel1.createStringArray());
          return true;
        case 30:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            Debug.MemoryInfo memoryInfo = (Debug.MemoryInfo)Debug.MemoryInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder5 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool3 = true;
          } else {
            bool3 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool5 = true;
          } else {
            bool5 = false;
          } 
          dumpMemInfo((ParcelFileDescriptor)iBinder1, (Debug.MemoryInfo)iBinder5, bool4, bool1, bool2, bool3, bool5, paramParcel1.createStringArray());
          return true;
        case 29:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleTrimMemory(paramParcel1.readInt());
          return true;
        case 28:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          updatePackageCompatibilityInfo(str1, (CompatibilityInfo)paramParcel1);
          return true;
        case 27:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setCoreSettings((Bundle)paramParcel1);
          return true;
        case 26:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          updateHttpProxy();
          return true;
        case 25:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          clearDnsCache();
          return true;
        case 24:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          dumpActivity((ParcelFileDescriptor)str1, paramParcel1.readStrongBinder(), paramParcel1.readString(), paramParcel1.createStringArray());
          return true;
        case 23:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          str3 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            RemoteCallback remoteCallback1 = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          dumpHeap(bool4, bool1, bool2, str3, (ParcelFileDescriptor)str1, (RemoteCallback)paramParcel1);
          return true;
        case 22:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleCrash(paramParcel1.readString());
          return true;
        case 21:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          dispatchPackageBroadcast(paramParcel1.readInt(), paramParcel1.createStringArray());
          return true;
        case 20:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleSuicide();
          return true;
        case 19:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder = paramParcel1.readStrongBinder();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          scheduleOnNewActivityOptions(iBinder, (Bundle)paramParcel1);
          return true;
        case 18:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          if (paramParcel1.readInt() != 0) {
            CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            str3 = null;
          } 
          scheduleDestroyBackupAgent((ApplicationInfo)iBinder, (CompatibilityInfo)str3, paramParcel1.readInt());
          return true;
        case 17:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          if (paramParcel1.readInt() != 0) {
            CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            str3 = null;
          } 
          scheduleCreateBackupAgent((ApplicationInfo)iBinder, (CompatibilityInfo)str3, paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 16:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          setSchedulingGroup(paramParcel1.readInt());
          return true;
        case 15:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          bool4 = bool1;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          if (paramParcel1.readInt() != 0) {
            ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          profilerControl(bool4, (ProfilerInfo)iBinder, paramParcel1.readInt());
          return true;
        case 14:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleLowMemory();
          return true;
        case 13:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iIntentReceiver = IIntentReceiver.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          paramInt1 = paramParcel1.readInt();
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            str3 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          scheduleRegisteredReceiver(iIntentReceiver, (Intent)iBinder, paramInt1, str2, (Bundle)str3, bool4, bool1, paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 12:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          if (paramParcel1.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          dumpService((ParcelFileDescriptor)iBinder, paramParcel1.readStrongBinder(), paramParcel1.createStringArray());
          return true;
        case 11:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder = paramParcel1.readStrongBinder();
          if (paramParcel1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          scheduleUnbindService(iBinder, (Intent)paramParcel1);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder4 = paramParcel1.readStrongBinder();
          if (paramParcel1.readInt() != 0) {
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          bool4 = bool2;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          scheduleBindService(iBinder4, (Intent)iBinder, bool4, paramParcel1.readInt());
          return true;
        case 9:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          processInBackground();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          updateTimeZone();
          return true;
        case 7:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder = paramParcel1.readStrongBinder();
          if (paramParcel1.readInt() != 0) {
            ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          scheduleServiceArgs(iBinder, (ParceledListSlice)paramParcel1);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleExit();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          runIsolatedEntryPoint(paramParcel1.readString(), paramParcel1.createStringArray());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          str4 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            ApplicationInfo applicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          if (paramParcel1.readInt() != 0) {
            ProviderInfoList providerInfoList = (ProviderInfoList)ProviderInfoList.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder4 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
          } else {
            iIntentReceiver = null;
          } 
          if (paramParcel1.readInt() != 0) {
            ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            str2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            bundle1 = null;
          } 
          iInstrumentationWatcher = IInstrumentationWatcher.Stub.asInterface(paramParcel1.readStrongBinder());
          iUiAutomationConnection = IUiAutomationConnection.Stub.asInterface(paramParcel1.readStrongBinder());
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (paramParcel1.readInt() != 0)
            bool3 = true; 
          if (paramParcel1.readInt() != 0) {
            configuration = (Configuration)Configuration.CREATOR.createFromParcel(paramParcel1);
          } else {
            configuration = null;
          } 
          if (paramParcel1.readInt() != 0) {
            compatibilityInfo = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            compatibilityInfo = null;
          } 
          hashMap = paramParcel1.readHashMap(getClass().getClassLoader());
          if (paramParcel1.readInt() != 0) {
            bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            bundle2 = null;
          } 
          str6 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            autofillOptions = (AutofillOptions)AutofillOptions.CREATOR.createFromParcel(paramParcel1);
          } else {
            autofillOptions = null;
          } 
          if (paramParcel1.readInt() != 0) {
            contentCaptureOptions = (ContentCaptureOptions)ContentCaptureOptions.CREATOR.createFromParcel(paramParcel1);
          } else {
            contentCaptureOptions = null;
          } 
          bindApplication(str4, (ApplicationInfo)iBinder, (ProviderInfoList)iBinder4, (ComponentName)iIntentReceiver, (ProfilerInfo)str2, bundle1, iInstrumentationWatcher, iUiAutomationConnection, paramInt1, bool4, bool1, bool2, bool3, configuration, compatibilityInfo, hashMap, bundle2, str6, autofillOptions, contentCaptureOptions, paramParcel1.createLongArray());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          scheduleStopService(paramParcel1.readStrongBinder());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.IApplicationThread");
          iBinder2 = paramParcel1.readStrongBinder();
          if (paramParcel1.readInt() != 0) {
            ServiceInfo serviceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder = null;
          } 
          if (paramParcel1.readInt() != 0) {
            CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            iBinder4 = null;
          } 
          scheduleCreateService(iBinder2, (ServiceInfo)iBinder, (CompatibilityInfo)iBinder4, paramParcel1.readInt());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.IApplicationThread");
      if (paramParcel1.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
      } else {
        iBinder = null;
      } 
      if (paramParcel1.readInt() != 0) {
        ActivityInfo activityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(paramParcel1);
      } else {
        iBinder4 = null;
      } 
      if (paramParcel1.readInt() != 0) {
        CompatibilityInfo compatibilityInfo1 = (CompatibilityInfo)CompatibilityInfo.CREATOR.createFromParcel(paramParcel1);
      } else {
        iBinder2 = null;
      } 
      paramInt1 = paramParcel1.readInt();
      String str5 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      } else {
        str2 = null;
      } 
      if (paramParcel1.readInt() != 0) {
        bool4 = true;
      } else {
        bool4 = false;
      } 
      scheduleReceiver((Intent)iBinder, (ActivityInfo)iBinder4, (CompatibilityInfo)iBinder2, paramInt1, str5, (Bundle)str2, bool4, paramParcel1.readInt(), paramParcel1.readInt());
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IApplicationThread$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */