package android.app;

import android.util.Slog;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

class WarningContextClassLoader extends ClassLoader {
  private static boolean warned = false;
  
  private WarningContextClassLoader() {}
  
  private void warn(String paramString) {
    if (warned)
      return; 
    warned = true;
    Thread.currentThread().setContextClassLoader(getParent());
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ClassLoader.");
    stringBuilder.append(paramString);
    stringBuilder.append(": The class loader returned by Thread.getContextClassLoader() may fail for processes that host multiple applications. You should explicitly specify a context class loader. For example: Thread.setContextClassLoader(getClass().getClassLoader());");
    Slog.w("ActivityThread", stringBuilder.toString());
  }
  
  public void clearAssertionStatus() {
    warn("clearAssertionStatus");
    getParent().clearAssertionStatus();
  }
  
  public URL getResource(String paramString) {
    warn("getResource");
    return getParent().getResource(paramString);
  }
  
  public InputStream getResourceAsStream(String paramString) {
    warn("getResourceAsStream");
    return getParent().getResourceAsStream(paramString);
  }
  
  public Enumeration<URL> getResources(String paramString) throws IOException {
    warn("getResources");
    return getParent().getResources(paramString);
  }
  
  public Class<?> loadClass(String paramString) throws ClassNotFoundException {
    warn("loadClass");
    return getParent().loadClass(paramString);
  }
  
  public void setClassAssertionStatus(String paramString, boolean paramBoolean) {
    warn("setClassAssertionStatus");
    getParent().setClassAssertionStatus(paramString, paramBoolean);
  }
  
  public void setDefaultAssertionStatus(boolean paramBoolean) {
    warn("setDefaultAssertionStatus");
    getParent().setDefaultAssertionStatus(paramBoolean);
  }
  
  public void setPackageAssertionStatus(String paramString, boolean paramBoolean) {
    warn("setPackageAssertionStatus");
    getParent().setPackageAssertionStatus(paramString, paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$WarningContextClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */