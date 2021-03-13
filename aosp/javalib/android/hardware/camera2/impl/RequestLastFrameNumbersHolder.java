package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.SubmitInfo;
import java.util.List;

public class RequestLastFrameNumbersHolder {
  private boolean mInflightCompleted;
  
  private final long mLastRegularFrameNumber;
  
  private final long mLastReprocessFrameNumber;
  
  private final long mLastZslStillFrameNumber;
  
  private final int mRequestId;
  
  private boolean mSequenceCompleted;
  
  RequestLastFrameNumbersHolder(int paramInt, long paramLong, int[] paramArrayOfint) {
    long l1 = -1L;
    long l2 = -1L;
    if (paramArrayOfint != null) {
      if (paramLong >= (paramArrayOfint.length - 1)) {
        long l3;
        long l4;
        int i = paramArrayOfint.length - 1;
        while (true) {
          l3 = l1;
          l4 = l2;
          if (i >= 0) {
            if (paramArrayOfint[i] == 2 && l2 == -1L) {
              l4 = paramLong;
              l3 = l1;
            } else {
              l3 = l1;
              l4 = l2;
              if (paramArrayOfint[i] == 0) {
                l3 = l1;
                l4 = l2;
                if (l1 == -1L) {
                  l3 = paramLong;
                  l4 = l2;
                } 
              } 
            } 
            if (l4 != -1L && l3 != -1L)
              break; 
            paramLong--;
            i--;
            l1 = l3;
            l2 = l4;
            continue;
          } 
          break;
        } 
        this.mLastRegularFrameNumber = l3;
        this.mLastZslStillFrameNumber = l4;
        this.mLastReprocessFrameNumber = -1L;
        this.mRequestId = paramInt;
        this.mSequenceCompleted = false;
        this.mInflightCompleted = false;
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("lastFrameNumber: ");
      stringBuilder.append(paramLong);
      stringBuilder.append(" should be at least ");
      stringBuilder.append(paramArrayOfint.length - 1);
      stringBuilder.append(" for the number of requests in the list: ");
      stringBuilder.append(paramArrayOfint.length);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("repeatingRequest list must not be null");
  }
  
  public RequestLastFrameNumbersHolder(List<CaptureRequest> paramList, SubmitInfo paramSubmitInfo) {
    long l1 = -1L;
    long l2 = -1L;
    long l3 = -1L;
    long l4 = paramSubmitInfo.getLastFrameNumber();
    if (paramSubmitInfo.getLastFrameNumber() >= (paramList.size() - 1)) {
      int i = paramList.size() - 1;
      while (i >= 0) {
        long l5;
        long l6;
        long l7;
        int j = ((CaptureRequest)paramList.get(i)).getRequestType();
        if (j == 1 && l2 == -1L) {
          l5 = l4;
          l6 = l1;
          l7 = l3;
        } else if (j == 2 && l3 == -1L) {
          l7 = l4;
          l6 = l1;
          l5 = l2;
        } else {
          l6 = l1;
          l5 = l2;
          l7 = l3;
          if (j == 0) {
            l6 = l1;
            l5 = l2;
            l7 = l3;
            if (l1 == -1L) {
              l6 = l4;
              l7 = l3;
              l5 = l2;
            } 
          } 
        } 
        if (l5 != -1L && l7 != -1L && l6 != -1L) {
          l1 = l6;
          l2 = l5;
          l3 = l7;
          break;
        } 
        l4--;
        i--;
        l1 = l6;
        l2 = l5;
        l3 = l7;
      } 
      this.mLastRegularFrameNumber = l1;
      this.mLastReprocessFrameNumber = l2;
      this.mLastZslStillFrameNumber = l3;
      this.mRequestId = paramSubmitInfo.getRequestId();
      this.mSequenceCompleted = false;
      this.mInflightCompleted = false;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("lastFrameNumber: ");
    stringBuilder.append(paramSubmitInfo.getLastFrameNumber());
    stringBuilder.append(" should be at least ");
    stringBuilder.append(paramList.size() - 1);
    stringBuilder.append(" for the number of  requests in the list: ");
    stringBuilder.append(paramList.size());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public long getLastFrameNumber() {
    return Math.max(this.mLastZslStillFrameNumber, Math.max(this.mLastRegularFrameNumber, this.mLastReprocessFrameNumber));
  }
  
  public long getLastRegularFrameNumber() {
    return this.mLastRegularFrameNumber;
  }
  
  public long getLastReprocessFrameNumber() {
    return this.mLastReprocessFrameNumber;
  }
  
  public long getLastZslStillFrameNumber() {
    return this.mLastZslStillFrameNumber;
  }
  
  public int getRequestId() {
    return this.mRequestId;
  }
  
  public boolean isInflightCompleted() {
    return this.mInflightCompleted;
  }
  
  public boolean isSequenceCompleted() {
    return this.mSequenceCompleted;
  }
  
  public void markInflightCompleted() {
    this.mInflightCompleted = true;
  }
  
  public void markSequenceCompleted() {
    this.mSequenceCompleted = true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/RequestLastFrameNumbersHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */