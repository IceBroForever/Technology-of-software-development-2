package com.zulus.task2.serializer;

import com.zulus.task2.educationalInstitution.EducationalInstitution;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer {
    private String filename;

    public Serializer(String filename){
        this.filename = filename;
    }

    public void serializeInstitutions(List<EducationalInstitution> institutions){
        try {
            BufferedOutputStream bos = new BufferedOutputStream (new FileOutputStream(filename));
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(institutions);

            oos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<EducationalInstitution> deserializeInstitutions(int count){
        List<EducationalInstitution> institutions = new ArrayList<>(count);

        try {
            BufferedInputStream bos = new BufferedInputStream(new FileInputStream(filename));
            ObjectInputStream ois = new ObjectInputStream(bos);

            institutions = (List<EducationalInstitution>) ois.readObject();

            ois.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return institutions;
    }
}
