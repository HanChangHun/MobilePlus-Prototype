package android.content.pm;

import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackageLite {
  public final String baseCodePath;
  
  public final int baseRevisionCode;
  
  public final String codePath;
  
  public final String[] configForSplit;
  
  public final boolean coreApp;
  
  public final boolean debuggable;
  
  public final boolean extractNativeLibs;
  
  public final int installLocation;
  
  public final boolean[] isFeatureSplits;
  
  public final boolean isolatedSplits;
  
  public final boolean multiArch;
  
  public final String packageName;
  
  public final String[] splitCodePaths;
  
  public final String[] splitNames;
  
  public final int[] splitRevisionCodes;
  
  public final boolean use32bitAbi;
  
  public final String[] usesSplitNames;
  
  public final VerifierInfo[] verifiers;
  
  public final int versionCode;
  
  public final int versionCodeMajor;
  
  public PackageLite(String paramString, PackageParser.ApkLite paramApkLite, String[] paramArrayOfString1, boolean[] paramArrayOfboolean, String[] paramArrayOfString2, String[] paramArrayOfString3, String[] paramArrayOfString4, int[] paramArrayOfint) {
    this.packageName = paramApkLite.packageName;
    this.versionCode = paramApkLite.versionCode;
    this.versionCodeMajor = paramApkLite.versionCodeMajor;
    this.installLocation = paramApkLite.installLocation;
    this.verifiers = paramApkLite.verifiers;
    this.splitNames = paramArrayOfString1;
    this.isFeatureSplits = paramArrayOfboolean;
    this.usesSplitNames = paramArrayOfString2;
    this.configForSplit = paramArrayOfString3;
    this.codePath = paramString;
    this.baseCodePath = paramApkLite.codePath;
    this.splitCodePaths = paramArrayOfString4;
    this.baseRevisionCode = paramApkLite.revisionCode;
    this.splitRevisionCodes = paramArrayOfint;
    this.coreApp = paramApkLite.coreApp;
    this.debuggable = paramApkLite.debuggable;
    this.multiArch = paramApkLite.multiArch;
    this.use32bitAbi = paramApkLite.use32bitAbi;
    this.extractNativeLibs = paramApkLite.extractNativeLibs;
    this.isolatedSplits = paramApkLite.isolatedSplits;
  }
  
  public List<String> getAllCodePaths() {
    ArrayList<String> arrayList = new ArrayList();
    arrayList.add(this.baseCodePath);
    if (!ArrayUtils.isEmpty((Object[])this.splitCodePaths))
      Collections.addAll(arrayList, this.splitCodePaths); 
    return arrayList;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$PackageLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */