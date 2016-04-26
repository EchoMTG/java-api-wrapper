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

## Add the INTERNET Permission
To use Volley, you must add the `android.permission.INTERNET` permission to your app's manifest. Without this, your app won't be able to connect to the network.

## Gradle Install
*Currently not working until version 1.0 is release* Please copy code into your project for now.

    repositories {
        maven {
            url  "http://dl.bintray.com/ardeay/EchoMTG-Java-API-Wrapper" 
        }
    }



# Example Usage
From an inside a Activity or Fragment, you need to instantiate the singleton and feed it a call back along with parameters. In this example, shared prerences is instantiated to store the token recieved from Auth. That token will be used for every other request that will be made, and also for business logic like, if no token, show a login, else show inventory.

Imports for File

    import com.thoughtbombstudios.echomtg.EchoMTGWrapper.EchoMTGJavaAPIWrapper;
    import org.json.JSONException;
    import org.json.JSONObject;
    
Example of using the EchoMTGAPI Wrapper Singleton with a call back in an activity or fragment class

    // auth example, would be inside an activity or fragment class method
    SharedPreferences sharedPref; // use to store token locally
    sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE); 
    String email = "your@email.com"; // passed through UI input
    String pass = "asdfsbfsbsadfsa"; // passed through UI input 
    EchoMTGJavaAPIWrapper.getInstance(getActivity().getApplicationContext()).authRequest(new EchoMTGJavaAPIWrapper.EchoCallback(){
          @Override
          public void onSuccess(String result){
              JSONObject jsonObject= null;
              String token = null;
              try {

                  jsonObject = new JSONObject(result);
                  token = jsonObject.getString("token");

              } catch (JSONException e) {
                  e.printStackTrace();
              }
              
              // example code to store token to shared preferences
              SharedPreferences.Editor editor = sharedPref.edit();
              editor.putString(getString(R.string.token_reference), token);
              editor.commit();
          }
          public void onFailure(Error result) {
            //your error code
          }
          
      },email,pass);
    
Link to Gist https://gist.github.com/ardeay/7fca0db056c2837933331c75def5c3be.js
