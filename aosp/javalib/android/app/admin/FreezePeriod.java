package android.app.admin;

import android.util.Log;
import android.util.Pair;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FreezePeriod {
  static final int DAYS_IN_YEAR = 365;
  
  private static final int DUMMY_YEAR = 2001;
  
  private static final String TAG = "FreezePeriod";
  
  private final MonthDay mEnd;
  
  private final int mEndDay;
  
  private final MonthDay mStart;
  
  private final int mStartDay;
  
  private FreezePeriod(int paramInt1, int paramInt2) {
    this.mStartDay = paramInt1;
    this.mStart = dayOfYearToMonthDay(paramInt1);
    this.mEndDay = paramInt2;
    this.mEnd = dayOfYearToMonthDay(paramInt2);
  }
  
  public FreezePeriod(MonthDay paramMonthDay1, MonthDay paramMonthDay2) {
    this.mStart = paramMonthDay1;
    this.mStartDay = paramMonthDay1.atYear(2001).getDayOfYear();
    this.mEnd = paramMonthDay2;
    this.mEndDay = paramMonthDay2.atYear(2001).getDayOfYear();
  }
  
  static List<FreezePeriod> canonicalizePeriods(List<FreezePeriod> paramList) {
    boolean[] arrayOfBoolean = new boolean[365];
    for (FreezePeriod freezePeriod : paramList) {
      for (int j = freezePeriod.mStartDay; j <= freezePeriod.getEffectiveEndDay(); j++)
        arrayOfBoolean[(j - 1) % 365] = true; 
    } 
    paramList = new ArrayList<>();
    int i = 0;
    while (true) {
      int j = i;
      if (j < 365) {
        if (!arrayOfBoolean[j]) {
          i = j + 1;
          continue;
        } 
        for (i = j; i < 365 && arrayOfBoolean[i]; i++);
        paramList.add(new FreezePeriod(j + 1, i));
        continue;
      } 
      i = paramList.size() - 1;
      if (i > 0 && ((FreezePeriod)paramList.get(i)).mEndDay == 365 && ((FreezePeriod)paramList.get(0)).mStartDay == 1) {
        paramList.set(i, new FreezePeriod(((FreezePeriod)paramList.get(i)).mStartDay, ((FreezePeriod)paramList.get(0)).mEndDay));
        paramList.remove(0);
      } 
      return paramList;
    } 
  }
  
  private static int dayOfYearDisregardLeapYear(LocalDate paramLocalDate) {
    return paramLocalDate.withYear(2001).getDayOfYear();
  }
  
  private static MonthDay dayOfYearToMonthDay(int paramInt) {
    LocalDate localDate = LocalDate.ofYearDay(2001, paramInt);
    return MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
  }
  
  public static int distanceWithoutLeapYear(LocalDate paramLocalDate1, LocalDate paramLocalDate2) {
    return dayOfYearDisregardLeapYear(paramLocalDate1) - dayOfYearDisregardLeapYear(paramLocalDate2) + (paramLocalDate1.getYear() - paramLocalDate2.getYear()) * 365;
  }
  
  static void validateAgainstPreviousFreezePeriod(List<FreezePeriod> paramList, LocalDate paramLocalDate1, LocalDate paramLocalDate2, LocalDate paramLocalDate3) {
    FreezePeriod freezePeriod1;
    StringBuilder stringBuilder1;
    if (paramList.size() == 0 || paramLocalDate1 == null || paramLocalDate2 == null)
      return; 
    if (paramLocalDate1.isAfter(paramLocalDate3) || paramLocalDate2.isAfter(paramLocalDate3)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Previous period (");
      stringBuilder.append(paramLocalDate1);
      stringBuilder.append(",");
      stringBuilder.append(paramLocalDate2);
      stringBuilder.append(") is after current date ");
      stringBuilder.append(paramLocalDate3);
      Log.w("FreezePeriod", stringBuilder.toString());
    } 
    paramList = canonicalizePeriods(paramList);
    FreezePeriod freezePeriod2 = paramList.get(0);
    Iterator<FreezePeriod> iterator = paramList.iterator();
    while (true) {
      freezePeriod1 = freezePeriod2;
      if (iterator.hasNext()) {
        freezePeriod1 = iterator.next();
        if (freezePeriod1.contains(paramLocalDate3) || freezePeriod1.mStartDay > dayOfYearDisregardLeapYear(paramLocalDate3))
          break; 
        continue;
      } 
      break;
    } 
    Pair<LocalDate, LocalDate> pair2 = freezePeriod1.toCurrentOrFutureRealDates(paramLocalDate3);
    Pair<LocalDate, LocalDate> pair1 = pair2;
    if (paramLocalDate3.isAfter((ChronoLocalDate)pair2.first))
      pair1 = new Pair(paramLocalDate3, pair2.second); 
    if (!((LocalDate)pair1.first).isAfter((ChronoLocalDate)pair1.second)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Prev: ");
      stringBuilder.append(paramLocalDate1);
      stringBuilder.append(",");
      stringBuilder.append(paramLocalDate2);
      stringBuilder.append("; cur: ");
      stringBuilder.append(pair1.first);
      stringBuilder.append(",");
      stringBuilder.append(pair1.second);
      String str = stringBuilder.toString();
      long l = (distanceWithoutLeapYear((LocalDate)pair1.first, paramLocalDate2) - 1);
      if (l > 0L) {
        if (l < 60L) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Previous freeze period too close to new period: ");
          stringBuilder1.append(l);
          stringBuilder1.append(", ");
          stringBuilder1.append(str);
          throw SystemUpdatePolicy.ValidationFailedException.combinedPeriodTooClose(stringBuilder1.toString());
        } 
      } else {
        l = (distanceWithoutLeapYear((LocalDate)((Pair)stringBuilder1).second, paramLocalDate1) + 1);
        if (l > 90L) {
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Combined freeze period exceeds maximum days: ");
          stringBuilder1.append(l);
          stringBuilder1.append(", ");
          stringBuilder1.append(str);
          throw SystemUpdatePolicy.ValidationFailedException.combinedPeriodTooLong(stringBuilder1.toString());
        } 
      } 
      return;
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Current freeze dates inverted: ");
    stringBuilder2.append(((Pair)stringBuilder1).first);
    stringBuilder2.append("-");
    stringBuilder2.append(((Pair)stringBuilder1).second);
    throw new IllegalStateException(stringBuilder2.toString());
  }
  
  static void validatePeriods(List<FreezePeriod> paramList) {
    List<FreezePeriod> list = canonicalizePeriods(paramList);
    if (list.size() == paramList.size()) {
      byte b = 0;
      while (b < list.size()) {
        FreezePeriod freezePeriod = list.get(b);
        if (freezePeriod.getLength() <= 90) {
          FreezePeriod freezePeriod1;
          if (b > 0) {
            freezePeriod1 = list.get(b - 1);
          } else {
            freezePeriod1 = list.get(list.size() - 1);
          } 
          if (freezePeriod1 != freezePeriod) {
            int i;
            if (b == 0 && !freezePeriod1.isWrapped()) {
              i = freezePeriod.mStartDay + 365 - freezePeriod1.mEndDay - 1;
            } else {
              i = freezePeriod.mStartDay - freezePeriod1.mEndDay - 1;
            } 
            if (i < 60) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("Freeze periods ");
              stringBuilder1.append(freezePeriod1);
              stringBuilder1.append(" and ");
              stringBuilder1.append(freezePeriod);
              stringBuilder1.append(" are too close together: ");
              stringBuilder1.append(i);
              stringBuilder1.append(" days apart");
              throw SystemUpdatePolicy.ValidationFailedException.freezePeriodTooClose(stringBuilder1.toString());
            } 
          } 
          b++;
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Freeze period ");
        stringBuilder.append(freezePeriod);
        stringBuilder.append(" is too long: ");
        stringBuilder.append(freezePeriod.getLength());
        stringBuilder.append(" days");
        throw SystemUpdatePolicy.ValidationFailedException.freezePeriodTooLong(stringBuilder.toString());
      } 
      return;
    } 
    throw SystemUpdatePolicy.ValidationFailedException.duplicateOrOverlapPeriods();
  }
  
  boolean after(LocalDate paramLocalDate) {
    boolean bool;
    if (this.mStartDay > dayOfYearDisregardLeapYear(paramLocalDate)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean contains(LocalDate paramLocalDate) {
    int i = dayOfYearDisregardLeapYear(paramLocalDate);
    boolean bool = isWrapped();
    boolean bool1 = false;
    boolean bool2 = false;
    if (!bool) {
      bool1 = bool2;
      if (this.mStartDay <= i) {
        bool1 = bool2;
        if (i <= this.mEndDay)
          bool1 = true; 
      } 
      return bool1;
    } 
    if (this.mStartDay <= i || i <= this.mEndDay)
      bool1 = true; 
    return bool1;
  }
  
  int getEffectiveEndDay() {
    return !isWrapped() ? this.mEndDay : (this.mEndDay + 365);
  }
  
  public MonthDay getEnd() {
    return this.mEnd;
  }
  
  int getLength() {
    return getEffectiveEndDay() - this.mStartDay + 1;
  }
  
  public MonthDay getStart() {
    return this.mStart;
  }
  
  boolean isWrapped() {
    boolean bool;
    if (this.mEndDay < this.mStartDay) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  Pair<LocalDate, LocalDate> toCurrentOrFutureRealDates(LocalDate paramLocalDate) {
    byte b;
    int i = dayOfYearDisregardLeapYear(paramLocalDate);
    if (contains(paramLocalDate)) {
      if (this.mStartDay <= i) {
        i = 0;
        b = isWrapped();
      } else {
        i = -1;
        b = 0;
      } 
    } else if (this.mStartDay > i) {
      i = 0;
      b = isWrapped();
    } else {
      i = 1;
      b = 1;
    } 
    return new Pair(LocalDate.ofYearDay(2001, this.mStartDay).withYear(paramLocalDate.getYear() + i), LocalDate.ofYearDay(2001, this.mEndDay).withYear(paramLocalDate.getYear() + b));
  }
  
  public String toString() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(LocalDate.ofYearDay(2001, this.mStartDay).format(dateTimeFormatter));
    stringBuilder.append(" - ");
    stringBuilder.append(LocalDate.ofYearDay(2001, this.mEndDay).format(dateTimeFormatter));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/FreezePeriod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */