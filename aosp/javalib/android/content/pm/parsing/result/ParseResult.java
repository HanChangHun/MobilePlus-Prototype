package android.content.pm.parsing.result;

public interface ParseResult<ResultType> {
  int getErrorCode();
  
  String getErrorMessage();
  
  Exception getException();
  
  ResultType getResult();
  
  boolean isError();
  
  boolean isSuccess();
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/result/ParseResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */