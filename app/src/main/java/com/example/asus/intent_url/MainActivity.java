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
   // private String urlHead="https://";

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
