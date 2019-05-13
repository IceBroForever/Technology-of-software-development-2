package com.gmail.pasha1ruh.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Notebook {
    private Notes uncompleted = new Notes();
    private Notes completed = new Notes();

    public List<String> getUncompletedTasks(Date date) {
        return uncompleted.getNotesByDate(date);
    }

    public List<String> getCompletedTasks(Date date) {
        return completed.getNotesByDate(date);
    }

    public void addTask(Date date, String task) {
        uncompleted.addNote(date, task);
    }

    public String removeTask(Date date, int index) {
        return uncompleted.removeNote(date, index);
    }

    public void markTaskAsCompleted(Date date, int index) {
        completed.addNote(date, uncompleted.removeNote(date, index));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notebook notebook = (Notebook) o;

        if (!uncompleted.equals(notebook.uncompleted)) return false;
        return completed.equals(notebook.completed);
    }

    @Override
    public int hashCode() {
        int result = uncompleted.hashCode();
        result = 31 * result + completed.hashCode();
        return result;
    }

    private class Notes {
        HashMap<Date, LinkedList<String>> map = new HashMap<>();

        List<String> getNotesByDate(Date date) {
            return map.get(date);
        }

        void addNote(Date date, String note) {
            LinkedList<String> list;
            if(map.containsKey(date)) {
                list = map.get(date);
            } else {
                list = new LinkedList<>();
                map.put(date, list);
            }
            list.add(note);
        }

        String removeNote(Date date, int index) {
            LinkedList<String> list = map.get(date);
            if(list == null) return null;
            String removed = list.remove(index);
            if(list.isEmpty()) {
                map.remove(date);
            }
            return removed;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Notes notes = (Notes) o;

            return map.equals(notes.map);

        }

        @Override
        public int hashCode() {
            return map.hashCode();
        }

        @Override
        public String toString() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            StringBuilder builder = new StringBuilder();
            for(Date date: map.keySet()) {
                builder.append(dateFormat.format(date)).append('\n');
                for(String note: map.get(date)) {
                    builder.append('\t').append("- ").append(note).append('\n');
                }
            }
            return builder.toString();
        }
    }
}
