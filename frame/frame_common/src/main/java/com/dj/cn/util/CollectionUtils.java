package com.dj.cn.util;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version V1.0
 * @Title: CollectionUtils
 * @Package: com.dj.cn.util
 * @author: Lenovo
 * @date: 2019/8/8 17:06
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class CollectionUtils {
    public CollectionUtils() {
    }

    public static <E> boolean isNullOrEmpty(Collection<E> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return collection != null && collection.size() > 0;
    }

    public static <E> E min(Collection<E> collection, Comparator<? super E> comparator) {
        Optional<E> optional = collection.stream().min(comparator);
        return optional.isPresent() ? optional.get() : null;
    }

    public static int minInt(Collection<Integer> collection) {
        Integer value = (Integer)min(collection, Comparator.comparingInt((i) -> {
            return i;
        }));
        return value == null ? 0 : value;
    }

    public static <E> E max(Collection<E> collection, Comparator<? super E> comparator) {
        Optional<E> optional = collection.stream().max(comparator);
        return optional.isPresent() ? optional.get() : null;
    }

    public static int maxInt(Collection<Integer> collection) {
        Integer value = (Integer)max(collection, Comparator.comparingInt((i) -> {
            return i;
        }));
        return value == null ? 0 : value;
    }

    public static <E> long count(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().filter(predicate).count();
    }

    public static <E> boolean exist(Collection<E> collection, Predicate<E> predicate) {
        return count(collection, predicate) > 0L;
    }

    public static <E> Optional<E> findOptional(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().filter(predicate).findAny();
    }

    public static <E> E find(Collection<E> collection, Predicate<E> predicate) {
        Optional<E> optional = findOptional(collection, predicate);
        return optional.isPresent() ? optional.get() : null;
    }

    public static <E> Optional<E> parallelFindOptional(Collection<E> collection, Predicate<E> predicate) {
        return ((Stream)collection.stream().parallel()).filter(predicate).findAny();
    }

    public static <E> E parallelFind(Collection<E> collection, Predicate<E> predicate) {
        Optional<E> optional = parallelFindOptional(collection, predicate);
        return optional.isPresent() ? optional.get() : null;
    }

    public static <E> Stream<E> query(Collection<E> collection, Predicate<E> predicate) {
        return collection.stream().filter(predicate);
    }

    public static <E> Stream<E> parallelQuery(Collection<E> collection, Predicate<E> predicate) {
        return ((Stream)collection.stream().parallel()).filter(predicate);
    }

    public static <E> List<E> list(Collection<E> collection, Predicate<E> predicate) {
        return (List)query(collection, predicate).collect(Collectors.toList());
    }

    public static <E> List<E> parallelList(Collection<E> collection, Predicate<E> predicate) {
        return (List)parallelQuery(collection, predicate).collect(Collectors.toList());
    }

    public static <E, R> Stream<R> map(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return collection.stream().map(mapper);
    }

    public static <E, R> List<R> mapToList(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return (List)map(collection, mapper).collect(Collectors.toList());
    }

    public static <E, R> Set<R> mapToSet(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return (Set)map(collection, mapper).collect(Collectors.toSet());
    }

    public static <E, R> Stream<R> map(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return query(collection, predicate).map(mapper);
    }

    public static <E, R> List<R> mapToList(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return (List)map(collection, predicate, mapper).collect(Collectors.toList());
    }

    public static <E, R> Set<R> mapToSet(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return (Set)map(collection, predicate, mapper).collect(Collectors.toSet());
    }

    public static <E, R> Stream<R> parallelMap(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return ((Stream)collection.stream().parallel()).map(mapper);
    }

    public static <E, R> List<R> parallelMapToList(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return (List)parallelMap(collection, mapper).collect(Collectors.toList());
    }

    public static <E, R> Stream<R> parallelMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return ((Stream)collection.stream().parallel()).filter(predicate).map(mapper);
    }

    public static <E, R> List<R> parallelMapToList(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return (List)parallelMap(collection, predicate, mapper).collect(Collectors.toList());
    }

    public static <E, R> Stream<? extends R> mapAndDistinct(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return map(collection, mapper).distinct();
    }

    public static <E, R> List<R> mapAndDistinctToList(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return (List)mapAndDistinct(collection, mapper).collect(Collectors.toList());
    }

    public static <E, R> Set<R> mapAndDistinctToSet(Collection<E> collection, Function<? super E, ? extends R> mapper) {
        return (Set)mapAndDistinct(collection, mapper).collect(Collectors.toSet());
    }

    public static <E, R> Stream<? extends R> mapAndDistinct(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return map(collection, predicate, mapper).distinct();
    }

    public static <E, R> List<R> mapAndDistinctToList(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return (List)mapAndDistinct(collection, predicate, mapper).collect(Collectors.toList());
    }

    public static <E, R> Set<R> mapAndDistinctToSet(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends R> mapper) {
        return (Set)mapAndDistinct(collection, predicate, mapper).collect(Collectors.toSet());
    }

    public static <E, R> List<R> mapManyToList(Collection<E> collection, Function<? super E, ? extends Stream<? extends R>> mapper) {
        List<R> list = new LinkedList();
        map(collection, mapper).forEach((i) -> {
            list.addAll((Collection)i.collect(Collectors.toList()));
        });
        return list;
    }

    public static <K, E> Map<K, List<E>> groupToMap(Collection<E> collection, Function<? super E, ? extends K> classifier) {
        return (Map)collection.stream().collect(Collectors.groupingBy(classifier));
    }

    public static <K, E> Map<K, List<E>> parallelGroupToMap(Collection<E> collection, Function<? super E, ? extends K> classifier) {
        return (Map)((Stream)collection.stream().parallel()).collect(Collectors.groupingBy(classifier));
    }

    public static <K, E> Map<K, List<E>> groupToMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends K> classifier) {
        return (Map)query(collection, predicate).collect(Collectors.groupingBy(classifier));
    }

    public static <K, E> Map<K, List<E>> parallelGroupToMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends K> classifier) {
        return (Map)parallelQuery(collection, predicate).collect(Collectors.groupingBy(classifier));
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper) {
        return (Map)collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E, K, V, M extends Map<K, V>> Map<K, V> toMap(Collection<E> collection, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapSupplier) {
        return (Map)collection.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier));
    }

    public static <E, K, V> Map<K, V> parallelToMap(Collection<E> collection, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper) {
        return (Map)((Stream)collection.stream().parallel()).collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E, K, V> Map<K, V> parallelToMap(Collection<E> collection, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
        return (Map)((Stream)collection.stream().parallel()).collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper) {
        return (Map)query(collection, predicate).collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
        return (Map)query(collection, predicate).collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    public static <E, K, V> Map<K, V> parallelToMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper) {
        return (Map)parallelQuery(collection, predicate).collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E, K, V> Map<K, V> parallelToMap(Collection<E> collection, Predicate<E> predicate, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
        return (Map)parallelQuery(collection, predicate).collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E> List<E> paging(Collection<E> collection, int pageIndex, int pageSize) {
        if (!isNullOrEmpty(collection) && pageIndex >= 1) {
            int skip = (pageIndex - 1) * pageSize;
            return (List)(skip >= 0 && skip < collection.size() ? (List)collection.stream().skip((long)skip).limit((long)pageSize).collect(Collectors.toList()) : new ArrayList());
        } else {
            return new ArrayList();
        }
    }

    public static <E> List<E> top(Collection<E> collection, int count) {
        return (List)(!isNullOrEmpty(collection) && count >= 1 ? (List)collection.stream().limit((long)count).collect(Collectors.toList()) : new ArrayList());
    }
}
