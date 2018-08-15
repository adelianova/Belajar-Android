package adel.co.asyst.learnrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import adel.co.asyst.learnrecycleview.adapter.AlbumAdapter;
import adel.co.asyst.learnrecycleview.model.AlbumModel;
import adel.co.asyst.learnrecycleview.retrofit.ApiClient;
import adel.co.asyst.learnrecycleview.retrofit.ApiServices;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    AlbumAdapter albumAdapter;
    ArrayList<AlbumModel> listAlbum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerView);

//        for(int i = 0;i<10;i++){
//            AlbumModel albumModel = new AlbumModel();
//            albumModel.setTitle("Title "+i);
//            albumModel.setArtist("Artist "+i);
//            albumModel.setImage("https://images-na.ssl-images-amazon.com/images/I/61McsadO1OL.jpg");
//
//            listAlbum.add(albumModel);
//        }
        // albumAdapter = new AlbumAdapter(this,listAlbum);

        //  getDataWithVolley();
        getDataWithRetrofit();
        albumAdapter = new AlbumAdapter(this, listAlbum, new AlbumAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(AlbumModel albumModel) {
                Toast.makeText(getApplicationContext(), albumModel.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(albumAdapter);
    }

    //pakai retrofit
    private void getDataWithRetrofit() {
        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);

        Call<ArrayList<AlbumModel>> call = apiServices.getAlbums();
        call.enqueue(new Callback<ArrayList<AlbumModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AlbumModel>> call, retrofit2.Response<ArrayList<AlbumModel>> response) {
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        listAlbum.addAll(response.body());

                        albumAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AlbumModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
//pakai volley
//    public void getDataWithVolley(){
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        String url="http://rallycoding.herokuapp.com/api/music_albums";
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>(){
//            @Override
//            public void onResponse(JSONArray response) {
//                Log.d("Response",response.toString());
//                    try {
//                        for (int i = 0; i < response.length(); i++) {
//                            AlbumModel albumModel = new Gson().fromJson(response.getString(i), AlbumModel.class);
//                            listAlbum.add(albumModel);
//                        }
//
//                        albumAdapter.notifyDataSetChanged();
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//    }
}
