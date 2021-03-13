package android.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.IBinder;
import android.view.WindowManagerGlobal;
import java.lang.ref.WeakReference;

public class WindowTokenClient extends IWindowToken.Stub {
  private WeakReference<WindowContext> mContextRef = null;
  
  private final ResourcesManager mResourcesManager = ResourcesManager.getInstance();
  
  void attachContext(WindowContext paramWindowContext) {
    if (this.mContextRef == null) {
      this.mContextRef = new WeakReference<>(paramWindowContext);
      ContextImpl contextImpl = ContextImpl.getImpl((Context)paramWindowContext);
      contextImpl.setResources(contextImpl.createWindowContextResources());
      return;
    } 
    throw new IllegalStateException("Context is already attached.");
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration, int paramInt) {
    boolean bool2;
    Context context = (Context)this.mContextRef.get();
    if (context == null)
      return; 
    int i = context.getDisplayId();
    boolean bool1 = true;
    if (paramInt != i) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (context.getResources().getConfiguration().diff(paramConfiguration) == 0)
      bool1 = false; 
    if (bool2 || bool1)
      this.mResourcesManager.updateResourcesForActivity((IBinder)this, paramConfiguration, paramInt, bool2); 
    if (bool2)
      context.updateDisplay(paramInt); 
  }
  
  public void onWindowTokenRemoved() {
    WindowContext windowContext = this.mContextRef.get();
    if (windowContext != null) {
      windowContext.destroy();
      this.mContextRef.clear();
    } 
    WindowManagerGlobal.getInstance().closeAll((IBinder)this, this.mContextRef.getClass().getName(), "WindowContext");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WindowTokenClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */