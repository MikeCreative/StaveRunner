package com.Howesthatapp.com;

/**
 * Created by Mike on 15/06/2015.
 *
 */
public class ShadersClass {

    public static String vertexShader = "attribute vec4 a_position;    \n" +
            "attribute vec4 a_color;\n" +
            "attribute vec2 a_texCoord0;\n" +
            "uniform mat4 u_projTrans;\n" +
            "varying vec4 v_color;" +
            "varying vec2 v_texCoords;" +
            "void main()                  \n" +
            "{                            \n" +
            "   v_color = vec4(1, 1, 1, 1); \n" +
            "   v_texCoords = a_texCoord0; \n" +
            "   gl_Position =  u_projTrans * a_position;  \n"      +
            "}         " +
            "" +
            "                   \n" ;


    public static String fragmentShader = "#ifdef GL_ES\n" +
            "precision highp float;\n" +
            "#endif\n" +
            "uniform float time;\n" +
            "uniform vec2 resolution;\n" +
            "uniform sampler2D tex;\n" +
            "uniform vec2 coords;\n" +

            "void main()                                  \n" +
            "{                                            \n" +
            "  vec2 cPos = -1.0 + 2.0 * (gl_FragCoord.xy + coords.xy) / resolution.xy;\n" +
            "  float cLength = length(cPos);\n" +

            "  vec2 uv = gl_FragCoord.xy/resolution.xy + (cPos/cLength)*sin(cLength*12.0-time*5.0)*0.01/(time + 1.0);\n" +
            "  vec3 col = texture2D(tex, uv).xyz;\n" +
            "  gl_FragColor = vec4(col, 1.0);\n" +
            "}";


}
