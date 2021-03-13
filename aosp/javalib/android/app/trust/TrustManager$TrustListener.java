package android.app.trust;

public interface TrustListener {
  void onTrustChanged(boolean paramBoolean, int paramInt1, int paramInt2);
  
  void onTrustError(CharSequence paramCharSequence);
  
  void onTrustManagedChanged(boolean paramBoolean, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/TrustManager$TrustListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */