package android.telephony.data;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class QualifiedNetworksServiceHandler extends Handler {
  QualifiedNetworksServiceHandler(Looper paramLooper) {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage) {
    int i = paramMessage.arg1;
    QualifiedNetworksService.NetworkAvailabilityProvider networkAvailabilityProvider = (QualifiedNetworksService.NetworkAvailabilityProvider)QualifiedNetworksService.access$300(QualifiedNetworksService.this).get(i);
    int j = paramMessage.what;
    if (j != 1) {
      if (j != 2) {
        if (j != 3) {
          if (j == 4 && networkAvailabilityProvider != null)
            QualifiedNetworksService.NetworkAvailabilityProvider.access$500(networkAvailabilityProvider, paramMessage.arg2, (int[])paramMessage.obj); 
        } else {
          for (i = 0; i < QualifiedNetworksService.access$300(QualifiedNetworksService.this).size(); i++) {
            QualifiedNetworksService.NetworkAvailabilityProvider networkAvailabilityProvider1 = (QualifiedNetworksService.NetworkAvailabilityProvider)QualifiedNetworksService.access$300(QualifiedNetworksService.this).get(i);
            if (networkAvailabilityProvider1 != null)
              networkAvailabilityProvider1.close(); 
          } 
          QualifiedNetworksService.access$300(QualifiedNetworksService.this).clear();
        } 
      } else if (networkAvailabilityProvider != null) {
        networkAvailabilityProvider.close();
        QualifiedNetworksService.access$300(QualifiedNetworksService.this).remove(i);
      } 
    } else {
      QualifiedNetworksService qualifiedNetworksService;
      if (QualifiedNetworksService.access$300(QualifiedNetworksService.this).get(i) != null) {
        qualifiedNetworksService = QualifiedNetworksService.this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Network availability provider for slot ");
        stringBuilder.append(i);
        stringBuilder.append(" already existed.");
        QualifiedNetworksService.access$100(qualifiedNetworksService, stringBuilder.toString());
        return;
      } 
      networkAvailabilityProvider = QualifiedNetworksService.this.onCreateNetworkAvailabilityProvider(i);
      if (networkAvailabilityProvider != null) {
        QualifiedNetworksService.access$300(QualifiedNetworksService.this).put(i, networkAvailabilityProvider);
        QualifiedNetworksService.NetworkAvailabilityProvider.access$400(networkAvailabilityProvider, (IQualifiedNetworksServiceCallback)((Message)qualifiedNetworksService).obj);
      } else {
        qualifiedNetworksService = QualifiedNetworksService.this;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to create network availability provider. slot index = ");
        stringBuilder.append(i);
        QualifiedNetworksService.access$100(qualifiedNetworksService, stringBuilder.toString());
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/QualifiedNetworksService$QualifiedNetworksServiceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */