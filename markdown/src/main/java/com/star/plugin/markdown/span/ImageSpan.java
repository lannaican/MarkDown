package com.star.plugin.markdown.span;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.star.plugin.markdown.span.base.ClickableSpan;
import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/18 6:16
 */
public abstract class ImageSpan extends DynamicDrawableSpan implements ClickableSpan, MarkDownSpan {

    private Drawable drawable;

    private String des;
    private int desSize;
    private int desColor;

    private float width;
    private float height;
    private float offsetX;
    private float paddingVertical;

    private float lineSpacingMultiplier;


    public ImageSpan(TextView view, @Nullable Bitmap bitmap, String des, int desSize, int desColor) {
        super(ALIGN_BOTTOM);
        this.des = des;
        this.desSize = desSize;
        this.desColor = desColor;
        this.paddingVertical = desSize;
        this.lineSpacingMultiplier = view.getLineSpacingMultiplier();
        if (des == null || des.length() == 0) {
            this.desSize = 0;
        }
        this.drawable = new BitmapDrawable(bitmap);
        if (bitmap != null) {
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            float maxWidth = view.getMeasuredWidth()
                    - view.getPaddingLeft()
                    - view.getPaddingRight();
            if (width > maxWidth) {
                float scale = maxWidth / width;
                height = height * scale;
                width = maxWidth;
            }
            offsetX = (maxWidth - width) / 2;
            drawable.setBounds(0, 0, (int)width, (int)height);
        }
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        if (fm != null) {
            fm.ascent = -(int)(height / lineSpacingMultiplier + desSize * 2 + paddingVertical * 2);
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
        canvas.save();
        canvas.translate(x + offsetX, top + paddingVertical);
        drawable.draw(canvas);
        //绘制说明文字
        if (!TextUtils.isEmpty(des) && width > 0) {
            int textColor = paint.getColor();
            float textSize = paint.getTextSize();
            Paint.Align align = paint.getTextAlign();
            paint.setColor(desColor);
            paint.setTextSize(desSize);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(des, width / 2, height + paddingVertical + desSize, paint);
            paint.setTextAlign(align);
            paint.setTextSize(textSize);
            paint.setColor(textColor);
        }
        canvas.restore();
    }

    @Override
    public void setPressed(boolean pressed) {

    }
}
