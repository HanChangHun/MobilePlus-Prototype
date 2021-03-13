package android.content.integrity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class AppInstallMetadata {
  private final Map<String, String> mAllowedInstallersAndCertificates;
  
  private final List<String> mAppCertificates;
  
  private final List<String> mInstallerCertificates;
  
  private final String mInstallerName;
  
  private final boolean mIsPreInstalled;
  
  private final boolean mIsStampPresent;
  
  private final boolean mIsStampTrusted;
  
  private final boolean mIsStampVerified;
  
  private final String mPackageName;
  
  private final String mStampCertificateHash;
  
  private final long mVersionCode;
  
  private AppInstallMetadata(Builder paramBuilder) {
    this.mPackageName = paramBuilder.mPackageName;
    this.mAppCertificates = paramBuilder.mAppCertificates;
    this.mInstallerName = paramBuilder.mInstallerName;
    this.mInstallerCertificates = paramBuilder.mInstallerCertificates;
    this.mVersionCode = paramBuilder.mVersionCode;
    this.mIsPreInstalled = paramBuilder.mIsPreInstalled;
    this.mIsStampPresent = paramBuilder.mIsStampPresent;
    this.mIsStampVerified = paramBuilder.mIsStampVerified;
    this.mIsStampTrusted = paramBuilder.mIsStampTrusted;
    this.mStampCertificateHash = paramBuilder.mStampCertificateHash;
    this.mAllowedInstallersAndCertificates = paramBuilder.mAllowedInstallersAndCertificates;
  }
  
  public Map<String, String> getAllowedInstallersAndCertificates() {
    return this.mAllowedInstallersAndCertificates;
  }
  
  public List<String> getAppCertificates() {
    return this.mAppCertificates;
  }
  
  public List<String> getInstallerCertificates() {
    return this.mInstallerCertificates;
  }
  
  public String getInstallerName() {
    return this.mInstallerName;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public String getStampCertificateHash() {
    return this.mStampCertificateHash;
  }
  
  public long getVersionCode() {
    return this.mVersionCode;
  }
  
  public boolean isPreInstalled() {
    return this.mIsPreInstalled;
  }
  
  public boolean isStampPresent() {
    return this.mIsStampPresent;
  }
  
  public boolean isStampTrusted() {
    return this.mIsStampTrusted;
  }
  
  public boolean isStampVerified() {
    return this.mIsStampVerified;
  }
  
  public String toString() {
    String str2;
    String str1 = this.mPackageName;
    List<String> list1 = this.mAppCertificates;
    String str3 = this.mInstallerName;
    String str4 = "null";
    String str5 = str3;
    if (str3 == null)
      str5 = "null"; 
    List<String> list3 = this.mInstallerCertificates;
    List<String> list2 = list3;
    if (list3 == null)
      str2 = "null"; 
    long l = this.mVersionCode;
    boolean bool1 = this.mIsPreInstalled;
    boolean bool2 = this.mIsStampPresent;
    boolean bool3 = this.mIsStampVerified;
    boolean bool4 = this.mIsStampTrusted;
    String str6 = this.mStampCertificateHash;
    if (str6 != null)
      str4 = str6; 
    return String.format("AppInstallMetadata { PackageName = %s, AppCerts = %s, InstallerName = %s, InstallerCerts = %s, VersionCode = %d, PreInstalled = %b, StampPresent = %b, StampVerified = %b, StampTrusted = %b, StampCert = %s }", new Object[] { str1, list1, str5, str2, Long.valueOf(l), Boolean.valueOf(bool1), Boolean.valueOf(bool2), Boolean.valueOf(bool3), Boolean.valueOf(bool4), str4 });
  }
  
  public static final class Builder {
    private Map<String, String> mAllowedInstallersAndCertificates = new HashMap<>();
    
    private List<String> mAppCertificates;
    
    private List<String> mInstallerCertificates;
    
    private String mInstallerName;
    
    private boolean mIsPreInstalled;
    
    private boolean mIsStampPresent;
    
    private boolean mIsStampTrusted;
    
    private boolean mIsStampVerified;
    
    private String mPackageName;
    
    private String mStampCertificateHash;
    
    private long mVersionCode;
    
    public AppInstallMetadata build() {
      Objects.requireNonNull(this.mPackageName);
      Objects.requireNonNull(this.mAppCertificates);
      return new AppInstallMetadata(this);
    }
    
    public Builder setAllowedInstallersAndCert(Map<String, String> param1Map) {
      this.mAllowedInstallersAndCertificates = param1Map;
      return this;
    }
    
    public Builder setAppCertificates(List<String> param1List) {
      Objects.requireNonNull(param1List);
      this.mAppCertificates = param1List;
      return this;
    }
    
    public Builder setInstallerCertificates(List<String> param1List) {
      Objects.requireNonNull(param1List);
      this.mInstallerCertificates = param1List;
      return this;
    }
    
    public Builder setInstallerName(String param1String) {
      Objects.requireNonNull(param1String);
      this.mInstallerName = param1String;
      return this;
    }
    
    public Builder setIsPreInstalled(boolean param1Boolean) {
      this.mIsPreInstalled = param1Boolean;
      return this;
    }
    
    public Builder setIsStampPresent(boolean param1Boolean) {
      this.mIsStampPresent = param1Boolean;
      return this;
    }
    
    public Builder setIsStampTrusted(boolean param1Boolean) {
      this.mIsStampTrusted = param1Boolean;
      return this;
    }
    
    public Builder setIsStampVerified(boolean param1Boolean) {
      this.mIsStampVerified = param1Boolean;
      return this;
    }
    
    public Builder setPackageName(String param1String) {
      Objects.requireNonNull(param1String);
      this.mPackageName = param1String;
      return this;
    }
    
    public Builder setStampCertificateHash(String param1String) {
      Objects.requireNonNull(param1String);
      this.mStampCertificateHash = param1String;
      return this;
    }
    
    public Builder setVersionCode(long param1Long) {
      this.mVersionCode = param1Long;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AppInstallMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */