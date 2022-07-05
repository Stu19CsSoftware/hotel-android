package com.ranlychan.ktv.olley;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class BaseJsonObjectRequest extends JsonObjectRequest {
    private static final String REQUEST_PARAM_KEY_1 = "X-LC-Id";
    private static final String REQUEST_PARAM_KEY_2 = "X-LC-Key";
    private static final String REQUEST_PARAM_VALUE_1 = "LLt4j6GcLm9G33Iz1dRSGKqB-gzGzoHsz";
    private static final String REQUEST_PARAM_VALUE_2 = "AE7yCF8rh4sAEzEliV1c4xB6";



    public BaseJsonObjectRequest(String url, JSONObject jsonRequest,
                           Listener<JSONObject> listener, ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public BaseJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                           Listener<JSONObject> listener, ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    // 重写头信息，为了服务器授权
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put(REQUEST_PARAM_KEY_1,REQUEST_PARAM_VALUE_1);
        headers.put(REQUEST_PARAM_KEY_2,REQUEST_PARAM_VALUE_2);
        headers.put("Content-Type","application/json");
        //headers.put("Host","llt4j6gc.lc-cn-n1-shared.com");
        headers.put("Content-Length",String.valueOf(getBody().length));

        //Log.d("ranly bodylength",String.valueOf(getBody().length));
        //headers.put("Content-Length",String.valueOf(getBody().length));

        //如果已经登录，追加头信息
        /*
        if(LoginHelper.isLogin())
        {
            headers.put(Config.HEADER_LOGIN_KEY, MyApplication.gUserID+","+MyApplication.gUserToken);
        }

         */
        return headers;
    }
}
