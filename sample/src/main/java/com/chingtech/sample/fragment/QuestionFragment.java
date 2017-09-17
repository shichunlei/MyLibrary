package com.chingtech.sample.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import chingtech.library.base.fragment.BaseFragment;
import chingtech.library.widget.NoScrollListView;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.OptionsListAdapter;
import com.chingtech.sample.bean.QuestionBean;
import java.util.ArrayList;
import java.util.List;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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
@ContentView(R.layout.fragment_question)
public class QuestionFragment extends BaseFragment {

    private StringBuffer sb;
    private QuestionBean questionBean;

    @ViewInject(R.id.tv_question_category)
    private TextView tvQuestionCategory;

    @ViewInject(R.id.tv_question_name)
    private TextView tvQuestionName;
    @ViewInject(R.id.tv_question_type)
    private TextView tvQuestionType;

    @ViewInject(R.id.et_fill_blanks)
    private EditText editText;

    @ViewInject(R.id.listview)
    private NoScrollListView listview;

    @ViewInject(R.id.btn_next)
    private Button btnSend;

    private List<String> options = new ArrayList<>();

    private OptionsListAdapter adapter;

    String str;

    public QuestionFragment(QuestionBean bean, String str) {
        this.questionBean = bean;
        this.str = str;
    }

    @Override
    protected void init() {
        Log.i("TAG", "QuestionFragment init: " + questionBean.toString());

        if (questionBean.getQuestion_type().equals("radio") || questionBean.getQuestion_type()
                                                                           .equals("multi_select")) {
            listview.setVisibility(View.VISIBLE);
            editText.setVisibility(View.GONE);
            Log.i("TAG", "getOptions: " + questionBean.getOptions());
            String strOptions[] = questionBean.getOptions().split(";");
            for (int i = 0; i < strOptions.length; i++) {
                options.add(strOptions[i]);
            }
            listview.setDivider(null);
            adapter = new OptionsListAdapter(context, options, listview, R.layout.item_option);
            listview.setAdapter(adapter);
        } else {
            listview.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
        }

        tvQuestionName.setText(questionBean.getName());
        tvQuestionCategory.setText(questionBean.getSub_category());
        sb = new StringBuffer();
        if (questionBean.getQuestion_type().equals("radio")) {
            tvQuestionType.setText("(单选题)");
            listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listview.setOnItemClickListener((parent, view, position, id) -> {
                adapter.notifyDataSetChanged();
            });
            btnSend.setOnClickListener(v -> {
                int position = listview.getCheckedItemPosition();
                showToast("选中的选项为" + options.get(position));
            });
        }
        if (questionBean.getQuestion_type().equals("multi_select")) {
            tvQuestionType.setText("(多选题)");
            listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listview.setOnItemClickListener((parent, view, position, id) -> {
                adapter.notifyDataSetChanged();
            });
            btnSend.setOnClickListener(v -> {
                long[] ids = listview.getCheckedItemIds();
                for (int i = 0; i < ids.length; i++) {
                    long id = ids[i];
                    sb.append(options.get((int) id)).append(" ");
                }
                showToast("选中的选项为" + sb.toString());
                sb.setLength(0);
            });
        }
        if (questionBean.getQuestion_type().equals("fill_blanks")) {
            tvQuestionType.setText("(填空题)");
        }
    }
}
