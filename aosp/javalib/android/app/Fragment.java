package android.app;

import android.animation.Animator;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.AndroidRuntimeException;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.util.SparseArray;
import android.util.SuperNotCalledException;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

@Deprecated
public class Fragment implements ComponentCallbacks2, View.OnCreateContextMenuListener {
  static final int ACTIVITY_CREATED = 2;
  
  static final int CREATED = 1;
  
  static final int INITIALIZING = 0;
  
  static final int INVALID_STATE = -1;
  
  static final int RESUMED = 5;
  
  static final int STARTED = 4;
  
  static final int STOPPED = 3;
  
  private static final Transition USE_DEFAULT_TRANSITION;
  
  private static final ArrayMap<String, Class<?>> sClassMap = new ArrayMap();
  
  boolean mAdded;
  
  AnimationInfo mAnimationInfo;
  
  Bundle mArguments;
  
  int mBackStackNesting;
  
  boolean mCalled;
  
  boolean mCheckedForLoaderManager;
  
  FragmentManagerImpl mChildFragmentManager;
  
  FragmentManagerNonConfig mChildNonConfig;
  
  ViewGroup mContainer;
  
  int mContainerId;
  
  boolean mDeferStart;
  
  boolean mDetached;
  
  int mFragmentId;
  
  FragmentManagerImpl mFragmentManager;
  
  boolean mFromLayout;
  
  boolean mHasMenu;
  
  boolean mHidden;
  
  boolean mHiddenChanged;
  
  FragmentHostCallback mHost;
  
  boolean mInLayout;
  
  int mIndex = -1;
  
  boolean mIsCreated;
  
  boolean mIsNewlyAdded;
  
  LayoutInflater mLayoutInflater;
  
  LoaderManagerImpl mLoaderManager;
  
  boolean mLoadersStarted;
  
  boolean mMenuVisible = true;
  
  Fragment mParentFragment;
  
  boolean mPerformedCreateView;
  
  boolean mRemoving;
  
  boolean mRestored;
  
  boolean mRetainInstance;
  
  boolean mRetaining;
  
  Bundle mSavedFragmentState;
  
  SparseArray<Parcelable> mSavedViewState;
  
  int mState = 0;
  
  String mTag;
  
  Fragment mTarget;
  
  int mTargetIndex = -1;
  
  int mTargetRequestCode;
  
  boolean mUserVisibleHint = true;
  
  View mView;
  
  String mWho;
  
  static {
    USE_DEFAULT_TRANSITION = (Transition)new TransitionSet();
  }
  
  private void callStartTransitionListener() {
    OnStartEnterTransitionListener onStartEnterTransitionListener;
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null) {
      animationInfo = null;
    } else {
      animationInfo.mEnterTransitionPostponed = false;
      onStartEnterTransitionListener = this.mAnimationInfo.mStartEnterTransitionListener;
      this.mAnimationInfo.mStartEnterTransitionListener = null;
    } 
    if (onStartEnterTransitionListener != null)
      onStartEnterTransitionListener.onStartEnterTransition(); 
  }
  
  private AnimationInfo ensureAnimationInfo() {
    if (this.mAnimationInfo == null)
      this.mAnimationInfo = new AnimationInfo(); 
    return this.mAnimationInfo;
  }
  
  public static Fragment instantiate(Context paramContext, String paramString) {
    return instantiate(paramContext, paramString, null);
  }
  
  public static Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle) {
    try {
      String str;
      ClassCastException classCastException;
      Class<?> clazz1 = (Class)sClassMap.get(paramString);
      Class<?> clazz2 = clazz1;
      if (clazz1 == null) {
        clazz2 = paramContext.getClassLoader().loadClass(paramString);
        if (Fragment.class.isAssignableFrom(clazz2)) {
          sClassMap.put(paramString, clazz2);
        } else {
          InstantiationException instantiationException = new InstantiationException();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Trying to instantiate a class ");
          stringBuilder.append(paramString);
          stringBuilder.append(" that is not a Fragment");
          str = stringBuilder.toString();
          classCastException = new ClassCastException();
          this();
          this(str, classCastException);
          throw instantiationException;
        } 
      } 
      Fragment fragment = classCastException.getConstructor(new Class[0]).newInstance(new Object[0]);
      if (str != null) {
        str.setClassLoader(fragment.getClass().getClassLoader());
        fragment.setArguments((Bundle)str);
      } 
      return fragment;
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": make sure class name exists, is public, and has an empty constructor that is public");
      throw new InstantiationException(stringBuilder.toString(), classNotFoundException);
    } catch (InstantiationException instantiationException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": make sure class name exists, is public, and has an empty constructor that is public");
      throw new InstantiationException(stringBuilder.toString(), instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": make sure class name exists, is public, and has an empty constructor that is public");
      throw new InstantiationException(stringBuilder.toString(), illegalAccessException);
    } catch (NoSuchMethodException noSuchMethodException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": could not find Fragment constructor");
      throw new InstantiationException(stringBuilder.toString(), noSuchMethodException);
    } catch (InvocationTargetException invocationTargetException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to instantiate fragment ");
      stringBuilder.append(paramString);
      stringBuilder.append(": calling Fragment constructor caused an exception");
      throw new InstantiationException(stringBuilder.toString(), invocationTargetException);
    } 
  }
  
  private static Transition loadTransition(Context paramContext, TypedArray paramTypedArray, Transition paramTransition1, Transition paramTransition2, int paramInt) {
    if (paramTransition1 != paramTransition2)
      return paramTransition1; 
    paramInt = paramTypedArray.getResourceId(paramInt, 0);
    Transition transition = paramTransition2;
    if (paramInt != 0) {
      transition = paramTransition2;
      if (paramInt != 17760256) {
        Transition transition1 = TransitionInflater.from(paramContext).inflateTransition(paramInt);
        transition = transition1;
        if (transition1 instanceof TransitionSet) {
          transition = transition1;
          if (((TransitionSet)transition1).getTransitionCount() == 0)
            transition = null; 
        } 
      } 
    } 
    return transition;
  }
  
  private boolean shouldChangeTransition(Transition paramTransition1, Transition paramTransition2) {
    boolean bool = true;
    if (paramTransition1 == paramTransition2) {
      if (this.mAnimationInfo == null)
        bool = false; 
      return bool;
    } 
    return true;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(this.mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(this.mContainerId));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(this.mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(this.mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(this.mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(this.mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(this.mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(this.mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(this.mRemoving);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(this.mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(this.mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(this.mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(this.mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(this.mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(this.mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(this.mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(this.mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(this.mUserVisibleHint);
    if (this.mFragmentManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(this.mFragmentManager);
    } 
    if (this.mHost != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHost=");
      paramPrintWriter.println(this.mHost);
    } 
    if (this.mParentFragment != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(this.mParentFragment);
    } 
    if (this.mArguments != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(this.mArguments);
    } 
    if (this.mSavedFragmentState != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(this.mSavedFragmentState);
    } 
    if (this.mSavedViewState != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(this.mSavedViewState);
    } 
    if (this.mTarget != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(this.mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(this.mTargetRequestCode);
    } 
    if (getNextAnim() != 0) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(getNextAnim());
    } 
    if (this.mContainer != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(this.mContainer);
    } 
    if (this.mView != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(this.mView);
    } 
    if (getAnimatingAway() != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(getAnimatingAway());
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(getStateAfterAnimating());
    } 
    if (this.mLoaderManager != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Loader Manager:");
      LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      loaderManagerImpl.dump(stringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
    if (this.mChildFragmentManager != null) {
      paramPrintWriter.print(paramString);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Child ");
      stringBuilder.append(this.mChildFragmentManager);
      stringBuilder.append(":");
      paramPrintWriter.println(stringBuilder.toString());
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  ");
      fragmentManagerImpl.dump(stringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  public final boolean equals(Object paramObject) {
    return super.equals(paramObject);
  }
  
  Fragment findFragmentByWho(String paramString) {
    if (paramString.equals(this.mWho))
      return this; 
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    return (fragmentManagerImpl != null) ? fragmentManagerImpl.findFragmentByWho(paramString) : null;
  }
  
  public final Activity getActivity() {
    Activity activity;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      activity = fragmentHostCallback.getActivity();
    } 
    return activity;
  }
  
  public boolean getAllowEnterTransitionOverlap() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null || animationInfo.mAllowEnterTransitionOverlap == null) ? true : this.mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
  }
  
  public boolean getAllowReturnTransitionOverlap() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null || animationInfo.mAllowReturnTransitionOverlap == null) ? true : this.mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
  }
  
  Animator getAnimatingAway() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mAnimatingAway;
  }
  
  public final Bundle getArguments() {
    return this.mArguments;
  }
  
  public final FragmentManager getChildFragmentManager() {
    if (this.mChildFragmentManager == null) {
      instantiateChildFragmentManager();
      int i = this.mState;
      if (i >= 5) {
        this.mChildFragmentManager.dispatchResume();
      } else if (i >= 4) {
        this.mChildFragmentManager.dispatchStart();
      } else if (i >= 2) {
        this.mChildFragmentManager.dispatchActivityCreated();
      } else if (i >= 1) {
        this.mChildFragmentManager.dispatchCreate();
      } 
    } 
    return this.mChildFragmentManager;
  }
  
  public Context getContext() {
    Context context;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      context = fragmentHostCallback.getContext();
    } 
    return context;
  }
  
  public Transition getEnterTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mEnterTransition;
  }
  
  SharedElementCallback getEnterTransitionCallback() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? SharedElementCallback.NULL_CALLBACK : animationInfo.mEnterTransitionCallback;
  }
  
  public Transition getExitTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mExitTransition;
  }
  
  SharedElementCallback getExitTransitionCallback() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? SharedElementCallback.NULL_CALLBACK : animationInfo.mExitTransitionCallback;
  }
  
  public final FragmentManager getFragmentManager() {
    return this.mFragmentManager;
  }
  
  public final Object getHost() {
    FragmentHostCallback<FragmentHostCallback> fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      fragmentHostCallback = fragmentHostCallback.onGetHost();
    } 
    return fragmentHostCallback;
  }
  
  public final int getId() {
    return this.mFragmentId;
  }
  
  public final LayoutInflater getLayoutInflater() {
    LayoutInflater layoutInflater = this.mLayoutInflater;
    return (layoutInflater == null) ? performGetLayoutInflater(null) : layoutInflater;
  }
  
  @Deprecated
  public LoaderManager getLoaderManager() {
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl != null)
      return loaderManagerImpl; 
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      this.mCheckedForLoaderManager = true;
      LoaderManagerImpl loaderManagerImpl1 = fragmentHostCallback.getLoaderManager(this.mWho, this.mLoadersStarted, true);
      this.mLoaderManager = loaderManagerImpl1;
      return loaderManagerImpl1;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  int getNextAnim() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mNextAnim;
  }
  
  int getNextTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mNextTransition;
  }
  
  int getNextTransitionStyle() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mNextTransitionStyle;
  }
  
  public final Fragment getParentFragment() {
    return this.mParentFragment;
  }
  
  public Transition getReenterTransition() {
    Transition transition;
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null)
      return null; 
    if (animationInfo.mReenterTransition == USE_DEFAULT_TRANSITION) {
      transition = getExitTransition();
    } else {
      transition = this.mAnimationInfo.mReenterTransition;
    } 
    return transition;
  }
  
  public final Resources getResources() {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null)
      return fragmentHostCallback.getContext().getResources(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public final boolean getRetainInstance() {
    return this.mRetainInstance;
  }
  
  public Transition getReturnTransition() {
    Transition transition;
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null)
      return null; 
    if (animationInfo.mReturnTransition == USE_DEFAULT_TRANSITION) {
      transition = getEnterTransition();
    } else {
      transition = this.mAnimationInfo.mReturnTransition;
    } 
    return transition;
  }
  
  public Transition getSharedElementEnterTransition() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? null : animationInfo.mSharedElementEnterTransition;
  }
  
  public Transition getSharedElementReturnTransition() {
    Transition transition;
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null)
      return null; 
    if (animationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION) {
      transition = getSharedElementEnterTransition();
    } else {
      transition = this.mAnimationInfo.mSharedElementReturnTransition;
    } 
    return transition;
  }
  
  int getStateAfterAnimating() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? 0 : animationInfo.mStateAfterAnimating;
  }
  
  public final String getString(int paramInt) {
    return getResources().getString(paramInt);
  }
  
  public final String getString(int paramInt, Object... paramVarArgs) {
    return getResources().getString(paramInt, paramVarArgs);
  }
  
  public final String getTag() {
    return this.mTag;
  }
  
  public final Fragment getTargetFragment() {
    return this.mTarget;
  }
  
  public final int getTargetRequestCode() {
    return this.mTargetRequestCode;
  }
  
  public final CharSequence getText(int paramInt) {
    return getResources().getText(paramInt);
  }
  
  public boolean getUserVisibleHint() {
    return this.mUserVisibleHint;
  }
  
  public View getView() {
    return this.mView;
  }
  
  public final int hashCode() {
    return super.hashCode();
  }
  
  void initState() {
    this.mIndex = -1;
    this.mWho = null;
    this.mAdded = false;
    this.mRemoving = false;
    this.mFromLayout = false;
    this.mInLayout = false;
    this.mRestored = false;
    this.mBackStackNesting = 0;
    this.mFragmentManager = null;
    this.mChildFragmentManager = null;
    this.mHost = null;
    this.mFragmentId = 0;
    this.mContainerId = 0;
    this.mTag = null;
    this.mHidden = false;
    this.mDetached = false;
    this.mRetaining = false;
    this.mLoaderManager = null;
    this.mLoadersStarted = false;
    this.mCheckedForLoaderManager = false;
  }
  
  void instantiateChildFragmentManager() {
    FragmentManagerImpl fragmentManagerImpl = new FragmentManagerImpl();
    this.mChildFragmentManager = fragmentManagerImpl;
    fragmentManagerImpl.attachController(this.mHost, new FragmentContainer() {
          public <T extends View> T onFindViewById(int param1Int) {
            if (Fragment.this.mView != null)
              return (T)Fragment.this.mView.findViewById(param1Int); 
            throw new IllegalStateException("Fragment does not have a view");
          }
          
          public boolean onHasView() {
            boolean bool;
            if (Fragment.this.mView != null) {
              bool = true;
            } else {
              bool = false;
            } 
            return bool;
          }
        },  this);
  }
  
  public final boolean isAdded() {
    boolean bool;
    if (this.mHost != null && this.mAdded) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isDetached() {
    return this.mDetached;
  }
  
  public final boolean isHidden() {
    return this.mHidden;
  }
  
  boolean isHideReplaced() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? false : animationInfo.mIsHideReplaced;
  }
  
  final boolean isInBackStack() {
    boolean bool;
    if (this.mBackStackNesting > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isInLayout() {
    return this.mInLayout;
  }
  
  boolean isPostponed() {
    AnimationInfo animationInfo = this.mAnimationInfo;
    return (animationInfo == null) ? false : animationInfo.mEnterTransitionPostponed;
  }
  
  public final boolean isRemoving() {
    return this.mRemoving;
  }
  
  public final boolean isResumed() {
    boolean bool;
    if (this.mState >= 5) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isStateSaved() {
    FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
    return (fragmentManagerImpl == null) ? false : fragmentManagerImpl.isStateSaved();
  }
  
  public final boolean isVisible() {
    if (isAdded() && !isHidden()) {
      View view = this.mView;
      if (view != null && view.getWindowToken() != null && this.mView.getVisibility() == 0)
        return true; 
    } 
    return false;
  }
  
  void noteStateNotSaved() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    this.mCalled = true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @Deprecated
  public void onAttach(Activity paramActivity) {
    this.mCalled = true;
  }
  
  public void onAttach(Context paramContext) {
    Activity activity;
    this.mCalled = true;
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback == null) {
      fragmentHostCallback = null;
    } else {
      activity = fragmentHostCallback.getActivity();
    } 
    if (activity != null) {
      this.mCalled = false;
      onAttach(activity);
    } 
  }
  
  public void onAttachFragment(Fragment paramFragment) {}
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    this.mCalled = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onCreate(Bundle paramBundle) {
    byte b;
    this.mCalled = true;
    Context context = getContext();
    if (context != null) {
      b = (context.getApplicationInfo()).targetSdkVersion;
    } else {
      b = 0;
    } 
    if (b >= 24) {
      restoreChildFragmentState(paramBundle, true);
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null && !fragmentManagerImpl.isStateAtLeast(1))
        this.mChildFragmentManager.dispatchCreate(); 
    } 
  }
  
  public Animator onCreateAnimator(int paramInt1, boolean paramBoolean, int paramInt2) {
    return null;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return null;
  }
  
  public void onDestroy() {
    this.mCalled = true;
    if (!this.mCheckedForLoaderManager) {
      this.mCheckedForLoaderManager = true;
      this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, this.mLoadersStarted, false);
    } 
    LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
    if (loaderManagerImpl != null)
      loaderManagerImpl.doDestroy(); 
  }
  
  public void onDestroyOptionsMenu() {}
  
  public void onDestroyView() {
    this.mCalled = true;
  }
  
  public void onDetach() {
    this.mCalled = true;
  }
  
  public LayoutInflater onGetLayoutInflater(Bundle paramBundle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      LayoutInflater layoutInflater = fragmentHostCallback.onGetLayoutInflater();
      if (this.mHost.onUseFragmentManagerInflaterFactory()) {
        getChildFragmentManager();
        layoutInflater.setPrivateFactory(this.mChildFragmentManager.getLayoutInflaterFactory());
      } 
      return layoutInflater;
    } 
    throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
  }
  
  public void onHiddenChanged(boolean paramBoolean) {}
  
  @Deprecated
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle) {
    this.mCalled = true;
  }
  
  public void onInflate(Context paramContext, AttributeSet paramAttributeSet, Bundle paramBundle) {
    Activity activity;
    boolean bool1;
    boolean bool2;
    onInflate(paramAttributeSet, paramBundle);
    this.mCalled = true;
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Fragment);
    Transition transition = getEnterTransition();
    FragmentHostCallback fragmentHostCallback2 = null;
    setEnterTransition(loadTransition(paramContext, typedArray, transition, null, 4));
    setReturnTransition(loadTransition(paramContext, typedArray, getReturnTransition(), USE_DEFAULT_TRANSITION, 6));
    setExitTransition(loadTransition(paramContext, typedArray, getExitTransition(), null, 3));
    setReenterTransition(loadTransition(paramContext, typedArray, getReenterTransition(), USE_DEFAULT_TRANSITION, 8));
    setSharedElementEnterTransition(loadTransition(paramContext, typedArray, getSharedElementEnterTransition(), null, 5));
    setSharedElementReturnTransition(loadTransition(paramContext, typedArray, getSharedElementReturnTransition(), USE_DEFAULT_TRANSITION, 7));
    AnimationInfo animationInfo = this.mAnimationInfo;
    if (animationInfo == null) {
      bool1 = false;
      bool2 = false;
    } else {
      if (animationInfo.mAllowEnterTransitionOverlap != null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (this.mAnimationInfo.mAllowReturnTransitionOverlap != null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
    } 
    if (!bool1)
      setAllowEnterTransitionOverlap(typedArray.getBoolean(9, true)); 
    if (!bool2)
      setAllowReturnTransitionOverlap(typedArray.getBoolean(10, true)); 
    typedArray.recycle();
    FragmentHostCallback fragmentHostCallback1 = this.mHost;
    if (fragmentHostCallback1 == null) {
      fragmentHostCallback1 = fragmentHostCallback2;
    } else {
      activity = fragmentHostCallback1.getActivity();
    } 
    if (activity != null) {
      this.mCalled = false;
      onInflate(activity, paramAttributeSet, paramBundle);
    } 
  }
  
  @Deprecated
  public void onInflate(AttributeSet paramAttributeSet, Bundle paramBundle) {
    this.mCalled = true;
  }
  
  public void onLowMemory() {
    this.mCalled = true;
  }
  
  @Deprecated
  public void onMultiWindowModeChanged(boolean paramBoolean) {}
  
  public void onMultiWindowModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    onMultiWindowModeChanged(paramBoolean);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    return false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {}
  
  public void onPause() {
    this.mCalled = true;
  }
  
  @Deprecated
  public void onPictureInPictureModeChanged(boolean paramBoolean) {}
  
  public void onPictureInPictureModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    onPictureInPictureModeChanged(paramBoolean);
  }
  
  public void onPrepareOptionsMenu(Menu paramMenu) {}
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {}
  
  public void onResume() {
    this.mCalled = true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onStart() {
    this.mCalled = true;
    if (!this.mLoadersStarted) {
      this.mLoadersStarted = true;
      if (!this.mCheckedForLoaderManager) {
        this.mCheckedForLoaderManager = true;
        this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, true, false);
      } else {
        LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
        if (loaderManagerImpl != null)
          loaderManagerImpl.doStart(); 
      } 
    } 
  }
  
  public void onStop() {
    this.mCalled = true;
  }
  
  public void onTrimMemory(int paramInt) {
    this.mCalled = true;
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle) {}
  
  public void onViewStateRestored(Bundle paramBundle) {
    this.mCalled = true;
  }
  
  void performActivityCreated(Bundle paramBundle) {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
    this.mState = 2;
    this.mCalled = false;
    onActivityCreated(paramBundle);
    if (this.mCalled) {
      FragmentManagerImpl fragmentManagerImpl1 = this.mChildFragmentManager;
      if (fragmentManagerImpl1 != null)
        fragmentManagerImpl1.dispatchActivityCreated(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onActivityCreated()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performConfigurationChanged(Configuration paramConfiguration) {
    onConfigurationChanged(paramConfiguration);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchConfigurationChanged(paramConfiguration); 
  }
  
  boolean performContextItemSelected(MenuItem paramMenuItem) {
    if (!this.mHidden) {
      if (onContextItemSelected(paramMenuItem))
        return true; 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null && fragmentManagerImpl.dispatchContextItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  void performCreate(Bundle paramBundle) {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
    this.mState = 1;
    this.mCalled = false;
    onCreate(paramBundle);
    this.mIsCreated = true;
    if (this.mCalled) {
      byte b;
      Context context = getContext();
      if (context != null) {
        b = (context.getApplicationInfo()).targetSdkVersion;
      } else {
        b = 0;
      } 
      if (b < 24)
        restoreChildFragmentState(paramBundle, false); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onCreate()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (!this.mHidden) {
      boolean bool = bool2;
      if (this.mHasMenu) {
        bool = bool2;
        if (this.mMenuVisible) {
          bool = true;
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
        } 
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      bool1 = bool;
      if (fragmentManagerImpl != null)
        bool1 = bool | fragmentManagerImpl.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater); 
    } 
    return bool1;
  }
  
  View performCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.noteStateNotSaved(); 
    this.mPerformedCreateView = true;
    return onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  void performDestroy() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchDestroy(); 
    this.mState = 0;
    this.mCalled = false;
    this.mIsCreated = false;
    onDestroy();
    if (this.mCalled) {
      this.mChildFragmentManager = null;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onDestroy()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performDestroyView() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchDestroyView(); 
    this.mState = 1;
    this.mCalled = false;
    onDestroyView();
    if (this.mCalled) {
      LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
      if (loaderManagerImpl != null)
        loaderManagerImpl.doReportNextStart(); 
      this.mPerformedCreateView = false;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onDestroyView()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performDetach() {
    this.mCalled = false;
    onDetach();
    this.mLayoutInflater = null;
    if (this.mCalled) {
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null)
        if (this.mRetaining) {
          fragmentManagerImpl.dispatchDestroy();
          this.mChildFragmentManager = null;
        } else {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Child FragmentManager of ");
          stringBuilder1.append(this);
          stringBuilder1.append(" was not  destroyed and this fragment is not retaining instance");
          throw new IllegalStateException(stringBuilder1.toString());
        }  
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onDetach()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  LayoutInflater performGetLayoutInflater(Bundle paramBundle) {
    LayoutInflater layoutInflater = onGetLayoutInflater(paramBundle);
    this.mLayoutInflater = layoutInflater;
    return layoutInflater;
  }
  
  void performLowMemory() {
    onLowMemory();
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchLowMemory(); 
  }
  
  @Deprecated
  void performMultiWindowModeChanged(boolean paramBoolean) {
    onMultiWindowModeChanged(paramBoolean);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchMultiWindowModeChanged(paramBoolean); 
  }
  
  void performMultiWindowModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    onMultiWindowModeChanged(paramBoolean, paramConfiguration);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchMultiWindowModeChanged(paramBoolean, paramConfiguration); 
  }
  
  boolean performOptionsItemSelected(MenuItem paramMenuItem) {
    if (!this.mHidden) {
      if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(paramMenuItem))
        return true; 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null && fragmentManagerImpl.dispatchOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  void performOptionsMenuClosed(Menu paramMenu) {
    if (!this.mHidden) {
      if (this.mHasMenu && this.mMenuVisible)
        onOptionsMenuClosed(paramMenu); 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null)
        fragmentManagerImpl.dispatchOptionsMenuClosed(paramMenu); 
    } 
  }
  
  void performPause() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchPause(); 
    this.mState = 4;
    this.mCalled = false;
    onPause();
    if (this.mCalled)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onPause()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  @Deprecated
  void performPictureInPictureModeChanged(boolean paramBoolean) {
    onPictureInPictureModeChanged(paramBoolean);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchPictureInPictureModeChanged(paramBoolean); 
  }
  
  void performPictureInPictureModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    onPictureInPictureModeChanged(paramBoolean, paramConfiguration);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchPictureInPictureModeChanged(paramBoolean, paramConfiguration); 
  }
  
  boolean performPrepareOptionsMenu(Menu paramMenu) {
    boolean bool1 = false;
    boolean bool2 = false;
    if (!this.mHidden) {
      boolean bool = bool2;
      if (this.mHasMenu) {
        bool = bool2;
        if (this.mMenuVisible) {
          bool = true;
          onPrepareOptionsMenu(paramMenu);
        } 
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
      bool1 = bool;
      if (fragmentManagerImpl != null)
        bool1 = bool | fragmentManagerImpl.dispatchPrepareOptionsMenu(paramMenu); 
    } 
    return bool1;
  }
  
  void performResume() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null) {
      fragmentManagerImpl.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mState = 5;
    this.mCalled = false;
    onResume();
    if (this.mCalled) {
      fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null) {
        fragmentManagerImpl.dispatchResume();
        this.mChildFragmentManager.execPendingActions();
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onResume()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performSaveInstanceState(Bundle paramBundle) {
    onSaveInstanceState(paramBundle);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null) {
      Parcelable parcelable = fragmentManagerImpl.saveAllState();
      if (parcelable != null)
        paramBundle.putParcelable("android:fragments", parcelable); 
    } 
  }
  
  void performStart() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null) {
      fragmentManagerImpl.noteStateNotSaved();
      this.mChildFragmentManager.execPendingActions();
    } 
    this.mState = 4;
    this.mCalled = false;
    onStart();
    if (this.mCalled) {
      fragmentManagerImpl = this.mChildFragmentManager;
      if (fragmentManagerImpl != null)
        fragmentManagerImpl.dispatchStart(); 
      LoaderManagerImpl loaderManagerImpl = this.mLoaderManager;
      if (loaderManagerImpl != null)
        loaderManagerImpl.doReportStart(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onStart()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performStop() {
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchStop(); 
    this.mState = 3;
    this.mCalled = false;
    onStop();
    if (this.mCalled) {
      if (this.mLoadersStarted) {
        this.mLoadersStarted = false;
        if (!this.mCheckedForLoaderManager) {
          this.mCheckedForLoaderManager = true;
          this.mLoaderManager = this.mHost.getLoaderManager(this.mWho, false, false);
        } 
        if (this.mLoaderManager != null)
          if (this.mHost.getRetainLoaders()) {
            this.mLoaderManager.doRetain();
          } else {
            this.mLoaderManager.doStop();
          }  
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onStop()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  void performTrimMemory(int paramInt) {
    onTrimMemory(paramInt);
    FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
    if (fragmentManagerImpl != null)
      fragmentManagerImpl.dispatchTrimMemory(paramInt); 
  }
  
  public void postponeEnterTransition() {
    (ensureAnimationInfo()).mEnterTransitionPostponed = true;
  }
  
  public void registerForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(this);
  }
  
  public final void requestPermissions(String[] paramArrayOfString, int paramInt) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      fragmentHostCallback.onRequestPermissionsFromFragment(this, paramArrayOfString, paramInt);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  void restoreChildFragmentState(Bundle paramBundle, boolean paramBoolean) {
    if (paramBundle != null) {
      Parcelable parcelable = paramBundle.getParcelable("android:fragments");
      if (parcelable != null) {
        if (this.mChildFragmentManager == null)
          instantiateChildFragmentManager(); 
        FragmentManagerImpl fragmentManagerImpl = this.mChildFragmentManager;
        if (paramBoolean) {
          FragmentManagerNonConfig fragmentManagerNonConfig = this.mChildNonConfig;
        } else {
          paramBundle = null;
        } 
        fragmentManagerImpl.restoreAllState(parcelable, (FragmentManagerNonConfig)paramBundle);
        this.mChildNonConfig = null;
        this.mChildFragmentManager.dispatchCreate();
      } 
    } 
  }
  
  final void restoreViewState(Bundle paramBundle) {
    SparseArray<Parcelable> sparseArray = this.mSavedViewState;
    if (sparseArray != null) {
      this.mView.restoreHierarchyState(sparseArray);
      this.mSavedViewState = null;
    } 
    this.mCalled = false;
    onViewStateRestored(paramBundle);
    if (this.mCalled)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" did not call through to super.onViewStateRestored()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  public void setAllowEnterTransitionOverlap(boolean paramBoolean) {
    AnimationInfo.access$002(ensureAnimationInfo(), Boolean.valueOf(paramBoolean));
  }
  
  public void setAllowReturnTransitionOverlap(boolean paramBoolean) {
    AnimationInfo.access$102(ensureAnimationInfo(), Boolean.valueOf(paramBoolean));
  }
  
  void setAnimatingAway(Animator paramAnimator) {
    (ensureAnimationInfo()).mAnimatingAway = paramAnimator;
  }
  
  public void setArguments(Bundle paramBundle) {
    if (this.mIndex < 0 || !isStateSaved()) {
      this.mArguments = paramBundle;
      return;
    } 
    throw new IllegalStateException("Fragment already active");
  }
  
  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    SharedElementCallback sharedElementCallback = paramSharedElementCallback;
    if (paramSharedElementCallback == null) {
      if (this.mAnimationInfo == null)
        return; 
      sharedElementCallback = SharedElementCallback.NULL_CALLBACK;
    } 
    (ensureAnimationInfo()).mEnterTransitionCallback = sharedElementCallback;
  }
  
  public void setEnterTransition(Transition paramTransition) {
    if (shouldChangeTransition(paramTransition, null))
      AnimationInfo.access$202(ensureAnimationInfo(), paramTransition); 
  }
  
  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    SharedElementCallback sharedElementCallback = paramSharedElementCallback;
    if (paramSharedElementCallback == null) {
      if (this.mAnimationInfo == null)
        return; 
      sharedElementCallback = SharedElementCallback.NULL_CALLBACK;
    } 
    (ensureAnimationInfo()).mExitTransitionCallback = sharedElementCallback;
  }
  
  public void setExitTransition(Transition paramTransition) {
    if (shouldChangeTransition(paramTransition, null))
      AnimationInfo.access$402(ensureAnimationInfo(), paramTransition); 
  }
  
  public void setHasOptionsMenu(boolean paramBoolean) {
    if (this.mHasMenu != paramBoolean) {
      this.mHasMenu = paramBoolean;
      if (isAdded() && !isHidden())
        this.mFragmentManager.invalidateOptionsMenu(); 
    } 
  }
  
  void setHideReplaced(boolean paramBoolean) {
    (ensureAnimationInfo()).mIsHideReplaced = paramBoolean;
  }
  
  final void setIndex(int paramInt, Fragment paramFragment) {
    this.mIndex = paramInt;
    if (paramFragment != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramFragment.mWho);
      stringBuilder.append(":");
      stringBuilder.append(this.mIndex);
      this.mWho = stringBuilder.toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("android:fragment:");
      stringBuilder.append(this.mIndex);
      this.mWho = stringBuilder.toString();
    } 
  }
  
  public void setInitialSavedState(SavedState paramSavedState) {
    if (this.mIndex < 0) {
      if (paramSavedState != null && paramSavedState.mState != null) {
        Bundle bundle = paramSavedState.mState;
      } else {
        paramSavedState = null;
      } 
      this.mSavedFragmentState = (Bundle)paramSavedState;
      return;
    } 
    throw new IllegalStateException("Fragment already active");
  }
  
  public void setMenuVisibility(boolean paramBoolean) {
    if (this.mMenuVisible != paramBoolean) {
      this.mMenuVisible = paramBoolean;
      if (this.mHasMenu && isAdded() && !isHidden())
        this.mFragmentManager.invalidateOptionsMenu(); 
    } 
  }
  
  void setNextAnim(int paramInt) {
    if (this.mAnimationInfo == null && paramInt == 0)
      return; 
    (ensureAnimationInfo()).mNextAnim = paramInt;
  }
  
  void setNextTransition(int paramInt1, int paramInt2) {
    if (this.mAnimationInfo == null && paramInt1 == 0 && paramInt2 == 0)
      return; 
    ensureAnimationInfo();
    this.mAnimationInfo.mNextTransition = paramInt1;
    this.mAnimationInfo.mNextTransitionStyle = paramInt2;
  }
  
  void setOnStartEnterTransitionListener(OnStartEnterTransitionListener paramOnStartEnterTransitionListener) {
    ensureAnimationInfo();
    if (paramOnStartEnterTransitionListener == this.mAnimationInfo.mStartEnterTransitionListener)
      return; 
    if (paramOnStartEnterTransitionListener == null || this.mAnimationInfo.mStartEnterTransitionListener == null) {
      if (this.mAnimationInfo.mEnterTransitionPostponed)
        this.mAnimationInfo.mStartEnterTransitionListener = paramOnStartEnterTransitionListener; 
      if (paramOnStartEnterTransitionListener != null)
        paramOnStartEnterTransitionListener.startListening(); 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Trying to set a replacement startPostponedEnterTransition on ");
    stringBuilder.append(this);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void setReenterTransition(Transition paramTransition) {
    if (shouldChangeTransition(paramTransition, USE_DEFAULT_TRANSITION))
      AnimationInfo.access$502(ensureAnimationInfo(), paramTransition); 
  }
  
  public void setRetainInstance(boolean paramBoolean) {
    this.mRetainInstance = paramBoolean;
  }
  
  public void setReturnTransition(Transition paramTransition) {
    if (shouldChangeTransition(paramTransition, USE_DEFAULT_TRANSITION))
      AnimationInfo.access$302(ensureAnimationInfo(), paramTransition); 
  }
  
  public void setSharedElementEnterTransition(Transition paramTransition) {
    if (shouldChangeTransition(paramTransition, null))
      AnimationInfo.access$602(ensureAnimationInfo(), paramTransition); 
  }
  
  public void setSharedElementReturnTransition(Transition paramTransition) {
    if (shouldChangeTransition(paramTransition, USE_DEFAULT_TRANSITION))
      AnimationInfo.access$702(ensureAnimationInfo(), paramTransition); 
  }
  
  void setStateAfterAnimating(int paramInt) {
    (ensureAnimationInfo()).mStateAfterAnimating = paramInt;
  }
  
  public void setTargetFragment(Fragment paramFragment, int paramInt) {
    FragmentManager fragmentManager2;
    FragmentManager fragmentManager1 = getFragmentManager();
    if (paramFragment != null) {
      fragmentManager2 = paramFragment.getFragmentManager();
    } else {
      fragmentManager2 = null;
    } 
    if (fragmentManager1 == null || fragmentManager2 == null || fragmentManager1 == fragmentManager2) {
      Fragment fragment = paramFragment;
      while (fragment != null) {
        if (fragment != this) {
          fragment = fragment.getTargetFragment();
          continue;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Setting ");
        stringBuilder1.append(paramFragment);
        stringBuilder1.append(" as the target of ");
        stringBuilder1.append(this);
        stringBuilder1.append(" would create a target cycle");
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      this.mTarget = paramFragment;
      this.mTargetRequestCode = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(paramFragment);
    stringBuilder.append(" must share the same FragmentManager to be set as a target fragment");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void setUserVisibleHint(boolean paramBoolean) {
    boolean bool1 = false;
    Context context1 = getContext();
    FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
    Context context2 = context1;
    if (fragmentManagerImpl != null) {
      context2 = context1;
      if (fragmentManagerImpl.mHost != null)
        context2 = this.mFragmentManager.mHost.getContext(); 
    } 
    boolean bool2 = true;
    if (context2 != null)
      if ((context2.getApplicationInfo()).targetSdkVersion <= 23) {
        bool1 = true;
      } else {
        bool1 = false;
      }  
    if (bool1) {
      if (!this.mUserVisibleHint && paramBoolean && this.mState < 4 && this.mFragmentManager != null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
    } else if (!this.mUserVisibleHint && paramBoolean && this.mState < 4 && this.mFragmentManager != null && isAdded()) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (bool1)
      this.mFragmentManager.performPendingDeferredStart(this); 
    this.mUserVisibleHint = paramBoolean;
    if (this.mState < 4 && !paramBoolean) {
      paramBoolean = bool2;
    } else {
      paramBoolean = false;
    } 
    this.mDeferStart = paramBoolean;
  }
  
  public boolean shouldShowRequestPermissionRationale(String paramString) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    return (fragmentHostCallback != null) ? fragmentHostCallback.getContext().getPackageManager().shouldShowRequestPermissionRationale(paramString) : false;
  }
  
  public void startActivity(Intent paramIntent) {
    startActivity(paramIntent, null);
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      if (paramBundle != null) {
        fragmentHostCallback.onStartActivityFromFragment(this, paramIntent, -1, paramBundle);
      } else {
        fragmentHostCallback.onStartActivityFromFragment(this, paramIntent, -1, null);
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt) {
    startActivityForResult(paramIntent, paramInt, null);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      fragmentHostCallback.onStartActivityFromFragment(this, paramIntent, paramInt, paramBundle);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void startActivityForResultAsUser(Intent paramIntent, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      fragmentHostCallback.onStartActivityAsUserFromFragment(this, paramIntent, paramInt, paramBundle, paramUserHandle);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    FragmentHostCallback fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      fragmentHostCallback.onStartIntentSenderFromFragment(this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(this);
    stringBuilder.append(" not attached to Activity");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void startPostponedEnterTransition() {
    FragmentManagerImpl fragmentManagerImpl = this.mFragmentManager;
    if (fragmentManagerImpl == null || fragmentManagerImpl.mHost == null) {
      (ensureAnimationInfo()).mEnterTransitionPostponed = false;
      return;
    } 
    if (Looper.myLooper() != this.mFragmentManager.mHost.getHandler().getLooper()) {
      this.mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new _$$Lambda$Fragment$m7ODa2MK0_rf4XCEL5JOn14G0h8(this));
    } else {
      callStartTransitionListener();
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, stringBuilder);
    if (this.mIndex >= 0) {
      stringBuilder.append(" #");
      stringBuilder.append(this.mIndex);
    } 
    if (this.mFragmentId != 0) {
      stringBuilder.append(" id=0x");
      stringBuilder.append(Integer.toHexString(this.mFragmentId));
    } 
    if (this.mTag != null) {
      stringBuilder.append(" ");
      stringBuilder.append(this.mTag);
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void unregisterForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(null);
  }
  
  static class AnimationInfo {
    private Boolean mAllowEnterTransitionOverlap;
    
    private Boolean mAllowReturnTransitionOverlap;
    
    Animator mAnimatingAway;
    
    private Transition mEnterTransition = null;
    
    SharedElementCallback mEnterTransitionCallback = SharedElementCallback.NULL_CALLBACK;
    
    boolean mEnterTransitionPostponed;
    
    private Transition mExitTransition = null;
    
    SharedElementCallback mExitTransitionCallback = SharedElementCallback.NULL_CALLBACK;
    
    boolean mIsHideReplaced;
    
    int mNextAnim;
    
    int mNextTransition;
    
    int mNextTransitionStyle;
    
    private Transition mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
    
    private Transition mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    
    private Transition mSharedElementEnterTransition = null;
    
    private Transition mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    
    Fragment.OnStartEnterTransitionListener mStartEnterTransitionListener;
    
    int mStateAfterAnimating;
  }
  
  @Deprecated
  public static class InstantiationException extends AndroidRuntimeException {
    public InstantiationException(String param1String, Exception param1Exception) {
      super(param1String, param1Exception);
    }
  }
  
  static interface OnStartEnterTransitionListener {
    void onStartEnterTransition();
    
    void startListening();
  }
  
  @Deprecated
  public static class SavedState implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
        public Fragment.SavedState createFromParcel(Parcel param2Parcel) {
          return new Fragment.SavedState(param2Parcel, null);
        }
        
        public Fragment.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new Fragment.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public Fragment.SavedState[] newArray(int param2Int) {
          return new Fragment.SavedState[param2Int];
        }
      };
    
    final Bundle mState;
    
    SavedState(Bundle param1Bundle) {
      this.mState = param1Bundle;
    }
    
    SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      Bundle bundle = param1Parcel.readBundle();
      this.mState = bundle;
      if (param1ClassLoader != null && bundle != null)
        bundle.setClassLoader(param1ClassLoader); 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeBundle(this.mState);
    }
  }
  
  class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public Fragment.SavedState createFromParcel(Parcel param1Parcel) {
      return new Fragment.SavedState(param1Parcel, null);
    }
    
    public Fragment.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new Fragment.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public Fragment.SavedState[] newArray(int param1Int) {
      return new Fragment.SavedState[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Fragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */