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
    /**
     * 得到当前用户的贡献图
     */
    public static final String USERCONTRIBUTEMAP = "GetAtlasByUserID ";
    /**
     * 得到用户的子节点数据
     */
    public static final String USERSHARELIST = "GetShareList";

    /**
     * 用户兑换循环包
     */
    public static final String CONVERSIONCYCLEPACKAGE = "ExchangePackage";

    /**
     *得到跑步排名
     */
    public static final String GETRUNNINGRANK = "GetTop10UserS";

    /**
     * 得到我的参数
     */
    public static final String GETMYPARAMETER = "GetMyCanshu";
    /**
     * 买家在拍卖行中点击购买
     */

    public static final String AUCTIONHOUSE = "BuyerClickToBuy";

    /**
     * 用户挂买操作(不扣手续费)
     */

    public static final String SAILOPERATION = "HangBuyOperation";

    /**
     * PersonalAuction 卖家挂卖到拍卖行(新)
     */

    public static final String SELLHANGTOAUCTIONHOUSE = "PersonalAuction";

    /**
     * SP_UserLargess 向他人转碳控因子、循环包、循环卷
     */
    public static final String EXCHANGECCF = "SP_UserLargess";

    /**
     * SellerClickToSale 卖家在拍卖行中点击出售
     */
    public static final String SELLERTOSELLINAUCTIONHOUSE = "SellerClickToSale";

    /**
     * SellerSendCCF 订单产生后,卖家手动确认打款金额并拨币
     */
    public static final String SELLERSENDCCF = "SellerSendCCF";

    /**
     * SellersComplainBuyer 卖家投诉买家
     */

    public static final String SELLERCOMPLAINSBUYER = "SellersComplainBuyer";

    /**
     *获取套餐列表
     */

    public static final String GETPACKAGELIST = "GetMealList";

    /**
     *获取新闻公告内容
     */

    public static final String GETNEWSNOTICE = "GetNewBody";

    /**
     * 上传步数
     */
    public static final String UPLOADSTEP = "UpStep";
    /**
     * 用户持有量上限
     */

    public static final String USERLIMIT = "Userlimit";
    /**
     *获取新闻公告列表
     */

    public static final String GETNEWSLIST = "GetNewlist";
    /**
     * 获取平台信息
     */
    public static final String GETPLATFORMINFO = "Getplatforminfo";
    /**
     * 获取个人信息
     */
    public static final String GETPERSONINFO = "GetUserInfo";
    /**
     * 获取个人信息
     */
    public static final String GETUSERINFO = "GetUserinfo";
    /**
     * 获取兑换循环包参数
     */

    public static final String GETCIRCLE = "GetCircle";
    /**
     * 用户购买套餐
     */

    public static final String PURCHASEMEAL = "UpgradeToUserStar";

    /**
     * 发送短信验证码
     * codeid:   3143331  密码找回
     3126312 注册
     */
    public static final String SENDCODE = "SendCode";

    /**
     * 电话号码短信验证找回密码
     */
    public static final String RETRIEVEPASSWORD = "RetrievePassword";


}
