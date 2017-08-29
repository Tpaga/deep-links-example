package co.tpaga.deeplinkapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = (WebView) findViewById(R.id.wv);

        String summary = "<html><body><p></p><p>Deeplinks</p><p></p><p>tpaga.co <a href=\"http://w.tpaga.co\">tpaga home</a></p>" +
                "<p>tpaga.co <a href=\"https://w.tpaga.co/gasotp/eyJtIjp7Im8iOiJUUFAiLCJhdCI6IkFVVEhfVE9LRU4ifSwiZCI6eyJkIjoiQ09md28zcC12c3I3SnlBcEFoRmtlRll3In19\">tpaga home</a></p> " +
                "<p>developers <a href=\"https://developer.android.com/\">developers</a></p></p></body></html>";
        wv.loadData(summary, "text/html", null);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "shouldOverrideUrlLoading: " + url);
                Intent intent;

                if (url.contains("w.tpaga")) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                    return true;
                }
                Log.i(TAG, "load url: " + url);

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println("on finish");
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getBaseContext(), "Error:" + description, Toast.LENGTH_SHORT).show();
            }
        });

        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

    }
}
