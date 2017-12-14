package cn.cnlinfo.ccf.mvp.model;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by JP on 2017/12/7 0007.
 */

public class LoadImageModel implements ILoadImageModel{
    private Context context;

    public LoadImageModel(Context context){
        this.context = context;
    }
    @Override
    public void startLoadImage(IGetImageDataListener listener) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,new String[]{"1","2","3","4","5","6","7","8"});
        listener.onLoadCompleted(arrayAdapter);
    }
}
