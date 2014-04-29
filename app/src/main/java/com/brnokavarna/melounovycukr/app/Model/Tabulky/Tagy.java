package com.brnokavarna.melounovycukr.app.Model.Tabulky;

/**
 * Created by Seky on 29. 4. 2014.
 */
public class Tagy {
    private int id;
    private String tag;

    /**
     * Constructors
     */
    public Tagy(){}

    public Tagy( String Tag)
    {
        this.tag = Tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
