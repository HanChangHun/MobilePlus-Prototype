package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.StateSet;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StateListDrawable extends DrawableContainer {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "StateListDrawable";
  
  private boolean mMutated;
  
  private StateListState mStateListState;
  
  public StateListDrawable() {
    this((StateListState)null, (Resources)null);
  }
  
  StateListDrawable(StateListState paramStateListState) {
    if (paramStateListState != null)
      setConstantState(paramStateListState); 
  }
  
  private StateListDrawable(StateListState paramStateListState, Resources paramResources) {
    setConstantState(new StateListState(paramStateListState, this, paramResources));
    onStateChange(getState());
  }
  
  private void inflateChildElements(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    StateListState stateListState = this.mStateListState;
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.StateListDrawableItem);
          Drawable drawable2 = typedArray.getDrawable(0);
          typedArray.recycle();
          int[] arrayOfInt = extractStateSet(paramAttributeSet);
          Drawable drawable1 = drawable2;
          if (drawable2 == null)
            while (true) {
              j = paramXmlPullParser.next();
              if (j == 4)
                continue; 
              if (j == 2) {
                drawable1 = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
                break;
              } 
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(paramXmlPullParser.getPositionDescription());
              stringBuilder.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
              throw new XmlPullParserException(stringBuilder.toString());
            }  
          stateListState.addStateSet(arrayOfInt, drawable1);
          continue;
        } 
      } 
      break;
    } 
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    StateListState stateListState = this.mStateListState;
    stateListState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    stateListState.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    stateListState.mVariablePadding = paramTypedArray.getBoolean(2, stateListState.mVariablePadding);
    stateListState.mConstantSize = paramTypedArray.getBoolean(3, stateListState.mConstantSize);
    stateListState.mEnterFadeDuration = paramTypedArray.getInt(4, stateListState.mEnterFadeDuration);
    stateListState.mExitFadeDuration = paramTypedArray.getInt(5, stateListState.mExitFadeDuration);
    stateListState.mDither = paramTypedArray.getBoolean(0, stateListState.mDither);
    stateListState.mAutoMirrored = paramTypedArray.getBoolean(6, stateListState.mAutoMirrored);
  }
  
  public void addState(int[] paramArrayOfint, Drawable paramDrawable) {
    if (paramDrawable != null) {
      this.mStateListState.addStateSet(paramArrayOfint, paramDrawable);
      onStateChange(getState());
    } 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    onStateChange(getState());
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  StateListState cloneConstantState() {
    return new StateListState(this.mStateListState, this, null);
  }
  
  int[] extractStateSet(AttributeSet paramAttributeSet) {
    byte b1 = 0;
    int i = paramAttributeSet.getAttributeCount();
    int[] arrayOfInt = new int[i];
    for (byte b2 = 0; b2 < i; b2++) {
      int j = paramAttributeSet.getAttributeNameResource(b2);
      if (j != 0 && j != 16842960 && j != 16843161) {
        if (!paramAttributeSet.getAttributeBooleanValue(b2, false))
          j = -j; 
        arrayOfInt[b1] = j;
        b1++;
      } 
    } 
    return StateSet.trimStateSet(arrayOfInt, b1);
  }
  
  public int findStateDrawableIndex(int[] paramArrayOfint) {
    return this.mStateListState.indexOfStateSet(paramArrayOfint);
  }
  
  public int getStateCount() {
    return this.mStateListState.getChildCount();
  }
  
  public Drawable getStateDrawable(int paramInt) {
    return this.mStateListState.getChild(paramInt);
  }
  
  StateListState getStateListState() {
    return this.mStateListState;
  }
  
  public int[] getStateSet(int paramInt) {
    return this.mStateListState.mStateSets[paramInt];
  }
  
  public boolean hasFocusStateSpecified() {
    return this.mStateListState.hasFocusStateSpecified();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.StateListDrawable);
    inflateWithAttributes(paramResources, paramXmlPullParser, typedArray, 1);
    updateStateFromTypedArray(typedArray);
    updateDensity(paramResources);
    typedArray.recycle();
    inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    onStateChange(getState());
  }
  
  public boolean isStateful() {
    return true;
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mStateListState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    null = super.onStateChange(paramArrayOfint);
    int i = this.mStateListState.indexOfStateSet(paramArrayOfint);
    int j = i;
    if (i < 0)
      j = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD); 
    return (selectDrawable(j) || null);
  }
  
  protected void setConstantState(DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof StateListState)
      this.mStateListState = (StateListState)paramDrawableContainerState; 
  }
  
  static class StateListState extends DrawableContainer.DrawableContainerState {
    int[][] mStateSets;
    
    int[] mThemeAttrs;
    
    StateListState(StateListState param1StateListState, StateListDrawable param1StateListDrawable, Resources param1Resources) {
      super(param1StateListState, param1StateListDrawable, param1Resources);
      if (param1StateListState != null) {
        this.mThemeAttrs = param1StateListState.mThemeAttrs;
        this.mStateSets = param1StateListState.mStateSets;
      } else {
        this.mThemeAttrs = null;
        this.mStateSets = new int[getCapacity()][];
      } 
    }
    
    int addStateSet(int[] param1ArrayOfint, Drawable param1Drawable) {
      int i = addChild(param1Drawable);
      this.mStateSets[i] = param1ArrayOfint;
      return i;
    }
    
    public boolean canApplyTheme() {
      return (this.mThemeAttrs != null || super.canApplyTheme());
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      super.growArray(param1Int1, param1Int2);
      int[][] arrayOfInt = new int[param1Int2][];
      System.arraycopy(this.mStateSets, 0, arrayOfInt, 0, param1Int1);
      this.mStateSets = arrayOfInt;
    }
    
    boolean hasFocusStateSpecified() {
      return StateSet.containsAttribute(this.mStateSets, 16842908);
    }
    
    int indexOfStateSet(int[] param1ArrayOfint) {
      int[][] arrayOfInt = this.mStateSets;
      int i = getChildCount();
      for (byte b = 0; b < i; b++) {
        if (StateSet.stateSetMatches(arrayOfInt[b], param1ArrayOfint))
          return b; 
      } 
      return -1;
    }
    
    void mutate() {
      int[] arrayOfInt = this.mThemeAttrs;
      if (arrayOfInt != null) {
        arrayOfInt = (int[])arrayOfInt.clone();
      } else {
        arrayOfInt = null;
      } 
      this.mThemeAttrs = arrayOfInt;
      int[][] arrayOfInt1 = this.mStateSets;
      int[][] arrayOfInt2 = new int[arrayOfInt1.length][];
      for (int i = arrayOfInt1.length - 1; i >= 0; i--) {
        arrayOfInt1 = this.mStateSets;
        if (arrayOfInt1[i] != null) {
          int[] arrayOfInt3 = (int[])arrayOfInt1[i].clone();
        } else {
          arrayOfInt1 = null;
        } 
        arrayOfInt2[i] = (int[])arrayOfInt1;
      } 
      this.mStateSets = arrayOfInt2;
    }
    
    public Drawable newDrawable() {
      return new StateListDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new StateListDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/StateListDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */