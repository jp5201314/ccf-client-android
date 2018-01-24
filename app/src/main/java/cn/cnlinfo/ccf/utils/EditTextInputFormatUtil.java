package cn.cnlinfo.ccf.utils;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class EditTextInputFormatUtil {
    /**
       移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
       联通：130、131、132、152、155、156、185、186
       电信：133、153、180、189、（1349卫通）
       总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
   */
    public static boolean isLegalPhoneNum(String phone){
        String patter = "[1][358]\\d{9}";
        return phone.matches(patter);
    }

    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public static boolean isLegalName(String name){
        if (name.contains("·") || name.contains("•")){
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }
    }
    /**
     * 验证输入的身份证号是否合法
     */
    public static boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }
}
