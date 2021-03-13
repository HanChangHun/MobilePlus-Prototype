package android.graphics;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.system.ErrnoException;
import android.view.IGraphicsStatsCallback;
import java.io.IOException;
import java.nio.ByteBuffer;

final class ActiveBuffer implements IBinder.DeathRecipient {
  final IGraphicsStatsCallback mCallback;
  
  final GraphicsStatsService.BufferInfo mInfo;
  
  ByteBuffer mMapping;
  
  final int mPid;
  
  SharedMemory mProcessBuffer;
  
  final IBinder mToken;
  
  final int mUid;
  
  ActiveBuffer(IGraphicsStatsCallback paramIGraphicsStatsCallback, int paramInt1, int paramInt2, String paramString, long paramLong) throws RemoteException, IOException {
    this.mInfo = new GraphicsStatsService.BufferInfo(paramGraphicsStatsService, paramString, paramLong, System.currentTimeMillis());
    this.mUid = paramInt1;
    this.mPid = paramInt2;
    this.mCallback = paramIGraphicsStatsCallback;
    IBinder iBinder = paramIGraphicsStatsCallback.asBinder();
    this.mToken = iBinder;
    iBinder.linkToDeath(this, 0);
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("GFXStats-");
      stringBuilder.append(paramInt2);
      SharedMemory sharedMemory = SharedMemory.create(stringBuilder.toString(), GraphicsStatsService.access$200(paramGraphicsStatsService));
      this.mProcessBuffer = sharedMemory;
      this.mMapping = sharedMemory.mapReadWrite();
    } catch (ErrnoException errnoException) {
      errnoException.rethrowAsIOException();
    } 
    this.mMapping.position(0);
    this.mMapping.put(GraphicsStatsService.access$300(paramGraphicsStatsService), 0, GraphicsStatsService.access$200(paramGraphicsStatsService));
  }
  
  public void binderDied() {
    this.mToken.unlinkToDeath(this, 0);
    GraphicsStatsService.access$400(GraphicsStatsService.this, this);
  }
  
  void closeAllBuffers() {
    ByteBuffer byteBuffer = this.mMapping;
    if (byteBuffer != null) {
      SharedMemory.unmap(byteBuffer);
      this.mMapping = null;
    } 
    SharedMemory sharedMemory = this.mProcessBuffer;
    if (sharedMemory != null) {
      sharedMemory.close();
      this.mProcessBuffer = null;
    } 
  }
  
  ParcelFileDescriptor getPfd() {
    try {
      return this.mProcessBuffer.getFdDup();
    } catch (IOException iOException) {
      throw new IllegalStateException("Failed to get PFD from memory file", iOException);
    } 
  }
  
  void readBytes(byte[] paramArrayOfbyte, int paramInt) throws IOException {
    ByteBuffer byteBuffer = this.mMapping;
    if (byteBuffer != null) {
      byteBuffer.position(0);
      this.mMapping.get(paramArrayOfbyte, 0, paramInt);
      return;
    } 
    throw new IOException("SharedMemory has been deactivated");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicsStatsService$ActiveBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */