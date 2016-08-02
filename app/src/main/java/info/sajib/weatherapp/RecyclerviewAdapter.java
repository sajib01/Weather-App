package info.sajib.weatherapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import info.sajib.weatherapp.databinding.FiveDayRecyclerviewLayoutBinding;
import info.sajib.weatherapp.model.weatherForecastModel;
import info.sajib.weatherapp.view.WeatherShow;
import info.sajib.weatherapp.viewmodel.Recyclerviewmodel;

/**
 * Created by sajib on 13-07-2016.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>{
    List<info.sajib.weatherapp.model.List> data= Collections.emptyList();
    Context context;
    OnItemClickListner onItemClickListner;
    public RecyclerviewAdapter(Context weatherShow ,OnItemClickListner onItemClickListner) {
        this.context=weatherShow;
        this.onItemClickListner=onItemClickListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FiveDayRecyclerviewLayoutBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.five_day_recyclerview_layout, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final info.sajib.weatherapp.model.List dataa=data.get(position);
        holder.bindRecyclerviewModel(data.get(position));
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListner.OnItemClick(dataa);

            }
        });
    }

    public interface OnItemClickListner
    {
        void OnItemClick(info.sajib.weatherapp.model.List data);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(weatherForecastModel data) {
        this.data=data.getList();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        FiveDayRecyclerviewLayoutBinding binding;
        public MyViewHolder(FiveDayRecyclerviewLayoutBinding binding)
        {
            super(binding.card);
            this.binding=binding;
        }

        public FiveDayRecyclerviewLayoutBinding getBinding() {
            return binding;
        }

        void bindRecyclerviewModel(info.sajib.weatherapp.model.List list) {
            if(binding.getViewmodel()==null)
            {
                binding.setViewmodel(new Recyclerviewmodel(context,list));
            }
            else
            {
                binding.getViewmodel().setData(list);
            }

        }
    }
}
