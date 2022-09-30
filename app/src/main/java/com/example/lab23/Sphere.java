package com.example.lab23;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
public class Sphere {
    public FloatBuffer mVertexBuffer;
    public FloatBuffer textureBuffer;
    public int n = 0;
    private float[][] colors = {
            {0f, 0f, 0f, 0f},
            {0.5f, 0.6f, 0.3f, 0f},
            {0.9f, 1f, 1f, 1f},
    };

    public Sphere() {
        int angleX = 20, angleY = 5;
        float PIf = (float) (Math.PI / 180.0f);

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(8000 * 3 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuf.asFloatBuffer();
        byteBuf = ByteBuffer.allocateDirect(1);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        for (int theta = -90; theta <= 90 - angleX; theta += angleX) {
            for (int phi = 0; phi <= 360; phi += angleY) {
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.cos(phi * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.sin(phi * PIf)) * 3);
                mVertexBuffer.put((float) (Math.sin(theta * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.cos(phi * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.sin(phi * PIf)) * 3);
                mVertexBuffer.put((float) (Math.sin((theta + angleX) * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.cos((phi + angleY) * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos((theta + angleX) * PIf) * Math.sin((phi + angleY) * PIf)) * 3);
                mVertexBuffer.put((float) (Math.sin((theta + angleX) * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.cos((phi + angleY) * PIf)) * 3);
                mVertexBuffer.put((float) (Math.cos(theta * PIf) * Math.sin((phi + angleY) * PIf)) * 3);
                mVertexBuffer.put((float) (Math.sin(theta * PIf)) * 3);
                n += 4;
            }
        }

        mVertexBuffer.position(0);
        textureBuffer.position(0);
    }
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        for (int i = 0; i < n; i += 4) {
            gl.glColor4f(colors[i % 3][0], colors[i % 3][1], 3, 0);
            gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, i, 4);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE
        );
    }
}
