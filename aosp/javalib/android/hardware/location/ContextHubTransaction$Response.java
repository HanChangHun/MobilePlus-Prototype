package android.hardware.location;

public class Response<R> {
  private R mContents;
  
  private int mResult;
  
  Response(int paramInt, R paramR) {
    this.mResult = paramInt;
    this.mContents = paramR;
  }
  
  public R getContents() {
    return this.mContents;
  }
  
  public int getResult() {
    return this.mResult;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubTransaction$Response.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */