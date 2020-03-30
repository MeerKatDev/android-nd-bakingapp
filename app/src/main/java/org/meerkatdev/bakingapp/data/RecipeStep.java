package org.meerkatdev.bakingapp.data;

import java.net.URL;

public class RecipeStep {

    public int id;
    public String shortDescription;
    public String description;
    public URL videoURL;
    public URL thumbnailURL;

    public RecipeStep(int id, String shortDescription, String description, URL videoURL, URL thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }
}
