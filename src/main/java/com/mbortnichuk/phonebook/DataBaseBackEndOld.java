package com.mbortnichuk.phonebook;

import java.util.Map;

/**
 * Created by Mariana on 05-Feb-17.
 */
public class DataBaseBackEndOld implements BackEndOld {

    @Override
    public void put(String dataFile, Map<String, String> records, boolean append) {

    }

    @Override
    public Map<String, String> getAll(String dataFile, boolean getByName) {
       return null;
    }

    @Override
    public String get(String dataFile, String key, boolean getByName) {
        return null;
    }
}
