package com.xianwei.drivermiles;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.core.client.SessionConfiguration;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    LoginManager loginManager;
    LoginCallback loginCallback;
    SessionConfiguration config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        initialUber();
    }

    private void initialUber() {
         config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("y7JB7wn7sUPYc690VXFJxjRUwUk-BFIm")
                // required for enhanced button features
                .setServerToken("gVxF2QOWORFCP7kskG7JVRWZryq_p4n7mnpkPOhC")
                // required for implicit grant authentication
                .setRedirectUri("com.xianwei.drivermiles.uberauth://redirect")
                // optional: set sandbox as operating environment
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .setScopes(Arrays.asList(Scope.PROFILE))
                .build();

        loginCallback = new LoginCallback() {
            @Override
            public void onLoginCancel() {
                // User canceled login
                Log.i("12345", "logincancel");
            }

            @Override
            public void onLoginError(@NonNull AuthenticationError error) {
                // Error occurred during login
                Log.i("12345", "onLoginError");
                Log.i("12345", error.name());

            }

            @Override
            public void onLoginSuccess(@NonNull AccessToken accessToken) {
                // Successful login!  The AccessToken will have already been saved.
                startActivity(new Intent(MainActivity.this,HostActivity.class));
            }

            @Override
            public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
                Log.i("12345", "onAuthorizationCodeReceived");
            }
        };

        AccessTokenManager accessTokenManager = new AccessTokenManager(this);
        loginManager = new LoginManager(accessTokenManager, loginCallback, config);
//        loginManager.setAuthCodeFlowEnabled(true);
    }

    @OnClick(R.id.btn_create_account)
    void createAccount(){
        loginManager.login(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        loginManager.onActivityResult(this, requestCode, resultCode, data);
    }
}
