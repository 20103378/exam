package com.example.utils;//package com.example.utils;
//
///**
// * @author : najiang
// * create at:  2020-03-18  17:12
// * @description:
// */
//public class StringTool {
//    public static String replace(String original ,String find,String replace)
//    {
//        if (original==null || find==null ||replace==null)
//        {
//            return original;
//        }
//        int findLen = find.length();
//        int originalLen = original.length();
//        if (originalLen==0 || findLen==0)
//        {
//            return original;
//        }
//        StringBuffer sb = new StringBuffer();
//        int begin = 0; //下次检索开始的位置
//        int i = original.indexOf(find); //找到的子串位置
//        while (i!=-1)
//        {
//            sb.append(original.substring(begin,i));
//            sb.append(replace);
//            begin = i + findLen;
//            i = original.indexOf(find,begin);
//        }
//        if (begin<originalLen)
//        {
//            sb.append(original.substring(begin));
//        }
//        return sb.toString();
//    }
//}