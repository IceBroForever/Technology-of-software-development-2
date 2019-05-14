package com.zulus.task2.concreteEducationalInstitutions;

import com.zulus.task2.educationalInstitution.EducationalInstitution;

import java.io.Serializable;
import java.util.Objects;

public class University implements EducationalInstitution, Serializable {
    private String name;
    private int studentsCount;

    public University(String name, int studentsCount){
        this.name = name;
        this.studentsCount = studentsCount;
    }

    @Override
    public String getName() {
        return "University: " + name;
    }

    @Override
    public int getStudentsCount() {
        return studentsCount;
    }

    @Override
    public void printInfo() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
        return "INFO: University name: " + name + "\r\nNumbers of students in university: " + studentsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University)) return false;
        University that = (University) o;
        return studentsCount == that.studentsCount &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentsCount);
    }
}
