// Analog Read with Serial Monitor

void setup() {
  // The setup routine runs once when you press reset

  Serial.begin(9600); //Initialize serial communication at 9600 bits per second
}

void loop() {
  // The loop routine runs over and over again forever

  int sensorValue = analogRead(A0); //Read input from analog pin 0

  Serial.print(sensorValue); // Prints the value from analog pin 0 

  delay(200); // Delay inbetween for stability 
}