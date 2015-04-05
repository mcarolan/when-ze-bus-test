package net.mcarolan.whenzebus.test;

import java.util.Set;

import junit.framework.Assert;

import com.google.common.collect.Sets;

import net.mcarolan.whenzebus.api.Prediction;
import net.mcarolan.whenzebus.api.PredictionClient;
import net.mcarolan.whenzebus.api.StopCode1;
import net.mcarolan.whenzebus.api.predictionfield.PredictionField;
import net.mcarolan.whenzebus.api.predictionfield.PredictionFields;
import android.test.AndroidTestCase;

public class PredictionClientTest extends AndroidTestCase {
	
	public void test_brownlow_street_has_buses() {
		final Set<? extends PredictionField> fields = Sets.newHashSet(PredictionFields.DestinationText, PredictionFields.LineName, PredictionFields.EstimatedTime, PredictionFields.ExpireTime);
		final PredictionClient predictionClient = new PredictionClient("http://countdown.api.tfl.gov.uk", new StopCode1("76458"));
		final Set<Prediction> predictions = predictionClient.getPredictions();
		Assert.assertTrue(predictions.size() > 0);
	}

}
