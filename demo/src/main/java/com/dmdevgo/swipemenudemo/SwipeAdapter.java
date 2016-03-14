package com.dmdevgo.swipemenudemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmdevgo.swipemenu.BaseSwipeMenuHolder;

/**
 * @author Dmitriy Gorbunov
 */
public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_swipe, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends BaseSwipeMenuHolder {

        final View menuView;
        final View contentView;

        public ViewHolder(View itemView) {
            super(itemView);
            menuView = itemView.findViewById(R.id.menu);
            contentView = itemView.findViewById(R.id.content);
        }

        @Override
        public int getMenuDirection() {
            return MENU_LEFT_AND_RIGHT;
        }

        @Override
        public View getMenuView() {
            return menuView;
        }

        @Override
        public View getContentView() {
            return contentView;
        }
    }

}
