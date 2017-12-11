package cn.cnlinfo.ccf.mvp.model;

import android.widget.Adapter;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public interface ILoadImageModel {
    void startLoadImage(IGetImageDataListener listener);

    interface IGetImageDataListener{
        void onLoadCompleted(Adapter adapter);
    }
}
