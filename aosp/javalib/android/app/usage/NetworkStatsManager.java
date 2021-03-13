package android.app.usage;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.DataUsageRequest;
import android.net.INetworkStatsService;
import android.net.NetworkIdentity;
import android.net.NetworkTemplate;
import android.net.netstats.provider.NetworkStatsProvider;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.DataUnit;
import android.util.Log;
import java.util.Objects;

public class NetworkStatsManager {
  public static final int CALLBACK_LIMIT_REACHED = 0;
  
  public static final int CALLBACK_RELEASED = 1;
  
  private static final boolean DBG = false;
  
  public static final int FLAG_AUGMENT_WITH_SUBSCRIPTION_PLAN = 4;
  
  public static final int FLAG_POLL_FORCE = 2;
  
  public static final int FLAG_POLL_ON_OPEN = 1;
  
  public static final long MIN_THRESHOLD_BYTES = DataUnit.MEBIBYTES.toBytes(2L);
  
  private static final String TAG = "NetworkStatsManager";
  
  private final Context mContext;
  
  private int mFlags;
  
  private final INetworkStatsService mService;
  
  public NetworkStatsManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this(paramContext, INetworkStatsService.Stub.asInterface(ServiceManager.getServiceOrThrow("netstats")));
  }
  
  public NetworkStatsManager(Context paramContext, INetworkStatsService paramINetworkStatsService) {
    this.mContext = paramContext;
    this.mService = paramINetworkStatsService;
    setPollOnOpen(true);
  }
  
  private static NetworkTemplate createTemplate(int paramInt, String paramString) {
    NetworkTemplate networkTemplate;
    if (paramInt != 0) {
      if (paramInt == 1) {
        networkTemplate = NetworkTemplate.buildTemplateWifiWildcard();
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cannot create template for network type ");
        stringBuilder.append(paramInt);
        stringBuilder.append(", subscriberId '");
        stringBuilder.append(NetworkIdentity.scrubSubscriberId((String)networkTemplate));
        stringBuilder.append("'.");
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
    } else if (networkTemplate == null) {
      networkTemplate = NetworkTemplate.buildTemplateMobileWildcard();
    } else {
      networkTemplate = NetworkTemplate.buildTemplateMobileAll((String)networkTemplate);
    } 
    return networkTemplate;
  }
  
  public NetworkStats queryDetails(int paramInt, String paramString, long paramLong1, long paramLong2) throws SecurityException, RemoteException {
    try {
      NetworkTemplate networkTemplate = createTemplate(paramInt, paramString);
      NetworkStats networkStats = new NetworkStats(this.mContext, networkTemplate, this.mFlags, paramLong1, paramLong2, this.mService);
      networkStats.startUserUidEnumeration();
      return networkStats;
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  public NetworkStats queryDetailsForUid(int paramInt1, String paramString, long paramLong1, long paramLong2, int paramInt2) throws SecurityException {
    return queryDetailsForUidTagState(paramInt1, paramString, paramLong1, paramLong2, paramInt2, 0, -1);
  }
  
  public NetworkStats queryDetailsForUid(NetworkTemplate paramNetworkTemplate, long paramLong1, long paramLong2, int paramInt) throws SecurityException {
    return queryDetailsForUidTagState(paramNetworkTemplate, paramLong1, paramLong2, paramInt, 0, -1);
  }
  
  public NetworkStats queryDetailsForUidTag(int paramInt1, String paramString, long paramLong1, long paramLong2, int paramInt2, int paramInt3) throws SecurityException {
    return queryDetailsForUidTagState(paramInt1, paramString, paramLong1, paramLong2, paramInt2, paramInt3, -1);
  }
  
  public NetworkStats queryDetailsForUidTagState(int paramInt1, String paramString, long paramLong1, long paramLong2, int paramInt2, int paramInt3, int paramInt4) throws SecurityException {
    return queryDetailsForUidTagState(createTemplate(paramInt1, paramString), paramLong1, paramLong2, paramInt2, paramInt3, paramInt4);
  }
  
  public NetworkStats queryDetailsForUidTagState(NetworkTemplate paramNetworkTemplate, long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3) throws SecurityException {
    try {
      NetworkStats networkStats = new NetworkStats();
      this(this.mContext, paramNetworkTemplate, this.mFlags, paramLong1, paramLong2, this.mService);
      networkStats.startHistoryEnumeration(paramInt1, paramInt2, paramInt3);
      return networkStats;
    } catch (RemoteException remoteException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error while querying stats for uid=");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" tag=");
      stringBuilder.append(paramInt2);
      stringBuilder.append(" state=");
      stringBuilder.append(paramInt3);
      Log.e("NetworkStatsManager", stringBuilder.toString(), (Throwable)remoteException);
      return null;
    } 
  }
  
  public NetworkStats querySummary(int paramInt, String paramString, long paramLong1, long paramLong2) throws SecurityException, RemoteException {
    try {
      NetworkTemplate networkTemplate = createTemplate(paramInt, paramString);
      return querySummary(networkTemplate, paramLong1, paramLong2);
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  public NetworkStats querySummary(NetworkTemplate paramNetworkTemplate, long paramLong1, long paramLong2) throws SecurityException, RemoteException {
    NetworkStats networkStats = new NetworkStats(this.mContext, paramNetworkTemplate, this.mFlags, paramLong1, paramLong2, this.mService);
    networkStats.startSummaryEnumeration();
    return networkStats;
  }
  
  public NetworkStats.Bucket querySummaryForDevice(int paramInt, String paramString, long paramLong1, long paramLong2) throws SecurityException, RemoteException {
    try {
      NetworkTemplate networkTemplate = createTemplate(paramInt, paramString);
      return querySummaryForDevice(networkTemplate, paramLong1, paramLong2);
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  public NetworkStats.Bucket querySummaryForDevice(NetworkTemplate paramNetworkTemplate, long paramLong1, long paramLong2) throws SecurityException, RemoteException {
    NetworkStats networkStats = new NetworkStats(this.mContext, paramNetworkTemplate, this.mFlags, paramLong1, paramLong2, this.mService);
    NetworkStats.Bucket bucket = networkStats.getDeviceSummaryForNetwork();
    networkStats.close();
    return bucket;
  }
  
  public NetworkStats.Bucket querySummaryForUser(int paramInt, String paramString, long paramLong1, long paramLong2) throws SecurityException, RemoteException {
    try {
      NetworkTemplate networkTemplate = createTemplate(paramInt, paramString);
      NetworkStats networkStats = new NetworkStats(this.mContext, networkTemplate, this.mFlags, paramLong1, paramLong2, this.mService);
      networkStats.startSummaryEnumeration();
      networkStats.close();
      return networkStats.getSummaryAggregate();
    } catch (IllegalArgumentException illegalArgumentException) {
      return null;
    } 
  }
  
  @SystemApi
  public void registerNetworkStatsProvider(String paramString, NetworkStatsProvider paramNetworkStatsProvider) {
    try {
      if (paramNetworkStatsProvider.getProviderCallbackBinder() == null) {
        paramNetworkStatsProvider.setProviderCallbackBinder(this.mService.registerNetworkStatsProvider(paramString, paramNetworkStatsProvider.getProviderBinder()));
      } else {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("provider is already registered");
        throw illegalArgumentException;
      } 
    } catch (RemoteException remoteException) {
      remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public void registerUsageCallback(int paramInt, String paramString, long paramLong, UsageCallback paramUsageCallback) {
    registerUsageCallback(paramInt, paramString, paramLong, paramUsageCallback, (Handler)null);
  }
  
  public void registerUsageCallback(int paramInt, String paramString, long paramLong, UsageCallback paramUsageCallback, Handler paramHandler) {
    registerUsageCallback(createTemplate(paramInt, paramString), paramInt, paramLong, paramUsageCallback, paramHandler);
  }
  
  public void registerUsageCallback(NetworkTemplate paramNetworkTemplate, int paramInt, long paramLong, UsageCallback paramUsageCallback, Handler paramHandler) {
    Looper looper;
    Objects.requireNonNull(paramUsageCallback, "UsageCallback cannot be null");
    if (paramHandler == null) {
      looper = Looper.myLooper();
    } else {
      looper = looper.getLooper();
    } 
    DataUsageRequest dataUsageRequest = new DataUsageRequest(0, paramNetworkTemplate, paramLong);
    try {
      CallbackHandler callbackHandler = new CallbackHandler();
      this(looper, paramInt, paramNetworkTemplate.getSubscriberId(), paramUsageCallback);
      INetworkStatsService iNetworkStatsService = this.mService;
      String str = this.mContext.getOpPackageName();
      Messenger messenger = new Messenger();
      this(callbackHandler);
      Binder binder = new Binder();
      this();
      UsageCallback.access$002(paramUsageCallback, iNetworkStatsService.registerUsageCallback(str, dataUsageRequest, messenger, (IBinder)binder));
      if (paramUsageCallback.request == null)
        Log.e("NetworkStatsManager", "Request from callback is null; should not happen"); 
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setAugmentWithSubscriptionPlan(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x4;
    } else {
      this.mFlags &= 0xFFFFFFFB;
    } 
  }
  
  public void setPollForce(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x2;
    } else {
      this.mFlags &= 0xFFFFFFFD;
    } 
  }
  
  public void setPollOnOpen(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x1;
    } else {
      this.mFlags &= 0xFFFFFFFE;
    } 
  }
  
  @SystemApi
  public void unregisterNetworkStatsProvider(NetworkStatsProvider paramNetworkStatsProvider) {
    try {
      paramNetworkStatsProvider.getProviderCallbackBinderOrThrow().unregister();
    } catch (RemoteException remoteException) {
      remoteException.rethrowAsRuntimeException();
    } 
  }
  
  public void unregisterUsageCallback(UsageCallback paramUsageCallback) {
    if (paramUsageCallback != null && paramUsageCallback.request != null && paramUsageCallback.request.requestId != 0)
      try {
        this.mService.unregisterUsageRequest(paramUsageCallback.request);
        return;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    throw new IllegalArgumentException("Invalid UsageCallback");
  }
  
  private static class CallbackHandler extends Handler {
    private NetworkStatsManager.UsageCallback mCallback;
    
    private final int mNetworkType;
    
    private final String mSubscriberId;
    
    CallbackHandler(Looper param1Looper, int param1Int, String param1String, NetworkStatsManager.UsageCallback param1UsageCallback) {
      super(param1Looper);
      this.mNetworkType = param1Int;
      this.mSubscriberId = param1String;
      this.mCallback = param1UsageCallback;
    }
    
    private static Object getObject(Message param1Message, String param1String) {
      return param1Message.getData().getParcelable(param1String);
    }
    
    public void handleMessage(Message param1Message) {
      DataUsageRequest dataUsageRequest = (DataUsageRequest)getObject(param1Message, "DataUsageRequest");
      int i = param1Message.what;
      if (i != 0) {
        if (i == 1)
          this.mCallback = null; 
      } else {
        NetworkStatsManager.UsageCallback usageCallback = this.mCallback;
        if (usageCallback != null) {
          usageCallback.onThresholdReached(this.mNetworkType, this.mSubscriberId);
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("limit reached with released callback for ");
          stringBuilder.append(dataUsageRequest);
          Log.e("NetworkStatsManager", stringBuilder.toString());
        } 
      } 
    }
  }
  
  public static abstract class UsageCallback {
    private DataUsageRequest request;
    
    public abstract void onThresholdReached(int param1Int, String param1String);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/NetworkStatsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */