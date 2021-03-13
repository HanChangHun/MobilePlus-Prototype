package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BurstHolder {
  private static final String TAG = "BurstHolder";
  
  private final boolean mRepeating;
  
  private final ArrayList<RequestHolder.Builder> mRequestBuilders = new ArrayList<>();
  
  private final int mRequestId;
  
  public BurstHolder(int paramInt, boolean paramBoolean, CaptureRequest[] paramArrayOfCaptureRequest, Collection<Long> paramCollection) {
    byte b1 = 0;
    int i = paramArrayOfCaptureRequest.length;
    for (byte b2 = 0; b2 < i; b2++) {
      CaptureRequest captureRequest = paramArrayOfCaptureRequest[b2];
      this.mRequestBuilders.add(new RequestHolder.Builder(paramInt, b1, captureRequest, paramBoolean, paramCollection));
      b1++;
    } 
    this.mRepeating = paramBoolean;
    this.mRequestId = paramInt;
  }
  
  public int getNumberOfRequests() {
    return this.mRequestBuilders.size();
  }
  
  public int getRequestId() {
    return this.mRequestId;
  }
  
  public boolean isRepeating() {
    return this.mRepeating;
  }
  
  public List<RequestHolder> produceRequestHolders(long paramLong) {
    ArrayList<RequestHolder> arrayList = new ArrayList();
    byte b = 0;
    Iterator<RequestHolder.Builder> iterator = this.mRequestBuilders.iterator();
    while (iterator.hasNext()) {
      arrayList.add(((RequestHolder.Builder)iterator.next()).build(b + paramLong));
      b++;
    } 
    return arrayList;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/BurstHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */