package com.queryresolvingsystem.bean;

public class RaisedComplain {
    private int complainId;
    private String complainType;
    private String complainDetails;
    private String raisedBy;
    private String solveBy;
    private String status;

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public String getComplainDetails() {
        return complainDetails;
    }

    public void setComplainDetails(String complainDetails) {
        this.complainDetails = complainDetails;
    }

    public String getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(String raisedBy) {
        this.raisedBy = raisedBy;
    }

    public String getSolveBy() {
        return solveBy;
    }

    public void setSolveBy(String solveBy) {
        this.solveBy = solveBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RaisedComplain{" +
                "complainId=" + complainId +
                ", complainType='" + complainType + '\'' +
                ", complainDetails='" + complainDetails + '\'' +
                ", raisedBy='" + raisedBy + '\'' +
                ", solveBy='" + solveBy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
