package android.database.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;

public class SQLiteTokenizer {
  public static final int OPTION_NONE = 0;
  
  public static final int OPTION_TOKEN_ONLY = 1;
  
  private static IllegalArgumentException genException(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    stringBuilder.append(" in '");
    stringBuilder.append(paramString2);
    stringBuilder.append("'");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static boolean isAlNum(char paramChar) {
    return (isAlpha(paramChar) || isNum(paramChar));
  }
  
  private static boolean isAlpha(char paramChar) {
    boolean bool;
    if (('a' <= paramChar && paramChar <= 'z') || ('A' <= paramChar && paramChar <= 'Z') || paramChar == '_') {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean isAnyOf(char paramChar, String paramString) {
    boolean bool;
    if (paramString.indexOf(paramChar) >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isFunction(String paramString) {
    byte b;
    paramString = paramString.toLowerCase(Locale.US);
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 1094496948:
        if (paramString.equals("replace")) {
          b = 21;
          break;
        } 
      case 687315525:
        if (paramString.equals("zeroblob")) {
          b = 32;
          break;
        } 
      case 116062944:
        if (paramString.equals("randomblob")) {
          b = 20;
          break;
        } 
      case 111499426:
        if (paramString.equals("upper")) {
          b = 31;
          break;
        } 
      case 110549828:
        if (paramString.equals("total")) {
          b = 26;
          break;
        } 
      case 108850068:
        if (paramString.equals("rtrim")) {
          b = 23;
          break;
        } 
      case 108704142:
        if (paramString.equals("round")) {
          b = 22;
          break;
        } 
      case 103308942:
        if (paramString.equals("ltrim")) {
          b = 15;
          break;
        } 
      case 103164673:
        if (paramString.equals("lower")) {
          b = 14;
          break;
        } 
      case 100360940:
        if (paramString.equals("instr")) {
          b = 9;
          break;
        } 
      case 94851343:
        if (paramString.equals("count")) {
          b = 4;
          break;
        } 
      case 3568674:
        if (paramString.equals("trim")) {
          b = 27;
          break;
        } 
      case 3321751:
        if (paramString.equals("like")) {
          b = 11;
          break;
        } 
      case 3175800:
        if (paramString.equals("glob")) {
          b = 5;
          break;
        } 
      case 3052374:
        if (paramString.equals("char")) {
          b = 2;
          break;
        } 
      case 114251:
        if (paramString.equals("sum")) {
          b = 25;
          break;
        } 
      case 108114:
        if (paramString.equals("min")) {
          b = 17;
          break;
        } 
      case 107876:
        if (paramString.equals("max")) {
          b = 16;
          break;
        } 
      case 103195:
        if (paramString.equals("hex")) {
          b = 7;
          break;
        } 
      case 96978:
        if (paramString.equals("avg")) {
          b = 1;
          break;
        } 
      case 96370:
        if (paramString.equals("abs")) {
          b = 0;
          break;
        } 
      case -216257731:
        if (paramString.equals("unlikely")) {
          b = 30;
          break;
        } 
      case -287016227:
        if (paramString.equals("unicode")) {
          b = 29;
          break;
        } 
      case -414949776:
        if (paramString.equals("likelihood")) {
          b = 12;
          break;
        } 
      case -660406060:
        if (paramString.equals("group_concat")) {
          b = 6;
          break;
        } 
      case -858802543:
        if (paramString.equals("typeof")) {
          b = 28;
          break;
        } 
      case -891529231:
        if (paramString.equals("substr")) {
          b = 24;
          break;
        } 
      case -938285885:
        if (paramString.equals("random")) {
          b = 19;
          break;
        } 
      case -946884697:
        if (paramString.equals("coalesce")) {
          b = 3;
          break;
        } 
      case -1034384156:
        if (paramString.equals("nullif")) {
          b = 18;
          break;
        } 
      case -1102761116:
        if (paramString.equals("likely")) {
          b = 13;
          break;
        } 
      case -1106363674:
        if (paramString.equals("length")) {
          b = 10;
          break;
        } 
      case -1191314396:
        if (paramString.equals("ifnull")) {
          b = 8;
          break;
        } 
    } 
    switch (b) {
      default:
        return false;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
        break;
    } 
    return true;
  }
  
  public static boolean isKeyword(String paramString) {
    short s;
    paramString = paramString.toUpperCase(Locale.US);
    switch (paramString.hashCode()) {
      default:
        s = -1;
        break;
      case 2123962405:
        if (paramString.equals("HAVING")) {
          s = 61;
          break;
        } 
      case 2110836180:
        if (paramString.equals("GROUPS")) {
          s = 60;
          break;
        } 
      case 2073804664:
        if (paramString.equals("FILTER")) {
          s = 52;
          break;
        } 
      case 2073136296:
        if (paramString.equals("WITHOUT")) {
          s = 142;
          break;
        } 
      case 2058938460:
        if (paramString.equals("EXISTS")) {
          s = 49;
          break;
        } 
      case 2058746137:
        if (paramString.equals("EXCEPT")) {
          s = 46;
          break;
        } 
      case 2054124673:
        if (paramString.equals("ESCAPE")) {
          s = 45;
          break;
        } 
      case 2013072275:
        if (paramString.equals("DETACH")) {
          s = 38;
          break;
        } 
      case 2012838315:
        if (paramString.equals("DELETE")) {
          s = 36;
          break;
        } 
      case 1996002556:
        if (paramString.equals("CREATE")) {
          s = 26;
          break;
        } 
      case 1993481527:
        if (paramString.equals("COMMIT")) {
          s = 23;
          break;
        } 
      case 1993459542:
        if (paramString.equals("COLUMN")) {
          s = 22;
          break;
        } 
      case 1959329793:
        if (paramString.equals("BINARY")) {
          s = 15;
          break;
        } 
      case 1955410815:
        if (paramString.equals("BEFORE")) {
          s = 12;
          break;
        } 
      case 1941037637:
        if (paramString.equals("ATTACH")) {
          s = 10;
          break;
        } 
      case 1925345846:
        if (paramString.equals("ACTION")) {
          s = 1;
          break;
        } 
      case 1870042760:
        if (paramString.equals("REFERENCES")) {
          s = 106;
          break;
        } 
      case 1844922713:
        if (paramString.equals("CURRENT")) {
          s = 28;
          break;
        } 
      case 1812479636:
        if (paramString.equals("REPLACE")) {
          s = 111;
          break;
        } 
      case 1808577511:
        if (paramString.equals("RELEASE")) {
          s = 109;
          break;
        } 
      case 1806077535:
        if (paramString.equals("REINDEX")) {
          s = 108;
          break;
        } 
      case 1667424262:
        if (paramString.equals("COLLATE")) {
          s = 21;
          break;
        } 
      case 1430517727:
        if (paramString.equals("DEFERRED")) {
          s = 35;
          break;
        } 
      case 1406276771:
        if (paramString.equals("PRECEDING")) {
          s = 100;
          break;
        } 
      case 1272812180:
        if (paramString.equals("CASCADE")) {
          s = 17;
          break;
        } 
      case 1184148203:
        if (paramString.equals("VIRTUAL")) {
          s = 137;
          break;
        } 
      case 1071324924:
        if (paramString.equals("DISTINCT")) {
          s = 39;
          break;
        } 
      case 986784458:
        if (paramString.equals("PARTITION")) {
          s = 97;
          break;
        } 
      case 522907364:
        if (paramString.equals("ROLLBACK")) {
          s = 114;
          break;
        } 
      case 501348328:
        if (paramString.equals("BETWEEN")) {
          s = 14;
          break;
        } 
      case 476614193:
        if (paramString.equals("TEMPORARY")) {
          s = 123;
          break;
        } 
      case 446081724:
        if (paramString.equals("RESTRICT")) {
          s = 112;
          break;
        } 
      case 403216866:
        if (paramString.equals("PRIMARY")) {
          s = 101;
          break;
        } 
      case 337882266:
        if (paramString.equals("DEFERRABLE")) {
          s = 34;
          break;
        } 
      case 294715869:
        if (paramString.equals("CONSTRAINT")) {
          s = 25;
          break;
        } 
      case 273740228:
        if (paramString.equals("UNBOUNDED")) {
          s = 129;
          break;
        } 
      case 202578898:
        if (paramString.equals("CONFLICT")) {
          s = 24;
          break;
        } 
      case 178245246:
        if (paramString.equals("EXCLUSIVE")) {
          s = 48;
          break;
        } 
      case 82560199:
        if (paramString.equals("WHERE")) {
          s = 139;
          break;
        } 
      case 81044580:
        if (paramString.equals("USING")) {
          s = 133;
          break;
        } 
      case 80895663:
        if (paramString.equals("UNION")) {
          s = 130;
          break;
        } 
      case 79578030:
        if (paramString.equals("TABLE")) {
          s = 121;
          break;
        } 
      case 78312308:
        if (paramString.equals("RTRIM")) {
          s = 117;
          break;
        } 
      case 77974012:
        if (paramString.equals("RIGHT")) {
          s = 113;
          break;
        } 
      case 77742365:
        if (paramString.equals("RANGE")) {
          s = 104;
          break;
        } 
      case 77737932:
        if (paramString.equals("RAISE")) {
          s = 103;
          break;
        } 
      case 77406376:
        if (paramString.equals("QUERY")) {
          s = 102;
          break;
        } 
      case 75573339:
        if (paramString.equals("OUTER")) {
          s = 95;
          break;
        } 
      case 75468590:
        if (paramString.equals("ORDER")) {
          s = 93;
          break;
        } 
      case 73130405:
        if (paramString.equals("MATCH")) {
          s = 81;
          break;
        } 
      case 72438683:
        if (paramString.equals("LIMIT")) {
          s = 80;
          break;
        } 
      case 69817910:
        if (paramString.equals("INNER")) {
          s = 69;
          break;
        } 
      case 69808306:
        if (paramString.equals("INDEX")) {
          s = 66;
          break;
        } 
      case 68091487:
        if (paramString.equals("GROUP")) {
          s = 59;
          break;
        } 
      case 64397344:
        if (paramString.equals("CROSS")) {
          s = 27;
          break;
        } 
      case 64089320:
        if (paramString.equals("CHECK")) {
          s = 20;
          break;
        } 
      case 63078537:
        if (paramString.equals("BEGIN")) {
          s = 13;
          break;
        } 
      case 62375926:
        if (paramString.equals("ALTER")) {
          s = 5;
          break;
        } 
      case 62197180:
        if (paramString.equals("AFTER")) {
          s = 3;
          break;
        } 
      case 62073616:
        if (paramString.equals("ABORT")) {
          s = 0;
          break;
        } 
      case 40307892:
        if (paramString.equals("FOREIGN")) {
          s = 55;
          break;
        } 
      case 2664646:
        if (paramString.equals("WITH")) {
          s = 141;
          break;
        } 
      case 2663226:
        if (paramString.equals("WHEN")) {
          s = 138;
          break;
        } 
      case 2634405:
        if (paramString.equals("VIEW")) {
          s = 136;
          break;
        } 
      case 2574819:
        if (paramString.equals("TIES")) {
          s = 125;
          break;
        } 
      case 2573853:
        if (paramString.equals("THEN")) {
          s = 124;
          break;
        } 
      case 2571220:
        if (paramString.equals("TEMP")) {
          s = 122;
          break;
        } 
      case 2521561:
        if (paramString.equals("ROWS")) {
          s = 116;
          break;
        } 
      case 2458409:
        if (paramString.equals("PLAN")) {
          s = 98;
          break;
        } 
      case 2438356:
        if (paramString.equals("OVER")) {
          s = 96;
          break;
        } 
      case 2407815:
        if (paramString.equals("NULL")) {
          s = 88;
          break;
        } 
      case 2336663:
        if (paramString.equals("LIKE")) {
          s = 79;
          break;
        } 
      case 2332679:
        if (paramString.equals("LEFT")) {
          s = 78;
          break;
        } 
      case 2282794:
        if (paramString.equals("JOIN")) {
          s = 76;
          break;
        } 
      case 2252384:
        if (paramString.equals("INTO")) {
          s = 73;
          break;
        } 
      case 2190712:
        if (paramString.equals("GLOB")) {
          s = 58;
          break;
        } 
      case 2169487:
        if (paramString.equals("FULL")) {
          s = 57;
          break;
        } 
      case 2166698:
        if (paramString.equals("FROM")) {
          s = 56;
          break;
        } 
      case 2150174:
        if (paramString.equals("FAIL")) {
          s = 51;
          break;
        } 
      case 2131257:
        if (paramString.equals("ELSE")) {
          s = 43;
          break;
        } 
      case 2120193:
        if (paramString.equals("EACH")) {
          s = 42;
          break;
        } 
      case 2107119:
        if (paramString.equals("DROP")) {
          s = 41;
          break;
        } 
      case 2094737:
        if (paramString.equals("DESC")) {
          s = 37;
          break;
        } 
      case 2061119:
        if (paramString.equals("CAST")) {
          s = 19;
          break;
        } 
      case 2061104:
        if (paramString.equals("CASE")) {
          s = 18;
          break;
        } 
      case 81986:
        if (paramString.equals("SET")) {
          s = 120;
          break;
        } 
      case 81338:
        if (paramString.equals("ROW")) {
          s = 115;
          break;
        } 
      case 77491:
        if (paramString.equals("NOT")) {
          s = 85;
          break;
        } 
      case 74303:
        if (paramString.equals("KEY")) {
          s = 77;
          break;
        } 
      case 69801:
        if (paramString.equals("FOR")) {
          s = 54;
          break;
        } 
      case 68795:
        if (paramString.equals("END")) {
          s = 44;
          break;
        } 
      case 65105:
        if (paramString.equals("ASC")) {
          s = 9;
          break;
        } 
      case 64951:
        if (paramString.equals("AND")) {
          s = 7;
          break;
        } 
      case 64897:
        if (paramString.equals("ALL")) {
          s = 4;
          break;
        } 
      case 64641:
        if (paramString.equals("ADD")) {
          s = 2;
          break;
        } 
      case 2683:
        if (paramString.equals("TO")) {
          s = 126;
          break;
        } 
      case 2531:
        if (paramString.equals("OR")) {
          s = 92;
          break;
        } 
      case 2527:
        if (paramString.equals("ON")) {
          s = 91;
          break;
        } 
      case 2519:
        if (paramString.equals("OF")) {
          s = 89;
          break;
        } 
      case 2497:
        if (paramString.equals("NO")) {
          s = 83;
          break;
        } 
      case 2346:
        if (paramString.equals("IS")) {
          s = 74;
          break;
        } 
      case 2341:
        if (paramString.equals("IN")) {
          s = 65;
          break;
        } 
      case 2333:
        if (paramString.equals("IF")) {
          s = 62;
          break;
        } 
      case 2187:
        if (paramString.equals("DO")) {
          s = 40;
          break;
        } 
      case 2135:
        if (paramString.equals("BY")) {
          s = 16;
          break;
        } 
      case 2098:
        if (paramString.equals("AS")) {
          s = 8;
          break;
        } 
      case -760130:
        if (paramString.equals("TRANSACTION")) {
          s = 127;
          break;
        } 
      case -146347732:
        if (paramString.equals("ANALYZE")) {
          s = 6;
          break;
        } 
      case -262905456:
        if (paramString.equals("CURRENT_TIMESTAMP")) {
          s = 31;
          break;
        } 
      case -341909096:
        if (paramString.equals("TRIGGER")) {
          s = 128;
          break;
        } 
      case -342592494:
        if (paramString.equals("RECURSIVE")) {
          s = 105;
          break;
        } 
      case -383989871:
        if (paramString.equals("IMMEDIATE")) {
          s = 64;
          break;
        } 
      case -479221261:
        if (paramString.equals("CURRENT_TIME")) {
          s = 30;
          break;
        } 
      case -479705388:
        if (paramString.equals("CURRENT_DATE")) {
          s = 29;
          break;
        } 
      case -591179561:
        if (paramString.equals("EXPLAIN")) {
          s = 50;
          break;
        } 
      case -603166278:
        if (paramString.equals("EXCLUDE")) {
          s = 47;
          break;
        } 
      case -742456719:
        if (paramString.equals("FOLLOWING")) {
          s = 53;
          break;
        } 
      case -1005357825:
        if (paramString.equals("INTERSECT")) {
          s = 72;
          break;
        } 
      case -1308685805:
        if (paramString.equals("SAVEPOINT")) {
          s = 118;
          break;
        } 
      case -1322009984:
        if (paramString.equals("AUTOINCREMENT")) {
          s = 11;
          break;
        } 
      case -1447470406:
        if (paramString.equals("NOTNULL")) {
          s = 87;
          break;
        } 
      case -1447660627:
        if (paramString.equals("NOTHING")) {
          s = 86;
          break;
        } 
      case -1619411166:
        if (paramString.equals("INSTEAD")) {
          s = 71;
          break;
        } 
      case -1633692463:
        if (paramString.equals("INDEXED")) {
          s = 67;
          break;
        } 
      case -1722875525:
        if (paramString.equals("DATABASE")) {
          s = 32;
          break;
        } 
      case -1734422544:
        if (paramString.equals("WINDOW")) {
          s = 140;
          break;
        } 
      case -1757367375:
        if (paramString.equals("INITIALLY")) {
          s = 68;
          break;
        } 
      case -1770483422:
        if (paramString.equals("VALUES")) {
          s = 135;
          break;
        } 
      case -1770751051:
        if (paramString.equals("VACUUM")) {
          s = 134;
          break;
        } 
      case -1785516855:
        if (paramString.equals("UPDATE")) {
          s = 132;
          break;
        } 
      case -1787199535:
        if (paramString.equals("UNIQUE")) {
          s = 131;
          break;
        } 
      case -1848073207:
        if (paramString.equals("NATURAL")) {
          s = 82;
          break;
        } 
      case -1852692228:
        if (paramString.equals("SELECT")) {
          s = 119;
          break;
        } 
      case -1881265346:
        if (paramString.equals("RENAME")) {
          s = 110;
          break;
        } 
      case -1881469687:
        if (paramString.equals("REGEXP")) {
          s = 107;
          break;
        } 
      case -1926899396:
        if (paramString.equals("PRAGMA")) {
          s = 99;
          break;
        } 
      case -1953474717:
        if (paramString.equals("OTHERS")) {
          s = 94;
          break;
        } 
      case -1966450541:
        if (paramString.equals("OFFSET")) {
          s = 90;
          break;
        } 
      case -1986874255:
        if (paramString.equals("NOCASE")) {
          s = 84;
          break;
        } 
      case -2032180703:
        if (paramString.equals("DEFAULT")) {
          s = 33;
          break;
        } 
      case -2125979215:
        if (paramString.equals("ISNULL")) {
          s = 75;
          break;
        } 
      case -2130463047:
        if (paramString.equals("INSERT")) {
          s = 70;
          break;
        } 
      case -2137067054:
        if (paramString.equals("IGNORE")) {
          s = 63;
          break;
        } 
    } 
    switch (s) {
      default:
        return false;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
      case 68:
      case 69:
      case 70:
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
      case 83:
      case 84:
      case 85:
      case 86:
      case 87:
      case 88:
      case 89:
      case 90:
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
      case 96:
      case 97:
      case 98:
      case 99:
      case 100:
      case 101:
      case 102:
      case 103:
      case 104:
      case 105:
      case 106:
      case 107:
      case 108:
      case 109:
      case 110:
      case 111:
      case 112:
      case 113:
      case 114:
      case 115:
      case 116:
      case 117:
      case 118:
      case 119:
      case 120:
      case 121:
      case 122:
      case 123:
      case 124:
      case 125:
      case 126:
      case 127:
      case 128:
      case 129:
      case 130:
      case 131:
      case 132:
      case 133:
      case 134:
      case 135:
      case 136:
      case 137:
      case 138:
      case 139:
      case 140:
      case 141:
      case 142:
        break;
    } 
    return true;
  }
  
  private static boolean isNum(char paramChar) {
    boolean bool;
    if ('0' <= paramChar && paramChar <= '9') {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isType(String paramString) {
    byte b;
    paramString = paramString.toUpperCase(Locale.US);
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 2022338513:
        if (paramString.equals("DOUBLE")) {
          b = 16;
          break;
        } 
      case 1959128815:
        if (paramString.equals("BIGINT")) {
          b = 5;
          break;
        } 
      case 954596061:
        if (paramString.equals("VARCHAR")) {
          b = 9;
          break;
        } 
      case 782694408:
        if (paramString.equals("BOOLEAN")) {
          b = 20;
          break;
        } 
      case 651290682:
        if (paramString.equals("MEDIUMINT")) {
          b = 4;
          break;
        } 
      case 176095624:
        if (paramString.equals("SMALLINT")) {
          b = 3;
          break;
        } 
      case 74101924:
        if (paramString.equals("NCHAR")) {
          b = 10;
          break;
        } 
      case 66988604:
        if (paramString.equals("FLOAT")) {
          b = 17;
          break;
        } 
      case 55823113:
        if (paramString.equals("CHARACTER")) {
          b = 8;
          break;
        } 
      case 2571565:
        if (paramString.equals("TEXT")) {
          b = 12;
          break;
        } 
      case 2511262:
        if (paramString.equals("REAL")) {
          b = 15;
          break;
        } 
      case 2252361:
        if (paramString.equals("INT8")) {
          b = 7;
          break;
        } 
      case 2252355:
        if (paramString.equals("INT2")) {
          b = 6;
          break;
        } 
      case 2090926:
        if (paramString.equals("DATE")) {
          b = 21;
          break;
        } 
      case 2071548:
        if (paramString.equals("CLOB")) {
          b = 13;
          break;
        } 
      case 2041757:
        if (paramString.equals("BLOB")) {
          b = 14;
          break;
        } 
      case 72655:
        if (paramString.equals("INT")) {
          b = 0;
          break;
        } 
      case -545151281:
        if (paramString.equals("NVARCHAR")) {
          b = 11;
          break;
        } 
      case -594415409:
        if (paramString.equals("TINYINT")) {
          b = 2;
          break;
        } 
      case -1282431251:
        if (paramString.equals("NUMERIC")) {
          b = 18;
          break;
        } 
      case -1618932450:
        if (paramString.equals("INTEGER")) {
          b = 1;
          break;
        } 
      case -1718637701:
        if (paramString.equals("DATETIME")) {
          b = 22;
          break;
        } 
      case -2034720975:
        if (paramString.equals("DECIMAL")) {
          b = 19;
          break;
        } 
    } 
    switch (b) {
      default:
        return false;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
        break;
    } 
    return true;
  }
  
  private static char peek(String paramString, int paramInt) {
    int i;
    if (paramInt < paramString.length()) {
      paramInt = paramString.charAt(paramInt);
      i = paramInt;
    } else {
      paramInt = 0;
      i = paramInt;
    } 
    return i;
  }
  
  public static List<String> tokenize(String paramString, int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    Objects.requireNonNull(arrayList);
    tokenize(paramString, paramInt, new _$$Lambda$I1A8dniBuRdoMGKE4fgVMPvl9YM(arrayList));
    return arrayList;
  }
  
  public static void tokenize(String paramString, int paramInt, Consumer<String> paramConsumer) {
    if (paramString == null)
      return; 
    int i = 0;
    int j = paramString.length();
    int k = paramInt;
    paramInt = i;
    label66: while (true) {
      i = paramInt;
      if (i < j) {
        char c = peek(paramString, i);
        if (isAlpha(c)) {
          for (paramInt = i + 1; isAlNum(peek(paramString, paramInt)); paramInt++);
          paramConsumer.accept(paramString.substring(i, paramInt));
          continue;
        } 
        if (isAnyOf(c, "'\"`")) {
          paramInt = i + 1;
          while (true) {
            int m = paramString.indexOf(c, paramInt);
            if (m >= 0) {
              if (peek(paramString, m + 1) != c) {
                paramInt = m + 1;
                if (c != '\'') {
                  String str = paramString.substring(i + 1, m);
                  if (str.indexOf(c) >= 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(String.valueOf(c));
                    stringBuilder.append(c);
                    str = str.replaceAll(stringBuilder.toString(), String.valueOf(c));
                  } 
                  paramConsumer.accept(str);
                  continue label66;
                } 
                i = k & 0x1;
                k = i;
                if (i == 0)
                  continue label66; 
                throw genException("Non-token detected", paramString);
              } 
              paramInt = m + 2;
              continue;
            } 
            throw genException("Unterminated quote", paramString);
          } 
        } 
        if (c == '[') {
          int m = paramString.indexOf(']', i + 1);
          if (m >= 0) {
            paramInt = m + 1;
            paramConsumer.accept(paramString.substring(i + 1, m));
            continue;
          } 
          throw genException("Unterminated quote", paramString);
        } 
        paramInt = k & 0x1;
        k = paramInt;
        if (paramInt == 0) {
          if (c == '-' && peek(paramString, i + 1) == '-') {
            paramInt = paramString.indexOf('\n', i + 2);
            if (paramInt >= 0) {
              paramInt++;
              continue;
            } 
            throw genException("Unterminated comment", paramString);
          } 
          if (c == '/' && peek(paramString, i + 1) == '*') {
            paramInt = paramString.indexOf("*/", i + 2);
            if (paramInt >= 0) {
              paramInt += 2;
              continue;
            } 
            throw genException("Unterminated comment", paramString);
          } 
          if (c != ';') {
            paramInt = i + 1;
            continue;
          } 
          throw genException("Semicolon is not allowed", paramString);
        } 
        throw genException("Non-token detected", paramString);
      } 
      break;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteTokenizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */