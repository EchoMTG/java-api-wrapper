import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class EchoMTGJavaAPIWrapper {

    public static final String API_HOST = "https://www.echomtg.com/api/";
    private static final String TAG = "EchoMTGJavaAPIWrapper";

    private static EchoMTGJavaAPIWrapper mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

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

        Toast.makeText(mCtx, "Attempting to authenticate "+email, Toast.LENGTH_SHORT).show();

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        callback.onSuccess(response);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(mCtx, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", pass);
                return params;
            }
        };

        addToRequestQueue(strRequest);
    }

    public interface EchoCallback{
        void onSuccess(String result);
    }

}
