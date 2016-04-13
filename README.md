# EchoMTG Java API Wrapper
An api wrapper written in Java for the magic the gathering application EchoMTG. More at https://www.echomtg.com/api/
 * API documentation: https://www.echomtg.com/api/
 * Source: http://github.com/ardeay/EchoMTG-Java-API-Wrapper
 * Note: Library is in beta and provided as-is

_See PHP wrapper: https://github.com/andrewgioia/EchoPHP_ by @andrewgioia


This is an open-source library, and contributions are welcome.

## Dependencies

Google Volley http://developer.android.com/training/volley/index.html

    dependencies {
        compile 'com.mcxiaoke.volley:library-aar:1.0.0'
    }




# Example Usage
From an inside a Activity or Fragment, you need to instantiate the singleton and feed it a call back along with parameters. In this example, shared prerences is instantiated to store the token recieved from Auth. That token will be used for every other request that will be made, and also for business logic like, if no token, show a login, else show inventory.


    import com.thoughtbombstudios.echomtg.EchoMTGWrapper.EchoMTGJavaAPIWrapper;
    import org.json.JSONException;
    import org.json.JSONObject;
    
    
<script src="https://gist.github.com/ardeay/7fca0db056c2837933331c75def5c3be.js"></script>
