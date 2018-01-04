package cn.cnlinfo.ccf.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.tendcloud.tenddata.TCAgent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by JP on 2017/10/11 0011.
 */

public class TradingCenterFragment extends BaseFragment {

    @BindView(R.id.chart_top)
    LineChartView chartTop;
    @BindView(R.id.sp_hang_sell_type)
    Spinner spHangSellType;
    @BindView(R.id.et_num)
    CleanEditText etNum;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private Unbinder unbinder;
    String[] date;//X轴的标注
    Float[] score;//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private int type = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trading_center, container, false);
        unbinder = ButterKnife.bind(this, view);
        TCAgent.onPageStart(getActivity(), "交易中心");
        getDayOfMonth();
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
        startHangSellOrBuy();//挂卖/挂买
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = etNum.getText().toString();
                if (!TextUtils.isEmpty(num)){
                    if (type==0){//挂卖
                        RequestParams params = new RequestParams();
                        params.addFormDataPart("sellerID", UserSharedPreference.getInstance().getUser().getUserID());
                        params.addFormDataPart("ccfValue",num);
                        HttpRequest.post(Constant.OPERATE_CCF_HOST + API.HANGSELL, params, new CCFHttpRequestCallback() {
                            @Override
                            protected void onDataSuccess(JSONObject data) {
                                showMessage(0,"挂卖成功");
                            }

                            @Override
                            protected void onDataError(int code, boolean flag, String msg) {
                                showMessage(code,msg);
                            }
                        });
                    }else {//挂买

                    }
                }else {
                    toast("输入框不能为空");
                }
            }
        });
        return view;
    }

    /**
     * 开始挂卖/挂买
     */

    private void startHangSellOrBuy(){
        spHangSellType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }


    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(getResources().getColor(R.color.color_blue_33b5e5));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        //line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.BLACK);  //设置字体颜色
        axisX.setName("时间/天");  //表格名称
        axisX.setTextSize(15);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("价格P(美金)");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.BLACK);

        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        chartTop.setInteractive(true);
        chartTop.setZoomType(ZoomType.HORIZONTAL);
        chartTop.setMaxZoom((float) 2);//最大方法比例
        chartTop.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chartTop.setLineChartData(data);
        chartTop.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(chartTop.getMaximumViewport());
        v.left = 0;
        v.right = 10;
        chartTop.setCurrentViewport(v);

        chartTop.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Logger.d(lineIndex + ":" + pointIndex);
                Logger.d(date[pointIndex] + "的值为" + value.getY());
                toast(date[pointIndex] + "的值为" + value.getY());
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        TCAgent.onPageEnd(getActivity(), "交易中心");
    }

    /*
        *
         * 根据当前月份获取当前月份的天数
         * @return
    */
    private void getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int month = aCalendar.get(Calendar.MONTH);
        int day = aCalendar.get(Calendar.DAY_OF_MONTH);
        Logger.d(month + "-" + day);
        date = new String[day];
        score = new Float[day];
        for (int i = 1; i <= day; i++) {
            date[i - 1] = month + "-" + i;
            score[i - 1] = i / 10f;
        }
    }
}
