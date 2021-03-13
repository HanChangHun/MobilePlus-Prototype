package android.graphics;

public class FontMetricsInt {
  public int ascent;
  
  public int bottom;
  
  public int descent;
  
  public int leading;
  
  public int top;
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("FontMetricsInt: top=");
    stringBuilder.append(this.top);
    stringBuilder.append(" ascent=");
    stringBuilder.append(this.ascent);
    stringBuilder.append(" descent=");
    stringBuilder.append(this.descent);
    stringBuilder.append(" bottom=");
    stringBuilder.append(this.bottom);
    stringBuilder.append(" leading=");
    stringBuilder.append(this.leading);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Paint$FontMetricsInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */