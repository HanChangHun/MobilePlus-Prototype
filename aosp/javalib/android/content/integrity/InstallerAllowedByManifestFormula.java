package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

public class InstallerAllowedByManifestFormula extends IntegrityFormula implements Parcelable {
  public static final Parcelable.Creator<InstallerAllowedByManifestFormula> CREATOR = new Parcelable.Creator<InstallerAllowedByManifestFormula>() {
      public InstallerAllowedByManifestFormula createFromParcel(Parcel param1Parcel) {
        return new InstallerAllowedByManifestFormula(param1Parcel);
      }
      
      public InstallerAllowedByManifestFormula[] newArray(int param1Int) {
        return new InstallerAllowedByManifestFormula[param1Int];
      }
    };
  
  public static final String INSTALLER_CERTIFICATE_NOT_EVALUATED = "";
  
  public InstallerAllowedByManifestFormula() {}
  
  private InstallerAllowedByManifestFormula(Parcel paramParcel) {}
  
  private static boolean installerInAllowedInstallersFromManifest(AppInstallMetadata paramAppInstallMetadata, Map<String, String> paramMap) {
    String str = paramAppInstallMetadata.getInstallerName();
    return !paramMap.containsKey(str) ? false : (!((String)paramMap.get(str)).equals("") ? paramAppInstallMetadata.getInstallerCertificates().contains(paramMap.get(paramAppInstallMetadata.getInstallerName())) : true);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getTag() {
    return 4;
  }
  
  public boolean isAppCertificateFormula() {
    return false;
  }
  
  public boolean isInstallerFormula() {
    return true;
  }
  
  public boolean matches(AppInstallMetadata paramAppInstallMetadata) {
    Map<String, String> map = paramAppInstallMetadata.getAllowedInstallersAndCertificates();
    return (map.isEmpty() || installerInAllowedInstallersFromManifest(paramAppInstallMetadata, map));
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/InstallerAllowedByManifestFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */