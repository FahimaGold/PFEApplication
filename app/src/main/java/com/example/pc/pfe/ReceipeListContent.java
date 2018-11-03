package com.example.pc.pfe;

/**
 * Created by pc on 25/05/2018.
 */

public class ReceipeListContent {
    private String receipeName;

    public ReceipeListContent(){

    }

    public ReceipeListContent(String receipeName) {
        this.receipeName = receipeName;
    }

    public String getReceipeName() {
        return receipeName;
    }

    public void setReceipeName(String receipeName) {
        this.receipeName = receipeName;
    }
}
