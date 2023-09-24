package ua.bala;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class LongMapImplPerformanceTest {

    private static final long SMALL_MAP_SIZE = 1000;
    private static final long MEDIUM_MAP_SIZE = 10000;
    private static final long LARGE_MAP_SIZE = 100000;

    @Test
    public void testWriteSmallMap() {
        long mapSize = SMALL_MAP_SIZE;
        long expTime = measureWritingTime(mapSize);
        long expTime2 = measureWritingTimeMap(mapSize);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testWriteMediumMap() {
        long mapSize = MEDIUM_MAP_SIZE;
        long expTime = measureWritingTime(mapSize);
        long expTime2 = measureWritingTimeMap(mapSize);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testWriteLargeMap() {
        long mapSize = LARGE_MAP_SIZE;
        long expTime = measureWritingTime(mapSize);
        long expTime2 = measureWritingTimeMap(mapSize);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testReadSmallMap() {
        long mapSize = SMALL_MAP_SIZE;
        LongMap<Integer> map = createRandomLongMap(mapSize);
        HashMap<Long, Integer> map2 = createRandomLongHashMap(mapSize);
        long expTime = measureReadingTime(map);
        long expTime2 = measureReadingTimeMap(map2);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testReadMediumMap() {
        long mapSize = MEDIUM_MAP_SIZE;
        LongMap<Integer> map = createRandomLongMap(mapSize);
        HashMap<Long, Integer> map2 = createRandomLongHashMap(mapSize);
        long expTime = measureReadingTime(map);
        long expTime2 = measureReadingTimeMap(map2);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testReadLargeMap() {
        long mapSize = LARGE_MAP_SIZE;
        LongMap<Integer> map = createRandomLongMap(mapSize);
        HashMap<Long, Integer> map2 = createRandomLongHashMap(mapSize);
        long expTime = measureReadingTime(map);
        long expTime2 = measureReadingTimeMap(map2);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testDeleteSmallMap() {
        long mapSize = SMALL_MAP_SIZE;
        LongMap<Integer> map = createRandomLongMap(mapSize);
        HashMap<Long, Integer> map2 = createRandomLongHashMap(mapSize);
        long expTime = measureDeletingTimeMap(map);
        long expTime2 = measureDeletingTimeMap(map2);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testDeleteMediumMap() {
        long mapSize = MEDIUM_MAP_SIZE;
        LongMap<Integer> map = createRandomLongMap(mapSize);
        HashMap<Long, Integer> map2 = createRandomLongHashMap(mapSize);
        long expTime = measureDeletingTimeMap(map);
        long expTime2 = measureDeletingTimeMap(map2);

        populateResult(expTime, expTime2);
    }

    @Test
    public void testDeleteLargeMap() {
        long mapSize = LARGE_MAP_SIZE;
        LongMap<Integer> map = createRandomLongMap(mapSize);
        HashMap<Long, Integer> map2 = createRandomLongHashMap(mapSize);
        long expTime = measureDeletingTimeMap(map);
        long expTime2 = measureDeletingTimeMap(map2);

        populateResult(expTime, expTime2);
    }

    private static void populateResult(long expTime, long expTime2) {
        String resultMessage = expTime > expTime2 ?
                "HashMap is faster than LongMapImpl in " + (expTime - expTime2) + "ms." :
                "LongMapImpl is faster than HashMap in " + (expTime2 - expTime) + "ms.";
        System.out.println(resultMessage);
    }

    private LongMap<Integer> createRandomLongMap(long size) {
        LongMapImpl<Integer> initMap = new LongMapImpl<>();
        for (Integer i = 0; i < size; i++) {
            initMap.put(i, i);
        }
        System.out.println("Created LongMap with " + initMap.size() + " elements");
        return initMap;
    }

    private HashMap<Long, Integer> createRandomLongHashMap(long size) {
        HashMap<Long, Integer> initMap = new HashMap<>();
        for (Integer i = 0; i < size; i++) {
            initMap.put(i.longValue(), i);
        }
        System.out.println("Created HashMap with " + initMap.size() + " elements");
        return initMap;
    }

    public long measureWritingTime(long size) {
        LongMapImpl<Integer> initMap = new LongMapImpl<>();
        long startTime = System.nanoTime();

        for (Integer i = 0; i < size; i++) {
            initMap.put(i, i);
        }

        long endTime = System.nanoTime();

        long executionTime = (endTime - startTime) / size;
        System.out.println("LongMapImpl - Execution Time: " + executionTime + " microseconds");
        return executionTime;
    }

    public long measureWritingTimeMap(long size) {
        HashMap<Long, Integer> initMap = new HashMap<>();
        long startTime = System.nanoTime();

        for (Integer i = 0; i < size; i++) {
            initMap.put(i.longValue(), i);
        }

        long endTime = System.nanoTime();

        long executionTime = (endTime - startTime) / size;
        System.out.println("HashMap - Execution Time: " + executionTime + " microseconds");
        return executionTime;
    }

    public long measureReadingTime(LongMap<Integer> map) {
        long size = map.size();
        long[] results = new long[(int) size];
        long startTime = System.nanoTime();

        for (long i = 0; i < size; i++) {
            results[(int) i] = map.get(i);
        }

        long endTime = System.nanoTime();
        System.out.println(Arrays.binarySearch(results, 1L));

        long executionTime = (endTime - startTime) / size;
        System.out.println("LongMap - Execution Time: " + executionTime + " microseconds");
        return executionTime;
    }

    public long measureReadingTimeMap(HashMap<Long, Integer> map) {
        long size = map.size();
        long[] results = new long[(int) size];
        long startTime = System.nanoTime();

        for (long i = 0; i < size; i++) {
            results[(int) i] = map.get(i);
        }

        long endTime = System.nanoTime();
        System.out.println(Arrays.binarySearch(results, 1L));

        long executionTime = (endTime - startTime) / size;
        System.out.println("HashMap - Execution Time: " + executionTime + " microseconds");
        return executionTime;
    }

    public long measureDeletingTimeMap(LongMap<Integer> map) {
        long size = map.size();
        long[] results = new long[(int) size];
        System.out.println("Starting remove test for " + size + " elements");
        long startTime = System.nanoTime();

        for (long i = 0; i < size; i++) {
            results[(int) i] = map.remove(i);
        }

        long endTime = System.nanoTime();
        System.out.println(Arrays.binarySearch(results, 1L));

        long executionTime = (endTime - startTime) / size;
        System.out.println("Execution Time: " + executionTime + " microseconds");
        return executionTime;
    }

    public long measureDeletingTimeMap(HashMap<Long, Integer> map) {
        long size = map.size();
        long[] results = new long[(int) size];
        System.out.println("Starting remove test for " + size + " elements");
        long startTime = System.nanoTime();

        for (long i = 0; i < size; i++) {
            results[(int) i] = map.remove(i);
        }

        long endTime = System.nanoTime();
        System.out.println(Arrays.binarySearch(results, 1L));

        long executionTime = (endTime - startTime) / size;
        System.out.println("Execution Time: " + executionTime + " microseconds");
        return executionTime;
    }
}