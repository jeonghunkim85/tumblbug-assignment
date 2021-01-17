package com.tumblbug.assignment.commons.utils;

import org.modelmapper.Converter;

public class Converters {

    public static final Converter<Object, String> TO_STRING_MAPPER = ctx -> ctx.getSource().toString();
}
