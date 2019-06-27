package com.core.word;


import com.core.utils.StringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Auther: SunBC
 * @Date: 2019/6/18 11:11
 * @Description:
 */
public class XmlParserUtils {
    /**
     * description: 校验大括号是否匹配
     * @auther: SunBC
     * @date: 2019/6/18 14:34
     */
    public static boolean isInvalidOfBrace(String data){
        data = StringUtil.removeInvisibleChar(data);
        char[] chars = data.toCharArray();
        int Brace_L_Num = 0;
        int Brace_R_Num = 0;
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i] == '{') Brace_L_Num++;
            if (chars[i] == '}') Brace_R_Num++;
        }
        if (Brace_R_Num == Brace_L_Num) return true;
        return false;
    }
    /**
     * 校验[##   ##]是否有效
     *  description:
     * @auther: SunBC
     * @date: 2019/6/27 16:01
     */
    public static boolean isInvalidOfSingleBracket(String data){
        data = StringUtil.removeInvisibleChar(data);
        char[] chars = data.toCharArray();
        int Brace_L_Num = 0;
        int Brace_R_Num = 0;
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i] == '{') Brace_L_Num++;
            if (chars[i] == '}') Brace_R_Num++;
        }
        if (Brace_R_Num == Brace_L_Num) return true;
        return false;
    }
    /**
     * description: 校验#是否匹配
     * @auther: SunBC
     * @date: 2019/6/18 14:38
     */
    public static boolean VarifyPound(String data){
        char[] chars = data.toCharArray();
        int Pound_Num = 0;
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i] == '#') Pound_Num++;
        }
        if (Pound_Num%2 != 0) return false;
        return true;
    }
    /**
     * description:校验方括号是否匹配
     * @auther: SunBC
     * @date: 2019/6/18 14:38
     */
    public static boolean VarifyBracket(String data){
        char[] chars = data.toCharArray();
        int Bracket_L_Num = 0;
        int Bracket_R_Num = 0;
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i] == '[') Bracket_L_Num++;
            if (chars[i] == ']') Bracket_R_Num++;
        }
        if (Bracket_R_Num != Bracket_L_Num) return false;
        return true;
    }

    public static String VarifyAll(String data){
        data = StringUtil.removeInvisibleChar(data);
        String errorInfor = "";
        int length = data.length();
        if (length == 0) return null;
        int x = data.indexOf('*');
        int j = data.indexOf('#');
        int a = data.indexOf('#');
        int f = data.indexOf('[');
        int d = data.indexOf('{');
        int f_ = data.indexOf('[');
        int d_ = data.indexOf('{');
        if (x>d || x>f)  {
            int i = 15;
            substringBeforeAfterSize(data, x, i);
        }
        char[] chars = data.toCharArray();
        ArrayList<Character> stack = new ArrayList<Character>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(true){}
        }

        return null;
    }

    private static String substringBeforeAfterSize(String data, int i, int size) {
        String s;
        String pre = "";
        String sub = "";
        int l = data.length();
        if (l == 0) return "";
        if (i>l-1) return data;
        if (i < size) pre = data.substring(0,i);
        else pre = data.substring(i-15,i);
        if (i > l -size) sub = data.substring(i, l -1);
        else  sub = data.substring(i, i+15);
        return pre+sub;
    }

    /**
     * description: 验证是否存在占位符
     * @auther: SunBC
     * @date: 2019/6/18 19:13
     */
    public static boolean ContainPlaceHolder(String data){
        for (String ph :PlaceHolder.PHARR) {
            if (data.contains(ph)) return true;
        }
        return false;
    }


    public static String substringBefore(final String str, final String separator) {
        if (str == null || str.length() == 0 || separator == null) {
            return str;
        }
        if (separator.isEmpty()) {
            return "";
        }
        final int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String substringBeforeLast(final String str, final String separator) {
        if (str == null || "".equals(str) || separator == null || "".equals(separator)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String substringBetween(final String str, final String open, final String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start != -1) {
            final int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }
    /**
     ** <pre>
     *      * (null, *)      = null
     *      * ("", *)        = ""
     *      * (*, null)      = ""
     *      * ("abc", "a")   = "bc"
     *      * ("abcba", "b") = "cba"
     *      * ("abc", "c")   = ""
     *      * ("abc", "d")   = ""
     *      * ("abc", "")    = "abc"

     */
    public static String substringAfter(final String str, final String separator) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (separator == null) {
            return "";
        }
        final int pos = str.indexOf(separator);
        if (pos == -1) {
            return "";
        }
        return str.substring(pos + separator.length());
    }
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null ||open == null || open.length() == 0 || close == null || close.length() == 0) {
            return null;
        }
        final int strLen = str.length();
        if (strLen == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String [list.size()]);
    }

    public static Element AddParentNode(Element ele, String parentName, HashMap<String ,String > Attr){
        if (ele == null) return null;
        Element parent = ele.getParent();
        List elements = parent.elements();
        int eleIndex = elements.indexOf(ele);
        ArrayList<Element> elementsPrefix = new ArrayList<>();
        ArrayList<Element> elementsSubfix = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            Element e = (Element) elements.get(i);
            if (i <eleIndex)
                elementsPrefix.add(e);
            if (eleIndex <i)
                elementsSubfix.add(e);
            parent.remove(e);
        }

        for (int i = 0; i <elementsPrefix.size() ; i++) {
            Element element = elementsPrefix.get(i);
            parent.add(element);
        }
        Element parentNameEle = parent.addElement(parentName);
        Set<String> keyset = Attr.keySet();
        for (String key:keyset){
            parentNameEle.addAttribute(key,Attr.get(key));
        }
        parentNameEle.add(ele);
        for (int i = 0; i <elementsSubfix.size() ; i++) {
            Element element = elementsSubfix.get(i);
            parent.add(element);
        }
        return parentNameEle;
    }
    public static Element AddParentNode(Element beginEle, Element endEle, String name, HashMap<String, String> attMap) {
        if (beginEle == null || endEle == null) return null;
        Element beginEleParent = beginEle.getParent();
        Element endEleParent = endEle.getParent();
        if (!beginEleParent.equals(endEleParent))  throw new RuntimeException("模板占位符格式不正确：-----"+beginEle.getText()+"-----部分的占位符起始符与结束符不同级");
        List elements = beginEleParent.elements();
        ArrayList<Element> elementPrefixArr = new ArrayList<>();
        ArrayList<Element> elementArr = new ArrayList<>();
        ArrayList<Element> elementSubfixArr = new ArrayList<>();
        int beginIndex = elements.indexOf(beginEle);
        int endIndex = elements.indexOf(endEle);
        for (int j = 0; j < elements.size(); j++) {
            Element e = (Element)elements.get(j);
            if (j<beginIndex)elementPrefixArr.add(e);
            else if (j > endIndex) elementSubfixArr.add(e);
            else elementArr.add(e);
            beginEleParent.remove(e);
        }
        for (int j = 0; j < elementPrefixArr.size(); j++) {
            beginEleParent.add(elementPrefixArr.get(j));
        }

        Element element = beginEleParent.addElement(name);
        Set<String> keyset = attMap.keySet();
        for (String key:keyset){
            element.addAttribute(key,attMap.get(key));
        }
        for (int j = 0; j < elementArr.size(); j++) {
            element.add(elementArr.get(j));
        }
        for (int j = 0; j < elementSubfixArr.size(); j++) {
            beginEleParent.add(elementSubfixArr.get(j));
        }
        return element;
    }

}