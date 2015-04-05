package net.mcarolan.whenzebus.test;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import junit.framework.Assert;

import net.mcarolan.whenzebus.api.predictionfield.PredictionField;
import net.mcarolan.whenzebus.api.predictionfield.PredictionFieldComparator;
import net.mcarolan.whenzebus.api.predictionfield.PredictionFields;

import com.google.common.collect.Lists;

public class PredictionFieldComparatorTest {
	
	@Test
	public void destination_text_before_destination_name() {
		final ArrayList<? extends PredictionField> fields = Lists.newArrayList(PredictionFields.DestinationName, PredictionFields.DestinationText);
		Collections.sort(fields, new PredictionFieldComparator());
		Assert.assertEquals(Lists.newArrayList(PredictionFields.DestinationText, PredictionFields.DestinationName), fields);
	}

}
