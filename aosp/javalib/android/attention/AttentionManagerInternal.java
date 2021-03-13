package android.attention;

public abstract class AttentionManagerInternal {
  public abstract void cancelAttentionCheck(AttentionCallbackInternal paramAttentionCallbackInternal);
  
  public abstract boolean checkAttention(long paramLong, AttentionCallbackInternal paramAttentionCallbackInternal);
  
  public abstract boolean isAttentionServiceSupported();
  
  public static abstract class AttentionCallbackInternal {
    public abstract void onFailure(int param1Int);
    
    public abstract void onSuccess(int param1Int, long param1Long);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/attention/AttentionManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */