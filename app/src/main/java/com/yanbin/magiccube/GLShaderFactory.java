package com.yanbin.magiccube;

import android.opengl.GLES20;

/**
 * Created by 彥彬 on 2015/4/5.
 */
public class GLShaderFactory {

    public static final String FIELD_POSITION = "vPosition";
    public static final String FIELD_COLOR = "vColor";

    private static final String SHADER_CODE_VERTEX =
            "attribute vec4 " + FIELD_POSITION  + ";" +
            "void main(){" +
            "   gl_Position = " + FIELD_POSITION  + ";" +
            "}";

    private static final String SHADER_CODE_FRAGMENT =
            "precision mediump float;" +
            "uniform vec4 " + FIELD_COLOR + ";"  +
            "void main(){" +
            "   gl_FragColor = " + FIELD_COLOR + ";"  +
            "}";




    public static int getDefaultShader(){
        GLShaderFactory shaderFactory = new GLShaderFactory();
        return shaderFactory.createShaderProgram(SHADER_CODE_VERTEX, SHADER_CODE_FRAGMENT);
    }

    private int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    private int createShaderProgram(String vertexShaderCode, String fragmentShaderCode){
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        int shaderProgram = GLES20.glCreateProgram();

        GLES20.glAttachShader(shaderProgram, vertexShader);
        GLES20.glAttachShader(shaderProgram, fragmentShader);

        GLES20.glLinkProgram(shaderProgram);

        return shaderProgram;
    }
}
