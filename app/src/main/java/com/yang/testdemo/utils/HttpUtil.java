package com.yang.testdemo.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonStringRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author LuCenly
 *         <p/>
 *         <p>
 *         Http访问请求封装
 *         </p>
 *         2015年7月23日
 */
public class HttpUtil {

    private static final String TAG = "HttpUtil";

    private static RequestQueue requestQueue = null;
    private static ImageLoader imgLoader = null;


    private static final AtomicInteger REQ_ID = new AtomicInteger();

    public static void init(Context _context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(_context);
            requestQueue.start();
        }
    }

    public static synchronized void start(String url,
                                          final Map<String, String> map, Listener<String> _listener,
                                          ErrorListener _errorListener) {
        int id = REQ_ID.getAndIncrement();
        Log.d(TAG, id + "_StringRequest: " + url);
        ListenerWrap<String> listener = new ListenerWrap<String>(id, _listener);
        ErrorListenerWrap errListener = new ErrorListenerWrap(id,
                _errorListener);
        StringRequest stringRequest = new StringRequest(Method.POST, url,
                listener, errListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static StringRequest addStringRequest(int _method, String _uri,
                                                 Listener<String> _listener, ErrorListener _errorListener) {
        int id = REQ_ID.getAndIncrement();

        Log.d(TAG, id + "_StringRequest: " + _uri);

        ListenerWrap<String> listener = new ListenerWrap<String>(id, _listener);
        ErrorListenerWrap errListener = new ErrorListenerWrap(id,
                _errorListener);
        StringRequest req = new StringRequest(_method, _uri, listener,
                errListener);
        requestQueue.add(req);
        return req;
    }

    public static StringRequest addStringPostRequest(String _uri,
                                                     Listener<String> _listener, ErrorListener _errorListener) {
        return addStringRequest(Method.POST, _uri, _listener, _errorListener);
    }

    /**
     * @param url
     * @param jsonStr
     * @param _listener
     * @param _errorListener
     */
    public static JsonStringRequest addJsonPostRequest(final String url,
                                                       final String jsonStr, final Listener<JSONObject> _listener,
                                                       final ErrorListener _errorListener) {
        int id = REQ_ID.getAndIncrement();

        Log.d(TAG, id + "_JsonRequest: " + url + ",params:[" + jsonStr + "]");

        ListenerWrap<JSONObject> listener = new ListenerWrap<JSONObject>(id, _listener);
        ErrorListenerWrap errListener = new ErrorListenerWrap(id, _errorListener);
        JsonStringRequest req = new JsonStringRequest(Method.POST, url,
                jsonStr, listener, errListener);
        req.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        requestQueue.add(req);
        return req;
    }

    private static class ListenerWrap<T> implements Listener<T> {
        private int id;
        private Listener<T> realListener;

        public ListenerWrap(int _id, Listener<T> _realListener) {
            id = _id;
            realListener = _realListener;
        }

        @Override
        public void onResponse(final T dataStr) {
            realListener.onResponse(dataStr);
        }
    }

    private static class ErrorListenerWrap implements ErrorListener {
        private int id;
        private ErrorListener realListener;

        public ErrorListenerWrap(int _id, ErrorListener _realListener) {
            id = _id;
            realListener = _realListener;
        }

        @Override
        public void onErrorResponse(final VolleyError arg0) {
            realListener.onErrorResponse(arg0);
        }
    }

}
