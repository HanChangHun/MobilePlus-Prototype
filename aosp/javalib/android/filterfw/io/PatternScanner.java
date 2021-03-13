package android.filterfw.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternScanner {
  private Pattern mIgnorePattern;
  
  private String mInput;
  
  private int mLineNo = 0;
  
  private int mOffset = 0;
  
  private int mStartOfLine = 0;
  
  public PatternScanner(String paramString) {
    this.mInput = paramString;
  }
  
  public PatternScanner(String paramString, Pattern paramPattern) {
    this.mInput = paramString;
    this.mIgnorePattern = paramPattern;
    skip(paramPattern);
  }
  
  public boolean atEnd() {
    boolean bool;
    if (this.mOffset >= this.mInput.length()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String eat(Pattern paramPattern, String paramString) {
    String str = tryEat(paramPattern);
    if (str != null)
      return str; 
    throw new RuntimeException(unexpectedTokenMessage(paramString));
  }
  
  public int lineNo() {
    return this.mLineNo;
  }
  
  public boolean peek(Pattern paramPattern) {
    Pattern pattern = this.mIgnorePattern;
    if (pattern != null)
      skip(pattern); 
    Matcher matcher = paramPattern.matcher(this.mInput);
    matcher.region(this.mOffset, this.mInput.length());
    return matcher.lookingAt();
  }
  
  public void skip(Pattern paramPattern) {
    Matcher matcher = paramPattern.matcher(this.mInput);
    matcher.region(this.mOffset, this.mInput.length());
    if (matcher.lookingAt()) {
      updateLineCount(this.mOffset, matcher.end());
      this.mOffset = matcher.end();
    } 
  }
  
  public String tryEat(Pattern paramPattern) {
    String str;
    Pattern pattern = this.mIgnorePattern;
    if (pattern != null)
      skip(pattern); 
    Matcher matcher = paramPattern.matcher(this.mInput);
    matcher.region(this.mOffset, this.mInput.length());
    paramPattern = null;
    if (matcher.lookingAt()) {
      updateLineCount(this.mOffset, matcher.end());
      this.mOffset = matcher.end();
      str = this.mInput.substring(matcher.start(), matcher.end());
    } 
    if (str != null) {
      Pattern pattern1 = this.mIgnorePattern;
      if (pattern1 != null)
        skip(pattern1); 
    } 
    return str;
  }
  
  public String unexpectedTokenMessage(String paramString) {
    String str = this.mInput.substring(this.mStartOfLine, this.mOffset);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unexpected token on line ");
    stringBuilder.append(this.mLineNo + 1);
    stringBuilder.append(" after '");
    stringBuilder.append(str);
    stringBuilder.append("' <- Expected ");
    stringBuilder.append(paramString);
    stringBuilder.append("!");
    return stringBuilder.toString();
  }
  
  public void updateLineCount(int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      if (this.mInput.charAt(paramInt1) == '\n') {
        this.mLineNo++;
        this.mStartOfLine = paramInt1 + 1;
      } 
      paramInt1++;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/PatternScanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */