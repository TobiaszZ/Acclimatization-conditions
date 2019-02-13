package environmentalConditions.model;

import java.util.Date;

public class EnviromentalDataFromFileCsv {

    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Date date;


    public EnviromentalDataFromFileCsv(Double temperature, Double pressure, Double humidity, Date date) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.date = date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EnviromentalDataFromFileCsv{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", date=" + date +
                '}';
    }
}
