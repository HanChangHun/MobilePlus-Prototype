package android.hardware.display;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.MathUtils;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

@SystemApi
public final class BrightnessCorrection implements Parcelable {
  public static final Parcelable.Creator<BrightnessCorrection> CREATOR = new Parcelable.Creator<BrightnessCorrection>() {
      public BrightnessCorrection createFromParcel(Parcel param1Parcel) {
        return (param1Parcel.readInt() != 1) ? null : BrightnessCorrection.ScaleAndTranslateLog.readFromParcel(param1Parcel);
      }
      
      public BrightnessCorrection[] newArray(int param1Int) {
        return new BrightnessCorrection[param1Int];
      }
    };
  
  private static final int SCALE_AND_TRANSLATE_LOG = 1;
  
  private static final String TAG_SCALE_AND_TRANSLATE_LOG = "scale-and-translate-log";
  
  private BrightnessCorrectionImplementation mImplementation;
  
  private BrightnessCorrection(BrightnessCorrectionImplementation paramBrightnessCorrectionImplementation) {
    this.mImplementation = paramBrightnessCorrectionImplementation;
  }
  
  public static BrightnessCorrection createScaleAndTranslateLog(float paramFloat1, float paramFloat2) {
    return new BrightnessCorrection(new ScaleAndTranslateLog(paramFloat1, paramFloat2));
  }
  
  private static float loadFloatFromXml(XmlPullParser paramXmlPullParser, String paramString) {
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    try {
      return Float.parseFloat(str);
    } catch (NullPointerException|NumberFormatException nullPointerException) {
      return Float.NaN;
    } 
  }
  
  public static BrightnessCorrection loadFromXml(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException {
    int i = paramXmlPullParser.getDepth();
    while (XmlUtils.nextElementWithin(paramXmlPullParser, i)) {
      if ("scale-and-translate-log".equals(paramXmlPullParser.getName()))
        return ScaleAndTranslateLog.loadFromXml(paramXmlPullParser); 
    } 
    return null;
  }
  
  public float apply(float paramFloat) {
    return this.mImplementation.apply(paramFloat);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this) ? true : (!(paramObject instanceof BrightnessCorrection) ? false : ((BrightnessCorrection)paramObject).mImplementation.equals(this.mImplementation));
  }
  
  public int hashCode() {
    return this.mImplementation.hashCode();
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    this.mImplementation.saveToXml(paramXmlSerializer);
  }
  
  public String toString() {
    return this.mImplementation.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mImplementation.writeToParcel(paramParcel);
  }
  
  private static interface BrightnessCorrectionImplementation {
    float apply(float param1Float);
    
    void saveToXml(XmlSerializer param1XmlSerializer) throws IOException;
    
    String toString();
    
    void writeToParcel(Parcel param1Parcel);
  }
  
  private static class ScaleAndTranslateLog implements BrightnessCorrectionImplementation {
    private static final String ATTR_SCALE = "scale";
    
    private static final String ATTR_TRANSLATE = "translate";
    
    private static final float MAX_SCALE = 2.0F;
    
    private static final float MAX_TRANSLATE = 0.7F;
    
    private static final float MIN_SCALE = 0.5F;
    
    private static final float MIN_TRANSLATE = -0.6F;
    
    private final float mScale;
    
    private final float mTranslate;
    
    ScaleAndTranslateLog(float param1Float1, float param1Float2) {
      if (!Float.isNaN(param1Float1) && !Float.isNaN(param1Float2)) {
        this.mScale = MathUtils.constrain(param1Float1, 0.5F, 2.0F);
        this.mTranslate = MathUtils.constrain(param1Float2, -0.6F, 0.7F);
        return;
      } 
      throw new IllegalArgumentException("scale and translate must be numbers");
    }
    
    static BrightnessCorrection loadFromXml(XmlPullParser param1XmlPullParser) throws IOException, XmlPullParserException {
      return BrightnessCorrection.createScaleAndTranslateLog(BrightnessCorrection.loadFloatFromXml(param1XmlPullParser, "scale"), BrightnessCorrection.loadFloatFromXml(param1XmlPullParser, "translate"));
    }
    
    static BrightnessCorrection readFromParcel(Parcel param1Parcel) {
      return BrightnessCorrection.createScaleAndTranslateLog(param1Parcel.readFloat(), param1Parcel.readFloat());
    }
    
    public float apply(float param1Float) {
      return MathUtils.exp(this.mScale * MathUtils.log(param1Float) + this.mTranslate);
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (param1Object == this)
        return true; 
      if (!(param1Object instanceof ScaleAndTranslateLog))
        return false; 
      param1Object = param1Object;
      if (((ScaleAndTranslateLog)param1Object).mScale != this.mScale || ((ScaleAndTranslateLog)param1Object).mTranslate != this.mTranslate)
        bool = false; 
      return bool;
    }
    
    public int hashCode() {
      return (1 * 31 + Float.hashCode(this.mScale)) * 31 + Float.hashCode(this.mTranslate);
    }
    
    public void saveToXml(XmlSerializer param1XmlSerializer) throws IOException {
      param1XmlSerializer.startTag(null, "scale-and-translate-log");
      param1XmlSerializer.attribute(null, "scale", Float.toString(this.mScale));
      param1XmlSerializer.attribute(null, "translate", Float.toString(this.mTranslate));
      param1XmlSerializer.endTag(null, "scale-and-translate-log");
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
    
    public void writeToParcel(Parcel param1Parcel) {
      param1Parcel.writeInt(1);
      param1Parcel.writeFloat(this.mScale);
      param1Parcel.writeFloat(this.mTranslate);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessCorrection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */