

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class EchoMTGJavaAPIWrapper {

    public static final String API_HOST = "https://www.echomtg.com/api/";
    private static final String TAG = "EchoMTGJavaAPIWrapper";

    private static EchoMTGJavaAPIWrapper mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    private static final String app_name = "YOUR_REGISTERED_APP_NAME"; // email teeg at echomtg dot com to register an app

    private EchoMTGJavaAPIWrapper(Context context)
    {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized EchoMTGJavaAPIWrapper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new EchoMTGJavaAPIWrapper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void authRequest(final EchoCallback callback, final String email, final String pass){
        String url = API_HOST + "user/auth/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", pass);
                params.put("app", app_name);
                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public void registerUser(final EchoCallback callback, final String username, final String email, final String pass){
        String url = API_HOST + "user/register/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("username", username);
                params.put("password", pass);
                params.put("app", app_name);
                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public void addToInventory(final EchoCallback callback, final String auth, final int mid, final boolean foil){
        String url = API_HOST + "inventory/add/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mid", Integer.toString(mid));
                if(foil){
                    params.put("foil", "1");
                } else {
                    params.put("foil", "0");
                }
                params.put("auth", auth);

                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public void toggleInventoryItemFoil(final EchoCallback callback, final String auth, final int inventory_id, final boolean foil){
        String url = API_HOST + "inventory/toggle_foil/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("inventory_id", Integer.toString(inventory_id));
                if(foil){
                    params.put("foil", "1");
                } else {
                    params.put("foil", "0");
                }

                params.put("auth", auth);

                return params;
            }
        };

        addToRequestQueue(strRequest);
    }


    public void removeFromInventory(final EchoCallback callback, final String auth, final int inventory_id){
        String url = API_HOST + "inventory/remove/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("inventory_id", Integer.toString(inventory_id));
                params.put("auth", auth);

                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public void addToWatchlist(final EchoCallback callback, final String auth, final int multiverse_id){
        String url = API_HOST + "watchlist/add/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mid", Integer.toString(multiverse_id));
                params.put("auth", auth);

                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public void removeFromWatchlist(final EchoCallback callback, final String auth, final int watchlist_id){
        String url = API_HOST + "watchlist/remove/";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            callback.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //error here
                        callback.onFailure("error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", Integer.toString(watchlist_id));
                params.put("auth", auth);

                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public interface EchoCallback{
        void onSuccess(JSONObject result);
        void onFailure(String error);
    }

}
