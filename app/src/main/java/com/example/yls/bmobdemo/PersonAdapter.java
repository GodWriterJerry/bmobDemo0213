package com.example.yls.bmobdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yls on 2017/3/6.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<Person> mList;
    private onDelListener mOnDelListener;


    public PersonAdapter(List<Person> list, onDelListener listener) {
        mList = list;
        this.mOnDelListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Person person = mList.get(position);
        holder.tv5.setText(person.getName());
        holder.tv6.setText(person.getAddress());
        holder.tv7.setText(String.valueOf(person.getAge()));
        holder.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            mOnDelListener.refresh();
                            Toast.makeText(MainActivity.mactivity, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.mactivity, "删除失败，很抱歉", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void changeData(List<Person> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv5, tv6, tv7;
        private Button btn5;

        public ViewHolder(View itemView) {
            super(itemView);
            tv5 = (TextView) itemView.findViewById(R.id.tv_5);
            tv6 = (TextView) itemView.findViewById(R.id.tv_6);
            tv7 = (TextView) itemView.findViewById(R.id.tv_7);
            btn5= (Button) itemView.findViewById(R.id.btn_5);
        }
    }
}
