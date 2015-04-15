package net.mcarolan.whenzebus.test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import net.mcarolan.whenzebus.api.Response;
import net.mcarolan.whenzebus.api.ResponseParser;
import net.mcarolan.whenzebus.api.field.Field;
import net.mcarolan.whenzebus.api.field.Fields;
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

	final Set<Field> fields = Sets.newHashSet(Fields.DestinationText, Fields.LineName, Fields.EstimatedTime, Fields.ExpireTime);
	
	public void test_parse_valid_response() {
		final ResponseParser predictionParser = new ResponseParser(response);
		predictionParser.extractResponses(fields);
	}
	
	public void test_parse_valid_response_size() {
		final ResponseParser predictionParser = new ResponseParser(response);
		final Set<Response> predictions = predictionParser.extractResponses(fields);
		Assert.assertEquals(10, predictions.size());
	}

	public void test_parse_valid_response_contains_element() {
		final ResponseParser predictionParser = new ResponseParser(response);
		final Set<Response> predictions = predictionParser.extractResponses(fields);
		final ImmutableMap.Builder<Field, String> builder = ImmutableMap.builder();
		
		builder.put(Fields.LineName, "242");
		builder.put(Fields.DestinationText, "Tottenham Ct Rd");
		builder.put(Fields.EstimatedTime, "1428138627000");
		builder.put(Fields.ExpireTime, "1428138627000");
		
		final Map<Field, String> fieldNameToValues = builder.build();
		Assert.assertTrue(predictions.contains(new Response(fieldNameToValues)));
	}
}
