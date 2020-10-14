package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    private Fragment mTab01 = new FindFragment();
    private Fragment mTab02 = new StoreFragment();
    private Fragment mTab03 = new DiscussionFragment();
    private Fragment mTab04 = new MineFragment();

    private FragmentManager fm;

    private LinearLayout mTabFind;
    private LinearLayout mTabStore;
    private LinearLayout mTabDiscussion;
    private LinearLayout mTabMine;

    private ImageButton mImgFind;
    private ImageButton mImgStore;
    private ImageButton mImgDiscussion;
    private ImageButton mImgMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        limitListener();
        initFragment();
        setfragment(0);
    }

    private void initFragment(){
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();
    }

    //将监听范围限制在bottom中的四个LinearLayout中，避免由于全屏监听所造成的大量内存消耗
    private void limitListener(){
        mTabFind.setOnClickListener(this);
        mTabStore.setOnClickListener(this);
        mTabDiscussion.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
    }

    private void initView(){
        mTabFind = (LinearLayout)findViewById(R.id.find);
        mTabStore = (LinearLayout)findViewById(R.id.store);
        mTabDiscussion = (LinearLayout)findViewById(R.id.discussion);
        mTabMine = (LinearLayout)findViewById(R.id.mine);

        mImgFind = (ImageButton)findViewById(R.id.findImage);
        mImgStore = (ImageButton)findViewById(R.id.storeImage);
        mImgDiscussion = (ImageButton)findViewById(R.id.discussionImage);
        mImgMine = (ImageButton)findViewById(R.id.mineImage);
    }

    //Hidefragment函数，先将所有页面先hide起来，select中后再展示
    private void Hidefragment(FragmentTransaction transaction){
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    //select中第i个页面以展示
    private void setfragment(int i){
        FragmentTransaction transaction = fm.beginTransaction();
        Hidefragment(transaction);
        switch (i){
            case 0:
                transaction.show(mTab01);
                mImgFind.setImageResource(R.drawable.find_chosen);
                break;
            case 1:
                transaction.show(mTab02);
                mImgStore.setImageResource(R.drawable.store_chosen);
                break;
            case 2:
                transaction.show(mTab03);
                mImgDiscussion.setImageResource(R.drawable.discussion_chosen);
                break;
            case 3:
                transaction.show(mTab04);
                mImgMine.setImageResource(R.drawable.mine_chosen);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    //重写点击响应函数,点击到相应的LinearLayout，传对应值到setfragment函数，进行页面内容和bottom图标切换
    @Override
    public void onClick(View view) {
        //做图标切换前先初始化一次所有图标
        resetimg();
        switch (view.getId()){
            case R.id.find:
                setfragment(0);
                break;
            case R.id.store:
                setfragment(1);
                break;
            case R.id.discussion:
                setfragment(2);
                break;
            case R.id.mine:
                setfragment(3);
                break;
            default:
                break;
        }
    }

    //还原图标
    public void resetimg(){
        mImgFind.setImageResource(R.drawable.find);
        mImgStore.setImageResource(R.drawable.store);
        mImgDiscussion.setImageResource(R.drawable.discussion);
        mImgMine.setImageResource(R.drawable.mine);
    }
}
