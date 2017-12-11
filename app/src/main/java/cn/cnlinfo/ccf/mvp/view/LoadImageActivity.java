package cn.cnlinfo.ccf.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.mvp.presenter.LoadImagePresenter;

public class LoadImageActivity extends AppCompatActivity implements ILoadImageActivity{
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        listView = this.findViewById(R.id.lv_show_image);
        showImage();
    }

    @Override
    public void showImage() {
        LoadImagePresenter loadImagePresenter = new LoadImagePresenter(this);
        loadImagePresenter.showData(listView);
    }
}
