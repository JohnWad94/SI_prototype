import processing.serial.*;

Serial mySerial;
String portName;

float sq1Color;      // Variable to store color value of the first square
float sq1Width;      // Variable to store first rectangle width
float sq1BlurAmount; // Variable to store blur amount of the first square

float sq2Color;      // Variable to store color value of the second square
float sq2Width;      // Variable to store second rectangle width
float sq2BlurAmount; // Variable to store blur amount of the second square
float sq2Roundness;  // Variable to store roundness (corner radius) of the second square

void setup() {
  size(1290, 960);
  
  portName = Serial.list()[4];  // Adjust the index based on your setup
  mySerial = new Serial(this, portName, 9600);
  
  colorMode(HSB, 1023);  // Set color mode to HSB, with a range of 0 to 1023 for hue
}

void draw() {
  colorMode(RGB);       // Switch to RGB mode
  background(255);      // Set background to white in RGB mode
  colorMode(HSB, 1023); // Switch back to HSB mode for the knobs' data processing and rectangle coloring
  
  while (mySerial.available() > 0) {
    String serialData = mySerial.readStringUntil('\n');
    if (serialData != null) {
      serialData = trim(serialData);  // Remove any whitespace
      String[] parts = split(serialData, ':');  // Split the identifier from the value
      if (parts.length == 2) {
        float value = float(parts[1]);  // Convert the value part to a float
        switch(parts[0]) {  // Check the identifier part
          case "Knob1":
            sq1Color = value;  // Store the value directly, to be used as hue
            break;
          case "Knob2":
            sq1Width = map(value, 0, 1023, 0, width);  // Map Knob2 value to first rectangle width
            sq1BlurAmount = sq1Width * 0.05;  // Calculate blur amount as a percentage of width
            break;
          case "Knob3":
            sq2Color = value;  // Store the value directly, to be used as hue for the second square
            sq2Roundness = map(value, 0, 1023, 0, 50);  // Map Knob3 value to roundness of the second square
            break;
          case "Knob4":
            sq2Width = map(value, 0, 1023, 0, width);  // Map Knob4 value to second rectangle width
            sq2BlurAmount = sq2Width * 0.05;  // Calculate blur amount as a percentage of width
            break;
        }
      }
    }
  }
  
  float rectHeight = height;  // Set the height of the rectangle to the canvas height
  rectMode(CENTER);  // Draw rect from center
  
  // Draw the first rectangle with its blur
  fill(sq1Color, 255, 255);
  rect(width/3, height/2, sq1Width, rectHeight, sq2Roundness);
  filter(BLUR, sq1BlurAmount);  // Apply blur amount for first square
  
  // Draw the second rectangle with its blur
  fill(sq2Color, 255, 255);
  rect(2 * width/3, height/2, sq2Width, rectHeight, sq2Roundness);
  filter(BLUR, sq2BlurAmount);  // Apply blur amount for second square
}
