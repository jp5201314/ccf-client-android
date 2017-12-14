package cn.cnlinfo.ccf.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
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
        startLoadData();
    }

    private void startLoadData() {
        LoadImagePresenter loadImagePresenter = new LoadImagePresenter(this,this);
        loadImagePresenter.showData();
    }

    /**
     * 给listview设置适配器
     * @param adapter
     */
    @Override
    public void showImage(ListAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
