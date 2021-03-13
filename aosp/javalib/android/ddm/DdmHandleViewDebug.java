package android.ddm;

import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.view.WindowManagerGlobal;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;

public class DdmHandleViewDebug extends ChunkHandler {
  private static final int CHUNK_VULW = type("VULW");
  
  private static final int CHUNK_VUOP;
  
  private static final int CHUNK_VURT = type("VURT");
  
  private static final int ERR_EXCEPTION = -3;
  
  private static final int ERR_INVALID_OP = -1;
  
  private static final int ERR_INVALID_PARAM = -2;
  
  private static final String TAG = "DdmViewDebug";
  
  private static final int VUOP_CAPTURE_VIEW = 1;
  
  private static final int VUOP_DUMP_DISPLAYLIST = 2;
  
  private static final int VUOP_INVOKE_VIEW_METHOD = 4;
  
  private static final int VUOP_PROFILE_VIEW = 3;
  
  private static final int VUOP_SET_LAYOUT_PARAMETER = 5;
  
  private static final int VURT_CAPTURE_LAYERS = 2;
  
  private static final int VURT_DUMP_HIERARCHY = 1;
  
  private static final int VURT_DUMP_THEME = 3;
  
  private static final DdmHandleViewDebug sInstance;
  
  static {
    CHUNK_VUOP = type("VUOP");
    sInstance = new DdmHandleViewDebug();
  }
  
  private Chunk captureLayers(View paramView) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    try {
      ViewDebug.captureLayers(paramView, dataOutputStream);
      try {
        dataOutputStream.close();
      } catch (IOException iOException1) {}
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      return new Chunk(CHUNK_VURT, arrayOfByte, 0, arrayOfByte.length);
    } catch (IOException iOException1) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Unexpected error while obtaining view hierarchy: ");
      stringBuilder.append(iOException1.getMessage());
      Chunk chunk = createFailChunk(1, stringBuilder.toString());
      try {
        dataOutputStream.close();
      } catch (IOException iOException) {}
      return chunk;
    } finally {}
    try {
      iOException.close();
    } catch (IOException iOException1) {}
    throw paramView;
  }
  
  private Chunk captureView(View paramView1, View paramView2) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
    try {
      ViewDebug.capture(paramView1, byteArrayOutputStream, paramView2);
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      return new Chunk(CHUNK_VUOP, arrayOfByte, 0, arrayOfByte.length);
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected error while capturing view: ");
      stringBuilder.append(iOException.getMessage());
      return createFailChunk(1, stringBuilder.toString());
    } 
  }
  
  private Chunk dumpDisplayLists(final View rootView, final View targetView) {
    rootView.post(new Runnable() {
          public void run() {
            ViewDebug.outputDisplayList(rootView, targetView);
          }
        });
    return null;
  }
  
  private Chunk dumpHierarchy(View paramView, ByteBuffer paramByteBuffer) {
    boolean bool1;
    boolean bool2;
    boolean bool3;
    if (paramByteBuffer.getInt() > 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramByteBuffer.getInt() > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (paramByteBuffer.hasRemaining() && paramByteBuffer.getInt() > 0) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    long l1 = System.currentTimeMillis();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2097152);
    if (bool3)
      try {
        ViewDebug.dumpv2(paramView, byteArrayOutputStream);
        long l = System.currentTimeMillis();
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Time to obtain view hierarchy (ms): ");
        stringBuilder1.append(l - l1);
        Log.d("DdmViewDebug", stringBuilder1.toString());
        byte[] arrayOfByte1 = byteArrayOutputStream.toByteArray();
        return new Chunk(CHUNK_VURT, arrayOfByte1, 0, arrayOfByte1.length);
      } catch (IOException|InterruptedException iOException) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected error while obtaining view hierarchy: ");
        stringBuilder.append(iOException.getMessage());
        return createFailChunk(1, stringBuilder.toString());
      }  
    ViewDebug.dump((View)stringBuilder, bool1, bool2, (OutputStream)iOException);
    long l2 = System.currentTimeMillis();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Time to obtain view hierarchy (ms): ");
    stringBuilder.append(l2 - l1);
    Log.d("DdmViewDebug", stringBuilder.toString());
    byte[] arrayOfByte = iOException.toByteArray();
    return new Chunk(CHUNK_VURT, arrayOfByte, 0, arrayOfByte.length);
  }
  
  private Chunk dumpTheme(View paramView) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
    try {
      ViewDebug.dumpTheme(paramView, byteArrayOutputStream);
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      return new Chunk(CHUNK_VURT, arrayOfByte, 0, arrayOfByte.length);
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected error while dumping the theme: ");
      stringBuilder.append(iOException.getMessage());
      return createFailChunk(1, stringBuilder.toString());
    } 
  }
  
  private View getRootView(ByteBuffer paramByteBuffer) {
    try {
      String str = getString(paramByteBuffer, paramByteBuffer.getInt());
      return WindowManagerGlobal.getInstance().getRootView(str);
    } catch (BufferUnderflowException bufferUnderflowException) {
      return null;
    } 
  }
  
  private View getTargetView(View paramView, ByteBuffer paramByteBuffer) {
    try {
      String str = getString(paramByteBuffer, paramByteBuffer.getInt());
      return ViewDebug.findView(paramView, str);
    } catch (BufferUnderflowException bufferUnderflowException) {
      return null;
    } 
  }
  
  private Chunk invokeViewMethod(View paramView1, View paramView2, ByteBuffer paramByteBuffer) {
    StringBuilder stringBuilder;
    Object[] arrayOfObject;
    String str = getString(paramByteBuffer, paramByteBuffer.getInt());
    if (!paramByteBuffer.hasRemaining()) {
      Class[] arrayOfClass = new Class[0];
      arrayOfObject = new Object[0];
    } else {
      int i = arrayOfObject.getInt();
      Class[] arrayOfClass = new Class[i];
      Object[] arrayOfObject1 = new Object[i];
      for (byte b = 0; b < i; b++) {
        char c = arrayOfObject.getChar();
        if (c != 'F') {
          if (c != 'S') {
            if (c != 'Z') {
              if (c != 'I') {
                if (c != 'J') {
                  switch (c) {
                    default:
                      stringBuilder = new StringBuilder();
                      stringBuilder.append("arg ");
                      stringBuilder.append(b);
                      stringBuilder.append(", unrecognized type: ");
                      stringBuilder.append(c);
                      Log.e("DdmViewDebug", stringBuilder.toString());
                      stringBuilder = new StringBuilder();
                      stringBuilder.append("Unsupported parameter type (");
                      stringBuilder.append(c);
                      stringBuilder.append(") to invoke view method.");
                      return createFailChunk(-2, stringBuilder.toString());
                    case 'D':
                      stringBuilder[b] = (StringBuilder)double.class;
                      arrayOfObject1[b] = Double.valueOf(arrayOfObject.getDouble());
                      break;
                    case 'C':
                      stringBuilder[b] = (StringBuilder)char.class;
                      arrayOfObject1[b] = Character.valueOf(arrayOfObject.getChar());
                      break;
                    case 'B':
                      stringBuilder[b] = (StringBuilder)byte.class;
                      arrayOfObject1[b] = Byte.valueOf(arrayOfObject.get());
                      break;
                  } 
                } else {
                  stringBuilder[b] = (StringBuilder)long.class;
                  arrayOfObject1[b] = Long.valueOf(arrayOfObject.getLong());
                } 
              } else {
                stringBuilder[b] = (StringBuilder)int.class;
                arrayOfObject1[b] = Integer.valueOf(arrayOfObject.getInt());
              } 
            } else {
              boolean bool;
              stringBuilder[b] = (StringBuilder)boolean.class;
              if (arrayOfObject.get() == 0) {
                bool = false;
              } else {
                bool = true;
              } 
              arrayOfObject1[b] = Boolean.valueOf(bool);
            } 
          } else {
            stringBuilder[b] = (StringBuilder)short.class;
            arrayOfObject1[b] = Short.valueOf(arrayOfObject.getShort());
          } 
        } else {
          stringBuilder[b] = (StringBuilder)float.class;
          arrayOfObject1[b] = Float.valueOf(arrayOfObject.getFloat());
        } 
      } 
      arrayOfObject = arrayOfObject1;
    } 
    try {
      Method method = paramView2.getClass().getMethod(str, (Class<?>[])stringBuilder);
      try {
        ViewDebug.invokeViewMethod(paramView2, method, arrayOfObject);
        return null;
      } catch (Exception exception) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Exception while invoking method: ");
        stringBuilder1.append(exception.getCause().getMessage());
        Log.e("DdmViewDebug", stringBuilder1.toString());
        String str2 = exception.getCause().getMessage();
        String str1 = str2;
        if (str2 == null)
          str1 = exception.getCause().toString(); 
        return createFailChunk(-3, str1);
      } 
    } catch (NoSuchMethodException noSuchMethodException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("No such method: ");
      stringBuilder1.append(noSuchMethodException.getMessage());
      Log.e("DdmViewDebug", stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("No such method: ");
      stringBuilder1.append(noSuchMethodException.getMessage());
      return createFailChunk(-2, stringBuilder1.toString());
    } 
  }
  
  private Chunk listWindows() {
    String[] arrayOfString = WindowManagerGlobal.getInstance().getViewRootNames();
    int i = 4;
    int j = arrayOfString.length;
    boolean bool = false;
    byte b;
    for (b = 0; b < j; b++)
      i = i + 4 + arrayOfString[b].length() * 2; 
    ByteBuffer byteBuffer = ByteBuffer.allocate(i);
    byteBuffer.order(ChunkHandler.CHUNK_ORDER);
    byteBuffer.putInt(arrayOfString.length);
    i = arrayOfString.length;
    for (b = bool; b < i; b++) {
      String str = arrayOfString[b];
      byteBuffer.putInt(str.length());
      putString(byteBuffer, str);
    } 
    return new Chunk(CHUNK_VULW, byteBuffer);
  }
  
  private Chunk profileView(View paramView1, View paramView2) {
    byte[] arrayOfByte;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream), 32768);
    try {
      ViewDebug.profileViewAndChildren(paramView2, bufferedWriter);
      try {
        bufferedWriter.close();
      } catch (IOException null) {}
      arrayOfByte = byteArrayOutputStream.toByteArray();
      return new Chunk(CHUNK_VUOP, arrayOfByte, 0, arrayOfByte.length);
    } catch (IOException iOException1) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Unexpected error while profiling view: ");
      stringBuilder.append(iOException1.getMessage());
      Chunk chunk = createFailChunk(1, stringBuilder.toString());
      try {
        arrayOfByte.close();
      } catch (IOException iOException) {}
      return chunk;
    } finally {}
    try {
      iOException.close();
    } catch (IOException iOException1) {}
    throw paramView2;
  }
  
  public static void register() {
    DdmServer.registerHandler(CHUNK_VULW, sInstance);
    DdmServer.registerHandler(CHUNK_VURT, sInstance);
    DdmServer.registerHandler(CHUNK_VUOP, sInstance);
  }
  
  private Chunk setLayoutParameter(View paramView1, View paramView2, ByteBuffer paramByteBuffer) {
    String str = getString(paramByteBuffer, paramByteBuffer.getInt());
    int i = paramByteBuffer.getInt();
    try {
      ViewDebug.setLayoutParameter(paramView2, str, i);
      return null;
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Exception setting layout parameter: ");
      stringBuilder.append(exception);
      Log.e("DdmViewDebug", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append("Error accessing field ");
      stringBuilder.append(str);
      stringBuilder.append(":");
      stringBuilder.append(exception.getMessage());
      return createFailChunk(-3, stringBuilder.toString());
    } 
  }
  
  public void connected() {}
  
  public void disconnected() {}
  
  public Chunk handleChunk(Chunk paramChunk) {
    int i = paramChunk.type;
    if (i == CHUNK_VULW)
      return listWindows(); 
    ByteBuffer byteBuffer = wrapChunk(paramChunk);
    int j = byteBuffer.getInt();
    View view2 = getRootView(byteBuffer);
    if (view2 == null)
      return createFailChunk(-2, "Invalid View Root"); 
    if (i == CHUNK_VURT) {
      if (j == 1)
        return dumpHierarchy(view2, byteBuffer); 
      if (j == 2)
        return captureLayers(view2); 
      if (j == 3)
        return dumpTheme(view2); 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unknown view root operation: ");
      stringBuilder1.append(j);
      return createFailChunk(-1, stringBuilder1.toString());
    } 
    View view1 = getTargetView(view2, byteBuffer);
    if (view1 == null)
      return createFailChunk(-2, "Invalid target view"); 
    if (i == CHUNK_VUOP) {
      StringBuilder stringBuilder1;
      if (j != 1) {
        if (j != 2) {
          if (j != 3) {
            if (j != 4) {
              if (j != 5) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Unknown view operation: ");
                stringBuilder1.append(j);
                return createFailChunk(-1, stringBuilder1.toString());
              } 
              return setLayoutParameter(view2, (View)stringBuilder1, byteBuffer);
            } 
            return invokeViewMethod(view2, (View)stringBuilder1, byteBuffer);
          } 
          return profileView(view2, (View)stringBuilder1);
        } 
        return dumpDisplayLists(view2, (View)stringBuilder1);
      } 
      return captureView(view2, (View)stringBuilder1);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown packet ");
    stringBuilder.append(ChunkHandler.name(i));
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleViewDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */