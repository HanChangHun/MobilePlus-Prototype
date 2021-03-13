package android.content.res;

import java.util.List;

public final class ProviderResourceEntry implements FontResourcesParser.FamilyResourceEntry {
  private final List<List<String>> mCerts;
  
  private final String mProviderAuthority;
  
  private final String mProviderPackage;
  
  private final String mQuery;
  
  public ProviderResourceEntry(String paramString1, String paramString2, String paramString3, List<List<String>> paramList) {
    this.mProviderAuthority = paramString1;
    this.mProviderPackage = paramString2;
    this.mQuery = paramString3;
    this.mCerts = paramList;
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


/* Location:              /home/chun/Desktop/temp/!/android/content/res/FontResourcesParser$ProviderResourceEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */