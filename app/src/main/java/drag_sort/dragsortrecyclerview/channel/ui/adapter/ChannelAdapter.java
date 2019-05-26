package drag_sort.dragsortrecyclerview.channel.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import drag_sort.dragsortrecyclerview.R;
import drag_sort.dragsortrecyclerview.channel.entity.ChannelInfo;
import drag_sort.dragsortrecyclerview.channel.util.IItemHelper;
import drag_sort.dragsortrecyclerview.channel.view.MyViewHolder;
import drag_sort.dragsortrecyclerview.channel.util.ToastUtil;


/**
 * Created by Ji on 2019/4/30.
 */


public class ChannelAdapter extends RecyclerView.Adapter<MyViewHolder> implements IItemHelper {
    //title
    public static final int CHANNEL_TITLE=1;
    //my channel
    public static final int MY_CHANNEL=2;
    //other
    public static final int OTHER_CHANNEL=3;
    private final Context mContext;
    private List<ChannelInfo> mDatas=new ArrayList<>();

    List<ChannelInfo> mMyChannel=new ArrayList<>();

    List<ChannelInfo> moptionChannel=new ArrayList<>();

    private final LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    //is edit model
    private boolean isEdit;

    public ChannelAdapter(Context context, List<ChannelInfo> datas, RecyclerView recyclerView){
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
        this.mRecyclerView = recyclerView;
        for(int i=0;i<mDatas.size();i++){
            if(mDatas.get(i).getTypeView()==2){
                mMyChannel.add(mDatas.get(i));
            }else if(mDatas.get(i).getTypeView()==3){
                moptionChannel.add(mDatas.get(i));
            }
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view=null;
        if(viewType==CHANNEL_TITLE){
            view = mInflater.inflate(R.layout.channel_title, parent, false);
        }else {
            view = mInflater.inflate(R.layout.channel_item, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if(isEdit&&position<=mMyChannel.size()){
            ImageView imageEdit=holder.getView(R.id.img_edit);
            if(imageEdit!=null){
                imageEdit.setVisibility(View.VISIBLE);
            }
        }else {
            ImageView imageEdit=holder.getView(R.id.img_edit);
            if(imageEdit!=null){
                imageEdit.setVisibility(View.GONE);
            }
        }
        TextView channel = holder.getView(R.id.src);
        if(channel!=null){
            channel.setText(mDatas.get(position).getSrc());
        }
        TextView title = holder.getView(R.id.title);
        if(title!=null){
            title.setText(mDatas.get(position).getSrc());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDatas.get(holder.getAdapterPosition()).getTypeView()==MY_CHANNEL){
                    //my channel
                    if(isEdit){
                        move(holder.getAdapterPosition());
                    }else{
                        ToastUtil.showShort(mContext,mDatas.get(holder.getAdapterPosition()).getSrc());
                    }

                }else if(mDatas.get(holder.getAdapterPosition()).getTypeView()==OTHER_CHANNEL){
                    //other channel
                    if(isEdit){
                        move(holder.getAdapterPosition());
                    }else{
                        ToastUtil.showShort(mContext,mDatas.get(holder.getAdapterPosition()).getSrc());
                    }
                }
            }
        });


        if(mDatas.get(holder.getAdapterPosition()).getTypeView()==MY_CHANNEL){
            //long click
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int childCount = mRecyclerView.getChildCount();
                    childCount=childCount-1-moptionChannel.size();
                    if(!isEdit){

                        for (int i = 0; i < childCount; i++) {
                            View view = mRecyclerView.getChildAt(i);
                            ImageView imgEdit = (ImageView) view.findViewById(R.id.img_edit);
                            if (imgEdit != null) {
                                imgEdit.setVisibility(View.VISIBLE);
                            }
                        }
                        isEdit=true;

                        // enable drag listener
                        if(onStartDragListener!=null){
                            onStartDragListener.startDrag(holder);
                        }

                    }else{
                        for (int i = 0; i < childCount; i++) {
                            View view = mRecyclerView.getChildAt(i);
                            ImageView imgEdit = (ImageView) view.findViewById(R.id.img_edit);
                            if (imgEdit != null) {
                                imgEdit.setVisibility(View.GONE);
                            }
                        }
                        isEdit=false;
                    }
                    return true;
                }
            });
            //enable sort
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(isEdit){
                        switch (event.getAction()){

                            case MotionEvent.ACTION_MOVE:
                                if(onStartDragListener!=null){
                                    onStartDragListener.startDrag(holder);
                                }
                                break;
                        }
                    }
                    return false;
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas.get(position).getTypeView()==1){
            return CHANNEL_TITLE;
        }else if(mDatas.get(position).getTypeView()==2){
            return MY_CHANNEL;
        }else{
            return OTHER_CHANNEL;
        }
    }

    /**
     * move the item
     * @param position
     */
    private void move(int position) {

        if(position>mMyChannel.size()+1){
            int i = position - 2 - mMyChannel.size();
            //other
            ChannelInfo item = moptionChannel.get(position-2-mMyChannel.size());
            moptionChannel.remove(item);
            item.setTypeView(2);
            mMyChannel.add(item);

            notifyData();
            notifyItemMoved(position, mMyChannel.size());

        }else if(position>0&&position<=mMyChannel.size()){

            ChannelInfo item = mMyChannel.get(position-1);
            mMyChannel.remove(item);
            item.setTypeView(3);
            moptionChannel.add(0, item);

            notifyData();
            notifyItemMoved(position, mMyChannel.size() + 2);
        }
    }


    @Override
    public void itemMoved(int oldPosition, int newPosition) {
        ChannelInfo channelBean = mMyChannel.get(oldPosition - 1);
        mMyChannel.remove(oldPosition - 1);
        mMyChannel.add(newPosition-1,channelBean);
        notifyData();
        notifyItemMoved(oldPosition, newPosition);
    }

    private void notifyData(){

        mDatas.clear();
        mDatas.add(new ChannelInfo("myChannel",1));
        mDatas.addAll(mMyChannel);
        mDatas.add(new ChannelInfo("optionChannel",1));
        mDatas.addAll(moptionChannel);
    }

    @Override
    public void itemDismiss(int position) {
    }

    private OnStartDragListener onStartDragListener;

    public interface OnStartDragListener{
        void startDrag(RecyclerView.ViewHolder holder);
    }

    public void setOnStartDragListener(OnStartDragListener onStartDragListener){

        this.onStartDragListener = onStartDragListener;
    }
}
