// Define constants for each potentiometer's analog pin
const int pot1 = A0;
const int pot2 = A1;
const int pot3 = A2;
const int pot4 = A3;

// Variables to store the current value of each potentiometer
int pot1Value = 0;
int pot2Value = 0;
int pot3Value = 0;
int pot4Value = 0;

// Variables to store the last known value of each potentiometer
int lastPot1Value = 0;
int lastPot2Value = 0;
int lastPot3Value = 0;
int lastPot4Value = 0;

void setup() {
  // Initialize serial communication at 9600 baud rate
  Serial.begin(9600);
}

void loop() {
  // Read the current value from each potentiometer
  pot1Value = analogRead(pot1);
  pot2Value = analogRead(pot2);
  pot3Value = analogRead(pot3);
  pot4Value = analogRead(pot4);

  // If the current value of pot1 differs from its last known value, send the new value over serial
  if(pot1Value != lastPot1Value) {
    Serial.print("Knob1: ");
    Serial.println(pot1Value);
    lastPot1Value = pot1Value;  // Update the last known value
  }

  // If the current value of pot2 differs from its last known value, send the new value over serial
  if(pot2Value != lastPot2Value) {
    Serial.print("Knob2: ");
    Serial.println(pot2Value);
    lastPot2Value = pot2Value;  // Update the last known value
  }

  // If the current value of pot3 differs from its last known value, send the new value over serial
  if(pot3Value != lastPot3Value) {
    Serial.print("Knob3: ");
    Serial.println(pot3Value);
    lastPot3Value = pot3Value;  // Update the last known value
  }

  // If the current value of pot4 differs from its last known value, send the new value over serial
  if(pot4Value != lastPot4Value) {
    Serial.print("Knob4: ");
    Serial.println(pot4Value);
    lastPot4Value = pot4Value;  // Update the last known value
  }

  // Add a delay to reduce the frequency of readings and serial transmissions
  delay(300);
}
