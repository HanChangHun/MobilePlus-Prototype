package android.app.blob;

import android.content.Context;
import android.os.Bundle;
import android.os.LimitExceededException;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.UserHandle;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BlobStoreManager {
  public static final int COMMIT_RESULT_ERROR = 1;
  
  public static final int COMMIT_RESULT_SUCCESS = 0;
  
  public static final int INVALID_RES_ID = -1;
  
  private final Context mContext;
  
  private final IBlobStoreManager mService;
  
  public BlobStoreManager(Context paramContext, IBlobStoreManager paramIBlobStoreManager) {
    this.mContext = paramContext;
    this.mService = paramIBlobStoreManager;
  }
  
  public void abandonSession(long paramLong) throws IOException {
    try {
      this.mService.abandonSession(paramLong, this.mContext.getOpPackageName());
      return;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void acquireLease(BlobHandle paramBlobHandle, int paramInt) throws IOException {
    acquireLease(paramBlobHandle, paramInt, 0L);
  }
  
  public void acquireLease(BlobHandle paramBlobHandle, int paramInt, long paramLong) throws IOException {
    try {
      this.mService.acquireLease(paramBlobHandle, paramInt, null, paramLong, this.mContext.getOpPackageName());
      return;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      parcelableException.maybeRethrow(LimitExceededException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void acquireLease(BlobHandle paramBlobHandle, CharSequence paramCharSequence) throws IOException {
    acquireLease(paramBlobHandle, paramCharSequence, 0L);
  }
  
  public void acquireLease(BlobHandle paramBlobHandle, CharSequence paramCharSequence, long paramLong) throws IOException {
    try {
      this.mService.acquireLease(paramBlobHandle, -1, paramCharSequence, paramLong, this.mContext.getOpPackageName());
      return;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      parcelableException.maybeRethrow(LimitExceededException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long createSession(BlobHandle paramBlobHandle) throws IOException {
    try {
      return this.mService.createSession(paramBlobHandle, this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      parcelableException.maybeRethrow(LimitExceededException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void deleteBlob(BlobInfo paramBlobInfo) throws IOException {
    try {
      this.mService.deleteBlob(paramBlobInfo.getId());
      return;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public LeaseInfo getLeaseInfo(BlobHandle paramBlobHandle) throws IOException {
    try {
      return this.mService.getLeaseInfo(paramBlobHandle, this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<BlobHandle> getLeasedBlobs() throws IOException {
    try {
      return this.mService.getLeasedBlobs(this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getRemainingLeaseQuotaBytes() {
    try {
      return this.mService.getRemainingLeaseQuotaBytes(this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ParcelFileDescriptor openBlob(BlobHandle paramBlobHandle) throws IOException {
    try {
      return this.mService.openBlob(paramBlobHandle, this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Session openSession(long paramLong) throws IOException {
    try {
      return new Session(this.mService.openSession(paramLong, this.mContext.getOpPackageName()));
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<BlobInfo> queryBlobsForUser(UserHandle paramUserHandle) throws IOException {
    try {
      return this.mService.queryBlobsForUser(paramUserHandle.getIdentifier());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void releaseLease(BlobHandle paramBlobHandle) throws IOException {
    try {
      this.mService.releaseLease(paramBlobHandle, this.mContext.getOpPackageName());
      return;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void waitForIdle(long paramLong) throws InterruptedException, TimeoutException {
    try {
      CountDownLatch countDownLatch = new CountDownLatch();
      this(1);
      IBlobStoreManager iBlobStoreManager = this.mService;
      RemoteCallback remoteCallback = new RemoteCallback();
      _$$Lambda$BlobStoreManager$WokCX7JCH8Z_5Nui6QQuoLrjMog _$$Lambda$BlobStoreManager$WokCX7JCH8Z_5Nui6QQuoLrjMog = new _$$Lambda$BlobStoreManager$WokCX7JCH8Z_5Nui6QQuoLrjMog();
      this(countDownLatch);
      this(_$$Lambda$BlobStoreManager$WokCX7JCH8Z_5Nui6QQuoLrjMog);
      iBlobStoreManager.waitForIdle(remoteCallback);
      if (countDownLatch.await(paramLong, TimeUnit.MILLISECONDS))
        return; 
      TimeoutException timeoutException = new TimeoutException();
      this("Timed out waiting for service to become idle");
      throw timeoutException;
    } catch (ParcelableException parcelableException) {
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public static class Session implements Closeable {
    private final IBlobStoreSession mSession;
    
    private Session(IBlobStoreSession param1IBlobStoreSession) {
      this.mSession = param1IBlobStoreSession;
    }
    
    public void abandon() throws IOException {
      try {
        this.mSession.abandon();
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void allowPackageAccess(String param1String, byte[] param1ArrayOfbyte) throws IOException {
      try {
        this.mSession.allowPackageAccess(param1String, param1ArrayOfbyte);
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        parcelableException.maybeRethrow(LimitExceededException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void allowPublicAccess() throws IOException {
      try {
        this.mSession.allowPublicAccess();
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void allowSameSignatureAccess() throws IOException {
      try {
        this.mSession.allowSameSignatureAccess();
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void close() throws IOException {
      try {
        this.mSession.close();
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public void commit(Executor param1Executor, Consumer<Integer> param1Consumer) throws IOException {
      try {
        IBlobStoreSession iBlobStoreSession = this.mSession;
        IBlobCommitCallback.Stub stub = new IBlobCommitCallback.Stub() {
            public void onResult(int param2Int) {
              executor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$2oD6p7V9cUAK7HNu017eF9iL6ZI.INSTANCE, resultCallback, Integer.valueOf(param2Int)));
            }
          };
        super(this, param1Executor, param1Consumer);
        iBlobStoreSession.commit(stub);
        return;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public long getSize() throws IOException {
      try {
        return this.mSession.getSize();
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public boolean isPackageAccessAllowed(String param1String, byte[] param1ArrayOfbyte) throws IOException {
      try {
        return this.mSession.isPackageAccessAllowed(param1String, param1ArrayOfbyte);
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public boolean isPublicAccessAllowed() throws IOException {
      try {
        return this.mSession.isPublicAccessAllowed();
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public boolean isSameSignatureAccessAllowed() throws IOException {
      try {
        return this.mSession.isSameSignatureAccessAllowed();
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public ParcelFileDescriptor openRead() throws IOException {
      try {
        return this.mSession.openRead();
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
    
    public ParcelFileDescriptor openWrite(long param1Long1, long param1Long2) throws IOException {
      try {
        ParcelFileDescriptor parcelFileDescriptor = this.mSession.openWrite(param1Long1, param1Long2);
        parcelFileDescriptor.seekTo(param1Long1);
        return parcelFileDescriptor;
      } catch (ParcelableException parcelableException) {
        parcelableException.maybeRethrow(IOException.class);
        throw new RuntimeException(parcelableException);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    }
  }
  
  class null extends IBlobCommitCallback.Stub {
    public void onResult(int param1Int) {
      executor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$2oD6p7V9cUAK7HNu017eF9iL6ZI.INSTANCE, resultCallback, Integer.valueOf(param1Int)));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobStoreManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */