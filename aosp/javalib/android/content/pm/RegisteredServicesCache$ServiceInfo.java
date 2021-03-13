package android.content.pm;

import android.content.ComponentName;

public class ServiceInfo<V> {
  public final ComponentInfo componentInfo;
  
  public final ComponentName componentName;
  
  public final V type;
  
  public final int uid;
  
  public ServiceInfo(V paramV, ComponentInfo paramComponentInfo, ComponentName paramComponentName) {
    byte b;
    this.type = paramV;
    this.componentInfo = paramComponentInfo;
    this.componentName = paramComponentName;
    if (paramComponentInfo != null) {
      b = paramComponentInfo.applicationInfo.uid;
    } else {
      b = -1;
    } 
    this.uid = b;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ServiceInfo: ");
    stringBuilder.append(this.type);
    stringBuilder.append(", ");
    stringBuilder.append(this.componentName);
    stringBuilder.append(", uid ");
    stringBuilder.append(this.uid);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/RegisteredServicesCache$ServiceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */