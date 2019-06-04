package com.zulus.task2.concreteEducationalInstitutions;


import com.zulus.task2.educationalInstitution.EducationalInstitution;

import java.io.Serializable;
import java.util.Objects;

public class College implements EducationalInstitution, Serializable {
    private String name;
    private int studentsCount;
    private int id;
    private static long serialVersionUID = 0;

    public College(String name, int studentsCount){
        this.name = name;
        this.studentsCount = studentsCount;
    }

    @Override
    public String getName() {
        return "College: " + name;
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
        return "INFO: College name: " + name + "\r\nNumbers of students in college: " + studentsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof College)) return false;
        College college = (College) o;
        return studentsCount == college.studentsCount &&
                name.equals(college.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentsCount);
    }
}
