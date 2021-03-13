package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class LevelListDrawable extends DrawableContainer {
  private LevelListState mLevelListState;
  
  private boolean mMutated;
  
  public LevelListDrawable() {
    this((LevelListState)null, (Resources)null);
  }
  
  private LevelListDrawable(LevelListState paramLevelListState, Resources paramResources) {
    setConstantState(new LevelListState(paramLevelListState, this, paramResources));
    onLevelChange(getLevel());
  }
  
  private void inflateChildElements(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.LevelListDrawableItem);
          j = typedArray.getInt(1, 0);
          k = typedArray.getInt(2, 0);
          int m = typedArray.getResourceId(0, 0);
          typedArray.recycle();
          if (k >= 0) {
            Drawable drawable;
            if (m != 0) {
              drawable = paramResources.getDrawable(m, paramTheme);
            } else {
              while (true) {
                m = paramXmlPullParser.next();
                if (m == 4)
                  continue; 
                if (m == 2) {
                  drawable = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
                  break;
                } 
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(paramXmlPullParser.getPositionDescription());
                stringBuilder1.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                throw new XmlPullParserException(stringBuilder1.toString());
              } 
            } 
            this.mLevelListState.addLevel(j, k, drawable);
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramXmlPullParser.getPositionDescription());
          stringBuilder.append(": <item> tag requires a 'maxLevel' attribute");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
      } 
      break;
    } 
    onLevelChange(getLevel());
  }
  
  public void addLevel(int paramInt1, int paramInt2, Drawable paramDrawable) {
    if (paramDrawable != null) {
      this.mLevelListState.addLevel(paramInt1, paramInt2, paramDrawable);
      onLevelChange(getLevel());
    } 
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  LevelListState cloneConstantState() {
    return new LevelListState(this.mLevelListState, this, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateDensity(paramResources);
    inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mLevelListState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onLevelChange(int paramInt) {
    return selectDrawable(this.mLevelListState.indexOfLevel(paramInt)) ? true : super.onLevelChange(paramInt);
  }
  
  protected void setConstantState(DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof LevelListState)
      this.mLevelListState = (LevelListState)paramDrawableContainerState; 
  }
  
  private static final class LevelListState extends DrawableContainer.DrawableContainerState {
    private int[] mHighs;
    
    private int[] mLows;
    
    LevelListState(LevelListState param1LevelListState, LevelListDrawable param1LevelListDrawable, Resources param1Resources) {
      super(param1LevelListState, param1LevelListDrawable, param1Resources);
      if (param1LevelListState != null) {
        this.mLows = param1LevelListState.mLows;
        this.mHighs = param1LevelListState.mHighs;
      } else {
        this.mLows = new int[getCapacity()];
        this.mHighs = new int[getCapacity()];
      } 
    }
    
    private void mutate() {
      this.mLows = (int[])this.mLows.clone();
      this.mHighs = (int[])this.mHighs.clone();
    }
    
    public void addLevel(int param1Int1, int param1Int2, Drawable param1Drawable) {
      int i = addChild(param1Drawable);
      this.mLows[i] = param1Int1;
      this.mHighs[i] = param1Int2;
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      super.growArray(param1Int1, param1Int2);
      int[] arrayOfInt = new int[param1Int2];
      System.arraycopy(this.mLows, 0, arrayOfInt, 0, param1Int1);
      this.mLows = arrayOfInt;
      arrayOfInt = new int[param1Int2];
      System.arraycopy(this.mHighs, 0, arrayOfInt, 0, param1Int1);
      this.mHighs = arrayOfInt;
    }
    
    public int indexOfLevel(int param1Int) {
      int[] arrayOfInt1 = this.mLows;
      int[] arrayOfInt2 = this.mHighs;
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        if (param1Int >= arrayOfInt1[b] && param1Int <= arrayOfInt2[b])
          return b; 
      } 
      return -1;
    }
    
    public Drawable newDrawable() {
      return new LevelListDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new LevelListDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/LevelListDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */