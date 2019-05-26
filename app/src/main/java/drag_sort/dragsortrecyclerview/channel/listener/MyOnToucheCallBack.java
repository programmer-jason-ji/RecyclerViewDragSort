package drag_sort.dragsortrecyclerview.channel.listener;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import drag_sort.dragsortrecyclerview.channel.util.IItemHelper;

/**
 * Created by Ji on 2017/8/19.
 */

public class MyOnToucheCallBack extends ItemTouchHelper.Callback {

    private IItemHelper itemHelper;

    public MyOnToucheCallBack(IItemHelper itemHelper){

        this.itemHelper = itemHelper;
    }

    //different move approach
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        // slide to delete, swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END
        int swipeFlags =  ItemTouchHelper.START;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

   // move to a new position
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // can't move between different Types
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        itemHelper.itemMoved(viewHolder.getLayoutPosition(),target.getLayoutPosition());
        return false;
    }

    // slide to dismiss
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemHelper.itemDismiss(viewHolder.getLayoutPosition());
    }

    /**
     * true --enable Long Press Drag
     * false --disable Long Press Drag
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * close slide
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {

        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);

//        if(actionState!= ItemTouchHelper.ACTION_STATE_IDLE){
//            viewHolder.itemView.setBackgroundColor(Color.GRAY);
//        }
    }


    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
//        viewHolder.itemView.setBackgroundColor(0);
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //moving
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
          /*  final float alpha = 80 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);*/

        }else if(actionState==ItemTouchHelper.ACTION_STATE_IDLE){
             // moving
         }else if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){
         }
    }
}
