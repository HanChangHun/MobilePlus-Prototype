package android.content.pm.parsing.result;

import android.content.pm.ApplicationInfo;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Slog;
import com.android.internal.compat.IPlatformCompat;
import com.android.internal.util.CollectionUtils;
import java.util.Map;

public class ParseTypeImpl implements ParseInput, ParseResult<Object> {
  public static final boolean DEBUG_FILL_STACK_TRACE = false;
  
  public static final boolean DEBUG_LOG_ON_ERROR = false;
  
  public static final boolean DEBUG_THROW_ALL_ERRORS = false;
  
  private static final String TAG = "PackageParsing";
  
  private ParseInput.Callback mCallback;
  
  private ArrayMap<Long, String> mDeferredErrors = null;
  
  private int mErrorCode = 1;
  
  private String mErrorMessage;
  
  private Exception mException;
  
  private String mPackageName;
  
  private Object mResult;
  
  private Integer mTargetSdkVersion;
  
  public ParseTypeImpl(ParseInput.Callback paramCallback) {
    this.mCallback = paramCallback;
  }
  
  public static ParseTypeImpl forDefaultParsing() {
    return new ParseTypeImpl(new _$$Lambda$ParseTypeImpl$BtZ1hEXecC_DzCfE9C1vVlLdZ0o(IPlatformCompat.Stub.asInterface(ServiceManager.getService("platform_compat"))));
  }
  
  public static ParseTypeImpl forParsingWithoutPlatformCompat() {
    return new ParseTypeImpl((ParseInput.Callback)_$$Lambda$ParseTypeImpl$w0XQhKhTM2QZoYasGubxOSuJkI4.INSTANCE);
  }
  
  public ParseResult<?> deferError(String paramString, long paramLong) {
    if (this.mTargetSdkVersion != null) {
      ArrayMap<Long, String> arrayMap = this.mDeferredErrors;
      if (arrayMap != null && arrayMap.containsKey(Long.valueOf(paramLong)))
        return success(null); 
      if (this.mCallback.isChangeEnabled(paramLong, this.mPackageName, this.mTargetSdkVersion.intValue()))
        return error(paramString); 
      if (this.mDeferredErrors == null)
        this.mDeferredErrors = new ArrayMap(); 
      this.mDeferredErrors.put(Long.valueOf(paramLong), null);
      return success(null);
    } 
    if (this.mDeferredErrors == null)
      this.mDeferredErrors = new ArrayMap(); 
    this.mDeferredErrors.putIfAbsent(Long.valueOf(paramLong), paramString);
    return success(null);
  }
  
  public ParseResult<?> enableDeferredError(String paramString, int paramInt) {
    this.mPackageName = paramString;
    this.mTargetSdkVersion = Integer.valueOf(paramInt);
    for (paramInt = CollectionUtils.size((Map)this.mDeferredErrors) - 1; paramInt >= 0; paramInt--) {
      long l = ((Long)this.mDeferredErrors.keyAt(paramInt)).longValue();
      paramString = (String)this.mDeferredErrors.valueAt(paramInt);
      if (this.mCallback.isChangeEnabled(l, this.mPackageName, this.mTargetSdkVersion.intValue()))
        return error(paramString); 
      this.mDeferredErrors.setValueAt(paramInt, null);
    } 
    return success(null);
  }
  
  public <ResultType> ParseResult<ResultType> error(int paramInt) {
    return error(paramInt, null);
  }
  
  public <ResultType> ParseResult<ResultType> error(int paramInt, String paramString) {
    return error(paramInt, paramString, null);
  }
  
  public <ResultType> ParseResult<ResultType> error(int paramInt, String paramString, Exception paramException) {
    this.mErrorCode = paramInt;
    this.mErrorMessage = paramString;
    this.mException = paramException;
    return this;
  }
  
  public <ResultType> ParseResult<ResultType> error(ParseResult<?> paramParseResult) {
    return error(paramParseResult.getErrorCode(), paramParseResult.getErrorMessage(), paramParseResult.getException());
  }
  
  public <ResultType> ParseResult<ResultType> error(String paramString) {
    return error(-108, paramString);
  }
  
  public int getErrorCode() {
    return this.mErrorCode;
  }
  
  public String getErrorMessage() {
    return this.mErrorMessage;
  }
  
  public Exception getException() {
    return this.mException;
  }
  
  public Object getResult() {
    return this.mResult;
  }
  
  public boolean isError() {
    return isSuccess() ^ true;
  }
  
  public boolean isSuccess() {
    int i = this.mErrorCode;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public ParseInput reset() {
    this.mResult = null;
    this.mErrorCode = 1;
    this.mErrorMessage = null;
    this.mException = null;
    ArrayMap<Long, String> arrayMap = this.mDeferredErrors;
    if (arrayMap != null)
      arrayMap.erase(); 
    return this;
  }
  
  public <ResultType> ParseResult<ResultType> skip(String paramString) {
    return error(-125, paramString);
  }
  
  public <ResultType> ParseResult<ResultType> success(ResultType paramResultType) {
    if (this.mErrorCode != 1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Cannot set to success after set to error, was ");
      stringBuilder.append(this.mErrorMessage);
      Slog.wtf("PackageParsing", stringBuilder.toString(), this.mException);
    } 
    this.mResult = paramResultType;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/result/ParseTypeImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */