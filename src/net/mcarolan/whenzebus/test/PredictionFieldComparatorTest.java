package net.mcarolan.whenzebus.test;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import junit.framework.Assert;

import net.mcarolan.whenzebus.api.field.Field;
import net.mcarolan.whenzebus.api.field.FieldComparator;
import net.mcarolan.whenzebus.api.field.Fields;

import com.google.common.collect.Lists;

public class PredictionFieldComparatorTest {
	
	@Test
	public void destination_text_before_destination_name() {
		final ArrayList<? extends Field> fields = Lists.newArrayList(Fields.DestinationName, Fields.DestinationText);
		Collections.sort(fields, new FieldComparator());
		Assert.assertEquals(Lists.newArrayList(Fields.DestinationText, Fields.DestinationName), fields);
	}

}
