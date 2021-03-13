package android.hardware.display;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.android.internal.util.Preconditions;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

@SystemApi
public final class BrightnessConfiguration implements Parcelable {
  private static final String ATTR_CATEGORY = "category";
  
  private static final String ATTR_COLLECT_COLOR = "collect-color";
  
  private static final String ATTR_DESCRIPTION = "description";
  
  private static final String ATTR_LUX = "lux";
  
  private static final String ATTR_MODEL_LOWER_BOUND = "model-lower-bound";
  
  private static final String ATTR_MODEL_TIMEOUT = "model-timeout";
  
  private static final String ATTR_MODEL_UPPER_BOUND = "model-upper-bound";
  
  private static final String ATTR_NITS = "nits";
  
  private static final String ATTR_PACKAGE_NAME = "package-name";
  
  public static final Parcelable.Creator<BrightnessConfiguration> CREATOR = new Parcelable.Creator<BrightnessConfiguration>() {
      public BrightnessConfiguration createFromParcel(Parcel param1Parcel) {
        BrightnessConfiguration.Builder builder = new BrightnessConfiguration.Builder(param1Parcel.createFloatArray(), param1Parcel.createFloatArray());
        int i = param1Parcel.readInt();
        byte b;
        for (b = 0; b < i; b++)
          builder.addCorrectionByPackageName(param1Parcel.readString(), (BrightnessCorrection)BrightnessCorrection.CREATOR.createFromParcel(param1Parcel)); 
        i = param1Parcel.readInt();
        for (b = 0; b < i; b++)
          builder.addCorrectionByCategory(param1Parcel.readInt(), (BrightnessCorrection)BrightnessCorrection.CREATOR.createFromParcel(param1Parcel)); 
        builder.setDescription(param1Parcel.readString());
        builder.setShouldCollectColorSamples(param1Parcel.readBoolean());
        builder.setShortTermModelTimeoutMillis(param1Parcel.readLong());
        builder.setShortTermModelLowerLuxMultiplier(param1Parcel.readFloat());
        builder.setShortTermModelUpperLuxMultiplier(param1Parcel.readFloat());
        return builder.build();
      }
      
      public BrightnessConfiguration[] newArray(int param1Int) {
        return new BrightnessConfiguration[param1Int];
      }
    };
  
  public static final long SHORT_TERM_TIMEOUT_UNSET = -1L;
  
  private static final String TAG_BRIGHTNESS_CORRECTION = "brightness-correction";
  
  private static final String TAG_BRIGHTNESS_CORRECTIONS = "brightness-corrections";
  
  private static final String TAG_BRIGHTNESS_CURVE = "brightness-curve";
  
  private static final String TAG_BRIGHTNESS_PARAMS = "brightness-params";
  
  private static final String TAG_BRIGHTNESS_POINT = "brightness-point";
  
  private final Map<Integer, BrightnessCorrection> mCorrectionsByCategory;
  
  private final Map<String, BrightnessCorrection> mCorrectionsByPackageName;
  
  private final String mDescription;
  
  private final float[] mLux;
  
  private final float[] mNits;
  
  private final float mShortTermModelLowerLuxMultiplier;
  
  private final long mShortTermModelTimeout;
  
  private final float mShortTermModelUpperLuxMultiplier;
  
  private final boolean mShouldCollectColorSamples;
  
  private BrightnessConfiguration(float[] paramArrayOffloat1, float[] paramArrayOffloat2, Map<String, BrightnessCorrection> paramMap, Map<Integer, BrightnessCorrection> paramMap1, String paramString, boolean paramBoolean, long paramLong, float paramFloat1, float paramFloat2) {
    this.mLux = paramArrayOffloat1;
    this.mNits = paramArrayOffloat2;
    this.mCorrectionsByPackageName = paramMap;
    this.mCorrectionsByCategory = paramMap1;
    this.mDescription = paramString;
    this.mShouldCollectColorSamples = paramBoolean;
    this.mShortTermModelTimeout = paramLong;
    this.mShortTermModelLowerLuxMultiplier = paramFloat1;
    this.mShortTermModelUpperLuxMultiplier = paramFloat2;
  }
  
  private boolean checkFloatEquals(float paramFloat1, float paramFloat2) {
    boolean bool = Float.isNaN(paramFloat1);
    boolean bool1 = true;
    if (bool && Float.isNaN(paramFloat2))
      return true; 
    if (paramFloat1 != paramFloat2)
      bool1 = false; 
    return bool1;
  }
  
  private static float loadFloatFromXml(XmlPullParser paramXmlPullParser, String paramString) {
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    try {
      return Float.parseFloat(str);
    } catch (NullPointerException|NumberFormatException nullPointerException) {
      return Float.NaN;
    } 
  }
  
  public static BrightnessConfiguration loadFromXml(XmlPullParser paramXmlPullParser) throws IOException, XmlPullParserException {
    ArrayList<Float> arrayList1 = new ArrayList();
    ArrayList<Float> arrayList2 = new ArrayList();
    HashMap<Object, Object> hashMap1 = new HashMap<>();
    HashMap<Object, Object> hashMap2 = new HashMap<>();
    int i = paramXmlPullParser.getDepth();
    float f1 = Float.NaN;
    float f2 = Float.NaN;
    long l = -1L;
    boolean bool = false;
    String str = null;
    while (XmlUtils.nextElementWithin(paramXmlPullParser, i)) {
      int k;
      if ("brightness-curve".equals(paramXmlPullParser.getName())) {
        str = paramXmlPullParser.getAttributeValue(null, "description");
        k = paramXmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(paramXmlPullParser, k)) {
          if (!"brightness-point".equals(paramXmlPullParser.getName()))
            continue; 
          float f3 = loadFloatFromXml(paramXmlPullParser, "lux");
          float f4 = loadFloatFromXml(paramXmlPullParser, "nits");
          arrayList1.add(Float.valueOf(f3));
          arrayList2.add(Float.valueOf(f4));
        } 
        continue;
      } 
      if ("brightness-corrections".equals(paramXmlPullParser.getName())) {
        k = paramXmlPullParser.getDepth();
        while (XmlUtils.nextElementWithin(paramXmlPullParser, k)) {
          if (!"brightness-correction".equals(paramXmlPullParser.getName()))
            continue; 
          String str1 = paramXmlPullParser.getAttributeValue(null, "package-name");
          String str2 = paramXmlPullParser.getAttributeValue(null, "category");
          BrightnessCorrection brightnessCorrection = BrightnessCorrection.loadFromXml(paramXmlPullParser);
          if (str1 != null) {
            hashMap1.put(str1, brightnessCorrection);
            continue;
          } 
          if (str2 != null)
            try {
              int m = Integer.parseInt(str2);
              try {
                hashMap2.put(Integer.valueOf(m), brightnessCorrection);
              } catch (NullPointerException|NumberFormatException nullPointerException) {}
            } catch (NullPointerException|NumberFormatException nullPointerException) {} 
        } 
        k = i;
      } else {
        k = i;
        if ("brightness-params".equals(paramXmlPullParser.getName())) {
          bool = Boolean.parseBoolean(paramXmlPullParser.getAttributeValue(null, "collect-color"));
          Long long_ = loadLongFromXml(paramXmlPullParser, "model-timeout");
          if (long_ != null)
            l = long_.longValue(); 
          f2 = loadFloatFromXml(paramXmlPullParser, "model-lower-bound");
          f1 = loadFloatFromXml(paramXmlPullParser, "model-upper-bound");
          continue;
        } 
      } 
      i = k;
    } 
    int j = arrayList1.size();
    float[] arrayOfFloat2 = new float[j];
    float[] arrayOfFloat1 = new float[j];
    for (i = 0; i < j; i++) {
      arrayOfFloat2[i] = ((Float)arrayList1.get(i)).floatValue();
      arrayOfFloat1[i] = ((Float)arrayList2.get(i)).floatValue();
    } 
    Builder builder = new Builder(arrayOfFloat2, arrayOfFloat1);
    builder.setDescription(str);
    null = hashMap1.entrySet().iterator();
    i = j;
    while (null.hasNext()) {
      Map.Entry entry = null.next();
      builder.addCorrectionByPackageName((String)entry.getKey(), (BrightnessCorrection)entry.getValue());
    } 
    for (Map.Entry<Object, Object> entry : hashMap2.entrySet())
      builder.addCorrectionByCategory(((Integer)entry.getKey()).intValue(), (BrightnessCorrection)entry.getValue()); 
    builder.setShouldCollectColorSamples(bool);
    builder.setShortTermModelTimeoutMillis(l);
    builder.setShortTermModelLowerLuxMultiplier(f2);
    builder.setShortTermModelUpperLuxMultiplier(f1);
    return builder.build();
  }
  
  private static Long loadLongFromXml(XmlPullParser paramXmlPullParser, String paramString) {
    String str = paramXmlPullParser.getAttributeValue(null, paramString);
    try {
      long l = Long.parseLong(str);
      return Long.valueOf(l);
    } catch (NullPointerException|NumberFormatException nullPointerException) {
      return null;
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof BrightnessConfiguration))
      return false; 
    paramObject = paramObject;
    if (!Arrays.equals(this.mLux, ((BrightnessConfiguration)paramObject).mLux) || !Arrays.equals(this.mNits, ((BrightnessConfiguration)paramObject).mNits) || !this.mCorrectionsByPackageName.equals(((BrightnessConfiguration)paramObject).mCorrectionsByPackageName) || !this.mCorrectionsByCategory.equals(((BrightnessConfiguration)paramObject).mCorrectionsByCategory) || !Objects.equals(this.mDescription, ((BrightnessConfiguration)paramObject).mDescription) || this.mShouldCollectColorSamples != ((BrightnessConfiguration)paramObject).mShouldCollectColorSamples || this.mShortTermModelTimeout != ((BrightnessConfiguration)paramObject).mShortTermModelTimeout || !checkFloatEquals(this.mShortTermModelLowerLuxMultiplier, ((BrightnessConfiguration)paramObject).mShortTermModelLowerLuxMultiplier) || !checkFloatEquals(this.mShortTermModelUpperLuxMultiplier, ((BrightnessConfiguration)paramObject).mShortTermModelUpperLuxMultiplier))
      bool = false; 
    return bool;
  }
  
  public BrightnessCorrection getCorrectionByCategory(int paramInt) {
    return this.mCorrectionsByCategory.get(Integer.valueOf(paramInt));
  }
  
  public BrightnessCorrection getCorrectionByPackageName(String paramString) {
    return this.mCorrectionsByPackageName.get(paramString);
  }
  
  public Pair<float[], float[]> getCurve() {
    float[] arrayOfFloat1 = this.mLux;
    arrayOfFloat1 = Arrays.copyOf(arrayOfFloat1, arrayOfFloat1.length);
    float[] arrayOfFloat2 = this.mNits;
    return Pair.create(arrayOfFloat1, Arrays.copyOf(arrayOfFloat2, arrayOfFloat2.length));
  }
  
  public String getDescription() {
    return this.mDescription;
  }
  
  public float getShortTermModelLowerLuxMultiplier() {
    return this.mShortTermModelLowerLuxMultiplier;
  }
  
  public long getShortTermModelTimeoutMillis() {
    return this.mShortTermModelTimeout;
  }
  
  public float getShortTermModelUpperLuxMultiplier() {
    return this.mShortTermModelUpperLuxMultiplier;
  }
  
  public int hashCode() {
    int i = (((1 * 31 + Arrays.hashCode(this.mLux)) * 31 + Arrays.hashCode(this.mNits)) * 31 + this.mCorrectionsByPackageName.hashCode()) * 31 + this.mCorrectionsByCategory.hashCode();
    String str = this.mDescription;
    int j = i;
    if (str != null)
      j = i * 31 + str.hashCode(); 
    return (((j * 31 + Boolean.hashCode(this.mShouldCollectColorSamples)) * 31 + Long.hashCode(this.mShortTermModelTimeout)) * 31 + Float.hashCode(this.mShortTermModelLowerLuxMultiplier)) * 31 + Float.hashCode(this.mShortTermModelUpperLuxMultiplier);
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.startTag(null, "brightness-curve");
    String str = this.mDescription;
    if (str != null)
      paramXmlSerializer.attribute(null, "description", str); 
    int i;
    for (i = 0; i < this.mLux.length; i++) {
      paramXmlSerializer.startTag(null, "brightness-point");
      paramXmlSerializer.attribute(null, "lux", Float.toString(this.mLux[i]));
      paramXmlSerializer.attribute(null, "nits", Float.toString(this.mNits[i]));
      paramXmlSerializer.endTag(null, "brightness-point");
    } 
    paramXmlSerializer.endTag(null, "brightness-curve");
    paramXmlSerializer.startTag(null, "brightness-corrections");
    for (Map.Entry<String, BrightnessCorrection> entry : this.mCorrectionsByPackageName.entrySet()) {
      str = (String)entry.getKey();
      BrightnessCorrection brightnessCorrection = (BrightnessCorrection)entry.getValue();
      paramXmlSerializer.startTag(null, "brightness-correction");
      paramXmlSerializer.attribute(null, "package-name", str);
      brightnessCorrection.saveToXml(paramXmlSerializer);
      paramXmlSerializer.endTag(null, "brightness-correction");
    } 
    for (Map.Entry<Integer, BrightnessCorrection> entry : this.mCorrectionsByCategory.entrySet()) {
      i = ((Integer)entry.getKey()).intValue();
      BrightnessCorrection brightnessCorrection = (BrightnessCorrection)entry.getValue();
      paramXmlSerializer.startTag(null, "brightness-correction");
      paramXmlSerializer.attribute(null, "category", Integer.toString(i));
      brightnessCorrection.saveToXml(paramXmlSerializer);
      paramXmlSerializer.endTag(null, "brightness-correction");
    } 
    paramXmlSerializer.endTag(null, "brightness-corrections");
    paramXmlSerializer.startTag(null, "brightness-params");
    if (this.mShouldCollectColorSamples)
      paramXmlSerializer.attribute(null, "collect-color", Boolean.toString(true)); 
    long l = this.mShortTermModelTimeout;
    if (l >= 0L)
      paramXmlSerializer.attribute(null, "model-timeout", Long.toString(l)); 
    if (!Float.isNaN(this.mShortTermModelLowerLuxMultiplier))
      paramXmlSerializer.attribute(null, "model-lower-bound", Float.toString(this.mShortTermModelLowerLuxMultiplier)); 
    if (!Float.isNaN(this.mShortTermModelUpperLuxMultiplier))
      paramXmlSerializer.attribute(null, "model-upper-bound", Float.toString(this.mShortTermModelUpperLuxMultiplier)); 
    paramXmlSerializer.endTag(null, "brightness-params");
  }
  
  public boolean shouldCollectColorSamples() {
    return this.mShouldCollectColorSamples;
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder("BrightnessConfiguration{[");
    int i = this.mLux.length;
    for (byte b = 0; b < i; b++) {
      if (b != 0)
        stringBuilder1.append(", "); 
      stringBuilder1.append("(");
      stringBuilder1.append(this.mLux[b]);
      stringBuilder1.append(", ");
      stringBuilder1.append(this.mNits[b]);
      stringBuilder1.append(")");
    } 
    stringBuilder1.append("], {");
    for (Map.Entry<String, BrightnessCorrection> entry : this.mCorrectionsByPackageName.entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("'");
      stringBuilder.append((String)entry.getKey());
      stringBuilder.append("': ");
      stringBuilder.append(entry.getValue());
      stringBuilder.append(", ");
      stringBuilder1.append(stringBuilder.toString());
    } 
    for (Map.Entry<Integer, BrightnessCorrection> entry : this.mCorrectionsByCategory.entrySet()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(entry.getKey());
      stringBuilder.append(": ");
      stringBuilder.append(entry.getValue());
      stringBuilder.append(", ");
      stringBuilder1.append(stringBuilder.toString());
    } 
    stringBuilder1.append("}, '");
    String str = this.mDescription;
    if (str != null)
      stringBuilder1.append(str); 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(", shouldCollectColorSamples = ");
    stringBuilder2.append(this.mShouldCollectColorSamples);
    stringBuilder1.append(stringBuilder2.toString());
    if (this.mShortTermModelTimeout >= 0L) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", shortTermModelTimeout = ");
      stringBuilder2.append(this.mShortTermModelTimeout);
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (!Float.isNaN(this.mShortTermModelLowerLuxMultiplier)) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", shortTermModelLowerLuxMultiplier = ");
      stringBuilder2.append(this.mShortTermModelLowerLuxMultiplier);
      stringBuilder1.append(stringBuilder2.toString());
    } 
    if (!Float.isNaN(this.mShortTermModelLowerLuxMultiplier)) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(", shortTermModelUpperLuxMultiplier = ");
      stringBuilder2.append(this.mShortTermModelUpperLuxMultiplier);
      stringBuilder1.append(stringBuilder2.toString());
    } 
    stringBuilder1.append("'}");
    return stringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeFloatArray(this.mLux);
    paramParcel.writeFloatArray(this.mNits);
    paramParcel.writeInt(this.mCorrectionsByPackageName.size());
    for (Map.Entry<String, BrightnessCorrection> entry : this.mCorrectionsByPackageName.entrySet()) {
      String str = (String)entry.getKey();
      BrightnessCorrection brightnessCorrection = (BrightnessCorrection)entry.getValue();
      paramParcel.writeString(str);
      brightnessCorrection.writeToParcel(paramParcel, paramInt);
    } 
    paramParcel.writeInt(this.mCorrectionsByCategory.size());
    for (Map.Entry<Integer, BrightnessCorrection> entry : this.mCorrectionsByCategory.entrySet()) {
      int i = ((Integer)entry.getKey()).intValue();
      BrightnessCorrection brightnessCorrection = (BrightnessCorrection)entry.getValue();
      paramParcel.writeInt(i);
      brightnessCorrection.writeToParcel(paramParcel, paramInt);
    } 
    paramParcel.writeString(this.mDescription);
    paramParcel.writeBoolean(this.mShouldCollectColorSamples);
    paramParcel.writeLong(this.mShortTermModelTimeout);
    paramParcel.writeFloat(this.mShortTermModelLowerLuxMultiplier);
    paramParcel.writeFloat(this.mShortTermModelUpperLuxMultiplier);
  }
  
  public static class Builder {
    private static final int MAX_CORRECTIONS_BY_CATEGORY = 20;
    
    private static final int MAX_CORRECTIONS_BY_PACKAGE_NAME = 20;
    
    private Map<Integer, BrightnessCorrection> mCorrectionsByCategory;
    
    private Map<String, BrightnessCorrection> mCorrectionsByPackageName;
    
    private float[] mCurveLux;
    
    private float[] mCurveNits;
    
    private String mDescription;
    
    private float mShortTermModelLowerLuxMultiplier = Float.NaN;
    
    private long mShortTermModelTimeout = -1L;
    
    private float mShortTermModelUpperLuxMultiplier = Float.NaN;
    
    private boolean mShouldCollectColorSamples;
    
    public Builder(float[] param1ArrayOffloat1, float[] param1ArrayOffloat2) {
      Objects.requireNonNull(param1ArrayOffloat1);
      Objects.requireNonNull(param1ArrayOffloat2);
      if (param1ArrayOffloat1.length != 0 && param1ArrayOffloat2.length != 0) {
        if (param1ArrayOffloat1.length == param1ArrayOffloat2.length) {
          if (param1ArrayOffloat1[0] == 0.0F) {
            Preconditions.checkArrayElementsInRange(param1ArrayOffloat1, 0.0F, Float.MAX_VALUE, "lux");
            Preconditions.checkArrayElementsInRange(param1ArrayOffloat2, 0.0F, Float.MAX_VALUE, "nits");
            checkMonotonic(param1ArrayOffloat1, true, "lux");
            checkMonotonic(param1ArrayOffloat2, false, "nits");
            this.mCurveLux = param1ArrayOffloat1;
            this.mCurveNits = param1ArrayOffloat2;
            this.mCorrectionsByPackageName = new HashMap<>();
            this.mCorrectionsByCategory = new HashMap<>();
            return;
          } 
          throw new IllegalArgumentException("Initial control point must be for 0 lux");
        } 
        throw new IllegalArgumentException("Lux and nits arrays must be the same length");
      } 
      throw new IllegalArgumentException("Lux and nits arrays must not be empty");
    }
    
    private static void checkMonotonic(float[] param1ArrayOffloat, boolean param1Boolean, String param1String) {
      if (param1ArrayOffloat.length <= 1)
        return; 
      float f = param1ArrayOffloat[0];
      for (byte b = 1; b < param1ArrayOffloat.length; b++) {
        String str1;
        if (f > param1ArrayOffloat[b] || (f == param1ArrayOffloat[b] && param1Boolean)) {
          if (param1Boolean) {
            str1 = "strictly increasing";
          } else {
            str1 = "monotonic";
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(param1String);
          stringBuilder.append(" values must be ");
          stringBuilder.append(str1);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        String str2 = str1[b];
      } 
    }
    
    public Builder addCorrectionByCategory(int param1Int, BrightnessCorrection param1BrightnessCorrection) {
      Objects.requireNonNull(param1BrightnessCorrection, "correction must not be null");
      if (this.mCorrectionsByCategory.size() < getMaxCorrectionsByCategory()) {
        this.mCorrectionsByCategory.put(Integer.valueOf(param1Int), param1BrightnessCorrection);
        return this;
      } 
      throw new IllegalArgumentException("Too many corrections by category");
    }
    
    public Builder addCorrectionByPackageName(String param1String, BrightnessCorrection param1BrightnessCorrection) {
      Objects.requireNonNull(param1String, "packageName must not be null");
      Objects.requireNonNull(param1BrightnessCorrection, "correction must not be null");
      if (this.mCorrectionsByPackageName.size() < getMaxCorrectionsByPackageName()) {
        this.mCorrectionsByPackageName.put(param1String, param1BrightnessCorrection);
        return this;
      } 
      throw new IllegalArgumentException("Too many corrections by package name");
    }
    
    public BrightnessConfiguration build() {
      if (this.mCurveLux != null && this.mCurveNits != null)
        return new BrightnessConfiguration(this.mCurveLux, this.mCurveNits, this.mCorrectionsByPackageName, this.mCorrectionsByCategory, this.mDescription, this.mShouldCollectColorSamples, this.mShortTermModelTimeout, this.mShortTermModelLowerLuxMultiplier, this.mShortTermModelUpperLuxMultiplier); 
      throw new IllegalStateException("A curve must be set!");
    }
    
    public int getMaxCorrectionsByCategory() {
      return 20;
    }
    
    public int getMaxCorrectionsByPackageName() {
      return 20;
    }
    
    public Builder setDescription(String param1String) {
      this.mDescription = param1String;
      return this;
    }
    
    public Builder setShortTermModelLowerLuxMultiplier(float param1Float) {
      if (param1Float >= 0.0F) {
        this.mShortTermModelLowerLuxMultiplier = param1Float;
        return this;
      } 
      throw new IllegalArgumentException("Negative lux multiplier");
    }
    
    public Builder setShortTermModelTimeoutMillis(long param1Long) {
      this.mShortTermModelTimeout = param1Long;
      return this;
    }
    
    public Builder setShortTermModelUpperLuxMultiplier(float param1Float) {
      if (param1Float >= 0.0F) {
        this.mShortTermModelUpperLuxMultiplier = param1Float;
        return this;
      } 
      throw new IllegalArgumentException("Negative lux multiplier");
    }
    
    public Builder setShouldCollectColorSamples(boolean param1Boolean) {
      this.mShouldCollectColorSamples = param1Boolean;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */