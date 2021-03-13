package android.hardware.camera2.legacy;

import android.os.ServiceSpecificException;
import android.system.OsConstants;
import android.util.AndroidException;

public class LegacyExceptionUtils {
  public static final int ALREADY_EXISTS;
  
  public static final int BAD_VALUE;
  
  public static final int DEAD_OBJECT;
  
  public static final int INVALID_OPERATION;
  
  public static final int NO_ERROR = 0;
  
  public static final int PERMISSION_DENIED = -OsConstants.EPERM;
  
  private static final String TAG = "LegacyExceptionUtils";
  
  public static final int TIMED_OUT;
  
  static {
    ALREADY_EXISTS = -OsConstants.EEXIST;
    BAD_VALUE = -OsConstants.EINVAL;
    DEAD_OBJECT = -OsConstants.ENOSYS;
    INVALID_OPERATION = -OsConstants.EPIPE;
    TIMED_OUT = -OsConstants.ETIMEDOUT;
  }
  
  private LegacyExceptionUtils() {
    throw new AssertionError();
  }
  
  public static int throwOnError(int paramInt) throws BufferQueueAbandonedException {
    if (paramInt == 0)
      return 0; 
    if (paramInt != BAD_VALUE) {
      if (paramInt >= 0)
        return paramInt; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown error ");
      stringBuilder.append(paramInt);
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    throw new BufferQueueAbandonedException();
  }
  
  public static void throwOnServiceError(int paramInt) {
    String str;
    if (paramInt >= 0)
      return; 
    if (paramInt != PERMISSION_DENIED) {
      if (paramInt == ALREADY_EXISTS)
        return; 
      if (paramInt != BAD_VALUE) {
        if (paramInt != DEAD_OBJECT) {
          if (paramInt != TIMED_OUT) {
            if (paramInt != -OsConstants.EACCES) {
              if (paramInt != -OsConstants.EBUSY) {
                if (paramInt != -OsConstants.EUSERS) {
                  if (paramInt != -OsConstants.ENODEV) {
                    if (paramInt != -OsConstants.EOPNOTSUPP) {
                      if (paramInt == INVALID_OPERATION) {
                        paramInt = 10;
                        str = "Illegal state encountered in camera service.";
                      } else {
                        byte b = 10;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unknown camera device error ");
                        stringBuilder.append(paramInt);
                        str = stringBuilder.toString();
                        paramInt = b;
                      } 
                    } else {
                      paramInt = 9;
                      str = "Deprecated camera HAL does not support this";
                    } 
                  } else {
                    paramInt = 4;
                    str = "Camera device not available";
                  } 
                } else {
                  paramInt = 8;
                  str = "Maximum number of cameras in use";
                } 
              } else {
                paramInt = 7;
                str = "Camera already in use";
              } 
            } else {
              paramInt = 6;
              str = "Camera disabled by policy";
            } 
          } else {
            paramInt = 10;
            str = "Operation timed out in camera service";
          } 
        } else {
          paramInt = 4;
          str = "Camera service not available";
        } 
      } else {
        paramInt = 3;
        str = "Bad argument passed to camera service";
      } 
    } else {
      paramInt = 1;
      str = "Lacking privileges to access camera service";
    } 
    throw new ServiceSpecificException(paramInt, str);
  }
  
  public static class BufferQueueAbandonedException extends AndroidException {
    public BufferQueueAbandonedException() {}
    
    public BufferQueueAbandonedException(Exception param1Exception) {
      super(param1Exception);
    }
    
    public BufferQueueAbandonedException(String param1String) {
      super(param1String);
    }
    
    public BufferQueueAbandonedException(String param1String, Throwable param1Throwable) {
      super(param1String, param1Throwable);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyExceptionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */