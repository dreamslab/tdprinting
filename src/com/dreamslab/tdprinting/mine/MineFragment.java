package com.dreamslab.tdprinting.mine;

import java.util.ArrayList;
import java.util.HashMap;

import com.dreamslab.tdprinting.R;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class MineFragment extends ListFragment {

    private static final String ITEM_ICON = "item_icon";
    private static final String ITEM_LABEL = "item_label";

    private final String[] mFrom = new String[] {
            ITEM_ICON, ITEM_LABEL
    };

    private final int[] mTo = new int[] {
            R.id.icon, R.id.label
    };

    private ArrayList<HashMap<String, Object>> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupList();
    }

    private void setupList() {
        mData = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put(ITEM_ICON, R.drawable.ic_list_item_orders);
        item.put(ITEM_LABEL, getString(R.string.my_orders));
        mData.add(item);

        item = new HashMap<String, Object>();
        item.put(ITEM_ICON, R.drawable.ic_list_item_favorites);
        item.put(ITEM_LABEL, getString(R.string.my_favorites));
        mData.add(item);

        item = new HashMap<String, Object>();
        item.put(ITEM_ICON, R.drawable.ic_list_item_messages);
        item.put(ITEM_LABEL, getString(R.string.my_messages));
        mData.add(item);

        item = new HashMap<String, Object>();
        item.put(ITEM_ICON, R.drawable.ic_list_item_account_settings);
        item.put(ITEM_LABEL, getString(R.string.my_account_settings));
        mData.add(item);

        item = new HashMap<String, Object>();
        item.put(ITEM_ICON, R.drawable.ic_list_item_feed_back);
        item.put(ITEM_LABEL, getString(R.string.my_feed_back));
        mData.add(item);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), mData,
                R.layout.mine_list_item, mFrom, mTo);
        getListView().setAdapter(adapter);
    }
}
