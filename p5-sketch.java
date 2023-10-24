import processing.serial.*;
import processing.sound.*;

Serial mySerial;
String serial_data_string_1;
float serial_data_float_1;
String serial_data_string_2;
float serial_data_float_2;
String serial_data_string_3;
float serial_data_float_3;
String portName;

float sqColor1; // Variable to store background color
float sqColor2; // Variable to store background color
float sqColor3; // Variable to store background color

void setup() {
  size(500, 500);
  sqColor1 = 0; // Initial background color
  
  portName = Serial.list()[1]; // Adjust the index based on your setup
  mySerial = new Serial(this, portName, 9600);
  
  
}

void draw() {
  while (mySerial.available() > 0) {
    String serialData = mySerial.readStringUntil('\n');
    if (serialData != null) {
      serialData = trim(serialData);  // Remove any whitespace
      String[] parts = split(serialData, ':');  // Split the identifier from the value
      if (parts.length == 2) {
        float value = float(parts[1]);  // Convert the value part to a float
        value = map(value, 0, 1023, 0, 255);  // Map the value to the 0-255 range
        switch(parts[0]) {  // Check the identifier part
          case "R":
            sqColor1 = value;
            break;
          case "G":
            sqColor2 = value;
            break;
          case "B":
            sqColor3 = value;
            break;
        }
      }
    }
  }
  background(sqColor1, sqColor2, sqColor3);
  println("R: ", sqColor1, "G: ", sqColor2, "B: ", sqColor3);
}