package cn.cnlinfo.ccf;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class API {

    /**
     * 测试get方法地址
     * https://api.douban.com/v2/movie/top250?start=0&count=10
     */

    /**
     * 测试post方法地址
     * http://ccf.hrkji.com/RegUser.asmx/Login
     * username
     * password
     */

    /**
     * 登录
     */
    public static final String CCFLOGIN = "Login";

    /**
     * 注册
     */
    public static final String CCFREGISTER = "Register";

    /**
     * 设置密保
     */
    public static final String CCFSETENCRYPTED = "SetSafeQuestion";
    /**
     * 验证密保
     */
    public static final String CCFVERFYENCRYPTED = "SecurityQuestionIsTrue";

    /**
     * 重置密码
     */
    public static final String CCFRESETPASSWORD = "ChangePwdByQuestion";
}
