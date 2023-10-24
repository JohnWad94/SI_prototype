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
        switch(parts[0]) {  // Check the identifier part
          case "R":
            sqColor1 = map(value, 0, 1023, 0, 255);
            break;
          case "G":
            sqColor2 = map(value, 0, 1023, 0, 255);
            break;
          case "B":
            sqColor3 = map(value, 0, 1023, 0, 255);
            break;
        }
      }
    }
  }
  
  background(255);  // Set background to white or another color
  
  // Draw the rounded square (or circle) first
  fill(sqColor1, sqColor2, sqColor3);  // Set fill color based on RGB values
  float rectSize = sqColor2;  // Set the size of the square
  float maxRadius = rectSize / 2;  // Maximum corner radius (when it becomes a circle)
  float cornerRadius = map(sqColor1, 0, 255, 0, maxRadius);  // Map R value to corner radius
  rectMode(CENTER);  // Draw rect from center
  rect(width/2, height/2, rectSize, rectSize, cornerRadius);  // Draw rounded square
  
  // Apply blur effect
  float minBlur = 5;
  float maxBlur = 20;  // Set a maximum blur value
  float blurAmount = map(sqColor3, 0, 255, minBlur, maxBlur);  // Map B value to blur amount
  filter(BLUR, blurAmount);  // Apply blur
}