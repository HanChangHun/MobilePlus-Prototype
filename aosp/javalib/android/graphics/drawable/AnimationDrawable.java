package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimationDrawable extends DrawableContainer implements Runnable, Animatable {
  private boolean mAnimating;
  
  private AnimationState mAnimationState;
  
  private int mCurFrame = 0;
  
  private boolean mMutated;
  
  private boolean mRunning;
  
  public AnimationDrawable() {
    this((AnimationState)null, (Resources)null);
  }
  
  private AnimationDrawable(AnimationState paramAnimationState, Resources paramResources) {
    setConstantState(new AnimationState(paramAnimationState, this, paramResources));
    if (paramAnimationState != null)
      setFrame(0, true, false); 
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
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimationDrawableItem);
          k = typedArray.getInt(0, -1);
          if (k >= 0) {
            Drawable drawable2 = typedArray.getDrawable(1);
            typedArray.recycle();
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
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(paramXmlPullParser.getPositionDescription());
                stringBuilder1.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                throw new XmlPullParserException(stringBuilder1.toString());
              }  
            this.mAnimationState.addFrame(drawable1, k);
            if (drawable1 != null)
              drawable1.setCallback(this); 
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(paramXmlPullParser.getPositionDescription());
          stringBuilder.append(": <item> tag requires a 'duration' attribute");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
      } 
      break;
    } 
  }
  
  private void nextFrame(boolean paramBoolean) {
    int i = this.mCurFrame;
    boolean bool = true;
    int j = i + 1;
    int k = this.mAnimationState.getChildCount();
    if (this.mAnimationState.mOneShot && j >= k - 1) {
      i = 1;
    } else {
      i = 0;
    } 
    int m = j;
    if (!this.mAnimationState.mOneShot) {
      m = j;
      if (j >= k)
        m = 0; 
    } 
    if (i != 0)
      bool = false; 
    setFrame(m, paramBoolean, bool);
  }
  
  private void setFrame(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramInt >= this.mAnimationState.getChildCount())
      return; 
    this.mAnimating = paramBoolean2;
    this.mCurFrame = paramInt;
    selectDrawable(paramInt);
    if (paramBoolean1 || paramBoolean2)
      unscheduleSelf(this); 
    if (paramBoolean2) {
      this.mCurFrame = paramInt;
      this.mRunning = true;
      scheduleSelf(this, SystemClock.uptimeMillis() + this.mAnimationState.mDurations[paramInt]);
    } 
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    AnimationState animationState = this.mAnimationState;
    animationState.mVariablePadding = paramTypedArray.getBoolean(1, animationState.mVariablePadding);
    animationState = this.mAnimationState;
    AnimationState.access$002(animationState, paramTypedArray.getBoolean(2, animationState.mOneShot));
  }
  
  public void addFrame(Drawable paramDrawable, int paramInt) {
    this.mAnimationState.addFrame(paramDrawable, paramInt);
    if (!this.mRunning)
      setFrame(0, true, false); 
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  AnimationState cloneConstantState() {
    return new AnimationState(this.mAnimationState, this, null);
  }
  
  public int getDuration(int paramInt) {
    return this.mAnimationState.mDurations[paramInt];
  }
  
  public Drawable getFrame(int paramInt) {
    return this.mAnimationState.getChild(paramInt);
  }
  
  public int getNumberOfFrames() {
    return this.mAnimationState.getChildCount();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimationDrawable);
    inflateWithAttributes(paramResources, paramXmlPullParser, typedArray, 0);
    updateStateFromTypedArray(typedArray);
    updateDensity(paramResources);
    typedArray.recycle();
    inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    setFrame(0, true, false);
  }
  
  public boolean isOneShot() {
    return this.mAnimationState.mOneShot;
  }
  
  public boolean isRunning() {
    return this.mRunning;
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mAnimationState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  public void run() {
    nextFrame(false);
  }
  
  protected void setConstantState(DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof AnimationState)
      this.mAnimationState = (AnimationState)paramDrawableContainerState; 
  }
  
  public void setOneShot(boolean paramBoolean) {
    AnimationState.access$002(this.mAnimationState, paramBoolean);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (paramBoolean1) {
      if (paramBoolean2 || bool) {
        int i;
        byte b = 0;
        if (paramBoolean2 || (!this.mRunning && !this.mAnimationState.mOneShot) || this.mCurFrame >= this.mAnimationState.getChildCount()) {
          i = 1;
        } else {
          i = 0;
        } 
        if (i) {
          i = b;
        } else {
          i = this.mCurFrame;
        } 
        setFrame(i, true, this.mAnimating);
      } 
    } else {
      unscheduleSelf(this);
    } 
    return bool;
  }
  
  public void start() {
    boolean bool = true;
    this.mAnimating = true;
    if (!isRunning()) {
      if (this.mAnimationState.getChildCount() <= 1 && this.mAnimationState.mOneShot)
        bool = false; 
      setFrame(0, false, bool);
    } 
  }
  
  public void stop() {
    this.mAnimating = false;
    if (isRunning()) {
      this.mCurFrame = 0;
      unscheduleSelf(this);
    } 
  }
  
  public void unscheduleSelf(Runnable paramRunnable) {
    this.mRunning = false;
    super.unscheduleSelf(paramRunnable);
  }
  
  private static final class AnimationState extends DrawableContainer.DrawableContainerState {
    private int[] mDurations;
    
    private boolean mOneShot = false;
    
    AnimationState(AnimationState param1AnimationState, AnimationDrawable param1AnimationDrawable, Resources param1Resources) {
      super(param1AnimationState, param1AnimationDrawable, param1Resources);
      if (param1AnimationState != null) {
        this.mDurations = param1AnimationState.mDurations;
        this.mOneShot = param1AnimationState.mOneShot;
      } else {
        this.mDurations = new int[getCapacity()];
        this.mOneShot = false;
      } 
    }
    
    private void mutate() {
      this.mDurations = (int[])this.mDurations.clone();
    }
    
    public void addFrame(Drawable param1Drawable, int param1Int) {
      int i = addChild(param1Drawable);
      this.mDurations[i] = param1Int;
    }
    
    public void growArray(int param1Int1, int param1Int2) {
      super.growArray(param1Int1, param1Int2);
      int[] arrayOfInt = new int[param1Int2];
      System.arraycopy(this.mDurations, 0, arrayOfInt, 0, param1Int1);
      this.mDurations = arrayOfInt;
    }
    
    public Drawable newDrawable() {
      return new AnimationDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new AnimationDrawable(this, param1Resources);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimationDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */