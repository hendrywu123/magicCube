package com.yanbin.magiccube;


import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 彥彬 on 2015/4/4.
 */
public class OpenGLRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        // 清除螢幕和深度緩衝區
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // 設定新視域視窗的大小
        gl.glViewport(0, 0, width, height);
        // 選擇投射的陣列模式
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 重設投射陣
        gl.glLoadIdentity();
        // 計算視窗的寬高比率
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
                100.0f);
        // 選擇MODELVIEW陣列
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 重設MODELVIEW陣列
        gl.glLoadIdentity(); }


    @Override
    public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config) {
        // TODO Auto-generated method stub
        // 設定背景顏色為黑色, 格式是RGBA
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // 設定流暢的陰影模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        // 深度緩區的設定
        gl.glClearDepthf(1.0f);
        // 啟動深度的測試
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // GL_LEQUAL深度函式測試
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // 設定很好的角度計算模式
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); }

}
