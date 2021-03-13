package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;

class CallbackRunnable implements Runnable {
  private Filter mFilter;
  
  private Frame mFrame;
  
  private FilterContext.OnFrameReceivedListener mListener;
  
  private Object mUserData;
  
  public CallbackRunnable(FilterContext.OnFrameReceivedListener paramOnFrameReceivedListener, Filter paramFilter, Frame paramFrame, Object paramObject) {
    this.mListener = paramOnFrameReceivedListener;
    this.mFilter = paramFilter;
    this.mFrame = paramFrame;
    this.mUserData = paramObject;
  }
  
  public void run() {
    this.mListener.onFrameReceived(this.mFilter, this.mFrame, this.mUserData);
    this.mFrame.release();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/base/CallbackFilter$CallbackRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */