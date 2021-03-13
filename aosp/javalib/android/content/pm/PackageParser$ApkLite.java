package android.content.pm;

import java.util.List;

public class ApkLite {
  public final String codePath;
  
  public final String configForSplit;
  
  public final boolean coreApp;
  
  public final boolean debuggable;
  
  public final boolean extractNativeLibs;
  
  public final int installLocation;
  
  public boolean isFeatureSplit;
  
  public final boolean isSplitRequired;
  
  public final boolean isolatedSplits;
  
  public final int minSdkVersion;
  
  public final boolean multiArch;
  
  public final boolean overlayIsStatic;
  
  public final int overlayPriority;
  
  public final String packageName;
  
  public final boolean profilableByShell;
  
  public final int revisionCode;
  
  public final PackageParser.SigningDetails signingDetails;
  
  public final String splitName;
  
  public final String targetPackageName;
  
  public final int targetSdkVersion;
  
  public final boolean use32bitAbi;
  
  public final boolean useEmbeddedDex;
  
  public final String usesSplitName;
  
  public final VerifierInfo[] verifiers;
  
  public final int versionCode;
  
  public final int versionCodeMajor;
  
  public ApkLite(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, String paramString5, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<VerifierInfo> paramList, PackageParser.SigningDetails paramSigningDetails, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, boolean paramBoolean8, boolean paramBoolean9, boolean paramBoolean10, String paramString6, boolean paramBoolean11, int paramInt5, int paramInt6, int paramInt7) {
    this.codePath = paramString1;
    this.packageName = paramString2;
    this.splitName = paramString3;
    this.isFeatureSplit = paramBoolean1;
    this.configForSplit = paramString4;
    this.usesSplitName = paramString5;
    this.versionCode = paramInt1;
    this.versionCodeMajor = paramInt2;
    this.revisionCode = paramInt3;
    this.installLocation = paramInt4;
    this.signingDetails = paramSigningDetails;
    this.verifiers = paramList.<VerifierInfo>toArray(new VerifierInfo[paramList.size()]);
    this.coreApp = paramBoolean3;
    this.debuggable = paramBoolean4;
    this.profilableByShell = paramBoolean5;
    this.multiArch = paramBoolean6;
    this.use32bitAbi = paramBoolean7;
    this.useEmbeddedDex = paramBoolean8;
    this.extractNativeLibs = paramBoolean9;
    this.isolatedSplits = paramBoolean10;
    this.isSplitRequired = paramBoolean2;
    this.targetPackageName = paramString6;
    this.overlayIsStatic = paramBoolean11;
    this.overlayPriority = paramInt5;
    this.minSdkVersion = paramInt6;
    this.targetSdkVersion = paramInt7;
  }
  
  public long getLongVersionCode() {
    return PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$ApkLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */