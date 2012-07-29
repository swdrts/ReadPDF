package com.yhx.readpdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // CopyAssets();
        // AssetManager assetManager = getAssets();
        // File file = new File("file:///android_assets/jpg/test.jpg");
        // Uri path = Uri.fromFile(file);
        // Uri path =
        // Uri.parse("android.resource://com.yhx.readpdf/assets/pdf/test.pdf");
        // Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setDataAndType(path, "application/pdf");
        // Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setDataAndType(path, "image/*");
        // startActivity(intent);

        /*
         * try { InputStream in = new FileInputStream(file); OutputStream out =
         * new FileOutputStream("/sdcard/tt.jpg"); copyFile(in, out); } catch
         * (FileNotFoundException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
         * catch block e.printStackTrace(); }
         */
        InputStream abpath = getClass().getResourceAsStream("/assets/text.jpg");
        String path;
        try {
            path = new String(InputStreamToByte(abpath));
            Log.e("hex", path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void CopyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                out = new FileOutputStream("/sdcard/" + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch (Exception e) {
                Log.e("tag", e.getMessage());
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }

}
