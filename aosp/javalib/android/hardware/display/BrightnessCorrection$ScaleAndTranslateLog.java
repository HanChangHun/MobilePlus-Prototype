package android.hardware.display;

import android.os.Parcel;
import android.util.MathUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class ScaleAndTranslateLog implements BrightnessCorrection.BrightnessCorrectionImplementation {
  private static final String ATTR_SCALE = "scale";
  
  private static final String ATTR_TRANSLATE = "translate";
  
  private static final float MAX_SCALE = 2.0F;
  
  private static final float MAX_TRANSLATE = 0.7F;
  
  private static final float MIN_SCALE = 0.5F;
  
  private static final float MIN_TRANSLATE = -0.6F;
  
  private final float mScale;
  
  private final float mTranslate;
  
  ScaleAndTranslateLog(float paramFloat1, float paramFloat2) {
    if (!Float.isNaN(paramFloat1) && !Float.isNaN(paramFloat2)) {
      this.mScale = MathUtils.constrain(paramFloat1, 0.5F, 2.0F);
      this.mTranslate = MathUtils.constrain(paramFloat2, -0.6F, 0.7F);
      return;
    } 
    throw new IllegalArgumentException("scale and translate must be numbers");
  }
  
  static BrightnessCorrection loadFromXml(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException {
    return BrightnessCorrection.createScaleAndTranslateLog(BrightnessCorrection.access$000(paramXmlPullParser, "scale"), BrightnessCorrection.access$000(paramXmlPullParser, "translate"));
  }
  
  static BrightnessCorrection readFromParcel(Parcel paramParcel) {
    return BrightnessCorrection.createScaleAndTranslateLog(paramParcel.readFloat(), paramParcel.readFloat());
  }
  
  public float apply(float paramFloat) {
    return MathUtils.exp(this.mScale * MathUtils.log(paramFloat) + this.mTranslate);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof ScaleAndTranslateLog))
      return false; 
    paramObject = paramObject;
    if (((ScaleAndTranslateLog)paramObject).mScale != this.mScale || ((ScaleAndTranslateLog)paramObject).mTranslate != this.mTranslate)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return (1 * 31 + Float.hashCode(this.mScale)) * 31 + Float.hashCode(this.mTranslate);
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.startTag(null, "scale-and-translate-log");
    paramXmlSerializer.attribute(null, "scale", Float.toString(this.mScale));
    paramXmlSerializer.attribute(null, "translate", Float.toString(this.mTranslate));
    paramXmlSerializer.endTag(null, "scale-and-translate-log");
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ScaleAndTranslateLog(");
    stringBuilder.append(this.mScale);
    stringBuilder.append(", ");
    stringBuilder.append(this.mTranslate);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel) {
    paramParcel.writeInt(1);
    paramParcel.writeFloat(this.mScale);
    paramParcel.writeFloat(this.mTranslate);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessCorrection$ScaleAndTranslateLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */