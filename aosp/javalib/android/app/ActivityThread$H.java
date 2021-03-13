package android.app;

import android.app.servertransaction.ClientTransaction;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.Trace;
import android.util.Pair;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.os.SomeArgs;

class H extends Handler {
  public static final int APPLICATION_INFO_CHANGED = 156;
  
  public static final int ATTACH_AGENT = 155;
  
  public static final int ATTACH_STARTUP_AGENTS = 162;
  
  public static final int BIND_APPLICATION = 110;
  
  public static final int BIND_SERVICE = 121;
  
  public static final int CLEAN_UP_CONTEXT = 119;
  
  public static final int CONFIGURATION_CHANGED = 118;
  
  public static final int CREATE_BACKUP_AGENT = 128;
  
  public static final int CREATE_SERVICE = 114;
  
  public static final int DESTROY_BACKUP_AGENT = 129;
  
  public static final int DISPATCH_PACKAGE_BROADCAST = 133;
  
  public static final int DUMP_ACTIVITY = 136;
  
  public static final int DUMP_HEAP = 135;
  
  public static final int DUMP_PROVIDER = 141;
  
  public static final int DUMP_SERVICE = 123;
  
  public static final int ENTER_ANIMATION_COMPLETE = 149;
  
  public static final int EXECUTE_TRANSACTION = 159;
  
  public static final int EXIT_APPLICATION = 111;
  
  public static final int GC_WHEN_IDLE = 120;
  
  public static final int INSTALL_PROVIDER = 145;
  
  public static final int LOCAL_VOICE_INTERACTION_STARTED = 154;
  
  public static final int LOW_MEMORY = 124;
  
  public static final int ON_NEW_ACTIVITY_OPTIONS = 146;
  
  public static final int PROFILER_CONTROL = 127;
  
  public static final int PURGE_RESOURCES = 161;
  
  public static final int RECEIVER = 113;
  
  public static final int RELAUNCH_ACTIVITY = 160;
  
  public static final int REMOVE_PROVIDER = 131;
  
  public static final int REQUEST_ASSIST_CONTEXT_EXTRAS = 143;
  
  public static final int RUN_ISOLATED_ENTRY_POINT = 158;
  
  public static final int SCHEDULE_CRASH = 134;
  
  public static final int SERVICE_ARGS = 115;
  
  public static final int SET_CORE_SETTINGS = 138;
  
  public static final int SLEEPING = 137;
  
  public static final int START_BINDER_TRACKING = 150;
  
  public static final int STOP_BINDER_TRACKING_AND_DUMP = 151;
  
  public static final int STOP_SERVICE = 116;
  
  public static final int SUICIDE = 130;
  
  public static final int TRANSLUCENT_CONVERSION_COMPLETE = 144;
  
  public static final int UNBIND_SERVICE = 122;
  
  public static final int UNSTABLE_PROVIDER_DIED = 142;
  
  public static final int UPDATE_PACKAGE_COMPATIBILITY_INFO = 139;
  
  String codeToString(int paramInt) {
    return Integer.toString(paramInt);
  }
  
  public void handleMessage(Message paramMessage) {
    ClientTransaction clientTransaction;
    Application application;
    Pair pair;
    IBinder iBinder;
    ActivityThread activityThread1;
    ActivityThread.ContextCleanupInfo contextCleanupInfo;
    ActivityThread.AppBindData appBindData;
    String str;
    ActivityThread activityThread2;
    int i = paramMessage.what;
    boolean bool1 = true;
    boolean bool2 = true;
    switch (i) {
      case 162:
        ActivityThread.handleAttachStartupAgents((String)paramMessage.obj);
        break;
      case 161:
        ActivityThread.this.schedulePurgeIdler();
        break;
      case 160:
        ActivityThread.access$3300(ActivityThread.this, (IBinder)paramMessage.obj);
        break;
      case 159:
        clientTransaction = (ClientTransaction)paramMessage.obj;
        ActivityThread.access$3200(ActivityThread.this).execute(clientTransaction);
        if (ActivityThread.isSystem())
          clientTransaction.recycle(); 
        break;
      case 158:
        ActivityThread.access$3100(ActivityThread.this, (String)((SomeArgs)paramMessage.obj).arg1, (String[])((SomeArgs)paramMessage.obj).arg2);
        break;
      case 156:
        ActivityThread.this.handleApplicationInfoChanged((ApplicationInfo)paramMessage.obj);
        break;
      case 155:
        application = ActivityThread.this.getApplication();
        str = (String)paramMessage.obj;
        if (application != null) {
          LoadedApk loadedApk = application.mLoadedApk;
        } else {
          application = null;
        } 
        ActivityThread.handleAttachAgent(str, (LoadedApk)application);
        break;
      case 154:
        ActivityThread.access$3000(ActivityThread.this, (IBinder)((SomeArgs)paramMessage.obj).arg1, (IVoiceInteractor)((SomeArgs)paramMessage.obj).arg2);
        break;
      case 151:
        ActivityThread.access$2900(ActivityThread.this, (ParcelFileDescriptor)paramMessage.obj);
        break;
      case 150:
        ActivityThread.access$2800(ActivityThread.this);
        break;
      case 149:
        ActivityThread.access$2700(ActivityThread.this, (IBinder)paramMessage.obj);
        break;
      case 146:
        pair = (Pair)paramMessage.obj;
        ActivityThread.this.onNewActivityOptions((IBinder)pair.first, (ActivityOptions)pair.second);
        break;
      case 145:
        ActivityThread.this.handleInstallProvider((ProviderInfo)paramMessage.obj);
        break;
      case 144:
        activityThread2 = ActivityThread.this;
        iBinder = (IBinder)paramMessage.obj;
        if (paramMessage.arg1 != 1)
          bool2 = false; 
        activityThread2.handleTranslucentConversionComplete(iBinder, bool2);
        break;
      case 143:
        ActivityThread.this.handleRequestAssistContextExtras((ActivityThread.RequestAssistContextExtras)paramMessage.obj);
        break;
      case 142:
        ActivityThread.this.handleUnstableProviderDied((IBinder)paramMessage.obj, false);
        break;
      case 141:
        ActivityThread.access$2400(ActivityThread.this, (ActivityThread.DumpComponentInfo)paramMessage.obj);
        break;
      case 139:
        ActivityThread.access$2600(ActivityThread.this, (ActivityThread.UpdateCompatibilityData)paramMessage.obj);
        break;
      case 138:
        Trace.traceBegin(64L, "setCoreSettings");
        ActivityThread.access$2500(ActivityThread.this, (Bundle)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 136:
        ActivityThread.access$2300(ActivityThread.this, (ActivityThread.DumpComponentInfo)paramMessage.obj);
        break;
      case 135:
        ActivityThread.handleDumpHeap((ActivityThread.DumpHeapData)paramMessage.obj);
        break;
      case 134:
        throw new RemoteServiceException((String)paramMessage.obj);
      case 133:
        Trace.traceBegin(64L, "broadcastPackage");
        ActivityThread.this.handleDispatchPackageBroadcast(paramMessage.arg1, (String[])paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 131:
        Trace.traceBegin(64L, "providerRemove");
        ActivityThread.this.completeRemoveProvider((ActivityThread.ProviderRefCount)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 130:
        Process.killProcess(Process.myPid());
        break;
      case 129:
        Trace.traceBegin(64L, "backupDestroyAgent");
        ActivityThread.access$2200(ActivityThread.this, (ActivityThread.CreateBackupAgentData)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 128:
        Trace.traceBegin(64L, "backupCreateAgent");
        ActivityThread.access$2100(ActivityThread.this, (ActivityThread.CreateBackupAgentData)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 127:
        activityThread1 = ActivityThread.this;
        if (paramMessage.arg1 != 0) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        activityThread1.handleProfilerControl(bool2, (ProfilerInfo)paramMessage.obj, paramMessage.arg2);
        break;
      case 124:
        Trace.traceBegin(64L, "lowMemory");
        ActivityThread.this.handleLowMemory();
        Trace.traceEnd(64L);
        break;
      case 123:
        ActivityThread.access$2000(ActivityThread.this, (ActivityThread.DumpComponentInfo)paramMessage.obj);
        break;
      case 122:
        Trace.traceBegin(64L, "serviceUnbind");
        ActivityThread.access$1700(ActivityThread.this, (ActivityThread.BindServiceData)paramMessage.obj);
        ActivityThread.this.schedulePurgeIdler();
        Trace.traceEnd(64L);
        break;
      case 121:
        Trace.traceBegin(64L, "serviceBind");
        ActivityThread.access$1600(ActivityThread.this, (ActivityThread.BindServiceData)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 120:
        ActivityThread.this.scheduleGcIdler();
        break;
      case 119:
        contextCleanupInfo = (ActivityThread.ContextCleanupInfo)paramMessage.obj;
        contextCleanupInfo.context.performFinalCleanup(contextCleanupInfo.who, contextCleanupInfo.what);
        break;
      case 118:
        ActivityThread.this.handleConfigurationChanged((Configuration)paramMessage.obj);
        break;
      case 116:
        Trace.traceBegin(64L, "serviceStop");
        ActivityThread.access$1900(ActivityThread.this, (IBinder)paramMessage.obj);
        ActivityThread.this.schedulePurgeIdler();
        Trace.traceEnd(64L);
        break;
      case 115:
        if (Trace.isTagEnabled(64L)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("serviceStart: ");
          stringBuilder.append(String.valueOf(paramMessage.obj));
          Trace.traceBegin(64L, stringBuilder.toString());
        } 
        ActivityThread.access$1800(ActivityThread.this, (ActivityThread.ServiceArgsData)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 114:
        if (Trace.isTagEnabled(64L)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("serviceCreate: ");
          stringBuilder.append(String.valueOf(paramMessage.obj));
          Trace.traceBegin(64L, stringBuilder.toString());
        } 
        ActivityThread.access$1500(ActivityThread.this, (ActivityThread.CreateServiceData)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 113:
        Trace.traceBegin(64L, "broadcastReceiveComp");
        ActivityThread.access$1400(ActivityThread.this, (ActivityThread.ReceiverData)paramMessage.obj);
        Trace.traceEnd(64L);
        break;
      case 111:
        if (ActivityThread.this.mInitialApplication != null)
          ActivityThread.this.mInitialApplication.onTerminate(); 
        Looper.myLooper().quit();
        break;
      case 110:
        Trace.traceBegin(64L, "bindApplication");
        appBindData = (ActivityThread.AppBindData)paramMessage.obj;
        ActivityThread.access$1300(ActivityThread.this, appBindData);
        Trace.traceEnd(64L);
        break;
    } 
    Object object = paramMessage.obj;
    if (object instanceof SomeArgs)
      ((SomeArgs)object).recycle(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$H.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */