package com.croquis.list;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListAdapter;

public class CroquisListActivity extends Activity {
    protected ListAdapter mAdapter;
    protected CroquisListView mList;

    private Handler mHandler = new Handler();
    private boolean mFinishedStart = false;

    private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mList.focusableViewAvailable(mList);
        }
    };

    protected void onListItemClick(CroquisListView l, View v, int position, long id) {
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        ensureList();
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRequestFocus);
        super.onDestroy();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        View emptyView = findViewById(android.R.id.empty);
        mList = (CroquisListView)findViewById(android.R.id.list);
        if (mList == null) {
            throw new RuntimeException(
                    "Your content must have a ListView whose id attribute is " +
                    "'android.R.id.list'");
        }
        if (emptyView != null) {
            mList.setEmptyView(emptyView);
        }
        mList.setOnItemClickListener(mOnClickListener);
        if (mFinishedStart) {
            setListAdapter(mAdapter);
        }
        mHandler.post(mRequestFocus);
        mFinishedStart = true;
    }

    public void setListAdapter(ListAdapter adapter) {
        synchronized (this) {
            ensureList();
            mAdapter = adapter;
            mList.setAdapter(adapter);
        }
    }

    public void setSelection(int position) {
        mList.setSelection(position);
    }

    public int getSelectedItemPosition() {
        return mList.getSelectedItemPosition();
    }

    public long getSelectedItemId() {
        return mList.getSelectedItemId();
    }

    public CroquisListView getListView() {
        ensureList();
        return mList;
    }

    public ListAdapter getListAdapter() {
        return mAdapter;
    }

    private void ensureList() {
        if (mList != null) {
            return;
        }
        //setContentView(android.R.layout.list_content);
    }

    private CroquisAdapterView.OnItemClickListener mOnClickListener = new CroquisAdapterView.OnItemClickListener() {
        public void onItemClick(CroquisAdapterView<?> parent, View v, int position, long id)
        {
            onListItemClick((CroquisListView)parent, v, position, id);
        }
    };
}
