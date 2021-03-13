package android.annotation;

public enum Client {
  MODULE_LIBRARIES, PRIVILEGED_APPS, SYSTEM_SERVER;
  
  static {
    MODULE_LIBRARIES = new Client("MODULE_LIBRARIES", 1);
    Client client = new Client("SYSTEM_SERVER", 2);
    SYSTEM_SERVER = client;
    $VALUES = new Client[] { PRIVILEGED_APPS, MODULE_LIBRARIES, client };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/annotation/SystemApi$Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */