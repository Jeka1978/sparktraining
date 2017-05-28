package taxi;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

/**
 * Created by Evegeny on 28/05/2017.
 */
public class Main {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("Taxi");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> rdd = sc.textFile("data/taxi_order.txt");
        rdd.persist(StorageLevel.MEMORY_AND_DISK());
        System.out.println(rdd.count());
//        rdd.collect().forEach(System.out::println);
        JavaRDD<Trip> trips = rdd.map(line -> {
                    String[] words = line.split(" ");
                    return Trip.builder().id(words[0]).city(words[1].toLowerCase()).distance(Integer.parseInt(words[2])).build();
                }
        );

        trips.persist(StorageLevel.MEMORY_AND_DISK());
        JavaRDD<Trip> bostonTrips = trips.filter(trip -> trip.getCity().equals("boston"));
        bostonTrips.persist(StorageLevel.MEMORY_AND_DISK());
        long longBostonTripsCount = bostonTrips.filter(trip -> trip.getDistance() > 10).count();
        System.out.println("longBostonTripsCount = " + longBostonTripsCount);
        Double totalKmToBoston = bostonTrips.mapToDouble(Trip::getDistance).sum();
        System.out.println("totalKmToBoston = " + totalKmToBoston);
        JavaPairRDD<String, String> driversInfo = sc.textFile("data/drivers.txt")
                .mapToPair(line -> {
                            String[] words = line.split(",");
                            return new Tuple2<>(words[0], words[1]);
                        }
                );



        trips.mapToPair(trip -> new Tuple2<>(trip.getId(), trip.getDistance()))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .mapToPair(Tuple2::swap)
                .join(driversInfo).map(tuple->tuple._2()._2()).takeOrdered(3).forEach(System.out::println);

        // todo fix it, because join will shuffle data again


    }
}














