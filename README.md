# EchoMTG Java API Wrapper
An api wrapper written in Java for the magic the gathering application EchoMTG. More at https://www.echomtg.com/api/
 * API documentation: https://www.echomtg.com/api/
 * Source: http://github.com/ardeay/EchoMTG-Java-API-Wrapper
 * Note: Library is in beta and provided as-is

_See PHP wrapper: https://github.com/andrewgioia/EchoPHP_ by @andrewgioia


## Dependencies
* Google Volley

This is an open-source library, and contributions are welcome.


# Exmaple Usage
From an inside a Activity or Fragment, you need to instantiate the singleton and feed it a call back along with parameters. In this example, shared prerences is instantiated to store the token recieved from Auth. That token will be used for every other request that will be made, and also for business logic like, if no token, show a login, else show inventory.

    // inventory example
    SharedPreferences sharedPref;
    sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    String email = "your@email.com";
    String pass = "asdfsbfsbsadfsa";
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


                  Toast.makeText(getActivity().getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();

                  SharedPreferences.Editor editor = sharedPref.edit();
                  editor.putString(getString(R.string.token_reference), token);
                  editor.commit();
              }
          },email,pass);
