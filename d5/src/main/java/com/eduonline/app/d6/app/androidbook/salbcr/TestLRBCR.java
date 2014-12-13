package com.eduonline.app.d6.app.androidbook.salbcr;

public class TestLRBCR
extends ALongRunningReceiver
{
	@Override
	public Class getLRSClass() 
	{
		Utils.logThreadSignature("TestLRBCR");
		return TestLRBCRService.class;
	}
}
