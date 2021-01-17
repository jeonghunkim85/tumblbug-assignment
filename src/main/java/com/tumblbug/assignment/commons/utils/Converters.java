package com.tumblbug.assignment.commons.utils;

import org.modelmapper.Converter;

import java.util.UUID;

public class Converters {

    public static final Converter<Object, String> TO_STRING_CONVERTER = ctx -> ctx.getSource().toString();
}
