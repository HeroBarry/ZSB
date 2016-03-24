package com.dmd.zsb.mvp.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.dmd.tutor.utils.TLog;
import com.dmd.zsb.mvp.interactor.SignInInteractor;
import com.dmd.zsb.mvp.listeners.BaseSingleLoadedListener;
import com.dmd.zsb.utils.UriHelper;
import com.dmd.zsb.utils.VolleyHelper;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Administrator on 2016/3/14.
 */
public class SignInInteractorImpl implements SignInInteractor{

    private BaseSingleLoadedListener<JsonObject> loadedListener;

    public SignInInteractorImpl(BaseSingleLoadedListener<JsonObject> loadedListener) {
        this.loadedListener = loadedListener;
    }

    @Override
    public void signIn(JsonObject jsonObject) {
        GsonRequest<JsonObject> gsonRequest=new GsonRequest<JsonObject>(UriHelper.getInstance().signIn(jsonObject),null,new TypeToken<JsonObject>(){}.getType(), new Response.Listener<JsonObject>(){
            @Override
            public void onResponse(JsonObject response) {
                loadedListener.onSuccess(response);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                loadedListener.onError(error.getMessage());
            }
        });
        gsonRequest.setShouldCache(true);
        gsonRequest.setTag("signIn");
        VolleyHelper.getInstance().getRequestQueue().add(gsonRequest);
    }

}
