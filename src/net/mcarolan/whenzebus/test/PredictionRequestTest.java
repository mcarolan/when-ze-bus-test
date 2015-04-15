package net.mcarolan.whenzebus.test;

import junit.framework.Assert;

import org.junit.Test;

import net.mcarolan.whenzebus.api.Request;
import net.mcarolan.whenzebus.api.StopCode1;
import net.mcarolan.whenzebus.api.field.Fields;

import com.google.common.collect.ImmutableSet;

public class PredictionRequestTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void construct_estimatedtime_without_expiretime() {
		new Request(ImmutableSet.of(Fields.EstimatedTime), new StopCode1("hi"), false);
	}
	
	@Test
	public void construct_with_estimatedtime_and_expiretime() {
		new Request(ImmutableSet.of(Fields.EstimatedTime, Fields.ExpireTime), new StopCode1("hi"), false);
	}
	
	@Test
	public void estimatedtime_and_expiretime_geturi() {
		final Request request = new Request(ImmutableSet.of(Fields.EstimatedTime, Fields.ExpireTime), new StopCode1("hi"), false);
		final String uri = request.getURI();
		Assert.assertEquals("/interfaces/ura/instant_V1?ReturnList=EsimatedTime,ExpireTime&StopCode1=hi", uri);
	}

}
