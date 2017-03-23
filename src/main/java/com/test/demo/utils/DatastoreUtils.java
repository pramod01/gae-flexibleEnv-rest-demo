package com.test.demo.utils;


import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.DateTime;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.test.demo.constants.ApplicationConstants;
import com.test.demo.controller.RestController;

public class DatastoreUtils {

  static final Logger LOG = Logger.getLogger(RestController.class.getName());
  static final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

  /*
   * Method to retrieve latest 10 visits from datastore ordered by timestamp
   */

  public static JSONArray retrieveEntriesFromDatastore() {
    final Query<Entity> query =
        Query.newEntityQueryBuilder().setKind(ApplicationConstants.datastore_kind)
        .setOrderBy(StructuredQuery.OrderBy.desc(ApplicationConstants.datastore_property2))
        .setLimit(10).build();
    final QueryResults<Entity> results = DatastoreUtils.datastore.run(query);
    final JSONArray jsonArray = new JSONArray();
    while (results.hasNext()) {
      final Entity entity = results.next();
      final JSONObject json = new JSONObject();
      json.append("0",entity.getString(ApplicationConstants.datastore_property3));
      json.append("1",entity.getDateTime(ApplicationConstants.datastore_property2));
      json.append("2",entity.getString(ApplicationConstants.datastore_property1));
      jsonArray.put(json);
    }
    return jsonArray;
  }

  /*
   * Method to create datastore entry under kind(table) visit properties(columns) userIp, name of
   * user who hits url and timestamp are recorded as entity (row)
   */

  public static void saveUserDetailsToDatastore(final String userIp, final String name) {
    final KeyFactory keyFactory =
        DatastoreUtils.datastore.newKeyFactory().setKind(ApplicationConstants.datastore_kind);
    final IncompleteKey key = keyFactory.setKind(ApplicationConstants.datastore_kind).newKey();

    // Record a visit to the datastore, storing the IP and timestamp.
    final FullEntity<IncompleteKey> curVisit =
        FullEntity.newBuilder(key).set(ApplicationConstants.datastore_property1, name)
        .set(ApplicationConstants.datastore_property2, DateTime.now())
        .set(ApplicationConstants.datastore_property3, userIp).build();

    final Entity entity = DatastoreUtils.datastore.add(curVisit);
    DatastoreUtils.LOG.severe(String.format(ApplicationConstants.entity_creation_success_message,
        entity.getKey()));
  }
}
