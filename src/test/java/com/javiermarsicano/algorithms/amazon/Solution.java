package com.javiermarsicano.algorithms.amazon;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{

    @Test
    public void testStores() {
        List<List<Integer>> grid = getGridWithZeroes(5, 4);

        grid.get(1).set(2,1);
        grid.get(1).set(3,1);
        grid.get(3).set(2,1);
        grid.get(3).set(1,1);

        int result = numberAmazonGoStores(grid.size(), grid.get(0).size(), grid);

        assertEquals(2,result);
    }

    @Test
    public void testStores2() {
        List<List<Integer>> grid = getGridWithZeroes(5, 4);

        grid.get(0).set(0,1);
        grid.get(0).set(1,1);
        grid.get(1).set(2,1);
        grid.get(3).set(0,1);
        grid.get(3).set(2,1);
        grid.get(3).set(3,1);
        grid.get(4).set(0,1);
        grid.get(4).set(1,1);
        grid.get(4).set(2,1);
        grid.get(4).set(3,1);

        int result = numberAmazonGoStores(grid.size(), grid.get(0).size(), grid);

        assertEquals(3,result);
    }

    @Test
    public void testServers() {
        List<List<Integer>> grid = getGridWithZeroes(4, 5);

        grid.get(0).set(1,1);
        grid.get(0).set(2,1);
        grid.get(0).set(4,1);
        grid.get(1).set(1,1);
        grid.get(1).set(3,1);
        grid.get(2).set(4,1);
        grid.get(3).set(1,1);

        int days = minimumDays(grid.size(), grid.get(0).size(), grid);

        assertEquals(2, days);
    }

    @NotNull
    private List<List<Integer>> getGridWithZeroes(int rows, int cols) {
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                grid.get(i).add(0);
            }
        }
        return grid;
    }

    /**
     Amazon plans to open Amazon Go stores downtown in the city of Techlandia. Downtown Techlandia consists of city blocks,
     represented as a grid of M*N blocks. Each block represents either building denoted 1 or open area denoted by 0. Adjacent
     blocks with value 1 are considered clusters of building. Diagonal blocks with value 1 are not considered part of the
     same cluster. Amazon plans to have an Amazon Go store in each cluster of building.
     Wrke an algorithm to find the number of stores that Amazon can open in downtown Techlandia.
     input
     - row; an integer representing the number of rows in the grid;
     - column, an integer representing the number, columns in the grid;
     grid a two-dimensional integer array representing downtown Techlandia.

     Output Returns an integer representing the number of Amazon Go stores that Amazon could build in downtown Techlandia
     * */

    private static final int VISITED_MARK = -1;

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int numberAmazonGoStores(int rows, int column, List<List<Integer> > grid)
    {
        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == 1) {
                    count++;
                    markCluster(i,j, grid);
                }
            }
        }

        return count;
    }

    void markCluster(int row, int col, List<List<Integer> > grid) {
        if (grid.get(row).get(col) == 1) {
            grid.get(row).set(col, VISITED_MARK);

            if (row > 0) { //move one block up
                markCluster(row-1, col, grid);
            }
            if (row < grid.size()-1) { //move one block down
                markCluster(row+1, col, grid);
            }
            if (col < grid.get(row).size()-1) { //move one block right
                markCluster(row, col+1, grid);
            }
            if (col > 0) { //move one block left
                markCluster(row, col-1, grid);
            }
        }
    }
    // METHOD SIGNATURE ENDS

    /**
     We emphasize the submission of a fully working code over partially correct but efficient code.

     Amazon has 2D grid of cell towers. All servers need to be updated to the latest software version. Servers that already have
     the update should not be updated again. Servers are in the form of a 2D array of 0 and 1 where the value 0 represents an out
     of date server and the value of 1 represents an updated server. In a single day, an updated server can push the update tho
     each of its adjacent out of date servers. An adjacent server is either on the left, right, above or below a given server.
     Once the server receives the update, it becomes updated and can push the update to its adjacent servers on the following day.
     Given the 2D array representing the current status of its servers.
     Write an algorithm that will determine the minimum number of days required to push the update to all the available servers

     input
     - row; an integer representing Me number of rows in the grid;
     - column; an integer representing the number of columns in the grid,
     - grid an integer array representing the current status of servers. The value 0 represents an out of date server and the
     value 1 represents an updated server.

     Output Return an integer representing the minimum number of days required to update all the servers in the grid network.
     Return -1 if all the available servers cannot be updated
     Note
     Diagonally placed cells are not considered neighbors
     * */

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumDays(int rows, int columns, List<List<Integer> > grid)
    {
        int count = 0;
        boolean found = true;
        while (found){
            found = false;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) == 1) {
                        updateServer(i,j, grid);
                    } else if (grid.get(i).get(j) == 0) {
                        found = true;
                    }
                }
            }
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) == -1) {
                        grid.get(i).set(j,1);
                    }
                }
            }
            count++;

        }

        return count;
    }

    void updateServer(int row, int col, List<List<Integer> > grid) {
        if (row > 0 && grid.get(row-1).get(col) == 0) {
            grid.get(row-1).set(col,-1);
        }
        if (row < grid.size()-1 && grid.get(row+1).get(col) == 0) {
            grid.get(row+1).set(col,-1);
        }
        if (col < grid.get(row).size()-1 && grid.get(row).get(col+1) == 0) {
            grid.get(row).set(col+1,-1);
        }
        if (col > 0 && grid.get(row).get(col-1) == 0) {
            grid.get(row).set(col-1,-1);
        }
    }

}