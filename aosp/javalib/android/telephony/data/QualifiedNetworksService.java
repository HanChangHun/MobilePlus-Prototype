package android.telephony.data;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.SparseArray;
import com.android.telephony.Rlog;
import java.util.List;
import java.util.function.ToIntFunction;

@SystemApi
public abstract class QualifiedNetworksService extends Service {
  private static final int QNS_CREATE_NETWORK_AVAILABILITY_PROVIDER = 1;
  
  private static final int QNS_REMOVE_ALL_NETWORK_AVAILABILITY_PROVIDERS = 3;
  
  private static final int QNS_REMOVE_NETWORK_AVAILABILITY_PROVIDER = 2;
  
  private static final int QNS_UPDATE_QUALIFIED_NETWORKS = 4;
  
  public static final String QUALIFIED_NETWORKS_SERVICE_INTERFACE = "android.telephony.data.QualifiedNetworksService";
  
  private static final String TAG = QualifiedNetworksService.class.getSimpleName();
  
  public final IQualifiedNetworksServiceWrapper mBinder = new IQualifiedNetworksServiceWrapper();
  
  private final QualifiedNetworksServiceHandler mHandler;
  
  private final HandlerThread mHandlerThread;
  
  private final SparseArray<NetworkAvailabilityProvider> mProviders = new SparseArray();
  
  public QualifiedNetworksService() {
    HandlerThread handlerThread = new HandlerThread(TAG);
    this.mHandlerThread = handlerThread;
    handlerThread.start();
    this.mHandler = new QualifiedNetworksServiceHandler(this.mHandlerThread.getLooper());
    log("Qualified networks service created");
  }
  
  private void log(String paramString) {
    Rlog.d(TAG, paramString);
  }
  
  private void loge(String paramString) {
    Rlog.e(TAG, paramString);
  }
  
  public IBinder onBind(Intent paramIntent) {
    if (paramIntent == null || !"android.telephony.data.QualifiedNetworksService".equals(paramIntent.getAction())) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected intent ");
      stringBuilder.append(paramIntent);
      loge(stringBuilder.toString());
      return null;
    } 
    return (IBinder)this.mBinder;
  }
  
  public abstract NetworkAvailabilityProvider onCreateNetworkAvailabilityProvider(int paramInt);
  
  public void onDestroy() {
    this.mHandlerThread.quit();
  }
  
  public boolean onUnbind(Intent paramIntent) {
    this.mHandler.obtainMessage(3).sendToTarget();
    return false;
  }
  
  private class IQualifiedNetworksServiceWrapper extends IQualifiedNetworksService.Stub {
    private IQualifiedNetworksServiceWrapper() {}
    
    public void createNetworkAvailabilityProvider(int param1Int, IQualifiedNetworksServiceCallback param1IQualifiedNetworksServiceCallback) {
      QualifiedNetworksService.this.mHandler.obtainMessage(1, param1Int, 0, param1IQualifiedNetworksServiceCallback).sendToTarget();
    }
    
    public void removeNetworkAvailabilityProvider(int param1Int) {
      QualifiedNetworksService.this.mHandler.obtainMessage(2, param1Int, 0).sendToTarget();
    }
  }
  
  public abstract class NetworkAvailabilityProvider implements AutoCloseable {
    private IQualifiedNetworksServiceCallback mCallback;
    
    private SparseArray<int[]> mQualifiedNetworkTypesList = new SparseArray();
    
    private final int mSlotIndex;
    
    public NetworkAvailabilityProvider(int param1Int) {
      this.mSlotIndex = param1Int;
    }
    
    private void onUpdateQualifiedNetworkTypes(int param1Int, int[] param1ArrayOfint) {
      this.mQualifiedNetworkTypesList.put(param1Int, param1ArrayOfint);
      IQualifiedNetworksServiceCallback iQualifiedNetworksServiceCallback = this.mCallback;
      if (iQualifiedNetworksServiceCallback != null)
        try {
          iQualifiedNetworksServiceCallback.onQualifiedNetworkTypesChanged(param1Int, param1ArrayOfint);
        } catch (RemoteException remoteException) {
          QualifiedNetworksService qualifiedNetworksService = QualifiedNetworksService.this;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to call onQualifiedNetworksChanged. ");
          stringBuilder.append(remoteException);
          qualifiedNetworksService.loge(stringBuilder.toString());
        }  
    }
    
    private void registerForQualifiedNetworkTypesChanged(IQualifiedNetworksServiceCallback param1IQualifiedNetworksServiceCallback) {
      this.mCallback = param1IQualifiedNetworksServiceCallback;
      if (param1IQualifiedNetworksServiceCallback != null)
        for (byte b = 0; b < this.mQualifiedNetworkTypesList.size(); b++) {
          try {
            this.mCallback.onQualifiedNetworkTypesChanged(this.mQualifiedNetworkTypesList.keyAt(b), (int[])this.mQualifiedNetworkTypesList.valueAt(b));
          } catch (RemoteException remoteException) {
            QualifiedNetworksService qualifiedNetworksService = QualifiedNetworksService.this;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to call onQualifiedNetworksChanged. ");
            stringBuilder.append(remoteException);
            qualifiedNetworksService.loge(stringBuilder.toString());
          } 
        }  
    }
    
    public abstract void close();
    
    public final int getSlotIndex() {
      return this.mSlotIndex;
    }
    
    public final void updateQualifiedNetworkTypes(int param1Int, List<Integer> param1List) {
      int[] arrayOfInt = param1List.stream().mapToInt((ToIntFunction)_$$Lambda$QualifiedNetworksService$NetworkAvailabilityProvider$sNPqwkqArvqymBmHYmxAc4rF5Es.INSTANCE).toArray();
      QualifiedNetworksService.this.mHandler.obtainMessage(4, this.mSlotIndex, param1Int, arrayOfInt).sendToTarget();
    }
  }
  
  private class QualifiedNetworksServiceHandler extends Handler {
    QualifiedNetworksServiceHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      int i = param1Message.arg1;
      QualifiedNetworksService.NetworkAvailabilityProvider networkAvailabilityProvider = (QualifiedNetworksService.NetworkAvailabilityProvider)QualifiedNetworksService.this.mProviders.get(i);
      int j = param1Message.what;
      if (j != 1) {
        if (j != 2) {
          if (j != 3) {
            if (j == 4 && networkAvailabilityProvider != null)
              networkAvailabilityProvider.onUpdateQualifiedNetworkTypes(param1Message.arg2, (int[])param1Message.obj); 
          } else {
            for (i = 0; i < QualifiedNetworksService.this.mProviders.size(); i++) {
              QualifiedNetworksService.NetworkAvailabilityProvider networkAvailabilityProvider1 = (QualifiedNetworksService.NetworkAvailabilityProvider)QualifiedNetworksService.this.mProviders.get(i);
              if (networkAvailabilityProvider1 != null)
                networkAvailabilityProvider1.close(); 
            } 
            QualifiedNetworksService.this.mProviders.clear();
          } 
        } else if (networkAvailabilityProvider != null) {
          networkAvailabilityProvider.close();
          QualifiedNetworksService.this.mProviders.remove(i);
        } 
      } else {
        QualifiedNetworksService qualifiedNetworksService;
        if (QualifiedNetworksService.this.mProviders.get(i) != null) {
          qualifiedNetworksService = QualifiedNetworksService.this;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Network availability provider for slot ");
          stringBuilder.append(i);
          stringBuilder.append(" already existed.");
          qualifiedNetworksService.loge(stringBuilder.toString());
          return;
        } 
        networkAvailabilityProvider = QualifiedNetworksService.this.onCreateNetworkAvailabilityProvider(i);
        if (networkAvailabilityProvider != null) {
          QualifiedNetworksService.this.mProviders.put(i, networkAvailabilityProvider);
          networkAvailabilityProvider.registerForQualifiedNetworkTypesChanged((IQualifiedNetworksServiceCallback)((Message)qualifiedNetworksService).obj);
        } else {
          qualifiedNetworksService = QualifiedNetworksService.this;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Failed to create network availability provider. slot index = ");
          stringBuilder.append(i);
          qualifiedNetworksService.loge(stringBuilder.toString());
        } 
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/QualifiedNetworksService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */