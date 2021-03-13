package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class DataCallFailCause {
  public static final int ACTIVATION_REJECT_GGSN = 30;
  
  public static final int ACTIVATION_REJECT_UNSPECIFIED = 31;
  
  public static final int APN_TYPE_CONFLICT = 112;
  
  public static final int AUTH_FAILURE_ON_EMERGENCY_CALL = 122;
  
  public static final int COMPANION_IFACE_IN_USE = 118;
  
  public static final int CONDITIONAL_IE_ERROR = 100;
  
  public static final int DATA_REGISTRATION_FAIL = -2;
  
  public static final int EMERGENCY_IFACE_ONLY = 116;
  
  public static final int EMM_ACCESS_BARRED = 115;
  
  public static final int EMM_ACCESS_BARRED_INFINITE_RETRY = 121;
  
  public static final int ERROR_UNSPECIFIED = 65535;
  
  public static final int ESM_INFO_NOT_RECEIVED = 53;
  
  public static final int FEATURE_NOT_SUPP = 40;
  
  public static final int FILTER_SEMANTIC_ERROR = 44;
  
  public static final int FILTER_SYTAX_ERROR = 45;
  
  public static final int IFACE_AND_POL_FAMILY_MISMATCH = 120;
  
  public static final int IFACE_MISMATCH = 117;
  
  public static final int INSUFFICIENT_RESOURCES = 26;
  
  public static final int INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN = 114;
  
  public static final int INVALID_MANDATORY_INFO = 96;
  
  public static final int INVALID_PCSCF_ADDR = 113;
  
  public static final int INVALID_TRANSACTION_ID = 81;
  
  public static final int IP_ADDRESS_MISMATCH = 119;
  
  public static final int MAX_ACTIVE_PDP_CONTEXT_REACHED = 65;
  
  public static final int MESSAGE_INCORRECT_SEMANTIC = 95;
  
  public static final int MESSAGE_TYPE_UNSUPPORTED = 97;
  
  public static final int MISSING_UKNOWN_APN = 27;
  
  public static final int MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE = 101;
  
  public static final int MSG_TYPE_NONCOMPATIBLE_STATE = 98;
  
  public static final int MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED = 55;
  
  public static final int NAS_SIGNALLING = 14;
  
  public static final int NETWORK_FAILURE = 38;
  
  public static final int NONE = 0;
  
  public static final int NSAPI_IN_USE = 35;
  
  public static final int OEM_DCFAILCAUSE_1 = 4097;
  
  public static final int OEM_DCFAILCAUSE_10 = 4106;
  
  public static final int OEM_DCFAILCAUSE_11 = 4107;
  
  public static final int OEM_DCFAILCAUSE_12 = 4108;
  
  public static final int OEM_DCFAILCAUSE_13 = 4109;
  
  public static final int OEM_DCFAILCAUSE_14 = 4110;
  
  public static final int OEM_DCFAILCAUSE_15 = 4111;
  
  public static final int OEM_DCFAILCAUSE_2 = 4098;
  
  public static final int OEM_DCFAILCAUSE_3 = 4099;
  
  public static final int OEM_DCFAILCAUSE_4 = 4100;
  
  public static final int OEM_DCFAILCAUSE_5 = 4101;
  
  public static final int OEM_DCFAILCAUSE_6 = 4102;
  
  public static final int OEM_DCFAILCAUSE_7 = 4103;
  
  public static final int OEM_DCFAILCAUSE_8 = 4104;
  
  public static final int OEM_DCFAILCAUSE_9 = 4105;
  
  public static final int ONLY_IPV4_ALLOWED = 50;
  
  public static final int ONLY_IPV6_ALLOWED = 51;
  
  public static final int ONLY_SINGLE_BEARER_ALLOWED = 52;
  
  public static final int OPERATOR_BARRED = 8;
  
  public static final int PDN_CONN_DOES_NOT_EXIST = 54;
  
  public static final int PDP_WITHOUT_ACTIVE_TFT = 46;
  
  public static final int PREF_RADIO_TECH_CHANGED = -4;
  
  public static final int PROTOCOL_ERRORS = 111;
  
  public static final int QOS_NOT_ACCEPTED = 37;
  
  public static final int RADIO_POWER_OFF = -5;
  
  public static final int REGULAR_DEACTIVATION = 36;
  
  public static final int SERVICE_OPTION_NOT_SUBSCRIBED = 33;
  
  public static final int SERVICE_OPTION_NOT_SUPPORTED = 32;
  
  public static final int SERVICE_OPTION_OUT_OF_ORDER = 34;
  
  public static final int SIGNAL_LOST = -3;
  
  public static final int TETHERED_CALL_ACTIVE = -6;
  
  public static final int TFT_SEMANTIC_ERROR = 41;
  
  public static final int TFT_SYTAX_ERROR = 42;
  
  public static final int UMTS_REACTIVATION_REQ = 39;
  
  public static final int UNKNOWN_INFO_ELEMENT = 99;
  
  public static final int UNKNOWN_PDP_ADDRESS_TYPE = 28;
  
  public static final int UNKNOWN_PDP_CONTEXT = 43;
  
  public static final int UNSUPPORTED_APN_IN_CURRENT_PLMN = 66;
  
  public static final int USER_AUTHENTICATION = 29;
  
  public static final int VOICE_REGISTRATION_FAIL = -1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0x8) == 8) {
      arrayList.add("OPERATOR_BARRED");
      i = 0x0 | 0x8;
    } 
    int j = i;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("NAS_SIGNALLING");
      j = i | 0xE;
    } 
    i = j;
    if ((paramInt & 0x1A) == 26) {
      arrayList.add("INSUFFICIENT_RESOURCES");
      i = j | 0x1A;
    } 
    int k = i;
    if ((paramInt & 0x1B) == 27) {
      arrayList.add("MISSING_UKNOWN_APN");
      k = i | 0x1B;
    } 
    j = k;
    if ((paramInt & 0x1C) == 28) {
      arrayList.add("UNKNOWN_PDP_ADDRESS_TYPE");
      j = k | 0x1C;
    } 
    i = j;
    if ((paramInt & 0x1D) == 29) {
      arrayList.add("USER_AUTHENTICATION");
      i = j | 0x1D;
    } 
    k = i;
    if ((paramInt & 0x1E) == 30) {
      arrayList.add("ACTIVATION_REJECT_GGSN");
      k = i | 0x1E;
    } 
    j = k;
    if ((paramInt & 0x1F) == 31) {
      arrayList.add("ACTIVATION_REJECT_UNSPECIFIED");
      j = k | 0x1F;
    } 
    i = j;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("SERVICE_OPTION_NOT_SUPPORTED");
      i = j | 0x20;
    } 
    j = i;
    if ((paramInt & 0x21) == 33) {
      arrayList.add("SERVICE_OPTION_NOT_SUBSCRIBED");
      j = i | 0x21;
    } 
    k = j;
    if ((paramInt & 0x22) == 34) {
      arrayList.add("SERVICE_OPTION_OUT_OF_ORDER");
      k = j | 0x22;
    } 
    i = k;
    if ((paramInt & 0x23) == 35) {
      arrayList.add("NSAPI_IN_USE");
      i = k | 0x23;
    } 
    j = i;
    if ((paramInt & 0x24) == 36) {
      arrayList.add("REGULAR_DEACTIVATION");
      j = i | 0x24;
    } 
    i = j;
    if ((paramInt & 0x25) == 37) {
      arrayList.add("QOS_NOT_ACCEPTED");
      i = j | 0x25;
    } 
    j = i;
    if ((paramInt & 0x26) == 38) {
      arrayList.add("NETWORK_FAILURE");
      j = i | 0x26;
    } 
    i = j;
    if ((paramInt & 0x27) == 39) {
      arrayList.add("UMTS_REACTIVATION_REQ");
      i = j | 0x27;
    } 
    j = i;
    if ((paramInt & 0x28) == 40) {
      arrayList.add("FEATURE_NOT_SUPP");
      j = i | 0x28;
    } 
    k = j;
    if ((paramInt & 0x29) == 41) {
      arrayList.add("TFT_SEMANTIC_ERROR");
      k = j | 0x29;
    } 
    i = k;
    if ((paramInt & 0x2A) == 42) {
      arrayList.add("TFT_SYTAX_ERROR");
      i = k | 0x2A;
    } 
    j = i;
    if ((paramInt & 0x2B) == 43) {
      arrayList.add("UNKNOWN_PDP_CONTEXT");
      j = i | 0x2B;
    } 
    i = j;
    if ((paramInt & 0x2C) == 44) {
      arrayList.add("FILTER_SEMANTIC_ERROR");
      i = j | 0x2C;
    } 
    k = i;
    if ((paramInt & 0x2D) == 45) {
      arrayList.add("FILTER_SYTAX_ERROR");
      k = i | 0x2D;
    } 
    j = k;
    if ((paramInt & 0x2E) == 46) {
      arrayList.add("PDP_WITHOUT_ACTIVE_TFT");
      j = k | 0x2E;
    } 
    i = j;
    if ((paramInt & 0x32) == 50) {
      arrayList.add("ONLY_IPV4_ALLOWED");
      i = j | 0x32;
    } 
    j = i;
    if ((paramInt & 0x33) == 51) {
      arrayList.add("ONLY_IPV6_ALLOWED");
      j = i | 0x33;
    } 
    i = j;
    if ((paramInt & 0x34) == 52) {
      arrayList.add("ONLY_SINGLE_BEARER_ALLOWED");
      i = j | 0x34;
    } 
    j = i;
    if ((paramInt & 0x35) == 53) {
      arrayList.add("ESM_INFO_NOT_RECEIVED");
      j = i | 0x35;
    } 
    i = j;
    if ((paramInt & 0x36) == 54) {
      arrayList.add("PDN_CONN_DOES_NOT_EXIST");
      i = j | 0x36;
    } 
    j = i;
    if ((paramInt & 0x37) == 55) {
      arrayList.add("MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED");
      j = i | 0x37;
    } 
    i = j;
    if ((paramInt & 0x41) == 65) {
      arrayList.add("MAX_ACTIVE_PDP_CONTEXT_REACHED");
      i = j | 0x41;
    } 
    j = i;
    if ((paramInt & 0x42) == 66) {
      arrayList.add("UNSUPPORTED_APN_IN_CURRENT_PLMN");
      j = i | 0x42;
    } 
    k = j;
    if ((paramInt & 0x51) == 81) {
      arrayList.add("INVALID_TRANSACTION_ID");
      k = j | 0x51;
    } 
    i = k;
    if ((paramInt & 0x5F) == 95) {
      arrayList.add("MESSAGE_INCORRECT_SEMANTIC");
      i = k | 0x5F;
    } 
    j = i;
    if ((paramInt & 0x60) == 96) {
      arrayList.add("INVALID_MANDATORY_INFO");
      j = i | 0x60;
    } 
    k = j;
    if ((paramInt & 0x61) == 97) {
      arrayList.add("MESSAGE_TYPE_UNSUPPORTED");
      k = j | 0x61;
    } 
    i = k;
    if ((paramInt & 0x62) == 98) {
      arrayList.add("MSG_TYPE_NONCOMPATIBLE_STATE");
      i = k | 0x62;
    } 
    k = i;
    if ((paramInt & 0x63) == 99) {
      arrayList.add("UNKNOWN_INFO_ELEMENT");
      k = i | 0x63;
    } 
    j = k;
    if ((paramInt & 0x64) == 100) {
      arrayList.add("CONDITIONAL_IE_ERROR");
      j = k | 0x64;
    } 
    i = j;
    if ((paramInt & 0x65) == 101) {
      arrayList.add("MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE");
      i = j | 0x65;
    } 
    j = i;
    if ((paramInt & 0x6F) == 111) {
      arrayList.add("PROTOCOL_ERRORS");
      j = i | 0x6F;
    } 
    i = j;
    if ((paramInt & 0x70) == 112) {
      arrayList.add("APN_TYPE_CONFLICT");
      i = j | 0x70;
    } 
    j = i;
    if ((paramInt & 0x71) == 113) {
      arrayList.add("INVALID_PCSCF_ADDR");
      j = i | 0x71;
    } 
    i = j;
    if ((paramInt & 0x72) == 114) {
      arrayList.add("INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN");
      i = j | 0x72;
    } 
    k = i;
    if ((paramInt & 0x73) == 115) {
      arrayList.add("EMM_ACCESS_BARRED");
      k = i | 0x73;
    } 
    j = k;
    if ((paramInt & 0x74) == 116) {
      arrayList.add("EMERGENCY_IFACE_ONLY");
      j = k | 0x74;
    } 
    i = j;
    if ((paramInt & 0x75) == 117) {
      arrayList.add("IFACE_MISMATCH");
      i = j | 0x75;
    } 
    j = i;
    if ((paramInt & 0x76) == 118) {
      arrayList.add("COMPANION_IFACE_IN_USE");
      j = i | 0x76;
    } 
    i = j;
    if ((paramInt & 0x77) == 119) {
      arrayList.add("IP_ADDRESS_MISMATCH");
      i = j | 0x77;
    } 
    j = i;
    if ((paramInt & 0x78) == 120) {
      arrayList.add("IFACE_AND_POL_FAMILY_MISMATCH");
      j = i | 0x78;
    } 
    i = j;
    if ((paramInt & 0x79) == 121) {
      arrayList.add("EMM_ACCESS_BARRED_INFINITE_RETRY");
      i = j | 0x79;
    } 
    j = i;
    if ((paramInt & 0x7A) == 122) {
      arrayList.add("AUTH_FAILURE_ON_EMERGENCY_CALL");
      j = i | 0x7A;
    } 
    i = j;
    if ((paramInt & 0x1001) == 4097) {
      arrayList.add("OEM_DCFAILCAUSE_1");
      i = j | 0x1001;
    } 
    j = i;
    if ((paramInt & 0x1002) == 4098) {
      arrayList.add("OEM_DCFAILCAUSE_2");
      j = i | 0x1002;
    } 
    i = j;
    if ((paramInt & 0x1003) == 4099) {
      arrayList.add("OEM_DCFAILCAUSE_3");
      i = j | 0x1003;
    } 
    j = i;
    if ((paramInt & 0x1004) == 4100) {
      arrayList.add("OEM_DCFAILCAUSE_4");
      j = i | 0x1004;
    } 
    i = j;
    if ((paramInt & 0x1005) == 4101) {
      arrayList.add("OEM_DCFAILCAUSE_5");
      i = j | 0x1005;
    } 
    j = i;
    if ((paramInt & 0x1006) == 4102) {
      arrayList.add("OEM_DCFAILCAUSE_6");
      j = i | 0x1006;
    } 
    k = j;
    if ((paramInt & 0x1007) == 4103) {
      arrayList.add("OEM_DCFAILCAUSE_7");
      k = j | 0x1007;
    } 
    i = k;
    if ((paramInt & 0x1008) == 4104) {
      arrayList.add("OEM_DCFAILCAUSE_8");
      i = k | 0x1008;
    } 
    j = i;
    if ((paramInt & 0x1009) == 4105) {
      arrayList.add("OEM_DCFAILCAUSE_9");
      j = i | 0x1009;
    } 
    i = j;
    if ((paramInt & 0x100A) == 4106) {
      arrayList.add("OEM_DCFAILCAUSE_10");
      i = j | 0x100A;
    } 
    j = i;
    if ((paramInt & 0x100B) == 4107) {
      arrayList.add("OEM_DCFAILCAUSE_11");
      j = i | 0x100B;
    } 
    i = j;
    if ((paramInt & 0x100C) == 4108) {
      arrayList.add("OEM_DCFAILCAUSE_12");
      i = j | 0x100C;
    } 
    j = i;
    if ((paramInt & 0x100D) == 4109) {
      arrayList.add("OEM_DCFAILCAUSE_13");
      j = i | 0x100D;
    } 
    i = j;
    if ((paramInt & 0x100E) == 4110) {
      arrayList.add("OEM_DCFAILCAUSE_14");
      i = j | 0x100E;
    } 
    j = i;
    if ((paramInt & 0x100F) == 4111) {
      arrayList.add("OEM_DCFAILCAUSE_15");
      j = i | 0x100F;
    } 
    i = j;
    if ((paramInt & 0xFFFFFFFF) == -1) {
      arrayList.add("VOICE_REGISTRATION_FAIL");
      i = j | 0xFFFFFFFF;
    } 
    j = i;
    if ((paramInt & 0xFFFFFFFE) == -2) {
      arrayList.add("DATA_REGISTRATION_FAIL");
      j = i | 0xFFFFFFFE;
    } 
    i = j;
    if ((paramInt & 0xFFFFFFFD) == -3) {
      arrayList.add("SIGNAL_LOST");
      i = j | 0xFFFFFFFD;
    } 
    j = i;
    if ((paramInt & 0xFFFFFFFC) == -4) {
      arrayList.add("PREF_RADIO_TECH_CHANGED");
      j = i | 0xFFFFFFFC;
    } 
    i = j;
    if ((paramInt & 0xFFFFFFFB) == -5) {
      arrayList.add("RADIO_POWER_OFF");
      i = j | 0xFFFFFFFB;
    } 
    j = i;
    if ((paramInt & 0xFFFFFFFA) == -6) {
      arrayList.add("TETHERED_CALL_ACTIVE");
      j = i | 0xFFFFFFFA;
    } 
    i = j;
    if ((0xFFFF & paramInt) == 65535) {
      arrayList.add("ERROR_UNSPECIFIED");
      i = j | 0xFFFF;
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
    if (paramInt == 0)
      return "NONE"; 
    if (paramInt == 8)
      return "OPERATOR_BARRED"; 
    if (paramInt == 14)
      return "NAS_SIGNALLING"; 
    if (paramInt == 26)
      return "INSUFFICIENT_RESOURCES"; 
    if (paramInt == 27)
      return "MISSING_UKNOWN_APN"; 
    if (paramInt == 28)
      return "UNKNOWN_PDP_ADDRESS_TYPE"; 
    if (paramInt == 29)
      return "USER_AUTHENTICATION"; 
    if (paramInt == 30)
      return "ACTIVATION_REJECT_GGSN"; 
    if (paramInt == 31)
      return "ACTIVATION_REJECT_UNSPECIFIED"; 
    if (paramInt == 32)
      return "SERVICE_OPTION_NOT_SUPPORTED"; 
    if (paramInt == 33)
      return "SERVICE_OPTION_NOT_SUBSCRIBED"; 
    if (paramInt == 34)
      return "SERVICE_OPTION_OUT_OF_ORDER"; 
    if (paramInt == 35)
      return "NSAPI_IN_USE"; 
    if (paramInt == 36)
      return "REGULAR_DEACTIVATION"; 
    if (paramInt == 37)
      return "QOS_NOT_ACCEPTED"; 
    if (paramInt == 38)
      return "NETWORK_FAILURE"; 
    if (paramInt == 39)
      return "UMTS_REACTIVATION_REQ"; 
    if (paramInt == 40)
      return "FEATURE_NOT_SUPP"; 
    if (paramInt == 41)
      return "TFT_SEMANTIC_ERROR"; 
    if (paramInt == 42)
      return "TFT_SYTAX_ERROR"; 
    if (paramInt == 43)
      return "UNKNOWN_PDP_CONTEXT"; 
    if (paramInt == 44)
      return "FILTER_SEMANTIC_ERROR"; 
    if (paramInt == 45)
      return "FILTER_SYTAX_ERROR"; 
    if (paramInt == 46)
      return "PDP_WITHOUT_ACTIVE_TFT"; 
    if (paramInt == 50)
      return "ONLY_IPV4_ALLOWED"; 
    if (paramInt == 51)
      return "ONLY_IPV6_ALLOWED"; 
    if (paramInt == 52)
      return "ONLY_SINGLE_BEARER_ALLOWED"; 
    if (paramInt == 53)
      return "ESM_INFO_NOT_RECEIVED"; 
    if (paramInt == 54)
      return "PDN_CONN_DOES_NOT_EXIST"; 
    if (paramInt == 55)
      return "MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED"; 
    if (paramInt == 65)
      return "MAX_ACTIVE_PDP_CONTEXT_REACHED"; 
    if (paramInt == 66)
      return "UNSUPPORTED_APN_IN_CURRENT_PLMN"; 
    if (paramInt == 81)
      return "INVALID_TRANSACTION_ID"; 
    if (paramInt == 95)
      return "MESSAGE_INCORRECT_SEMANTIC"; 
    if (paramInt == 96)
      return "INVALID_MANDATORY_INFO"; 
    if (paramInt == 97)
      return "MESSAGE_TYPE_UNSUPPORTED"; 
    if (paramInt == 98)
      return "MSG_TYPE_NONCOMPATIBLE_STATE"; 
    if (paramInt == 99)
      return "UNKNOWN_INFO_ELEMENT"; 
    if (paramInt == 100)
      return "CONDITIONAL_IE_ERROR"; 
    if (paramInt == 101)
      return "MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE"; 
    if (paramInt == 111)
      return "PROTOCOL_ERRORS"; 
    if (paramInt == 112)
      return "APN_TYPE_CONFLICT"; 
    if (paramInt == 113)
      return "INVALID_PCSCF_ADDR"; 
    if (paramInt == 114)
      return "INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN"; 
    if (paramInt == 115)
      return "EMM_ACCESS_BARRED"; 
    if (paramInt == 116)
      return "EMERGENCY_IFACE_ONLY"; 
    if (paramInt == 117)
      return "IFACE_MISMATCH"; 
    if (paramInt == 118)
      return "COMPANION_IFACE_IN_USE"; 
    if (paramInt == 119)
      return "IP_ADDRESS_MISMATCH"; 
    if (paramInt == 120)
      return "IFACE_AND_POL_FAMILY_MISMATCH"; 
    if (paramInt == 121)
      return "EMM_ACCESS_BARRED_INFINITE_RETRY"; 
    if (paramInt == 122)
      return "AUTH_FAILURE_ON_EMERGENCY_CALL"; 
    if (paramInt == 4097)
      return "OEM_DCFAILCAUSE_1"; 
    if (paramInt == 4098)
      return "OEM_DCFAILCAUSE_2"; 
    if (paramInt == 4099)
      return "OEM_DCFAILCAUSE_3"; 
    if (paramInt == 4100)
      return "OEM_DCFAILCAUSE_4"; 
    if (paramInt == 4101)
      return "OEM_DCFAILCAUSE_5"; 
    if (paramInt == 4102)
      return "OEM_DCFAILCAUSE_6"; 
    if (paramInt == 4103)
      return "OEM_DCFAILCAUSE_7"; 
    if (paramInt == 4104)
      return "OEM_DCFAILCAUSE_8"; 
    if (paramInt == 4105)
      return "OEM_DCFAILCAUSE_9"; 
    if (paramInt == 4106)
      return "OEM_DCFAILCAUSE_10"; 
    if (paramInt == 4107)
      return "OEM_DCFAILCAUSE_11"; 
    if (paramInt == 4108)
      return "OEM_DCFAILCAUSE_12"; 
    if (paramInt == 4109)
      return "OEM_DCFAILCAUSE_13"; 
    if (paramInt == 4110)
      return "OEM_DCFAILCAUSE_14"; 
    if (paramInt == 4111)
      return "OEM_DCFAILCAUSE_15"; 
    if (paramInt == -1)
      return "VOICE_REGISTRATION_FAIL"; 
    if (paramInt == -2)
      return "DATA_REGISTRATION_FAIL"; 
    if (paramInt == -3)
      return "SIGNAL_LOST"; 
    if (paramInt == -4)
      return "PREF_RADIO_TECH_CHANGED"; 
    if (paramInt == -5)
      return "RADIO_POWER_OFF"; 
    if (paramInt == -6)
      return "TETHERED_CALL_ACTIVE"; 
    if (paramInt == 65535)
      return "ERROR_UNSPECIFIED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/DataCallFailCause.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */