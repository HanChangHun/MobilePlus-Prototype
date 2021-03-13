package android.content.res;

public final class FontFileResourceEntry {
  public static final int ITALIC = 1;
  
  public static final int RESOLVE_BY_FONT_TABLE = -1;
  
  public static final int UPRIGHT = 0;
  
  private final String mFileName;
  
  private int mItalic;
  
  private int mResourceId;
  
  private int mTtcIndex;
  
  private String mVariationSettings;
  
  private int mWeight;
  
  public FontFileResourceEntry(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3) {
    this.mFileName = paramString1;
    this.mWeight = paramInt1;
    this.mItalic = paramInt2;
    this.mVariationSettings = paramString2;
    this.mTtcIndex = paramInt3;
  }
  
  public String getFileName() {
    return this.mFileName;
  }
  
  public int getItalic() {
    return this.mItalic;
  }
  
  public int getTtcIndex() {
    return this.mTtcIndex;
  }
  
  public String getVariationSettings() {
    return this.mVariationSettings;
  }
  
  public int getWeight() {
    return this.mWeight;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/FontResourcesParser$FontFileResourceEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */