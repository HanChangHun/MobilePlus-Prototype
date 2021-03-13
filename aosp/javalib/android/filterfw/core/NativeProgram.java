package android.filterfw.core;

public class NativeProgram extends Program {
  private boolean mHasGetValueFunction;
  
  private boolean mHasInitFunction;
  
  private boolean mHasResetFunction;
  
  private boolean mHasSetValueFunction;
  
  private boolean mHasTeardownFunction;
  
  private boolean mTornDown;
  
  private int nativeProgramId;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  public NativeProgram(String paramString1, String paramString2) {
    StringBuilder stringBuilder1;
    this.mHasInitFunction = false;
    this.mHasTeardownFunction = false;
    this.mHasSetValueFunction = false;
    this.mHasGetValueFunction = false;
    this.mHasResetFunction = false;
    this.mTornDown = false;
    allocate();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("lib");
    stringBuilder3.append(paramString1);
    stringBuilder3.append(".so");
    paramString1 = stringBuilder3.toString();
    if (openNativeLibrary(paramString1)) {
      stringBuilder3 = new StringBuilder();
      stringBuilder3.append(paramString2);
      stringBuilder3.append("_process");
      String str = stringBuilder3.toString();
      if (bindProcessFunction(str)) {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString2);
        stringBuilder1.append("_init");
        this.mHasInitFunction = bindInitFunction(stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString2);
        stringBuilder1.append("_teardown");
        this.mHasTeardownFunction = bindTeardownFunction(stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString2);
        stringBuilder1.append("_setvalue");
        this.mHasSetValueFunction = bindSetValueFunction(stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString2);
        stringBuilder1.append("_getvalue");
        this.mHasGetValueFunction = bindGetValueFunction(stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramString2);
        stringBuilder1.append("_reset");
        this.mHasResetFunction = bindResetFunction(stringBuilder1.toString());
        if (!this.mHasInitFunction || callNativeInit())
          return; 
        throw new RuntimeException("Could not initialize NativeProgram!");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not find native program function name ");
      stringBuilder.append(str);
      stringBuilder.append(" in library ");
      stringBuilder.append((String)stringBuilder1);
      stringBuilder.append("! This function is required!");
      throw new RuntimeException(stringBuilder.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Could not find native library named '");
    stringBuilder2.append((String)stringBuilder1);
    stringBuilder2.append("' required for native program!");
    throw new RuntimeException(stringBuilder2.toString());
  }
  
  private native boolean allocate();
  
  private native boolean bindGetValueFunction(String paramString);
  
  private native boolean bindInitFunction(String paramString);
  
  private native boolean bindProcessFunction(String paramString);
  
  private native boolean bindResetFunction(String paramString);
  
  private native boolean bindSetValueFunction(String paramString);
  
  private native boolean bindTeardownFunction(String paramString);
  
  private native String callNativeGetValue(String paramString);
  
  private native boolean callNativeInit();
  
  private native boolean callNativeProcess(NativeFrame[] paramArrayOfNativeFrame, NativeFrame paramNativeFrame);
  
  private native boolean callNativeReset();
  
  private native boolean callNativeSetValue(String paramString1, String paramString2);
  
  private native boolean callNativeTeardown();
  
  private native boolean deallocate();
  
  private native boolean nativeInit();
  
  private native boolean openNativeLibrary(String paramString);
  
  protected void finalize() throws Throwable {
    tearDown();
  }
  
  public Object getHostValue(String paramString) {
    if (!this.mTornDown) {
      if (this.mHasGetValueFunction)
        return callNativeGetValue(paramString); 
      throw new RuntimeException("Attempting to get native variable, but native code does not define native getvalue function!");
    } 
    throw new RuntimeException("NativeProgram already torn down!");
  }
  
  public void process(Frame[] paramArrayOfFrame, Frame paramFrame) {
    if (!this.mTornDown) {
      NativeFrame[] arrayOfNativeFrame = new NativeFrame[paramArrayOfFrame.length];
      byte b = 0;
      while (b < paramArrayOfFrame.length) {
        if (paramArrayOfFrame[b] == null || paramArrayOfFrame[b] instanceof NativeFrame) {
          arrayOfNativeFrame[b] = (NativeFrame)paramArrayOfFrame[b];
          b++;
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("NativeProgram got non-native frame as input ");
        stringBuilder.append(b);
        stringBuilder.append("!");
        throw new RuntimeException(stringBuilder.toString());
      } 
      if (paramFrame == null || paramFrame instanceof NativeFrame) {
        if (callNativeProcess(arrayOfNativeFrame, (NativeFrame)paramFrame))
          return; 
        throw new RuntimeException("Calling native process() caused error!");
      } 
      throw new RuntimeException("NativeProgram got non-native output frame!");
    } 
    throw new RuntimeException("NativeProgram already torn down!");
  }
  
  public void reset() {
    if (!this.mHasResetFunction || callNativeReset())
      return; 
    throw new RuntimeException("Could not reset NativeProgram!");
  }
  
  public void setHostValue(String paramString, Object paramObject) {
    if (!this.mTornDown) {
      if (this.mHasSetValueFunction) {
        if (callNativeSetValue(paramString, paramObject.toString()))
          return; 
        paramObject = new StringBuilder();
        paramObject.append("Error setting native value for variable '");
        paramObject.append(paramString);
        paramObject.append("'!");
        throw new RuntimeException(paramObject.toString());
      } 
      throw new RuntimeException("Attempting to set native variable, but native code does not define native setvalue function!");
    } 
    throw new RuntimeException("NativeProgram already torn down!");
  }
  
  public void tearDown() {
    if (this.mTornDown)
      return; 
    if (!this.mHasTeardownFunction || callNativeTeardown()) {
      deallocate();
      this.mTornDown = true;
      return;
    } 
    throw new RuntimeException("Could not tear down NativeProgram!");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/NativeProgram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */