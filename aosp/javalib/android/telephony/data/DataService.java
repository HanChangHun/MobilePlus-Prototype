package android.telephony.data;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.net.LinkProperties;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.SparseArray;
import com.android.telephony.Rlog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public abstract class DataService extends Service {
  private static final int DATA_SERVICE_CREATE_DATA_SERVICE_PROVIDER = 1;
  
  private static final int DATA_SERVICE_INDICATION_DATA_CALL_LIST_CHANGED = 11;
  
  private static final int DATA_SERVICE_REMOVE_ALL_DATA_SERVICE_PROVIDERS = 3;
  
  private static final int DATA_SERVICE_REMOVE_DATA_SERVICE_PROVIDER = 2;
  
  private static final int DATA_SERVICE_REQUEST_DEACTIVATE_DATA_CALL = 5;
  
  private static final int DATA_SERVICE_REQUEST_REGISTER_DATA_CALL_LIST_CHANGED = 9;
  
  private static final int DATA_SERVICE_REQUEST_REQUEST_DATA_CALL_LIST = 8;
  
  private static final int DATA_SERVICE_REQUEST_SETUP_DATA_CALL = 4;
  
  private static final int DATA_SERVICE_REQUEST_SET_DATA_PROFILE = 7;
  
  private static final int DATA_SERVICE_REQUEST_SET_INITIAL_ATTACH_APN = 6;
  
  private static final int DATA_SERVICE_REQUEST_UNREGISTER_DATA_CALL_LIST_CHANGED = 10;
  
  public static final int REQUEST_REASON_HANDOVER = 3;
  
  public static final int REQUEST_REASON_NORMAL = 1;
  
  public static final int REQUEST_REASON_SHUTDOWN = 2;
  
  public static final int REQUEST_REASON_UNKNOWN = 0;
  
  public static final String SERVICE_INTERFACE = "android.telephony.data.DataService";
  
  private static final String TAG = DataService.class.getSimpleName();
  
  public final IDataServiceWrapper mBinder = new IDataServiceWrapper();
  
  private final DataServiceHandler mHandler;
  
  private final HandlerThread mHandlerThread;
  
  private final SparseArray<DataServiceProvider> mServiceMap = new SparseArray();
  
  public DataService() {
    HandlerThread handlerThread = new HandlerThread(TAG);
    this.mHandlerThread = handlerThread;
    handlerThread.start();
    this.mHandler = new DataServiceHandler(this.mHandlerThread.getLooper());
    log("Data service created");
  }
  
  private void log(String paramString) {
    Rlog.d(TAG, paramString);
  }
  
  private void loge(String paramString) {
    Rlog.e(TAG, paramString);
  }
  
  public IBinder onBind(Intent paramIntent) {
    if (paramIntent == null || !"android.telephony.data.DataService".equals(paramIntent.getAction())) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected intent ");
      stringBuilder.append(paramIntent);
      loge(stringBuilder.toString());
      return null;
    } 
    return (IBinder)this.mBinder;
  }
  
  public abstract DataServiceProvider onCreateDataServiceProvider(int paramInt);
  
  public void onDestroy() {
    this.mHandlerThread.quit();
    super.onDestroy();
  }
  
  public boolean onUnbind(Intent paramIntent) {
    this.mHandler.obtainMessage(3).sendToTarget();
    return false;
  }
  
  private static final class DataCallListChangedIndication {
    public final IDataServiceCallback callback;
    
    public final List<DataCallResponse> dataCallList;
    
    DataCallListChangedIndication(List<DataCallResponse> param1List, IDataServiceCallback param1IDataServiceCallback) {
      this.dataCallList = param1List;
      this.callback = param1IDataServiceCallback;
    }
  }
  
  private class DataServiceHandler extends Handler {
    DataServiceHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      StringBuilder stringBuilder1;
      List<DataProfile> list1;
      DataService.DeactivateDataCallRequest deactivateDataCallRequest1;
      List<DataProfile> list2;
      int i = param1Message.arg1;
      DataService.DataServiceProvider dataServiceProvider2 = (DataService.DataServiceProvider)DataService.this.mServiceMap.get(i);
      int j = param1Message.what;
      DataService dataService = null;
      DataService.DeactivateDataCallRequest deactivateDataCallRequest2 = null;
      StringBuilder stringBuilder2 = null;
      switch (j) {
        default:
          return;
        case 11:
          if (dataServiceProvider2 != null) {
            DataService.DataCallListChangedIndication dataCallListChangedIndication = (DataService.DataCallListChangedIndication)param1Message.obj;
            try {
              dataCallListChangedIndication.callback.onDataCallListChanged(dataCallListChangedIndication.dataCallList);
            } catch (RemoteException remoteException) {
              dataService = DataService.this;
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("Failed to call onDataCallListChanged. ");
              stringBuilder2.append(remoteException);
              dataService.loge(stringBuilder2.toString());
            } 
          } 
        case 10:
          if (dataServiceProvider2 != null)
            dataServiceProvider2.unregisterForDataCallListChanged((IDataServiceCallback)((Message)remoteException).obj); 
        case 9:
          if (dataServiceProvider2 != null)
            dataServiceProvider2.registerForDataCallListChanged((IDataServiceCallback)((Message)remoteException).obj); 
        case 8:
          if (dataServiceProvider2 != null)
            dataServiceProvider2.requestDataCallList(new DataServiceCallback((IDataServiceCallback)((Message)remoteException).obj)); 
        case 7:
          if (dataServiceProvider2 != null) {
            DataService.SetDataProfileRequest setDataProfileRequest = (DataService.SetDataProfileRequest)((Message)remoteException).obj;
            list2 = setDataProfileRequest.dps;
            boolean bool = setDataProfileRequest.isRoaming;
            if (setDataProfileRequest.callback != null) {
              DataServiceCallback dataServiceCallback = new DataServiceCallback(setDataProfileRequest.callback);
            } else {
              stringBuilder1 = stringBuilder2;
            } 
            dataServiceProvider2.setDataProfile(list2, bool, (DataServiceCallback)stringBuilder1);
          } 
        case 6:
          if (dataServiceProvider2 != null) {
            DataService.SetInitialAttachApnRequest setInitialAttachApnRequest = (DataService.SetInitialAttachApnRequest)((Message)stringBuilder1).obj;
            DataProfile dataProfile = setInitialAttachApnRequest.dataProfile;
            boolean bool = setInitialAttachApnRequest.isRoaming;
            if (setInitialAttachApnRequest.callback != null) {
              DataServiceCallback dataServiceCallback = new DataServiceCallback(setInitialAttachApnRequest.callback);
            } else {
              list1 = list2;
            } 
            dataServiceProvider2.setInitialAttachApn(dataProfile, bool, (DataServiceCallback)list1);
          } 
        case 5:
          if (dataServiceProvider2 != null) {
            deactivateDataCallRequest1 = (DataService.DeactivateDataCallRequest)((Message)list1).obj;
            j = deactivateDataCallRequest1.cid;
            i = deactivateDataCallRequest1.reason;
            if (deactivateDataCallRequest1.callback != null) {
              DataServiceCallback dataServiceCallback = new DataServiceCallback(deactivateDataCallRequest1.callback);
            } else {
              deactivateDataCallRequest1 = deactivateDataCallRequest2;
            } 
            dataServiceProvider2.deactivateDataCall(j, i, (DataServiceCallback)deactivateDataCallRequest1);
          } 
        case 4:
          if (dataServiceProvider2 != null) {
            DataService.SetupDataCallRequest setupDataCallRequest = (DataService.SetupDataCallRequest)((Message)deactivateDataCallRequest1).obj;
            i = setupDataCallRequest.accessNetworkType;
            DataProfile dataProfile = setupDataCallRequest.dataProfile;
            boolean bool2 = setupDataCallRequest.isRoaming;
            boolean bool1 = setupDataCallRequest.allowRoaming;
            j = setupDataCallRequest.reason;
            LinkProperties linkProperties = setupDataCallRequest.linkProperties;
            if (setupDataCallRequest.callback != null) {
              DataServiceCallback dataServiceCallback = new DataServiceCallback(setupDataCallRequest.callback);
            } else {
              setupDataCallRequest = null;
            } 
            dataServiceProvider2.setupDataCall(i, dataProfile, bool2, bool1, j, linkProperties, (DataServiceCallback)setupDataCallRequest);
          } 
        case 3:
          for (i = 0; i < DataService.this.mServiceMap.size(); i++) {
            dataServiceProvider1 = (DataService.DataServiceProvider)DataService.this.mServiceMap.get(i);
            if (dataServiceProvider1 != null)
              dataServiceProvider1.close(); 
          } 
          DataService.this.mServiceMap.clear();
        case 2:
          if (dataServiceProvider2 != null) {
            dataServiceProvider2.close();
            DataService.this.mServiceMap.remove(i);
          } 
        case 1:
          break;
      } 
      DataService.DataServiceProvider dataServiceProvider1 = DataService.this.onCreateDataServiceProvider(((Message)dataServiceProvider1).arg1);
      if (dataServiceProvider1 != null)
        DataService.this.mServiceMap.put(i, dataServiceProvider1); 
    }
  }
  
  public abstract class DataServiceProvider implements AutoCloseable {
    private final List<IDataServiceCallback> mDataCallListChangedCallbacks = new ArrayList<>();
    
    private final int mSlotIndex;
    
    public DataServiceProvider(int param1Int) {
      this.mSlotIndex = param1Int;
    }
    
    private void registerForDataCallListChanged(IDataServiceCallback param1IDataServiceCallback) {
      synchronized (this.mDataCallListChangedCallbacks) {
        this.mDataCallListChangedCallbacks.add(param1IDataServiceCallback);
        return;
      } 
    }
    
    private void unregisterForDataCallListChanged(IDataServiceCallback param1IDataServiceCallback) {
      synchronized (this.mDataCallListChangedCallbacks) {
        this.mDataCallListChangedCallbacks.remove(param1IDataServiceCallback);
        return;
      } 
    }
    
    public abstract void close();
    
    public void deactivateDataCall(int param1Int1, int param1Int2, DataServiceCallback param1DataServiceCallback) {
      if (param1DataServiceCallback != null)
        param1DataServiceCallback.onDeactivateDataCallComplete(1); 
    }
    
    public final int getSlotIndex() {
      return this.mSlotIndex;
    }
    
    public final void notifyDataCallListChanged(List<DataCallResponse> param1List) {
      synchronized (this.mDataCallListChangedCallbacks) {
        for (IDataServiceCallback iDataServiceCallback : this.mDataCallListChangedCallbacks) {
          DataService.DataServiceHandler dataServiceHandler = DataService.this.mHandler;
          int i = this.mSlotIndex;
          DataService.DataCallListChangedIndication dataCallListChangedIndication = new DataService.DataCallListChangedIndication();
          this(param1List, iDataServiceCallback);
          dataServiceHandler.obtainMessage(11, i, 0, dataCallListChangedIndication).sendToTarget();
        } 
        return;
      } 
    }
    
    public void requestDataCallList(DataServiceCallback param1DataServiceCallback) {
      param1DataServiceCallback.onRequestDataCallListComplete(1, null);
    }
    
    public void setDataProfile(List<DataProfile> param1List, boolean param1Boolean, DataServiceCallback param1DataServiceCallback) {
      if (param1DataServiceCallback != null)
        param1DataServiceCallback.onSetDataProfileComplete(1); 
    }
    
    public void setInitialAttachApn(DataProfile param1DataProfile, boolean param1Boolean, DataServiceCallback param1DataServiceCallback) {
      if (param1DataServiceCallback != null)
        param1DataServiceCallback.onSetInitialAttachApnComplete(1); 
    }
    
    public void setupDataCall(int param1Int1, DataProfile param1DataProfile, boolean param1Boolean1, boolean param1Boolean2, int param1Int2, LinkProperties param1LinkProperties, DataServiceCallback param1DataServiceCallback) {
      if (param1DataServiceCallback != null)
        param1DataServiceCallback.onSetupDataCallComplete(1, null); 
    }
  }
  
  private static final class DeactivateDataCallRequest {
    public final IDataServiceCallback callback;
    
    public final int cid;
    
    public final int reason;
    
    DeactivateDataCallRequest(int param1Int1, int param1Int2, IDataServiceCallback param1IDataServiceCallback) {
      this.cid = param1Int1;
      this.reason = param1Int2;
      this.callback = param1IDataServiceCallback;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DeactivateDataReason {}
  
  private class IDataServiceWrapper extends IDataService.Stub {
    private IDataServiceWrapper() {}
    
    public void createDataServiceProvider(int param1Int) {
      DataService.this.mHandler.obtainMessage(1, param1Int, 0).sendToTarget();
    }
    
    public void deactivateDataCall(int param1Int1, int param1Int2, int param1Int3, IDataServiceCallback param1IDataServiceCallback) {
      DataService.this.mHandler.obtainMessage(5, param1Int1, 0, new DataService.DeactivateDataCallRequest(param1Int2, param1Int3, param1IDataServiceCallback)).sendToTarget();
    }
    
    public void registerForDataCallListChanged(int param1Int, IDataServiceCallback param1IDataServiceCallback) {
      if (param1IDataServiceCallback == null) {
        DataService.this.loge("registerForDataCallListChanged: callback is null");
        return;
      } 
      DataService.this.mHandler.obtainMessage(9, param1Int, 0, param1IDataServiceCallback).sendToTarget();
    }
    
    public void removeDataServiceProvider(int param1Int) {
      DataService.this.mHandler.obtainMessage(2, param1Int, 0).sendToTarget();
    }
    
    public void requestDataCallList(int param1Int, IDataServiceCallback param1IDataServiceCallback) {
      if (param1IDataServiceCallback == null) {
        DataService.this.loge("requestDataCallList: callback is null");
        return;
      } 
      DataService.this.mHandler.obtainMessage(8, param1Int, 0, param1IDataServiceCallback).sendToTarget();
    }
    
    public void setDataProfile(int param1Int, List<DataProfile> param1List, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) {
      DataService.this.mHandler.obtainMessage(7, param1Int, 0, new DataService.SetDataProfileRequest(param1List, param1Boolean, param1IDataServiceCallback)).sendToTarget();
    }
    
    public void setInitialAttachApn(int param1Int, DataProfile param1DataProfile, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) {
      DataService.this.mHandler.obtainMessage(6, param1Int, 0, new DataService.SetInitialAttachApnRequest(param1DataProfile, param1Boolean, param1IDataServiceCallback)).sendToTarget();
    }
    
    public void setupDataCall(int param1Int1, int param1Int2, DataProfile param1DataProfile, boolean param1Boolean1, boolean param1Boolean2, int param1Int3, LinkProperties param1LinkProperties, IDataServiceCallback param1IDataServiceCallback) {
      DataService.this.mHandler.obtainMessage(4, param1Int1, 0, new DataService.SetupDataCallRequest(param1Int2, param1DataProfile, param1Boolean1, param1Boolean2, param1Int3, param1LinkProperties, param1IDataServiceCallback)).sendToTarget();
    }
    
    public void unregisterForDataCallListChanged(int param1Int, IDataServiceCallback param1IDataServiceCallback) {
      if (param1IDataServiceCallback == null) {
        DataService.this.loge("unregisterForDataCallListChanged: callback is null");
        return;
      } 
      DataService.this.mHandler.obtainMessage(10, param1Int, 0, param1IDataServiceCallback).sendToTarget();
    }
  }
  
  private static final class SetDataProfileRequest {
    public final IDataServiceCallback callback;
    
    public final List<DataProfile> dps;
    
    public final boolean isRoaming;
    
    SetDataProfileRequest(List<DataProfile> param1List, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) {
      this.dps = param1List;
      this.isRoaming = param1Boolean;
      this.callback = param1IDataServiceCallback;
    }
  }
  
  private static final class SetInitialAttachApnRequest {
    public final IDataServiceCallback callback;
    
    public final DataProfile dataProfile;
    
    public final boolean isRoaming;
    
    SetInitialAttachApnRequest(DataProfile param1DataProfile, boolean param1Boolean, IDataServiceCallback param1IDataServiceCallback) {
      this.dataProfile = param1DataProfile;
      this.isRoaming = param1Boolean;
      this.callback = param1IDataServiceCallback;
    }
  }
  
  private static final class SetupDataCallRequest {
    public final int accessNetworkType;
    
    public final boolean allowRoaming;
    
    public final IDataServiceCallback callback;
    
    public final DataProfile dataProfile;
    
    public final boolean isRoaming;
    
    public final LinkProperties linkProperties;
    
    public final int reason;
    
    SetupDataCallRequest(int param1Int1, DataProfile param1DataProfile, boolean param1Boolean1, boolean param1Boolean2, int param1Int2, LinkProperties param1LinkProperties, IDataServiceCallback param1IDataServiceCallback) {
      this.accessNetworkType = param1Int1;
      this.dataProfile = param1DataProfile;
      this.isRoaming = param1Boolean1;
      this.allowRoaming = param1Boolean2;
      this.linkProperties = param1LinkProperties;
      this.reason = param1Int2;
      this.callback = param1IDataServiceCallback;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SetupDataReason {}
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */