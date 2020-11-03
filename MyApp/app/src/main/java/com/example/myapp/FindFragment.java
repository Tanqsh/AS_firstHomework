package com.example.myapp;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

//将R.layout.tab01压缩到FindFragment类中，将一个fragment对象化，就可通过调用对象的形式来调用fragment
public class FindFragment extends Fragment {

    //数据成员声明：第二次作业
    private View view;
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();//存放模拟数据
    private RecycleAdapterDome adapterDome;//数据适配器


    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //第二次作业

        //获取fragment的layout
        view = inflater.inflate(R.layout.tab01, container, false);

        recyclerView = view.findViewById(R.id.findRecyclerView);

        initData();//初始化数据集合list
        adapterDome = new RecycleAdapterDome(getActivity(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());



        //纵向布局
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterDome);
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //在RecyclerView中为适配器中写监听事件的接口
        adapterDome.setOnItemClickListener(new RecycleAdapterDome.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, List<String> list) {
                //此处进行监听事件的业务处理
                Toast.makeText(getActivity(),"正在加载应用详情~",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //初始化数据
    private void initData() {

        list.add("王者荣耀");
        list.add("和平精英");
        list.add("率土之滨");
        list.add("阴阳师");
        list.add("LOL:Wild Rift");
        list.add("炉石传说");
        list.add("元气骑士");
        list.add("天天酷跑");
        list.add("时空猎人");
        list.add("刀塔传奇");
        list.add("神庙逃亡");
        list.add("部落冲突");
        list.add("球球大作战");
        list.add("三国杀");
        list.add("明日之后");
        list.add("开心消消乐");
        list.add("我叫MT");
    }



}
