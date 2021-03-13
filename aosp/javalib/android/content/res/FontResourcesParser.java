package android.content.res;

import android.util.Log;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontResourcesParser {
  private static final String TAG = "FontResourcesParser";
  
  public static FamilyResourceEntry parse(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    int i;
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i == 2)
      return readFamilies(paramXmlPullParser, paramResources); 
    throw new XmlPullParserException("No start tag found");
  }
  
  private static FamilyResourceEntry readFamilies(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    paramXmlPullParser.require(2, null, "font-family");
    if (paramXmlPullParser.getName().equals("font-family"))
      return readFamily(paramXmlPullParser, paramResources); 
    skip(paramXmlPullParser);
    Log.e("FontResourcesParser", "Failed to find font-family tag");
    return null;
  }
  
  private static FamilyResourceEntry readFamily(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    ArrayList<List<String>> arrayList;
    TypedArray typedArray = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.FontFamily);
    String str1 = typedArray.getString(0);
    String str2 = typedArray.getString(2);
    byte b = 1;
    String str3 = typedArray.getString(1);
    int i = typedArray.getResourceId(3, 0);
    typedArray.recycle();
    if (str1 != null && str2 != null && str3 != null) {
      while (paramXmlPullParser.next() != 3)
        skip(paramXmlPullParser); 
      typedArray = null;
      TypedArray typedArray1 = typedArray;
      if (i != 0) {
        TypedArray typedArray2 = paramResources.obtainTypedArray(i);
        typedArray1 = typedArray;
        if (typedArray2.length() > 0) {
          arrayList = new ArrayList();
          if (typedArray2.getResourceId(0, 0) == 0)
            b = 0; 
          if (b) {
            for (b = 0; b < typedArray2.length(); b++)
              arrayList.add(Arrays.asList(paramResources.getStringArray(typedArray2.getResourceId(b, 0)))); 
          } else {
            arrayList.add(Arrays.asList(paramResources.getStringArray(i)));
          } 
        } 
      } 
      return new ProviderResourceEntry(str1, str2, str3, arrayList);
    } 
    ArrayList<FontFileResourceEntry> arrayList1 = new ArrayList();
    while (arrayList.next() != 3) {
      if (arrayList.getEventType() != 2)
        continue; 
      if (arrayList.getName().equals("font")) {
        FontFileResourceEntry fontFileResourceEntry = readFont((XmlPullParser)arrayList, paramResources);
        if (fontFileResourceEntry != null)
          arrayList1.add(fontFileResourceEntry); 
        continue;
      } 
      skip((XmlPullParser)arrayList);
    } 
    return arrayList1.isEmpty() ? null : new FontFamilyFilesResourceEntry(arrayList1.<FontFileResourceEntry>toArray(new FontFileResourceEntry[arrayList1.size()]));
  }
  
  private static FontFileResourceEntry readFont(XmlPullParser paramXmlPullParser, Resources paramResources) throws XmlPullParserException, IOException {
    TypedArray typedArray = paramResources.obtainAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.FontFamilyFont);
    int i = typedArray.getInt(1, -1);
    int j = typedArray.getInt(2, -1);
    String str2 = typedArray.getString(4);
    int k = typedArray.getInt(3, 0);
    String str1 = typedArray.getString(0);
    typedArray.recycle();
    while (paramXmlPullParser.next() != 3)
      skip(paramXmlPullParser); 
    return (str1 == null) ? null : new FontFileResourceEntry(str1, i, j, str2, k);
  }
  
  private static void skip(XmlPullParser paramXmlPullParser) throws XmlPullParserException, IOException {
    for (byte b = 1; b; b++) {
      int i = paramXmlPullParser.next();
      if (i != 2) {
        if (i != 3)
          continue; 
        b--;
        continue;
      } 
    } 
  }
  
  public static interface FamilyResourceEntry {}
  
  public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
    private final FontResourcesParser.FontFileResourceEntry[] mEntries;
    
    public FontFamilyFilesResourceEntry(FontResourcesParser.FontFileResourceEntry[] param1ArrayOfFontFileResourceEntry) {
      this.mEntries = param1ArrayOfFontFileResourceEntry;
    }
    
    public FontResourcesParser.FontFileResourceEntry[] getEntries() {
      return this.mEntries;
    }
  }
  
  public static final class FontFileResourceEntry {
    public static final int ITALIC = 1;
    
    public static final int RESOLVE_BY_FONT_TABLE = -1;
    
    public static final int UPRIGHT = 0;
    
    private final String mFileName;
    
    private int mItalic;
    
    private int mResourceId;
    
    private int mTtcIndex;
    
    private String mVariationSettings;
    
    private int mWeight;
    
    public FontFileResourceEntry(String param1String1, int param1Int1, int param1Int2, String param1String2, int param1Int3) {
      this.mFileName = param1String1;
      this.mWeight = param1Int1;
      this.mItalic = param1Int2;
      this.mVariationSettings = param1String2;
      this.mTtcIndex = param1Int3;
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
  
  public static final class ProviderResourceEntry implements FamilyResourceEntry {
    private final List<List<String>> mCerts;
    
    private final String mProviderAuthority;
    
    private final String mProviderPackage;
    
    private final String mQuery;
    
    public ProviderResourceEntry(String param1String1, String param1String2, String param1String3, List<List<String>> param1List) {
      this.mProviderAuthority = param1String1;
      this.mProviderPackage = param1String2;
      this.mQuery = param1String3;
      this.mCerts = param1List;
    }
    
    public String getAuthority() {
      return this.mProviderAuthority;
    }
    
    public List<List<String>> getCerts() {
      return this.mCerts;
    }
    
    public String getPackage() {
      return this.mProviderPackage;
    }
    
    public String getQuery() {
      return this.mQuery;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/FontResourcesParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */