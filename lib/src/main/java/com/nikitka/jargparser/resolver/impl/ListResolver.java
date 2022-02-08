package com.nikitka.jargparser.resolver.impl;

import java.util.List;
import com.google.common.base.Splitter;
import com.nikitka.jargparser.resolver.Resolver;

public class ListResolver implements Resolver<List<?>> {

    @Override
    public List<?> resolve(String input) {
        return Splitter.on(",.?").trimResults().omitEmptyStrings().splitToList(input.replace("[", "").replace("]", ""));
    }   
}
