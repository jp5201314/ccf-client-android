package cn.cnlinfo.ccf;

import cn.cnlinfo.ccf.manager.PhoneManager;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class Constant {

    public static final String APP_DIR = PhoneManager.getSdCardRootPath() + "/ccf/";//app文件目录
    public static final String IMAGE_CACHE_DIR_PATH = APP_DIR + "cache/";// 图片缓存地址
    public static final String UPLOAD_FILES_DIR_PATH = APP_DIR + "upload/";//上传文件、零时文件存放地址
    public static final String DOWNLOAD_DIR_PATH = APP_DIR + "downloads/";// 下载文件存放地址
}
