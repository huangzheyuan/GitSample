package com.example.smartcity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/*** Picasso工具类*/
public class PicassoUtils {
    /**
     * 指定大小加载图片
     *
     * @param mContext   上下文
     * @param path       图片路径
     * @param width      宽
     * @param height     高
     * @param mImageView 控件
     */
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {

        Picasso.get().load(path).resize(width, height).centerCrop().into(mImageView);
    }
    public static void loadImageViewSize2(Context mContext, String path, ImageView mImageView) {
        Picasso.get().load(path).centerCrop().into(mImageView);
    }
    /**
     * 加载有默认图片
     * @param mContext   上下文
     * @param path       图片路径
     * @param mImageView 控件
     */
    public static void loadImageViewHolder(Context mContext, String path, ImageView mImageView) {
        Picasso.get().load(path).fit().into(mImageView);
    }
    /**
     * 裁剪图片
     * @param mContext   上下文
     * @param path       图片路径
     * @param mImageView 控件
     */
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        //Picasso.get().load(path).transform(new CropImageView());
    }
    public static void loadImageViewCrop2(Context mContext, String path, ImageView mImageView) {
        Picasso.get().load(path).into(mImageView);
    }



    /**
     * 自定义图片裁剪
     */
    public static class CropImageView implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap newBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (newBitmap != null) {
                //内存回收
                source.recycle();
            }
            return newBitmap;
        }

        @Override
        public String key() {
            return null;
        }

    }
}

