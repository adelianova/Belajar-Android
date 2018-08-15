package adel.co.asyst.learnrecycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import adel.co.asyst.learnrecycleview.R;
import adel.co.asyst.learnrecycleview.model.AlbumModel;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<AlbumModel> mListAlbum;
    onItemClickListener mListener;

    public AlbumAdapter(Context context, ArrayList<AlbumModel> listAlbum) {
        this.mContext = context;
        this.mListAlbum = listAlbum;
    }

    public AlbumAdapter(Context context, ArrayList<AlbumModel> listAlbum, onItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mListAlbum = listAlbum;
        this.mListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);

        return new AlbumAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final AlbumModel albumModel = mListAlbum.get(position);
        holder.artisttv.setText(albumModel.getArtist());
        holder.albumtv.setText(albumModel.getTitle());

        RequestOptions requestOptions = new RequestOptions().placeholder(mContext.getResources().getDrawable(R.drawable.ic_cloud)).error(R.drawable.ic_cloud);
        Glide.with(mContext).load(albumModel.getImage()).apply(requestOptions).into(holder.albumiv);

        holder.albumCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(albumModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListAlbum.size();
    }

    public interface onItemClickListener {
        void onItemClickListener(AlbumModel albumModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView albumiv;
        TextView albumtv, artisttv;
        CardView albumCardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            albumiv = itemView.findViewById(R.id.image_view_album);
            albumtv = itemView.findViewById(R.id.text_view_album);
            artisttv = itemView.findViewById(R.id.text_view_name);
            albumCardView = itemView.findViewById(R.id.cardView);
        }
    }
}
