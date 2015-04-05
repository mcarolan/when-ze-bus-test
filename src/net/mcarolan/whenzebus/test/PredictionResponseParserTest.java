package net.mcarolan.whenzebus.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import net.mcarolan.whenzebus.api.Prediction;
import net.mcarolan.whenzebus.api.PredictionParser;
import net.mcarolan.whenzebus.api.PredictionResponseParser;
import net.mcarolan.whenzebus.api.predictionfield.PredictionField;
import net.mcarolan.whenzebus.api.predictionfield.PredictionFields;
import android.test.AndroidTestCase;

public class PredictionResponseParserTest extends AndroidTestCase {
	
	final String response = "[4,\"1.0\",1428138085790]\n" +
			"[1,\"8\",\"Tottenham Ct Rd\",1428138596000,1428138596000]\n" +
			"[1,\"242\",\"Tottenham Ct Rd\",1428138627000,1428138627000]\n" +
			"[1,\"25\",\"Oxford Circus\",1428138587000,1428138587000]\n" +
			"[1,\"8\",\"Tottenham Ct Rd\",1428139203000,1428139203000]\n" +
			"[1,\"242\",\"Tottenham Ct Rd\",1428139078000,1428139078000]\n" +
			"[1,\"8\",\"Tottenham Ct Rd\",1428139237000,1428139237000]\n" +
			"[1,\"242\",\"Tottenham Ct Rd\",1428139428000,1428139428000]\n" +
			"[1,\"25\",\"Oxford Circus\",1428139533000,1428139533000]\n" +
			"[1,\"25\",\"Oxford Circus\",1428139452000,1428139452000]\n" +
			"[1,\"8\",\"Tottenham Ct Rd\",1428139682000,1428139682000]";

	final Set<PredictionField> fields = Sets.newHashSet(PredictionFields.DestinationText, PredictionFields.LineName, PredictionFields.EstimatedTime, PredictionFields.ExpireTime);
	
	public void test_parse_valid_response() {
		final PredictionResponseParser predictionParser = new PredictionResponseParser(response, new PredictionParser());
		predictionParser.extractPredictions(fields);
	}
	
	public void test_parse_valid_response_size() {
		final PredictionResponseParser predictionParser = new PredictionResponseParser(response, new PredictionParser());
		final Set<Prediction> predictions = predictionParser.extractPredictions(fields);
		Assert.assertEquals(10, predictions.size());
	}

	public void test_parse_valid_response_contains_element() {
		final PredictionResponseParser predictionParser = new PredictionResponseParser(response, new PredictionParser());
		final Set<Prediction> predictions = predictionParser.extractPredictions(fields);
		final ImmutableMap.Builder<PredictionField, String> builder = ImmutableMap.builder();
		
		builder.put(PredictionFields.LineName, "242");
		builder.put(PredictionFields.DestinationText, "Tottenham Ct Rd");
		builder.put(PredictionFields.EstimatedTime, "1428138627000");
		builder.put(PredictionFields.ExpireTime, "1428138627000");
		
		final Map<PredictionField, String> fieldNameToValues = builder.build();
		Assert.assertTrue(predictions.contains(new Prediction(fieldNameToValues)));
	}
}
