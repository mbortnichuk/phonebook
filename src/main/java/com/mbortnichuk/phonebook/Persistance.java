package com.mbortnichuk.phonebook;

import java.util.List;

/**
 * Created by Mariana on 05-Apr-17.
 */
public interface Persistance {

    public List<Record> read(String key, String value);

    public List<Record> readALL();

    public void create(Record record);

    public int update(Record record, String key, String value);

    public int delete(String key, String value);

}
