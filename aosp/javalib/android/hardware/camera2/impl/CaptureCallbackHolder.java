package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class CaptureCallbackHolder {
  private final CaptureCallback mCallback;
  
  private final Executor mExecutor;
  
  private final boolean mHasBatchedOutputs;
  
  private final boolean mRepeating;
  
  private final List<CaptureRequest> mRequestList;
  
  private final int mSessionId;
  
  CaptureCallbackHolder(CaptureCallback paramCaptureCallback, List<CaptureRequest> paramList, Executor paramExecutor, boolean paramBoolean, int paramInt) {
    if (paramCaptureCallback != null && paramExecutor != null) {
      this.mRepeating = paramBoolean;
      this.mExecutor = paramExecutor;
      this.mRequestList = new ArrayList<>(paramList);
      this.mCallback = paramCaptureCallback;
      this.mSessionId = paramInt;
      boolean bool = true;
      paramInt = 0;
      while (true) {
        paramBoolean = bool;
        if (paramInt < paramList.size()) {
          CaptureRequest captureRequest = paramList.get(paramInt);
          if (!captureRequest.isPartOfCRequestList()) {
            paramBoolean = false;
            break;
          } 
          if (paramInt == 0 && captureRequest.getTargets().size() != 2) {
            paramBoolean = false;
            break;
          } 
          paramInt++;
          continue;
        } 
        break;
      } 
      this.mHasBatchedOutputs = paramBoolean;
      return;
    } 
    throw new UnsupportedOperationException("Must have a valid handler and a valid callback");
  }
  
  public CaptureCallback getCallback() {
    return this.mCallback;
  }
  
  public Executor getExecutor() {
    return this.mExecutor;
  }
  
  public CaptureRequest getRequest() {
    return getRequest(0);
  }
  
  public CaptureRequest getRequest(int paramInt) {
    if (paramInt < this.mRequestList.size()) {
      if (paramInt >= 0)
        return this.mRequestList.get(paramInt); 
      throw new IllegalArgumentException(String.format("Requested subsequenceId %d is negative", new Object[] { Integer.valueOf(paramInt) }));
    } 
    throw new IllegalArgumentException(String.format("Requested subsequenceId %d is larger than request list size %d.", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(this.mRequestList.size()) }));
  }
  
  public int getRequestCount() {
    return this.mRequestList.size();
  }
  
  public int getSessionId() {
    return this.mSessionId;
  }
  
  public boolean hasBatchedOutputs() {
    return this.mHasBatchedOutputs;
  }
  
  public boolean isRepeating() {
    return this.mRepeating;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CaptureCallbackHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */