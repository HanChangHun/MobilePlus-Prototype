package android.content.res;

public final class FontFamilyFilesResourceEntry implements FontResourcesParser.FamilyResourceEntry {
  private final FontResourcesParser.FontFileResourceEntry[] mEntries;
  
  public FontFamilyFilesResourceEntry(FontResourcesParser.FontFileResourceEntry[] paramArrayOfFontFileResourceEntry) {
    this.mEntries = paramArrayOfFontFileResourceEntry;
  }
  
  public FontResourcesParser.FontFileResourceEntry[] getEntries() {
    return this.mEntries;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/FontResourcesParser$FontFamilyFilesResourceEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */