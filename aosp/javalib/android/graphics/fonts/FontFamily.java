package android.graphics.fonts;

import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.util.ArrayList;
import java.util.HashSet;
import libcore.util.NativeAllocationRegistry;

public final class FontFamily {
  private static final String TAG = "FontFamily";
  
  private final ArrayList<Font> mFonts;
  
  private final long mNativePtr;
  
  private FontFamily(ArrayList<Font> paramArrayList, long paramLong) {
    this.mFonts = paramArrayList;
    this.mNativePtr = paramLong;
  }
  
  public Font getFont(int paramInt) {
    return this.mFonts.get(paramInt);
  }
  
  public long getNativePtr() {
    return this.mNativePtr;
  }
  
  public int getSize() {
    return this.mFonts.size();
  }
  
  public static final class Builder {
    private static final NativeAllocationRegistry sFamilyRegistory = NativeAllocationRegistry.createMalloced(FontFamily.class.getClassLoader(), nGetReleaseNativeFamily());
    
    private final ArrayList<Font> mFonts = new ArrayList<>();
    
    private final HashSet<Integer> mStyleHashSet = new HashSet<>();
    
    public Builder(Font param1Font) {
      Preconditions.checkNotNull(param1Font, "font can not be null");
      this.mStyleHashSet.add(Integer.valueOf(makeStyleIdentifier(param1Font)));
      this.mFonts.add(param1Font);
    }
    
    private static int makeStyleIdentifier(Font param1Font) {
      return param1Font.getStyle().getWeight() | param1Font.getStyle().getSlant() << 16;
    }
    
    @CriticalNative
    private static native void nAddFont(long param1Long1, long param1Long2);
    
    private static native long nBuild(long param1Long, String param1String, int param1Int, boolean param1Boolean);
    
    @CriticalNative
    private static native long nGetReleaseNativeFamily();
    
    private static native long nInitBuilder();
    
    public Builder addFont(Font param1Font) {
      Preconditions.checkNotNull(param1Font, "font can not be null");
      if (this.mStyleHashSet.add(Integer.valueOf(makeStyleIdentifier(param1Font)))) {
        this.mFonts.add(param1Font);
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1Font);
      stringBuilder.append(" has already been added");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public FontFamily build() {
      return build("", 0, true);
    }
    
    public FontFamily build(String param1String, int param1Int, boolean param1Boolean) {
      long l = nInitBuilder();
      for (byte b = 0; b < this.mFonts.size(); b++)
        nAddFont(l, ((Font)this.mFonts.get(b)).getNativePtr()); 
      l = nBuild(l, param1String, param1Int, param1Boolean);
      FontFamily fontFamily = new FontFamily(this.mFonts, l);
      sFamilyRegistory.registerNativeAllocation(fontFamily, l);
      return fontFamily;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/FontFamily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */