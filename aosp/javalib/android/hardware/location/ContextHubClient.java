package android.hardware.location;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@SystemApi
public class ContextHubClient implements Closeable {
  private static final String TAG = "ContextHubClient";
  
  private final ContextHubInfo mAttachedHub;
  
  private IContextHubClient mClientProxy = null;
  
  private final CloseGuard mCloseGuard;
  
  private final AtomicBoolean mIsClosed = new AtomicBoolean(false);
  
  private final boolean mPersistent;
  
  ContextHubClient(ContextHubInfo paramContextHubInfo, boolean paramBoolean) {
    this.mAttachedHub = paramContextHubInfo;
    this.mPersistent = paramBoolean;
    if (paramBoolean) {
      this.mCloseGuard = null;
    } else {
      CloseGuard closeGuard = CloseGuard.get();
      this.mCloseGuard = closeGuard;
      closeGuard.open("close");
    } 
  }
  
  public void close() {
    if (!this.mIsClosed.getAndSet(true)) {
      CloseGuard closeGuard = this.mCloseGuard;
      if (closeGuard != null)
        closeGuard.close(); 
      try {
        this.mClientProxy.close();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      if (!this.mPersistent)
        close(); 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public ContextHubInfo getAttachedHub() {
    return this.mAttachedHub;
  }
  
  public int sendMessageToNanoApp(NanoAppMessage paramNanoAppMessage) {
    StringBuilder stringBuilder;
    Objects.requireNonNull(paramNanoAppMessage, "NanoAppMessage cannot be null");
    int i = this.mAttachedHub.getMaxPacketLengthBytes();
    byte[] arrayOfByte = paramNanoAppMessage.getMessageBody();
    if (arrayOfByte != null && arrayOfByte.length > i) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Message (");
      stringBuilder.append(arrayOfByte.length);
      stringBuilder.append(" bytes) exceeds max payload length (");
      stringBuilder.append(i);
      stringBuilder.append(" bytes)");
      Log.e("ContextHubClient", stringBuilder.toString());
      return 2;
    } 
    try {
      return this.mClientProxy.sendMessageToNanoApp((NanoAppMessage)stringBuilder);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  void setClientProxy(IContextHubClient paramIContextHubClient) {
    Objects.requireNonNull(paramIContextHubClient, "IContextHubClient cannot be null");
    if (this.mClientProxy == null) {
      this.mClientProxy = paramIContextHubClient;
      return;
    } 
    throw new IllegalStateException("Cannot change client proxy multiple times");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */