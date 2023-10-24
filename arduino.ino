const int pot1 = A0;
const int pot2 = A1;
const int pot3 = A2;
int pot1Value;
int pot2Value;
int pot3Value;

void setup() {
Serial.begin(9600);
}

void loop() {
Serial.print("R: ");
pot1Value = analogRead(pot1);
Serial.println(pot1Value);

Serial.print("G: ");
pot2Value = analogRead(pot2);
Serial.println(pot2Value);

Serial.print("B: ");
pot3Value = analogRead(pot3);
Serial.println(pot3Value);

delay(300);
}