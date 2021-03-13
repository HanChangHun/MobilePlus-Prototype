package android.app.admin;

public class PasswordPolicy {
  public static final int DEF_MINIMUM_LENGTH = 0;
  
  public static final int DEF_MINIMUM_LETTERS = 1;
  
  public static final int DEF_MINIMUM_LOWER_CASE = 0;
  
  public static final int DEF_MINIMUM_NON_LETTER = 0;
  
  public static final int DEF_MINIMUM_NUMERIC = 1;
  
  public static final int DEF_MINIMUM_SYMBOLS = 1;
  
  public static final int DEF_MINIMUM_UPPER_CASE = 0;
  
  public int length = 0;
  
  public int letters = 1;
  
  public int lowerCase = 0;
  
  public int nonLetter = 0;
  
  public int numeric = 1;
  
  public int quality = 0;
  
  public int symbols = 1;
  
  public int upperCase = 0;
  
  public PasswordMetrics getMinMetrics() {
    int i = this.quality;
    if (i == 0)
      return new PasswordMetrics(-1); 
    if (i == 32768 || i == 65536)
      return new PasswordMetrics(1); 
    PasswordMetrics passwordMetrics = new PasswordMetrics(4);
    passwordMetrics.length = this.length;
    i = this.quality;
    if (i == 196608) {
      passwordMetrics.seqLength = 3;
    } else if (i == 262144) {
      passwordMetrics.nonNumeric = 1;
    } else if (i == 327680) {
      passwordMetrics.numeric = 1;
      passwordMetrics.nonNumeric = 1;
    } else if (i == 393216) {
      passwordMetrics.numeric = this.numeric;
      passwordMetrics.letters = this.letters;
      passwordMetrics.upperCase = this.upperCase;
      passwordMetrics.lowerCase = this.lowerCase;
      passwordMetrics.nonLetter = this.nonLetter;
      passwordMetrics.symbols = this.symbols;
    } 
    return passwordMetrics;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */