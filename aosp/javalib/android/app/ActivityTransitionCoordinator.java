package android.app;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.view.GhostView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

abstract class ActivityTransitionCoordinator extends ResultReceiver {
  protected static final String KEY_ELEVATION = "shared_element:elevation";
  
  protected static final String KEY_IMAGE_MATRIX = "shared_element:imageMatrix";
  
  static final String KEY_REMOTE_RECEIVER = "android:remoteReceiver";
  
  protected static final String KEY_SCALE_TYPE = "shared_element:scaleType";
  
  protected static final String KEY_SCREEN_BOTTOM = "shared_element:screenBottom";
  
  protected static final String KEY_SCREEN_LEFT = "shared_element:screenLeft";
  
  protected static final String KEY_SCREEN_RIGHT = "shared_element:screenRight";
  
  protected static final String KEY_SCREEN_TOP = "shared_element:screenTop";
  
  protected static final String KEY_SNAPSHOT = "shared_element:bitmap";
  
  protected static final String KEY_TRANSLATION_Z = "shared_element:translationZ";
  
  public static final int MSG_ALLOW_RETURN_TRANSITION = 108;
  
  public static final int MSG_CANCEL = 106;
  
  public static final int MSG_EXIT_TRANSITION_COMPLETE = 104;
  
  public static final int MSG_HIDE_SHARED_ELEMENTS = 101;
  
  public static final int MSG_SET_REMOTE_RECEIVER = 100;
  
  public static final int MSG_SHARED_ELEMENT_DESTINATION = 107;
  
  public static final int MSG_START_EXIT_TRANSITION = 105;
  
  public static final int MSG_TAKE_SHARED_ELEMENTS = 103;
  
  protected static final ImageView.ScaleType[] SCALE_TYPE_VALUES = ImageView.ScaleType.values();
  
  private static final String TAG = "ActivityTransitionCoordinator";
  
  protected final ArrayList<String> mAllSharedElementNames;
  
  private boolean mBackgroundAnimatorComplete;
  
  private final FixedEpicenterCallback mEpicenterCallback = new FixedEpicenterCallback();
  
  private ArrayList<GhostViewListeners> mGhostViewListeners = new ArrayList<>();
  
  protected final boolean mIsReturning;
  
  private boolean mIsStartingTransition;
  
  protected SharedElementCallback mListener;
  
  private ArrayMap<View, Float> mOriginalAlphas = new ArrayMap();
  
  private Runnable mPendingTransition;
  
  protected ResultReceiver mResultReceiver;
  
  protected final ArrayList<String> mSharedElementNames = new ArrayList<>();
  
  private ArrayList<Matrix> mSharedElementParentMatrices;
  
  private boolean mSharedElementTransitionComplete;
  
  protected final ArrayList<View> mSharedElements = new ArrayList<>();
  
  private ArrayList<View> mStrippedTransitioningViews = new ArrayList<>();
  
  protected ArrayList<View> mTransitioningViews = new ArrayList<>();
  
  private boolean mViewsTransitionComplete;
  
  private Window mWindow;
  
  public ActivityTransitionCoordinator(Window paramWindow, ArrayList<String> paramArrayList, SharedElementCallback paramSharedElementCallback, boolean paramBoolean) {
    super(new Handler());
    this.mWindow = paramWindow;
    this.mListener = paramSharedElementCallback;
    this.mAllSharedElementNames = paramArrayList;
    this.mIsReturning = paramBoolean;
  }
  
  private static void findIncludedViews(Transition paramTransition, ArrayList<View> paramArrayList, ArraySet<View> paramArraySet) {
    if (paramTransition instanceof TransitionSet) {
      TransitionSet transitionSet = (TransitionSet)paramTransition;
      ArrayList<View> arrayList = new ArrayList();
      int i = paramArrayList.size();
      byte b;
      for (b = 0; b < i; b++) {
        View view = paramArrayList.get(b);
        if (paramTransition.isValidTarget(view))
          arrayList.add(view); 
      } 
      i = transitionSet.getTransitionCount();
      for (b = 0; b < i; b++)
        findIncludedViews(transitionSet.getTransitionAt(b), arrayList, paramArraySet); 
    } else {
      int i = paramArrayList.size();
      for (byte b = 0; b < i; b++) {
        View view = paramArrayList.get(b);
        if (paramTransition.isValidTarget(view))
          paramArraySet.add(view); 
      } 
    } 
  }
  
  private static SharedElementOriginalState getOldSharedElementState(View paramView, String paramString, Bundle paramBundle) {
    SharedElementOriginalState sharedElementOriginalState = new SharedElementOriginalState();
    sharedElementOriginalState.mLeft = paramView.getLeft();
    sharedElementOriginalState.mTop = paramView.getTop();
    sharedElementOriginalState.mRight = paramView.getRight();
    sharedElementOriginalState.mBottom = paramView.getBottom();
    sharedElementOriginalState.mMeasuredWidth = paramView.getMeasuredWidth();
    sharedElementOriginalState.mMeasuredHeight = paramView.getMeasuredHeight();
    sharedElementOriginalState.mTranslationZ = paramView.getTranslationZ();
    sharedElementOriginalState.mElevation = paramView.getElevation();
    if (!(paramView instanceof ImageView))
      return sharedElementOriginalState; 
    Bundle bundle = paramBundle.getBundle(paramString);
    if (bundle == null)
      return sharedElementOriginalState; 
    if (bundle.getInt("shared_element:scaleType", -1) < 0)
      return sharedElementOriginalState; 
    ImageView imageView = (ImageView)paramView;
    sharedElementOriginalState.mScaleType = imageView.getScaleType();
    if (sharedElementOriginalState.mScaleType == ImageView.ScaleType.MATRIX)
      sharedElementOriginalState.mMatrix = new Matrix(imageView.getImageMatrix()); 
    return sharedElementOriginalState;
  }
  
  private void getSharedElementParentMatrix(View paramView, Matrix paramMatrix) {
    int i;
    if (this.mSharedElementParentMatrices == null) {
      i = -1;
    } else {
      i = this.mSharedElements.indexOf(paramView);
    } 
    if (i < 0) {
      paramMatrix.reset();
      ViewParent viewParent = paramView.getParent();
      if (viewParent instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup)viewParent;
        viewGroup.transformMatrixToLocal(paramMatrix);
        paramMatrix.postTranslate(viewGroup.getScrollX(), viewGroup.getScrollY());
      } 
    } else {
      paramMatrix.set(this.mSharedElementParentMatrices.get(i));
    } 
  }
  
  public static boolean isInTransitionGroup(ViewParent paramViewParent, ViewGroup paramViewGroup) {
    if (paramViewParent == paramViewGroup || !(paramViewParent instanceof ViewGroup))
      return false; 
    ViewGroup viewGroup = (ViewGroup)paramViewParent;
    return viewGroup.isTransitionGroup() ? true : isInTransitionGroup(viewGroup.getParent(), paramViewGroup);
  }
  
  private static boolean isNested(View paramView, ArrayMap<String, View> paramArrayMap) {
    boolean bool2;
    ViewParent viewParent = paramView.getParent();
    boolean bool1 = false;
    while (true) {
      bool2 = bool1;
      if (viewParent instanceof View) {
        View view = (View)viewParent;
        if (paramArrayMap.containsValue(view)) {
          bool2 = true;
          break;
        } 
        ViewParent viewParent1 = view.getParent();
        continue;
      } 
      break;
    } 
    return bool2;
  }
  
  protected static Transition mergeTransitions(Transition paramTransition1, Transition paramTransition2) {
    if (paramTransition1 == null)
      return paramTransition2; 
    if (paramTransition2 == null)
      return paramTransition1; 
    TransitionSet transitionSet = new TransitionSet();
    transitionSet.addTransition(paramTransition1);
    transitionSet.addTransition(paramTransition2);
    return (Transition)transitionSet;
  }
  
  private static void noLayoutSuppressionForVisibilityTransitions(Transition paramTransition) {
    if (paramTransition instanceof Visibility) {
      ((Visibility)paramTransition).setSuppressLayout(false);
    } else if (paramTransition instanceof TransitionSet) {
      TransitionSet transitionSet = (TransitionSet)paramTransition;
      int i = transitionSet.getTransitionCount();
      for (byte b = 0; b < i; b++)
        noLayoutSuppressionForVisibilityTransitions(transitionSet.getTransitionAt(b)); 
    } 
  }
  
  protected static void removeExcludedViews(Transition paramTransition, ArrayList<View> paramArrayList) {
    ArraySet<View> arraySet = new ArraySet();
    findIncludedViews(paramTransition, paramArrayList, arraySet);
    paramArrayList.clear();
    paramArrayList.addAll((Collection<? extends View>)arraySet);
  }
  
  private static int scaleTypeToInt(ImageView.ScaleType paramScaleType) {
    byte b = 0;
    while (true) {
      ImageView.ScaleType[] arrayOfScaleType = SCALE_TYPE_VALUES;
      if (b < arrayOfScaleType.length) {
        if (paramScaleType == arrayOfScaleType[b])
          return b; 
        b++;
        continue;
      } 
      return -1;
    } 
  }
  
  private void setEpicenter(View paramView) {
    if (paramView == null) {
      this.mEpicenterCallback.setEpicenter(null);
    } else {
      Rect rect = new Rect();
      paramView.getBoundsOnScreen(rect);
      this.mEpicenterCallback.setEpicenter(rect);
    } 
  }
  
  protected static void setOriginalSharedElementState(ArrayList<View> paramArrayList, ArrayList<SharedElementOriginalState> paramArrayList1) {
    for (byte b = 0; b < paramArrayList1.size(); b++) {
      View view = paramArrayList.get(b);
      SharedElementOriginalState sharedElementOriginalState = paramArrayList1.get(b);
      if (view instanceof ImageView && sharedElementOriginalState.mScaleType != null) {
        ImageView imageView = (ImageView)view;
        imageView.setScaleType(sharedElementOriginalState.mScaleType);
        if (sharedElementOriginalState.mScaleType == ImageView.ScaleType.MATRIX)
          imageView.setImageMatrix(sharedElementOriginalState.mMatrix); 
      } 
      view.setElevation(sharedElementOriginalState.mElevation);
      view.setTranslationZ(sharedElementOriginalState.mTranslationZ);
      view.measure(View.MeasureSpec.makeMeasureSpec(sharedElementOriginalState.mMeasuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(sharedElementOriginalState.mMeasuredHeight, 1073741824));
      view.layout(sharedElementOriginalState.mLeft, sharedElementOriginalState.mTop, sharedElementOriginalState.mRight, sharedElementOriginalState.mBottom);
    } 
  }
  
  private void setSharedElementMatrices() {
    int i = this.mSharedElements.size();
    if (i > 0)
      this.mSharedElementParentMatrices = new ArrayList<>(i); 
    for (byte b = 0; b < i; b++) {
      ViewGroup viewGroup = (ViewGroup)((View)this.mSharedElements.get(b)).getParent();
      Matrix matrix = new Matrix();
      if (viewGroup != null) {
        viewGroup.transformMatrixToLocal(matrix);
        matrix.postTranslate(viewGroup.getScrollX(), viewGroup.getScrollY());
      } 
      this.mSharedElementParentMatrices.add(matrix);
    } 
  }
  
  private void setSharedElementState(View paramView, String paramString, Bundle paramBundle, Matrix paramMatrix, RectF paramRectF, int[] paramArrayOfint) {
    Bundle bundle = paramBundle.getBundle(paramString);
    if (bundle == null)
      return; 
    if (paramView instanceof ImageView) {
      int n = bundle.getInt("shared_element:scaleType", -1);
      if (n >= 0) {
        ImageView imageView = (ImageView)paramView;
        ImageView.ScaleType scaleType = SCALE_TYPE_VALUES[n];
        imageView.setScaleType(scaleType);
        if (scaleType == ImageView.ScaleType.MATRIX) {
          paramMatrix.setValues(bundle.getFloatArray("shared_element:imageMatrix"));
          imageView.setImageMatrix(paramMatrix);
        } 
      } 
    } 
    paramView.setTranslationZ(bundle.getFloat("shared_element:translationZ"));
    paramView.setElevation(bundle.getFloat("shared_element:elevation"));
    float f1 = bundle.getFloat("shared_element:screenLeft");
    float f2 = bundle.getFloat("shared_element:screenTop");
    float f3 = bundle.getFloat("shared_element:screenRight");
    float f4 = bundle.getFloat("shared_element:screenBottom");
    if (paramArrayOfint != null) {
      f1 -= paramArrayOfint[0];
      f2 -= paramArrayOfint[1];
      f3 -= paramArrayOfint[0];
      f4 -= paramArrayOfint[1];
    } else {
      getSharedElementParentMatrix(paramView, paramMatrix);
      paramRectF.set(f1, f2, f3, f4);
      paramMatrix.mapRect(paramRectF);
      f1 = paramRectF.left;
      f2 = paramRectF.top;
      paramView.getInverseMatrix().mapRect(paramRectF);
      f3 = paramRectF.width();
      f4 = paramRectF.height();
      paramView.setLeft(0);
      paramView.setTop(0);
      paramView.setRight(Math.round(f3));
      paramView.setBottom(Math.round(f4));
      paramRectF.set(0.0F, 0.0F, f3, f4);
      paramView.getMatrix().mapRect(paramRectF);
      f1 -= paramRectF.left;
      f2 -= paramRectF.top;
      f3 = f1 + f3;
      f4 = f2 + f4;
    } 
    int j = Math.round(f1);
    int i = Math.round(f2);
    int k = Math.round(f3) - j;
    int m = Math.round(f4) - i;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), View.MeasureSpec.makeMeasureSpec(m, 1073741824));
    paramView.layout(j, i, j + k, i + m);
  }
  
  private void setSharedElements(ArrayMap<String, View> paramArrayMap) {
    for (boolean bool = true; !paramArrayMap.isEmpty(); bool = false) {
      for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
        View view = (View)paramArrayMap.valueAt(i);
        String str = (String)paramArrayMap.keyAt(i);
        if (bool && (view == null || !view.isAttachedToWindow() || str == null)) {
          paramArrayMap.removeAt(i);
        } else if (!isNested(view, paramArrayMap)) {
          this.mSharedElementNames.add(str);
          this.mSharedElements.add(view);
          paramArrayMap.removeAt(i);
        } 
      } 
    } 
  }
  
  private void showView(View paramView, boolean paramBoolean) {
    Float float_ = (Float)this.mOriginalAlphas.remove(paramView);
    if (float_ != null)
      paramView.setAlpha(float_.floatValue()); 
    if (paramBoolean)
      paramView.setTransitionAlpha(1.0F); 
  }
  
  private void startInputWhenTransitionsComplete() {
    if (this.mViewsTransitionComplete && this.mSharedElementTransitionComplete) {
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null) {
        ViewRootImpl viewRootImpl = viewGroup.getViewRootImpl();
        if (viewRootImpl != null)
          viewRootImpl.setPausedForTransition(false); 
      } 
      onTransitionsComplete();
    } 
  }
  
  protected void backgroundAnimatorComplete() {
    this.mBackgroundAnimatorComplete = true;
  }
  
  protected boolean cancelPendingTransitions() {
    this.mPendingTransition = null;
    return this.mIsStartingTransition;
  }
  
  protected Bundle captureSharedElementState() {
    Bundle bundle = new Bundle();
    RectF rectF = new RectF();
    Matrix matrix = new Matrix();
    for (byte b = 0; b < this.mSharedElements.size(); b++)
      captureSharedElementState(this.mSharedElements.get(b), this.mSharedElementNames.get(b), bundle, matrix, rectF); 
    return bundle;
  }
  
  protected void captureSharedElementState(View paramView, String paramString, Bundle paramBundle, Matrix paramMatrix, RectF paramRectF) {
    Bundle bundle = new Bundle();
    paramMatrix.reset();
    paramView.transformMatrixToGlobal(paramMatrix);
    paramRectF.set(0.0F, 0.0F, paramView.getWidth(), paramView.getHeight());
    paramMatrix.mapRect(paramRectF);
    bundle.putFloat("shared_element:screenLeft", paramRectF.left);
    bundle.putFloat("shared_element:screenRight", paramRectF.right);
    bundle.putFloat("shared_element:screenTop", paramRectF.top);
    bundle.putFloat("shared_element:screenBottom", paramRectF.bottom);
    bundle.putFloat("shared_element:translationZ", paramView.getTranslationZ());
    bundle.putFloat("shared_element:elevation", paramView.getElevation());
    Parcelable parcelable = null;
    SharedElementCallback sharedElementCallback = this.mListener;
    if (sharedElementCallback != null)
      parcelable = sharedElementCallback.onCaptureSharedElementSnapshot(paramView, paramMatrix, paramRectF); 
    if (parcelable != null)
      bundle.putParcelable("shared_element:bitmap", parcelable); 
    if (paramView instanceof ImageView) {
      ImageView imageView = (ImageView)paramView;
      bundle.putInt("shared_element:scaleType", scaleTypeToInt(imageView.getScaleType()));
      if (imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
        float[] arrayOfFloat = new float[9];
        imageView.getImageMatrix().getValues(arrayOfFloat);
        bundle.putFloatArray("shared_element:imageMatrix", arrayOfFloat);
      } 
    } 
    paramBundle.putBundle(paramString, bundle);
  }
  
  protected void clearState() {
    this.mWindow = null;
    this.mSharedElements.clear();
    this.mTransitioningViews = null;
    this.mStrippedTransitioningViews = null;
    this.mOriginalAlphas.clear();
    this.mResultReceiver = null;
    this.mPendingTransition = null;
    this.mListener = null;
    this.mSharedElementParentMatrices = null;
  }
  
  protected Transition configureTransition(Transition paramTransition, boolean paramBoolean) {
    Transition transition = paramTransition;
    if (paramTransition != null) {
      paramTransition = paramTransition.clone();
      paramTransition.setEpicenterCallback(this.mEpicenterCallback);
      transition = setTargets(paramTransition, paramBoolean);
    } 
    noLayoutSuppressionForVisibilityTransitions(transition);
    return transition;
  }
  
  public ArrayList<View> copyMappedViews() {
    return new ArrayList<>(this.mSharedElements);
  }
  
  protected ArrayList<View> createSnapshots(Bundle paramBundle, Collection<String> paramCollection) {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface size : ()I
    //   6: istore_3
    //   7: new java/util/ArrayList
    //   10: dup
    //   11: iload_3
    //   12: invokespecial <init> : (I)V
    //   15: astore #4
    //   17: iload_3
    //   18: ifne -> 24
    //   21: aload #4
    //   23: areturn
    //   24: aload_0
    //   25: invokevirtual getWindow : ()Landroid/view/Window;
    //   28: invokevirtual getContext : ()Landroid/content/Context;
    //   31: astore #5
    //   33: iconst_2
    //   34: newarray int
    //   36: astore #6
    //   38: aload_0
    //   39: invokevirtual getDecor : ()Landroid/view/ViewGroup;
    //   42: astore #7
    //   44: aload #7
    //   46: ifnull -> 56
    //   49: aload #7
    //   51: aload #6
    //   53: invokevirtual getLocationOnScreen : ([I)V
    //   56: new android/graphics/Matrix
    //   59: dup
    //   60: invokespecial <init> : ()V
    //   63: astore #7
    //   65: aload_2
    //   66: invokeinterface iterator : ()Ljava/util/Iterator;
    //   71: astore #8
    //   73: aload #8
    //   75: invokeinterface hasNext : ()Z
    //   80: ifeq -> 177
    //   83: aload #8
    //   85: invokeinterface next : ()Ljava/lang/Object;
    //   90: checkcast java/lang/String
    //   93: astore #9
    //   95: aload_1
    //   96: aload #9
    //   98: invokevirtual getBundle : (Ljava/lang/String;)Landroid/os/Bundle;
    //   101: astore #10
    //   103: aconst_null
    //   104: astore_2
    //   105: aload #10
    //   107: ifnull -> 167
    //   110: aload #10
    //   112: ldc 'shared_element:bitmap'
    //   114: invokevirtual getParcelable : (Ljava/lang/String;)Landroid/os/Parcelable;
    //   117: astore_2
    //   118: aload_2
    //   119: ifnull -> 145
    //   122: aload_0
    //   123: getfield mListener : Landroid/app/SharedElementCallback;
    //   126: astore #10
    //   128: aload #10
    //   130: ifnull -> 145
    //   133: aload #10
    //   135: aload #5
    //   137: aload_2
    //   138: invokevirtual onCreateSnapshotView : (Landroid/content/Context;Landroid/os/Parcelable;)Landroid/view/View;
    //   141: astore_2
    //   142: goto -> 147
    //   145: aconst_null
    //   146: astore_2
    //   147: aload_2
    //   148: ifnull -> 167
    //   151: aload_0
    //   152: aload_2
    //   153: aload #9
    //   155: aload_1
    //   156: aload #7
    //   158: aconst_null
    //   159: aload #6
    //   161: invokespecial setSharedElementState : (Landroid/view/View;Ljava/lang/String;Landroid/os/Bundle;Landroid/graphics/Matrix;Landroid/graphics/RectF;[I)V
    //   164: goto -> 167
    //   167: aload #4
    //   169: aload_2
    //   170: invokevirtual add : (Ljava/lang/Object;)Z
    //   173: pop
    //   174: goto -> 73
    //   177: aload #4
    //   179: areturn
  }
  
  public ArrayList<String> getAcceptedNames() {
    return this.mSharedElementNames;
  }
  
  public ViewGroup getDecor() {
    ViewGroup viewGroup;
    Window window = this.mWindow;
    if (window == null) {
      window = null;
    } else {
      viewGroup = (ViewGroup)window.getDecorView();
    } 
    return viewGroup;
  }
  
  protected long getFadeDuration() {
    return getWindow().getTransitionBackgroundFadeDuration();
  }
  
  public ArrayList<String> getMappedNames() {
    ArrayList<String> arrayList = new ArrayList(this.mSharedElements.size());
    for (byte b = 0; b < this.mSharedElements.size(); b++)
      arrayList.add(((View)this.mSharedElements.get(b)).getTransitionName()); 
    return arrayList;
  }
  
  protected abstract Transition getViewsTransition();
  
  protected Window getWindow() {
    return this.mWindow;
  }
  
  protected void hideViews(ArrayList<View> paramArrayList) {
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++) {
      View view = paramArrayList.get(b);
      if (!this.mOriginalAlphas.containsKey(view))
        this.mOriginalAlphas.put(view, Float.valueOf(view.getAlpha())); 
      view.setAlpha(0.0F);
    } 
  }
  
  public boolean isTransitionRunning() {
    return (!this.mViewsTransitionComplete || !this.mSharedElementTransitionComplete || !this.mBackgroundAnimatorComplete);
  }
  
  protected boolean isViewsTransitionComplete() {
    return this.mViewsTransitionComplete;
  }
  
  protected ArrayMap<String, View> mapSharedElements(ArrayList<String> paramArrayList, ArrayList<View> paramArrayList1) {
    ArrayMap<String, View> arrayMap = new ArrayMap();
    if (paramArrayList != null) {
      for (byte b = 0; b < paramArrayList.size(); b++)
        arrayMap.put(paramArrayList.get(b), paramArrayList1.get(b)); 
    } else {
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null)
        viewGroup.findNamedViews((Map)arrayMap); 
    } 
    return arrayMap;
  }
  
  protected boolean moveSharedElementWithParent() {
    return true;
  }
  
  protected void moveSharedElementsFromOverlay() {
    int i = this.mGhostViewListeners.size();
    byte b;
    for (b = 0; b < i; b++)
      ((GhostViewListeners)this.mGhostViewListeners.get(b)).removeListener(); 
    this.mGhostViewListeners.clear();
    Window window = this.mWindow;
    if (window == null || !window.getSharedElementsUseOverlay())
      return; 
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null) {
      viewGroup.getOverlay();
      i = this.mSharedElements.size();
      for (b = 0; b < i; b++)
        GhostView.removeGhost(this.mSharedElements.get(b)); 
    } 
  }
  
  protected void moveSharedElementsToOverlay() {
    Window window = this.mWindow;
    if (window == null || !window.getSharedElementsUseOverlay())
      return; 
    setSharedElementMatrices();
    int i = this.mSharedElements.size();
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null) {
      boolean bool = moveSharedElementWithParent();
      Matrix matrix = new Matrix();
      for (byte b = 0; b < i; b++) {
        View view = this.mSharedElements.get(b);
        if (view.isAttachedToWindow()) {
          matrix.reset();
          ((Matrix)this.mSharedElementParentMatrices.get(b)).invert(matrix);
          GhostView.addGhost(view, viewGroup, matrix);
          ViewGroup viewGroup1 = (ViewGroup)view.getParent();
          if (bool && !isInTransitionGroup((ViewParent)viewGroup1, viewGroup)) {
            GhostViewListeners ghostViewListeners = new GhostViewListeners(view, (View)viewGroup1, viewGroup);
            viewGroup1.getViewTreeObserver().addOnPreDrawListener(ghostViewListeners);
            viewGroup1.addOnAttachStateChangeListener(ghostViewListeners);
            this.mGhostViewListeners.add(ghostViewListeners);
          } 
        } 
      } 
    } 
  }
  
  protected void notifySharedElementEnd(ArrayList<View> paramArrayList) {
    SharedElementCallback sharedElementCallback = this.mListener;
    if (sharedElementCallback != null)
      sharedElementCallback.onSharedElementEnd(this.mSharedElementNames, this.mSharedElements, paramArrayList); 
  }
  
  protected void onTransitionsComplete() {}
  
  protected void pauseInput() {
    ViewRootImpl viewRootImpl;
    ViewGroup viewGroup = getDecor();
    if (viewGroup == null) {
      viewGroup = null;
    } else {
      viewRootImpl = viewGroup.getViewRootImpl();
    } 
    if (viewRootImpl != null)
      viewRootImpl.setPausedForTransition(true); 
  }
  
  protected void scheduleGhostVisibilityChange(int paramInt) {
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null)
      OneShotPreDrawListener.add((View)viewGroup, new _$$Lambda$ActivityTransitionCoordinator$_HMo0E_15AzCK9fwQ8WHzdz8ZIw(this, paramInt)); 
  }
  
  protected void scheduleSetSharedElementEnd(ArrayList<View> paramArrayList) {
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null)
      OneShotPreDrawListener.add((View)viewGroup, new _$$Lambda$ActivityTransitionCoordinator$fkaPvc8GCghP2GMwEgS_J5m_T_4(this, paramArrayList)); 
  }
  
  protected void setEpicenter() {
    View view1 = null;
    View view2 = view1;
    if (!this.mAllSharedElementNames.isEmpty()) {
      view2 = view1;
      if (!this.mSharedElementNames.isEmpty()) {
        int i = this.mSharedElementNames.indexOf(this.mAllSharedElementNames.get(0));
        view2 = view1;
        if (i >= 0)
          view2 = this.mSharedElements.get(i); 
      } 
    } 
    setEpicenter(view2);
  }
  
  protected void setGhostVisibility(int paramInt) {
    int i = this.mSharedElements.size();
    for (byte b = 0; b < i; b++) {
      GhostView ghostView = GhostView.getGhost(this.mSharedElements.get(b));
      if (ghostView != null)
        ghostView.setVisibility(paramInt); 
    } 
  }
  
  protected void setResultReceiver(ResultReceiver paramResultReceiver) {
    this.mResultReceiver = paramResultReceiver;
  }
  
  protected ArrayList<SharedElementOriginalState> setSharedElementState(Bundle paramBundle, ArrayList<View> paramArrayList) {
    ArrayList<SharedElementOriginalState> arrayList = new ArrayList();
    if (paramBundle != null) {
      Matrix matrix = new Matrix();
      RectF rectF = new RectF();
      int i = this.mSharedElements.size();
      for (byte b = 0; b < i; b++) {
        View view = this.mSharedElements.get(b);
        String str = this.mSharedElementNames.get(b);
        arrayList.add(getOldSharedElementState(view, str, paramBundle));
        setSharedElementState(view, str, paramBundle, matrix, rectF, null);
      } 
    } 
    SharedElementCallback sharedElementCallback = this.mListener;
    if (sharedElementCallback != null)
      sharedElementCallback.onSharedElementStart(this.mSharedElementNames, this.mSharedElements, paramArrayList); 
    return arrayList;
  }
  
  protected Transition setTargets(Transition paramTransition, boolean paramBoolean) {
    if (paramTransition != null) {
      if (paramBoolean) {
        ArrayList<View> arrayList1 = this.mTransitioningViews;
        if (arrayList1 == null || arrayList1.isEmpty())
          return null; 
      } 
      TransitionSet transitionSet2 = new TransitionSet();
      ArrayList<View> arrayList = this.mTransitioningViews;
      if (arrayList != null)
        for (int i = arrayList.size() - 1; i >= 0; i--) {
          View view = this.mTransitioningViews.get(i);
          if (paramBoolean) {
            transitionSet2.addTarget(view);
          } else {
            transitionSet2.excludeTarget(view, true);
          } 
        }  
      arrayList = this.mStrippedTransitioningViews;
      if (arrayList != null)
        for (int i = arrayList.size() - 1; i >= 0; i--)
          transitionSet2.excludeTarget(this.mStrippedTransitioningViews.get(i), true);  
      transitionSet2.addTransition(paramTransition);
      TransitionSet transitionSet1 = transitionSet2;
      if (!paramBoolean) {
        arrayList = this.mTransitioningViews;
        transitionSet1 = transitionSet2;
        if (arrayList != null) {
          transitionSet1 = transitionSet2;
          if (!arrayList.isEmpty())
            transitionSet1 = (new TransitionSet()).addTransition((Transition)transitionSet2); 
        } 
      } 
      return (Transition)transitionSet1;
    } 
    return null;
  }
  
  protected void setTransitioningViewsVisiblity(int paramInt, boolean paramBoolean) {
    int i;
    ArrayList<View> arrayList = this.mTransitioningViews;
    if (arrayList == null) {
      i = 0;
    } else {
      i = arrayList.size();
    } 
    for (byte b = 0; b < i; b++) {
      View view = this.mTransitioningViews.get(b);
      if (paramBoolean) {
        view.setVisibility(paramInt);
      } else {
        view.setTransitionVisibility(paramInt);
      } 
    } 
  }
  
  protected void sharedElementTransitionComplete() {
    this.mSharedElementTransitionComplete = true;
    startInputWhenTransitionsComplete();
  }
  
  protected void showViews(ArrayList<View> paramArrayList, boolean paramBoolean) {
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++)
      showView(paramArrayList.get(b), paramBoolean); 
  }
  
  protected void startTransition(Runnable paramRunnable) {
    if (this.mIsStartingTransition) {
      this.mPendingTransition = paramRunnable;
    } else {
      this.mIsStartingTransition = true;
      paramRunnable.run();
    } 
  }
  
  protected void stripOffscreenViews() {
    if (this.mTransitioningViews == null)
      return; 
    Rect rect = new Rect();
    for (int i = this.mTransitioningViews.size() - 1; i >= 0; i--) {
      View view = this.mTransitioningViews.get(i);
      if (!view.getGlobalVisibleRect(rect)) {
        this.mTransitioningViews.remove(i);
        this.mStrippedTransitioningViews.add(view);
      } 
    } 
  }
  
  protected void transitionStarted() {
    this.mIsStartingTransition = false;
  }
  
  protected void viewsReady(ArrayMap<String, View> paramArrayMap) {
    paramArrayMap.retainAll(this.mAllSharedElementNames);
    SharedElementCallback sharedElementCallback = this.mListener;
    if (sharedElementCallback != null)
      sharedElementCallback.onMapSharedElements(this.mAllSharedElementNames, (Map<String, View>)paramArrayMap); 
    setSharedElements(paramArrayMap);
    if (getViewsTransition() != null && this.mTransitioningViews != null) {
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null)
        viewGroup.captureTransitioningViews(this.mTransitioningViews); 
      this.mTransitioningViews.removeAll(this.mSharedElements);
    } 
    setEpicenter();
  }
  
  protected void viewsTransitionComplete() {
    this.mViewsTransitionComplete = true;
    startInputWhenTransitionsComplete();
  }
  
  protected class ContinueTransitionListener extends TransitionListenerAdapter {
    public void onTransitionEnd(Transition param1Transition) {
      param1Transition.removeListener((Transition.TransitionListener)this);
    }
    
    public void onTransitionStart(Transition param1Transition) {
      ActivityTransitionCoordinator.access$102(ActivityTransitionCoordinator.this, false);
      Runnable runnable = ActivityTransitionCoordinator.this.mPendingTransition;
      ActivityTransitionCoordinator.access$202(ActivityTransitionCoordinator.this, null);
      if (runnable != null)
        ActivityTransitionCoordinator.this.startTransition(runnable); 
    }
  }
  
  private static class FixedEpicenterCallback extends Transition.EpicenterCallback {
    private Rect mEpicenter;
    
    private FixedEpicenterCallback() {}
    
    public Rect onGetEpicenter(Transition param1Transition) {
      return this.mEpicenter;
    }
    
    public void setEpicenter(Rect param1Rect) {
      this.mEpicenter = param1Rect;
    }
  }
  
  private static class GhostViewListeners implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    private ViewGroup mDecor;
    
    private Matrix mMatrix = new Matrix();
    
    private View mParent;
    
    private View mView;
    
    private ViewTreeObserver mViewTreeObserver;
    
    public GhostViewListeners(View param1View1, View param1View2, ViewGroup param1ViewGroup) {
      this.mView = param1View1;
      this.mParent = param1View2;
      this.mDecor = param1ViewGroup;
      this.mViewTreeObserver = param1View2.getViewTreeObserver();
    }
    
    public View getView() {
      return this.mView;
    }
    
    public boolean onPreDraw() {
      GhostView ghostView = GhostView.getGhost(this.mView);
      if (ghostView == null || !this.mView.isAttachedToWindow()) {
        removeListener();
        return true;
      } 
      GhostView.calculateMatrix(this.mView, this.mDecor, this.mMatrix);
      ghostView.setMatrix(this.mMatrix);
      return true;
    }
    
    public void onViewAttachedToWindow(View param1View) {
      this.mViewTreeObserver = param1View.getViewTreeObserver();
    }
    
    public void onViewDetachedFromWindow(View param1View) {
      removeListener();
    }
    
    public void removeListener() {
      if (this.mViewTreeObserver.isAlive()) {
        this.mViewTreeObserver.removeOnPreDrawListener(this);
      } else {
        this.mParent.getViewTreeObserver().removeOnPreDrawListener(this);
      } 
      this.mParent.removeOnAttachStateChangeListener(this);
    }
  }
  
  static class SharedElementOriginalState {
    int mBottom;
    
    float mElevation;
    
    int mLeft;
    
    Matrix mMatrix;
    
    int mMeasuredHeight;
    
    int mMeasuredWidth;
    
    int mRight;
    
    ImageView.ScaleType mScaleType;
    
    int mTop;
    
    float mTranslationZ;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTransitionCoordinator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */