package android.gesture;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GestureStroke {
  static final float TOUCH_TOLERANCE = 3.0F;
  
  public final RectF boundingBox;
  
  public final float length;
  
  private Path mCachedPath;
  
  public final float[] points;
  
  private final long[] timestamps;
  
  private GestureStroke(RectF paramRectF, float paramFloat, float[] paramArrayOffloat, long[] paramArrayOflong) {
    this.boundingBox = new RectF(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.bottom);
    this.length = paramFloat;
    this.points = (float[])paramArrayOffloat.clone();
    this.timestamps = (long[])paramArrayOflong.clone();
  }
  
  public GestureStroke(ArrayList<GesturePoint> paramArrayList) {
    int i = paramArrayList.size();
    float[] arrayOfFloat = new float[i * 2];
    long[] arrayOfLong = new long[i];
    RectF rectF = null;
    float f = 0.0F;
    byte b1 = 0;
    for (byte b2 = 0; b2 < i; b2++) {
      GesturePoint gesturePoint = paramArrayList.get(b2);
      arrayOfFloat[b2 * 2] = gesturePoint.x;
      arrayOfFloat[b2 * 2 + 1] = gesturePoint.y;
      arrayOfLong[b1] = gesturePoint.timestamp;
      if (rectF == null) {
        rectF = new RectF();
        rectF.top = gesturePoint.y;
        rectF.left = gesturePoint.x;
        rectF.right = gesturePoint.x;
        rectF.bottom = gesturePoint.y;
        f = 0.0F;
      } else {
        f = (float)(f + Math.hypot((gesturePoint.x - arrayOfFloat[(b2 - 1) * 2]), (gesturePoint.y - arrayOfFloat[(b2 - 1) * 2 + 1])));
        rectF.union(gesturePoint.x, gesturePoint.y);
      } 
      b1++;
    } 
    this.timestamps = arrayOfLong;
    this.points = arrayOfFloat;
    this.boundingBox = rectF;
    this.length = f;
  }
  
  static GestureStroke deserialize(DataInputStream paramDataInputStream) throws IOException {
    int i = paramDataInputStream.readInt();
    ArrayList<GesturePoint> arrayList = new ArrayList(i);
    for (byte b = 0; b < i; b++)
      arrayList.add(GesturePoint.deserialize(paramDataInputStream)); 
    return new GestureStroke(arrayList);
  }
  
  private void makePath() {
    // Byte code:
    //   0: aload_0
    //   1: getfield points : [F
    //   4: astore_1
    //   5: aload_1
    //   6: arraylength
    //   7: istore_2
    //   8: aconst_null
    //   9: astore_3
    //   10: fconst_0
    //   11: fstore #4
    //   13: fconst_0
    //   14: fstore #5
    //   16: iconst_0
    //   17: istore #6
    //   19: iload #6
    //   21: iload_2
    //   22: if_icmpge -> 169
    //   25: aload_1
    //   26: iload #6
    //   28: faload
    //   29: fstore #7
    //   31: aload_1
    //   32: iload #6
    //   34: iconst_1
    //   35: iadd
    //   36: faload
    //   37: fstore #8
    //   39: aload_3
    //   40: ifnonnull -> 72
    //   43: new android/graphics/Path
    //   46: dup
    //   47: invokespecial <init> : ()V
    //   50: astore #9
    //   52: aload #9
    //   54: fload #7
    //   56: fload #8
    //   58: invokevirtual moveTo : (FF)V
    //   61: fload #7
    //   63: fstore #10
    //   65: fload #8
    //   67: fstore #11
    //   69: goto -> 152
    //   72: fload #7
    //   74: fload #4
    //   76: fsub
    //   77: invokestatic abs : (F)F
    //   80: fstore #11
    //   82: fload #8
    //   84: fload #5
    //   86: fsub
    //   87: invokestatic abs : (F)F
    //   90: fstore #12
    //   92: fload #11
    //   94: ldc 3.0
    //   96: fcmpl
    //   97: ifge -> 119
    //   100: aload_3
    //   101: astore #9
    //   103: fload #4
    //   105: fstore #10
    //   107: fload #5
    //   109: fstore #11
    //   111: fload #12
    //   113: ldc 3.0
    //   115: fcmpl
    //   116: iflt -> 152
    //   119: aload_3
    //   120: fload #4
    //   122: fload #5
    //   124: fload #7
    //   126: fload #4
    //   128: fadd
    //   129: fconst_2
    //   130: fdiv
    //   131: fload #8
    //   133: fload #5
    //   135: fadd
    //   136: fconst_2
    //   137: fdiv
    //   138: invokevirtual quadTo : (FFFF)V
    //   141: fload #7
    //   143: fstore #10
    //   145: fload #8
    //   147: fstore #11
    //   149: aload_3
    //   150: astore #9
    //   152: iinc #6, 2
    //   155: aload #9
    //   157: astore_3
    //   158: fload #10
    //   160: fstore #4
    //   162: fload #11
    //   164: fstore #5
    //   166: goto -> 19
    //   169: aload_0
    //   170: aload_3
    //   171: putfield mCachedPath : Landroid/graphics/Path;
    //   174: return
  }
  
  public void clearPath() {
    Path path = this.mCachedPath;
    if (path != null)
      path.rewind(); 
  }
  
  public Object clone() {
    return new GestureStroke(this.boundingBox, this.length, this.points, this.timestamps);
  }
  
  public OrientedBoundingBox computeOrientedBoundingBox() {
    return GestureUtils.computeOrientedBoundingBox(this.points);
  }
  
  void draw(Canvas paramCanvas, Paint paramPaint) {
    if (this.mCachedPath == null)
      makePath(); 
    paramCanvas.drawPath(this.mCachedPath, paramPaint);
  }
  
  public Path getPath() {
    if (this.mCachedPath == null)
      makePath(); 
    return this.mCachedPath;
  }
  
  void serialize(DataOutputStream paramDataOutputStream) throws IOException {
    float[] arrayOfFloat = this.points;
    long[] arrayOfLong = this.timestamps;
    int i = this.points.length;
    paramDataOutputStream.writeInt(i / 2);
    for (byte b = 0; b < i; b += 2) {
      paramDataOutputStream.writeFloat(arrayOfFloat[b]);
      paramDataOutputStream.writeFloat(arrayOfFloat[b + 1]);
      paramDataOutputStream.writeLong(arrayOfLong[b / 2]);
    } 
  }
  
  public Path toPath(float paramFloat1, float paramFloat2, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: iload_3
    //   2: invokestatic temporalSampling : (Landroid/gesture/GestureStroke;I)[F
    //   5: astore #4
    //   7: aload_0
    //   8: getfield boundingBox : Landroid/graphics/RectF;
    //   11: astore #5
    //   13: aload #4
    //   15: aload #5
    //   17: getfield left : F
    //   20: fneg
    //   21: aload #5
    //   23: getfield top : F
    //   26: fneg
    //   27: invokestatic translate : ([FFF)[F
    //   30: pop
    //   31: fload_1
    //   32: aload #5
    //   34: invokevirtual width : ()F
    //   37: fdiv
    //   38: fstore_1
    //   39: fload_2
    //   40: aload #5
    //   42: invokevirtual height : ()F
    //   45: fdiv
    //   46: fstore_2
    //   47: fload_1
    //   48: fload_2
    //   49: fcmpl
    //   50: ifle -> 58
    //   53: fload_2
    //   54: fstore_1
    //   55: goto -> 58
    //   58: aload #4
    //   60: fload_1
    //   61: fload_1
    //   62: invokestatic scale : ([FFF)[F
    //   65: pop
    //   66: fconst_0
    //   67: fstore #6
    //   69: fconst_0
    //   70: fstore #7
    //   72: aconst_null
    //   73: astore #8
    //   75: aload #4
    //   77: arraylength
    //   78: istore #9
    //   80: iconst_0
    //   81: istore_3
    //   82: iload_3
    //   83: iload #9
    //   85: if_icmpge -> 227
    //   88: aload #4
    //   90: iload_3
    //   91: faload
    //   92: fstore #10
    //   94: aload #4
    //   96: iload_3
    //   97: iconst_1
    //   98: iadd
    //   99: faload
    //   100: fstore #11
    //   102: aload #8
    //   104: ifnonnull -> 134
    //   107: new android/graphics/Path
    //   110: dup
    //   111: invokespecial <init> : ()V
    //   114: astore #5
    //   116: aload #5
    //   118: fload #10
    //   120: fload #11
    //   122: invokevirtual moveTo : (FF)V
    //   125: fload #10
    //   127: fstore_1
    //   128: fload #11
    //   130: fstore_2
    //   131: goto -> 211
    //   134: fload #10
    //   136: fload #6
    //   138: fsub
    //   139: invokestatic abs : (F)F
    //   142: fstore_1
    //   143: fload #11
    //   145: fload #7
    //   147: fsub
    //   148: invokestatic abs : (F)F
    //   151: fstore #12
    //   153: fload_1
    //   154: ldc 3.0
    //   156: fcmpl
    //   157: ifge -> 178
    //   160: fload #6
    //   162: fstore_1
    //   163: fload #7
    //   165: fstore_2
    //   166: aload #8
    //   168: astore #5
    //   170: fload #12
    //   172: ldc 3.0
    //   174: fcmpl
    //   175: iflt -> 211
    //   178: aload #8
    //   180: fload #6
    //   182: fload #7
    //   184: fload #10
    //   186: fload #6
    //   188: fadd
    //   189: fconst_2
    //   190: fdiv
    //   191: fload #11
    //   193: fload #7
    //   195: fadd
    //   196: fconst_2
    //   197: fdiv
    //   198: invokevirtual quadTo : (FFFF)V
    //   201: fload #10
    //   203: fstore_1
    //   204: fload #11
    //   206: fstore_2
    //   207: aload #8
    //   209: astore #5
    //   211: iinc #3, 2
    //   214: fload_1
    //   215: fstore #6
    //   217: fload_2
    //   218: fstore #7
    //   220: aload #5
    //   222: astore #8
    //   224: goto -> 82
    //   227: aload #8
    //   229: areturn
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gesture/GestureStroke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */