# Intent_URL
Intent被称为意图，是程序中组件进行交互的一种方式。不仅可以指定当前组件要执行的动作，还可以在不同组件之间进行数据传递。
根据开启目标组件的方式不同，分为显示意图和隐式意图。

1）显示意图：直接通过名称开启指定的目标组件通过其构造方法Intent(Context packageContext,Class<?> cls)第一个参数表示当前的Activity对象，一般可用this,第二个参数表示要启动的目标Activity。

[例]Intent intent=new Intent(this,Activity02.class);
    startActivity(intent);
    
2)隐式意图：通过指定action和category等属性信息，系统根据这些信息进行分析，然后寻找目标Activity。

[例]Intent intent=new Intent();
    //设置action动作，动作要和清单文件中设置一样
    intent.setAction("");
    startActivity(intent);
    
实例场景应用（隐式）

主要代码:

MainActivity.java
package com.example.asus.intent_url;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button begin_go;
    private EditText et_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        begin_go=(Button)findViewById(R.id.begin_go);
        et_url=(EditText)findViewById(R.id.et_url);
        begin_go.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.begin_go:
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(et_url.getText().toString()));
                startActivity(intent);
                //Intent choose=Intent.createChooser(intent,"选择一个浏览器");
                startActivity(Intent.createChooser(intent,"选择一个浏览器"));
                break;
        }
    }
}

//自定义网页浏览器

MyWebView.java
package com.example.asus.intent_url;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends AppCompatActivity {
    private WebView webView;
    private  String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myweb);
        initView();
        setWebView();
    }
    private void initView(){
        webView=(WebView)findViewById(R.id.web_view);
        Intent intent=getIntent();
        url=intent.getData().toString();
    }
    private void setWebView(){
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                webView.loadUrl(url);
                return true;
            }
        });
    }
}

activity_main.xml
<?xml version="1.0" encoding="utf-8"?>


<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <EditText
            android:id="@+id/et_url"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="输入网址"
            android:textSize="20sp" />
    <Button
        android:id="@+id/begin_go"
        android:layout_width="168dp"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="访问" />
    </LinearLayout>

</LinearLayout>
myweb.xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <WebView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/web_view">
    </WebView>

</android.support.constraint.ConstraintLayout>
AndroidManifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.asus.intent_url">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
               <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".MyWebView">
            <intent-filter >
                  <action android:name="android.intent.action.VIEW" />
                  <category android:name="android.intent.category.DEFAULT" />   
                    <category android:name="android.intent.category.BROWSABLE"></category>                
                  <data android:scheme="http"/>
            </intent-filter>
        </activity>
    </application>
    <uses-permission android:name="android.permission.INTERNET" />

</manifest>

运行效果截图：

自定义浏览器

<p align="center">
	<img src="https://github.com/Peiqiye/image/blob/master/intent.png" alt="Sample"  width="350" height="540">
	<p align="center">
		<em>访问界面/自定义浏览器</em>
	</p>
</p>


跳转到百度

<p align="center">
	<img src="https://github.com/Peiqiye/image/blob/master/baidu.png" alt="Sample"  width="350" height="540">
	<p align="center">
		<em>百度页面</em>
	</p>
</p>
