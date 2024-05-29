package edu.curtin.app;


public class Line {

    private int lineNum;
    private String lineData;

    public  Line() {
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