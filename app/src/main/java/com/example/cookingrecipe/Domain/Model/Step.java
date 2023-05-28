package com.example.cookingrecipe.Domain.Model;

public class Step {
    int step_order;
    String description;
    String image;


    public int getStep_order() {
        return step_order;
    }

    public void setStep_order(int step_order) {
        this.step_order = step_order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Step(int step_order, String description, String image) {

        this.step_order = step_order;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Step{" +
                "step_order=" + step_order +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
