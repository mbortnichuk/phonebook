package com.mbortnichuk.phonebook;

import java.util.Map;

/**
 * Created by Mariana on 05-Feb-17.
 */
public interface BackEndOld {

    void put(String dataFile, Map<String, String> records, boolean append);

    Map<String, String> getAll(String dataFile, boolean getByName);

    String get(String dataFile, String key, boolean getByName);



}
