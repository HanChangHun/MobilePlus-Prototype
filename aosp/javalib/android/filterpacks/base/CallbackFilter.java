package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.GenerateFinalPort;
import android.os.Handler;
import android.os.Looper;

public class CallbackFilter extends Filter {
  @GenerateFinalPort(hasDefault = true, name = "callUiThread")
  private boolean mCallbacksOnUiThread = true;
  
  @GenerateFieldPort(hasDefault = true, name = "listener")
  private FilterContext.OnFrameReceivedListener mListener;
  
  private Handler mUiThreadHandler;
  
  @GenerateFieldPort(hasDefault = true, name = "userData")
  private Object mUserData;
  
  public CallbackFilter(String paramString) {
    super(paramString);
  }
  
  public void prepare(FilterContext paramFilterContext) {
    if (this.mCallbacksOnUiThread)
      this.mUiThreadHandler = new Handler(Looper.getMainLooper()); 
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = pullInput("frame");
    FilterContext.OnFrameReceivedListener onFrameReceivedListener = this.mListener;
    if (onFrameReceivedListener != null) {
      CallbackRunnable callbackRunnable;
      if (this.mCallbacksOnUiThread) {
        frame.retain();
        callbackRunnable = new CallbackRunnable(this.mListener, this, frame, this.mUserData);
        if (!this.mUiThreadHandler.post(callbackRunnable))
          throw new RuntimeException("Unable to send callback to UI thread!"); 
      } else {
        callbackRunnable.onFrameReceived(this, frame, this.mUserData);
      } 
      return;
    } 
    throw new RuntimeException("CallbackFilter received frame, but no listener set!");
  }
  
  public void setupPorts() {
    addInputPort("frame");
  }
  
  private class CallbackRunnable implements Runnable {
    private Filter mFilter;
    
    private Frame mFrame;
    
    private FilterContext.OnFrameReceivedListener mListener;
    
    private Object mUserData;
    
    public CallbackRunnable(FilterContext.OnFrameReceivedListener param1OnFrameReceivedListener, Filter param1Filter, Frame param1Frame, Object param1Object) {
      this.mListener = param1OnFrameReceivedListener;
      this.mFilter = param1Filter;
      this.mFrame = param1Frame;
      this.mUserData = param1Object;
    }
    
    public void run() {
      this.mListener.onFrameReceived(this.mFilter, this.mFrame, this.mUserData);
      this.mFrame.release();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/CallbackFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */