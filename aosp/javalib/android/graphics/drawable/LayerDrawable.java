package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class LayerDrawable extends Drawable implements Drawable.Callback {
  public static final int INSET_UNDEFINED = -2147483648;
  
  private static final String LOG_TAG = "LayerDrawable";
  
  public static final int PADDING_MODE_NEST = 0;
  
  public static final int PADDING_MODE_STACK = 1;
  
  private boolean mChildRequestedInvalidation;
  
  private Rect mHotspotBounds;
  
  LayerState mLayerState;
  
  private boolean mMutated;
  
  private int[] mPaddingB;
  
  private int[] mPaddingL;
  
  private int[] mPaddingR;
  
  private int[] mPaddingT;
  
  private boolean mSuspendChildInvalidation;
  
  private final Rect mTmpContainer = new Rect();
  
  private final Rect mTmpOutRect = new Rect();
  
  private final Rect mTmpRect = new Rect();
  
  LayerDrawable() {
    this((LayerState)null, (Resources)null);
  }
  
  LayerDrawable(LayerState paramLayerState, Resources paramResources) {
    paramLayerState = createConstantState(paramLayerState, paramResources);
    this.mLayerState = paramLayerState;
    if (paramLayerState.mNumChildren > 0) {
      ensurePadding();
      refreshPadding();
    } 
  }
  
  public LayerDrawable(Drawable[] paramArrayOfDrawable) {
    this(paramArrayOfDrawable, (LayerState)null);
  }
  
  LayerDrawable(Drawable[] paramArrayOfDrawable, LayerState paramLayerState) {
    this(paramLayerState, (Resources)null);
    if (paramArrayOfDrawable != null) {
      int i = paramArrayOfDrawable.length;
      ChildDrawable[] arrayOfChildDrawable = new ChildDrawable[i];
      for (byte b = 0; b < i; b++) {
        arrayOfChildDrawable[b] = new ChildDrawable(this.mLayerState.mDensity);
        Drawable drawable = paramArrayOfDrawable[b];
        (arrayOfChildDrawable[b]).mDrawable = drawable;
        if (drawable != null) {
          drawable.setCallback(this);
          LayerState layerState = this.mLayerState;
          layerState.mChildrenChangingConfigurations |= drawable.getChangingConfigurations();
        } 
      } 
      this.mLayerState.mNumChildren = i;
      this.mLayerState.mChildren = arrayOfChildDrawable;
      ensurePadding();
      refreshPadding();
      return;
    } 
    throw new IllegalArgumentException("layers must be non-null");
  }
  
  private void computeNestedPadding(Rect paramRect) {
    paramRect.left = 0;
    paramRect.top = 0;
    paramRect.right = 0;
    paramRect.bottom = 0;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      refreshChildPadding(b, arrayOfChildDrawable[b]);
      paramRect.left += this.mPaddingL[b];
      paramRect.top += this.mPaddingT[b];
      paramRect.right += this.mPaddingR[b];
      paramRect.bottom += this.mPaddingB[b];
    } 
  }
  
  private void computeStackedPadding(Rect paramRect) {
    paramRect.left = 0;
    paramRect.top = 0;
    paramRect.right = 0;
    paramRect.bottom = 0;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      refreshChildPadding(b, arrayOfChildDrawable[b]);
      paramRect.left = Math.max(paramRect.left, this.mPaddingL[b]);
      paramRect.top = Math.max(paramRect.top, this.mPaddingT[b]);
      paramRect.right = Math.max(paramRect.right, this.mPaddingR[b]);
      paramRect.bottom = Math.max(paramRect.bottom, this.mPaddingB[b]);
    } 
  }
  
  private ChildDrawable createLayer(Drawable paramDrawable) {
    ChildDrawable childDrawable = new ChildDrawable(this.mLayerState.mDensity);
    childDrawable.mDrawable = paramDrawable;
    return childDrawable;
  }
  
  private Drawable getFirstNonNullDrawable() {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        return drawable; 
    } 
    return null;
  }
  
  private void inflateLayers(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    LayerState layerState = this.mLayerState;
    int i = paramXmlPullParser.getDepth() + 1;
    while (true) {
      int j = paramXmlPullParser.next();
      if (j != 1) {
        int k = paramXmlPullParser.getDepth();
        if (k >= i || j != 3) {
          if (j != 2 || k > i || !paramXmlPullParser.getName().equals("item"))
            continue; 
          ChildDrawable childDrawable = new ChildDrawable(layerState.mDensity);
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.LayerDrawableItem);
          updateLayerFromTypedArray(childDrawable, typedArray);
          typedArray.recycle();
          if (childDrawable.mDrawable == null && (childDrawable.mThemeAttrs == null || childDrawable.mThemeAttrs[4] == 0))
            while (true) {
              j = paramXmlPullParser.next();
              if (j == 4)
                continue; 
              if (j == 2) {
                childDrawable.mDrawable = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
                childDrawable.mDrawable.setCallback(this);
                layerState.mChildrenChangingConfigurations |= childDrawable.mDrawable.getChangingConfigurations();
                break;
              } 
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(paramXmlPullParser.getPositionDescription());
              stringBuilder.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
              throw new XmlPullParserException(stringBuilder.toString());
            }  
          addLayer(childDrawable);
          continue;
        } 
      } 
      break;
    } 
  }
  
  private boolean refreshChildPadding(int paramInt, ChildDrawable paramChildDrawable) {
    if (paramChildDrawable.mDrawable != null) {
      Rect rect = this.mTmpRect;
      paramChildDrawable.mDrawable.getPadding(rect);
      if (rect.left != this.mPaddingL[paramInt] || rect.top != this.mPaddingT[paramInt] || rect.right != this.mPaddingR[paramInt] || rect.bottom != this.mPaddingB[paramInt]) {
        this.mPaddingL[paramInt] = rect.left;
        this.mPaddingT[paramInt] = rect.top;
        this.mPaddingR[paramInt] = rect.right;
        this.mPaddingB[paramInt] = rect.bottom;
        return true;
      } 
    } 
    return false;
  }
  
  private static int resolveGravity(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int i = paramInt1;
    if (!Gravity.isHorizontal(paramInt1))
      if (paramInt2 < 0) {
        i = paramInt1 | 0x7;
      } else {
        i = paramInt1 | 0x800003;
      }  
    paramInt1 = i;
    if (!Gravity.isVertical(i))
      if (paramInt3 < 0) {
        paramInt1 = i | 0x70;
      } else {
        paramInt1 = i | 0x30;
      }  
    i = paramInt1;
    if (paramInt2 < 0) {
      i = paramInt1;
      if (paramInt4 < 0)
        i = paramInt1 | 0x7; 
    } 
    paramInt1 = i;
    if (paramInt3 < 0) {
      paramInt1 = i;
      if (paramInt5 < 0)
        paramInt1 = i | 0x70; 
    } 
    return paramInt1;
  }
  
  private void resumeChildInvalidation() {
    this.mSuspendChildInvalidation = false;
    if (this.mChildRequestedInvalidation) {
      this.mChildRequestedInvalidation = false;
      invalidateSelf();
    } 
  }
  
  private void setLayerInsetInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
    ChildDrawable childDrawable = this.mLayerState.mChildren[paramInt1];
    childDrawable.mInsetL = paramInt2;
    childDrawable.mInsetT = paramInt3;
    childDrawable.mInsetR = paramInt4;
    childDrawable.mInsetB = paramInt5;
    childDrawable.mInsetS = paramInt6;
    childDrawable.mInsetE = paramInt7;
  }
  
  private void suspendChildInvalidation() {
    this.mSuspendChildInvalidation = true;
  }
  
  private void updateLayerBounds(Rect paramRect) {
    try {
      suspendChildInvalidation();
      updateLayerBoundsInternal(paramRect);
      return;
    } finally {
      resumeChildInvalidation();
    } 
  }
  
  private void updateLayerBoundsInternal(Rect paramRect) {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    Rect rect = this.mTmpOutRect;
    int n = getLayoutDirection();
    int i1 = 0;
    if (n == 1) {
      i2 = 1;
    } else {
      i2 = 0;
    } 
    int i3 = i2;
    int i2 = i1;
    if (this.mLayerState.mPaddingMode == 0)
      i2 = 1; 
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i4 = this.mLayerState.mNumChildren;
    i1 = 0;
    int i5 = i2;
    i2 = i1;
    while (true) {
      Rect rect1 = paramRect;
      if (i2 < i4) {
        int i6;
        int i7;
        int i8;
        ChildDrawable childDrawable = arrayOfChildDrawable[i2];
        Drawable drawable = childDrawable.mDrawable;
        if (drawable == null) {
          i6 = i;
          i7 = j;
          i8 = k;
          i1 = m;
        } else {
          i6 = childDrawable.mInsetT;
          i7 = childDrawable.mInsetB;
          if (i3 != 0) {
            i8 = childDrawable.mInsetE;
          } else {
            i8 = childDrawable.mInsetS;
          } 
          int i9 = i2;
          if (i3 != 0) {
            i1 = childDrawable.mInsetS;
          } else {
            i1 = childDrawable.mInsetE;
          } 
          if (i8 == Integer.MIN_VALUE)
            i8 = childDrawable.mInsetL; 
          if (i1 == Integer.MIN_VALUE)
            i1 = childDrawable.mInsetR; 
          Rect rect2 = this.mTmpContainer;
          rect2.set(rect1.left + i8 + i, rect1.top + i6 + j, rect1.right - i1 - k, rect1.bottom - i7 - m);
          i6 = drawable.getIntrinsicWidth();
          i7 = drawable.getIntrinsicHeight();
          i1 = childDrawable.mWidth;
          i8 = childDrawable.mHeight;
          int i10 = resolveGravity(childDrawable.mGravity, i1, i8, i6, i7);
          if (i1 < 0)
            i1 = i6; 
          if (i8 < 0)
            i8 = i7; 
          Gravity.apply(i10, i1, i8, rect2, rect, n);
          drawable.setBounds(rect);
          i6 = i;
          i7 = j;
          i8 = k;
          i1 = m;
          if (i5 != 0) {
            i6 = i + this.mPaddingL[i9];
            i8 = k + this.mPaddingR[i9];
            i7 = j + this.mPaddingT[i9];
            i1 = m + this.mPaddingB[i9];
          } 
        } 
        i2++;
        i = i6;
        j = i7;
        k = i8;
        m = i1;
        continue;
      } 
      break;
    } 
  }
  
  private void updateLayerFromTypedArray(ChildDrawable paramChildDrawable, TypedArray paramTypedArray) {
    LayerState layerState = this.mLayerState;
    layerState.mChildrenChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    paramChildDrawable.mThemeAttrs = paramTypedArray.extractThemeAttrs();
    int i = paramTypedArray.getIndexCount();
    for (byte b = 0; b < i; b++) {
      int j = paramTypedArray.getIndex(b);
      switch (j) {
        case 10:
          paramChildDrawable.mInsetE = paramTypedArray.getDimensionPixelOffset(j, paramChildDrawable.mInsetE);
          break;
        case 9:
          paramChildDrawable.mInsetS = paramTypedArray.getDimensionPixelOffset(j, paramChildDrawable.mInsetS);
          break;
        case 8:
          paramChildDrawable.mInsetB = paramTypedArray.getDimensionPixelOffset(j, paramChildDrawable.mInsetB);
          break;
        case 7:
          paramChildDrawable.mInsetR = paramTypedArray.getDimensionPixelOffset(j, paramChildDrawable.mInsetR);
          break;
        case 6:
          paramChildDrawable.mInsetT = paramTypedArray.getDimensionPixelOffset(j, paramChildDrawable.mInsetT);
          break;
        case 5:
          paramChildDrawable.mInsetL = paramTypedArray.getDimensionPixelOffset(j, paramChildDrawable.mInsetL);
          break;
        case 3:
          paramChildDrawable.mWidth = paramTypedArray.getDimensionPixelSize(j, paramChildDrawable.mWidth);
          break;
        case 2:
          paramChildDrawable.mHeight = paramTypedArray.getDimensionPixelSize(j, paramChildDrawable.mHeight);
          break;
        case 1:
          paramChildDrawable.mId = paramTypedArray.getResourceId(j, paramChildDrawable.mId);
          break;
        case 0:
          paramChildDrawable.mGravity = paramTypedArray.getInteger(j, paramChildDrawable.mGravity);
          break;
      } 
    } 
    Drawable drawable = paramTypedArray.getDrawable(4);
    if (drawable != null) {
      if (paramChildDrawable.mDrawable != null)
        paramChildDrawable.mDrawable.setCallback(null); 
      paramChildDrawable.mDrawable = drawable;
      paramChildDrawable.mDrawable.setCallback(this);
      layerState.mChildrenChangingConfigurations |= paramChildDrawable.mDrawable.getChangingConfigurations();
    } 
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    LayerState layerState = this.mLayerState;
    layerState.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    LayerState.access$002(layerState, paramTypedArray.extractThemeAttrs());
    int i = paramTypedArray.getIndexCount();
    for (byte b = 0; b < i; b++) {
      int j = paramTypedArray.getIndex(b);
      switch (j) {
        case 8:
          LayerState.access$202(layerState, paramTypedArray.getInteger(j, layerState.mPaddingMode));
          break;
        case 7:
          LayerState.access$102(layerState, paramTypedArray.getBoolean(j, layerState.mAutoMirrored));
          break;
        case 6:
          layerState.mPaddingEnd = paramTypedArray.getDimensionPixelOffset(j, layerState.mPaddingEnd);
          break;
        case 5:
          layerState.mPaddingStart = paramTypedArray.getDimensionPixelOffset(j, layerState.mPaddingStart);
          break;
        case 4:
          layerState.mOpacityOverride = paramTypedArray.getInt(j, layerState.mOpacityOverride);
          break;
        case 3:
          layerState.mPaddingBottom = paramTypedArray.getDimensionPixelOffset(j, layerState.mPaddingBottom);
          break;
        case 2:
          layerState.mPaddingRight = paramTypedArray.getDimensionPixelOffset(j, layerState.mPaddingRight);
          break;
        case 1:
          layerState.mPaddingTop = paramTypedArray.getDimensionPixelOffset(j, layerState.mPaddingTop);
          break;
        case 0:
          layerState.mPaddingLeft = paramTypedArray.getDimensionPixelOffset(j, layerState.mPaddingLeft);
          break;
      } 
    } 
  }
  
  public int addLayer(Drawable paramDrawable) {
    ChildDrawable childDrawable = createLayer(paramDrawable);
    int i = addLayer(childDrawable);
    ensurePadding();
    refreshChildPadding(i, childDrawable);
    return i;
  }
  
  int addLayer(ChildDrawable paramChildDrawable) {
    byte b;
    LayerState layerState = this.mLayerState;
    if (layerState.mChildren != null) {
      b = layerState.mChildren.length;
    } else {
      b = 0;
    } 
    int i = layerState.mNumChildren;
    if (i >= b) {
      ChildDrawable[] arrayOfChildDrawable = new ChildDrawable[b + 10];
      if (i > 0)
        System.arraycopy(layerState.mChildren, 0, arrayOfChildDrawable, 0, i); 
      layerState.mChildren = arrayOfChildDrawable;
    } 
    layerState.mChildren[i] = paramChildDrawable;
    layerState.mNumChildren++;
    layerState.invalidateCache();
    return i;
  }
  
  ChildDrawable addLayer(Drawable paramDrawable, int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    ChildDrawable childDrawable = createLayer(paramDrawable);
    childDrawable.mId = paramInt1;
    childDrawable.mThemeAttrs = paramArrayOfint;
    childDrawable.mDrawable.setAutoMirrored(isAutoMirrored());
    childDrawable.mInsetL = paramInt2;
    childDrawable.mInsetT = paramInt3;
    childDrawable.mInsetR = paramInt4;
    childDrawable.mInsetB = paramInt5;
    addLayer(childDrawable);
    LayerState layerState = this.mLayerState;
    layerState.mChildrenChangingConfigurations |= paramDrawable.getChangingConfigurations();
    paramDrawable.setCallback(this);
    return childDrawable;
  }
  
  public void applyTheme(Resources.Theme paramTheme) {
    super.applyTheme(paramTheme);
    LayerState layerState = this.mLayerState;
    int i = Drawable.resolveDensity(paramTheme.getResources(), 0);
    layerState.setDensity(i);
    if (layerState.mThemeAttrs != null) {
      TypedArray typedArray = paramTheme.resolveAttributes(layerState.mThemeAttrs, R.styleable.LayerDrawable);
      updateStateFromTypedArray(typedArray);
      typedArray.recycle();
    } 
    ChildDrawable[] arrayOfChildDrawable = layerState.mChildren;
    int j = layerState.mNumChildren;
    for (byte b = 0; b < j; b++) {
      ChildDrawable childDrawable = arrayOfChildDrawable[b];
      childDrawable.setDensity(i);
      if (childDrawable.mThemeAttrs != null) {
        TypedArray typedArray = paramTheme.resolveAttributes(childDrawable.mThemeAttrs, R.styleable.LayerDrawableItem);
        updateLayerFromTypedArray(childDrawable, typedArray);
        typedArray.recycle();
      } 
      Drawable drawable = childDrawable.mDrawable;
      if (drawable != null && drawable.canApplyTheme()) {
        drawable.applyTheme(paramTheme);
        layerState.mChildrenChangingConfigurations |= drawable.getChangingConfigurations();
      } 
    } 
  }
  
  public boolean canApplyTheme() {
    return (this.mLayerState.canApplyTheme() || super.canApplyTheme());
  }
  
  public void clearMutated() {
    super.clearMutated();
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.clearMutated(); 
    } 
    this.mMutated = false;
  }
  
  LayerState createConstantState(LayerState paramLayerState, Resources paramResources) {
    return new LayerState(paramLayerState, this, paramResources);
  }
  
  public void draw(Canvas paramCanvas) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.draw(paramCanvas); 
    } 
  }
  
  void ensurePadding() {
    int i = this.mLayerState.mNumChildren;
    int[] arrayOfInt = this.mPaddingL;
    if (arrayOfInt != null && arrayOfInt.length >= i)
      return; 
    this.mPaddingL = new int[i];
    this.mPaddingT = new int[i];
    this.mPaddingR = new int[i];
    this.mPaddingB = new int[i];
  }
  
  public Drawable findDrawableByLayerId(int paramInt) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (int i = this.mLayerState.mNumChildren - 1; i >= 0; i--) {
      if ((arrayOfChildDrawable[i]).mId == paramInt)
        return (arrayOfChildDrawable[i]).mDrawable; 
    } 
    return null;
  }
  
  public int findIndexByLayerId(int paramInt) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      if ((arrayOfChildDrawable[b]).mId == paramInt)
        return b; 
    } 
    return -1;
  }
  
  public int getAlpha() {
    Drawable drawable = getFirstNonNullDrawable();
    return (drawable != null) ? drawable.getAlpha() : super.getAlpha();
  }
  
  public int getBottomPadding() {
    return this.mLayerState.mPaddingBottom;
  }
  
  public int getChangingConfigurations() {
    return super.getChangingConfigurations() | this.mLayerState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState() {
    if (this.mLayerState.canConstantState()) {
      this.mLayerState.mChangingConfigurations = getChangingConfigurations();
      return this.mLayerState;
    } 
    return null;
  }
  
  public Drawable getDrawable(int paramInt) {
    if (paramInt < this.mLayerState.mNumChildren)
      return (this.mLayerState.mChildren[paramInt]).mDrawable; 
    throw new IndexOutOfBoundsException();
  }
  
  public int getEndPadding() {
    return this.mLayerState.mPaddingEnd;
  }
  
  public void getHotspotBounds(Rect paramRect) {
    Rect rect = this.mHotspotBounds;
    if (rect != null) {
      paramRect.set(rect);
    } else {
      super.getHotspotBounds(paramRect);
    } 
  }
  
  public int getId(int paramInt) {
    if (paramInt < this.mLayerState.mNumChildren)
      return (this.mLayerState.mChildren[paramInt]).mId; 
    throw new IndexOutOfBoundsException();
  }
  
  public int getIntrinsicHeight() {
    boolean bool;
    int i = -1;
    int j = 0;
    int k = 0;
    if (this.mLayerState.mPaddingMode == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int m = this.mLayerState.mNumChildren;
    byte b = 0;
    while (b < m) {
      int n;
      int i1;
      ChildDrawable childDrawable = arrayOfChildDrawable[b];
      if (childDrawable.mDrawable == null) {
        n = j;
        i1 = k;
      } else {
        if (childDrawable.mHeight < 0) {
          i2 = childDrawable.mDrawable.getIntrinsicHeight();
        } else {
          i2 = childDrawable.mHeight;
        } 
        if (i2 < 0) {
          i1 = -1;
        } else {
          i1 = childDrawable.mInsetT + i2 + childDrawable.mInsetB + j + k;
        } 
        int i2 = i;
        if (i1 > i)
          i2 = i1; 
        i = i2;
        n = j;
        i1 = k;
        if (bool) {
          n = j + this.mPaddingT[b];
          i1 = k + this.mPaddingB[b];
          i = i2;
        } 
      } 
      b++;
      j = n;
      k = i1;
    } 
    return i;
  }
  
  public int getIntrinsicWidth() {
    boolean bool2;
    int i = -1;
    int j = 0;
    int k = 0;
    int m = this.mLayerState.mPaddingMode;
    boolean bool1 = false;
    if (m == 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (getLayoutDirection() == 1)
      bool1 = true; 
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int n = this.mLayerState.mNumChildren;
    byte b = 0;
    while (b < n) {
      ChildDrawable childDrawable = arrayOfChildDrawable[b];
      if (childDrawable.mDrawable == null) {
        m = i;
      } else {
        int i1;
        int i2;
        if (bool1) {
          i1 = childDrawable.mInsetE;
        } else {
          i1 = childDrawable.mInsetS;
        } 
        if (bool1) {
          m = childDrawable.mInsetS;
        } else {
          m = childDrawable.mInsetE;
        } 
        if (i1 == Integer.MIN_VALUE)
          i1 = childDrawable.mInsetL; 
        if (m == Integer.MIN_VALUE)
          m = childDrawable.mInsetR; 
        if (childDrawable.mWidth < 0) {
          i2 = childDrawable.mDrawable.getIntrinsicWidth();
        } else {
          i2 = childDrawable.mWidth;
        } 
        if (i2 < 0) {
          i1 = -1;
        } else {
          i1 = i2 + i1 + m + j + k;
        } 
        m = i;
        if (i1 > i)
          m = i1; 
        if (bool2) {
          j += this.mPaddingL[b];
          k += this.mPaddingR[b];
        } 
      } 
      b++;
      i = m;
    } 
    return i;
  }
  
  public int getLayerGravity(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mGravity;
  }
  
  public int getLayerHeight(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mHeight;
  }
  
  public int getLayerInsetBottom(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mInsetB;
  }
  
  public int getLayerInsetEnd(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mInsetE;
  }
  
  public int getLayerInsetLeft(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mInsetL;
  }
  
  public int getLayerInsetRight(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mInsetR;
  }
  
  public int getLayerInsetStart(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mInsetS;
  }
  
  public int getLayerInsetTop(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mInsetT;
  }
  
  public int getLayerWidth(int paramInt) {
    return (this.mLayerState.mChildren[paramInt]).mWidth;
  }
  
  public int getLeftPadding() {
    return this.mLayerState.mPaddingLeft;
  }
  
  public int getNumberOfLayers() {
    return this.mLayerState.mNumChildren;
  }
  
  public int getOpacity() {
    return (this.mLayerState.mOpacityOverride != 0) ? this.mLayerState.mOpacityOverride : this.mLayerState.getOpacity();
  }
  
  public void getOutline(Outline paramOutline) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null) {
        drawable.getOutline(paramOutline);
        if (!paramOutline.isEmpty())
          return; 
      } 
    } 
  }
  
  public boolean getPadding(Rect paramRect) {
    int m;
    LayerState layerState = this.mLayerState;
    if (layerState.mPaddingMode == 0) {
      computeNestedPadding(paramRect);
    } else {
      computeStackedPadding(paramRect);
    } 
    int i = layerState.mPaddingTop;
    int j = layerState.mPaddingBottom;
    int k = getLayoutDirection();
    boolean bool = false;
    if (k == 1) {
      m = 1;
    } else {
      m = 0;
    } 
    if (m) {
      k = layerState.mPaddingEnd;
    } else {
      k = layerState.mPaddingStart;
    } 
    if (m) {
      m = layerState.mPaddingStart;
    } else {
      m = layerState.mPaddingEnd;
    } 
    if (k < 0)
      k = layerState.mPaddingLeft; 
    if (m < 0)
      m = layerState.mPaddingRight; 
    if (k >= 0)
      paramRect.left = k; 
    if (i >= 0)
      paramRect.top = i; 
    if (m >= 0)
      paramRect.right = m; 
    if (j >= 0)
      paramRect.bottom = j; 
    if (paramRect.left != 0 || paramRect.top != 0 || paramRect.right != 0 || paramRect.bottom != 0)
      bool = true; 
    return bool;
  }
  
  public int getPaddingMode() {
    return this.mLayerState.mPaddingMode;
  }
  
  public int getRightPadding() {
    return this.mLayerState.mPaddingRight;
  }
  
  public int getStartPadding() {
    return this.mLayerState.mPaddingStart;
  }
  
  public int getTopPadding() {
    return this.mLayerState.mPaddingTop;
  }
  
  public boolean hasFocusStateSpecified() {
    return this.mLayerState.hasFocusStateSpecified();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme) throws XmlPullParserException, IOException {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    LayerState layerState = this.mLayerState;
    int i = Drawable.resolveDensity(paramResources, 0);
    layerState.setDensity(i);
    TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.LayerDrawable);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
    ChildDrawable[] arrayOfChildDrawable = layerState.mChildren;
    int j = layerState.mNumChildren;
    for (byte b = 0; b < j; b++)
      arrayOfChildDrawable[b].setDensity(i); 
    inflateLayers(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    ensurePadding();
    refreshPadding();
  }
  
  public void invalidateDrawable(Drawable paramDrawable) {
    if (this.mSuspendChildInvalidation) {
      this.mChildRequestedInvalidation = true;
    } else {
      this.mLayerState.invalidateCache();
      invalidateSelf();
    } 
  }
  
  public boolean isAutoMirrored() {
    return this.mLayerState.mAutoMirrored;
  }
  
  public boolean isProjected() {
    if (super.isProjected())
      return true; 
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null && drawable.isProjected())
        return true; 
    } 
    return false;
  }
  
  public boolean isStateful() {
    return this.mLayerState.isStateful();
  }
  
  public void jumpToCurrentState() {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.jumpToCurrentState(); 
    } 
  }
  
  public Drawable mutate() {
    if (!this.mMutated && super.mutate() == this) {
      LayerState layerState = createConstantState(this.mLayerState, (Resources)null);
      this.mLayerState = layerState;
      ChildDrawable[] arrayOfChildDrawable = layerState.mChildren;
      int i = this.mLayerState.mNumChildren;
      for (byte b = 0; b < i; b++) {
        Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
        if (drawable != null)
          drawable.mutate(); 
      } 
      this.mMutated = true;
    } 
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect) {
    updateLayerBounds(paramRect);
  }
  
  public boolean onLayoutDirectionChanged(int paramInt) {
    boolean bool = false;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    byte b = 0;
    while (b < i) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      boolean bool1 = bool;
      if (drawable != null)
        bool1 = bool | drawable.setLayoutDirection(paramInt); 
      b++;
      bool = bool1;
    } 
    updateLayerBounds(getBounds());
    return bool;
  }
  
  protected boolean onLevelChange(int paramInt) {
    boolean bool = false;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    byte b = 0;
    while (b < i) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      boolean bool1 = bool;
      if (drawable != null) {
        bool1 = bool;
        if (drawable.setLevel(paramInt)) {
          refreshChildPadding(b, arrayOfChildDrawable[b]);
          bool1 = true;
        } 
      } 
      b++;
      bool = bool1;
    } 
    if (bool)
      updateLayerBounds(getBounds()); 
    return bool;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool = false;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    byte b = 0;
    while (b < i) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      boolean bool1 = bool;
      if (drawable != null) {
        bool1 = bool;
        if (drawable.isStateful()) {
          bool1 = bool;
          if (drawable.setState(paramArrayOfint)) {
            refreshChildPadding(b, arrayOfChildDrawable[b]);
            bool1 = true;
          } 
        } 
      } 
      b++;
      bool = bool1;
    } 
    if (bool)
      updateLayerBounds(getBounds()); 
    return bool;
  }
  
  void refreshPadding() {
    int i = this.mLayerState.mNumChildren;
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    for (byte b = 0; b < i; b++)
      refreshChildPadding(b, arrayOfChildDrawable[b]); 
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong) {
    scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setAlpha(paramInt); 
    } 
  }
  
  public void setAutoMirrored(boolean paramBoolean) {
    LayerState.access$102(this.mLayerState, paramBoolean);
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setAutoMirrored(paramBoolean); 
    } 
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setColorFilter(paramColorFilter); 
    } 
  }
  
  public void setDither(boolean paramBoolean) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setDither(paramBoolean); 
    } 
  }
  
  public void setDrawable(int paramInt, Drawable paramDrawable) {
    if (paramInt < this.mLayerState.mNumChildren) {
      ChildDrawable childDrawable = this.mLayerState.mChildren[paramInt];
      if (childDrawable.mDrawable != null) {
        if (paramDrawable != null)
          paramDrawable.setBounds(childDrawable.mDrawable.getBounds()); 
        childDrawable.mDrawable.setCallback(null);
      } 
      if (paramDrawable != null)
        paramDrawable.setCallback(this); 
      childDrawable.mDrawable = paramDrawable;
      this.mLayerState.invalidateCache();
      refreshChildPadding(paramInt, childDrawable);
      return;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public boolean setDrawableByLayerId(int paramInt, Drawable paramDrawable) {
    paramInt = findIndexByLayerId(paramInt);
    if (paramInt < 0)
      return false; 
    setDrawable(paramInt, paramDrawable);
    return true;
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setHotspot(paramFloat1, paramFloat2); 
    } 
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4); 
    } 
    Rect rect = this.mHotspotBounds;
    if (rect == null) {
      this.mHotspotBounds = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    } else {
      rect.set(paramInt1, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  public void setId(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mId = paramInt2;
  }
  
  public void setLayerGravity(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mGravity = paramInt2;
  }
  
  public void setLayerHeight(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mHeight = paramInt2;
  }
  
  public void setLayerInset(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    setLayerInsetInternal(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, -2147483648, -2147483648);
  }
  
  public void setLayerInsetBottom(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mInsetB = paramInt2;
  }
  
  public void setLayerInsetEnd(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mInsetE = paramInt2;
  }
  
  public void setLayerInsetLeft(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mInsetL = paramInt2;
  }
  
  public void setLayerInsetRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    setLayerInsetInternal(paramInt1, 0, paramInt3, 0, paramInt5, paramInt2, paramInt4);
  }
  
  public void setLayerInsetRight(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mInsetR = paramInt2;
  }
  
  public void setLayerInsetStart(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mInsetS = paramInt2;
  }
  
  public void setLayerInsetTop(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mInsetT = paramInt2;
  }
  
  public void setLayerSize(int paramInt1, int paramInt2, int paramInt3) {
    ChildDrawable childDrawable = this.mLayerState.mChildren[paramInt1];
    childDrawable.mWidth = paramInt2;
    childDrawable.mHeight = paramInt3;
  }
  
  public void setLayerWidth(int paramInt1, int paramInt2) {
    (this.mLayerState.mChildren[paramInt1]).mWidth = paramInt2;
  }
  
  public void setOpacity(int paramInt) {
    this.mLayerState.mOpacityOverride = paramInt;
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    LayerState layerState = this.mLayerState;
    layerState.mPaddingLeft = paramInt1;
    layerState.mPaddingTop = paramInt2;
    layerState.mPaddingRight = paramInt3;
    layerState.mPaddingBottom = paramInt4;
    layerState.mPaddingStart = -1;
    layerState.mPaddingEnd = -1;
  }
  
  public void setPaddingMode(int paramInt) {
    if (this.mLayerState.mPaddingMode != paramInt)
      LayerState.access$202(this.mLayerState, paramInt); 
  }
  
  public void setPaddingRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    LayerState layerState = this.mLayerState;
    layerState.mPaddingStart = paramInt1;
    layerState.mPaddingTop = paramInt2;
    layerState.mPaddingEnd = paramInt3;
    layerState.mPaddingBottom = paramInt4;
    layerState.mPaddingLeft = -1;
    layerState.mPaddingRight = -1;
  }
  
  public void setTintBlendMode(BlendMode paramBlendMode) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setTintBlendMode(paramBlendMode); 
    } 
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setTintList(paramColorStateList); 
    } 
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    ChildDrawable[] arrayOfChildDrawable = this.mLayerState.mChildren;
    int i = this.mLayerState.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null)
        drawable.setVisible(paramBoolean1, paramBoolean2); 
    } 
    return bool;
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable) {
    unscheduleSelf(paramRunnable);
  }
  
  static class ChildDrawable {
    public int mDensity;
    
    public Drawable mDrawable;
    
    public int mGravity;
    
    public int mHeight;
    
    public int mId;
    
    public int mInsetB;
    
    public int mInsetE;
    
    public int mInsetL;
    
    public int mInsetR;
    
    public int mInsetS;
    
    public int mInsetT;
    
    public int[] mThemeAttrs;
    
    public int mWidth;
    
    ChildDrawable(int param1Int) {
      this.mDensity = 160;
      this.mInsetS = Integer.MIN_VALUE;
      this.mInsetE = Integer.MIN_VALUE;
      this.mWidth = -1;
      this.mHeight = -1;
      this.mGravity = 0;
      this.mId = -1;
      this.mDensity = param1Int;
    }
    
    ChildDrawable(ChildDrawable param1ChildDrawable, LayerDrawable param1LayerDrawable, Resources param1Resources) {
      Drawable drawable2;
      this.mDensity = 160;
      this.mInsetS = Integer.MIN_VALUE;
      this.mInsetE = Integer.MIN_VALUE;
      this.mWidth = -1;
      this.mHeight = -1;
      this.mGravity = 0;
      this.mId = -1;
      Drawable drawable1 = param1ChildDrawable.mDrawable;
      if (drawable1 != null) {
        Drawable.ConstantState constantState = drawable1.getConstantState();
        if (constantState == null) {
          Drawable drawable = drawable1;
          drawable2 = drawable;
          if (drawable1.getCallback() != null) {
            Log.w("LayerDrawable", "Invalid drawable added to LayerDrawable! Drawable already belongs to another owner but does not expose a constant state.", new RuntimeException());
            drawable2 = drawable;
          } 
        } else if (param1Resources != null) {
          drawable2 = drawable2.newDrawable(param1Resources);
        } else {
          drawable2 = drawable2.newDrawable();
        } 
        drawable2.setLayoutDirection(drawable1.getLayoutDirection());
        drawable2.setBounds(drawable1.getBounds());
        drawable2.setLevel(drawable1.getLevel());
        drawable2.setCallback(param1LayerDrawable);
      } else {
        drawable2 = null;
      } 
      this.mDrawable = drawable2;
      this.mThemeAttrs = param1ChildDrawable.mThemeAttrs;
      this.mInsetL = param1ChildDrawable.mInsetL;
      this.mInsetT = param1ChildDrawable.mInsetT;
      this.mInsetR = param1ChildDrawable.mInsetR;
      this.mInsetB = param1ChildDrawable.mInsetB;
      this.mInsetS = param1ChildDrawable.mInsetS;
      this.mInsetE = param1ChildDrawable.mInsetE;
      this.mWidth = param1ChildDrawable.mWidth;
      this.mHeight = param1ChildDrawable.mHeight;
      this.mGravity = param1ChildDrawable.mGravity;
      this.mId = param1ChildDrawable.mId;
      int i = Drawable.resolveDensity(param1Resources, param1ChildDrawable.mDensity);
      this.mDensity = i;
      int j = param1ChildDrawable.mDensity;
      if (j != i)
        applyDensityScaling(j, i); 
    }
    
    private void applyDensityScaling(int param1Int1, int param1Int2) {
      this.mInsetL = Drawable.scaleFromDensity(this.mInsetL, param1Int1, param1Int2, false);
      this.mInsetT = Drawable.scaleFromDensity(this.mInsetT, param1Int1, param1Int2, false);
      this.mInsetR = Drawable.scaleFromDensity(this.mInsetR, param1Int1, param1Int2, false);
      this.mInsetB = Drawable.scaleFromDensity(this.mInsetB, param1Int1, param1Int2, false);
      int i = this.mInsetS;
      if (i != Integer.MIN_VALUE)
        this.mInsetS = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mInsetE;
      if (i != Integer.MIN_VALUE)
        this.mInsetE = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mWidth;
      if (i > 0)
        this.mWidth = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
      i = this.mHeight;
      if (i > 0)
        this.mHeight = Drawable.scaleFromDensity(i, param1Int1, param1Int2, true); 
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs == null) {
        Drawable drawable = this.mDrawable;
        return (drawable != null && drawable.canApplyTheme());
      } 
      return true;
    }
    
    public final void setDensity(int param1Int) {
      if (this.mDensity != param1Int) {
        int i = this.mDensity;
        this.mDensity = param1Int;
        applyDensityScaling(i, param1Int);
      } 
    }
  }
  
  static class LayerState extends Drawable.ConstantState {
    private boolean mAutoMirrored;
    
    int mChangingConfigurations;
    
    private boolean mCheckedOpacity;
    
    private boolean mCheckedStateful;
    
    LayerDrawable.ChildDrawable[] mChildren;
    
    int mChildrenChangingConfigurations;
    
    int mDensity;
    
    private boolean mIsStateful;
    
    int mNumChildren;
    
    private int mOpacity;
    
    int mOpacityOverride;
    
    int mPaddingBottom;
    
    int mPaddingEnd;
    
    int mPaddingLeft;
    
    private int mPaddingMode;
    
    int mPaddingRight;
    
    int mPaddingStart;
    
    int mPaddingTop;
    
    private int[] mThemeAttrs;
    
    LayerState(LayerState param1LayerState, LayerDrawable param1LayerDrawable, Resources param1Resources) {
      int i;
      this.mPaddingTop = -1;
      this.mPaddingBottom = -1;
      this.mPaddingLeft = -1;
      this.mPaddingRight = -1;
      this.mPaddingStart = -1;
      this.mPaddingEnd = -1;
      this.mOpacityOverride = 0;
      this.mAutoMirrored = false;
      this.mPaddingMode = 0;
      if (param1LayerState != null) {
        i = param1LayerState.mDensity;
      } else {
        i = 0;
      } 
      this.mDensity = Drawable.resolveDensity(param1Resources, i);
      if (param1LayerState != null) {
        LayerDrawable.ChildDrawable[] arrayOfChildDrawable = param1LayerState.mChildren;
        int j = param1LayerState.mNumChildren;
        this.mNumChildren = j;
        this.mChildren = new LayerDrawable.ChildDrawable[j];
        this.mChangingConfigurations = param1LayerState.mChangingConfigurations;
        this.mChildrenChangingConfigurations = param1LayerState.mChildrenChangingConfigurations;
        for (i = 0; i < j; i++) {
          LayerDrawable.ChildDrawable childDrawable = arrayOfChildDrawable[i];
          this.mChildren[i] = new LayerDrawable.ChildDrawable(childDrawable, param1LayerDrawable, param1Resources);
        } 
        this.mCheckedOpacity = param1LayerState.mCheckedOpacity;
        this.mOpacity = param1LayerState.mOpacity;
        this.mCheckedStateful = param1LayerState.mCheckedStateful;
        this.mIsStateful = param1LayerState.mIsStateful;
        this.mAutoMirrored = param1LayerState.mAutoMirrored;
        this.mPaddingMode = param1LayerState.mPaddingMode;
        this.mThemeAttrs = param1LayerState.mThemeAttrs;
        this.mPaddingTop = param1LayerState.mPaddingTop;
        this.mPaddingBottom = param1LayerState.mPaddingBottom;
        this.mPaddingLeft = param1LayerState.mPaddingLeft;
        this.mPaddingRight = param1LayerState.mPaddingRight;
        this.mPaddingStart = param1LayerState.mPaddingStart;
        this.mPaddingEnd = param1LayerState.mPaddingEnd;
        this.mOpacityOverride = param1LayerState.mOpacityOverride;
        i = param1LayerState.mDensity;
        j = this.mDensity;
        if (i != j)
          applyDensityScaling(i, j); 
      } else {
        this.mNumChildren = 0;
        this.mChildren = null;
      } 
    }
    
    private void applyDensityScaling(int param1Int1, int param1Int2) {
      int i = this.mPaddingLeft;
      if (i > 0)
        this.mPaddingLeft = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mPaddingTop;
      if (i > 0)
        this.mPaddingTop = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mPaddingRight;
      if (i > 0)
        this.mPaddingRight = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mPaddingBottom;
      if (i > 0)
        this.mPaddingBottom = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mPaddingStart;
      if (i > 0)
        this.mPaddingStart = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
      i = this.mPaddingEnd;
      if (i > 0)
        this.mPaddingEnd = Drawable.scaleFromDensity(i, param1Int1, param1Int2, false); 
    }
    
    public boolean canApplyTheme() {
      if (this.mThemeAttrs != null || super.canApplyTheme())
        return true; 
      LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      int i = this.mNumChildren;
      for (byte b = 0; b < i; b++) {
        if (arrayOfChildDrawable[b].canApplyTheme())
          return true; 
      } 
      return false;
    }
    
    public final boolean canConstantState() {
      LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      int i = this.mNumChildren;
      for (byte b = 0; b < i; b++) {
        Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
        if (drawable != null && drawable.getConstantState() == null)
          return false; 
      } 
      return true;
    }
    
    public int getChangingConfigurations() {
      return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
    }
    
    public final int getOpacity() {
      int m;
      if (this.mCheckedOpacity)
        return this.mOpacity; 
      int i = this.mNumChildren;
      LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      int j = -1;
      int k = 0;
      while (true) {
        m = j;
        if (k < i) {
          if ((arrayOfChildDrawable[k]).mDrawable != null) {
            m = k;
            break;
          } 
          k++;
          continue;
        } 
        break;
      } 
      if (m >= 0) {
        k = (arrayOfChildDrawable[m]).mDrawable.getOpacity();
      } else {
        k = -2;
      } 
      while (++m < i) {
        Drawable drawable = (arrayOfChildDrawable[m]).mDrawable;
        j = k;
        if (drawable != null)
          j = Drawable.resolveOpacity(k, drawable.getOpacity()); 
        m++;
        k = j;
      } 
      this.mOpacity = k;
      this.mCheckedOpacity = true;
      return k;
    }
    
    public final boolean hasFocusStateSpecified() {
      int i = this.mNumChildren;
      LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      for (byte b = 0; b < i; b++) {
        Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
        if (drawable != null && drawable.hasFocusStateSpecified())
          return true; 
      } 
      return false;
    }
    
    void invalidateCache() {
      this.mCheckedOpacity = false;
      this.mCheckedStateful = false;
    }
    
    public final boolean isStateful() {
      boolean bool2;
      if (this.mCheckedStateful)
        return this.mIsStateful; 
      int i = this.mNumChildren;
      LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
      boolean bool1 = false;
      byte b = 0;
      while (true) {
        bool2 = bool1;
        if (b < i) {
          Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
          if (drawable != null && drawable.isStateful()) {
            bool2 = true;
            break;
          } 
          b++;
          continue;
        } 
        break;
      } 
      this.mIsStateful = bool2;
      this.mCheckedStateful = true;
      return bool2;
    }
    
    public Drawable newDrawable() {
      return new LayerDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources param1Resources) {
      return new LayerDrawable(this, param1Resources);
    }
    
    protected void onDensityChanged(int param1Int1, int param1Int2) {
      applyDensityScaling(param1Int1, param1Int2);
    }
    
    public final void setDensity(int param1Int) {
      if (this.mDensity != param1Int) {
        int i = this.mDensity;
        this.mDensity = param1Int;
        onDensityChanged(i, param1Int);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/LayerDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */