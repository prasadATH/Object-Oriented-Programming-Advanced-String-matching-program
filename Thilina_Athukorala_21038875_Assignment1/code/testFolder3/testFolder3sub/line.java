import java.util.*;

public class line {

    private int lineNum;
    private String lineData;

    public void line() {
        lineNum = 0;
        lineData = "";

    }

    public int getLineNum() {
        return lineNum;
    }

    public String getLineData() {
        return lineData;
    }

    public void setLineNum(int inLineNum) {
        lineNum = inLineNum;
    }

    public void setLineData(String inLineData) {
        lineData = inLineData;
    }

}