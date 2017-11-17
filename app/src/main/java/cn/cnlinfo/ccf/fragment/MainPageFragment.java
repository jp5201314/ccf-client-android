package cn.cnlinfo.ccf.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.tendcloud.tenddata.TCAgent;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.UserSharedPreference;
import cn.cnlinfo.ccf.activity.BuildQRCodeActivity;
import cn.cnlinfo.ccf.manager.AppManage;
import cn.cnlinfo.ccf.utils.QRCodeUtil;
import cn.cnlinfo.ccf.view.StopScrollViewPager;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
@RuntimePermissions
public class MainPageFragment extends BaseFragment {

    @BindView(R.id.indicator)
    FixedIndicatorView indicator;
    @BindView(R.id.vp)
    StopScrollViewPager vp;
    @BindView(R.id.ibt_back)
    ImageButton ibtBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibt_add)
    ImageButton ibtAdd;
    private IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorFragmentPagerAdapter adapter;
    private Unbinder unbinder;
    private final String[] TITLES = {"主页信息", "循环包", "个人信息"};
    private PopupMenu popupMenu;
    private static final int REQUEST_CODE_SCAN = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        TCAgent.onPageStart(getActivity(), "主页");
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        vp.setStopScroll(true);
        ibtBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("主页");
        ibtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopupMenu(v);
                popupMenu.show();
            }
        });
        setIndicator();
    }


    @SuppressLint("RestrictedApi")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setPopupMenu(View view) {
        popupMenu = new PopupMenu(getActivity(), view, Gravity.END);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        Menu menu = popupMenu.getMenu();
        menuInflater.inflate(R.menu.layout_menu, menu);
        try {
            /**
             * 反射设置图片
             */
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
            mHelper.setForceShowIcon(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.sweep:
                        MainPageFragmentPermissionsDispatcher.toRichScanWithCheck(MainPageFragment.this);
                        break;
                    case R.id.my_qrcode:
                       startActivity(new Intent(getActivity(), BuildQRCodeActivity.class));
                        break;
                    case R.id.exit:
                        exit();
                        break;
                    case R.id.setting:
                        toast(item.getTitle().toString());
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 退出app清除账号
     */
    private void exit(){
        UserSharedPreference.getInstance().logout();
        AppManage.getInstance().exit(getActivity());
    }


    private void setIndicator() {
        float unSelectSize = 15;
        float selectSize = 15;
        OnTransitionTextListener listener = new OnTransitionTextListener() {
            @Override
            public TextView getTextView(View tabItemView, int position) {
                return (TextView) tabItemView.findViewById(R.id.tv_tab_text);
            }
        };
        indicator.setOnTransitionListener(listener.setColor(getResources().getColor(R.color.color_blue_4d8cd6),
                getResources().getColor(R.color.color_black_0e1214)).setSize(selectSize, unSelectSize));
        //将指示器和ViewPager绑定在一起
        indicatorViewPager = new IndicatorViewPager(indicator, vp);
        adapter = new ItemViewPagerIndicator(getChildFragmentManager());
        indicatorViewPager.setAdapter(adapter);
        indicatorViewPager.setPageOffscreenLimit(3);
        indicator.setScrollBar(new LayoutBar(getActivity(), R.layout.indicator_scroll_bar, ScrollBar.Gravity.BOTTOM));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        TCAgent.onPageEnd(getActivity(), "主页");
    }

    class ItemViewPagerIndicator extends IndicatorViewPager.IndicatorFragmentPagerAdapter {


        public ItemViewPagerIndicator(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_tab, container, false);
                TextView tv_tab = (TextView) convertView.findViewById(R.id.tv_tab_text);
                tv_tab.setTextSize(12);
                tv_tab.setText(TITLES[position]);
            }
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new MainPageInfoFragment();
                    break;
                case 1:
                    fragment = new CyclePackageFragment();
                    break;
                case 2:
                    fragment = new MyInfoFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }
    }

    /**
     * 申请需要的权限
     */
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void toRichScan() {
        QRCodeUtil.toRichScan(getActivity(), REQUEST_CODE_SCAN);
    }

    /**
     * 拒绝
     */
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void refuse() {
        toast("权限被拒绝");
    }

    /**
     * 不再询问
     */
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void neverEnquire() {
        toast("拍照权限被禁用，如果要使用拍照请手动启用");
    }

    /**
     * 当第一次申请权限时，用户选择拒绝，再次申请时调用此方法，在此方法中提示用户为什么需要这个权限，这需要展现给用户，而用户可以选择“继续”或者“中止”当前的权限许可
     *
     * @param request
     */
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE})
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getActivity())
                .setMessage("申请相机权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次执行请求
                        request.proceed();// 提示用户权限使用的对话框
                    }
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainPageFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}