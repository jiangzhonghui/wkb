package com.eduonline.app.d6.app.androidbook.asynctask;

import android.content.Context;

public class BaseTester 
{
	protected IReportBack mReportTo;
	protected Context mContext;
	public BaseTester(Context ctx, IReportBack target)
	{
		mReportTo = target;
		mContext = ctx;
	}
}
