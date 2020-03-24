package com.example.bf.kf.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.bf.kf.R;


/**
 * 数字导航view
 */
public class WordsNavigationView extends View {
        private WordsSelectLister listener;
        /*绘制的列表导航字母*/
        private String words[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        private int itemWidth,itemHeight;
        private int touchIndex=0;

        /*字母画笔*/
        private Paint wordsPaint;
        /*字母背景画笔*/
        private Paint bgPaint;

        public WordsNavigationView(Context context) {
                this(context,null);
        }

        public WordsNavigationView(Context context,  AttributeSet attrs) {
                this(context, attrs,0);
        }

        public WordsNavigationView(Context context,AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                wordsPaint=new Paint();
                wordsPaint.setColor(getResources().getColor(R.color.black_999));
                wordsPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.words_size));
                bgPaint=new Paint();

                bgPaint.setColor(getResources().getColor(R.color.black_E5E5E5));
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec,heightMeasureSpec);
                itemHeight=(getMeasuredHeight()-10)/27;
                itemWidth= getMeasuredWidth();
        }

        @Override
        protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                for(int i=0;i<words.length;i++){
                        if (touchIndex==i){
                                //绘制文字圆形背景
                                canvas.drawCircle(itemWidth/2,itemHeight/2+i*itemHeight,24,bgPaint);
                                wordsPaint.setColor(getResources().getColor(R.color.black_333));
                        }else{
                                wordsPaint.setColor(getResources().getColor(R.color.black_999));
                        }
                        //获取文字的宽高
                        Rect rect = new Rect();
                        wordsPaint.getTextBounds(words[i], 0, 1, rect);
                        int wordWidth = rect.width();
                        //绘制字母
                        float wordX = itemWidth / 2 - wordWidth / 2-2;
                        float wordY = itemHeight / 2+rect.height()/2 + i * itemHeight;
                        canvas.drawText(words[i], wordX, wordY, wordsPaint);

                }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_MOVE:
                                float y = event.getY();
                                //关键点===获得我们按下的是那个索引(字母)
                                int index = (int) (y / itemHeight);
                                if (index != touchIndex)
                                        touchIndex = index;
                                //防止数组越界
                                if (listener != null && 0 <= touchIndex && touchIndex <= words.length - 1) {
                                        //回调按下的字母
                                        listener.wordsChange(words[touchIndex]);
                                }
                                invalidate();
                                break;
                        case MotionEvent.ACTION_UP:
                                //手指抬起,不做任何操作
                                break;
                }
                return true;
        }

        /**
         * 设置选择的letter
         * @param letter
         */
        public void setSelectLetter(String letter){
                boolean hasLetter=false;
                for(int i=0;i<words.length;i++){
                        String showLetter=words[i];
                        if(showLetter.equalsIgnoreCase(letter)){
                                touchIndex=i;
                                hasLetter=true;
                                break;
                        }
                }
                if(hasLetter){
                        invalidate();
                }
        }

        public void setWordsSelectLister(WordsSelectLister wordsSelectLister){
                this.listener=wordsSelectLister;
        }
        public  interface  WordsSelectLister{
                void  wordsChange(String words);
        }
}