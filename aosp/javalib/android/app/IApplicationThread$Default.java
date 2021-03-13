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
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import com.android.internal.app.IVoiceInteractor;
import java.util.Map;

public class Default implements IApplicationThread {
  public IBinder asBinder() {
    return null;
  }
  
  public void attachAgent(String paramString) throws RemoteException {}
  
  public void attachStartupAgents(String paramString) throws RemoteException {}
  
  public void bindApplication(String paramString1, ApplicationInfo paramApplicationInfo, ProviderInfoList paramProviderInfoList, ComponentName paramComponentName, ProfilerInfo paramProfilerInfo, Bundle paramBundle1, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, Map paramMap, Bundle paramBundle2, String paramString2, AutofillOptions paramAutofillOptions, ContentCaptureOptions paramContentCaptureOptions, long[] paramArrayOflong) throws RemoteException {}
  
  public void clearDnsCache() throws RemoteException {}
  
  public void dispatchPackageBroadcast(int paramInt, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpActivity(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String paramString, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpCacheInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpDbInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpGfxInfo(ParcelFileDescriptor paramParcelFileDescriptor, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpHeap(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString, ParcelFileDescriptor paramParcelFileDescriptor, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void dumpMemInfo(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpMemInfoProto(ParcelFileDescriptor paramParcelFileDescriptor, Debug.MemoryInfo paramMemoryInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpProvider(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) throws RemoteException {}
  
  public void dumpService(ParcelFileDescriptor paramParcelFileDescriptor, IBinder paramIBinder, String[] paramArrayOfString) throws RemoteException {}
  
  public void handleTrustStorageUpdate() throws RemoteException {}
  
  public void notifyCleartextNetwork(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void performDirectAction(IBinder paramIBinder, String paramString, Bundle paramBundle, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) throws RemoteException {}
  
  public void processInBackground() throws RemoteException {}
  
  public void profilerControl(boolean paramBoolean, ProfilerInfo paramProfilerInfo, int paramInt) throws RemoteException {}
  
  public void requestAssistContextExtras(IBinder paramIBinder1, IBinder paramIBinder2, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void requestDirectActions(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor, RemoteCallback paramRemoteCallback1, RemoteCallback paramRemoteCallback2) throws RemoteException {}
  
  public void runIsolatedEntryPoint(String paramString, String[] paramArrayOfString) throws RemoteException {}
  
  public void scheduleApplicationInfoChanged(ApplicationInfo paramApplicationInfo) throws RemoteException {}
  
  public void scheduleBindService(IBinder paramIBinder, Intent paramIntent, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void scheduleCrash(String paramString) throws RemoteException {}
  
  public void scheduleCreateBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void scheduleCreateService(IBinder paramIBinder, ServiceInfo paramServiceInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) throws RemoteException {}
  
  public void scheduleDestroyBackupAgent(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt) throws RemoteException {}
  
  public void scheduleEnterAnimationComplete(IBinder paramIBinder) throws RemoteException {}
  
  public void scheduleExit() throws RemoteException {}
  
  public void scheduleInstallProvider(ProviderInfo paramProviderInfo) throws RemoteException {}
  
  public void scheduleLocalVoiceInteractionStarted(IBinder paramIBinder, IVoiceInteractor paramIVoiceInteractor) throws RemoteException {}
  
  public void scheduleLowMemory() throws RemoteException {}
  
  public void scheduleOnNewActivityOptions(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {}
  
  public void scheduleReceiver(Intent paramIntent, ActivityInfo paramActivityInfo, CompatibilityInfo paramCompatibilityInfo, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void scheduleRegisteredReceiver(IIntentReceiver paramIIntentReceiver, Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void scheduleServiceArgs(IBinder paramIBinder, ParceledListSlice paramParceledListSlice) throws RemoteException {}
  
  public void scheduleStopService(IBinder paramIBinder) throws RemoteException {}
  
  public void scheduleSuicide() throws RemoteException {}
  
  public void scheduleTransaction(ClientTransaction paramClientTransaction) throws RemoteException {}
  
  public void scheduleTranslucentConversionComplete(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void scheduleTrimMemory(int paramInt) throws RemoteException {}
  
  public void scheduleUnbindService(IBinder paramIBinder, Intent paramIntent) throws RemoteException {}
  
  public void setCoreSettings(Bundle paramBundle) throws RemoteException {}
  
  public void setNetworkBlockSeq(long paramLong) throws RemoteException {}
  
  public void setProcessState(int paramInt) throws RemoteException {}
  
  public void setSchedulingGroup(int paramInt) throws RemoteException {}
  
  public void startBinderTracking() throws RemoteException {}
  
  public void stopBinderTrackingAndDump(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {}
  
  public void unstableProviderDied(IBinder paramIBinder) throws RemoteException {}
  
  public void updateHttpProxy() throws RemoteException {}
  
  public void updatePackageCompatibilityInfo(String paramString, CompatibilityInfo paramCompatibilityInfo) throws RemoteException {}
  
  public void updateTimePrefs(int paramInt) throws RemoteException {}
  
  public void updateTimeZone() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IApplicationThread$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */