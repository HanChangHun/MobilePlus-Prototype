package android.content.integrity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Builder {
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
    return new AppInstallMetadata(this, null);
  }
  
  public Builder setAllowedInstallersAndCert(Map<String, String> paramMap) {
    this.mAllowedInstallersAndCertificates = paramMap;
    return this;
  }
  
  public Builder setAppCertificates(List<String> paramList) {
    Objects.requireNonNull(paramList);
    this.mAppCertificates = paramList;
    return this;
  }
  
  public Builder setInstallerCertificates(List<String> paramList) {
    Objects.requireNonNull(paramList);
    this.mInstallerCertificates = paramList;
    return this;
  }
  
  public Builder setInstallerName(String paramString) {
    Objects.requireNonNull(paramString);
    this.mInstallerName = paramString;
    return this;
  }
  
  public Builder setIsPreInstalled(boolean paramBoolean) {
    this.mIsPreInstalled = paramBoolean;
    return this;
  }
  
  public Builder setIsStampPresent(boolean paramBoolean) {
    this.mIsStampPresent = paramBoolean;
    return this;
  }
  
  public Builder setIsStampTrusted(boolean paramBoolean) {
    this.mIsStampTrusted = paramBoolean;
    return this;
  }
  
  public Builder setIsStampVerified(boolean paramBoolean) {
    this.mIsStampVerified = paramBoolean;
    return this;
  }
  
  public Builder setPackageName(String paramString) {
    Objects.requireNonNull(paramString);
    this.mPackageName = paramString;
    return this;
  }
  
  public Builder setStampCertificateHash(String paramString) {
    Objects.requireNonNull(paramString);
    this.mStampCertificateHash = paramString;
    return this;
  }
  
  public Builder setVersionCode(long paramLong) {
    this.mVersionCode = paramLong;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AppInstallMetadata$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */