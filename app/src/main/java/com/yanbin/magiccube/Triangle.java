package com.yanbin.magiccube;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by 彥彬 on 2015/4/5
 */
public class Triangle {

    private FloatBuffer vertexBuffer;

    private static final int COORDS_PER_VERTEX = 3;
    private static final int BYTES_PER_FLOAT = 4;
    private final int vertexStride = COORDS_PER_VERTEX *BYTES_PER_FLOAT;
    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;

    private static float triangleCoords[] = {
            0.0f, 0.62008459f, 0.0f,  //top
            -0.5f, -0.311004243f, 0.0f,    //bottom left
            0.5f, -0.311004243f, 0.0f,    //bottom right
            };

    private float color[] = {1.0f, 0.0f, 0.0f, 1,0f};  //RGBA
    private int mProgram;

    public Triangle(){
        initVertexBuffer();
        mProgram = GLShaderFactory.getDefaultShader();
    }

    private void initVertexBuffer(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleCoords.length*BYTES_PER_FLOAT);
        byteBuffer.order(ByteOrder.nativeOrder());

        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);
    }

    public void draw(){
        GLES20.glUseProgram(mProgram);

        int positionHandle = GLES20.glGetAttribLocation(mProgram, GLShaderFactory.FIELD_POSITION);
        int colorHandle = GLES20.glGetUniformLocation(mProgram, GLShaderFactory.FIELD_COLOR);

        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
