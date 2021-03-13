package android.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.InflateException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableInflater {
  private static final HashMap<String, Constructor<? extends Drawable>> CONSTRUCTOR_MAP = new HashMap<>();
  
  private final ClassLoader mClassLoader;
  
  private final Resources mRes;
  
  public DrawableInflater(Resources paramResources, ClassLoader paramClassLoader) {
    this.mRes = paramResources;
    this.mClassLoader = paramClassLoader;
  }
  
  private Drawable inflateFromClass(String paramString) {
    InflateException inflateException;
    try {
      synchronized (CONSTRUCTOR_MAP) {
        Constructor<? extends Drawable> constructor1 = CONSTRUCTOR_MAP.get(paramString);
        Constructor<? extends Drawable> constructor2 = constructor1;
        if (constructor1 == null) {
          constructor2 = this.mClassLoader.loadClass(paramString).<Drawable>asSubclass(Drawable.class).getConstructor(new Class[0]);
          CONSTRUCTOR_MAP.put(paramString, constructor2);
        } 
        return constructor2.newInstance(new Object[0]);
      } 
    } catch (NoSuchMethodException noSuchMethodException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error inflating class ");
      stringBuilder.append(paramString);
      inflateException = new InflateException(stringBuilder.toString());
      inflateException.initCause(noSuchMethodException);
      throw inflateException;
    } catch (ClassCastException classCastException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Class is not a Drawable ");
      stringBuilder.append((String)inflateException);
      inflateException = new InflateException(stringBuilder.toString());
      inflateException.initCause(classCastException);
      throw inflateException;
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Class not found ");
      stringBuilder.append((String)inflateException);
      inflateException = new InflateException(stringBuilder.toString());
      inflateException.initCause(classNotFoundException);
      throw inflateException;
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error inflating class ");
      stringBuilder.append((String)inflateException);
      inflateException = new InflateException(stringBuilder.toString());
      inflateException.initCause(exception);
      throw inflateException;
    } 
  }
  
  private Drawable inflateFromTag(String paramString) {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 2118620333:
        if (paramString.equals("animated-vector")) {
          b = 10;
          break;
        } 
      case 2013827269:
        if (paramString.equals("animated-rotate")) {
          b = 14;
          break;
        } 
      case 1442046129:
        if (paramString.equals("animated-image")) {
          b = 19;
          break;
        } 
      case 1191572447:
        if (paramString.equals("selector")) {
          b = 0;
          break;
        } 
      case 160680263:
        if (paramString.equals("level-list")) {
          b = 2;
          break;
        } 
      case 109399969:
        if (paramString.equals("shape")) {
          b = 8;
          break;
        } 
      case 109250890:
        if (paramString.equals("scale")) {
          b = 11;
          break;
        } 
      case 100360477:
        if (paramString.equals("inset")) {
          b = 16;
          break;
        } 
      case 94842723:
        if (paramString.equals("color")) {
          b = 7;
          break;
        } 
      case 3056464:
        if (paramString.equals("clip")) {
          b = 12;
          break;
        } 
      case -94197862:
        if (paramString.equals("layer-list")) {
          b = 3;
          break;
        } 
      case -510364471:
        if (paramString.equals("animated-selector")) {
          b = 1;
          break;
        } 
      case -820387517:
        if (paramString.equals("vector")) {
          b = 9;
          break;
        } 
      case -925180581:
        if (paramString.equals("rotate")) {
          b = 13;
          break;
        } 
      case -930826704:
        if (paramString.equals("ripple")) {
          b = 5;
          break;
        } 
      case -1388777169:
        if (paramString.equals("bitmap")) {
          b = 17;
          break;
        } 
      case -1493546681:
        if (paramString.equals("animation-list")) {
          b = 15;
          break;
        } 
      case -1671889043:
        if (paramString.equals("nine-patch")) {
          b = 18;
          break;
        } 
      case -1724158635:
        if (paramString.equals("transition")) {
          b = 4;
          break;
        } 
      case -2024464016:
        if (paramString.equals("adaptive-icon")) {
          b = 6;
          break;
        } 
    } 
    switch (b) {
      default:
        return null;
      case 19:
        return new AnimatedImageDrawable();
      case 18:
        return new NinePatchDrawable();
      case 17:
        return new BitmapDrawable();
      case 16:
        return new InsetDrawable();
      case 15:
        return new AnimationDrawable();
      case 14:
        return new AnimatedRotateDrawable();
      case 13:
        return new RotateDrawable();
      case 12:
        return new ClipDrawable();
      case 11:
        return new ScaleDrawable();
      case 10:
        return new AnimatedVectorDrawable();
      case 9:
        return new VectorDrawable();
      case 8:
        return new GradientDrawable();
      case 7:
        return new ColorDrawable();
      case 6:
        return new AdaptiveIconDrawable();
      case 5:
        return new RippleDrawable();
      case 4:
        return new TransitionDrawable();
      case 3:
        return new LayerDrawable();
      case 2:
        return new LevelListDrawable();
      case 1:
        return new AnimatedStateListDrawable();
      case 0:
        break;
    } 
    return new StateListDrawable();
  }
  
  public static Drawable loadDrawable(Context paramContext, int paramInt) {
    return loadDrawable(paramContext.getResources(), paramContext.getTheme(), paramInt);
  }
  
  public static Drawable loadDrawable(Resources paramResources, Resources.Theme paramTheme, int paramInt) {
    return paramResources.getDrawable(paramInt, paramTheme);
  }
  
  public Drawable inflateFromXml(String paramString, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    return inflateFromXmlForDensity(paramString, paramXmlPullParser, paramAttributeSet, 0, paramTheme);
  }
  
  Drawable inflateFromXmlForDensity(String paramString, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, int paramInt, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    String str = paramString;
    if (paramString.equals("drawable")) {
      str = paramAttributeSet.getAttributeValue(null, "class");
      if (str == null)
        throw new InflateException("<drawable> tag must specify class attribute"); 
    } 
    Drawable drawable2 = inflateFromTag(str);
    Drawable drawable1 = drawable2;
    if (drawable2 == null)
      drawable1 = inflateFromClass(str); 
    drawable1.setSrcDensityOverride(paramInt);
    drawable1.inflate(this.mRes, paramXmlPullParser, paramAttributeSet, paramTheme);
    return drawable1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/DrawableInflater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */