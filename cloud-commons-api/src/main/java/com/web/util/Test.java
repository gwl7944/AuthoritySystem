package com.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.web.util
 * @ClassName: Test
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/25 15:33
 * @Version: 1.0
 */


public class Test {


    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String str = System.lineSeparator()+s;
                StringBuffer sbsb = new StringBuffer();
                sbsb.append(str);
                if(sbsb.toString().contains("付")){
                    int ssa = getCharacterPosition(sbsb.toString(), "付");
                    String substring = str.substring(0, ssa+1);
                    sbsb.delete(0,sbsb.length());
                    sbsb.append(substring);
                }

                //去掉支付方式
                String payway = getPayway(sbsb.toString());
                String replace1 = sbsb.toString().replace(payway, "");

                //去掉单号
                String s1 = tqNumber(replace1);
                String replace = replace1.replace(s1, "");

                //去掉车牌号
                String checkchepai = checkchepai(replace);
                String[] split = checkchepai.split(",");
                StringBuffer repss = new StringBuffer();
                repss.append(replace);
                for (String sps:split){
                    String replace2 = repss.toString().replace(sps, "");
                    repss.delete(0,repss.length());
                    repss.append(replace2);
                }

                //去掉手机号
                String reqcss = repss.toString();
                String s2 = checkNum(reqcss);
                String replace2 = reqcss.replace(s2, "");

                //去掉保险
                String baoxian = getBaoxian(replace2);
                String replace3 = replace2.replace(baoxian, "");

                System.out.println(replace3);
                result.append(str);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }


    public static String txt2String22(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String str = System.lineSeparator()+s;

                String getcar = getcar(str);
                String replace = str.replace(getcar, "");

                Pattern p1 = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher m1 = p1.matcher(replace);


                if (m1.find()) {
                    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                    Matcher m = p.matcher(replace);
                    int nums = 0;
                    while(m.find()) {
                        nums= m.start();
                        //当"/"符号第i次出现的位置
                    }
                    String substring = replace.substring(0, nums+1);

                    if (replace.length()>nums+2){
                        String substring1 = replace.substring(nums+2,replace.length()-1);
                        System.out.print(substring+"\t"+substring1);
                    }else {
                        System.out.print(substring);
                    }
                }else{
                    System.out.print(replace);
                }






                result.append(str);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 提取车型
     * */
    public static String getcar(String str){
        String[] stss = new String[]{"普锐斯","卡罗拉","宝骏510","五菱宝骏系列","新宏光SRS-3","宝骏RS-3","宝骏360","荣光新卡1.5双排","别克君威",
                "威驰","荣放","普拉多","五菱之光小卡1.2单排","宝骏730","五菱宏光V1.5劲取","五菱荣光小卡1.5双排","五菱宏光V1.5劲取","宏光S",
                "--","五菱荣光S1.2基本","卡罗拉双擎","奕泽","亚洲龙","宏光S3","长安","塞纳","皇冠","别克","威驰FS","兰德酷路泽","本田","其他",
                "五菱之光V",	"五菱新卡单排","花冠","现代","宝骏530","宝骏310","五菱征程","荣光新卡","宝骏RM-5","汉兰达","雷克萨斯","五菱荣光V",
                "五菱之光","宝骏560","五菱荣光RC-6","五菱宏光PLUS","卡罗拉双擎","埃尔法","宝骏310W","宝骏RM-5","锐志","大众","五菱宏光",
                "宝骏RS-5","五菱厢式","荣光新卡1.5单排","荣光新卡1.8单排","五菱宏光V封窗","三菱","荣光新卡1.8双排","酷路泽","荣光小卡1.5封箱","迷你","新车"};

        for (String ss:stss){
            if (str.contains(ss)){
                return ss;
            }
        }
        return "";

    }




    /**
     * 提取车牌号
     * */
    public static int getCharacterPosition(String url,String str){
        //这里是获取"/"符号的位置	lastindexof从字符串末尾开始检索，检索到子字符
        Matcher slashMatcher = Pattern.compile(str).matcher(url);
        int mIdx = 0;
        int nums = 0;
        while(slashMatcher.find()) {
            mIdx++;
            nums= slashMatcher.start();
            //当"/"符号第i次出现的位置

            /*if(mIdx == 1){
                break;
            }*/
        }
        return nums;
    }

    /**
     * 提取车牌2
     * **/
    public static String checkchepai(String num){
        if (num == null || num.length() == 0 ){ return "" ;}
        Pattern pattern = Pattern.compile( "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}(([A-HJ-Z]{1}[A-HJ-NP-Z0-9]{5})|([A-HJ-Z]{1}(([DF]{1}[A-HJ-NP-Z0-9]{1}[0-9]{4})|([0-9]{5}[DF]{1})))|([A-HJ-Z]{1}[A-D0-9]{1}[0-9]{3}警)))|([0-9]{6}使)|((([沪粤川云桂鄂陕蒙藏黑辽渝]{1}A)|鲁B|闽D|蒙E|蒙H)[0-9]{4}领)|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})" );
        Matcher matcher = pattern.matcher(num);
        StringBuffer bf = new StringBuffer( 64 );
        while (matcher.find()) {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(matcher.group());
            if (m.find()) {
                bf.append(matcher.group()+",");
            }
        }
        int len = bf.length();
        if (len > 0 ) {
            bf.deleteCharAt(len - 1 );
        }
        return bf.toString();
    }




    /**
     * 提取支付方式
     * */
    public static String getPayway(String str){
        String[] stss = new String[]{"现金支付","支付宝支付","微信支付","刷卡支付" ,"转账支付","支付"};

        for (String ss:stss){
            if (str.contains(ss)){
                return ss;
            }
        }
        return "";

    }

    /**
     * 提取保险
     * */
    public static String getBaoxian(String str){
        String[] stss = new String[]{"人寿保险","平安保险","太平洋保险","中保","天安","永安","人保","紫金","阳光","中华联合","安盛天平","太平"};

        for (String ss:stss){
            if (str.contains(ss)){
                return ss;
            }
        }
        return "";

    }


    /**
     * 提取电话号
     * **/
    public static String checkNum(String num){
        if (num == null || num.length() == 0 ){ return "" ;}
        Pattern pattern = Pattern.compile( "(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)" );
        Matcher matcher = pattern.matcher(num);
        StringBuffer bf = new StringBuffer( 64 );
        while (matcher.find()) {
            bf.append(matcher.group()).append( "," );
        }
        int len = bf.length();
        if (len > 0 ) {
            bf.deleteCharAt(len - 1 );
        }
        return bf.toString();
    }

   /**
    *  提取单号
    * */
    public static String tqNumber(String str){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        if (str.length()>15){
            String out = str.substring(0,16);

            Matcher m = p.matcher(out);
            if (m.find()) {
                Matcher m1 = p.matcher(str.substring(0,15));
                if (m1.find()){
                    return str.substring(0,14);
                }else {
                    return str.substring(0,15);
                }
            }
            return out;
        }else {
            return str;
        }

    }


    public static void main(String[] args) {
        //File file = new File("E:/test1.txt");
        File file = new File("E:/t3.txt");
        String stt = txt2String22(file);
        //System.out.println(stt);
    }

}
