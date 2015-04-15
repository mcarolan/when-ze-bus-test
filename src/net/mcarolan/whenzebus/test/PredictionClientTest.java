package net.mcarolan.whenzebus.test;

import java.util.Set;

import junit.framework.Assert;

import com.google.common.collect.Sets;

import net.mcarolan.whenzebus.api.Response;
import net.mcarolan.whenzebus.api.Client;
import net.mcarolan.whenzebus.api.StopCode1;
import net.mcarolan.whenzebus.api.UnknownBusStop;
import net.mcarolan.whenzebus.api.field.Field;
import net.mcarolan.whenzebus.api.field.Fields;
import android.test.AndroidTestCase;

public class PredictionClientTest extends AndroidTestCase {
	
	public void test_brownlow_street_has_buses() {
		final Set<? extends Field> fields = Sets.newHashSet(Fields.DestinationText, Fields.LineName, Fields.EstimatedTime, Fields.ExpireTime);
		final Client predictionClient = new Client("http://countdown.api.tfl.gov.uk");
		final Set<Response> predictions = predictionClient.getResponses(new StopCode1("76458"), false, Client.DEFAULT_PREDICTION_FIELDS);
		Assert.assertTrue(predictions.size() > 0);
	}

	public void test_brownlow_street_stop_information() {
		final Set<? extends Field> fields = Sets.newHashSet(Fields.StopPointName);
		final Client predictionClient = new Client("http://countdown.api.tfl.gov.uk");
		final Set<Response> responses = predictionClient.getResponses(new StopCode1("76458"), true, fields);
		
		Assert.assertEquals(1, responses.size());
		final Response first = responses.iterator().next();
		
		Assert.assertEquals("Brownlow Street", Fields.StopPointName.extract(first));
	}
	
	public void test_unknown_stopcode1_throws_correct_exception() {
		final Set<? extends Field> fields = Sets.newHashSet(Fields.StopPointName);
		final Client predictionClient = new Client("http://countdown.api.tfl.gov.uk");
		
		boolean unknownBusStopThrown = false;
		try {
			predictionClient.getResponses(new StopCode1("2745982475982"), true, fields);
		}
		catch (UnknownBusStop e) {
			unknownBusStopThrown = true;
		}
		
		if (!unknownBusStopThrown) {
			throw new IllegalStateException("expected UnknownBusStop exception");
		}
	}

}
