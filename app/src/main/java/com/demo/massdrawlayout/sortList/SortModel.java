package com.demo.massdrawlayout.sortList;

import java.io.Serializable;

/**
 * @author J
 */
public class SortModel implements Serializable {

    private String name;
    private Boolean selected;
    private String sortLetters;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public SortModel() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
