package com.chingtech.sample.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.fragment.BaseFragment;
import chingtech.library.utils.LogUtils;
import chingtech.library.widget.NoScrollListView;
import com.bumptech.glide.Glide;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.OptionsListAdapter;
import com.chingtech.sample.bean.JztkBean;
import com.chingtech.sample.bean.OptionBean;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample.fragment
 * Description:
 * Created by 师春雷
 * Created at 17/8/27 下午5:07
 */
public class QuestionFragment extends BaseFragment {

    private JztkBean bean;

    @BindView(R.id.tv_question_name)
    TextView tvQuestionName;
    @BindView(R.id.tv_question_type)
    TextView tvQuestionType;

    @BindView(R.id.listview)
    NoScrollListView listview;

    @BindView(R.id.tv_answer)
    TextView tvAnswer;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.tv_explains)
    TextView tvExplains;

    private OptionsListAdapter adapter;

    private List<OptionBean> optionBeans = new ArrayList<>();

    String str;

    private String[] zimu = {"A", "B", "C", "D"};

    public QuestionFragment(JztkBean bean, String str) {
        this.bean = bean;
        this.str = str;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_question;
    }

    @Override
    protected void init() {
        LogUtils.i("TAG", "QuestionFragment init: " + bean.toString());

        listview.setVisibility(View.VISIBLE);
        if (optionBeans.size() > 0) {
            optionBeans.clear();
        }
        optionBeans.add(new OptionBean("A", bean.getItem1()));
        optionBeans.add(new OptionBean("B", bean.getItem2()));
        if (!TextUtils.isEmpty(bean.getItem3())) {
            optionBeans.add(new OptionBean("C", bean.getItem3()));
        }
        if (!TextUtils.isEmpty(bean.getItem4())) {
            optionBeans.add(new OptionBean("D", bean.getItem4()));
        }
        listview.setDivider(null);
        adapter = new OptionsListAdapter(context, optionBeans, listview, R.layout.item_option);
        listview.setAdapter(adapter);

        tvQuestionName.setText(bean.getQuestion());
        tvExplains.setText(bean.getExplains());

        tvAnswer.setText("正确答案：" + zimu[Integer.valueOf(bean.getAnswer()) - 1]);

        if (TextUtils.isEmpty(bean.getUrl())) {
            image.setVisibility(View.GONE);
        } else {
            image.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(bean.getUrl()).into(image);
        }

        tvQuestionType.setText("(" + str + ")");
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            adapter.notifyDataSetChanged();
        });
    }
}
