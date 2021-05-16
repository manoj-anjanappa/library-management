package com.library.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvReaderUtil {
	
	private static final CsvMapper mapper = new CsvMapper();

	public static <T> List<T> read(Class<T> type, InputStream stream) throws IOException{
		CsvSchema schema = mapper.schemaFor(type).withHeader().withColumnReordering(true);
		ObjectReader reader = mapper.readerFor(type).with(schema);
		return reader.<T>readValues(stream).readAll();
	}
}
