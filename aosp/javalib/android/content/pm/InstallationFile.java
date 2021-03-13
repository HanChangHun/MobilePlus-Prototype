package android.content.pm;

import android.annotation.SystemApi;

@SystemApi
public final class InstallationFile {
  private final InstallationFileParcel mParcel;
  
  public InstallationFile(int paramInt, String paramString, long paramLong, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    InstallationFileParcel installationFileParcel = new InstallationFileParcel();
    this.mParcel = installationFileParcel;
    installationFileParcel.location = paramInt;
    this.mParcel.name = paramString;
    this.mParcel.size = paramLong;
    this.mParcel.metadata = paramArrayOfbyte1;
    this.mParcel.signature = paramArrayOfbyte2;
  }
  
  public InstallationFileParcel getData() {
    return this.mParcel;
  }
  
  public long getLengthBytes() {
    return this.mParcel.size;
  }
  
  public int getLocation() {
    return this.mParcel.location;
  }
  
  public byte[] getMetadata() {
    return this.mParcel.metadata;
  }
  
  public String getName() {
    return this.mParcel.name;
  }
  
  public byte[] getSignature() {
    return this.mParcel.signature;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/InstallationFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */