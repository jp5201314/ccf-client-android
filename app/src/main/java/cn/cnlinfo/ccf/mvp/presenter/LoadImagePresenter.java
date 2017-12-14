package cn.cnlinfo.ccf.mvp.presenter;

import android.content.Context;
import android.widget.ListAdapter;

import cn.cnlinfo.ccf.mvp.model.ILoadImageModel;
import cn.cnlinfo.ccf.mvp.model.LoadImageModel;
import cn.cnlinfo.ccf.mvp.view.ILoadImageActivity;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class LoadImagePresenter {
    private Context context;
    private ILoadImageActivity iLoadImageActivity;
    private ILoadImageModel iLoadImageModel;

    public LoadImagePresenter(Context context,ILoadImageActivity loadImageActivity){
        this.context = context;
        this.iLoadImageActivity = loadImageActivity;
    }

    public void showData() {
        iLoadImageModel = new LoadImageModel(context);
        iLoadImageModel.startLoadImage(new ILoadImageModel.IGetImageDataListener() {
            @Override
            public void onLoadCompleted(ListAdapter adapter) {
                iLoadImageActivity.showImage(adapter);
            }
        });
    }
}
