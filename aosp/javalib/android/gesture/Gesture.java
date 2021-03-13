package android.gesture;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Gesture implements Parcelable {
  private static final boolean BITMAP_RENDERING_ANTIALIAS = true;
  
  private static final boolean BITMAP_RENDERING_DITHER = true;
  
  private static final int BITMAP_RENDERING_WIDTH = 2;
  
  public static final Parcelable.Creator<Gesture> CREATOR;
  
  private static final long GESTURE_ID_BASE = System.currentTimeMillis();
  
  private static final AtomicInteger sGestureCount = new AtomicInteger(0);
  
  private final RectF mBoundingBox = new RectF();
  
  private long mGestureID = GESTURE_ID_BASE + sGestureCount.incrementAndGet();
  
  private final ArrayList<GestureStroke> mStrokes = new ArrayList<>();
  
  static {
    CREATOR = new Parcelable.Creator<Gesture>() {
        public Gesture createFromParcel(Parcel param1Parcel) {
          IOException iOException = null;
          long l = param1Parcel.readLong();
          DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(param1Parcel.createByteArray()));
          try {
            Gesture gesture = Gesture.deserialize(dataInputStream);
            GestureUtils.closeStream(dataInputStream);
          } catch (IOException iOException1) {
            Log.e("Gestures", "Error reading Gesture from parcel:", iOException1);
            iOException1 = iOException;
            GestureUtils.closeStream(dataInputStream);
          } finally {}
          if (param1Parcel != null)
            Gesture.access$002((Gesture)param1Parcel, l); 
          return (Gesture)param1Parcel;
        }
        
        public Gesture[] newArray(int param1Int) {
          return new Gesture[param1Int];
        }
      };
  }
  
  static Gesture deserialize(DataInputStream paramDataInputStream) throws IOException {
    Gesture gesture = new Gesture();
    gesture.mGestureID = paramDataInputStream.readLong();
    int i = paramDataInputStream.readInt();
    for (byte b = 0; b < i; b++)
      gesture.addStroke(GestureStroke.deserialize(paramDataInputStream)); 
    return gesture;
  }
  
  public void addStroke(GestureStroke paramGestureStroke) {
    this.mStrokes.add(paramGestureStroke);
    this.mBoundingBox.union(paramGestureStroke.boundingBox);
  }
  
  public Object clone() {
    Gesture gesture = new Gesture();
    gesture.mBoundingBox.set(this.mBoundingBox.left, this.mBoundingBox.top, this.mBoundingBox.right, this.mBoundingBox.bottom);
    int i = this.mStrokes.size();
    for (byte b = 0; b < i; b++) {
      GestureStroke gestureStroke = this.mStrokes.get(b);
      gesture.mStrokes.add((GestureStroke)gestureStroke.clone());
    } 
    return gesture;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public RectF getBoundingBox() {
    return this.mBoundingBox;
  }
  
  public long getID() {
    return this.mGestureID;
  }
  
  public float getLength() {
    int i = 0;
    ArrayList<GestureStroke> arrayList = this.mStrokes;
    int j = arrayList.size();
    for (byte b = 0; b < j; b++)
      i = (int)(i + ((GestureStroke)arrayList.get(b)).length); 
    return i;
  }
  
  public ArrayList<GestureStroke> getStrokes() {
    return this.mStrokes;
  }
  
  public int getStrokesCount() {
    return this.mStrokes.size();
  }
  
  void serialize(DataOutputStream paramDataOutputStream) throws IOException {
    ArrayList<GestureStroke> arrayList = this.mStrokes;
    int i = arrayList.size();
    paramDataOutputStream.writeLong(this.mGestureID);
    paramDataOutputStream.writeInt(i);
    for (byte b = 0; b < i; b++)
      ((GestureStroke)arrayList.get(b)).serialize(paramDataOutputStream); 
  }
  
  void setID(long paramLong) {
    this.mGestureID = paramLong;
  }
  
  public Bitmap toBitmap(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Bitmap bitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setDither(true);
    paint.setColor(paramInt4);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeJoin(Paint.Join.ROUND);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeWidth(2.0F);
    Path path = toPath();
    RectF rectF = new RectF();
    path.computeBounds(rectF, true);
    float f1 = (paramInt1 - paramInt3 * 2) / rectF.width();
    float f2 = (paramInt2 - paramInt3 * 2) / rectF.height();
    if (f1 > f2)
      f1 = f2; 
    paint.setStrokeWidth(2.0F / f1);
    path.offset(-rectF.left + (paramInt1 - rectF.width() * f1) / 2.0F, -rectF.top + (paramInt2 - rectF.height() * f1) / 2.0F);
    canvas.translate(paramInt3, paramInt3);
    canvas.scale(f1, f1);
    canvas.drawPath(path, paint);
    return bitmap;
  }
  
  public Bitmap toBitmap(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    Bitmap bitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.translate(paramInt3, paramInt3);
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setDither(true);
    paint.setColor(paramInt5);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeJoin(Paint.Join.ROUND);
    paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeWidth(2.0F);
    ArrayList<GestureStroke> arrayList = this.mStrokes;
    int i = arrayList.size();
    for (paramInt5 = 0; paramInt5 < i; paramInt5++)
      canvas.drawPath(((GestureStroke)arrayList.get(paramInt5)).toPath((paramInt1 - paramInt3 * 2), (paramInt2 - paramInt3 * 2), paramInt4), paint); 
    return bitmap;
  }
  
  public Path toPath() {
    return toPath(null);
  }
  
  public Path toPath(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return toPath(null, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public Path toPath(Path paramPath) {
    Path path = paramPath;
    if (paramPath == null)
      path = new Path(); 
    ArrayList<GestureStroke> arrayList = this.mStrokes;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      path.addPath(((GestureStroke)arrayList.get(b)).getPath()); 
    return path;
  }
  
  public Path toPath(Path paramPath, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Path path = paramPath;
    if (paramPath == null)
      path = new Path(); 
    ArrayList<GestureStroke> arrayList = this.mStrokes;
    int i = arrayList.size();
    for (byte b = 0; b < i; b++)
      path.addPath(((GestureStroke)arrayList.get(b)).toPath((paramInt1 - paramInt3 * 2), (paramInt2 - paramInt3 * 2), paramInt4)); 
    return path;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mGestureID);
    paramInt = 0;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    try {
      serialize(dataOutputStream);
      paramInt = 1;
      GestureUtils.closeStream(dataOutputStream);
      GestureUtils.closeStream(byteArrayOutputStream);
    } catch (IOException iOException) {
      Log.e("Gestures", "Error writing Gesture to parcel:", iOException);
      GestureUtils.closeStream(dataOutputStream);
      GestureUtils.closeStream(byteArrayOutputStream);
    } finally {}
    if (paramInt != 0)
      paramParcel.writeByteArray(byteArrayOutputStream.toByteArray()); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/Gesture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */