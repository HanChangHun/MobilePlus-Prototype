package android.content;

import android.util.Pair;

public final class CallingIdentity {
  public final long binderToken;
  
  public final Pair<String, String> callingPackage;
  
  public CallingIdentity(long paramLong, Pair<String, String> paramPair) {
    this.binderToken = paramLong;
    this.callingPackage = paramPair;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProvider$CallingIdentity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */