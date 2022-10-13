package com.acebanenco.blogic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BooleanPermutationsBuilder {
    public List<Map<String, Integer>> buildPermutations(Set<String> identifierSet) {
        List<String> identifierList = new ArrayList<>(identifierSet);
        int max = 1 << identifierSet.size();
        return IntStream.range(0, max)
                .boxed()
                .map(value -> IntStream.range(0, identifierList.size())
                        .boxed()
                        .collect(Collectors.toMap(
                                identifierList::get,
                                i -> (value >> (identifierList.size() - 1 - i)) & 1,
                                (x,y) -> x,
                                LinkedHashMap::new
                        )))
                .collect(Collectors.toList());
    }
}
