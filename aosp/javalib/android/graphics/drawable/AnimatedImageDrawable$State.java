package android.graphics.drawable;

import android.content.res.AssetFileDescriptor;
import java.io.InputStream;

class State {
  private final AssetFileDescriptor mAssetFd;
  
  boolean mAutoMirrored = false;
  
  private final InputStream mInputStream;
  
  final long mNativePtr;
  
  int mRepeatCount = -2;
  
  int[] mThemeAttrs = null;
  
  State(long paramLong, InputStream paramInputStream, AssetFileDescriptor paramAssetFileDescriptor) {
    this.mNativePtr = paramLong;
    this.mInputStream = paramInputStream;
    this.mAssetFd = paramAssetFileDescriptor;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedImageDrawable$State.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */