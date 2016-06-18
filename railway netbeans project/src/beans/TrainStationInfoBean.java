/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Harish
 */
public class TrainStationInfoBean {

    private String stationCode, time;
    private int trainNo, stationOrder;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public int getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(int stationOrder) {
        this.stationOrder = stationOrder;
    }
}
