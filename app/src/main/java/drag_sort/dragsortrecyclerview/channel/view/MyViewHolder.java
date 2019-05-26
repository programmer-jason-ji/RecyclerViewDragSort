package drag_sort.dragsortrecyclerview.channel.view;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ji on 2019/4/30.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    public MyViewHolder(View itemView) {
        super(itemView);
        mViews=new SparseArray();
    }

    public <T extends View> T getView(@IdRes int id){
        View view= mViews.get(id);

        if(view==null){
            view =itemView.findViewById(id);
            mViews.put(id,view);
        }

        return (T) view;
    }

    /**
     * set Text
     */
    public MyViewHolder setText(@IdRes int id, CharSequence text){
        TextView textView = getView(id);
        if(TextUtils.isEmpty(text)){
            textView.setText("");
        }else{
            textView.setText(text);
        }

        return this;
    }




    public MyViewHolder setImageResource(@IdRes int id, @DrawableRes int resId){
        ImageView view = getView(id);
        view.setImageResource(resId);
        return this;
    }


    public MyViewHolder setImage(@IdRes int id, ImageLoader loader){
        ImageView view = getView(id);
        loader.loadImage(view,loader.mPath);
        return this;
    }


    public abstract static class ImageLoader{
        String mPath;
        public ImageLoader(String path){
            this.mPath=path;
        }

        protected abstract void loadImage(ImageView imageView,String path);
    }
}
