package android.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import android.view.WindowManagerImpl;
import java.lang.ref.Reference;

public class WindowContext extends ContextWrapper {
  private boolean mOwnsToken;
  
  private final WindowTokenClient mToken;
  
  private final WindowManagerImpl mWindowManager;
  
  private final IWindowManager mWms = WindowManagerGlobal.getWindowManagerService();
  
  public WindowContext(Context paramContext, int paramInt, Bundle paramBundle) {
    super(null);
    WindowTokenClient windowTokenClient = new WindowTokenClient();
    this.mToken = windowTokenClient;
    paramContext = createBaseWindowContext(paramContext, (IBinder)windowTokenClient);
    attachBaseContext(paramContext);
    paramContext.setOuterContext((Context)this);
    this.mToken.attachContext(this);
    WindowManagerImpl windowManagerImpl = new WindowManagerImpl((Context)this);
    this.mWindowManager = windowManagerImpl;
    windowManagerImpl.setDefaultToken((IBinder)this.mToken);
    boolean bool = false;
    try {
      paramInt = this.mWms.addWindowTokenWithOptions((IBinder)this.mToken, paramInt, getDisplayId(), paramBundle, getPackageName());
      if (paramInt != -12) {
        if (paramInt == 0)
          bool = true; 
        this.mOwnsToken = bool;
        Reference.reachabilityFence(this);
        return;
      } 
      throw new UnsupportedOperationException("createWindowContext failed! Too many unused window contexts. Please see Context#createWindowContext documentation for detail.");
    } catch (RemoteException remoteException) {
      this.mOwnsToken = false;
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  private static ContextImpl createBaseWindowContext(Context paramContext, IBinder paramIBinder) {
    return ContextImpl.getImpl(paramContext).createBaseWindowContext(paramIBinder);
  }
  
  void destroy() {
    ((ContextImpl)getBaseContext()).scheduleFinalCleanup(getClass().getName(), "WindowContext");
    Reference.reachabilityFence(this);
  }
  
  protected void finalize() throws Throwable {
    release();
    super.finalize();
  }
  
  public Object getSystemService(String paramString) {
    return "window".equals(paramString) ? this.mWindowManager : super.getSystemService(paramString);
  }
  
  public void release() {
    if (this.mOwnsToken)
      try {
        this.mWms.removeWindowToken((IBinder)this.mToken, getDisplayId());
        this.mOwnsToken = false;
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    destroy();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WindowContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */