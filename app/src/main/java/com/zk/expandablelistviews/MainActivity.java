package com.zk.expandablelistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnGroupExpanded {

    private ExpandableListView mElistview;
    public String[] titleStrings = {"女朋友", "女神", "基友", "小弟"};

    public String[][] nameStrings = {
            {"苍井空", "波多野结衣", "小泽玛莉亚", "龙泽罗拉"},
            {"鹿晗", "李易峰", "吴亦凡", "王俊凯"},
            {"张先生", "刘先生", "李先生", "杜先生", "小弟弟"},
            {"奥巴驴", "小学僧", "儿童劫", "托儿索"}
    };

    private MyAdapter mAdapter;
    private List<TitleInfo> mList = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mElistview = findViewById(R.id.mElistview);
        initList();
        initAdapter();
        initListenter();

    }

    /**
     * ExpandableListView条目点击事件
     */
    private void initListenter() {
        //子对象点击监听事件
        mElistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,nameStrings[groupPosition][childPosition]+"",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //组对象点击监听事件
        mElistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;//请务必返回false，否则分组不会展开
            }
        });
        //组对象判断分组监听事件
        mAdapter.setOnGroupExPanded(this);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new MyAdapter(mList, this);
        mElistview.setAdapter(mAdapter);
    }

    /**
     * 初始化数据源
     */
    private void initList() {

        for (int i = 0; i < titleStrings.length; i++) {
            //创建组对象
            TitleInfo info= new TitleInfo();
            //循环添加组的标题名
            info.setTitle(titleStrings[i]);
            //创建子对象数据源
            List<ContentInfo> list = new ArrayList<>();
            for (int j = 0; j < nameStrings.length; j++) {
                //创建子对象
                ContentInfo info2 = new ContentInfo();
                //添加用户名或者头像
                info2.setName(nameStrings[i][j]);
                //将子对象添加到数据源
                list.add(info2);
            }
            //将子对象数据源复制给组对象
            info.setInfo(list);
            //将组对象添加到总数据源中
            mList.add(info);

        }

    }


    /**
     * 监听是否关闭其他的组对象
     * @param groupPostion
     */
    @Override
    public void onGroupExpanded(int groupPostion) {
        EListViewUtils utils=new EListViewUtils();
        utils.expandOnlyOne(groupPostion,mElistview);
    }
}
