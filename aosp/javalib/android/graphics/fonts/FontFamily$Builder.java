package android.graphics.fonts;

import com.android.internal.util.Preconditions;
import dalvik.annotation.optimization.CriticalNative;
import java.util.ArrayList;
import java.util.HashSet;
import libcore.util.NativeAllocationRegistry;

public final class Builder {
  private static final NativeAllocationRegistry sFamilyRegistory = NativeAllocationRegistry.createMalloced(FontFamily.class.getClassLoader(), nGetReleaseNativeFamily());
  
  private final ArrayList<Font> mFonts = new ArrayList<>();
  
  private final HashSet<Integer> mStyleHashSet = new HashSet<>();
  
  public Builder(Font paramFont) {
    Preconditions.checkNotNull(paramFont, "font can not be null");
    this.mStyleHashSet.add(Integer.valueOf(makeStyleIdentifier(paramFont)));
    this.mFonts.add(paramFont);
  }
  
  private static int makeStyleIdentifier(Font paramFont) {
    return paramFont.getStyle().getWeight() | paramFont.getStyle().getSlant() << 16;
  }
  
  @CriticalNative
  private static native void nAddFont(long paramLong1, long paramLong2);
  
  private static native long nBuild(long paramLong, String paramString, int paramInt, boolean paramBoolean);
  
  @CriticalNative
  private static native long nGetReleaseNativeFamily();
  
  private static native long nInitBuilder();
  
  public Builder addFont(Font paramFont) {
    Preconditions.checkNotNull(paramFont, "font can not be null");
    if (this.mStyleHashSet.add(Integer.valueOf(makeStyleIdentifier(paramFont)))) {
      this.mFonts.add(paramFont);
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramFont);
    stringBuilder.append(" has already been added");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public FontFamily build() {
    return build("", 0, true);
  }
  
  public FontFamily build(String paramString, int paramInt, boolean paramBoolean) {
    long l = nInitBuilder();
    for (byte b = 0; b < this.mFonts.size(); b++)
      nAddFont(l, ((Font)this.mFonts.get(b)).getNativePtr()); 
    l = nBuild(l, paramString, paramInt, paramBoolean);
    FontFamily fontFamily = new FontFamily(this.mFonts, l, null);
    sFamilyRegistory.registerNativeAllocation(fontFamily, l);
    return fontFamily;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/fonts/FontFamily$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */