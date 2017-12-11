package cn.cnlinfo.ccf.mvp.presenter;

import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import cn.cnlinfo.ccf.mvp.model.ILoadImageModel;
import cn.cnlinfo.ccf.mvp.model.LoadImageModel;
import cn.cnlinfo.ccf.mvp.view.ILoadImageActivity;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class LoadImagePresenter {

    private ILoadImageActivity iLoadImageActivity;
    private ILoadImageModel iLoadImageModel;

    public LoadImagePresenter(ILoadImageActivity loadImageActivity){
        this.iLoadImageActivity = loadImageActivity;
    }

    public void showData(final View view){
        if (view instanceof ListView){
            iLoadImageModel = new LoadImageModel();
            iLoadImageModel.startLoadImage(new ILoadImageModel.IGetImageDataListener() {
                @Override
                public void onLoadCompleted(Adapter adapter) {
                    ((ListView) view).setAdapter((ListAdapter) adapter);
                }
            });
        }
    }


}
