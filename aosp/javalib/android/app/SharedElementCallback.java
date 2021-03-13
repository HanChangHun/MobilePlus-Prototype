package android.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.HardwareBuffer;
import android.os.Bundle;
import android.os.Parcelable;
import android.transition.TransitionUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
  private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
  
  private static final String BUNDLE_SNAPSHOT_COLOR_SPACE = "sharedElement:snapshot:colorSpace";
  
  private static final String BUNDLE_SNAPSHOT_HARDWARE_BUFFER = "sharedElement:snapshot:hardwareBuffer";
  
  private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
  
  private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
  
  static final SharedElementCallback NULL_CALLBACK = new SharedElementCallback() {
    
    };
  
  private Matrix mTempMatrix;
  
  public Parcelable onCaptureSharedElementSnapshot(View paramView, Matrix paramMatrix, RectF paramRectF) {
    Bundle bundle;
    float[] arrayOfFloat;
    if (paramView instanceof ImageView) {
      ImageView imageView = (ImageView)paramView;
      Drawable drawable1 = imageView.getDrawable();
      Drawable drawable2 = imageView.getBackground();
      if (drawable1 != null && (drawable2 == null || drawable2.getAlpha() == 0)) {
        Bitmap bitmap = TransitionUtils.createDrawableBitmap(drawable1, (View)imageView);
        if (bitmap != null) {
          bundle = new Bundle();
          if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
            bundle.putParcelable("sharedElement:snapshot:bitmap", (Parcelable)bitmap);
          } else {
            bundle.putParcelable("sharedElement:snapshot:hardwareBuffer", (Parcelable)bitmap.getHardwareBuffer());
            ColorSpace colorSpace = bitmap.getColorSpace();
            if (colorSpace != null)
              bundle.putInt("sharedElement:snapshot:colorSpace", colorSpace.getId()); 
          } 
          bundle.putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
          if (imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            paramMatrix = imageView.getImageMatrix();
            arrayOfFloat = new float[9];
            paramMatrix.getValues(arrayOfFloat);
            bundle.putFloatArray("sharedElement:snapshot:imageMatrix", arrayOfFloat);
          } 
          return (Parcelable)bundle;
        } 
      } 
    } 
    Matrix matrix = this.mTempMatrix;
    if (matrix == null) {
      this.mTempMatrix = new Matrix(paramMatrix);
    } else {
      matrix.set(paramMatrix);
    } 
    ViewGroup viewGroup = (ViewGroup)bundle.getParent();
    return (Parcelable)TransitionUtils.createViewBitmap((View)bundle, this.mTempMatrix, (RectF)arrayOfFloat, viewGroup);
  }
  
  public View onCreateSnapshotView(Context paramContext, Parcelable paramParcelable) {
    ImageView imageView;
    float[] arrayOfFloat;
    View view;
    Bitmap bitmap = null;
    if (paramParcelable instanceof Bundle) {
      Bundle bundle = (Bundle)paramParcelable;
      HardwareBuffer hardwareBuffer = (HardwareBuffer)bundle.getParcelable("sharedElement:snapshot:hardwareBuffer");
      bitmap = (Bitmap)bundle.getParcelable("sharedElement:snapshot:bitmap");
      if (hardwareBuffer == null && bitmap == null)
        return null; 
      Bitmap bitmap1 = bitmap;
      if (bitmap == null) {
        ColorSpace colorSpace;
        bitmap = null;
        int i = bundle.getInt("sharedElement:snapshot:colorSpace", 0);
        bitmap1 = bitmap;
        if (i >= 0) {
          bitmap1 = bitmap;
          if (i < (ColorSpace.Named.values()).length)
            colorSpace = ColorSpace.get(ColorSpace.Named.values()[i]); 
        } 
        bitmap1 = Bitmap.wrapHardwareBuffer(hardwareBuffer, colorSpace);
      } 
      ImageView imageView2 = new ImageView(paramContext);
      imageView = imageView2;
      imageView2.setImageBitmap(bitmap1);
      imageView2.setScaleType(ImageView.ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
      ImageView imageView1 = imageView;
      if (imageView2.getScaleType() == ImageView.ScaleType.MATRIX) {
        arrayOfFloat = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
        Matrix matrix = new Matrix();
        matrix.setValues(arrayOfFloat);
        imageView2.setImageMatrix(matrix);
        ImageView imageView3 = imageView;
      } 
    } else if (arrayOfFloat instanceof Bitmap) {
      Bitmap bitmap1 = (Bitmap)arrayOfFloat;
      view = new View((Context)imageView);
      view.setBackground((Drawable)new BitmapDrawable(imageView.getResources(), bitmap1));
    } 
    return view;
  }
  
  public void onMapSharedElements(List<String> paramList, Map<String, View> paramMap) {}
  
  public void onRejectSharedElements(List<View> paramList) {}
  
  public void onSharedElementEnd(List<String> paramList, List<View> paramList1, List<View> paramList2) {}
  
  public void onSharedElementStart(List<String> paramList, List<View> paramList1, List<View> paramList2) {}
  
  public void onSharedElementsArrived(List<String> paramList, List<View> paramList1, OnSharedElementsReadyListener paramOnSharedElementsReadyListener) {
    paramOnSharedElementsReadyListener.onSharedElementsReady();
  }
  
  public static interface OnSharedElementsReadyListener {
    void onSharedElementsReady();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SharedElementCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */