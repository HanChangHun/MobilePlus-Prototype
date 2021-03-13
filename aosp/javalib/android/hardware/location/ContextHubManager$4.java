package android.hardware.location;

import android.os.Handler;

class null extends IContextHubCallback.Stub {
  public void onMessageReceipt(int paramInt1, int paramInt2, ContextHubMessage paramContextHubMessage) {
    synchronized (ContextHubManager.this) {
      if (ContextHubManager.access$000(ContextHubManager.this) != null) {
        Handler handler = ContextHubManager.access$100(ContextHubManager.this);
        _$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI _$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI = new _$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI();
        this(this, paramInt1, paramInt2, paramContextHubMessage);
        handler.post(_$$Lambda$ContextHubManager$4$sylEfC1Rx_cxuQRnKuthZXmV8KI);
      } else if (ContextHubManager.access$200(ContextHubManager.this) != null) {
        ContextHubManager.access$200(ContextHubManager.this).onMessageReceipt(paramInt1, paramInt2, paramContextHubMessage);
      } 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */