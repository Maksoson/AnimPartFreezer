package com.example.a1.animpartfreezer.Adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.animpartfreezer.Interface.ItemClickListener;
import com.example.a1.animpartfreezer.Model.Recipe;
import com.example.a1.animpartfreezer.R;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import org.w3c.dom.Text;

import java.util.List;

class MyViewHolderWithoutChild extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textView;

    ItemClickListener itemClickListener;

    public MyViewHolderWithoutChild(View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.textView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}

class MyViewHolderWithChild extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textView,textViewChild;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLayout;

    ItemClickListener itemClickListener;

    public MyViewHolderWithChild(View itemView){
        super(itemView);
        textView =itemView.findViewById(R.id.textView);
        textViewChild = itemView.findViewById(R.id.textViewChild);
        button = (RelativeLayout) itemView.findViewById(R.id.button);
        expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }
}


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Recipe> recipes;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public MyAdapter(List<Recipe> recipes){
        this.recipes = recipes;
        for (int i=0;i<recipes.size();i++)
            expandState.append(i,false);
    }

    //press ctrl+o


    @Override
    public int getItemViewType(int position) {
        if(recipes.get(position).isExpandable())
            return 1;
        else
            return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        this.context = parent.getContext();
        if (viewType == 0) //Without items
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_without_child,parent,false);
            return new MyViewHolderWithoutChild(view);
        }
        else
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_with_child,parent,false);
            return new MyViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, final int position){

        switch (holder.getItemViewType())
        {
            case 0:
            {
                MyViewHolderWithoutChild viewHolder = (MyViewHolderWithoutChild) holder;
                final Recipe recipe = recipes.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(recipes.get(position).getRecipeName());

                //Set Event
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "Without child click: "+recipes.get(position).getRecipeName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
            case 1:
            {
                final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild) holder;
                Recipe recipe = recipes.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(recipe.getRecipeName());
                //Because we using Recycler View so we need use 'ExpandableLinearLayout'
                viewHolder.expandableLayout.setInRecyclerView(true);
                viewHolder.expandableLayout.setExpanded(expandState.get(position));
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolder.button,0f,180f).start();
                        expandState.put(position,true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.button,180f,0f).start();
                        expandState.put(position,false);
                    }

                });

                viewHolder.button.setRotation(expandState.get(position)?180f:0f);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Expandable child item
                        viewHolder.expandableLayout.toggle();
                    }
                });

                viewHolder.textViewChild.setText(recipes.get(position).getProductName());

                viewHolder.textViewChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, ""+recipes.get(position).getProductName(), Toast.LENGTH_SHORT).show();
                    }
                });

                //Set Event
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(context, "With child click: "+recipes.get(position).getRecipeName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            break;
            default:
                break;
        }
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation",from,to);
        animator.setDuration(200);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount()
    {
        return recipes.size();
    }
}
