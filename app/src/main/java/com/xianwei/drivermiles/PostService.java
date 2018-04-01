package com.xianwei.drivermiles;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xianwei li on 3/31/2018.
 */

public interface PostService {

    @POST("/services/T042FMKHH/B1LF1T12L/HoTvYOUSovFpfl9LI59GBKnq")
    Call<Void> postLocation(@Body DataModel locationData);
}
