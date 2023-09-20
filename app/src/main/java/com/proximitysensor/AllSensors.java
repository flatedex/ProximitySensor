package com.proximitysensor;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AllSensors extends AppCompatActivity {
    SensorManager sensorManager;
    ArrayAdapter<String> adapter;
    List<Sensor> deviceSensors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sensors);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> listSensorType = new ArrayList<>();

        if(deviceSensors.size() != 0) {
            int counter = 0;
            for (Sensor sensor : deviceSensors) {
                counter++;
                listSensorType.add(counter + " " + sensor.getName()+"\n"+
                        "Индекс типа: " + sensor.getType() + "\n" +
                        "Производитель: " + sensor.getVendor() + "\n" +
                        "Версия: " + sensor.getVersion() + "\n" +
                        "Мощность: " + sensor.getPower() + " мВт\n" +
                        "Задержка: [" + sensor.getMinDelay() + "," + sensor.getMaxDelay() + "] мкс\n" +
                        "Макс. значение: " + sensor.getMaximumRange() + "\n" +
                        "Точность: " + sensor.getResolution() + "\n"
                );
            }
        } else {
            Toast.makeText(this, "На устройстве нет датчиков", Toast.LENGTH_SHORT).show();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSensorType);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

}