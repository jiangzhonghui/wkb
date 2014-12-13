package com.eduonline.app.d6.app.androidbook.salbcr;

public class Test30SecBCR
extends ALongRunningReceiver
{
	@Override
	public Class getLRSClass() 
	{
		Utils.logThreadSignature("Test30SecBCR");
		return Test30SecBCRService.class;
	}
}
