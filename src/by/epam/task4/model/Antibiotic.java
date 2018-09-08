/*
 * class: Antibiotic
 */

package by.epam.task4.model;

/**
 * Concrete class that represents antibiotic-type medicine
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class Antibiotic extends Medicine {

    private boolean recipe;

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) { return false; }
        Antibiotic antibiotic = (Antibiotic) obj;
        if (recipe != antibiotic.recipe) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += super.hashCode();
        hashCode += recipe ? 1 : 0;
        return hashCode;
    }
    
    @Override
    public String toString() {
        return super.toString()
                + ", recipe: " + recipe + "\n"; 
    }
}