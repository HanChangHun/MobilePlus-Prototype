package android.hardware.radio.V1_1;

import java.util.ArrayList;

public final class EutranBands {
  public static final int BAND_1 = 1;
  
  public static final int BAND_10 = 10;
  
  public static final int BAND_11 = 11;
  
  public static final int BAND_12 = 12;
  
  public static final int BAND_13 = 13;
  
  public static final int BAND_14 = 14;
  
  public static final int BAND_17 = 17;
  
  public static final int BAND_18 = 18;
  
  public static final int BAND_19 = 19;
  
  public static final int BAND_2 = 2;
  
  public static final int BAND_20 = 20;
  
  public static final int BAND_21 = 21;
  
  public static final int BAND_22 = 22;
  
  public static final int BAND_23 = 23;
  
  public static final int BAND_24 = 24;
  
  public static final int BAND_25 = 25;
  
  public static final int BAND_26 = 26;
  
  public static final int BAND_27 = 27;
  
  public static final int BAND_28 = 28;
  
  public static final int BAND_3 = 3;
  
  public static final int BAND_30 = 30;
  
  public static final int BAND_31 = 31;
  
  public static final int BAND_33 = 33;
  
  public static final int BAND_34 = 34;
  
  public static final int BAND_35 = 35;
  
  public static final int BAND_36 = 36;
  
  public static final int BAND_37 = 37;
  
  public static final int BAND_38 = 38;
  
  public static final int BAND_39 = 39;
  
  public static final int BAND_4 = 4;
  
  public static final int BAND_40 = 40;
  
  public static final int BAND_41 = 41;
  
  public static final int BAND_42 = 42;
  
  public static final int BAND_43 = 43;
  
  public static final int BAND_44 = 44;
  
  public static final int BAND_45 = 45;
  
  public static final int BAND_46 = 46;
  
  public static final int BAND_47 = 47;
  
  public static final int BAND_48 = 48;
  
  public static final int BAND_5 = 5;
  
  public static final int BAND_6 = 6;
  
  public static final int BAND_65 = 65;
  
  public static final int BAND_66 = 66;
  
  public static final int BAND_68 = 68;
  
  public static final int BAND_7 = 7;
  
  public static final int BAND_70 = 70;
  
  public static final int BAND_8 = 8;
  
  public static final int BAND_9 = 9;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("BAND_1");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("BAND_2");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("BAND_3");
      i = j | 0x3;
    } 
    int k = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("BAND_4");
      k = i | 0x4;
    } 
    j = k;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("BAND_5");
      j = k | 0x5;
    } 
    i = j;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("BAND_6");
      i = j | 0x6;
    } 
    k = i;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("BAND_7");
      k = i | 0x7;
    } 
    j = k;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("BAND_8");
      j = k | 0x8;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("BAND_9");
      i = j | 0x9;
    } 
    j = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("BAND_10");
      j = i | 0xA;
    } 
    i = j;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("BAND_11");
      i = j | 0xB;
    } 
    j = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("BAND_12");
      j = i | 0xC;
    } 
    k = j;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("BAND_13");
      k = j | 0xD;
    } 
    i = k;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("BAND_14");
      i = k | 0xE;
    } 
    k = i;
    if ((paramInt & 0x11) == 17) {
      arrayList.add("BAND_17");
      k = i | 0x11;
    } 
    j = k;
    if ((paramInt & 0x12) == 18) {
      arrayList.add("BAND_18");
      j = k | 0x12;
    } 
    i = j;
    if ((paramInt & 0x13) == 19) {
      arrayList.add("BAND_19");
      i = j | 0x13;
    } 
    j = i;
    if ((paramInt & 0x14) == 20) {
      arrayList.add("BAND_20");
      j = i | 0x14;
    } 
    i = j;
    if ((paramInt & 0x15) == 21) {
      arrayList.add("BAND_21");
      i = j | 0x15;
    } 
    j = i;
    if ((paramInt & 0x16) == 22) {
      arrayList.add("BAND_22");
      j = i | 0x16;
    } 
    i = j;
    if ((paramInt & 0x17) == 23) {
      arrayList.add("BAND_23");
      i = j | 0x17;
    } 
    j = i;
    if ((paramInt & 0x18) == 24) {
      arrayList.add("BAND_24");
      j = i | 0x18;
    } 
    i = j;
    if ((paramInt & 0x19) == 25) {
      arrayList.add("BAND_25");
      i = j | 0x19;
    } 
    j = i;
    if ((paramInt & 0x1A) == 26) {
      arrayList.add("BAND_26");
      j = i | 0x1A;
    } 
    i = j;
    if ((paramInt & 0x1B) == 27) {
      arrayList.add("BAND_27");
      i = j | 0x1B;
    } 
    j = i;
    if ((paramInt & 0x1C) == 28) {
      arrayList.add("BAND_28");
      j = i | 0x1C;
    } 
    i = j;
    if ((paramInt & 0x1E) == 30) {
      arrayList.add("BAND_30");
      i = j | 0x1E;
    } 
    j = i;
    if ((paramInt & 0x1F) == 31) {
      arrayList.add("BAND_31");
      j = i | 0x1F;
    } 
    i = j;
    if ((paramInt & 0x21) == 33) {
      arrayList.add("BAND_33");
      i = j | 0x21;
    } 
    j = i;
    if ((paramInt & 0x22) == 34) {
      arrayList.add("BAND_34");
      j = i | 0x22;
    } 
    k = j;
    if ((paramInt & 0x23) == 35) {
      arrayList.add("BAND_35");
      k = j | 0x23;
    } 
    i = k;
    if ((paramInt & 0x24) == 36) {
      arrayList.add("BAND_36");
      i = k | 0x24;
    } 
    j = i;
    if ((paramInt & 0x25) == 37) {
      arrayList.add("BAND_37");
      j = i | 0x25;
    } 
    k = j;
    if ((paramInt & 0x26) == 38) {
      arrayList.add("BAND_38");
      k = j | 0x26;
    } 
    i = k;
    if ((paramInt & 0x27) == 39) {
      arrayList.add("BAND_39");
      i = k | 0x27;
    } 
    j = i;
    if ((paramInt & 0x28) == 40) {
      arrayList.add("BAND_40");
      j = i | 0x28;
    } 
    i = j;
    if ((paramInt & 0x29) == 41) {
      arrayList.add("BAND_41");
      i = j | 0x29;
    } 
    j = i;
    if ((paramInt & 0x2A) == 42) {
      arrayList.add("BAND_42");
      j = i | 0x2A;
    } 
    k = j;
    if ((paramInt & 0x2B) == 43) {
      arrayList.add("BAND_43");
      k = j | 0x2B;
    } 
    i = k;
    if ((paramInt & 0x2C) == 44) {
      arrayList.add("BAND_44");
      i = k | 0x2C;
    } 
    j = i;
    if ((paramInt & 0x2D) == 45) {
      arrayList.add("BAND_45");
      j = i | 0x2D;
    } 
    i = j;
    if ((paramInt & 0x2E) == 46) {
      arrayList.add("BAND_46");
      i = j | 0x2E;
    } 
    j = i;
    if ((paramInt & 0x2F) == 47) {
      arrayList.add("BAND_47");
      j = i | 0x2F;
    } 
    i = j;
    if ((paramInt & 0x30) == 48) {
      arrayList.add("BAND_48");
      i = j | 0x30;
    } 
    j = i;
    if ((paramInt & 0x41) == 65) {
      arrayList.add("BAND_65");
      j = i | 0x41;
    } 
    i = j;
    if ((paramInt & 0x42) == 66) {
      arrayList.add("BAND_66");
      i = j | 0x42;
    } 
    j = i;
    if ((paramInt & 0x44) == 68) {
      arrayList.add("BAND_68");
      j = i | 0x44;
    } 
    i = j;
    if ((paramInt & 0x46) == 70) {
      arrayList.add("BAND_70");
      i = j | 0x46;
    } 
    if (paramInt != i) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(i & paramInt));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(int paramInt) {
    if (paramInt == 1)
      return "BAND_1"; 
    if (paramInt == 2)
      return "BAND_2"; 
    if (paramInt == 3)
      return "BAND_3"; 
    if (paramInt == 4)
      return "BAND_4"; 
    if (paramInt == 5)
      return "BAND_5"; 
    if (paramInt == 6)
      return "BAND_6"; 
    if (paramInt == 7)
      return "BAND_7"; 
    if (paramInt == 8)
      return "BAND_8"; 
    if (paramInt == 9)
      return "BAND_9"; 
    if (paramInt == 10)
      return "BAND_10"; 
    if (paramInt == 11)
      return "BAND_11"; 
    if (paramInt == 12)
      return "BAND_12"; 
    if (paramInt == 13)
      return "BAND_13"; 
    if (paramInt == 14)
      return "BAND_14"; 
    if (paramInt == 17)
      return "BAND_17"; 
    if (paramInt == 18)
      return "BAND_18"; 
    if (paramInt == 19)
      return "BAND_19"; 
    if (paramInt == 20)
      return "BAND_20"; 
    if (paramInt == 21)
      return "BAND_21"; 
    if (paramInt == 22)
      return "BAND_22"; 
    if (paramInt == 23)
      return "BAND_23"; 
    if (paramInt == 24)
      return "BAND_24"; 
    if (paramInt == 25)
      return "BAND_25"; 
    if (paramInt == 26)
      return "BAND_26"; 
    if (paramInt == 27)
      return "BAND_27"; 
    if (paramInt == 28)
      return "BAND_28"; 
    if (paramInt == 30)
      return "BAND_30"; 
    if (paramInt == 31)
      return "BAND_31"; 
    if (paramInt == 33)
      return "BAND_33"; 
    if (paramInt == 34)
      return "BAND_34"; 
    if (paramInt == 35)
      return "BAND_35"; 
    if (paramInt == 36)
      return "BAND_36"; 
    if (paramInt == 37)
      return "BAND_37"; 
    if (paramInt == 38)
      return "BAND_38"; 
    if (paramInt == 39)
      return "BAND_39"; 
    if (paramInt == 40)
      return "BAND_40"; 
    if (paramInt == 41)
      return "BAND_41"; 
    if (paramInt == 42)
      return "BAND_42"; 
    if (paramInt == 43)
      return "BAND_43"; 
    if (paramInt == 44)
      return "BAND_44"; 
    if (paramInt == 45)
      return "BAND_45"; 
    if (paramInt == 46)
      return "BAND_46"; 
    if (paramInt == 47)
      return "BAND_47"; 
    if (paramInt == 48)
      return "BAND_48"; 
    if (paramInt == 65)
      return "BAND_65"; 
    if (paramInt == 66)
      return "BAND_66"; 
    if (paramInt == 68)
      return "BAND_68"; 
    if (paramInt == 70)
      return "BAND_70"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/EutranBands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */