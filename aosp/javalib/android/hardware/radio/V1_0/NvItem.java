package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class NvItem {
  public static final int CDMA_1X_ADVANCED_ENABLED = 57;
  
  public static final int CDMA_ACCOLC = 4;
  
  public static final int CDMA_BC10 = 52;
  
  public static final int CDMA_BC14 = 53;
  
  public static final int CDMA_EHRPD_ENABLED = 58;
  
  public static final int CDMA_EHRPD_FORCED = 59;
  
  public static final int CDMA_MDN = 3;
  
  public static final int CDMA_MEID = 1;
  
  public static final int CDMA_MIN = 2;
  
  public static final int CDMA_PRL_VERSION = 51;
  
  public static final int CDMA_SO68 = 54;
  
  public static final int CDMA_SO73_COP0 = 55;
  
  public static final int CDMA_SO73_COP1TO7 = 56;
  
  public static final int DEVICE_MSL = 11;
  
  public static final int LTE_BAND_ENABLE_25 = 71;
  
  public static final int LTE_BAND_ENABLE_26 = 72;
  
  public static final int LTE_BAND_ENABLE_41 = 73;
  
  public static final int LTE_HIDDEN_BAND_PRIORITY_25 = 77;
  
  public static final int LTE_HIDDEN_BAND_PRIORITY_26 = 78;
  
  public static final int LTE_HIDDEN_BAND_PRIORITY_41 = 79;
  
  public static final int LTE_SCAN_PRIORITY_25 = 74;
  
  public static final int LTE_SCAN_PRIORITY_26 = 75;
  
  public static final int LTE_SCAN_PRIORITY_41 = 76;
  
  public static final int MIP_PROFILE_AAA_AUTH = 33;
  
  public static final int MIP_PROFILE_AAA_SPI = 39;
  
  public static final int MIP_PROFILE_HA_AUTH = 34;
  
  public static final int MIP_PROFILE_HA_SPI = 38;
  
  public static final int MIP_PROFILE_HOME_ADDRESS = 32;
  
  public static final int MIP_PROFILE_MN_AAA_SS = 41;
  
  public static final int MIP_PROFILE_MN_HA_SS = 40;
  
  public static final int MIP_PROFILE_NAI = 31;
  
  public static final int MIP_PROFILE_PRI_HA_ADDR = 35;
  
  public static final int MIP_PROFILE_REV_TUN_PREF = 37;
  
  public static final int MIP_PROFILE_SEC_HA_ADDR = 36;
  
  public static final int OMADM_HFA_LEVEL = 18;
  
  public static final int RTN_ACTIVATION_DATE = 13;
  
  public static final int RTN_LIFE_CALLS = 15;
  
  public static final int RTN_LIFE_DATA_RX = 17;
  
  public static final int RTN_LIFE_DATA_TX = 16;
  
  public static final int RTN_LIFE_TIMER = 14;
  
  public static final int RTN_RECONDITIONED_STATUS = 12;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CDMA_MEID");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CDMA_MIN");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("CDMA_MDN");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CDMA_ACCOLC");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("DEVICE_MSL");
      i = j | 0xB;
    } 
    j = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("RTN_RECONDITIONED_STATUS");
      j = i | 0xC;
    } 
    i = j;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("RTN_ACTIVATION_DATE");
      i = j | 0xD;
    } 
    int k = i;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("RTN_LIFE_TIMER");
      k = i | 0xE;
    } 
    j = k;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("RTN_LIFE_CALLS");
      j = k | 0xF;
    } 
    i = j;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("RTN_LIFE_DATA_TX");
      i = j | 0x10;
    } 
    j = i;
    if ((paramInt & 0x11) == 17) {
      arrayList.add("RTN_LIFE_DATA_RX");
      j = i | 0x11;
    } 
    i = j;
    if ((paramInt & 0x12) == 18) {
      arrayList.add("OMADM_HFA_LEVEL");
      i = j | 0x12;
    } 
    j = i;
    if ((paramInt & 0x1F) == 31) {
      arrayList.add("MIP_PROFILE_NAI");
      j = i | 0x1F;
    } 
    i = j;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("MIP_PROFILE_HOME_ADDRESS");
      i = j | 0x20;
    } 
    j = i;
    if ((paramInt & 0x21) == 33) {
      arrayList.add("MIP_PROFILE_AAA_AUTH");
      j = i | 0x21;
    } 
    i = j;
    if ((paramInt & 0x22) == 34) {
      arrayList.add("MIP_PROFILE_HA_AUTH");
      i = j | 0x22;
    } 
    k = i;
    if ((paramInt & 0x23) == 35) {
      arrayList.add("MIP_PROFILE_PRI_HA_ADDR");
      k = i | 0x23;
    } 
    j = k;
    if ((paramInt & 0x24) == 36) {
      arrayList.add("MIP_PROFILE_SEC_HA_ADDR");
      j = k | 0x24;
    } 
    i = j;
    if ((paramInt & 0x25) == 37) {
      arrayList.add("MIP_PROFILE_REV_TUN_PREF");
      i = j | 0x25;
    } 
    j = i;
    if ((paramInt & 0x26) == 38) {
      arrayList.add("MIP_PROFILE_HA_SPI");
      j = i | 0x26;
    } 
    i = j;
    if ((paramInt & 0x27) == 39) {
      arrayList.add("MIP_PROFILE_AAA_SPI");
      i = j | 0x27;
    } 
    j = i;
    if ((paramInt & 0x28) == 40) {
      arrayList.add("MIP_PROFILE_MN_HA_SS");
      j = i | 0x28;
    } 
    i = j;
    if ((paramInt & 0x29) == 41) {
      arrayList.add("MIP_PROFILE_MN_AAA_SS");
      i = j | 0x29;
    } 
    j = i;
    if ((paramInt & 0x33) == 51) {
      arrayList.add("CDMA_PRL_VERSION");
      j = i | 0x33;
    } 
    k = j;
    if ((paramInt & 0x34) == 52) {
      arrayList.add("CDMA_BC10");
      k = j | 0x34;
    } 
    i = k;
    if ((paramInt & 0x35) == 53) {
      arrayList.add("CDMA_BC14");
      i = k | 0x35;
    } 
    j = i;
    if ((paramInt & 0x36) == 54) {
      arrayList.add("CDMA_SO68");
      j = i | 0x36;
    } 
    i = j;
    if ((paramInt & 0x37) == 55) {
      arrayList.add("CDMA_SO73_COP0");
      i = j | 0x37;
    } 
    j = i;
    if ((paramInt & 0x38) == 56) {
      arrayList.add("CDMA_SO73_COP1TO7");
      j = i | 0x38;
    } 
    i = j;
    if ((paramInt & 0x39) == 57) {
      arrayList.add("CDMA_1X_ADVANCED_ENABLED");
      i = j | 0x39;
    } 
    j = i;
    if ((paramInt & 0x3A) == 58) {
      arrayList.add("CDMA_EHRPD_ENABLED");
      j = i | 0x3A;
    } 
    i = j;
    if ((paramInt & 0x3B) == 59) {
      arrayList.add("CDMA_EHRPD_FORCED");
      i = j | 0x3B;
    } 
    j = i;
    if ((paramInt & 0x47) == 71) {
      arrayList.add("LTE_BAND_ENABLE_25");
      j = i | 0x47;
    } 
    k = j;
    if ((paramInt & 0x48) == 72) {
      arrayList.add("LTE_BAND_ENABLE_26");
      k = j | 0x48;
    } 
    i = k;
    if ((paramInt & 0x49) == 73) {
      arrayList.add("LTE_BAND_ENABLE_41");
      i = k | 0x49;
    } 
    j = i;
    if ((paramInt & 0x4A) == 74) {
      arrayList.add("LTE_SCAN_PRIORITY_25");
      j = i | 0x4A;
    } 
    i = j;
    if ((paramInt & 0x4B) == 75) {
      arrayList.add("LTE_SCAN_PRIORITY_26");
      i = j | 0x4B;
    } 
    j = i;
    if ((paramInt & 0x4C) == 76) {
      arrayList.add("LTE_SCAN_PRIORITY_41");
      j = i | 0x4C;
    } 
    i = j;
    if ((paramInt & 0x4D) == 77) {
      arrayList.add("LTE_HIDDEN_BAND_PRIORITY_25");
      i = j | 0x4D;
    } 
    j = i;
    if ((paramInt & 0x4E) == 78) {
      arrayList.add("LTE_HIDDEN_BAND_PRIORITY_26");
      j = i | 0x4E;
    } 
    i = j;
    if ((paramInt & 0x4F) == 79) {
      arrayList.add("LTE_HIDDEN_BAND_PRIORITY_41");
      i = j | 0x4F;
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
      return "CDMA_MEID"; 
    if (paramInt == 2)
      return "CDMA_MIN"; 
    if (paramInt == 3)
      return "CDMA_MDN"; 
    if (paramInt == 4)
      return "CDMA_ACCOLC"; 
    if (paramInt == 11)
      return "DEVICE_MSL"; 
    if (paramInt == 12)
      return "RTN_RECONDITIONED_STATUS"; 
    if (paramInt == 13)
      return "RTN_ACTIVATION_DATE"; 
    if (paramInt == 14)
      return "RTN_LIFE_TIMER"; 
    if (paramInt == 15)
      return "RTN_LIFE_CALLS"; 
    if (paramInt == 16)
      return "RTN_LIFE_DATA_TX"; 
    if (paramInt == 17)
      return "RTN_LIFE_DATA_RX"; 
    if (paramInt == 18)
      return "OMADM_HFA_LEVEL"; 
    if (paramInt == 31)
      return "MIP_PROFILE_NAI"; 
    if (paramInt == 32)
      return "MIP_PROFILE_HOME_ADDRESS"; 
    if (paramInt == 33)
      return "MIP_PROFILE_AAA_AUTH"; 
    if (paramInt == 34)
      return "MIP_PROFILE_HA_AUTH"; 
    if (paramInt == 35)
      return "MIP_PROFILE_PRI_HA_ADDR"; 
    if (paramInt == 36)
      return "MIP_PROFILE_SEC_HA_ADDR"; 
    if (paramInt == 37)
      return "MIP_PROFILE_REV_TUN_PREF"; 
    if (paramInt == 38)
      return "MIP_PROFILE_HA_SPI"; 
    if (paramInt == 39)
      return "MIP_PROFILE_AAA_SPI"; 
    if (paramInt == 40)
      return "MIP_PROFILE_MN_HA_SS"; 
    if (paramInt == 41)
      return "MIP_PROFILE_MN_AAA_SS"; 
    if (paramInt == 51)
      return "CDMA_PRL_VERSION"; 
    if (paramInt == 52)
      return "CDMA_BC10"; 
    if (paramInt == 53)
      return "CDMA_BC14"; 
    if (paramInt == 54)
      return "CDMA_SO68"; 
    if (paramInt == 55)
      return "CDMA_SO73_COP0"; 
    if (paramInt == 56)
      return "CDMA_SO73_COP1TO7"; 
    if (paramInt == 57)
      return "CDMA_1X_ADVANCED_ENABLED"; 
    if (paramInt == 58)
      return "CDMA_EHRPD_ENABLED"; 
    if (paramInt == 59)
      return "CDMA_EHRPD_FORCED"; 
    if (paramInt == 71)
      return "LTE_BAND_ENABLE_25"; 
    if (paramInt == 72)
      return "LTE_BAND_ENABLE_26"; 
    if (paramInt == 73)
      return "LTE_BAND_ENABLE_41"; 
    if (paramInt == 74)
      return "LTE_SCAN_PRIORITY_25"; 
    if (paramInt == 75)
      return "LTE_SCAN_PRIORITY_26"; 
    if (paramInt == 76)
      return "LTE_SCAN_PRIORITY_41"; 
    if (paramInt == 77)
      return "LTE_HIDDEN_BAND_PRIORITY_25"; 
    if (paramInt == 78)
      return "LTE_HIDDEN_BAND_PRIORITY_26"; 
    if (paramInt == 79)
      return "LTE_HIDDEN_BAND_PRIORITY_41"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/NvItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */