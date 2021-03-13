package android.content.pm.parsing.component;

import android.content.pm.parsing.ParsingPackage;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class ParsedInstrumentationUtils {
  public static ParseResult<ParsedInstrumentation> parseInstrumentation(ParsingPackage paramParsingPackage, Resources paramResources, XmlResourceParser paramXmlResourceParser, boolean paramBoolean, ParseInput paramParseInput) throws IOException, XmlPullParserException {
    ParsedInstrumentation parsedInstrumentation = new ParsedInstrumentation();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<");
    stringBuilder.append(paramXmlResourceParser.getName());
    stringBuilder.append(">");
    String str = stringBuilder.toString();
    TypedArray typedArray = paramResources.obtainAttributes((AttributeSet)paramXmlResourceParser, R.styleable.AndroidManifestInstrumentation);
    try {
      ParseResult<ParsedInstrumentation> parseResult = ParsedComponentUtils.parseComponent(parsedInstrumentation, str, paramParsingPackage, typedArray, paramBoolean, paramParseInput, 7, null, 1, 0, 6, 2, 8);
      paramBoolean = parseResult.isError();
      if (paramBoolean) {
        typedArray.recycle();
        return parseResult;
      } 
      TypedArray typedArray1 = typedArray;
      try {
        parsedInstrumentation.setTargetPackage(typedArray1.getNonResourceString(3));
        parsedInstrumentation.setTargetProcesses(typedArray1.getNonResourceString(9));
        parsedInstrumentation.handleProfiling = typedArray1.getBoolean(4, false);
        parsedInstrumentation.functionalTest = typedArray1.getBoolean(5, false);
        typedArray1.recycle();
        return ComponentParseUtils.parseAllMetaData(paramParsingPackage, paramResources, paramXmlResourceParser, str, parsedInstrumentation, paramParseInput);
      } finally {}
    } finally {}
    typedArray.recycle();
    throw paramParsingPackage;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/parsing/component/ParsedInstrumentationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */