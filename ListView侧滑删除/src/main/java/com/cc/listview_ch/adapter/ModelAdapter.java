package com.cc.listview_ch.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cc.listview_ch.R;
import com.cc.listview_ch.model.ModelInfo;
import com.cc.listview_ch.view.SlideLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器
 * @kcw
 */
public class ModelAdapter extends BaseAdapter {

    //上下文
    private Activity activity;

    //数据源
    private List<ModelInfo> datas;

    //获取布局文件
    private LayoutInflater inflater;

    public ModelAdapter(Activity act, List<ModelInfo> data){
        activity = act;
        datas = data;

        //实例化获取xml的东西
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public ModelInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.lv_item, null);
            viewHolder = new ViewHolder();
            viewHolder.txt_item = convertView.findViewById(R.id.txt_item);
            viewHolder.txt_delete = convertView.findViewById(R.id.txt_delete);
            //设置一个tag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据源
        final ModelInfo data = datas.get(position);
        viewHolder.txt_item.setText(data.getName());

        viewHolder.txt_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, datas.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.txt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideLayout slideLayout = (SlideLayout) v.getParent();
                slideLayout.closeMenu();

                datas.remove(data);
                notifyDataSetChanged();
            }
        });

        final SlideLayout slideLayout = (SlideLayout) convertView;

        slideLayout.setOnStatechangelistenter(new MyOnStateChangeListenter());

        return convertView;
    }

    private SlideLayout slideLayout;

    class MyOnStateChangeListenter implements SlideLayout.OnStatechangelistenter{

        @Override
        public void onClose(SlideLayout layout) {
            if(slideLayout == layout){
                slideLayout = null;
            }
        }

        @Override
        public void onDown(SlideLayout layout) {
            if(slideLayout != null && slideLayout != layout){
                slideLayout.closeMenu();
            }
        }

        @Override
        public void onOpen(SlideLayout layout) {
            slideLayout = layout;
        }
    }

    class ViewHolder{
        TextView txt_item;
        TextView txt_delete;
    }

}
