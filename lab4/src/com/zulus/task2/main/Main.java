package com.zulus.task2.main;

import com.zulus.task2.concreteEducationalInstitutions.College;
import com.zulus.task2.concreteEducationalInstitutions.University;
import com.zulus.task2.educationalInstitution.EducationalInstitution;
import com.zulus.task2.serializer.Serializer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<EducationalInstitution> institutions = new ArrayList<>() {{
            add(new College("DNTU", 400));
            add(new University("KNTEU", 23000));
            add(new University("KPI", 34000));
        }};
        Serializer serializer = new Serializer("institutions.dat");

        System.out.println("Serialize");
        serializer.serializeInstitutions(institutions);

        for (var institution : institutions) {
            institution.printInfo();
        }

        List<EducationalInstitution> deserializedInstitutions = serializer.deserializeInstitutions(institutions.size());
        System.out.println("\nDeserialize:");

        for (var institution : deserializedInstitutions) {
            institution.printInfo();
        }
    }
}
