package net.mcarolan.whenzebus.test;

import java.util.Set;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;

import net.mcarolan.whenzebus.api.Response;
import net.mcarolan.whenzebus.api.ResponseParser;
import net.mcarolan.whenzebus.api.field.Field;
import net.mcarolan.whenzebus.api.field.Fields;

import android.test.AndroidTestCase;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class PredictionParserTest extends AndroidTestCase {
	
	private final Set<? extends Field> fields = Sets.newHashSet(Fields.DestinationText, Fields.LineName, Fields.EstimatedTime); 
	
	public void test_isPrediction_on_valid_prediction() throws JSONException {
		final String prediction = "[1,\"242\",\"Tottenham Ct Rd\",1428064256000]";
		final ResponseParser responseParser = new ResponseParser(prediction);
		
		final boolean result = responseParser.isResponse(new JSONArray(prediction), fields);
		Assert.assertTrue(result);
	}
	
	public void test_parsePrediction_on_valid_prediction() throws JSONException {
		final String predictionJsonArray = "[1,\"242\",\"Tottenham Ct Rd\",1428064256000]";
		final ResponseParser responseParser = new ResponseParser(predictionJsonArray);
		final Response prediction = responseParser.parseResponse(new JSONArray(predictionJsonArray), fields);
		Assert.assertEquals("Tottenham Ct Rd", Fields.DestinationText.extract(prediction));
		Assert.assertEquals("242", Fields.LineName.extract(prediction));
		Assert.assertEquals(1428064256000L, Fields.EstimatedTime.extract(prediction).getMillis());
	}
	
	public void test_parsePrediction_on_no_field_prediction() throws JSONException {
		final String predictionJsonArray = "[1]";
		final ResponseParser responseParser = new ResponseParser(predictionJsonArray);
		final ImmutableSet<Field> fields = ImmutableSet.of();
		responseParser.parseResponse(new JSONArray(predictionJsonArray), fields);
	}
	
	public void test_isPrediction_on_invalid_prediction_responsetype() throws JSONException {
		final String prediction = "[0,\"242\",\"Tottenham Ct Rd\",1428064256000]";
		final ResponseParser responseParser = new ResponseParser(prediction);
		final boolean result = responseParser.isResponse(new JSONArray(prediction), fields);
		Assert.assertTrue(!result);
	}
	
	public void test_not_isPrediction_when_fewer_fields() throws JSONException {
		final String prediction = "[1,\"242\",\"Tottenham Ct Rd\"]";
		final ResponseParser responseParser = new ResponseParser(prediction);
		final boolean result = responseParser.isResponse(new JSONArray(prediction), fields);
		Assert.assertFalse(result);
	}
	
	public void test_isPrediction_no_fields() throws JSONException {
		final String prediction = "[1]";
		final ResponseParser responseParser = new ResponseParser(prediction);
		final ImmutableSet<Field> fields = ImmutableSet.of();
		final boolean result = responseParser.isResponse(new JSONArray(prediction), fields);
		Assert.assertTrue(result);
	}

}
