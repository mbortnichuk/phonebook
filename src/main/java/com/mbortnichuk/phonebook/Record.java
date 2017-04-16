package com.mbortnichuk.phonebook;

/**
 * Created by Mariana on 05-Apr-17.
 */
public class Record {

    private int id;
    private String phone;
    private String name;

    public Record(int id, String phone, String name){
        this.id = id;
        this.phone = phone;
        this.name = name;
    }

    public Record(String phone, String name){
        this(-1, phone, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (id != record.id) return false;
        if (!phone.equals(record.phone)) return false;
        return name.equals(record.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + phone.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
