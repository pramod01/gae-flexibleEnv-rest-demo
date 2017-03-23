package com.test.demo.constants;

public interface ApplicationConstants {

  //Datastore kind and column details

  String datastore_kind = "visit";
  String datastore_property1 = "visitor_name";
  String datastore_property2 = "timestamp";
  String datastore_property3 = "remoteclientIpAddress";
  String entity_creation_success_message = "Entity creation success: '%s'";;
  String entity_creation_failure_message = "Entity creation failure: '%s'";

  //Servlet POST method

  String form_parameter = "name";
  String json_key = "name";
  String error_message = "form parameter - name is empty";
}
