package cn.cnlinfo.ccf.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
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
import cn.cnlinfo.ccf.view.StopScrollViewPager;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

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
    private final String[] TITLES = {"主页信息", "循环包", "分享信息"};
    private PopupMenu popupMenu;

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
        Menu menu =  popupMenu.getMenu();
        menuInflater.inflate(R.menu.layout_menu,menu);
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
            mHelper.setForceShowIcon(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();   }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
               switch (itemId){
                   case R.id.sweep:
                       toast(item.getTitle().toString());
                       break;
                   case R.id.addFriend:
                       toast(item.getTitle().toString());
                       break;
                   case R.id.shareUser:
                       toast(item.getTitle().toString());
                       break;
                   case R.id.setting:
                       toast(item.getTitle().toString());
                       break;
               }
               return false;
           }
       });
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
                    fragment = new ShareQRCodeFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }

    }
}