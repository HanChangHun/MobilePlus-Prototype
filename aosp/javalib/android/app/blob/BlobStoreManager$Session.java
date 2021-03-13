package android.app.blob;

import android.os.LimitExceededException;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.RemoteException;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Session implements Closeable {
  private final IBlobStoreSession mSession;
  
  private Session(IBlobStoreSession paramIBlobStoreSession) {
    this.mSession = paramIBlobStoreSession;
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
  
  public void allowPackageAccess(String paramString, byte[] paramArrayOfbyte) throws IOException {
    try {
      this.mSession.allowPackageAccess(paramString, paramArrayOfbyte);
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
  
  public void commit(Executor paramExecutor, Consumer<Integer> paramConsumer) throws IOException {
    try {
      IBlobStoreSession iBlobStoreSession = this.mSession;
      IBlobCommitCallback.Stub stub = new IBlobCommitCallback.Stub() {
          public void onResult(int param2Int) {
            executor.execute((Runnable)PooledLambda.obtainRunnable((BiConsumer)_$$Lambda$2oD6p7V9cUAK7HNu017eF9iL6ZI.INSTANCE, resultCallback, Integer.valueOf(param2Int)));
          }
        };
      super(this, paramExecutor, paramConsumer);
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
  
  public boolean isPackageAccessAllowed(String paramString, byte[] paramArrayOfbyte) throws IOException {
    try {
      return this.mSession.isPackageAccessAllowed(paramString, paramArrayOfbyte);
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
  
  public ParcelFileDescriptor openWrite(long paramLong1, long paramLong2) throws IOException {
    try {
      ParcelFileDescriptor parcelFileDescriptor = this.mSession.openWrite(paramLong1, paramLong2);
      parcelFileDescriptor.seekTo(paramLong1);
      return parcelFileDescriptor;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobStoreManager$Session.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */