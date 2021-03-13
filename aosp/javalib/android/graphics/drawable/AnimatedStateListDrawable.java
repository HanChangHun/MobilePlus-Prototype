package android.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.LongSparseLongArray;
import android.util.SparseIntArray;
import android.util.StateSet;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedStateListDrawable extends StateListDrawable {
  private static final String ELEMENT_ITEM = "item";
  
  private static final String ELEMENT_TRANSITION = "transition";
  
  private static final String LOGTAG = AnimatedStateListDrawable.class.getSimpleName();
  
  private boolean mMutated;
  
  private AnimatedStateListState mState;
  
  private Transition mTransition;
  
  private int mTransitionFromIndex = -1;
  
  private int mTransitionToIndex = -1;
  
  public AnimatedStateListDrawable() {
    this((AnimatedStateListState)null, (Resources)null);
  }
  
  private AnimatedStateListDrawable(AnimatedStateListState paramAnimatedStateListState, Resources paramResources) {
    super((StateListDrawable.StateListState)null);
    setConstantState(new AnimatedStateListState(paramAnimatedStateListState, this, paramResources));
    onStateChange(getState());
    jumpToCurrentState();
  }
  
  private void inflateChildElements(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i)
            continue; 
          if (paramXmlPullParser.getName().equals("item")) {
            parseItem(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
            continue;
          } 
          if (paramXmlPullParser.getName().equals("transition"))
            parseTransition(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme); 
          continue;
        } 
      } 
      break;
    } 
  }
  
  private void init() {
    onStateChange(getState());
  }
  
  private int parseItem(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableItem);
    int i = typedArray.getResourceId(0, 0);
    Drawable drawable2 = typedArray.getDrawable(1);
    typedArray.recycle();
    int[] arrayOfInt = extractStateSet(paramAttributeSet);
    Drawable drawable1 = drawable2;
    if (drawable2 == null)
      while (true) {
        int j = paramXmlPullParser.next();
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
    return this.mState.addStateSet(arrayOfInt, drawable1, i);
  }
  
  private int parseTransition(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableTransition);
    int i = typedArray.getResourceId(2, 0);
    int j = typedArray.getResourceId(1, 0);
    boolean bool = typedArray.getBoolean(3, false);
    Drawable drawable2 = typedArray.getDrawable(0);
    typedArray.recycle();
    Drawable drawable1 = drawable2;
    if (drawable2 == null)
      while (true) {
        int k = paramXmlPullParser.next();
        if (k == 4)
          continue; 
        if (k == 2) {
          drawable1 = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
          break;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramXmlPullParser.getPositionDescription());
        stringBuilder.append(": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        throw new XmlPullParserException(stringBuilder.toString());
      }  
    return this.mState.addTransition(i, j, drawable1, bool);
  }
  
  private boolean selectTransition(int paramInt) {
    AnimationDrawableTransition animationDrawableTransition;
    AnimatableTransition animatableTransition;
    int i;
    Transition transition = this.mTransition;
    if (transition != null) {
      if (paramInt == this.mTransitionToIndex)
        return true; 
      if (paramInt == this.mTransitionFromIndex && transition.canReverse()) {
        transition.reverse();
        this.mTransitionToIndex = this.mTransitionFromIndex;
        this.mTransitionFromIndex = paramInt;
        return true;
      } 
      i = this.mTransitionToIndex;
      transition.stop();
    } else {
      i = getCurrentIndex();
    } 
    this.mTransition = null;
    this.mTransitionFromIndex = -1;
    this.mTransitionToIndex = -1;
    AnimatedStateListState animatedStateListState = this.mState;
    int j = animatedStateListState.getKeyframeIdAt(i);
    int k = animatedStateListState.getKeyframeIdAt(paramInt);
    if (k == 0 || j == 0)
      return false; 
    int m = animatedStateListState.indexOfTransition(j, k);
    if (m < 0)
      return false; 
    boolean bool = animatedStateListState.transitionHasReversibleFlag(j, k);
    selectDrawable(m);
    Drawable drawable = getCurrent();
    if (drawable instanceof AnimationDrawable) {
      boolean bool1 = animatedStateListState.isTransitionReversed(j, k);
      animationDrawableTransition = new AnimationDrawableTransition((AnimationDrawable)drawable, bool1, bool);
    } else {
      AnimatedVectorDrawableTransition animatedVectorDrawableTransition;
      if (animationDrawableTransition instanceof AnimatedVectorDrawable) {
        boolean bool1 = animatedStateListState.isTransitionReversed(j, k);
        animatedVectorDrawableTransition = new AnimatedVectorDrawableTransition((AnimatedVectorDrawable)animationDrawableTransition, bool1, bool);
      } else {
        if (animatedVectorDrawableTransition instanceof Animatable) {
          animatableTransition = new AnimatableTransition((Animatable)animatedVectorDrawableTransition);
          animatableTransition.start();
          this.mTransition = animatableTransition;
          this.mTransitionFromIndex = i;
          this.mTransitionToIndex = paramInt;
          return true;
        } 
        return false;
      } 
    } 
    animatableTransition.start();
    this.mTransition = animatableTransition;
    this.mTransitionFromIndex = i;
    this.mTransitionToIndex = paramInt;
    return true;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    AnimatedStateListState animatedStateListState = this.mState;
    animatedStateListState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    animatedStateListState.mAnimThemeAttrs = paramTypedArray.extractThemeAttrs();
    animatedStateListState.setVariablePadding(paramTypedArray.getBoolean(2, animatedStateListState.mVariablePadding));
    animatedStateListState.setConstantSize(paramTypedArray.getBoolean(3, animatedStateListState.mConstantSize));
    animatedStateListState.setEnterFadeDuration(paramTypedArray.getInt(4, animatedStateListState.mEnterFadeDuration));
    animatedStateListState.setExitFadeDuration(paramTypedArray.getInt(5, animatedStateListState.mExitFadeDuration));
    setDither(paramTypedArray.getBoolean(0, animatedStateListState.mDither));
    setAutoMirrored(paramTypedArray.getBoolean(6, animatedStateListState.mAutoMirrored));
  }
  
  public void addState(int[] paramArrayOfint, Drawable paramDrawable, int paramInt) {
    if (paramDrawable != null) {
      this.mState.addStateSet(paramArrayOfint, paramDrawable, paramInt);
      onStateChange(getState());
      return;
    } 
    throw new IllegalArgumentException("Drawable must not be null");
  }
  
  public <T extends Drawable & Animatable> void addTransition(int paramInt1, int paramInt2, T paramT, boolean paramBoolean) {
    if (paramT != null) {
      this.mState.addTransition(paramInt1, paramInt2, (Drawable)paramT, paramBoolean);
      return;
    } 
    throw new IllegalArgumentException("Transition drawable must not be null");
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    AnimatedStateListState animatedStateListState = this.mState;
    if (animatedStateListState == null || animatedStateListState.mAnimThemeAttrs == null)
      return; 
    TypedArray typedArray = paramTheme.resolveAttributes(animatedStateListState.mAnimThemeAttrs, R.styleable.AnimatedRotateDrawable);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    init();
  }
  
  public void clearMutated() {
    super.clearMutated();
    this.mMutated = false;
  }
  
  AnimatedStateListState cloneConstantState() {
    return new AnimatedStateListState(this.mState, this, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawable);
    inflateWithAttributes(paramResources, paramXmlPullParser, typedArray, 1);
    updateStateFromTypedArray(typedArray);
    updateDensity(paramResources);
    typedArray.recycle();
    inflateChildElements(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    init();
  }
  
  public boolean isStateful() {
    return true;
  }
  
  public void jumpToCurrentState() {
    super.jumpToCurrentState();
    Transition transition = this.mTransition;
    if (transition != null) {
      transition.stop();
      this.mTransition = null;
      selectDrawable(this.mTransitionToIndex);
      this.mTransitionToIndex = -1;
      this.mTransitionFromIndex = -1;
    } 
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mState.mutate();
      this.mMutated = true;
    } 
    return this;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool1;
    int i = this.mState.indexOfKeyframe(paramArrayOfint);
    if (i != getCurrentIndex() && (selectTransition(i) || selectDrawable(i))) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Drawable drawable = getCurrent();
    boolean bool2 = bool1;
    if (drawable != null)
      bool2 = bool1 | drawable.setState(paramArrayOfint); 
    return bool2;
  }
  
  protected void setConstantState(DrawableContainer.DrawableContainerState paramDrawableContainerState) {
    super.setConstantState(paramDrawableContainerState);
    if (paramDrawableContainerState instanceof AnimatedStateListState)
      this.mState = (AnimatedStateListState)paramDrawableContainerState; 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (this.mTransition != null && (bool || paramBoolean2))
      if (paramBoolean1) {
        this.mTransition.start();
      } else {
        jumpToCurrentState();
      }  
    return bool;
  }
  
  private static class AnimatableTransition extends Transition {
    private final Animatable mA;
    
    public AnimatableTransition(Animatable param1Animatable) {
      this.mA = param1Animatable;
    }
    
    public void start() {
      this.mA.start();
    }
    
    public void stop() {
      this.mA.stop();
    }
  }
  
  static class AnimatedStateListState extends StateListDrawable.StateListState {
    private static final long REVERSED_BIT = 4294967296L;
    
    private static final long REVERSIBLE_FLAG_BIT = 8589934592L;
    
    int[] mAnimThemeAttrs;
    
    SparseIntArray mStateIds;
    
    LongSparseLongArray mTransitions;
    
    AnimatedStateListState(AnimatedStateListState param1AnimatedStateListState, AnimatedStateListDrawable param1AnimatedStateListDrawable, Resources param1Resources) {
      super(param1AnimatedStateListState, param1AnimatedStateListDrawable, param1Resources);
      if (param1AnimatedStateListState != null) {
        this.mAnimThemeAttrs = param1AnimatedStateListState.mAnimThemeAttrs;
        this.mTransitions = param1AnimatedStateListState.mTransitions;
        this.mStateIds = param1AnimatedStateListState.mStateIds;
      } else {
        this.mTransitions = new LongSparseLongArray();
        this.mStateIds = new SparseIntArray();
      } 
    }
    
    private static long generateTransitionKey(int param1Int1, int param1Int2) {
      return param1Int1 << 32L | param1Int2;
    }
    
    int addStateSet(int[] param1ArrayOfint, Drawable param1Drawable, int param1Int) {
      int i = addStateSet(param1ArrayOfint, param1Drawable);
      this.mStateIds.put(i, param1Int);
      return i;
    }
    
    int addTransition(int param1Int1, int param1Int2, Drawable param1Drawable, boolean param1Boolean) {
      int i = addChild(param1Drawable);
      long l1 = generateTransitionKey(param1Int1, param1Int2);
      long l2 = 0L;
      if (param1Boolean)
        l2 = 8589934592L; 
      this.mTransitions.append(l1, i | l2);
      if (param1Boolean) {
        l1 = generateTransitionKey(param1Int2, param1Int1);
        this.mTransitions.append(l1, i | 0x100000000L | l2);
      } 
      return i;
    }
    
    public boolean canApplyTheme() {
      return (this.mAnimThemeAttrs != null || super.canApplyTheme());
    }
    
    int getKeyframeIdAt(int param1Int) {
      boolean bool = false;
      if (param1Int < 0) {
        param1Int = bool;
      } else {
        param1Int = this.mStateIds.get(param1Int, 0);
      } 
      return param1Int;
    }
    
    int indexOfKeyframe(int[] param1ArrayOfint) {
      int i = indexOfStateSet(param1ArrayOfint);
      return (i >= 0) ? i : indexOfStateSet(StateSet.WILD_CARD);
    }
    
    int indexOfTransition(int param1Int1, int param1Int2) {
      long l = generateTransitionKey(param1Int1, param1Int2);
      return (int)this.mTransitions.get(l, -1L);
    }
    
    boolean isTransitionReversed(int param1Int1, int param1Int2) {
      boolean bool;
      long l = generateTransitionKey(param1Int1, param1Int2);
      if ((this.mTransitions.get(l, -1L) & 0x100000000L) != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void mutate() {
      this.mTransitions = this.mTransitions.clone();
      this.mStateIds = this.mStateIds.clone();
    }
    
    public Drawable newDrawable() {
      return new AnimatedStateListDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new AnimatedStateListDrawable(this, param1Resources);
    }
    
    boolean transitionHasReversibleFlag(int param1Int1, int param1Int2) {
      boolean bool;
      long l = generateTransitionKey(param1Int1, param1Int2);
      if ((this.mTransitions.get(l, -1L) & 0x200000000L) != 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
  
  private static class AnimatedVectorDrawableTransition extends Transition {
    private final AnimatedVectorDrawable mAvd;
    
    private final boolean mHasReversibleFlag;
    
    private final boolean mReversed;
    
    public AnimatedVectorDrawableTransition(AnimatedVectorDrawable param1AnimatedVectorDrawable, boolean param1Boolean1, boolean param1Boolean2) {
      this.mAvd = param1AnimatedVectorDrawable;
      this.mReversed = param1Boolean1;
      this.mHasReversibleFlag = param1Boolean2;
    }
    
    public boolean canReverse() {
      boolean bool;
      if (this.mAvd.canReverse() && this.mHasReversibleFlag) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void reverse() {
      if (canReverse()) {
        this.mAvd.reverse();
      } else {
        Log.w(AnimatedStateListDrawable.LOGTAG, "Can't reverse, either the reversible is set to false, or the AnimatedVectorDrawable can't reverse");
      } 
    }
    
    public void start() {
      if (this.mReversed) {
        reverse();
      } else {
        this.mAvd.start();
      } 
    }
    
    public void stop() {
      this.mAvd.stop();
    }
  }
  
  private static class AnimationDrawableTransition extends Transition {
    private final ObjectAnimator mAnim;
    
    private final boolean mHasReversibleFlag;
    
    public AnimationDrawableTransition(AnimationDrawable param1AnimationDrawable, boolean param1Boolean1, boolean param1Boolean2) {
      boolean bool;
      int i = param1AnimationDrawable.getNumberOfFrames();
      if (param1Boolean1) {
        bool = i - 1;
      } else {
        bool = false;
      } 
      if (param1Boolean1) {
        i = 0;
      } else {
        i--;
      } 
      AnimatedStateListDrawable.FrameInterpolator frameInterpolator = new AnimatedStateListDrawable.FrameInterpolator(param1AnimationDrawable, param1Boolean1);
      ObjectAnimator objectAnimator = ObjectAnimator.ofInt(param1AnimationDrawable, "currentIndex", new int[] { bool, i });
      objectAnimator.setAutoCancel(true);
      objectAnimator.setDuration(frameInterpolator.getTotalDuration());
      objectAnimator.setInterpolator(frameInterpolator);
      this.mHasReversibleFlag = param1Boolean2;
      this.mAnim = objectAnimator;
    }
    
    public boolean canReverse() {
      return this.mHasReversibleFlag;
    }
    
    public void reverse() {
      this.mAnim.reverse();
    }
    
    public void start() {
      this.mAnim.start();
    }
    
    public void stop() {
      this.mAnim.cancel();
    }
  }
  
  private static class FrameInterpolator implements TimeInterpolator {
    private int[] mFrameTimes;
    
    private int mFrames;
    
    private int mTotalDuration;
    
    public FrameInterpolator(AnimationDrawable param1AnimationDrawable, boolean param1Boolean) {
      updateFrames(param1AnimationDrawable, param1Boolean);
    }
    
    public float getInterpolation(float param1Float) {
      int i = (int)(this.mTotalDuration * param1Float + 0.5F);
      int j = this.mFrames;
      int[] arrayOfInt = this.mFrameTimes;
      byte b;
      for (b = 0; b < j && i >= arrayOfInt[b]; b++)
        i -= arrayOfInt[b]; 
      if (b < j) {
        param1Float = i / this.mTotalDuration;
      } else {
        param1Float = 0.0F;
      } 
      return b / j + param1Float;
    }
    
    public int getTotalDuration() {
      return this.mTotalDuration;
    }
    
    public int updateFrames(AnimationDrawable param1AnimationDrawable, boolean param1Boolean) {
      int i = param1AnimationDrawable.getNumberOfFrames();
      this.mFrames = i;
      int[] arrayOfInt = this.mFrameTimes;
      if (arrayOfInt == null || arrayOfInt.length < i)
        this.mFrameTimes = new int[i]; 
      arrayOfInt = this.mFrameTimes;
      int j = 0;
      for (byte b = 0; b < i; b++) {
        if (param1Boolean) {
          k = i - b - 1;
        } else {
          k = b;
        } 
        int k = param1AnimationDrawable.getDuration(k);
        arrayOfInt[b] = k;
        j += k;
      } 
      this.mTotalDuration = j;
      return j;
    }
  }
  
  private static abstract class Transition {
    private Transition() {}
    
    public boolean canReverse() {
      return false;
    }
    
    public void reverse() {}
    
    public abstract void start();
    
    public abstract void stop();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */