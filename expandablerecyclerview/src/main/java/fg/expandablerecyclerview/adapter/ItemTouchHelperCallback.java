package fg.expandablerecyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import fg.expandablerecyclerview.view.BaseViewHolder;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final String TAG = ItemTouchHelperCallback.class.getSimpleName();

    private int toPosition;
    private int mFromPosition = -1;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.e(TAG, "getMovementFlags()");
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        if (viewHolder instanceof BaseViewHolder && ((BaseViewHolder) target).getItem().isFix()) {
            return false;
        }
        int fromPosition = viewHolder.getAdapterPosition();
        if (mFromPosition < 0)
            mFromPosition = fromPosition;
        toPosition = target.getAdapterPosition();
        recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
        Log.e(TAG, "onMove()4");
        return true;
    }

    //重写拖拽不可用
    @Override
    public boolean isLongPressDragEnabled() {
        Log.e(TAG, "isLongPressDragEnabled()");
//        return false;
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e(TAG, "onSwiped:" + direction);

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.e(TAG, "onSelectedChanged()");
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            mFromPosition = -1;
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.e(TAG, "clearView()");
        viewHolder.itemView.setBackgroundColor(0);
        if (mFromPosition <0 || toPosition == mFromPosition)
            return;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof AbstractAdapter) {
            AbstractAdapter adapter1 = ((AbstractAdapter) adapter);
            adapter1.move(mFromPosition, toPosition);
        }
    }
}
