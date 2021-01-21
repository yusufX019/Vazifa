package com.example.vazifa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Task> tasks;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        private final CheckBox checkBox;

        //Constructor of Inner class
        public ViewHolder(View view){
            super(view);

            textView=view.findViewById(R.id.text_inList);
            checkBox=view.findViewById(R.id.checkbox_inList);
        }

        //Getters
        public TextView getTextView() { return textView; }
        public CheckBox getCheckBox() { return checkBox; }
    }

    //Constructor
    public CustomAdapter(List<Task> tasks){ this.tasks=tasks; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_with_checkbox,viewGroup,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position){
        viewHolder.getTextView().setText(tasks.get(position).getName());
        viewHolder.getCheckBox().setChecked(tasks.get(position).isCompleted());
    }

    @Override
    public int getItemCount(){return tasks.size();}

    private  View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.getContext(),"Clicked",Toast.LENGTH_SHORT).show();
        }
    };


}
