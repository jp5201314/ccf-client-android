package cn.cnlinfo.ccf.activity;

import android.os.Bundle;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;

public class PreviewSaveActivity extends BaseActivity {

    @BindView(R.id.iv_icon)
    PhotoView ivIcon;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_save);
        unbinder = ButterKnife.bind(this);
        ivIcon.enable();
        Glide.with(this).load(getIntent().getStringExtra("url")).asBitmap().centerCrop().into(ivIcon);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
