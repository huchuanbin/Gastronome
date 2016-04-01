package com.hcb.gastronome.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huchuanbin on 16/4/1.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleHolder> {

        public List<T> list;
        public Context context;
        private AdapterView.OnItemClickListener onItemClickListener;
        private AdapterView.OnItemLongClickListener onItemLongClickListener;
        private int headerCount;

        public BaseRecycleAdapter(Context context) {
            this.context = context;
            list = new ArrayList<>();
            headerCount = 0;
        }

        public void setHeaderCount(int headerCount) {
            this.headerCount = headerCount;
        }

        /**
         * 设置数据源
         *
         * @param list
         */
        public void setItems(List<T> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        public void clearItems() {
            this.list.clear();
            notifyDataSetChanged();
        }

        public void resetItems(List<T> list) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }

        /**
         * 继续添加数据  load more
         *
         * @param list
         */
        public void addItems(List<T> list) {
            addItems(list, this.list.size());
        }

        public void addItems(List<T> list, int position) {
            if (list == null)
                return;
            this.list.addAll(list);
            notifyItemRangeInserted(position, list.size());
        }

        public void addItem(T t) {
            addItem(t, this.list.size());
        }

        public void addItem(T t, int position) {
            this.list.add(position, t);
            notifyItemInserted(position);
        }


        /**
         * 判断是否有数据
         * 没有就直接添加数据，有则更新数据
         *
         * @param list
         */
        public void autoInsertItems(List<T> list) {
            if (this.list == null || this.list.size() == 0) {
                setItems(list);
            } else {
                addItems(list);
            }
        }

        /**
         * 获取对应position的data
         *
         * @param position position in layout
         * @return
         */
        public T getItemByPosition(int position) {
            try {
                if (list != null)
                    return list.get(position);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 获取所有数据
         *
         * @return
         */
        public List<T> getItems() {
            return list;
        }

        /**
         * 删除对应position 的item
         *
         * @param position position in layout
         * @return
         */
        public T removeItem(int position) {
            if (list != null) {
                T t = list.get(position);
                notifyItemRemoved(position);
                list.remove(position);
                return t;
            }
            return null;
        }


        public abstract int onCreateViewLayoutID(int viewType);

        public abstract void onBindViewHolder(ViewHolder holder, T data, int position);

        @Override
        public BaseRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(onCreateViewLayoutID(viewType), parent, false);
            return new BaseRecycleHolder(view);
        }

        @Override
        public void onViewRecycled(BaseRecycleHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public void onBindViewHolder(BaseRecycleHolder holder, final int position) {
            if (position < headerCount) {
                onBindViewHolder(holder.getViewHolder(), null, position);
            } else {
                onBindViewHolder(holder.getViewHolder(), list.get(position - headerCount), position - headerCount);
            }
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(null, v, holder.getLayoutPosition(), holder.getItemId()));
            }
            if (onItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(v -> onItemLongClickListener.onItemLongClick(null, v, holder.getLayoutPosition(), holder.getItemId()));
            }

        }

        @Override
        public int getItemCount() {
            if (list != null)
                return list.size()+headerCount;
            return headerCount;
        }

        public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
            this.onItemLongClickListener = onItemLongClickListener;
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

    }