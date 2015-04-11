package com.yanbin.magiccube;

/**
 * Created by 彥彬 on 2015/4/5.
 */
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

public class GLES20Renderer extends GLRenderer {

    private Square square;

    private final float[] mProjectionMatrix = new float[16];

    @Override
    public void onCreate(int width, int height,
                         boolean contextLost) {
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        initProjectionMatrix(width, height);
        square = new Square();
    }

    private void initProjectionMatrix(int width, int height){
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(boolean firstDraw) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT
                | GLES20.GL_DEPTH_BUFFER_BIT);

        float[] mMVPMatrix = getMVPMatrix();
        square.setmMVPMatrix(mMVPMatrix);
        square.draw();
    }

    private float[] getMVPMatrix(){
        float[] mMVPMatrix = new float[16];
        float[] mRotationMatrix = new float[16];
        float[] mViewMatrix = getDefaultViewMatrix();

        long time = SystemClock.uptimeMillis();
        float angle =  0.001f*((float) time);

        Matrix.setRotateM(mRotationMatrix, 0, angle, 0, 0, -1.0f);

        Matrix.multiplyMM(mViewMatrix, 0, mRotationMatrix, 0, mViewMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);


        return mMVPMatrix;
    }

    private float[] getDefaultViewMatrix(){
        float eyeX = 0f;
        float eyeY = 0f;
        float eyeZ = -3f;
        float centerX = 0f;
        float centerY = 0f;
        float centerZ = 0f;
        float upX = 0f;
        float upY = 1f;
        float upZ = 0f;

        float[] mViewMatrix = new float[16];
        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return mViewMatrix;
    }

}
