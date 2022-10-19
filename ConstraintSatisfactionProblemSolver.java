package model;

import java.util.ArrayList;
import java.util.List;

//A B C D E F G H
//0 1 2 3 4 5 6 7

/**
 * @author Joel Hempel
 * @since 10/18/2022
 * Solves a constraint satisfaction problem with 8 variables and 17 constraints using DFS with pruning
 */
public class ConstraintSatisfactionProblemSolver {
    ArrayList<Integer> currentStateList;
    ArrayList<ArrayList<Integer>> solutionList;
    Boolean isSolved = false;
    int numOfSolutionsFound = 0;
    int numberOfFailedChecks = 0;


    public ConstraintSatisfactionProblemSolver(){
        currentStateList = new ArrayList<>();
        solutionList = new ArrayList<ArrayList<Integer>>();
        findSolutionDFS(0, currentStateList);
        System.out.println("The number of failed checks is " + numberOfFailedChecks + " and the solutions (if any) are:\n");
        for (int i = 0; i < solutionList.size(); i++){
            printState(solutionList.get(i), true);
        }

    }



    /**
     * recursively prune paths and expand to further states if they increase the number of conditions met
     * @param stateListIndex index of the list of parameters
     * @return Boolean whether the next path makes the solution better
     */
    private Boolean findSolutionDFS(int stateListIndex, ArrayList<Integer> previousList){
        ArrayList<Integer> tmpState = new ArrayList<>(previousList);


        if (previousList.size() == 8 && allConstraintsSatisfied(tmpState)){
            solutionList.add(tmpState);
            this.numOfSolutionsFound++;
            return true;
        }


        tmpState.add(1);


        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }

        //test value of 2
        tmpState.set(stateListIndex, tmpState.get(stateListIndex) + 1);
        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }


        //test value of 3
        tmpState.set(stateListIndex, tmpState.get(stateListIndex) + 1);
        if (allConstraintsSatisfied(tmpState)) {
            findSolutionDFS(stateListIndex + 1, tmpState);
        }

        //test value of 4
        tmpState.set(stateListIndex, tmpState.get(stateListIndex) + 1);
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
    public Boolean allConstraintsSatisfied(List<Integer> list){


        if (list.size() > 6 && list.get(0) <= list.get(6)) {
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 7 && list.get(0) > list.get(7)) {
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 5 && Math.abs(list.get(5)-list.get(1)) != 1) {
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 7 && list.get(6) >= list.get(7)) {
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 6 && Math.abs(list.get(6) - list.get(2)) != 1){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 7 && Math.abs(list.get(7)- list.get(2)) % 2 != 0){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 7 && list.get(7) == list.get(3)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 6 && list.get(3) < list.get(6)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 3 && list.get(3) == list.get(2)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 4 && list.get(4) == list.get(2)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 4 && list.get(4) >= list.get(3)-1){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 7 && list.get(4) == list.get(7)-2){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 6 && list.get(6) == list.get(5)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 7 && list.get(7) == list.get(5)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 5 && list.get(2) == list.get(5)){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 5 && list.get(3) == list.get(5)-1){
            numberOfFailedChecks++;
            return false;
        }
        if (list.size() > 5 && Math.abs(list.get(4) - list.get(5)) % 2 != 1){
            numberOfFailedChecks++;
            return false;
        }



        return true;
    }

    void printState(List<Integer> list, Boolean solved){
        char tmpChar = 'A';
        for (int i = 0; i < list.size(); i++){
            System.out.print(tmpChar + " " + list.get(i) + "   ");
            tmpChar++;
        }
        System.out.println();


    }


}
