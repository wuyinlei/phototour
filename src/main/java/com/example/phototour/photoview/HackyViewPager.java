package com.example.phototour.photoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义viewpager解决java.lang.IllegalArgumentException: pointerIndex out of range 
 * 同样适用于：scrollview、webview、mapview等
 * 
 * @author blue
 * 
 */
public class HackyViewPager extends ViewPager
{

	public HackyViewPager(Context context)
	{
		super(context);
	}

	public HackyViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		try
		{
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		try
		{
			return super.onTouchEvent(event);
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
