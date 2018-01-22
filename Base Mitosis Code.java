class Cell {
  PVector pos;
  float r;
  color c;

  Cell(PVector pos, float r, color c) { //New Cell class holding the position, radius, and colour
    this.pos = pos.copy(); //Sets position of the object
    this.r = r; //Sets radius of the object to the class value
    this.c = c; //Sets radius of the object to the class value
  }

  Cell() {
    this.pos  = new PVector(random(width), random(height)); //Starts the mother cell at a random location
    this.r = 60; //Base radius for the mother cells
    this.c =  color(random(100, 255), 0, random(100, 255), 100); //Assigns a random colour to the mother cell
  }


  boolean clicked(int x, int y) { //Checks to see if the cell is clicked
    float d = dist(this.pos.x, this.pos.y, x, y); //Checks the distance between the mouse and the center of the cell
    if (d < this.r) { //If the distance is the less than the radius
      return true; //Return that the cell has been clicked
    } else {
      return false; //Return that it hasn't been clicked
    }
  }

  Cell mitosis() { //Starts new cell 
    Cell cell = new Cell(this.pos, this.r*0.8, this.c); //Creates a new cell. Cell location is the same as the mother cell but radius is decreased by 20%. Colour is the same.
    return cell; //Return the data of the new cell
  }

  void move() { //Moves the cell in a random direction by the distance indicated by vel.
    PVector vel = PVector.random2D(); //Generates a random vector
    this.pos.add(vel); //Pushes the cell in a random direction slightly
  }

  void show() {
    noStroke(); //Turns off stroke
    fill(this.c); //Fills with new colour
    ellipse(this.pos.x, this.pos.y, this.r, this.r); //Creates the cell at a the new location
  }
}

ArrayList<Cell> cells = new ArrayList<Cell>(); //Holds and creates the new cells' information

void setup() {
  size(700, 700);
  cells.add(new Cell()); //Starts up mother cell 1
  cells.add(new Cell()); //Starts up mother cell 2
}

void draw() {
 background(200); //Background colour
 for (Cell c : cells) { //For every cell...
   c.move(); //...move it slightly...
   c.show(); //...and show its new position
 }
}

void mousePressed() {
  for (int i = cells.size()-1; i >= 0; i--) { //Goes through each cell
    Cell c = cells.get(i); //Gets the information for the cell
    if (c.clicked(mouseX, mouseY)) { //Checks to see if it was clicked
      cells.add(c.mitosis()); //Adds 2 cells using the mitosis function
