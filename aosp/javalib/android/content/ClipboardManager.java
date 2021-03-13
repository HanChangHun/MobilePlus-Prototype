package android.content;

import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.ClipboardManager;
import java.util.ArrayList;
import java.util.Objects;

public class ClipboardManager extends ClipboardManager {
  private final Context mContext;
  
  private final Handler mHandler;
  
  private final ArrayList<OnPrimaryClipChangedListener> mPrimaryClipChangedListeners = new ArrayList<>();
  
  private final IOnPrimaryClipChangedListener.Stub mPrimaryClipChangedServiceListener = new IOnPrimaryClipChangedListener.Stub() {
      public void dispatchPrimaryClipChanged() {
        ClipboardManager.this.mHandler.post(new _$$Lambda$ClipboardManager$1$hQk8olbGAgUi4WWNG4ZuDZsM39s(this));
      }
    };
  
  private final IClipboard mService;
  
  public ClipboardManager(Context paramContext, Handler paramHandler) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mService = IClipboard.Stub.asInterface(ServiceManager.getServiceOrThrow("clipboard"));
  }
  
  public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener paramOnPrimaryClipChangedListener) {
    synchronized (this.mPrimaryClipChangedListeners) {
      boolean bool = this.mPrimaryClipChangedListeners.isEmpty();
      if (bool)
        try {
          this.mService.addPrimaryClipChangedListener(this.mPrimaryClipChangedServiceListener, this.mContext.getOpPackageName(), this.mContext.getUserId());
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      this.mPrimaryClipChangedListeners.add(remoteException);
      return;
    } 
  }
  
  public void clearPrimaryClip() {
    try {
      this.mService.clearPrimaryClip(this.mContext.getOpPackageName(), this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ClipData getPrimaryClip() {
    try {
      return this.mService.getPrimaryClip(this.mContext.getOpPackageName(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ClipDescription getPrimaryClipDescription() {
    try {
      return this.mService.getPrimaryClipDescription(this.mContext.getOpPackageName(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public CharSequence getText() {
    ClipData clipData = getPrimaryClip();
    return (clipData != null && clipData.getItemCount() > 0) ? clipData.getItemAt(0).coerceToText(this.mContext) : null;
  }
  
  public boolean hasPrimaryClip() {
    try {
      return this.mService.hasPrimaryClip(this.mContext.getOpPackageName(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public boolean hasText() {
    try {
      return this.mService.hasClipboardText(this.mContext.getOpPackageName(), this.mContext.getUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener paramOnPrimaryClipChangedListener) {
    synchronized (this.mPrimaryClipChangedListeners) {
      this.mPrimaryClipChangedListeners.remove(paramOnPrimaryClipChangedListener);
      boolean bool = this.mPrimaryClipChangedListeners.isEmpty();
      if (bool)
        try {
          this.mService.removePrimaryClipChangedListener(this.mPrimaryClipChangedServiceListener, this.mContext.getOpPackageName(), this.mContext.getUserId());
        } catch (RemoteException remoteException) {
          throw remoteException.rethrowFromSystemServer();
        }  
      return;
    } 
  }
  
  void reportPrimaryClipChanged() {
    synchronized (this.mPrimaryClipChangedListeners) {
      if (this.mPrimaryClipChangedListeners.size() <= 0)
        return; 
      Object[] arrayOfObject = this.mPrimaryClipChangedListeners.toArray();
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((OnPrimaryClipChangedListener)arrayOfObject[b]).onPrimaryClipChanged(); 
      return;
    } 
  }
  
  public void setPrimaryClip(ClipData paramClipData) {
    try {
      Objects.requireNonNull(paramClipData);
      paramClipData.prepareToLeaveProcess(true);
      this.mService.setPrimaryClip(paramClipData, this.mContext.getOpPackageName(), this.mContext.getUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public void setText(CharSequence paramCharSequence) {
    setPrimaryClip(ClipData.newPlainText(null, paramCharSequence));
  }
  
  public static interface OnPrimaryClipChangedListener {
    void onPrimaryClipChanged();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ClipboardManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */