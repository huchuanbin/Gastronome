package com.hcb.gastronome.util;

/**
 * Created by valuesfeng on 14-9-1.
 */

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 正则工具类
 * 提供验证邮箱、手机号、电话号码、身份证号码、数字等方法
 */
public final class RegexUtils {


    /**
     * 验证字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 去除 空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 验证用户名
     *
     * @param userName 用户名
     * @return
     */
    public static boolean isUserNmaeRule(String userName) {
        if (isEmpty(userName)) {
            return false;
        }
        String reges = "^(?!_)(?!.*?_$)(?!-)(?!.*?-$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
//        String regex = " ^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,10}+$";
        return Pattern.matches(reges, userName);
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
//        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex, idCard);
    }

    public static String deleteString(String str, String deleteStr) {
        return str.replaceAll(deleteStr, "");
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、177,189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(CharSequence mobile) {
//        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        String regex = "(\\+\\d+)?1\\d{10}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *              数字之后是空格分隔的国家（地区）代码。</p>
     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

    public static String clearChar(String content) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(content);
        content = m.replaceAll("");
        return content;
    }

    private static Pattern alPattern = Pattern.compile("@\\w+");
    private static Pattern talkPattern = Pattern.compile("#\\w+#");
    private static Pattern urlPattern = Pattern.compile(
            "((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
                    + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
                    + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
                    + "((?:(?:[" + Patterns.GOOD_IRI_CHAR + "][" + Patterns.GOOD_IRI_CHAR + "\\-]{0,64}\\.)+"   // named host
                    + Patterns.TOP_LEVEL_DOMAIN_STR_FOR_WEB_URL
                    + "|(?:(?:25[0-5]|2[0-4]" // or ip address
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]"
                    + "|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9])))"
                    + "(?:\\:\\d{1,5})?)" // plus option port number
                    + "(\\/(?:(?:[" + Patterns.GOOD_IRI_CHAR + "\\;\\/\\?\\:\\@\\&\\=\\#\\~"  // plus option query params
                    + "\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?"
                    + "(?:\\b|$)");

//    public static void checkLink(final TextView view) {
//        view.setAutoLinkMask(0);
////        Linkify.addLinks(view, talkPattern, Constant.TALK_SCHEMA);
//        Linkify.addLinks(view, urlPattern, Constant.HTTP_SCHEMA, new Linkify.MatchFilter() {
//            @Override
//            public boolean acceptMatch(CharSequence s, int start, int end) {
//                if (s.toString().contains("http://") || s.toString().contains("https://")) {
//                    view.setOnClickListener(null);
//                    return true;
//                }
//                return false;
//            }
//        }, null);
//        if (MainApp.getInstance().getUser() != null)
//            Linkify.addLinks(view, alPattern, Constant.ALT_SCHEMA, new Linkify.MatchFilter() {
//                @Override
//                public boolean acceptMatch(CharSequence s, int start, int end) {
//                    if (s.toString().contains("@")) {
//                        view.setOnClickListener(null);
//                        return true;
//                    }
//                    return false;
//                }
//            }, null);
//        try {
////            Spannable s = (Spannable) view.getText();
//            SpannableString s = new SpannableString(view.getText());
//            URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
//            for (URLSpan span : spans) {
//                int start = s.getSpanStart(span);
//                int end = s.getSpanEnd(span);
//                s.removeSpan(span);
//                span = new URLSpanNoUnderline(span.getURL());
//                s.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            view.setText(s);
//            view.setMovementMethod(LinkMovementMethod.getInstance());
//        } catch (ClassCastException e) {
//            e.printStackTrace();
//        }
//    }

    //转义/**  */
    public static String replaceChar(String text) {
        Pattern p = Pattern.compile("/\\*\\*.*\\*/");
        Matcher m = p.matcher(text);
        return m.replaceAll("");
    }

    private static final String like = "@.+\\s";
    private static final String tag = "#.+1#";

    public static String replaceLikeAndTag(String text) {
        Pattern p = Pattern.compile("[@.+\\s[#.+#]] ");
        Matcher m = p.matcher(text);
        return m.replaceAll("");
    }

    public static String replaceTag(String text) {
        Pattern p = Pattern.compile("#");
        Matcher m = p.matcher(text);
        return m.replaceAll("");
    }

    public static String replaceAt(String text) {
        Pattern p = Pattern.compile("@");
        Matcher m = p.matcher(text);
        return m.replaceAll("");
    }

//    public boolean String matcherUser(String text) {

//        String all  = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,10}{1}quot;;//{2,10}表示字符的长度是2-10
//        return Pattern.compile(all);
//        boolean tf = pattern.matcher("12345").matches();
//    }

//    public static String replaceTag(String text) {
//        Pattern p = Pattern.compile("#");
//        Matcher m = p.matcher(text);
//        return m.replaceAll("");
//    }

    //    public static String addChar(String text) {
//        Pattern p = Pattern.compile("/\\*\\*.*\\*/");
//        Matcher m = p.matcher(text);
//        //content = m.replaceAll("")
//        return m.replaceAll("");
//    }
    public static boolean verifyUserName(String text) {
        String all = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-]{1,30}$";
        Pattern pattern = Pattern.compile(all);
        return pattern.matcher(text).matches();
//            return pattern.matcher(text).matches();
//            return m.replaceAll("");
    }

    public static boolean verifyPassWord(String text) {
        String all = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-]{1,30}$";
        Pattern pattern = Pattern.compile(all);
        return pattern.matcher(text).matches();
//            return pattern.matcher(text).matches();
//            return m.replaceAll("");
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}