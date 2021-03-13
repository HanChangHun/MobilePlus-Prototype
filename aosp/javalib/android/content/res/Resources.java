package android.content.res;

import android.animation.Animator;
import android.animation.StateListAnimator;
import android.content.res.loader.ResourcesLoader;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableInflater;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pools;
import android.util.TypedValue;
import android.view.DisplayAdjustments;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewHierarchyEncoder;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParserException;

public class Resources {
  public static final int ID_NULL = 0;
  
  private static final int MIN_THEME_REFS_FLUSH_SIZE = 32;
  
  static final String TAG = "Resources";
  
  static Resources mSystem;
  
  private static final Object sSync = new Object();
  
  private int mBaseApkAssetsSize;
  
  private UpdateCallbacks mCallbacks = null;
  
  final ClassLoader mClassLoader;
  
  private DrawableInflater mDrawableInflater;
  
  private DisplayAdjustments mOverrideDisplayAdjustments;
  
  private ResourcesImpl mResourcesImpl;
  
  private final ArrayList<WeakReference<Theme>> mThemeRefs = new ArrayList<>();
  
  private int mThemeRefsNextFlushSize = 32;
  
  private TypedValue mTmpValue = new TypedValue();
  
  private final Object mTmpValueLock = new Object();
  
  final Pools.SynchronizedPool<TypedArray> mTypedArrayPool = new Pools.SynchronizedPool(5);
  
  private final Object mUpdateLock = new Object();
  
  static {
    mSystem = null;
  }
  
  private Resources() {
    this(null);
    DisplayMetrics displayMetrics = new DisplayMetrics();
    displayMetrics.setToDefaults();
    Configuration configuration = new Configuration();
    configuration.setToDefaults();
    this.mResourcesImpl = new ResourcesImpl(AssetManager.getSystem(), displayMetrics, configuration, new DisplayAdjustments());
  }
  
  @Deprecated
  public Resources(AssetManager paramAssetManager, DisplayMetrics paramDisplayMetrics, Configuration paramConfiguration) {
    this(null);
    this.mResourcesImpl = new ResourcesImpl(paramAssetManager, paramDisplayMetrics, paramConfiguration, new DisplayAdjustments());
  }
  
  public Resources(ClassLoader paramClassLoader) {
    if (paramClassLoader == null)
      paramClassLoader = ClassLoader.getSystemClassLoader(); 
    this.mClassLoader = paramClassLoader;
  }
  
  private void checkCallbacksRegistered() {
    if (this.mCallbacks == null)
      this.mCallbacks = new AssetManagerUpdateHandler(); 
  }
  
  public static int getAttributeSetSourceResId(AttributeSet paramAttributeSet) {
    return ResourcesImpl.getAttributeSetSourceResId(paramAttributeSet);
  }
  
  public static Resources getSystem() {
    synchronized (sSync) {
      Resources resources1 = mSystem;
      Resources resources2 = resources1;
      if (resources1 == null) {
        resources2 = new Resources();
        this();
        mSystem = resources2;
      } 
      return resources2;
    } 
  }
  
  public static TypedArray obtainAttributes(Resources paramResources, Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    return (paramTheme == null) ? paramResources.obtainAttributes(paramAttributeSet, paramArrayOfint) : paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
  }
  
  private TypedValue obtainTempTypedValue() {
    null = null;
    synchronized (this.mTmpValueLock) {
      if (this.mTmpValue != null) {
        null = this.mTmpValue;
        this.mTmpValue = null;
      } 
      return (null == null) ? new TypedValue() : null;
    } 
  }
  
  private void releaseTempTypedValue(TypedValue paramTypedValue) {
    synchronized (this.mTmpValueLock) {
      if (this.mTmpValue == null)
        this.mTmpValue = paramTypedValue; 
      return;
    } 
  }
  
  public static boolean resourceHasPackage(int paramInt) {
    boolean bool;
    if (paramInt >>> 24 != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static int selectDefaultTheme(int paramInt1, int paramInt2) {
    return selectSystemTheme(paramInt1, paramInt2, 16973829, 16973931, 16974120, 16974143);
  }
  
  public static int selectSystemTheme(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    return (paramInt1 != 0) ? paramInt1 : ((paramInt2 < 11) ? paramInt3 : ((paramInt2 < 14) ? paramInt4 : ((paramInt2 < 24) ? paramInt5 : paramInt6)));
  }
  
  public static void updateSystemConfiguration(Configuration paramConfiguration, DisplayMetrics paramDisplayMetrics, CompatibilityInfo paramCompatibilityInfo) {
    Resources resources = mSystem;
    if (resources != null)
      resources.updateConfiguration(paramConfiguration, paramDisplayMetrics, paramCompatibilityInfo); 
  }
  
  public void addLoaders(ResourcesLoader... paramVarArgs) {
    synchronized (this.mUpdateLock) {
      checkCallbacksRegistered();
      ArrayList<ResourcesLoader> arrayList = new ArrayList();
      this((Collection)this.mResourcesImpl.getAssets().getLoaders());
      ArraySet arraySet = new ArraySet();
      this(arrayList);
      int i;
      for (i = 0; i < paramVarArgs.length; i++) {
        ResourcesLoader resourcesLoader = paramVarArgs[i];
        if (!arraySet.contains(resourcesLoader))
          arrayList.add(resourcesLoader); 
      } 
      if (arraySet.size() == arrayList.size())
        return; 
      this.mCallbacks.onLoadersChanged(this, arrayList);
      i = arraySet.size();
      int j = arrayList.size();
      while (i < j) {
        ((ResourcesLoader)arrayList.get(i)).registerOnProvidersChangedCallback(this, this.mCallbacks);
        i++;
      } 
      return;
    } 
  }
  
  public int calcConfigChanges(Configuration paramConfiguration) {
    return this.mResourcesImpl.calcConfigChanges(paramConfiguration);
  }
  
  public void clearLoaders() {
    synchronized (this.mUpdateLock) {
      checkCallbacksRegistered();
      List<?> list = Collections.emptyList();
      List<ResourcesLoader> list1 = this.mResourcesImpl.getAssets().getLoaders();
      this.mCallbacks.onLoadersChanged(this, (List)list);
      Iterator<ResourcesLoader> iterator = list1.iterator();
      while (iterator.hasNext())
        ((ResourcesLoader)iterator.next()).unregisterOnProvidersChangedCallback(this); 
      return;
    } 
  }
  
  public final void finishPreloading() {
    this.mResourcesImpl.finishPreloading();
  }
  
  public final void flushLayoutCache() {
    this.mResourcesImpl.flushLayoutCache();
  }
  
  public XmlResourceParser getAnimation(int paramInt) throws NotFoundException {
    return loadXmlResourceParser(paramInt, "anim");
  }
  
  public ConfigurationBoundResourceCache<Animator> getAnimatorCache() {
    return this.mResourcesImpl.getAnimatorCache();
  }
  
  public final AssetManager getAssets() {
    return this.mResourcesImpl.getAssets();
  }
  
  public boolean getBoolean(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      boolean bool = true;
      resourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type >= 16 && typedValue.type <= 31) {
        paramInt = typedValue.data;
        if (paramInt == 0)
          bool = false; 
        return bool;
      } 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public ClassLoader getClassLoader() {
    return this.mClassLoader;
  }
  
  @Deprecated
  public int getColor(int paramInt) throws NotFoundException {
    return getColor(paramInt, null);
  }
  
  public int getColor(int paramInt, Theme paramTheme) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      resourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type >= 16 && typedValue.type <= 31) {
        paramInt = typedValue.data;
        return paramInt;
      } 
      if (typedValue.type == 3) {
        paramInt = resourcesImpl.loadColorStateList(this, typedValue, paramInt, paramTheme).getDefaultColor();
        return paramInt;
      } 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  @Deprecated
  public ColorStateList getColorStateList(int paramInt) throws NotFoundException {
    ColorStateList colorStateList = getColorStateList(paramInt, null);
    if (colorStateList != null && colorStateList.canApplyTheme()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ColorStateList ");
      stringBuilder.append(getResourceName(paramInt));
      stringBuilder.append(" has unresolved theme attributes! Consider using Resources.getColorStateList(int, Theme) or Context.getColorStateList(int).");
      Log.w("Resources", stringBuilder.toString(), new RuntimeException());
    } 
    return colorStateList;
  }
  
  public ColorStateList getColorStateList(int paramInt, Theme paramTheme) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      resourcesImpl.getValue(paramInt, typedValue, true);
      return resourcesImpl.loadColorStateList(this, typedValue, paramInt, paramTheme);
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public CompatibilityInfo getCompatibilityInfo() {
    return this.mResourcesImpl.getCompatibilityInfo();
  }
  
  public Configuration getConfiguration() {
    return this.mResourcesImpl.getConfiguration();
  }
  
  public float getDimension(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      resourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type == 5)
        return TypedValue.complexToDimension(typedValue.data, resourcesImpl.getDisplayMetrics()); 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public int getDimensionPixelOffset(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      resourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type == 5) {
        paramInt = TypedValue.complexToDimensionPixelOffset(typedValue.data, resourcesImpl.getDisplayMetrics());
        return paramInt;
      } 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public int getDimensionPixelSize(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      resourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type == 5) {
        paramInt = TypedValue.complexToDimensionPixelSize(typedValue.data, resourcesImpl.getDisplayMetrics());
        return paramInt;
      } 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public DisplayAdjustments getDisplayAdjustments() {
    DisplayAdjustments displayAdjustments = this.mOverrideDisplayAdjustments;
    return (displayAdjustments != null) ? displayAdjustments : this.mResourcesImpl.getDisplayAdjustments();
  }
  
  public DisplayMetrics getDisplayMetrics() {
    return this.mResourcesImpl.getDisplayMetrics();
  }
  
  @Deprecated
  public Drawable getDrawable(int paramInt) throws NotFoundException {
    Drawable drawable = getDrawable(paramInt, null);
    if (drawable != null && drawable.canApplyTheme()) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Drawable ");
      stringBuilder.append(getResourceName(paramInt));
      stringBuilder.append(" has unresolved theme attributes! Consider using Resources.getDrawable(int, Theme) or Context.getDrawable(int).");
      Log.w("Resources", stringBuilder.toString(), new RuntimeException());
    } 
    return drawable;
  }
  
  public Drawable getDrawable(int paramInt, Theme paramTheme) throws NotFoundException {
    return getDrawableForDensity(paramInt, 0, paramTheme);
  }
  
  @Deprecated
  public Drawable getDrawableForDensity(int paramInt1, int paramInt2) throws NotFoundException {
    return getDrawableForDensity(paramInt1, paramInt2, null);
  }
  
  public Drawable getDrawableForDensity(int paramInt1, int paramInt2, Theme paramTheme) {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      this.mResourcesImpl.getValueForDensity(paramInt1, paramInt2, typedValue, true);
      return loadDrawable(typedValue, paramInt1, paramInt2, paramTheme);
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public final DrawableInflater getDrawableInflater() {
    if (this.mDrawableInflater == null)
      this.mDrawableInflater = new DrawableInflater(this, this.mClassLoader); 
    return this.mDrawableInflater;
  }
  
  public float getFloat(int paramInt) {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      this.mResourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type == 4)
        return typedValue.getFloat(); 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public Typeface getFont(int paramInt) throws NotFoundException {
    StringBuilder stringBuilder;
    TypedValue typedValue = obtainTempTypedValue();
    try {
      ResourcesImpl resourcesImpl = this.mResourcesImpl;
      resourcesImpl.getValue(paramInt, typedValue, true);
      Typeface typeface = resourcesImpl.loadFont(this, typedValue, paramInt);
      if (typeface != null)
        return typeface; 
      releaseTempTypedValue(typedValue);
      stringBuilder = new StringBuilder();
      stringBuilder.append("Font resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      throw new NotFoundException(stringBuilder.toString());
    } finally {
      releaseTempTypedValue((TypedValue)stringBuilder);
    } 
  }
  
  Typeface getFont(TypedValue paramTypedValue, int paramInt) throws NotFoundException {
    return this.mResourcesImpl.loadFont(this, paramTypedValue, paramInt);
  }
  
  public float getFraction(int paramInt1, int paramInt2, int paramInt3) {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      this.mResourcesImpl.getValue(paramInt1, typedValue, true);
      if (typedValue.type == 6)
        return TypedValue.complexToFraction(typedValue.data, paramInt2, paramInt3); 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt1));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public int getIdentifier(String paramString1, String paramString2, String paramString3) {
    return this.mResourcesImpl.getIdentifier(paramString1, paramString2, paramString3);
  }
  
  public ResourcesImpl getImpl() {
    return this.mResourcesImpl;
  }
  
  public int[] getIntArray(int paramInt) throws NotFoundException {
    int[] arrayOfInt = this.mResourcesImpl.getAssets().getResourceIntArray(paramInt);
    if (arrayOfInt != null)
      return arrayOfInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Int array resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new NotFoundException(stringBuilder.toString());
  }
  
  public int getInteger(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      this.mResourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type >= 16 && typedValue.type <= 31) {
        paramInt = typedValue.data;
        return paramInt;
      } 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public String getLastResourceResolution() throws NotFoundException {
    return this.mResourcesImpl.getLastResourceResolution();
  }
  
  public XmlResourceParser getLayout(int paramInt) throws NotFoundException {
    return loadXmlResourceParser(paramInt, "layout");
  }
  
  public List<ResourcesLoader> getLoaders() {
    return this.mResourcesImpl.getAssets().getLoaders();
  }
  
  @Deprecated
  public Movie getMovie(int paramInt) throws NotFoundException {
    InputStream inputStream = openRawResource(paramInt);
    Movie movie = Movie.decodeStream(inputStream);
    try {
      inputStream.close();
    } catch (IOException iOException) {}
    return movie;
  }
  
  public LongSparseArray<Drawable.ConstantState> getPreloadedDrawables() {
    return this.mResourcesImpl.getPreloadedDrawables();
  }
  
  public String getQuantityString(int paramInt1, int paramInt2) throws NotFoundException {
    return getQuantityText(paramInt1, paramInt2).toString();
  }
  
  public String getQuantityString(int paramInt1, int paramInt2, Object... paramVarArgs) throws NotFoundException {
    String str = getQuantityText(paramInt1, paramInt2).toString();
    return String.format(this.mResourcesImpl.getConfiguration().getLocales().get(0), str, paramVarArgs);
  }
  
  public CharSequence getQuantityText(int paramInt1, int paramInt2) throws NotFoundException {
    return this.mResourcesImpl.getQuantityText(paramInt1, paramInt2);
  }
  
  public String getResourceEntryName(int paramInt) throws NotFoundException {
    return this.mResourcesImpl.getResourceEntryName(paramInt);
  }
  
  public String getResourceName(int paramInt) throws NotFoundException {
    return this.mResourcesImpl.getResourceName(paramInt);
  }
  
  public String getResourcePackageName(int paramInt) throws NotFoundException {
    return this.mResourcesImpl.getResourcePackageName(paramInt);
  }
  
  public String getResourceTypeName(int paramInt) throws NotFoundException {
    return this.mResourcesImpl.getResourceTypeName(paramInt);
  }
  
  public Configuration[] getSizeConfigurations() {
    return this.mResourcesImpl.getSizeConfigurations();
  }
  
  public ConfigurationBoundResourceCache<StateListAnimator> getStateListAnimatorCache() {
    return this.mResourcesImpl.getStateListAnimatorCache();
  }
  
  public String getString(int paramInt) throws NotFoundException {
    return getText(paramInt).toString();
  }
  
  public String getString(int paramInt, Object... paramVarArgs) throws NotFoundException {
    String str = getString(paramInt);
    return String.format(this.mResourcesImpl.getConfiguration().getLocales().get(0), str, paramVarArgs);
  }
  
  public String[] getStringArray(int paramInt) throws NotFoundException {
    String[] arrayOfString = this.mResourcesImpl.getAssets().getResourceStringArray(paramInt);
    if (arrayOfString != null)
      return arrayOfString; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("String array resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new NotFoundException(stringBuilder.toString());
  }
  
  public CharSequence getText(int paramInt) throws NotFoundException {
    CharSequence charSequence = this.mResourcesImpl.getAssets().getResourceText(paramInt);
    if (charSequence != null)
      return charSequence; 
    charSequence = new StringBuilder();
    charSequence.append("String resource ID #0x");
    charSequence.append(Integer.toHexString(paramInt));
    throw new NotFoundException(charSequence.toString());
  }
  
  public CharSequence getText(int paramInt, CharSequence paramCharSequence) {
    CharSequence charSequence;
    if (paramInt != 0) {
      charSequence = this.mResourcesImpl.getAssets().getResourceText(paramInt);
    } else {
      charSequence = null;
    } 
    if (charSequence != null)
      paramCharSequence = charSequence; 
    return paramCharSequence;
  }
  
  public CharSequence[] getTextArray(int paramInt) throws NotFoundException {
    CharSequence[] arrayOfCharSequence = this.mResourcesImpl.getAssets().getResourceTextArray(paramInt);
    if (arrayOfCharSequence != null)
      return arrayOfCharSequence; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Text array resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new NotFoundException(stringBuilder.toString());
  }
  
  public void getValue(int paramInt, TypedValue paramTypedValue, boolean paramBoolean) throws NotFoundException {
    this.mResourcesImpl.getValue(paramInt, paramTypedValue, paramBoolean);
  }
  
  public void getValue(String paramString, TypedValue paramTypedValue, boolean paramBoolean) throws NotFoundException {
    this.mResourcesImpl.getValue(paramString, paramTypedValue, paramBoolean);
  }
  
  public void getValueForDensity(int paramInt1, int paramInt2, TypedValue paramTypedValue, boolean paramBoolean) throws NotFoundException {
    this.mResourcesImpl.getValueForDensity(paramInt1, paramInt2, paramTypedValue, paramBoolean);
  }
  
  public XmlResourceParser getXml(int paramInt) throws NotFoundException {
    return loadXmlResourceParser(paramInt, "xml");
  }
  
  public boolean hasOverrideDisplayAdjustments() {
    boolean bool;
    if (this.mOverrideDisplayAdjustments != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  ColorStateList loadColorStateList(TypedValue paramTypedValue, int paramInt, Theme paramTheme) throws NotFoundException {
    return this.mResourcesImpl.loadColorStateList(this, paramTypedValue, paramInt, paramTheme);
  }
  
  public ComplexColor loadComplexColor(TypedValue paramTypedValue, int paramInt, Theme paramTheme) {
    return this.mResourcesImpl.loadComplexColor(this, paramTypedValue, paramInt, paramTheme);
  }
  
  Drawable loadDrawable(TypedValue paramTypedValue, int paramInt1, int paramInt2, Theme paramTheme) throws NotFoundException {
    return this.mResourcesImpl.loadDrawable(this, paramTypedValue, paramInt1, paramInt2, paramTheme);
  }
  
  XmlResourceParser loadXmlResourceParser(int paramInt, String paramString) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      this.mResourcesImpl.getValue(paramInt, typedValue, true);
      if (typedValue.type == 3)
        return loadXmlResourceParser(typedValue.string.toString(), paramInt, typedValue.assetCookie, paramString); 
      NotFoundException notFoundException = new NotFoundException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Resource ID #0x");
      stringBuilder.append(Integer.toHexString(paramInt));
      stringBuilder.append(" type #0x");
      stringBuilder.append(Integer.toHexString(typedValue.type));
      stringBuilder.append(" is not valid");
      this(stringBuilder.toString());
      throw notFoundException;
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  XmlResourceParser loadXmlResourceParser(String paramString1, int paramInt1, int paramInt2, String paramString2) throws NotFoundException {
    return this.mResourcesImpl.loadXmlResourceParser(paramString1, paramInt1, paramInt2, paramString2);
  }
  
  public final Theme newTheme() {
    Theme theme = new Theme();
    theme.setImpl(this.mResourcesImpl.newThemeImpl());
    synchronized (this.mThemeRefs) {
      ArrayList<WeakReference<Theme>> arrayList = this.mThemeRefs;
      WeakReference<Theme> weakReference = new WeakReference();
      this((T)theme);
      arrayList.add(weakReference);
      if (this.mThemeRefs.size() > this.mThemeRefsNextFlushSize) {
        this.mThemeRefs.removeIf((Predicate<? super WeakReference<Theme>>)_$$Lambda$Resources$4msWUw7LKsgLexLZjIfWa4oguq4.INSTANCE);
        this.mThemeRefsNextFlushSize = Math.max(32, this.mThemeRefs.size() * 2);
      } 
      return theme;
    } 
  }
  
  public TypedArray obtainAttributes(AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    TypedArray typedArray = TypedArray.obtain(this, paramArrayOfint.length);
    paramAttributeSet = paramAttributeSet;
    this.mResourcesImpl.getAssets().retrieveAttributes((XmlBlock.Parser)paramAttributeSet, paramArrayOfint, typedArray.mData, typedArray.mIndices);
    typedArray.mXml = (XmlBlock.Parser)paramAttributeSet;
    return typedArray;
  }
  
  public TypedArray obtainTypedArray(int paramInt) throws NotFoundException {
    ResourcesImpl resourcesImpl = this.mResourcesImpl;
    int i = resourcesImpl.getAssets().getResourceArraySize(paramInt);
    if (i >= 0) {
      TypedArray typedArray = TypedArray.obtain(this, i);
      typedArray.mLength = resourcesImpl.getAssets().getResourceArray(paramInt, typedArray.mData);
      typedArray.mIndices[0] = 0;
      return typedArray;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Array resource ID #0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new NotFoundException(stringBuilder.toString());
  }
  
  public InputStream openRawResource(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      return openRawResource(paramInt, typedValue);
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public InputStream openRawResource(int paramInt, TypedValue paramTypedValue) throws NotFoundException {
    return this.mResourcesImpl.openRawResource(paramInt, paramTypedValue);
  }
  
  public AssetFileDescriptor openRawResourceFd(int paramInt) throws NotFoundException {
    TypedValue typedValue = obtainTempTypedValue();
    try {
      return this.mResourcesImpl.openRawResourceFd(paramInt, typedValue);
    } finally {
      releaseTempTypedValue(typedValue);
    } 
  }
  
  public void overrideDisplayAdjustments(Consumer<DisplayAdjustments> paramConsumer) {
    if (paramConsumer != null) {
      DisplayAdjustments displayAdjustments = new DisplayAdjustments(this.mResourcesImpl.getDisplayAdjustments());
      this.mOverrideDisplayAdjustments = displayAdjustments;
      paramConsumer.accept(displayAdjustments);
    } else {
      this.mOverrideDisplayAdjustments = null;
    } 
  }
  
  public void parseBundleExtra(String paramString, AttributeSet paramAttributeSet, Bundle paramBundle) throws XmlPullParserException {
    TypedArray typedArray = obtainAttributes(paramAttributeSet, R.styleable.Extra);
    boolean bool = false;
    String str = typedArray.getString(0);
    if (str != null) {
      TypedValue typedValue = typedArray.peekValue(1);
      if (typedValue != null) {
        if (typedValue.type == 3) {
          paramBundle.putCharSequence(str, typedValue.coerceToString());
        } else if (typedValue.type == 18) {
          if (typedValue.data != 0)
            bool = true; 
          paramBundle.putBoolean(str, bool);
        } else if (typedValue.type >= 16 && typedValue.type <= 31) {
          paramBundle.putInt(str, typedValue.data);
        } else if (typedValue.type == 4) {
          paramBundle.putFloat(str, typedValue.getFloat());
        } else {
          typedArray.recycle();
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("<");
          stringBuilder2.append(paramString);
          stringBuilder2.append("> only supports string, integer, float, color, and boolean at ");
          stringBuilder2.append(paramAttributeSet.getPositionDescription());
          throw new XmlPullParserException(stringBuilder2.toString());
        } 
        typedArray.recycle();
        return;
      } 
      typedArray.recycle();
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("<");
      stringBuilder1.append(paramString);
      stringBuilder1.append("> requires an android:value or android:resource attribute at ");
      stringBuilder1.append(paramAttributeSet.getPositionDescription());
      throw new XmlPullParserException(stringBuilder1.toString());
    } 
    typedArray.recycle();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(paramString);
    stringBuilder.append("> requires an android:name attribute at ");
    stringBuilder.append(paramAttributeSet.getPositionDescription());
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  public void parseBundleExtras(XmlResourceParser paramXmlResourceParser, Bundle paramBundle) throws XmlPullParserException, IOException {
    int i = paramXmlResourceParser.getDepth();
    while (true) {
      int j = paramXmlResourceParser.next();
      if (j != 1 && (j != 3 || paramXmlResourceParser.getDepth() > i)) {
        if (j == 3 || j == 4)
          continue; 
        if (paramXmlResourceParser.getName().equals("extra")) {
          parseBundleExtra("extra", paramXmlResourceParser, paramBundle);
          XmlUtils.skipCurrentTag(paramXmlResourceParser);
          continue;
        } 
        XmlUtils.skipCurrentTag(paramXmlResourceParser);
        continue;
      } 
      break;
    } 
  }
  
  public void preloadFonts(int paramInt) {
    TypedArray typedArray = obtainTypedArray(paramInt);
    try {
      int i = typedArray.length();
      for (paramInt = 0; paramInt < i; paramInt++)
        typedArray.getFont(paramInt); 
      return;
    } finally {
      typedArray.recycle();
    } 
  }
  
  public void removeLoaders(ResourcesLoader... paramVarArgs) {
    synchronized (this.mUpdateLock) {
      checkCallbacksRegistered();
      ArraySet arraySet = new ArraySet();
      this((Object[])paramVarArgs);
      ArrayList<ResourcesLoader> arrayList = new ArrayList();
      this();
      List<ResourcesLoader> list = this.mResourcesImpl.getAssets().getLoaders();
      byte b = 0;
      int i = list.size();
      while (b < i) {
        ResourcesLoader resourcesLoader = list.get(b);
        if (!arraySet.contains(resourcesLoader))
          arrayList.add(resourcesLoader); 
        b++;
      } 
      if (list.size() == arrayList.size())
        return; 
      this.mCallbacks.onLoadersChanged(this, arrayList);
      for (b = 0; b < paramVarArgs.length; b++)
        paramVarArgs[b].unregisterOnProvidersChangedCallback(this); 
      return;
    } 
  }
  
  public void setCallbacks(UpdateCallbacks paramUpdateCallbacks) {
    if (this.mCallbacks == null) {
      this.mCallbacks = paramUpdateCallbacks;
      return;
    } 
    throw new IllegalStateException("callback already registered");
  }
  
  public void setCompatibilityInfo(CompatibilityInfo paramCompatibilityInfo) {
    if (paramCompatibilityInfo != null)
      this.mResourcesImpl.updateConfiguration(null, null, paramCompatibilityInfo); 
  }
  
  public void setImpl(ResourcesImpl paramResourcesImpl) {
    if (paramResourcesImpl == this.mResourcesImpl)
      return; 
    this.mBaseApkAssetsSize = ArrayUtils.size((Object[])paramResourcesImpl.getAssets().getApkAssets());
    this.mResourcesImpl = paramResourcesImpl;
    synchronized (this.mThemeRefs) {
      int i = this.mThemeRefs.size();
      for (byte b = 0; b < i; b++) {
        WeakReference<Theme> weakReference = this.mThemeRefs.get(b);
        if (weakReference != null) {
          Theme theme = weakReference.get();
        } else {
          weakReference = null;
        } 
        if (weakReference != null)
          weakReference.setImpl(this.mResourcesImpl.newThemeImpl(weakReference.getKey())); 
      } 
      return;
    } 
  }
  
  public final void startPreloading() {
    this.mResourcesImpl.startPreloading();
  }
  
  @Deprecated
  public void updateConfiguration(Configuration paramConfiguration, DisplayMetrics paramDisplayMetrics) {
    updateConfiguration(paramConfiguration, paramDisplayMetrics, null);
  }
  
  public void updateConfiguration(Configuration paramConfiguration, DisplayMetrics paramDisplayMetrics, CompatibilityInfo paramCompatibilityInfo) {
    this.mResourcesImpl.updateConfiguration(paramConfiguration, paramDisplayMetrics, paramCompatibilityInfo);
  }
  
  public class AssetManagerUpdateHandler implements UpdateCallbacks {
    public void onLoaderUpdated(ResourcesLoader param1ResourcesLoader) {
      ResourcesImpl resourcesImpl = Resources.this.mResourcesImpl;
      AssetManager assetManager = resourcesImpl.getAssets();
      if (assetManager.getLoaders().contains(param1ResourcesLoader)) {
        resourcesImpl.clearAllCaches();
        assetManager.setLoaders(assetManager.getLoaders());
      } 
    }
    
    public void onLoadersChanged(Resources param1Resources, List<ResourcesLoader> param1List) {
      boolean bool;
      if (Resources.this == param1Resources) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool);
      ResourcesImpl resourcesImpl = Resources.this.mResourcesImpl;
      resourcesImpl.clearAllCaches();
      resourcesImpl.getAssets().setLoaders(param1List);
    }
  }
  
  public static class NotFoundException extends RuntimeException {
    public NotFoundException() {}
    
    public NotFoundException(String param1String) {
      super(param1String);
    }
    
    public NotFoundException(String param1String, Exception param1Exception) {
      super(param1String, param1Exception);
    }
  }
  
  public final class Theme {
    private ResourcesImpl.ThemeImpl mThemeImpl;
    
    private Theme() {}
    
    private String getResourceNameFromHexString(String param1String) {
      return Resources.this.getResourceName(Integer.parseInt(param1String, 16));
    }
    
    public void applyStyle(int param1Int, boolean param1Boolean) {
      this.mThemeImpl.applyStyle(param1Int, param1Boolean);
    }
    
    public void dump(int param1Int, String param1String1, String param1String2) {
      this.mThemeImpl.dump(param1Int, param1String1, param1String2);
    }
    
    public void encode(ViewHierarchyEncoder param1ViewHierarchyEncoder) {
      param1ViewHierarchyEncoder.beginObject(this);
      String[] arrayOfString = getTheme();
      for (byte b = 0; b < arrayOfString.length; b += 2)
        param1ViewHierarchyEncoder.addProperty(arrayOfString[b], arrayOfString[b + 1]); 
      param1ViewHierarchyEncoder.endObject();
    }
    
    public int[] getAllAttributes() {
      return this.mThemeImpl.getAllAttributes();
    }
    
    int getAppliedStyleResId() {
      return this.mThemeImpl.getAppliedStyleResId();
    }
    
    public int[] getAttributeResolutionStack(int param1Int1, int param1Int2, int param1Int3) {
      int[] arrayOfInt = this.mThemeImpl.getAttributeResolutionStack(param1Int1, param1Int2, param1Int3);
      return (arrayOfInt == null) ? new int[0] : arrayOfInt;
    }
    
    public int getChangingConfigurations() {
      return this.mThemeImpl.getChangingConfigurations();
    }
    
    public Drawable getDrawable(int param1Int) throws Resources.NotFoundException {
      return Resources.this.getDrawable(param1Int, this);
    }
    
    public int getExplicitStyle(AttributeSet param1AttributeSet) {
      TypedValue typedValue;
      if (param1AttributeSet == null)
        return 0; 
      int i = param1AttributeSet.getStyleAttribute();
      if (i == 0)
        return 0; 
      String str = getResources().getResourceTypeName(i);
      if ("attr".equals(str)) {
        typedValue = new TypedValue();
        if (resolveAttribute(i, typedValue, true))
          return typedValue.resourceId; 
      } else if ("style".equals(typedValue)) {
        return i;
      } 
      return 0;
    }
    
    public Resources.ThemeKey getKey() {
      return this.mThemeImpl.getKey();
    }
    
    long getNativeTheme() {
      return this.mThemeImpl.getNativeTheme();
    }
    
    public Resources getResources() {
      return Resources.this;
    }
    
    @ExportedProperty(category = "theme", hasAdjacentMapping = true)
    public String[] getTheme() {
      return this.mThemeImpl.getTheme();
    }
    
    public TypedArray obtainStyledAttributes(int param1Int, int[] param1ArrayOfint) throws Resources.NotFoundException {
      return this.mThemeImpl.obtainStyledAttributes(this, null, param1ArrayOfint, 0, param1Int);
    }
    
    public TypedArray obtainStyledAttributes(AttributeSet param1AttributeSet, int[] param1ArrayOfint, int param1Int1, int param1Int2) {
      return this.mThemeImpl.obtainStyledAttributes(this, param1AttributeSet, param1ArrayOfint, param1Int1, param1Int2);
    }
    
    public TypedArray obtainStyledAttributes(int[] param1ArrayOfint) {
      return this.mThemeImpl.obtainStyledAttributes(this, null, param1ArrayOfint, 0, 0);
    }
    
    public void rebase() {
      this.mThemeImpl.rebase();
    }
    
    public boolean resolveAttribute(int param1Int, TypedValue param1TypedValue, boolean param1Boolean) {
      return this.mThemeImpl.resolveAttribute(param1Int, param1TypedValue, param1Boolean);
    }
    
    public TypedArray resolveAttributes(int[] param1ArrayOfint1, int[] param1ArrayOfint2) {
      return this.mThemeImpl.resolveAttributes(this, param1ArrayOfint1, param1ArrayOfint2);
    }
    
    void setImpl(ResourcesImpl.ThemeImpl param1ThemeImpl) {
      this.mThemeImpl = param1ThemeImpl;
    }
    
    public void setTo(Theme param1Theme) {
      this.mThemeImpl.setTo(param1Theme.mThemeImpl);
    }
  }
  
  static class ThemeKey implements Cloneable {
    int mCount;
    
    boolean[] mForce;
    
    private int mHashCode = 0;
    
    int[] mResId;
    
    public void append(int param1Int, boolean param1Boolean) {
      if (this.mResId == null)
        this.mResId = new int[4]; 
      if (this.mForce == null)
        this.mForce = new boolean[4]; 
      this.mResId = GrowingArrayUtils.append(this.mResId, this.mCount, param1Int);
      this.mForce = GrowingArrayUtils.append(this.mForce, this.mCount, param1Boolean);
      this.mCount++;
      this.mHashCode = (this.mHashCode * 31 + param1Int) * 31 + param1Boolean;
    }
    
    public ThemeKey clone() {
      ThemeKey themeKey = new ThemeKey();
      themeKey.mResId = this.mResId;
      themeKey.mForce = this.mForce;
      themeKey.mCount = this.mCount;
      themeKey.mHashCode = this.mHashCode;
      return themeKey;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass() || hashCode() != param1Object.hashCode())
        return false; 
      param1Object = param1Object;
      if (this.mCount != ((ThemeKey)param1Object).mCount)
        return false; 
      int i = this.mCount;
      for (byte b = 0; b < i; b++) {
        if (this.mResId[b] != ((ThemeKey)param1Object).mResId[b] || this.mForce[b] != ((ThemeKey)param1Object).mForce[b])
          return false; 
      } 
      return true;
    }
    
    public int hashCode() {
      return this.mHashCode;
    }
    
    public void setTo(ThemeKey param1ThemeKey) {
      int[] arrayOfInt = param1ThemeKey.mResId;
      boolean[] arrayOfBoolean2 = null;
      if (arrayOfInt == null) {
        arrayOfInt = null;
      } else {
        arrayOfInt = (int[])arrayOfInt.clone();
      } 
      this.mResId = arrayOfInt;
      boolean[] arrayOfBoolean1 = param1ThemeKey.mForce;
      if (arrayOfBoolean1 == null) {
        arrayOfBoolean1 = arrayOfBoolean2;
      } else {
        arrayOfBoolean1 = (boolean[])arrayOfBoolean1.clone();
      } 
      this.mForce = arrayOfBoolean1;
      this.mCount = param1ThemeKey.mCount;
      this.mHashCode = param1ThemeKey.mHashCode;
    }
  }
  
  public static interface UpdateCallbacks extends ResourcesLoader.UpdateCallbacks {
    void onLoadersChanged(Resources param1Resources, List<ResourcesLoader> param1List);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/Resources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */