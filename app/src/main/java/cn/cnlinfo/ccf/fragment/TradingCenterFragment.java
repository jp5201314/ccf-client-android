package cn.cnlinfo.ccf.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.shizhefei.mvc.IDataAdapter;
import com.shizhefei.mvc.MVCHelper;
import com.tendcloud.tenddata.TCAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.API;
import cn.cnlinfo.ccf.Constant;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.activity.TradingCenterActivity;
import cn.cnlinfo.ccf.entity.ItemPriceListEntity;
import cn.cnlinfo.ccf.mvc.datasource.PriceChartDataSource;
import cn.cnlinfo.ccf.mvc.helper.MVCUltraHelper;
import cn.cnlinfo.ccf.net_okhttpfinal.CCFHttpRequestCallback;
import cn.cnlinfo.ccf.view.CleanEditText;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
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

public class TradingCenterFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.chart_top)
    LineChartView chartTop;
    @BindView(R.id.sp_hang_sell_type)
    Spinner spHangSellType;
    @BindView(R.id.et_num)
    CleanEditText etNum;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_enter_trading_platform)
    Button btnEnterTradingPlatform;
    @BindView(R.id.ptr)
    PtrClassicFrameLayout ptr;
    private Unbinder unbinder;
    String[] date;//X轴的标注
    Float[] score;//图表的数据点,y轴的数据标注
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private int type = 0;
    private MVCHelper mvcHelper;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_trading_center);
        unbinder = ButterKnife.bind(this, getContentView());
        TCAgent.onPageStart(getActivity(), "交易中心");
        getPriceList();//获取价格变动列表，绘制价格走势图
        startHangSellOrBuy();//挂卖/挂买
        setEditTextFocus(etNum);
        btnEnterTradingPlatform.setOnClickListener(this);
        btnOk.setOnClickListener(this);

    }

    private void getPriceList(){
        mvcHelper = new MVCUltraHelper<List<ItemPriceListEntity>>(ptr);
        mvcHelper.setNeedCheckNetwork(true);
        mvcHelper.setDataSource(new PriceChartDataSource());
        mvcHelper.setAdapter(new IDataAdapter<List<ItemPriceListEntity>>() {
            @Override
            public void notifyDataChanged(List<ItemPriceListEntity> itemPriceListEntities, boolean isRefresh) {
                Logger.d(itemPriceListEntities.toString());
                getDayOfMonth(itemPriceListEntities);
            }

            @Override
            public List<ItemPriceListEntity> getData() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
        mvcHelper.refresh();

    }
    /**
     * 开始挂卖/挂买
     */

    private void startHangSellOrBuy() {
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
            Logger.d(date[i].toString());
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            Logger.d(score[i]);
            mPointValues.add(new PointValue(i, score[i]));
        }
    }


    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(getResources().getColor(R.color.color_blue_33b5e5));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.DIAMOND);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(true);//曲线是否平滑，即是曲线还是折线
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
        axisX.setHasLines(true); //x 轴分割线,设置是否显示坐标网格。
        //axisX.setInside(true);//设置是否将轴坐标的值显示在图表内侧。
        //axisX.setAutoGenerated(true);//设置是否自动生成轴对象，自动适应表格的范围。
        axisX.setHasSeparationLine(true);//设置是否显示轴标签与图表之间的分割线
        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis().setHasLines(true);  //Y轴
        axisY.setName("价格P(美金)");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.BLACK);
        //axisY.setInside(true);//设置是否将轴坐标的值显示在图表内侧。
        axisY.setAutoGenerated(true);//设置是否自动生成轴对象，自动适应表格的范围。
        axisY.setHasSeparationLine(true);//设置是否显示轴标签与图表之间的分割线
        axisY.setFormatter(new SimpleAxisValueFormatter());//标签返回的数据格式
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        data.setValueLabelTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//设置标签文字字体
        data.setValueLabelBackgroundAuto(true);//设置是否自动绘制标签背景

        //设置行为属性，支持缩放、滑动以及平移
        chartTop.setInteractive(true);
        chartTop.setZoomType(ZoomType.HORIZONTAL);
        chartTop.setMaxZoom((float) 2);//最大放大比例
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
                Logger.d(date[pointIndex] + "ccf的价格为  " + value.getY());
                toast(date[pointIndex] + "ccf的价格为  " + value.getY()+"美元");
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        unbinder.unbind();
        TCAgent.onPageEnd(getActivity(), "交易中心");
        HttpRequest.cancel(Constant.OPERATE_CCF_HOST + API.HANGSELL);
        HttpRequest.cancel(Constant.PRICE_LIST_HOST + API.PRICELIST);
    }

    /*
    *
     * 根据当前月份获取当前月份的天数
     * @return
    */
    private void getDayOfMonth(List<ItemPriceListEntity> itemPriceListEntities) {
        date = new String[itemPriceListEntities.size()];
        score = new Float[itemPriceListEntities.size()];
        for (int i = 0; i < itemPriceListEntities.size(); i++) {
            date[i] = itemPriceListEntities.get(i).getAddTime();
            score[i] = (float) itemPriceListEntities.get(i).getPrice();
        }
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                String num = etNum.getText().toString();
                if (!TextUtils.isEmpty(num)) {
                    if (type == 0) {//挂卖
                        RequestParams params = new RequestParams();
                        params.addFormDataPart("sellerID", UserSharedPreference.getInstance().getUser().getUserID());
                        params.addFormDataPart("ccfValue", num);
                        HttpRequest.post(Constant.OPERATE_CCF_HOST + API.HANGSELL, params, new CCFHttpRequestCallback() {
                            @Override
                            protected void onDataSuccess(JSONObject data) {
                                showMessage(0, "挂卖成功");
                                etNum.setText("");
                                etNum.clearFocus();
                                etNum.setFocusable(false);
                            }

                            @Override
                            protected void onDataError(int code, boolean flag, String msg) {
                                showMessage(code, msg);
                            }
                        });
                    } else {//挂买
                        RequestParams params = new RequestParams();
                        params.addFormDataPart("userID", UserSharedPreference.getInstance().getUser().getUserID());
                        params.addFormDataPart("ccfValue", num);
                        HttpRequest.post(Constant.OPERATE_CCF_HOST + API.HANGBUY, params, new CCFHttpRequestCallback() {
                            @Override
                            protected void onDataSuccess(JSONObject data) {
                                showMessage(0, "挂买成功");
                                etNum.setText("");
                                etNum.clearFocus();
                                etNum.setFocusable(false);
                            }

                            @Override
                            protected void onDataError(int code, boolean flag, String msg) {
                                showMessage(code, msg);
                            }
                        });
                    }
                } else {
                    toast("输入框不能为空");
                }
                break;
            case R.id.btn_enter_trading_platform:
                startActivity(new Intent(getActivity(), TradingCenterActivity.class));
                break;
            default:
                break;
        }
    }

}
