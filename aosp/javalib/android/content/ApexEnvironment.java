package android.content;

import android.annotation.SystemApi;
import android.os.Environment;
import android.os.UserHandle;
import java.io.File;
import java.util.Objects;

@SystemApi
public class ApexEnvironment {
  private static final String APEX_DATA = "apexdata";
  
  private final String mApexModuleName;
  
  private ApexEnvironment(String paramString) {
    this.mApexModuleName = paramString;
  }
  
  public static ApexEnvironment getApexEnvironment(String paramString) {
    Objects.requireNonNull(paramString, "apexModuleName cannot be null");
    return new ApexEnvironment(paramString);
  }
  
  public File getCredentialProtectedDataDirForUser(UserHandle paramUserHandle) {
    return Environment.buildPath(Environment.getDataMiscCeDirectory(paramUserHandle.getIdentifier()), new String[] { "apexdata", this.mApexModuleName });
  }
  
  public File getDeviceProtectedDataDir() {
    return Environment.buildPath(Environment.getDataMiscDirectory(), new String[] { "apexdata", this.mApexModuleName });
  }
  
  public File getDeviceProtectedDataDirForUser(UserHandle paramUserHandle) {
    return Environment.buildPath(Environment.getDataMiscDeDirectory(paramUserHandle.getIdentifier()), new String[] { "apexdata", this.mApexModuleName });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ApexEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */