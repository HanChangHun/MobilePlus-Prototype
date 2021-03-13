package android.content.pm.parsing.result;

public interface ParseInput {
  ParseResult<?> deferError(String paramString, long paramLong);
  
  ParseResult<?> enableDeferredError(String paramString, int paramInt);
  
  <ResultType> ParseResult<ResultType> error(int paramInt);
  
  <ResultType> ParseResult<ResultType> error(int paramInt, String paramString);
  
  <ResultType> ParseResult<ResultType> error(int paramInt, String paramString, Exception paramException);
  
  <ResultType> ParseResult<ResultType> error(ParseResult<?> paramParseResult);
  
  <ResultType> ParseResult<ResultType> error(String paramString);
  
  <ResultType> ParseResult<ResultType> skip(String paramString);
  
  <ResultType> ParseResult<ResultType> success(ResultType paramResultType);
  
  public static interface Callback {
    boolean isChangeEnabled(long param1Long, String param1String, int param1Int);
  }
  
  public static final class DeferredError {
    public static final long EMPTY_INTENT_ACTION_CATEGORY = 151163173L;
    
    public static final long MISSING_APP_TAG = 150776642L;
    
    public static final long RESOURCES_ARSC_COMPRESSED = 132742131L;
    
    public static int getTargetSdkForChange(long param1Long) {
      return (param1Long == 150776642L || param1Long == 151163173L || param1Long == 132742131L) ? 29 : -1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/result/ParseInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */