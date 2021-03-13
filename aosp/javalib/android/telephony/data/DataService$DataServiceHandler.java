package android.telephony.data;

import android.net.LinkProperties;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import java.util.List;

class DataServiceHandler extends Handler {
  DataServiceHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder1;
    List<DataProfile> list1;
    DataService.DeactivateDataCallRequest deactivateDataCallRequest1;
    List<DataProfile> list2;
    int i = paramMessage.arg1;
    DataService.DataServiceProvider dataServiceProvider2 = (DataService.DataServiceProvider)DataService.access$200(DataService.this).get(i);
    int j = paramMessage.what;
    DataService dataService = null;
    DataService.DeactivateDataCallRequest deactivateDataCallRequest2 = null;
    StringBuilder stringBuilder2 = null;
    switch (j) {
      default:
        return;
      case 11:
        if (dataServiceProvider2 != null) {
          DataService.DataCallListChangedIndication dataCallListChangedIndication = (DataService.DataCallListChangedIndication)paramMessage.obj;
          try {
            dataCallListChangedIndication.callback.onDataCallListChanged(dataCallListChangedIndication.dataCallList);
          } catch (RemoteException remoteException) {
            dataService = DataService.this;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to call onDataCallListChanged. ");
            stringBuilder2.append(remoteException);
            DataService.access$500(dataService, stringBuilder2.toString());
          } 
        } 
      case 10:
        if (dataServiceProvider2 != null)
          DataService.DataServiceProvider.access$400(dataServiceProvider2, (IDataServiceCallback)((Message)remoteException).obj); 
      case 9:
        if (dataServiceProvider2 != null)
          DataService.DataServiceProvider.access$300(dataServiceProvider2, (IDataServiceCallback)((Message)remoteException).obj); 
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
        for (i = 0; i < DataService.access$200(DataService.this).size(); i++) {
          dataServiceProvider1 = (DataService.DataServiceProvider)DataService.access$200(DataService.this).get(i);
          if (dataServiceProvider1 != null)
            dataServiceProvider1.close(); 
        } 
        DataService.access$200(DataService.this).clear();
      case 2:
        if (dataServiceProvider2 != null) {
          dataServiceProvider2.close();
          DataService.access$200(DataService.this).remove(i);
        } 
      case 1:
        break;
    } 
    DataService.DataServiceProvider dataServiceProvider1 = DataService.this.onCreateDataServiceProvider(((Message)dataServiceProvider1).arg1);
    if (dataServiceProvider1 != null)
      DataService.access$200(DataService.this).put(i, dataServiceProvider1); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$DataServiceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */