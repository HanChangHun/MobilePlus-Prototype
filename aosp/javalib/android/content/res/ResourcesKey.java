package android.content.res;

import android.content.res.loader.ResourcesLoader;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Objects;

public final class ResourcesKey {
  public final CompatibilityInfo mCompatInfo;
  
  public final int mDisplayId;
  
  private final int mHash;
  
  public final String[] mLibDirs;
  
  public final ResourcesLoader[] mLoaders;
  
  public final String[] mOverlayDirs;
  
  public final Configuration mOverrideConfiguration;
  
  public final String mResDir;
  
  public final String[] mSplitResDirs;
  
  public ResourcesKey(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo) {
    this(paramString, paramArrayOfString1, paramArrayOfString2, paramArrayOfString3, paramInt, paramConfiguration, paramCompatibilityInfo, null);
  }
  
  public ResourcesKey(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, ResourcesLoader[] paramArrayOfResourcesLoader) {
    this.mResDir = paramString;
    this.mSplitResDirs = paramArrayOfString1;
    this.mOverlayDirs = paramArrayOfString2;
    this.mLibDirs = paramArrayOfString3;
    if (paramArrayOfResourcesLoader != null && paramArrayOfResourcesLoader.length == 0)
      paramArrayOfResourcesLoader = null; 
    this.mLoaders = paramArrayOfResourcesLoader;
    this.mDisplayId = paramInt;
    if (paramConfiguration == null)
      paramConfiguration = Configuration.EMPTY; 
    this.mOverrideConfiguration = new Configuration(paramConfiguration);
    if (paramCompatibilityInfo == null)
      paramCompatibilityInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO; 
    this.mCompatInfo = paramCompatibilityInfo;
    this.mHash = (((((((17 * 31 + Objects.hashCode(this.mResDir)) * 31 + Arrays.hashCode((Object[])this.mSplitResDirs)) * 31 + Arrays.hashCode((Object[])this.mOverlayDirs)) * 31 + Arrays.hashCode((Object[])this.mLibDirs)) * 31 + this.mDisplayId) * 31 + Objects.hashCode(this.mOverrideConfiguration)) * 31 + Objects.hashCode(this.mCompatInfo)) * 31 + Arrays.hashCode((Object[])this.mLoaders);
  }
  
  private static boolean anyStartsWith(String[] paramArrayOfString, String paramString) {
    if (paramArrayOfString != null) {
      int i = paramArrayOfString.length;
      for (byte b = 0; b < i; b++) {
        String str = paramArrayOfString[b];
        if (str != null && str.startsWith(paramString))
          return true; 
      } 
    } 
    return false;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof ResourcesKey))
      return false; 
    paramObject = paramObject;
    return (this.mHash != ((ResourcesKey)paramObject).mHash) ? false : (!Objects.equals(this.mResDir, ((ResourcesKey)paramObject).mResDir) ? false : (!Arrays.equals((Object[])this.mSplitResDirs, (Object[])((ResourcesKey)paramObject).mSplitResDirs) ? false : (!Arrays.equals((Object[])this.mOverlayDirs, (Object[])((ResourcesKey)paramObject).mOverlayDirs) ? false : (!Arrays.equals((Object[])this.mLibDirs, (Object[])((ResourcesKey)paramObject).mLibDirs) ? false : ((this.mDisplayId != ((ResourcesKey)paramObject).mDisplayId) ? false : (!Objects.equals(this.mOverrideConfiguration, ((ResourcesKey)paramObject).mOverrideConfiguration) ? false : (!Objects.equals(this.mCompatInfo, ((ResourcesKey)paramObject).mCompatInfo) ? false : (!!Arrays.equals((Object[])this.mLoaders, (Object[])((ResourcesKey)paramObject).mLoaders)))))))));
  }
  
  public boolean hasOverrideConfiguration() {
    return Configuration.EMPTY.equals(this.mOverrideConfiguration) ^ true;
  }
  
  public int hashCode() {
    return this.mHash;
  }
  
  public boolean isPathReferenced(String paramString) {
    String str = this.mResDir;
    boolean bool = true;
    if (str != null && str.startsWith(paramString))
      return true; 
    if (!anyStartsWith(this.mSplitResDirs, paramString) && !anyStartsWith(this.mOverlayDirs, paramString) && !anyStartsWith(this.mLibDirs, paramString))
      bool = false; 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = (new StringBuilder()).append("ResourcesKey{");
    stringBuilder.append(" mHash=");
    stringBuilder.append(Integer.toHexString(this.mHash));
    stringBuilder.append(" mResDir=");
    stringBuilder.append(this.mResDir);
    stringBuilder.append(" mSplitDirs=[");
    String[] arrayOfString = this.mSplitResDirs;
    if (arrayOfString != null)
      stringBuilder.append(TextUtils.join(",", (Object[])arrayOfString)); 
    stringBuilder.append("]");
    stringBuilder.append(" mOverlayDirs=[");
    arrayOfString = this.mOverlayDirs;
    if (arrayOfString != null)
      stringBuilder.append(TextUtils.join(",", (Object[])arrayOfString)); 
    stringBuilder.append("]");
    stringBuilder.append(" mLibDirs=[");
    arrayOfString = this.mLibDirs;
    if (arrayOfString != null)
      stringBuilder.append(TextUtils.join(",", (Object[])arrayOfString)); 
    stringBuilder.append("]");
    stringBuilder.append(" mDisplayId=");
    stringBuilder.append(this.mDisplayId);
    stringBuilder.append(" mOverrideConfig=");
    stringBuilder.append(Configuration.resourceQualifierString(this.mOverrideConfiguration));
    stringBuilder.append(" mCompatInfo=");
    stringBuilder.append(this.mCompatInfo);
    stringBuilder.append(" mLoaders=[");
    ResourcesLoader[] arrayOfResourcesLoader = this.mLoaders;
    if (arrayOfResourcesLoader != null)
      stringBuilder.append(TextUtils.join(",", (Object[])arrayOfResourcesLoader)); 
    stringBuilder.append("]}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ResourcesKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */