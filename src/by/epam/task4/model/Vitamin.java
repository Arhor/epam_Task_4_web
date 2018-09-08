/*
 * class: Vitamin
 */

package by.epam.task4.model;

/**
 * Concrete class that represents vitamin-type medicine
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class Vitamin extends Medicine {

    private String solution;

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) { return false; }
        Vitamin vitamin = (Vitamin) obj;
        if (solution == null) {
            if (vitamin.solution != null) {
                return false;
            }
        } else if (!solution.equals(vitamin.solution)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += super.hashCode();
        hashCode += solution == null ? 0 : solution.hashCode();
        return hashCode;
    }
    
    @Override
    public String toString() {
        return super.toString()
                + ", solution: " + solution + "\n";
    }
}
