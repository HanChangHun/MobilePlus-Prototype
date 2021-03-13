package android.content.pm;

import android.annotation.SystemApi;
import android.content.IntentSender;
import android.os.FileBridge;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.RemoteException;
import android.system.ErrnoException;
import android.system.Os;
import android.util.ExceptionUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class Session implements Closeable {
  protected final IPackageInstallerSession mSession;
  
  public Session(IPackageInstallerSession paramIPackageInstallerSession) {
    this.mSession = paramIPackageInstallerSession;
  }
  
  public void abandon() {
    try {
      this.mSession.abandon();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void addChildSessionId(int paramInt) {
    try {
      this.mSession.addChildSessionId(paramInt);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void addFile(int paramInt, String paramString, long paramLong, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    try {
      this.mSession.addFile(paramInt, paramString, paramLong, paramArrayOfbyte1, paramArrayOfbyte2);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void addProgress(float paramFloat) {
    try {
      this.mSession.addClientProgress(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void close() {
    try {
      this.mSession.close();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void commit(IntentSender paramIntentSender) {
    try {
      this.mSession.commit(paramIntentSender, false);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void commitTransferred(IntentSender paramIntentSender) {
    try {
      this.mSession.commit(paramIntentSender, true);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void fsync(OutputStream paramOutputStream) throws IOException {
    if (PackageInstaller.ENABLE_REVOCABLE_FD) {
      if (paramOutputStream instanceof ParcelFileDescriptor.AutoCloseOutputStream) {
        try {
          Os.fsync(((ParcelFileDescriptor.AutoCloseOutputStream)paramOutputStream).getFD());
        } catch (ErrnoException errnoException) {
          throw errnoException.rethrowAsIOException();
        } 
      } else {
        throw new IllegalArgumentException("Unrecognized stream");
      } 
    } else {
      if (errnoException instanceof FileBridge.FileBridgeOutputStream) {
        ((FileBridge.FileBridgeOutputStream)errnoException).fsync();
        return;
      } 
      throw new IllegalArgumentException("Unrecognized stream");
    } 
  }
  
  public int[] getChildSessionIds() {
    try {
      return this.mSession.getChildSessionIds();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public DataLoaderParams getDataLoaderParams() {
    try {
      DataLoaderParamsParcel dataLoaderParamsParcel = this.mSession.getDataLoaderParams();
      return (dataLoaderParamsParcel == null) ? null : new DataLoaderParams(dataLoaderParamsParcel);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public String[] getNames() throws IOException {
    try {
      return this.mSession.getNames();
    } catch (RuntimeException runtimeException) {
      ExceptionUtils.maybeUnwrapIOException(runtimeException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getParentSessionId() {
    try {
      return this.mSession.getParentSessionId();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isMultiPackage() {
    try {
      return this.mSession.isMultiPackage();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isStaged() {
    try {
      return this.mSession.isStaged();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public InputStream openRead(String paramString) throws IOException {
    try {
      return (InputStream)new ParcelFileDescriptor.AutoCloseInputStream(this.mSession.openRead(paramString));
    } catch (RuntimeException runtimeException) {
      ExceptionUtils.maybeUnwrapIOException(runtimeException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public OutputStream openWrite(String paramString, long paramLong1, long paramLong2) throws IOException {
    try {
      return (OutputStream)(PackageInstaller.ENABLE_REVOCABLE_FD ? new ParcelFileDescriptor.AutoCloseOutputStream(this.mSession.openWrite(paramString, paramLong1, paramLong2)) : new FileBridge.FileBridgeOutputStream(this.mSession.openWrite(paramString, paramLong1, paramLong2)));
    } catch (RuntimeException runtimeException) {
      ExceptionUtils.maybeUnwrapIOException(runtimeException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeChildSessionId(int paramInt) {
    try {
      this.mSession.removeChildSessionId(paramInt);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void removeFile(int paramInt, String paramString) {
    try {
      this.mSession.removeFile(paramInt, paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeSplit(String paramString) throws IOException {
    try {
      this.mSession.removeSplit(paramString);
      return;
    } catch (RuntimeException runtimeException) {
      ExceptionUtils.maybeUnwrapIOException(runtimeException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void setProgress(float paramFloat) {
    setStagingProgress(paramFloat);
  }
  
  public void setStagingProgress(float paramFloat) {
    try {
      this.mSession.setClientProgress(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void transfer(String paramString) throws PackageManager.NameNotFoundException {
    Objects.requireNonNull(paramString);
    try {
      this.mSession.transfer(paramString);
      return;
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(PackageManager.NameNotFoundException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void write(String paramString, long paramLong1, long paramLong2, ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    try {
      this.mSession.write(paramString, paramLong1, paramLong2, paramParcelFileDescriptor);
      return;
    } catch (RuntimeException runtimeException) {
      ExceptionUtils.maybeUnwrapIOException(runtimeException);
      throw runtimeException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageInstaller$Session.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */