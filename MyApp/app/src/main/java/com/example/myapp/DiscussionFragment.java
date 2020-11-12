package com.example.myapp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;





/**
 * A simple {@link Fragment} subclass.
 */

//将R.layout.tab03压缩到DiscussionFragment类中，将一个fragment对象化，就可通过调用对象的形式来调用fragment
public class DiscussionFragment extends Fragment implements View.OnClickListener {

    private View view;

    // 获取界面中显示歌曲标题、作者文本框
    TextView title, author;
    // 播放/暂停、停止按钮
    ImageButton play, stop;
    //上一首、下一首按钮
    Button button1;
    Button button2;

    ActivityReceiver activityReceiver;

    public static final String CTL_ACTION =
            "org.xr.action.CTL_ACTION";
    public static final String UPDATE_ACTION =
            "org.xr.action.UPDATE_ACTION";

    // 定义音乐的播放状态，0x11代表没有播放；0x12代表正在播放；0x13代表暂停
    int status = 0x11;
    String[] titleStrs = new String[] { "葫芦娃","吴哥窟","画心","忘川彼岸" };
    String[] authorStrs = new String[] { "吴应炬", "阿梨粤","张靓颖","零一九零贰"};



    public DiscussionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab03, container, false);

        // 获取程序界面界面中的两个按钮
        play = (ImageButton) view.findViewById(R.id.play);
        stop = (ImageButton) view.findViewById(R.id.stop);
        title = (TextView) view.findViewById(R.id.title);
        author = (TextView) view.findViewById(R.id.author);

        //获取上一首、下一首按钮
        button1 = (Button) view.findViewById(R.id.lastone);
        button2 = (Button) view.findViewById(R.id.nextone);

        // 为两个按钮的单击事件添加监听器
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        //为上一首、下一首按钮添加监听器
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


        activityReceiver = new ActivityReceiver();
        // 创建IntentFilter
        IntentFilter filter = new IntentFilter();
        // 指定BroadcastReceiver监听的Action
        filter.addAction(UPDATE_ACTION);
        // 注册BroadcastReceiver
        getActivity().registerReceiver(activityReceiver, filter);

        Intent intent = new Intent(getActivity(), MusicService.class);
        // 启动后台Service
        getActivity().startService(intent);

        // Inflate the layout for this fragment
        return view;
    }


    // 自定义的BroadcastReceiver，负责监听从Service传回来的广播
    public class ActivityReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            // 获取Intent中的update消息，update代表播放状态
            int update = intent.getIntExtra("update", -1);
            // 获取Intent中的current消息，current代表当前正在播放的歌曲
            int current = intent.getIntExtra("current", -1);
            if (current >= 0)
            {
                title.setText(titleStrs[current]);
                author.setText(authorStrs[current]);
            }


            switch (update)
            {
                case 0x11:
                    play.setImageResource(R.drawable.play);
                    status = 0x11;
                    break;
                // 控制系统进入播放状态
                case 0x12:
                    // 播放状态下设置使用暂停图标
                    play.setImageResource(R.drawable.pause);
                    // 设置当前状态
                    status = 0x12;
                    break;
                // 控制系统进入暂停状态
                case 0x13:
                    // 暂停状态下设置使用播放图标
                    play.setImageResource(R.drawable.play);
                    // 设置当前状态
                    status = 0x13;
                    break;
            }
        }
    }

    @Override
    public void onClick(View source)
    {
        // 创建Intent
        Intent intent = new Intent("org.xr.action.CTL_ACTION");
        switch (source.getId())
        {
            // 按下播放/暂停按钮
            case R.id.play:
                intent.putExtra("control", 1);
                break;
            // 按下停止按钮
            case R.id.stop:
                intent.putExtra("control", 2);
                break;
            // 按下上一曲按钮
            case R.id.lastone:
                intent.putExtra("control", 3);
                break;
            // 按下下一曲按钮
            case R.id.nextone:
                intent.putExtra("control", 4);
                break;

        }
        // 发送广播，将被Service组件中的BroadcastReceiver接收到
        getActivity().sendBroadcast(intent);
    }
}
