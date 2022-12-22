package Cell;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class NormalCellTest {

    @Test
    void findNeighbors() {
        NormalCell testCell = new NormalCell(5,5);
        ArrayList<int[]> actualResult = new ArrayList<>();
        actualResult = testCell.findNeighbors(10,10);
        ArrayList<int[]> expectedRes = new ArrayList<>();
        Collections.addAll(expectedRes,new int[]{4,4}, new int[]{4,5},new int[]{4,6},
                new int[]{5,4},new int[]{5,6},new int[]{6,4},new int[]{6,5},
                new int[]{6,6});
        assertEquals(expectedRes.size(),actualResult.size());
        for(int i= 0;i<expectedRes.size();i++) {
            boolean flag = false;
            for (int j = 0; j < expectedRes.size(); j++) {
                if (expectedRes.get(i)[0] == actualResult.get(j)[0] && expectedRes.get(i)[1] == actualResult.get(j)[1]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}