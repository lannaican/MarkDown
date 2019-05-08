package com.star.plugin.markdown;

import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.star.plugin.markdown.listener.OnMarkDownListener;
import com.star.plugin.markdown.span.base.ClickableSpan;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/15 5:12
 */
public class MarkDownTextView extends AppCompatTextView {

    public MarkDownTextView(Context context) {
        this(context, null);
    }

    public MarkDownTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarkDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLineSpacing(0, MarkDown.getProperty().getLineSpacingMultiplier());
        setMovementMethod(new LinkTouchMovementMethod());
    }

    public void setMarkDown(String text) {
        MarkDown.set(this, text, null);
    }

    public void setMarkDown(String text, OnMarkDownListener listener) {
        MarkDown.set(this, text, listener);
    }

    @Override
    public void setHighlightColor(int color) {
        super.setHighlightColor(0);
    }

    private boolean linkTouch;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        linkTouch = false;
        super.onTouchEvent(event);
        return linkTouch;
    }

    class LinkTouchMovementMethod extends LinkMovementMethod {

        private ClickableSpan downSpan;

        @Override
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
            int action = event.getAction();
            ClickableSpan span = getPressedSpan(textView, spannable, event);
            if (action == MotionEvent.ACTION_DOWN) {
                downSpan = span;
                if (span == null) {
                    Selection.removeSelection(spannable);
                    Touch.onTouchEvent(textView, spannable, event);
                    return false;
                } else {
                    span.setPressed(true);
                    Selection.setSelection(spannable, spannable.getSpanStart(span), spannable.getSpanEnd(span));
                    linkTouch = true;
                    return true;
                }
            }
            if (action == MotionEvent.ACTION_UP) {
                downSpan = null;
                if (span == null) {
                    Selection.removeSelection(spannable);
                    Touch.onTouchEvent(textView, spannable, event);
                    return false;
                } else {
                    span.onSpanClick(textView);
                    span.setPressed(false);
                    Selection.removeSelection(spannable);
                    linkTouch = true;
                    return true;
                }
            }
            if (action == MotionEvent.ACTION_CANCEL) {
                if (downSpan != null) {
                    downSpan.setPressed(false);
                    downSpan = null;
                }
                Selection.removeSelection(spannable);
            }
            return Touch.onTouchEvent(textView, spannable, event);
        }

        private ClickableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= textView.getTotalPaddingLeft();
            y -= textView.getTotalPaddingTop();

            x += textView.getScrollX();
            y += textView.getScrollY();

            Layout layout = textView.getLayout();
            int verticalLine = layout.getLineForVertical(y);
            int horizontalOffset = layout.getOffsetForHorizontal(verticalLine, x);

            ClickableSpan span = null;

            ClickableSpan[] link = spannable.getSpans(horizontalOffset, horizontalOffset, ClickableSpan.class);
            if (link.length > 0) {
                span = link[0];
            }
            return span;
        }
    }

}
