package android.app.slice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SliceQuery {
  private static final String TAG = "SliceQuery";
  
  public static boolean compareTypes(SliceItem paramSliceItem, String paramString) {
    if (paramString.length() == 3 && paramString.equals("*/*"))
      return true; 
    if (paramSliceItem.getSubType() == null && paramString.indexOf('/') < 0)
      return paramSliceItem.getFormat().equals(paramString); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramSliceItem.getFormat());
    stringBuilder.append("/");
    stringBuilder.append(paramSliceItem.getSubType());
    return stringBuilder.toString().matches(paramString.replaceAll("\\*", ".*"));
  }
  
  private static boolean contains(SliceItem paramSliceItem1, SliceItem paramSliceItem2) {
    return (paramSliceItem1 == null || paramSliceItem2 == null) ? false : stream(paramSliceItem1).filter(new _$$Lambda$SliceQuery$fdDPNErwIni_vCQ6k_MlGGBunoE(paramSliceItem2)).findAny().isPresent();
  }
  
  public static SliceItem find(Slice paramSlice, String paramString) {
    return find(paramSlice, paramString, (String[])null, (String[])null);
  }
  
  public static SliceItem find(Slice paramSlice, String paramString1, String paramString2, String paramString3) {
    return find(paramSlice, paramString1, new String[] { paramString2 }, new String[] { paramString3 });
  }
  
  public static SliceItem find(Slice paramSlice, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    List<String> list = paramSlice.getHints();
    return find(new SliceItem(paramSlice, "slice", null, list.<String>toArray(new String[list.size()])), paramString, paramArrayOfString1, paramArrayOfString2);
  }
  
  public static SliceItem find(SliceItem paramSliceItem, String paramString) {
    return find(paramSliceItem, paramString, (String[])null, (String[])null);
  }
  
  public static SliceItem find(SliceItem paramSliceItem, String paramString1, String paramString2, String paramString3) {
    return find(paramSliceItem, paramString1, new String[] { paramString2 }, new String[] { paramString3 });
  }
  
  public static SliceItem find(SliceItem paramSliceItem, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    return stream(paramSliceItem).filter(new _$$Lambda$SliceQuery$cG9kHpHpv4nbm7p3sCvlkQGlqQw(paramString, paramArrayOfString1, paramArrayOfString2)).findFirst().orElse(null);
  }
  
  public static List<SliceItem> findAll(SliceItem paramSliceItem, String paramString) {
    return findAll(paramSliceItem, paramString, (String[])null, (String[])null);
  }
  
  public static List<SliceItem> findAll(SliceItem paramSliceItem, String paramString1, String paramString2, String paramString3) {
    return findAll(paramSliceItem, paramString1, new String[] { paramString2 }, new String[] { paramString3 });
  }
  
  public static List<SliceItem> findAll(SliceItem paramSliceItem, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    return (List<SliceItem>)stream(paramSliceItem).filter(new _$$Lambda$SliceQuery$hLToAajdaMbaf8BUtZ8fpGK980E(paramString, paramArrayOfString1, paramArrayOfString2)).collect(Collectors.toList());
  }
  
  public static SliceItem findNotContaining(SliceItem paramSliceItem, List<SliceItem> paramList) {
    SliceItem sliceItem = null;
    while (sliceItem == null && paramList.size() != 0) {
      SliceItem sliceItem1 = paramList.remove(0);
      if (!contains(paramSliceItem, sliceItem1))
        sliceItem = sliceItem1; 
    } 
    return sliceItem;
  }
  
  public static SliceItem getPrimaryIcon(Slice paramSlice) {
    for (SliceItem sliceItem : paramSlice.getItems()) {
      if (Objects.equals(sliceItem.getFormat(), "image"))
        return sliceItem; 
      if ((!compareTypes(sliceItem, "slice") || !sliceItem.hasHint("list")) && !sliceItem.hasHint("actions") && !sliceItem.hasHint("list_item") && !compareTypes(sliceItem, "action")) {
        sliceItem = find(sliceItem, "image");
        if (sliceItem != null)
          return sliceItem; 
      } 
    } 
    return null;
  }
  
  public static Stream<SliceItem> stream(SliceItem paramSliceItem) {
    final LinkedList<SliceItem> items = new LinkedList();
    linkedList.add(paramSliceItem);
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<SliceItem>() {
            public boolean hasNext() {
              boolean bool;
              if (items.size() != 0) {
                bool = true;
              } else {
                bool = false;
              } 
              return bool;
            }
            
            public SliceItem next() {
              SliceItem sliceItem = items.poll();
              if (SliceQuery.compareTypes(sliceItem, "slice") || SliceQuery.compareTypes(sliceItem, "action"))
                items.addAll(sliceItem.getSlice().getItems()); 
              return sliceItem;
            }
          }0), false);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */