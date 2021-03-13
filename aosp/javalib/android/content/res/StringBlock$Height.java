package android.content.res;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.style.LineHeightSpan;

class Height implements LineHeightSpan.WithDensity {
  private static float sProportion = 0.0F;
  
  private int mSize;
  
  public Height(int paramInt) {
    this.mSize = paramInt;
  }
  
  public void chooseHeight(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint.FontMetricsInt paramFontMetricsInt) {
    chooseHeight(paramCharSequence, paramInt1, paramInt2, paramInt3, paramInt4, paramFontMetricsInt, null);
  }
  
  public void chooseHeight(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint.FontMetricsInt paramFontMetricsInt, TextPaint paramTextPaint) {
    paramInt2 = this.mSize;
    paramInt1 = paramInt2;
    if (paramTextPaint != null)
      paramInt1 = (int)(paramInt2 * paramTextPaint.density); 
    if (paramFontMetricsInt.bottom - paramFontMetricsInt.top < paramInt1) {
      paramFontMetricsInt.top = paramFontMetricsInt.bottom - paramInt1;
      paramFontMetricsInt.ascent -= paramInt1;
    } else {
      if (sProportion == 0.0F) {
        Paint paint = new Paint();
        paint.setTextSize(100.0F);
        Rect rect = new Rect();
        paint.getTextBounds("ABCDEFG", 0, 7, rect);
        sProportion = rect.top / paint.ascent();
      } 
      paramInt2 = (int)Math.ceil((-paramFontMetricsInt.top * sProportion));
      if (paramInt1 - paramFontMetricsInt.descent >= paramInt2) {
        paramFontMetricsInt.top = paramFontMetricsInt.bottom - paramInt1;
        paramFontMetricsInt.ascent = paramFontMetricsInt.descent - paramInt1;
      } else if (paramInt1 >= paramInt2) {
        paramInt2 = -paramInt2;
        paramFontMetricsInt.ascent = paramInt2;
        paramFontMetricsInt.top = paramInt2;
        paramInt1 = paramFontMetricsInt.top + paramInt1;
        paramFontMetricsInt.descent = paramInt1;
        paramFontMetricsInt.bottom = paramInt1;
      } else {
        paramInt1 = -paramInt1;
        paramFontMetricsInt.ascent = paramInt1;
        paramFontMetricsInt.top = paramInt1;
        paramFontMetricsInt.descent = 0;
        paramFontMetricsInt.bottom = 0;
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/StringBlock$Height.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */