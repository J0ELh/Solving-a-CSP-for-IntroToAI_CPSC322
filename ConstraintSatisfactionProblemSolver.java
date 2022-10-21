package model;

import java.util.ArrayList;
import java.util.List;

//A B C D E F G H
//0 1 2 3 4 5 6 7

/**
 * @author Joel Hempel
 * @since 10/21/2022
 * Solves a constraint satisfaction problem with 8 variables and 17 constraints using DFS with pruning
 */
public class ConstraintSatisfactionProblemSolver {
    char[] ordering = {'F', 'H', 'D', 'C', 'G', 'E', 'A', 'B'}; //CHANGE THIS TO HAVE A DIFFERENT ORDER OF CONSTRAINTS
    ArrayList<Constraint> currentStateList;
    ArrayList<ArrayList<Integer>> solutionList;
    int numOfSolutionsFound = 0;
    int numberOfFailedChecks = 0;


    public ConstraintSatisfactionProblemSolver(){
        currentStateList = new ArrayList<>();
        solutionList = new ArrayList<ArrayList<Integer>>();
        findSolutionDFS(0, currentStateList);
        System.out.println("The number of failed branches is " + numberOfFailedChecks + " and the solutions (if any) are:\n");
        for (int i = 0; i < solutionList.size(); i++){
            printState(solutionList.get(i));
        }

    }



    /**
     * recursively prune paths and expand to further states if they increase the number of conditions met
     * @param stateListIndex index of the list of parameters
     * @return Boolean whether the next path makes the solution better
     */
    private Boolean findSolutionDFS(int stateListIndex, ArrayList<Constraint> previousList){
        ArrayList<Constraint> tmpState = new ArrayList<>(previousList);


        if (previousList.size() == 8 && allConstraintsSatisfied(tmpState)){
            ArrayList<Integer> solList = new ArrayList<>();
            for (int i = 0; i < tmpState.size(); i++) {
                solList.add(tmpState.get(i).getValue());
            }
            solutionList.add(solList);
            this.numOfSolutionsFound++;
            return true;
        }

        Constraint currentConstraint = new Constraint(ordering[stateListIndex]);
        currentConstraint.setValue(1);
        tmpState.add(currentConstraint);

        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }

        //test value of 2
        currentConstraint.setValue(2);
        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }


        //test value of 3
        currentConstraint.setValue(3);
        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }

        //test value of 4
        currentConstraint.setValue(4);
        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }




        tmpState.remove(tmpState.size()-1);// remove the last element once it's been checked
        return false;

    }


    /**
     * Check constraints. Only check the constraints including variables that have been assigned already.
     * @return Boolean whether all constraints have been satisfied
     */
    public Boolean allConstraintsSatisfied(List<Constraint> list){
        //check which constraints are already contained in the list
        int containsA = -1;
        int containsB = -1;
        int containsC = -1;
        int containsD = -1;
        int containsE = -1;
        int containsF = -1;
        int containsG = -1;
        int containsH = -1;
        //make sure whichever constraints are in the list are satisfied
        for (int i = 0; i < list.size(); i++) {
            switch(list.get(i).getName()) {
                case 'A':
                    containsA = i;
                    break;
                case 'B':
                    containsB = i;
                    break;
                case 'C':
                    containsC = i;
                    break;
                case 'D':
                    containsD = i;
                    break;
                case 'E':
                    containsE = i;
                    break;
                case 'F':
                    containsF = i;
                    break;
                case 'G':
                    containsG = i;
                    break;
                case 'H':
                    containsH = i;
                    break;
            }

            if (containsA >= 0 && containsG >= 0) {
                if (list.get(containsA).getValue() <= list.get(containsG).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsA >= 0 && containsH >= 0) {
                if (list.get(containsA).getValue() > list.get(containsH).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsF >= 0 && containsB >= 0) {
                if (Math.abs(list.get(containsF).getValue() - list.get(containsB).getValue()) != 1) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsG >= 0 && containsH >= 0) {
                if (list.get(containsG).getValue() >= list.get(containsH).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsG >= 0 && containsC >= 0) {
                if (Math.abs(list.get(containsG).getValue() - list.get(containsC).getValue()) != 1) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsH >= 0 && containsC >= 0) {
                if (Math.abs(list.get(containsH).getValue() - list.get(containsC).getValue()) %2 != 0) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsH >= 0 && containsD >= 0) {
                if (list.get(containsH).getValue() == list.get(containsD).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsD >= 0 && containsG >= 0) {
                if (list.get(containsD).getValue() < list.get(containsG).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsD >= 0 && containsC >= 0) {
                if (list.get(containsD).getValue() == list.get(containsC).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsE >= 0 && containsC >= 0) {
                if (list.get(containsE).getValue() == list.get(containsC).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsE >= 0 && containsD >= 0) {
                if (list.get(containsE).getValue() >= list.get(containsD).getValue() -1) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsE >= 0 && containsH >= 0) {
                if (list.get(containsE).getValue() == list.get(containsH).getValue() -2) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsG >= 0 && containsF >= 0) {
                if (list.get(containsG).getValue() == list.get(containsF).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsH >= 0 && containsF >= 0) {
                if (list.get(containsH).getValue() == list.get(containsF).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsC >= 0 && containsF >= 0) {
                if (list.get(containsC).getValue() == list.get(containsF).getValue()) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsD >= 0 && containsF >= 0) {
                if (list.get(containsD).getValue() == list.get(containsF).getValue() -1) {
                    numberOfFailedChecks++;
                    return false;
                }
            }

            if (containsE >= 0 && containsF >= 0) {
                if (Math.abs(list.get(containsE).getValue() - list.get(containsF).getValue()) %2 != 1) {
                    numberOfFailedChecks++;
                    return false;
                }
            }



        }



        return true;
    }

    /**
     * prints each variable and its value
     * @param list the list of values
     */
    void printState(List<Integer> list){

        for (int i = 0; i < list.size(); i++) {
            System.out.print(ordering[i] + "" +list.get(i) + "   ");
        }
        System.out.println();


    }


}
