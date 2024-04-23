package figures;

//Bryan Villarreal/Adrian Torres: Implemented the rook class
//Modified: 3/6/24 11:09 PM
public class Rook extends Figure {
  // empty constructor
  public Rook() {
  }

  // constructor with parameters
  public Rook(PieceType type, Colorr color, Column column, int row) {
    super(type, color, column, row);
  }

  @Override
  public String toString() {
    return (this.type + " -- Color: " + this.color + ", Column: " + this.column + ", Row: " + this.row);
  }// end toString

  @Override
  public boolean moveTo(Column newColumn, int newRow) {
    return this.column == newColumn ^ this.row == newRow;
  }// end moveto
}// end Rook