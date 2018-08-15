package adel.co.asyst.learnrecycleview.retrofit;

import java.util.ArrayList;

import adel.co.asyst.learnrecycleview.model.AlbumModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("music_albums")
    Call<ArrayList<AlbumModel>> getAlbums();
}
