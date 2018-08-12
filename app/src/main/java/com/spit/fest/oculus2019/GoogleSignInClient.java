package com.spit.fest.oculus2019;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSignInClient
{
    private static com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;

    public static com.google.android.gms.auth.api.signin.GoogleSignInClient getGoogleSignInClient(Context context) {
        if (googleSignInClient!=null)
            return googleSignInClient;
        return getClient(context);
    }

    private static com.google.android.gms.auth.api.signin.GoogleSignInClient getClient(Context context)
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken("1097191139368-6umkfful9s7gsrvhkurp8pmtkndcsup7.apps.googleusercontent.com")
                .requestId()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context,gso);
        return googleSignInClient;
    }
}
