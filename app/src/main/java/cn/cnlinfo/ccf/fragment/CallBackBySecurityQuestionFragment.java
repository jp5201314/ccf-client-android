package cn.cnlinfo.ccf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cnlinfo.ccf.R;
import cn.cnlinfo.ccf.view.CleanEditText;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class CallBackBySecurityQuestionFragment extends BaseFragment{
    @BindView(R.id.sp_one)
    Spinner spOne;
    @BindView(R.id.ct_answer_one)
    CleanEditText ctAnswerOne;
    @BindView(R.id.sp_two)
    Spinner spTwo;
    @BindView(R.id.ct_answer_two)
    CleanEditText ctAnswerTwo;
    @BindView(R.id.sp_three)
    Spinner spThree;
    @BindView(R.id.ct_answer_three)
    CleanEditText ctAnswerThree;
    private Unbinder unbinder;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private String answerOne;
    private String answerTwo;
    private String answerThree;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call_back_by_security_card, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        setDataSource();
        //适配器
        arr_adapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spOne.setAdapter(arr_adapter);
        spTwo.setAdapter(arr_adapter);
        spThree.setAdapter(arr_adapter);
    }

    private void initEditData() {
        answerOne = ctAnswerOne.getText().toString();
        answerTwo = ctAnswerTwo.getText().toString();
        answerThree = ctAnswerThree.getText().toString();
    }

    private void setDataSource(){
        //数据
        data_list = new ArrayList<>();
        data_list.add("你的妈妈的名字?");
        data_list.add("你上的小学名称?");
        data_list.add("你的最好的朋友?");
        data_list.add("对你影响最深的人?");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
