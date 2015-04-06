package com.yanbin.magiccube;

/**
 * Created by 彥彬 on 2015/4/5.
 */
import android.opengl.GLES20;

public class GLES20Renderer extends GLRenderer {

    private Square square;

    @Override
    public void onCreate(int width, int height,
                         boolean contextLost) {
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        square = new Square();
        square.setDrawMode(GLES20.GL_TRIANGLES);
    }

    @Override
    public void onDrawFrame(boolean firstDraw) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT
                | GLES20.GL_DEPTH_BUFFER_BIT);

        square.draw();
    }
}
