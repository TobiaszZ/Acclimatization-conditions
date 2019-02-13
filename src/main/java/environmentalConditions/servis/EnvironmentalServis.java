package environmentalConditions.servis;

import environmentalConditions.model.EnviromentalData;
import environmentalConditions.model.EnviromentalDataFromFileCsv;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class EnvironmentalServis {

    public String getData() {

        Connection connect = Jsoup.connect("http://localhost:8080/test");
//        Connection connect = Jsoup.connect("http://10.10.2.168");
        String enviromentalData = "test";
        try {
            Document document = connect.get();
            Elements links = document.select("body");
            enviromentalData = links.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enviromentalData;
    }


    public EnviromentalData filterData(String data) {
        Pattern pattern = Pattern.compile("([0-9]+,[0-9])");
        Matcher matcher = pattern.matcher(data);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        EnviromentalData currentData = new EnviromentalData(list.get(0), list.get(1), list.get(2));
        return currentData;
    }


    public void saveSpecificDataToCsvFile(List<EnviromentalDataFromFileCsv> enviromentalDataFromFileCsv, String from, String to) {

        try {
            Path dataPathCsv;
            if (from.equals(to)) {
                dataPathCsv = Paths.get(from + ".csv");
            } else {
                dataPathCsv = Paths.get(from + " " + to + ".csv");
            }


            if (!(Files.exists(dataPathCsv))) {
                Files.createFile(dataPathCsv);
                Files.write(dataPathCsv, ("Temperature C" + ";" + "Pressure hPa" + ";" + "Humidity %" + ";" + "Date" + "\n").getBytes());
            }
            for (EnviromentalDataFromFileCsv data : enviromentalDataFromFileCsv) {
                Files.write(dataPathCsv, (data.getTemperature() + ";" + data.getPressure() + ";" + data.getHumidity() + ";" + formatDate(data.getDate()) + "\n").getBytes(), StandardOpenOption.APPEND);
            }


        } catch (IOException ex) {
            System.out.println("Błąd zapisu do pliku.");
        }
    }


    public void saveToFile(EnviromentalData currentData) {

        try {
            Path dataPath = Paths.get("environmentalData.txt");
            Path dataPathCsv = Paths.get("environmentalData.csv");
            if (!(Files.exists(dataPath))) {
                Files.createFile(dataPath);
                Files.write(dataPath, ("Temperature C" + "         " + "Pressure hPa" + "        " + "Humidity %" + "          " + "Date" + "\n").getBytes());
            }
            Files.write(dataPath, ("     " + currentData.getTemperature() + "                  " + currentData.getPressure() + "              " + currentData.getHumidity() + "         " + formatDate(currentData.getDate()) + "\n").getBytes(), StandardOpenOption.APPEND);

            if (!(Files.exists(dataPathCsv))) {
                Files.createFile(dataPathCsv);
                Files.write(dataPathCsv, ("Temperature C" + ";" + "Pressure hPa" + ";" + "Humidity %" + ";" + "Date" + "\n").getBytes());
            }
            Files.write(dataPathCsv, (currentData.getTemperature() + ";" + currentData.getPressure() + ";" + currentData.getHumidity() + ";" + formatDate(currentData.getDate()) + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Błąd zapisu do pliku.");
        }
    }

    public String formatDate(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("pl", "PL"));

        String dateString = simpleDateFormat.format(date);
        return dateString;
    }


    public List<EnviromentalDataFromFileCsv> openFromFile() {
        List<EnviromentalDataFromFileCsv> resultList = new ArrayList<>();
        List<String> readAllLines = new ArrayList<>();
        Path dataPathCsv = Paths.get("environmentalData.csv");
        try {
            readAllLines = Files.readAllLines(dataPathCsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Double temperature = null;
        Double pressure = null;
        Double humidity = null;
        Date date = null;
        for (int i = 0; i < readAllLines.size(); i++) {


            if (i >= 1) {

                String[] split = readAllLines.get(i).split(";");
                temperature = Double.parseDouble(split[0].replace(",", "."));
                pressure = Double.parseDouble((split[1].replace(",", ".")));
                humidity = Double.parseDouble(split[2].replace(",", "."));
                date = null;
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat =
                        new SimpleDateFormat(pattern, new Locale("pl", "PL"));
                try {
                    date = simpleDateFormat.parse(split[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                resultList.add(new EnviromentalDataFromFileCsv(temperature, pressure, humidity, date));
            }


        }


        return resultList;
    }

    public List<EnviromentalDataFromFileCsv> getSpecificData(String from, String to, List<EnviromentalDataFromFileCsv> data) {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("pl", "PL"));
        List<EnviromentalDataFromFileCsv> collect;
        try {
            final Date dateFrom = simpleDateFormat.parse(from);
            final Date dateTo = simpleDateFormat.parse(to);
            if (dateFrom.equals(dateTo)) {
                collect = data.stream().filter(s -> (s.getDate().after(dateFrom) || s.getDate().equals(dateFrom)))
                        .collect(Collectors.toList());
            } else {
                collect = data.stream().filter(s -> (s.getDate().after(dateFrom) || s.getDate().equals(dateFrom)))
                        .filter(d -> (d.getDate().before(dateTo) || d.getDate().equals(dateTo)))
                        .collect(Collectors.toList());
            }

            return collect;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }


}
