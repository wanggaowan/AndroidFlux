package com.wgw.flux.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    
    private TextView mTextView;
    
    private MainStore mMainStore;
    
    private MainActionCreator mCreator;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTextView = findViewById(R.id.textView);
        
        mCreator = new MainActionCreator();
        
        mMainStore = new MainStore();
        mMainStore.register(ActionType.CHANGE_TEXT);
        mMainStore.text.observe(this, s -> mTextView.setText(s));
    }
    
    public void changeText(View view) {
        mCreator.changeText();
    }
}
