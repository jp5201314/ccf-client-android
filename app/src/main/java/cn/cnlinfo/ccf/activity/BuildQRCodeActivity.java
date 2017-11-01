package cn.cnlinfo.ccf.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.utils.QRCodeUtil;

public class BuildQRCodeActivity extends AppCompatActivity {

    @BindView(R.id.iv_qrCode)
    ImageView ivQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_qrcode);
        ButterKnife.bind(this);
        Bitmap logo = BitmapFactory.decodeResource(getResources(),R.mipmap.ccf);
        Bitmap bitmap = QRCodeUtil.buildQRCode("http:www.baidu.com",128, 128,logo);
        ivQrCode.setImageBitmap(bitmap);
    }
}
