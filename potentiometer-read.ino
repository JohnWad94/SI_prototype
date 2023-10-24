const int potentiometerPin = A0; 
int potentiometerValue; 

void setup() { 
  Serial.begin(9600); 
  } 


void loop() { 
  potentiometerValue = analogRead(potentiometerPin); 
  Serial.println(potentiometerValue); 
  delay(300); 
  }