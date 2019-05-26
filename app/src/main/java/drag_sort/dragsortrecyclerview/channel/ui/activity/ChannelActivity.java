package drag_sort.dragsortrecyclerview.channel.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import drag_sort.dragsortrecyclerview.R;
import drag_sort.dragsortrecyclerview.channel.ui.adapter.ChannelAdapter;
import drag_sort.dragsortrecyclerview.channel.entity.ChannelInfo;
import drag_sort.dragsortrecyclerview.channel.listener.MyOnToucheCallBack;


/**
 * Created by Ji on 2019/4/30.
 */


public class ChannelActivity extends AppCompatActivity implements ChannelAdapter.OnStartDragListener {

    private RecyclerView channelRecyler;
    private ChannelAdapter mAdapter;
    private ItemTouchHelper mTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_activity);
        channelRecyler = (RecyclerView) findViewById(R.id.channl_recyler);
        mAdapter = new ChannelAdapter(this,getDatas(),channelRecyler);
        GridLayoutManager manager=new GridLayoutManager(this,4);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                if(itemViewType==mAdapter.CHANNEL_TITLE){
                    return 4;
                }
                return 1;
            }
        });
        channelRecyler.setLayoutManager(manager);
        channelRecyler.setAdapter(mAdapter);
        //create ItemTouchHelper
        mTouchHelper = new ItemTouchHelper(new MyOnToucheCallBack(mAdapter));
        //attach到RecyclerView中
        mTouchHelper.attachToRecyclerView(channelRecyler);
        mAdapter.setOnStartDragListener(this);
    }

    private List<ChannelInfo> getDatas(){
        List<ChannelInfo> beanList=new ArrayList<>();
        beanList.add(new ChannelInfo("myChannel",1));

        for(int i=0;i<10;i++){
            beanList.add(new ChannelInfo("channel"+i,2));
        }

        beanList.add(new ChannelInfo("optionChannel",1));

        for(int i=0;i<10;i++){
            beanList.add(new ChannelInfo("other"+i,3));
        }

        return beanList;

    }

    @Override
    public void startDrag(RecyclerView.ViewHolder holder) {
        mTouchHelper.startDrag(holder);
    }
}
