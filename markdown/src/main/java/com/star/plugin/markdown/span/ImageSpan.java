package com.star.plugin.markdown.span;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.span.base.ClickableSpan;
import com.star.plugin.markdown.span.base.MarkDownSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/18 6:16
 */
public abstract class ImageSpan extends DynamicDrawableSpan implements ClickableSpan, MarkDownSpan {

    private Drawable drawable;

    private String des;
    private int desSize;

    public ImageSpan(@Nullable Bitmap bitmap, String des) {
        super(ALIGN_BOTTOM);
        this.des = des;
        this.desSize = MarkDown.getProperty().getImageDesSize();
        this.drawable = new BitmapDrawable(bitmap);
        if (bitmap != null) {
            drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        int height = MarkDown.getProperty().getImageHeight();
        if (fm != null) {
            fm.ascent = -(int)((float)height / MarkDown.getProperty().getLineSpacingMultiplier() + desSize * 2);
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return -1;
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top,
                     int y, int bottom, @NonNull Paint paint) {
        Drawable drawable = getDrawable();
        Rect rect = drawable.getBounds();
        float originWidth = rect.width();
        float originHeight = rect.height();
        float maxHeight = MarkDown.getProperty().getImageHeight();
        float maxWidth = (int)(canvas.getWidth() - (x * 2));
        float offsetX, offsetY;
        float width = originWidth;
        float height = originHeight;
        //先限制高度在最大高度以内，在限制宽度
        boolean horizontalLimit = originWidth / maxWidth > originHeight / maxHeight;
        if (horizontalLimit) {
            if (width > maxWidth) {
                float scale = maxWidth / width;
                width = maxWidth;
                height = height * scale;
            }
        } else {
            if (originHeight > maxHeight) {
                float scale = maxHeight / originHeight;
                height = maxHeight;
                width = width * scale;
            }
        }
        offsetX = (maxWidth - width) / 2;
        offsetY = (maxHeight - height) / 2;
        drawable.setBounds(0, 0, (int)width, (int)height);

        canvas.save();
        //int transY = bottom - drawable.getBounds().bottom - (int)offsetY - desSize * 3;
        canvas.translate(x + offsetX, top + offsetY);
        drawable.draw(canvas);
        //绘制说明文字
        int textColor = paint.getColor();
        float textSize = paint.getTextSize();
        Paint.Align align = paint.getTextAlign();
        paint.setColor(MarkDown.getProperty().getImageDesColor());
        paint.setTextSize(desSize);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(des, width / 2, height + desSize * 2, paint);
        paint.setTextAlign(align);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        canvas.restore();
    }

    @Override
    public void setPressed(boolean pressed) {

    }
}
