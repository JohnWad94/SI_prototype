// Import the p5.serialport library
let serial;
let portName;
let bgColor = 0;

function setup() {
  createCanvas(500, 500);
  serial = new p5.SerialPort();  // Make a new instance of the SerialPort object
  serial.on('list', printList);  // Set a callback function for the serialport list event
  serial.on('connected', serverConnected);  // Set a callback function for the serialport connected event
  serial.on('data', serialEvent);  // Set a callback function for the serialport data event
  serial.list();  // List all the serial ports
}

function serverConnected() {
  console.log('connected to server.');
}

function printList(portList) {
  // Assuming your Arduino is connected to the first port in the list
  portName = portList[0];
  serial.open(portName);  // Open a connection to the serialport
}

function serialEvent() {
  let serialDataString = serial.readStringUntil('\n');  // Read data from the serial port
  if (serialDataString.length > 0) {
    let serialDataFloat = parseFloat(serialDataString);
    bgColor = map(serialDataFloat, 0, 1023, 0, 255);
  }
}

function draw() {
  background(bgColor);
}
