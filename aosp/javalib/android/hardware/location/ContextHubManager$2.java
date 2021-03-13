package android.hardware.location;

import android.util.Log;
import java.util.List;

class null extends IContextHubTransactionCallback.Stub {
  public void onQueryResponse(int paramInt, List<NanoAppState> paramList) {
    transaction.setResponse(new ContextHubTransaction.Response<>(paramInt, paramList));
  }
  
  public void onTransactionComplete(int paramInt) {
    Log.e("ContextHubManager", "Received a non-query callback on a query request");
    transaction.setResponse(new ContextHubTransaction.Response(7, null));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */