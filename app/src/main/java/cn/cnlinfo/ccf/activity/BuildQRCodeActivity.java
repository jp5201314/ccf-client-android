package cn.cnlinfo.ccf.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.utils.QRCodeUtil;

public class BuildQRCodeActivity extends AppCompatActivity {

    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibt_add)
    ImageButton ibtAdd;
    @BindView(R.id.iv_qrCode)
    ImageView ivQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_qrcode);
        ButterKnife.bind(this);
        tvTitle.setText("我的二维码");
        ibtAdd.setVisibility(View.INVISIBLE);
        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ccf);
        Bitmap bitmap = QRCodeUtil.buildQRCode("http://ccf.hrkji.com/regUsers.aspx?pm=281677A216162405", 164, 164, logo);
        ivQrCode.setImageBitmap(bitmap);
    }
}
