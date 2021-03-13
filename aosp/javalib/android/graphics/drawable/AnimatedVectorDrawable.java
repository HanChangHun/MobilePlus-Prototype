package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.ActivityThread;
import android.app.Application;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.graphics.animation.NativeInterpolatorFactory;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.IntArray;
import android.util.Log;
import android.util.LongArray;
import android.util.PathParser;
import android.util.Property;
import android.view.Choreographer;
import android.view.NativeVectorDrawableAnimator;
import com.android.internal.R;
import com.android.internal.util.VirtualRefBasePtr;
import dalvik.annotation.optimization.FastNative;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawable extends Drawable implements Animatable2 {
  private static final String ANIMATED_VECTOR = "animated-vector";
  
  private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
  
  private static final String LOGTAG = "AnimatedVectorDrawable";
  
  private static final String TARGET = "target";
  
  private AnimatedVectorDrawableState mAnimatedVectorState;
  
  private ArrayList<Animatable2.AnimationCallback> mAnimationCallbacks = null;
  
  private Animator.AnimatorListener mAnimatorListener = null;
  
  private VectorDrawableAnimator mAnimatorSet;
  
  private AnimatorSet mAnimatorSetFromXml = null;
  
  private final Drawable.Callback mCallback;
  
  private boolean mMutated;
  
  private Resources mRes;
  
  public AnimatedVectorDrawable() {
    this((AnimatedVectorDrawableState)null, (Resources)null);
  }
  
  private AnimatedVectorDrawable(AnimatedVectorDrawableState paramAnimatedVectorDrawableState, Resources paramResources) {
    Drawable.Callback callback = new Drawable.Callback() {
        public void invalidateDrawable(Drawable param1Drawable) {
          AnimatedVectorDrawable.this.invalidateSelf();
        }
        
        public void scheduleDrawable(Drawable param1Drawable, Runnable param1Runnable, long param1Long) {
          AnimatedVectorDrawable.this.scheduleSelf(param1Runnable, param1Long);
        }
        
        public void unscheduleDrawable(Drawable param1Drawable, Runnable param1Runnable) {
          AnimatedVectorDrawable.this.unscheduleSelf(param1Runnable);
        }
      };
    this.mCallback = callback;
    this.mAnimatedVectorState = new AnimatedVectorDrawableState(paramAnimatedVectorDrawableState, callback, paramResources);
    this.mAnimatorSet = new VectorDrawableAnimatorRT(this);
    this.mRes = paramResources;
  }
  
  private static boolean containsSameValueType(PropertyValuesHolder paramPropertyValuesHolder, Property paramProperty) {
    Class<float> clazz1 = paramPropertyValuesHolder.getValueType();
    Class<float> clazz3 = paramProperty.getType();
    Class<float> clazz2 = float.class;
    boolean bool1 = false;
    boolean bool2 = false;
    null = false;
    if (clazz1 == clazz2 || clazz1 == Float.class) {
      if (clazz3 != float.class) {
        null = bool2;
        return (clazz3 == Float.class) ? true : null;
      } 
    } else {
      if (clazz1 == int.class || clazz1 == Integer.class) {
        if (clazz3 != int.class) {
          null = bool1;
          if (clazz3 == Integer.class)
            null = true; 
          return null;
        } 
      } else {
        if (clazz1 == clazz3)
          null = true; 
        return null;
      } 
      null = true;
    } 
    return true;
  }
  
  private void ensureAnimatorSet() {
    if (this.mAnimatorSetFromXml == null) {
      AnimatorSet animatorSet = new AnimatorSet();
      this.mAnimatorSetFromXml = animatorSet;
      this.mAnimatedVectorState.prepareLocalAnimators(animatorSet, this.mRes);
      this.mAnimatorSet.init(this.mAnimatorSetFromXml);
      this.mRes = null;
    } 
  }
  
  private void fallbackOntoUI() {
    VectorDrawableAnimator vectorDrawableAnimator = this.mAnimatorSet;
    if (vectorDrawableAnimator instanceof VectorDrawableAnimatorRT) {
      vectorDrawableAnimator = vectorDrawableAnimator;
      VectorDrawableAnimatorUI vectorDrawableAnimatorUI = new VectorDrawableAnimatorUI(this);
      this.mAnimatorSet = vectorDrawableAnimatorUI;
      AnimatorSet animatorSet = this.mAnimatorSetFromXml;
      if (animatorSet != null)
        vectorDrawableAnimatorUI.init(animatorSet); 
      if (((VectorDrawableAnimatorRT)vectorDrawableAnimator).mListener != null)
        this.mAnimatorSet.setListener(((VectorDrawableAnimatorRT)vectorDrawableAnimator).mListener); 
      vectorDrawableAnimator.transferPendingActions(this.mAnimatorSet);
    } 
  }
  
  private static native void nAddAnimator(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, int paramInt1, int paramInt2);
  
  private static native long nCreateAnimatorSet();
  
  @FastNative
  private static native long nCreateGroupPropertyHolder(long paramLong, int paramInt, float paramFloat1, float paramFloat2);
  
  @FastNative
  private static native long nCreatePathColorPropertyHolder(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  @FastNative
  private static native long nCreatePathDataPropertyHolder(long paramLong1, long paramLong2, long paramLong3);
  
  @FastNative
  private static native long nCreatePathPropertyHolder(long paramLong, int paramInt, float paramFloat1, float paramFloat2);
  
  @FastNative
  private static native long nCreateRootAlphaPropertyHolder(long paramLong, float paramFloat1, float paramFloat2);
  
  @FastNative
  private static native void nEnd(long paramLong);
  
  @FastNative
  private static native void nReset(long paramLong);
  
  private static native void nReverse(long paramLong, VectorDrawableAnimatorRT paramVectorDrawableAnimatorRT, int paramInt);
  
  private static native void nSetPropertyHolderData(long paramLong, float[] paramArrayOffloat, int paramInt);
  
  private static native void nSetPropertyHolderData(long paramLong, int[] paramArrayOfint, int paramInt);
  
  private static native void nSetVectorDrawableTarget(long paramLong1, long paramLong2);
  
  private static native void nStart(long paramLong, VectorDrawableAnimatorRT paramVectorDrawableAnimatorRT, int paramInt);
  
  private void removeAnimatorSetListener() {
    Animator.AnimatorListener animatorListener = this.mAnimatorListener;
    if (animatorListener != null) {
      this.mAnimatorSet.removeListener(animatorListener);
      this.mAnimatorListener = null;
    } 
  }
  
  private static boolean shouldIgnoreInvalidAnimation() {
    Application application = ActivityThread.currentApplication();
    return (application == null || application.getApplicationInfo() == null) ? true : (((application.getApplicationInfo()).targetSdkVersion < 24));
  }
  
  private static void updateAnimatorProperty(Animator paramAnimator, String paramString, VectorDrawable paramVectorDrawable, boolean paramBoolean) {
    Property property;
    StringBuilder stringBuilder;
    if (paramAnimator instanceof ObjectAnimator) {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = ((ObjectAnimator)paramAnimator).getValues();
      for (byte b = 0; b < arrayOfPropertyValuesHolder.length; b++) {
        PropertyValuesHolder propertyValuesHolder = arrayOfPropertyValuesHolder[b];
        String str = propertyValuesHolder.getPropertyName();
        Object object = paramVectorDrawable.getTargetByName(paramString);
        paramAnimator = null;
        if (object instanceof VectorDrawable.VObject) {
          property = ((VectorDrawable.VObject)object).getProperty(str);
        } else if (object instanceof VectorDrawable.VectorDrawableState) {
          property = ((VectorDrawable.VectorDrawableState)object).getProperty(str);
        } 
        if (property != null)
          if (containsSameValueType(propertyValuesHolder, property)) {
            propertyValuesHolder.setProperty(property);
          } else if (!paramBoolean) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Wrong valueType for Property: ");
            stringBuilder.append(str);
            stringBuilder.append(".  Expected type: ");
            stringBuilder.append(property.getType().toString());
            stringBuilder.append(". Actual type defined in resources: ");
            stringBuilder.append(propertyValuesHolder.getValueType().toString());
            throw new RuntimeException(stringBuilder.toString());
          }  
      } 
    } else if (property instanceof AnimatorSet) {
      Iterator<Animator> iterator = ((AnimatorSet)property).getChildAnimations().iterator();
      while (iterator.hasNext())
        updateAnimatorProperty(iterator.next(), (String)stringBuilder, paramVectorDrawable, paramBoolean); 
    } 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    VectorDrawable vectorDrawable = this.mAnimatedVectorState.mVectorDrawable;
    if (vectorDrawable != null && vectorDrawable.canApplyTheme())
      vectorDrawable.applyTheme(paramTheme); 
    if (paramTheme != null)
      this.mAnimatedVectorState.inflatePendingAnimators(paramTheme.getResources(), paramTheme); 
    if (this.mAnimatedVectorState.mPendingAnims == null)
      this.mRes = null; 
  }
  
  public boolean canApplyTheme() {
    boolean bool;
    AnimatedVectorDrawableState animatedVectorDrawableState = this.mAnimatedVectorState;
    if ((animatedVectorDrawableState != null && animatedVectorDrawableState.canApplyTheme()) || super.canApplyTheme()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean canReverse() {
    return this.mAnimatorSet.canReverse();
  }
  
  public void clearAnimationCallbacks() {
    removeAnimatorSetListener();
    ArrayList<Animatable2.AnimationCallback> arrayList = this.mAnimationCallbacks;
    if (arrayList == null)
      return; 
    arrayList.clear();
  }
  
  public void clearMutated() {
    super.clearMutated();
    if (this.mAnimatedVectorState.mVectorDrawable != null)
      this.mAnimatedVectorState.mVectorDrawable.clearMutated(); 
    this.mMutated = false;
  }
  
  public void draw(Canvas paramCanvas) {
    if (!paramCanvas.isHardwareAccelerated()) {
      VectorDrawableAnimator vectorDrawableAnimator = this.mAnimatorSet;
      if (vectorDrawableAnimator instanceof VectorDrawableAnimatorRT && !vectorDrawableAnimator.isRunning() && ((VectorDrawableAnimatorRT)this.mAnimatorSet).mPendingAnimationActions.size() > 0)
        fallbackOntoUI(); 
    } 
    this.mAnimatorSet.onDraw(paramCanvas);
    this.mAnimatedVectorState.mVectorDrawable.draw(paramCanvas);
  }
  
  public void forceAnimationOnUI() {
    VectorDrawableAnimator vectorDrawableAnimator = this.mAnimatorSet;
    if (vectorDrawableAnimator instanceof VectorDrawableAnimatorRT)
      if (!((VectorDrawableAnimatorRT)vectorDrawableAnimator).isRunning()) {
        fallbackOntoUI();
      } else {
        throw new UnsupportedOperationException("Cannot force Animated Vector Drawable to run on UI thread when the animation has started on RenderThread.");
      }  
  }
  
  public int getAlpha() {
    return this.mAnimatedVectorState.mVectorDrawable.getAlpha();
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mAnimatedVectorState.getChangingConfigurations();
  }
  
  public ColorFilter getColorFilter() {
    return this.mAnimatedVectorState.mVectorDrawable.getColorFilter();
  }
  
  public Drawable.ConstantState getConstantState() {
    this.mAnimatedVectorState.mChangingConfigurations = getChangingConfigurations();
    return this.mAnimatedVectorState;
  }
  
  public int getIntrinsicHeight() {
    return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth() {
    return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public Insets getOpticalInsets() {
    return this.mAnimatedVectorState.mVectorDrawable.getOpticalInsets();
  }
  
  public void getOutline(Outline paramOutline) {
    this.mAnimatedVectorState.mVectorDrawable.getOutline(paramOutline);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    Animator animator1;
    Animator animator2;
    AnimatedVectorDrawableState animatedVectorDrawableState = this.mAnimatedVectorState;
    int i = paramXmlPullParser.getEventType();
    float f = 1.0F;
    int j = paramXmlPullParser.getDepth();
    while (true) {
      String str = null;
      if (i != 1 && (paramXmlPullParser.getDepth() >= j + 1 || i != 3)) {
        float f1 = f;
        if (i == 2) {
          TypedArray typedArray;
          str = paramXmlPullParser.getName();
          if ("animated-vector".equals(str)) {
            typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedVectorDrawable);
            i = typedArray.getResourceId(0, 0);
            if (i != 0) {
              VectorDrawable vectorDrawable = (VectorDrawable)paramResources.getDrawable(i, paramTheme).mutate();
              vectorDrawable.setAllowCaching(false);
              vectorDrawable.setCallback(this.mCallback);
              f = vectorDrawable.getPixelSize();
              if (animatedVectorDrawableState.mVectorDrawable != null)
                animatedVectorDrawableState.mVectorDrawable.setCallback(null); 
              animatedVectorDrawableState.mVectorDrawable = vectorDrawable;
            } 
            typedArray.recycle();
            f1 = f;
          } else {
            f1 = f;
            if ("target".equals(typedArray)) {
              TypedArray typedArray1 = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedVectorDrawableTarget);
              String str1 = typedArray1.getString(0);
              i = typedArray1.getResourceId(1, 0);
              if (i != 0)
                if (paramTheme != null) {
                  animator2 = AnimatorInflater.loadAnimator(paramResources, paramTheme, i, f);
                  updateAnimatorProperty(animator2, str1, animatedVectorDrawableState.mVectorDrawable, animatedVectorDrawableState.mShouldIgnoreInvalidAnim);
                  animatedVectorDrawableState.addTargetAnimator(str1, animator2);
                } else {
                  animatedVectorDrawableState.addPendingAnimator(i, f, str1);
                }  
              typedArray1.recycle();
              f1 = f;
            } 
          } 
        } 
        i = paramXmlPullParser.next();
        f = f1;
        continue;
      } 
      break;
    } 
    if (animatedVectorDrawableState.mPendingAnims == null)
      animator1 = animator2; 
    this.mRes = (Resources)animator1;
  }
  
  public boolean isRunning() {
    return this.mAnimatorSet.isRunning();
  }
  
  public boolean isStateful() {
    return this.mAnimatedVectorState.mVectorDrawable.isStateful();
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      this.mAnimatedVectorState = new AnimatedVectorDrawableState(this.mAnimatedVectorState, this.mCallback, this.mRes);
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    this.mAnimatedVectorState.mVectorDrawable.setBounds(paramRect);
  }
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    return this.mAnimatedVectorState.mVectorDrawable.setLayoutDirection(paramInt);
  }
  
  protected boolean onLevelChange(int paramInt) {
    return this.mAnimatedVectorState.mVectorDrawable.setLevel(paramInt);
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    return this.mAnimatedVectorState.mVectorDrawable.setState(paramArrayOfint);
  }
  
  public void registerAnimationCallback(Animatable2.AnimationCallback paramAnimationCallback) {
    if (paramAnimationCallback == null)
      return; 
    if (this.mAnimationCallbacks == null)
      this.mAnimationCallbacks = new ArrayList<>(); 
    this.mAnimationCallbacks.add(paramAnimationCallback);
    if (this.mAnimatorListener == null)
      this.mAnimatorListener = (Animator.AnimatorListener)new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            ArrayList<Animatable2.AnimationCallback> arrayList = new ArrayList(AnimatedVectorDrawable.this.mAnimationCallbacks);
            int i = arrayList.size();
            for (byte b = 0; b < i; b++)
              ((Animatable2.AnimationCallback)arrayList.get(b)).onAnimationEnd(AnimatedVectorDrawable.this); 
          }
          
          public void onAnimationStart(Animator param1Animator) {
            ArrayList<Animatable2.AnimationCallback> arrayList = new ArrayList(AnimatedVectorDrawable.this.mAnimationCallbacks);
            int i = arrayList.size();
            for (byte b = 0; b < i; b++)
              ((Animatable2.AnimationCallback)arrayList.get(b)).onAnimationStart(AnimatedVectorDrawable.this); 
          }
        }; 
    this.mAnimatorSet.setListener(this.mAnimatorListener);
  }
  
  public void reset() {
    ensureAnimatorSet();
    this.mAnimatorSet.reset();
  }
  
  public void reverse() {
    ensureAnimatorSet();
    if (!canReverse()) {
      Log.w("AnimatedVectorDrawable", "AnimatedVectorDrawable can't reverse()");
      return;
    } 
    this.mAnimatorSet.reverse();
  }
  
  public void setAlpha(int paramInt) {
    this.mAnimatedVectorState.mVectorDrawable.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    this.mAnimatedVectorState.mVectorDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    this.mAnimatedVectorState.mVectorDrawable.setHotspot(paramFloat1, paramFloat2);
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mAnimatedVectorState.mVectorDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    this.mAnimatedVectorState.mVectorDrawable.setTintBlendMode(paramBlendMode);
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    this.mAnimatedVectorState.mVectorDrawable.setTintList(paramColorStateList);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    if (this.mAnimatorSet.isInfinite() && this.mAnimatorSet.isStarted())
      if (paramBoolean1) {
        this.mAnimatorSet.resume();
      } else {
        this.mAnimatorSet.pause();
      }  
    this.mAnimatedVectorState.mVectorDrawable.setVisible(paramBoolean1, paramBoolean2);
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void start() {
    ensureAnimatorSet();
    this.mAnimatorSet.start();
  }
  
  public void stop() {
    this.mAnimatorSet.end();
  }
  
  public boolean unregisterAnimationCallback(Animatable2.AnimationCallback paramAnimationCallback) {
    ArrayList<Animatable2.AnimationCallback> arrayList = this.mAnimationCallbacks;
    if (arrayList == null || paramAnimationCallback == null)
      return false; 
    boolean bool = arrayList.remove(paramAnimationCallback);
    if (this.mAnimationCallbacks.size() == 0)
      removeAnimatorSetListener(); 
    return bool;
  }
  
  private static class AnimatedVectorDrawableState extends Drawable.ConstantState {
    ArrayList<Animator> mAnimators;
    
    int mChangingConfigurations;
    
    ArrayList<PendingAnimator> mPendingAnims;
    
    private final boolean mShouldIgnoreInvalidAnim = AnimatedVectorDrawable.shouldIgnoreInvalidAnimation();
    
    ArrayMap<Animator, String> mTargetNameMap;
    
    VectorDrawable mVectorDrawable;
    
    public AnimatedVectorDrawableState(AnimatedVectorDrawableState param1AnimatedVectorDrawableState, Drawable.Callback param1Callback, Resources param1Resources) {
      if (param1AnimatedVectorDrawableState != null) {
        this.mChangingConfigurations = param1AnimatedVectorDrawableState.mChangingConfigurations;
        VectorDrawable vectorDrawable = param1AnimatedVectorDrawableState.mVectorDrawable;
        if (vectorDrawable != null) {
          Drawable.ConstantState constantState = vectorDrawable.getConstantState();
          if (param1Resources != null) {
            this.mVectorDrawable = (VectorDrawable)constantState.newDrawable(param1Resources);
          } else {
            this.mVectorDrawable = (VectorDrawable)constantState.newDrawable();
          } 
          VectorDrawable vectorDrawable1 = (VectorDrawable)this.mVectorDrawable.mutate();
          this.mVectorDrawable = vectorDrawable1;
          vectorDrawable1.setCallback(param1Callback);
          this.mVectorDrawable.setLayoutDirection(param1AnimatedVectorDrawableState.mVectorDrawable.getLayoutDirection());
          this.mVectorDrawable.setBounds(param1AnimatedVectorDrawableState.mVectorDrawable.getBounds());
          this.mVectorDrawable.setAllowCaching(false);
        } 
        if (param1AnimatedVectorDrawableState.mAnimators != null)
          this.mAnimators = new ArrayList<>(param1AnimatedVectorDrawableState.mAnimators); 
        if (param1AnimatedVectorDrawableState.mTargetNameMap != null)
          this.mTargetNameMap = new ArrayMap(param1AnimatedVectorDrawableState.mTargetNameMap); 
        if (param1AnimatedVectorDrawableState.mPendingAnims != null)
          this.mPendingAnims = new ArrayList<>(param1AnimatedVectorDrawableState.mPendingAnims); 
      } else {
        this.mVectorDrawable = new VectorDrawable();
      } 
    }
    
    private Animator prepareLocalAnimator(int param1Int) {
      StringBuilder stringBuilder;
      Animator animator1 = this.mAnimators.get(param1Int);
      Animator animator2 = animator1.clone();
      String str = (String)this.mTargetNameMap.get(animator1);
      Object object = this.mVectorDrawable.getTargetByName(str);
      if (!this.mShouldIgnoreInvalidAnim)
        if (object != null) {
          if (!(object instanceof VectorDrawable.VectorDrawableState) && !(object instanceof VectorDrawable.VObject)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Target should be either VGroup, VPath, or ConstantState, ");
            stringBuilder.append(object.getClass());
            stringBuilder.append(" is not supported");
            throw new UnsupportedOperationException(stringBuilder.toString());
          } 
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Target with the name \"");
          stringBuilder.append(str);
          stringBuilder.append("\" cannot be found in the VectorDrawable to be animated.");
          throw new IllegalStateException(stringBuilder.toString());
        }  
      stringBuilder.setTarget(object);
      return (Animator)stringBuilder;
    }
    
    public void addPendingAnimator(int param1Int, float param1Float, String param1String) {
      if (this.mPendingAnims == null)
        this.mPendingAnims = new ArrayList<>(1); 
      this.mPendingAnims.add(new PendingAnimator(param1Int, param1Float, param1String));
    }
    
    public void addTargetAnimator(String param1String, Animator param1Animator) {
      if (this.mAnimators == null) {
        this.mAnimators = new ArrayList<>(1);
        this.mTargetNameMap = new ArrayMap(1);
      } 
      this.mAnimators.add(param1Animator);
      this.mTargetNameMap.put(param1Animator, param1String);
    }
    
    public boolean canApplyTheme() {
      VectorDrawable vectorDrawable = this.mVectorDrawable;
      return ((vectorDrawable != null && vectorDrawable.canApplyTheme()) || this.mPendingAnims != null || super.canApplyTheme());
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations;
    }
    
    public void inflatePendingAnimators(Resources param1Resources, Resources.Theme param1Theme) {
      ArrayList<PendingAnimator> arrayList = this.mPendingAnims;
      if (arrayList != null) {
        this.mPendingAnims = null;
        byte b = 0;
        int i = arrayList.size();
        while (b < i) {
          PendingAnimator pendingAnimator = arrayList.get(b);
          Animator animator = pendingAnimator.newInstance(param1Resources, param1Theme);
          AnimatedVectorDrawable.updateAnimatorProperty(animator, pendingAnimator.target, this.mVectorDrawable, this.mShouldIgnoreInvalidAnim);
          addTargetAnimator(pendingAnimator.target, animator);
          b++;
        } 
      } 
    }
    
    public Drawable newDrawable() {
      return new AnimatedVectorDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new AnimatedVectorDrawable(this, param1Resources);
    }
    
    public void prepareLocalAnimators(AnimatorSet param1AnimatorSet, Resources param1Resources) {
      int i;
      if (this.mPendingAnims != null) {
        if (param1Resources != null) {
          inflatePendingAnimators(param1Resources, null);
        } else {
          Log.e("AnimatedVectorDrawable", "Failed to load animators. Either the AnimatedVectorDrawable must be created using a Resources object or applyTheme() must be called with a non-null Theme object.");
        } 
        this.mPendingAnims = null;
      } 
      ArrayList<Animator> arrayList = this.mAnimators;
      if (arrayList == null) {
        i = 0;
      } else {
        i = arrayList.size();
      } 
      if (i > 0) {
        AnimatorSet.Builder builder = param1AnimatorSet.play(prepareLocalAnimator(0));
        for (byte b = 1; b < i; b++)
          builder.with(prepareLocalAnimator(b)); 
      } 
    }
    
    private static class PendingAnimator {
      public final int animResId;
      
      public final float pathErrorScale;
      
      public final String target;
      
      public PendingAnimator(int param2Int, float param2Float, String param2String) {
        this.animResId = param2Int;
        this.pathErrorScale = param2Float;
        this.target = param2String;
      }
      
      public Animator newInstance(Resources param2Resources, Resources.Theme param2Theme) {
        return AnimatorInflater.loadAnimator(param2Resources, param2Theme, this.animResId, this.pathErrorScale);
      }
    }
  }
  
  private static class PendingAnimator {
    public final int animResId;
    
    public final float pathErrorScale;
    
    public final String target;
    
    public PendingAnimator(int param1Int, float param1Float, String param1String) {
      this.animResId = param1Int;
      this.pathErrorScale = param1Float;
      this.target = param1String;
    }
    
    public Animator newInstance(Resources param1Resources, Resources.Theme param1Theme) {
      return AnimatorInflater.loadAnimator(param1Resources, param1Theme, this.animResId, this.pathErrorScale);
    }
  }
  
  private static interface VectorDrawableAnimator {
    boolean canReverse();
    
    void end();
    
    void init(AnimatorSet param1AnimatorSet);
    
    boolean isInfinite();
    
    boolean isRunning();
    
    boolean isStarted();
    
    void onDraw(Canvas param1Canvas);
    
    void pause();
    
    void removeListener(Animator.AnimatorListener param1AnimatorListener);
    
    void reset();
    
    void resume();
    
    void reverse();
    
    void setListener(Animator.AnimatorListener param1AnimatorListener);
    
    void start();
  }
  
  public static class VectorDrawableAnimatorRT implements VectorDrawableAnimator, NativeVectorDrawableAnimator {
    private static final int END_ANIMATION = 4;
    
    private static final int MAX_SAMPLE_POINTS = 300;
    
    private static final int RESET_ANIMATION = 3;
    
    private static final int REVERSE_ANIMATION = 2;
    
    private static final int START_ANIMATION = 1;
    
    private boolean mContainsSequentialAnimators = false;
    
    private final AnimatedVectorDrawable mDrawable;
    
    private Handler mHandler;
    
    private boolean mInitialized = false;
    
    private boolean mIsInfinite = false;
    
    private boolean mIsReversible = false;
    
    private int mLastListenerId = 0;
    
    private WeakReference<RenderNode> mLastSeenTarget = null;
    
    private Animator.AnimatorListener mListener = null;
    
    private final IntArray mPendingAnimationActions = new IntArray();
    
    private long mSetPtr = 0L;
    
    private final VirtualRefBasePtr mSetRefBasePtr;
    
    private final LongArray mStartDelays = new LongArray();
    
    private boolean mStarted = false;
    
    private PropertyValuesHolder.PropertyValues mTmpValues = new PropertyValuesHolder.PropertyValues();
    
    VectorDrawableAnimatorRT(AnimatedVectorDrawable param1AnimatedVectorDrawable) {
      this.mDrawable = param1AnimatedVectorDrawable;
      long l = AnimatedVectorDrawable.nCreateAnimatorSet();
      this.mSetPtr = l;
      this.mSetRefBasePtr = new VirtualRefBasePtr(l);
    }
    
    private void addPendingAction(int param1Int) {
      invalidateOwningView();
      this.mPendingAnimationActions.add(param1Int);
    }
    
    private static void callOnFinished(VectorDrawableAnimatorRT param1VectorDrawableAnimatorRT, int param1Int) {
      param1VectorDrawableAnimatorRT.mHandler.post(new _$$Lambda$AnimatedVectorDrawable$VectorDrawableAnimatorRT$PzjgSeyQweoFjbEZJP80UteZqm8(param1VectorDrawableAnimatorRT, param1Int));
    }
    
    private static float[] createFloatDataPoints(PropertyValuesHolder.PropertyValues.DataSource param1DataSource, long param1Long) {
      int i = getFrameCount(param1Long);
      float[] arrayOfFloat = new float[i];
      float f = (i - 1);
      for (byte b = 0; b < i; b++)
        arrayOfFloat[b] = ((Float)param1DataSource.getValueAtFraction(b / f)).floatValue(); 
      return arrayOfFloat;
    }
    
    private static int[] createIntDataPoints(PropertyValuesHolder.PropertyValues.DataSource param1DataSource, long param1Long) {
      int i = getFrameCount(param1Long);
      int[] arrayOfInt = new int[i];
      float f = (i - 1);
      for (byte b = 0; b < i; b++)
        arrayOfInt[b] = ((Integer)param1DataSource.getValueAtFraction(b / f)).intValue(); 
      return arrayOfInt;
    }
    
    private void createNativeChildAnimator(long param1Long1, long param1Long2, ObjectAnimator param1ObjectAnimator) {
      long l1 = param1ObjectAnimator.getDuration();
      int i = param1ObjectAnimator.getRepeatCount();
      long l2 = param1ObjectAnimator.getStartDelay();
      TimeInterpolator timeInterpolator = param1ObjectAnimator.getInterpolator();
      long l3 = NativeInterpolatorFactory.createNativeInterpolator(timeInterpolator, l1);
      param1Long2 = (long)((float)(param1Long2 + l2) * ValueAnimator.getDurationScale());
      l1 = (long)((float)l1 * ValueAnimator.getDurationScale());
      this.mStartDelays.add(param1Long2);
      AnimatedVectorDrawable.nAddAnimator(this.mSetPtr, param1Long1, l3, param1Long2, l1, i, param1ObjectAnimator.getRepeatMode());
    }
    
    private void createRTAnimator(ObjectAnimator param1ObjectAnimator, long param1Long) {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = param1ObjectAnimator.getValues();
      Object object = param1ObjectAnimator.getTarget();
      if (object instanceof VectorDrawable.VGroup) {
        createRTAnimatorForGroup(arrayOfPropertyValuesHolder, param1ObjectAnimator, (VectorDrawable.VGroup)object, param1Long);
      } else if (object instanceof VectorDrawable.VPath) {
        for (byte b = 0; b < arrayOfPropertyValuesHolder.length; b++) {
          arrayOfPropertyValuesHolder[b].getPropertyValues(this.mTmpValues);
          if (this.mTmpValues.endValue instanceof PathParser.PathData && this.mTmpValues.propertyName.equals("pathData")) {
            createRTAnimatorForPath(param1ObjectAnimator, (VectorDrawable.VPath)object, param1Long);
          } else if (object instanceof VectorDrawable.VFullPath) {
            createRTAnimatorForFullPath(param1ObjectAnimator, (VectorDrawable.VFullPath)object, param1Long);
          } else if (!this.mDrawable.mAnimatedVectorState.mShouldIgnoreInvalidAnim) {
            throw new IllegalArgumentException("ClipPath only supports PathData property");
          } 
        } 
      } else if (object instanceof VectorDrawable.VectorDrawableState) {
        createRTAnimatorForRootGroup(arrayOfPropertyValuesHolder, param1ObjectAnimator, (VectorDrawable.VectorDrawableState)object, param1Long);
      } 
    }
    
    private void createRTAnimatorForFullPath(ObjectAnimator param1ObjectAnimator, VectorDrawable.VFullPath param1VFullPath, long param1Long) {
      StringBuilder stringBuilder;
      int i = param1VFullPath.getPropertyIndex(this.mTmpValues.propertyName);
      long l = param1VFullPath.getNativePtr();
      if (this.mTmpValues.type == Float.class || this.mTmpValues.type == float.class) {
        if (i < 0) {
          if (this.mDrawable.mAnimatedVectorState.mShouldIgnoreInvalidAnim)
            return; 
          stringBuilder = new StringBuilder();
          stringBuilder.append("Property: ");
          stringBuilder.append(this.mTmpValues.propertyName);
          stringBuilder.append(" is not supported for FullPath");
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        long l1 = AnimatedVectorDrawable.nCreatePathPropertyHolder(l, i, ((Float)this.mTmpValues.startValue).floatValue(), ((Float)this.mTmpValues.endValue).floatValue());
        l = l1;
        if (this.mTmpValues.dataSource != null) {
          float[] arrayOfFloat = createFloatDataPoints(this.mTmpValues.dataSource, stringBuilder.getDuration());
          AnimatedVectorDrawable.nSetPropertyHolderData(l1, arrayOfFloat, arrayOfFloat.length);
          l = l1;
        } 
      } else if (this.mTmpValues.type == Integer.class || this.mTmpValues.type == int.class) {
        long l1 = AnimatedVectorDrawable.nCreatePathColorPropertyHolder(l, i, ((Integer)this.mTmpValues.startValue).intValue(), ((Integer)this.mTmpValues.endValue).intValue());
        l = l1;
        if (this.mTmpValues.dataSource != null) {
          int[] arrayOfInt = createIntDataPoints(this.mTmpValues.dataSource, stringBuilder.getDuration());
          AnimatedVectorDrawable.nSetPropertyHolderData(l1, arrayOfInt, arrayOfInt.length);
          l = l1;
        } 
      } else {
        if (this.mDrawable.mAnimatedVectorState.mShouldIgnoreInvalidAnim)
          return; 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported type: ");
        stringBuilder.append(this.mTmpValues.type);
        stringBuilder.append(". Only float, int or PathData value is supported for Paths.");
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
      createNativeChildAnimator(l, param1Long, (ObjectAnimator)stringBuilder);
    }
    
    private void createRTAnimatorForGroup(PropertyValuesHolder[] param1ArrayOfPropertyValuesHolder, ObjectAnimator param1ObjectAnimator, VectorDrawable.VGroup param1VGroup, long param1Long) {
      long l = param1VGroup.getNativePtr();
      for (byte b = 0; b < param1ArrayOfPropertyValuesHolder.length; b++) {
        param1ArrayOfPropertyValuesHolder[b].getPropertyValues(this.mTmpValues);
        int i = VectorDrawable.VGroup.getPropertyIndex(this.mTmpValues.propertyName);
        if ((this.mTmpValues.type == Float.class || this.mTmpValues.type == float.class) && i >= 0) {
          long l1 = AnimatedVectorDrawable.nCreateGroupPropertyHolder(l, i, ((Float)this.mTmpValues.startValue).floatValue(), ((Float)this.mTmpValues.endValue).floatValue());
          if (this.mTmpValues.dataSource != null) {
            float[] arrayOfFloat = createFloatDataPoints(this.mTmpValues.dataSource, param1ObjectAnimator.getDuration());
            AnimatedVectorDrawable.nSetPropertyHolderData(l1, arrayOfFloat, arrayOfFloat.length);
          } 
          createNativeChildAnimator(l1, param1Long, param1ObjectAnimator);
        } 
      } 
    }
    
    private void createRTAnimatorForPath(ObjectAnimator param1ObjectAnimator, VectorDrawable.VPath param1VPath, long param1Long) {
      createNativeChildAnimator(AnimatedVectorDrawable.nCreatePathDataPropertyHolder(param1VPath.getNativePtr(), ((PathParser.PathData)this.mTmpValues.startValue).getNativePtr(), ((PathParser.PathData)this.mTmpValues.endValue).getNativePtr()), param1Long, param1ObjectAnimator);
    }
    
    private void createRTAnimatorForRootGroup(PropertyValuesHolder[] param1ArrayOfPropertyValuesHolder, ObjectAnimator param1ObjectAnimator, VectorDrawable.VectorDrawableState param1VectorDrawableState, long param1Long) {
      long l = param1VectorDrawableState.getNativeRenderer();
      if (!param1ObjectAnimator.getPropertyName().equals("alpha")) {
        if (this.mDrawable.mAnimatedVectorState.mShouldIgnoreInvalidAnim)
          return; 
        throw new UnsupportedOperationException("Only alpha is supported for root group");
      } 
      byte b = 0;
      while (true) {
        if (b < param1ArrayOfPropertyValuesHolder.length) {
          param1ArrayOfPropertyValuesHolder[b].getPropertyValues(this.mTmpValues);
          if (this.mTmpValues.propertyName.equals("alpha")) {
            Float float_1 = (Float)this.mTmpValues.startValue;
            Float float_2 = (Float)this.mTmpValues.endValue;
            break;
          } 
          b++;
          continue;
        } 
        param1ArrayOfPropertyValuesHolder = null;
        param1VectorDrawableState = null;
        break;
      } 
      if (param1ArrayOfPropertyValuesHolder == null && param1VectorDrawableState == null) {
        if (this.mDrawable.mAnimatedVectorState.mShouldIgnoreInvalidAnim)
          return; 
        throw new UnsupportedOperationException("No alpha values are specified");
      } 
      l = AnimatedVectorDrawable.nCreateRootAlphaPropertyHolder(l, param1ArrayOfPropertyValuesHolder.floatValue(), param1VectorDrawableState.floatValue());
      if (this.mTmpValues.dataSource != null) {
        float[] arrayOfFloat = createFloatDataPoints(this.mTmpValues.dataSource, param1ObjectAnimator.getDuration());
        AnimatedVectorDrawable.nSetPropertyHolderData(l, arrayOfFloat, arrayOfFloat.length);
      } 
      createNativeChildAnimator(l, param1Long, param1ObjectAnimator);
    }
    
    private void endAnimation() {
      AnimatedVectorDrawable.nEnd(this.mSetPtr);
      invalidateOwningView();
    }
    
    private static int getFrameCount(long param1Long) {
      int i = (int)(Choreographer.getInstance().getFrameIntervalNanos() / 1000000L);
      int j = Math.max(2, (int)Math.ceil(param1Long / i));
      i = j;
      if (j > 300) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Duration for the animation is too long :");
        stringBuilder.append(param1Long);
        stringBuilder.append(", the animation will subsample the keyframe or path data.");
        Log.w("AnimatedVectorDrawable", stringBuilder.toString());
        i = 300;
      } 
      return i;
    }
    
    private void handlePendingAction(int param1Int) {
      if (param1Int == 1) {
        startAnimation();
      } else if (param1Int == 2) {
        reverseAnimation();
      } else if (param1Int == 3) {
        resetAnimation();
      } else {
        if (param1Int == 4) {
          endAnimation();
          return;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Animation action ");
        stringBuilder.append(param1Int);
        stringBuilder.append("is not supported");
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
    }
    
    private void invalidateOwningView() {
      this.mDrawable.invalidateSelf();
    }
    
    private void onAnimationEnd(int param1Int) {
      if (param1Int != this.mLastListenerId)
        return; 
      this.mStarted = false;
      invalidateOwningView();
      Animator.AnimatorListener animatorListener = this.mListener;
      if (animatorListener != null)
        animatorListener.onAnimationEnd(null); 
    }
    
    private void parseAnimatorSet(AnimatorSet param1AnimatorSet, long param1Long) {
      ArrayList<Animator> arrayList = param1AnimatorSet.getChildAnimations();
      boolean bool = param1AnimatorSet.shouldPlayTogether();
      byte b = 0;
      long l;
      for (l = param1Long; b < arrayList.size(); l = param1Long) {
        Animator animator = arrayList.get(b);
        if (animator instanceof AnimatorSet) {
          parseAnimatorSet((AnimatorSet)animator, l);
        } else if (animator instanceof ObjectAnimator) {
          createRTAnimator((ObjectAnimator)animator, l);
        } 
        param1Long = l;
        if (!bool) {
          param1Long = l + animator.getTotalDuration();
          this.mContainsSequentialAnimators = true;
        } 
        b++;
      } 
    }
    
    private void resetAnimation() {
      AnimatedVectorDrawable.nReset(this.mSetPtr);
      invalidateOwningView();
    }
    
    private void reverseAnimation() {
      this.mStarted = true;
      long l = this.mSetPtr;
      int i = this.mLastListenerId + 1;
      this.mLastListenerId = i;
      AnimatedVectorDrawable.nReverse(l, this, i);
      invalidateOwningView();
      Animator.AnimatorListener animatorListener = this.mListener;
      if (animatorListener != null)
        animatorListener.onAnimationStart(null); 
    }
    
    private void startAnimation() {
      this.mStarted = true;
      if (this.mHandler == null)
        this.mHandler = new Handler(); 
      long l = this.mSetPtr;
      int i = this.mLastListenerId + 1;
      this.mLastListenerId = i;
      AnimatedVectorDrawable.nStart(l, this, i);
      invalidateOwningView();
      Animator.AnimatorListener animatorListener = this.mListener;
      if (animatorListener != null)
        animatorListener.onAnimationStart(null); 
    }
    
    private void transferPendingActions(AnimatedVectorDrawable.VectorDrawableAnimator param1VectorDrawableAnimator) {
      for (byte b = 0; b < this.mPendingAnimationActions.size(); b++) {
        int i = this.mPendingAnimationActions.get(b);
        if (i == 1) {
          param1VectorDrawableAnimator.start();
        } else if (i == 4) {
          param1VectorDrawableAnimator.end();
        } else if (i == 2) {
          param1VectorDrawableAnimator.reverse();
        } else if (i == 3) {
          param1VectorDrawableAnimator.reset();
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Animation action ");
          stringBuilder.append(i);
          stringBuilder.append("is not supported");
          throw new UnsupportedOperationException(stringBuilder.toString());
        } 
      } 
      this.mPendingAnimationActions.clear();
    }
    
    private boolean useLastSeenTarget() {
      WeakReference<RenderNode> weakReference = this.mLastSeenTarget;
      return (weakReference != null) ? useTarget(weakReference.get()) : false;
    }
    
    private boolean useTarget(RenderNode param1RenderNode) {
      if (param1RenderNode != null && param1RenderNode.isAttached()) {
        param1RenderNode.registerVectorDrawableAnimator(this);
        return true;
      } 
      return false;
    }
    
    public boolean canReverse() {
      return this.mIsReversible;
    }
    
    public void end() {
      if (!this.mInitialized)
        return; 
      if (useLastSeenTarget()) {
        endAnimation();
      } else {
        addPendingAction(4);
      } 
    }
    
    public long getAnimatorNativePtr() {
      return this.mSetPtr;
    }
    
    public void init(AnimatorSet param1AnimatorSet) {
      if (!this.mInitialized) {
        boolean bool;
        parseAnimatorSet(param1AnimatorSet, 0L);
        long l = this.mDrawable.mAnimatedVectorState.mVectorDrawable.getNativeTree();
        AnimatedVectorDrawable.nSetVectorDrawableTarget(this.mSetPtr, l);
        this.mInitialized = true;
        if (param1AnimatorSet.getTotalDuration() == -1L) {
          bool = true;
        } else {
          bool = false;
        } 
        this.mIsInfinite = bool;
        this.mIsReversible = true;
        if (this.mContainsSequentialAnimators) {
          this.mIsReversible = false;
        } else {
          for (byte b = 0; b < this.mStartDelays.size(); b++) {
            if (this.mStartDelays.get(b) > 0L) {
              this.mIsReversible = false;
              return;
            } 
          } 
        } 
        return;
      } 
      throw new UnsupportedOperationException("VectorDrawableAnimator cannot be re-initialized");
    }
    
    public boolean isInfinite() {
      return this.mIsInfinite;
    }
    
    public boolean isRunning() {
      return !this.mInitialized ? false : this.mStarted;
    }
    
    public boolean isStarted() {
      return this.mStarted;
    }
    
    public void onDraw(Canvas param1Canvas) {
      if (param1Canvas.isHardwareAccelerated())
        recordLastSeenTarget((RecordingCanvas)param1Canvas); 
    }
    
    public void pause() {}
    
    protected void recordLastSeenTarget(RecordingCanvas param1RecordingCanvas) {
      RenderNode renderNode = param1RecordingCanvas.mNode;
      this.mLastSeenTarget = new WeakReference<>(renderNode);
      if ((this.mInitialized || this.mPendingAnimationActions.size() > 0) && useTarget(renderNode)) {
        for (byte b = 0; b < this.mPendingAnimationActions.size(); b++)
          handlePendingAction(this.mPendingAnimationActions.get(b)); 
        this.mPendingAnimationActions.clear();
      } 
    }
    
    public void removeListener(Animator.AnimatorListener param1AnimatorListener) {
      this.mListener = null;
    }
    
    public void reset() {
      if (!this.mInitialized)
        return; 
      if (useLastSeenTarget()) {
        resetAnimation();
      } else {
        addPendingAction(3);
      } 
    }
    
    public void resume() {}
    
    public void reverse() {
      if (!this.mIsReversible || !this.mInitialized)
        return; 
      if (useLastSeenTarget()) {
        reverseAnimation();
      } else {
        addPendingAction(2);
      } 
    }
    
    public void setListener(Animator.AnimatorListener param1AnimatorListener) {
      this.mListener = param1AnimatorListener;
    }
    
    public void start() {
      if (!this.mInitialized)
        return; 
      if (useLastSeenTarget()) {
        startAnimation();
      } else {
        addPendingAction(1);
      } 
    }
  }
  
  private static class VectorDrawableAnimatorUI implements VectorDrawableAnimator {
    private final Drawable mDrawable;
    
    private boolean mIsInfinite = false;
    
    private ArrayList<Animator.AnimatorListener> mListenerArray = null;
    
    private AnimatorSet mSet = null;
    
    VectorDrawableAnimatorUI(AnimatedVectorDrawable param1AnimatedVectorDrawable) {
      this.mDrawable = param1AnimatedVectorDrawable;
    }
    
    private void invalidateOwningView() {
      this.mDrawable.invalidateSelf();
    }
    
    public boolean canReverse() {
      boolean bool;
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet != null && animatorSet.canReverse()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void end() {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null)
        return; 
      animatorSet.end();
    }
    
    public void init(AnimatorSet param1AnimatorSet) {
      if (this.mSet == null) {
        boolean bool;
        param1AnimatorSet = param1AnimatorSet.clone();
        this.mSet = param1AnimatorSet;
        if (param1AnimatorSet.getTotalDuration() == -1L) {
          bool = true;
        } else {
          bool = false;
        } 
        this.mIsInfinite = bool;
        ArrayList<Animator.AnimatorListener> arrayList = this.mListenerArray;
        if (arrayList != null && !arrayList.isEmpty()) {
          for (byte b = 0; b < this.mListenerArray.size(); b++)
            this.mSet.addListener(this.mListenerArray.get(b)); 
          this.mListenerArray.clear();
          this.mListenerArray = null;
        } 
        return;
      } 
      throw new UnsupportedOperationException("VectorDrawableAnimator cannot be re-initialized");
    }
    
    public boolean isInfinite() {
      return this.mIsInfinite;
    }
    
    public boolean isRunning() {
      boolean bool;
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet != null && animatorSet.isRunning()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isStarted() {
      boolean bool;
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet != null && animatorSet.isStarted()) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onDraw(Canvas param1Canvas) {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet != null && animatorSet.isStarted())
        invalidateOwningView(); 
    }
    
    public void pause() {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null)
        return; 
      animatorSet.pause();
    }
    
    public void removeListener(Animator.AnimatorListener param1AnimatorListener) {
      ArrayList<Animator.AnimatorListener> arrayList;
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null) {
        arrayList = this.mListenerArray;
        if (arrayList == null)
          return; 
        arrayList.remove(param1AnimatorListener);
      } else {
        arrayList.removeListener(param1AnimatorListener);
      } 
    }
    
    public void reset() {
      if (this.mSet == null)
        return; 
      start();
      this.mSet.cancel();
    }
    
    public void resume() {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null)
        return; 
      animatorSet.resume();
    }
    
    public void reverse() {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null)
        return; 
      animatorSet.reverse();
      invalidateOwningView();
    }
    
    public void setListener(Animator.AnimatorListener param1AnimatorListener) {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null) {
        if (this.mListenerArray == null)
          this.mListenerArray = new ArrayList<>(); 
        this.mListenerArray.add(param1AnimatorListener);
      } else {
        animatorSet.addListener(param1AnimatorListener);
      } 
    }
    
    public void start() {
      AnimatorSet animatorSet = this.mSet;
      if (animatorSet == null || animatorSet.isStarted())
        return; 
      this.mSet.start();
      invalidateOwningView();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */