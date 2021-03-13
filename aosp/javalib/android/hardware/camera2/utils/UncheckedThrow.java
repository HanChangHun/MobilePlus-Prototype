package android.hardware.camera2.utils;

public class UncheckedThrow {
  public static void throwAnyException(Exception paramException) {
    throwAnyImpl(paramException);
  }
  
  public static void throwAnyException(Throwable paramThrowable) {
    throwAnyImpl(paramThrowable);
  }
  
  private static <T extends Throwable> void throwAnyImpl(Throwable paramThrowable) throws T {
    throw (T)paramThrowable;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/UncheckedThrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */