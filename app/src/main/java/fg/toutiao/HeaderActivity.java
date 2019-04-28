package fg.toutiao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fg.base.HeaderRecyclerAdapter;
import fg.base.RecyclerBaseHolder;
import fg.mylibrary.R;
import fg.toutiao.model.Mode;

public class HeaderActivity extends AppCompatActivity {

    Mode mode2;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<Mode> modes = new ArrayList<>();
        for (int i = 0; i < 105; i++) {
            Mode d = new Mode();
            d.name = "客厅" + i;
            modes.add(d);
        }
        MAdapter mAdapter = new MAdapter(modes);
        View view = View.inflate(this, R.layout.item_chapter2, null);
        mAdapter.setHeaderView(view);
        mRecyclerView.setAdapter(mAdapter);
    }

    class MAdapter extends HeaderRecyclerAdapter<Mode> {
        public MAdapter(List datas) {
            super(datas);
        }

        @Override
        public int itemLayout() {
            return R.layout.item_section;
        }

        @Override
        public RecyclerBaseHolder getViewHolder(View itemView) {
            return new MHolder(itemView);
        }
    }

    class MHolder extends RecyclerBaseHolder<Mode> {

        TextView tv;

        public MHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item_section_name);
        }

        @Override
        public void refreshView(Mode data) {
            tv.setText(data.name);
        }
    }


}
