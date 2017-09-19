package com.manhdaovan.pluzzlegame.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String TAG = "Utils";
    public static final int MODE_ALL = 1;
    public static final int MODE_FILE_ONLY = 2;
    public static final int MODE_DIR_ONLY = 3;

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    @Nullable
    public static String uriToMd5(ContentResolver contentResolver, Uri uri){
        MessageDigest messageDigest = uriToMsgDigest(contentResolver, uri);
        if(messageDigest != null){
            return bytesToHex(messageDigest.digest());
        }
        return null;
    }

    @Nullable
    public static MessageDigest uriToMsgDigest(ContentResolver contentResolver, Uri uri){
        MessageDigest messageDigest = null;

        try{
            messageDigest = MessageDigest.getInstance("MD5");
            BufferedInputStream inputStream = new BufferedInputStream(contentResolver.openInputStream(uri));
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
            byte[] buffer = new byte[1024];
            while (digestInputStream.read(buffer, 0, buffer.length) != -1);

        }catch (Exception e){
            messageDigest = null;
        }

        return messageDigest;
    }

    @Nullable
    public static File createFolder(Context context, String folderName){
        try {
            File fileDir = context.getFilesDir();
            File newFolder = new File(fileDir.getAbsolutePath() + "/" + folderName);

            if(newFolder.exists() || newFolder.mkdir()){
                Toast.makeText(context, "Create folder: " + newFolder.toString(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Create folder: " + newFolder.toString());
                return newFolder;
            }else{
                Toast.makeText(context, "Cannot create folder: " + newFolder.toString(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Cannot create folder: " + newFolder.toString());
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<File> getDirs(Context context, int mode){
        File fileDir = context.getFilesDir();
        File[] allFiles = fileDir.listFiles();
        List<File> dirs = new ArrayList<>();

        for (File f : allFiles) {
            Log.e("DIRRR getAbsolutePath", "" + f.getAbsolutePath());
            Log.e("DIRRR isDirectory", "" + f.isDirectory());
            Utils.getAllSubFilesAndFolders(f);
            switch (mode){
                case MODE_ALL:
                    dirs.add(f);
                    break;
                case MODE_DIR_ONLY:
                    if (f.isDirectory()) dirs.add(f);
                    break;
                case MODE_FILE_ONLY:
                    if (f.isFile()) dirs.add(f);
                    break;
                default:
                    break;
            }
        }

        return dirs;
    }

    public static File saveFile(Context context, String targetFolder, String targetFileName, String sourceFile){
        FileInputStream fis;
        FileOutputStream fos;

        try {
            fis = new FileInputStream(new File(sourceFile));
            fos = new FileOutputStream(new File(targetFolder, targetFileName));
            byte[] buffer = new byte[1024];

            while(fis.read(buffer) != -1) fos.write(fis.read(buffer));

            fis.close();
            fos.close();

            Log.e(TAG, "saveFile OK: " + targetFolder + "/" + targetFileName);
            return new File(targetFolder, targetFileName);
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "saveFile NOTTTT OK: " + targetFolder + "/" + targetFileName + ":::" + e.getMessage());
            return null;
        }
    }

    public static List<File> getAllSubFilesAndFolders(File parent){
        File[] allFiles = parent.listFiles();
        List<File> results = new ArrayList<>();

        for (File f : allFiles) {
            Log.e("DIRRR getAbsolutePath", "" + f.getAbsolutePath());
            Log.e("DIRRR isDirectory", "" + f.isDirectory());
            results.add(f);
        }

        return results;
    }
}
