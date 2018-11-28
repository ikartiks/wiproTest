package wipro.wiprotest.utility;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import wipro.wiprotest.model.Data;

public interface RetrofitInterface {

    String baseUrl ="https://dl.dropboxusercontent.com/";

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Data> getData();

}

