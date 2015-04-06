package com.yanbin.magiccube;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by 彥彬 on 2015/4/5
 */
public class Square {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    private static final int COORDS_PER_VERTEX = 3;
    private static final int BYTES_PER_FLOAT = 4;
    private static final int BYTES_PER_SHORT = 4;

    private final int vertexStride = COORDS_PER_VERTEX *BYTES_PER_FLOAT;
    private final int vertexCount = squareCoords.length / COORDS_PER_VERTEX;

    private static float squareCoords[] = {
            -0.5f, 0.5f, 0.0f,  //top left
            -0.5f, -0.5f, 0.0f,    //bottom left
            0.5f, -0.5f, 0.0f,    //bottom right
            0.5f, 0.5f, 0.0f      //top right
            };

    private short drawOrder[] = {0, 1, 2, 0, 2, 3};

    private float color[] = {1.0f, 0.0f, 0.0f, 1,0f};  //RGBA
    private int mProgram;
    private int mDrawMode = -1;

    public Square(){
        initVertexBuffer();
        initDrawListBuffer();
        mProgram = GLShaderFactory.getDefaultShader();
    }

    public void setDrawMode(int mode){
        mDrawMode = mode;
    }

    private void initVertexBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(squareCoords.length*BYTES_PER_FLOAT);
        byteBuffer.order(ByteOrder.nativeOrder());

        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);
    }

    private void initDrawListBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(drawOrder.length * BYTES_PER_SHORT);

        byteBuffer.order(ByteOrder.nativeOrder());
        drawListBuffer = byteBuffer.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }

    public void draw(){
        GLES20.glUseProgram(mProgram);

        int positionHandle = GLES20.glGetAttribLocation(mProgram, GLShaderFactory.FIELD_POSITION);
        int colorHandle = GLES20.glGetUniformLocation(mProgram, GLShaderFactory.FIELD_COLOR);

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        drawTriangles();

        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    private void drawTriangles(){
        if(mDrawMode == GLES20.GL_TRIANGLE_FAN ||
                mDrawMode == GLES20.GL_TRIANGLE_STRIP  ||
                mDrawMode == GLES20.GL_TRIANGLES)
            GLES20.glDrawArrays(mDrawMode, 0, vertexCount);
        else
            GLES20.glDrawElements(mDrawMode, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);
    }
}
