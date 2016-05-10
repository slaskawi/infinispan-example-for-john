package org.infinispan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.infinispan.manager.DefaultCacheManager;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Sample application code.  For more examples visit the Quick Starts section on http://infinispan.org/documentation/
 */
public class Application {

   private static final String INFINISPAN_CONFIGURATION = "infinispan-local.xml";

   private static final Logger logger = LogManager.getLogger();

   public static void main(String[] args) throws Exception {
      DefaultCacheManager defaultCacheManager = new DefaultCacheManager(Application.class.getClassLoader().getResourceAsStream(INFINISPAN_CONFIGURATION));
      AdvancedCache<String, String> cache = defaultCacheManager.<String, String>getCache("another").getAdvancedCache();

      logger.error("==== Starting the test ====");
      addRandom10Entries(cache);
      logger.error("==== After 10 entries ====");
      addRandom10Entries(cache);
      logger.error("==== After 20 entries ====");
      addRandom10Entries(cache);
      logger.error("==== After 30 entries ====");
      logger.error("==== Ending the test ====");
   }

   public static void addRandom10Entries(AdvancedCache<String, String> cache) {
      IntStream.range(1, 10)
              .forEach(i -> cache.put(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
   }

}

