package net.mcarolan.whenzebus.test;

import junit.framework.Assert;

import org.junit.Test;

import net.mcarolan.whenzebus.api.PredictionRequest;
import net.mcarolan.whenzebus.api.StopCode1;
import net.mcarolan.whenzebus.api.predictionfield.PredictionFields;

import com.google.common.collect.ImmutableSet;

public class PredictionRequestTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void construct_estimatedtime_without_expiretime() {
		new PredictionRequest(ImmutableSet.of(PredictionFields.EstimatedTime), new StopCode1("hi"));
	}
	
	@Test
	public void construct_with_estimatedtime_and_expiretime() {
		new PredictionRequest(ImmutableSet.of(PredictionFields.EstimatedTime, PredictionFields.ExpireTime), new StopCode1("hi"));
	}
	
	@Test
	public void estimatedtime_and_expiretime_geturi() {
		final PredictionRequest request = new PredictionRequest(ImmutableSet.of(PredictionFields.EstimatedTime, PredictionFields.ExpireTime), new StopCode1("hi"));
		final String uri = request.getURI();
		Assert.assertEquals("/interfaces/ura/instant_V1?ReturnList=EsimatedTime,ExpireTime&StopCode1=hi", uri);
	}

}
