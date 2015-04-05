package net.mcarolan.whenzebus.test;

import java.util.Set;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;

import net.mcarolan.whenzebus.api.Prediction;
import net.mcarolan.whenzebus.api.PredictionParser;
import net.mcarolan.whenzebus.api.predictionfield.PredictionField;
import net.mcarolan.whenzebus.api.predictionfield.PredictionFields;

import android.test.AndroidTestCase;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class PredictionParserTest extends AndroidTestCase {
	
	private final Set<? extends PredictionField> fields = Sets.newHashSet(PredictionFields.DestinationText, PredictionFields.LineName, PredictionFields.EstimatedTime); 
	private final PredictionParser predictionParser = new PredictionParser();
	
	public void test_isPrediction_on_valid_prediction() throws JSONException {
		final String prediction = "[1,\"242\",\"Tottenham Ct Rd\",1428064256000]";
		final boolean result = predictionParser.isPrediction(new JSONArray(prediction), fields);
		Assert.assertTrue(result);
	}
	
	public void test_parsePrediction_on_valid_prediction() throws JSONException {
		final String predictionJsonArray = "[1,\"242\",\"Tottenham Ct Rd\",1428064256000]";
		final Prediction prediction = predictionParser.parsePrediction(new JSONArray(predictionJsonArray), fields);
		Assert.assertEquals("Tottenham Ct Rd", PredictionFields.DestinationText.extract(prediction));
		Assert.assertEquals("242", PredictionFields.LineName.extract(prediction));
		Assert.assertEquals(1428064256000L, PredictionFields.EstimatedTime.extract(prediction).getMillis());
	}
	
	public void test_parsePrediction_on_no_field_prediction() throws JSONException {
		final String predictionJsonArray = "[1]";
		final ImmutableSet<PredictionField> fields = ImmutableSet.of();
		predictionParser.parsePrediction(new JSONArray(predictionJsonArray), fields);
	}
	
	public void test_isPrediction_on_invalid_prediction_responsetype() throws JSONException {
		final String prediction = "[0,\"242\",\"Tottenham Ct Rd\",1428064256000]";
		final boolean result = predictionParser.isPrediction(new JSONArray(prediction), fields);
		Assert.assertTrue(!result);
	}
	
	public void test_not_isPrediction_when_fewer_fields() throws JSONException {
		final String prediction = "[1,\"242\",\"Tottenham Ct Rd\"]";
		final boolean result = predictionParser.isPrediction(new JSONArray(prediction), fields);
		Assert.assertFalse(result);
	}
	
	public void test_isPrediction_no_fields() throws JSONException {
		final String prediction = "[1]";
		final ImmutableSet<PredictionField> fields = ImmutableSet.of();
		final boolean result = predictionParser.isPrediction(new JSONArray(prediction), fields);
		Assert.assertTrue(result);
	}

}
