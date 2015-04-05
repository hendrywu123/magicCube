package com.yanbin.magiccube;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    private GLSurfaceView gl_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt_hint = (TextView)findViewById(R.id.txt_hint);
        txt_hint.setText("current GL version: " + Utils.currentGLVersion(this));

        initGLView();
    }

    private void initGLView() {
        if (Utils.has20GLSupport(this)) {
            gl_view = (GLSurfaceView)findViewById(R.id.gl_view);
            gl_view.setEGLContextClientVersion(2);
            gl_view.setPreserveEGLContextOnPause(true);
            gl_view.setRenderer(new GLES20Renderer());
        } else {
            // Time to get a new phone, OpenGL ES 2.0 not supported.
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gl_view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gl_view.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
